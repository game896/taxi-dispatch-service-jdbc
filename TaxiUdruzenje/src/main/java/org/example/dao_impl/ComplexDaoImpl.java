package org.example.dao_impl;

import org.example.dao.ComplexDao;
import org.example.dto.HardQueryOneDto;
import org.example.dto.HardQueryTwoDto;
import org.example.dto.SimpleQueryDto;
import org.example.taxi_udruzenje_connection.ConnectionUtil_HikariCP;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplexDaoImpl implements ComplexDao {

    @Override
    public List<SimpleQueryDto> jednostavanQuery() throws SQLException {
        String query = """
                SELECT v.mbrZ,
                       z.imeZ,
                       z.preZ,
                       COUNT(n.Klijent_mbrKl) AS broj_voznji,
                       SUM(n.cenVoz) AS ukupna_zarada
                FROM Vozac v
                JOIN Zaposleni z ON v.mbrZ = z.mbrZ
                LEFT JOIN NarucujeVoznju n ON v.mbrZ = n.ObavljaVoznje_Vozac_mbrZ
                GROUP BY v.mbrZ, z.imeZ, z.preZ
                ORDER BY ukupna_zarada DESC
                """;

        List<SimpleQueryDto> result = new ArrayList<>();

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer mbrZ = rs.getInt("mbrZ");
                String imeZ = rs.getString("imeZ");
                String preZ = rs.getString("preZ");
                Integer brojVoznji = rs.getInt("broj_voznji");
                BigDecimal ukupnaZarada = rs.getBigDecimal("ukupna_zarada");

                if (ukupnaZarada == null) {
                    ukupnaZarada = BigDecimal.ZERO;
                }

                result.add(new SimpleQueryDto(mbrZ, imeZ, preZ, brojVoznji, ukupnaZarada));
            }
        }

        return result;
    }

    @Override
    public List<HardQueryOneDto> tezakQueryJedan() throws SQLException {
        String query = """
                SELECT tg.nazTg,
                       COUNT(n.Klijent_mbrKl) AS broj_voznji,
                       SUM(n.cenVoz) AS prihod
                FROM TarifnaGrupa tg
                LEFT JOIN ObavljaVoznje ov ON tg.nazTg = ov.TarifnaGrupa_nazTg
                LEFT JOIN NarucujeVoznju n ON tg.nazTg = n.TarifnaGrupa_nazTg
                --WHERE tg.nazTG LIKE 'STANDARD'
                GROUP BY tg.nazTg
                HAVING SUM(n.cenVoz) > 1000
                ORDER BY prihod DESC
                """;

        List<HardQueryOneDto> result = new ArrayList<>();

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nazivTg = rs.getString("nazTg");
                Integer brojVoznji = rs.getInt("broj_voznji");
                BigDecimal prihod = rs.getBigDecimal("prihod");

                if (prihod == null) {
                    prihod = BigDecimal.ZERO;
                }

                result.add(new HardQueryOneDto(nazivTg, brojVoznji, prihod));
            }
        }

        return result;
    }

    @Override
    public List<HardQueryTwoDto> tezakQueryDva() throws SQLException {
        String query = """
                SELECT 
                    v.mbrZ AS Vozac_ID,
                    z.imeZ || ' ' || z.preZ AS ImePrezime,
                    nv.TarifnaGrupa_nazTg AS Tarifa,
                    COUNT(nv.Klijent_mbrKl) AS BrojVoznji,
                    AVG(nv.cenVoz) AS ProsecnaCena
                FROM 
                    NarucujeVoznju nv
                    LEFT JOIN Klijent k ON nv.Klijent_mbrKl = k.mbrKl
                    LEFT JOIN ObavljaVoznje ov ON nv.ObavljaVoznje_Vozac_mbrZ = ov.Vozac_mbrZ
                                              AND nv.Ov_nazTg = ov.TarifnaGrupa_nazTg
                    LEFT JOIN Vozac v ON nv.ObavljaVoznje_Vozac_mbrZ = v.mbrZ
                    LEFT JOIN Zaposleni z ON v.mbrZ = z.mbrZ
                WHERE
                    nv.cenVoz > 200
                GROUP BY 
                    v.mbrZ, z.imeZ, z.preZ, nv.TarifnaGrupa_nazTg
                HAVING 
                    COUNT(nv.Klijent_mbrKl) >= 1
                ORDER BY 
                    BrojVoznji DESC, ProsecnaCena DESC
                """;

        List<HardQueryTwoDto> result = new ArrayList<>();

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Integer vozacId = rs.getInt("Vozac_ID");
                String imePrezime = rs.getString("ImePrezime");
                String tarifa = rs.getString("Tarifa");
                Integer brojVoznji = rs.getInt("BrojVoznji");
                BigDecimal prosecnaCena = rs.getBigDecimal("ProsecnaCena");

                if (prosecnaCena == null) {
                    prosecnaCena = BigDecimal.ZERO;
                }

                result.add(new HardQueryTwoDto(vozacId, imePrezime, tarifa, brojVoznji, prosecnaCena));
            }
        }

        return result;
    }

    public boolean deleteKlijentTransactional(int mbrKl) {
        String deleteVoznji =
                "DELETE FROM NarucujeVoznju " +
                        "WHERE Klijent_mbrKl = ?";

        String deleteKlijent =
                "DELETE FROM Klijent " +
                        "WHERE mbrKl = ?";

        try (Connection connection = ConnectionUtil_HikariCP.getConnection()) {
            connection.setAutoCommit(false);

            try (
                    PreparedStatement psVoznji = connection.prepareStatement(deleteVoznji);
                    PreparedStatement psKlijent = connection.prepareStatement(deleteKlijent)
            ) {
                // brisanje voznji
                psVoznji.setInt(1, mbrKl);
                int voznjiDeleted = psVoznji.executeUpdate();

                // brisanje klijenta
                psKlijent.setInt(1, mbrKl);
                int klijentDeleted = psKlijent.executeUpdate();

                if (klijentDeleted == 0) {
                    connection.rollback();
                    System.out.println("Klijent ne postoji.");
                    return false;
                }

                connection.commit();
                System.out.println("Obrisano voznji: " + voznjiDeleted);
                System.out.println("Obrisan klijent: " + klijentDeleted);

                return true;

            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Transakcija neuspesna: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Povezivanje sa bazom neuspesno: " + e.getMessage());
            return false;
        }
    }
}
