package org.example.dao_impl;
import org.example.dao.KlijentDao;
import org.example.model.Klijent;
import org.example.taxi_udruzenje_connection.ConnectionUtil_HikariCP;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class KlijentDaoImpl implements KlijentDao {

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) FROM Klijent";
        try (Connection conn = org.example.taxi_udruzenje_connection.ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);
            return -1;
        }
    }

    @Override
    public boolean delete(Klijent entity) throws SQLException {
        return deleteById(entity.getMbrKl());
    }

    @Override
    public int deleteAll() throws SQLException {
        String query = "DELETE FROM Klijent";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            return ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM Klijent WHERE mbrKl=?";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean existsById(Integer id) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return existsByIdTry(conn, id);
        }
    }

    private boolean existsByIdTry(Connection conn, Integer id) throws SQLException {
        String query = "SELECT 1 FROM Klijent WHERE mbrKl=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.isBeforeFirst();
            }
        }
    }

    @Override
    public Iterable<Klijent> findAll() throws SQLException {
        String query = "SELECT mbrKl, emailKl, brtelKl, imeKl, przKl, Dispecer_mbrZ FROM Klijent";

        List<Klijent> list = new ArrayList<>();

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToKlijent(rs));
            }
        }

        return list;
    }

    @Override
    public Klijent findById(Integer id) throws SQLException {
        String query = "SELECT mbrKl, emailKl, brtelKl, imeKl, przKl, Dispecer_mbrZ FROM Klijent WHERE mbrKl=?";

        Klijent klijent = null;

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    klijent = mapResultSetToKlijent(rs);
                }
            }
        }

        return klijent;
    }

    @Override
    public Iterable<Klijent> findAllById(Iterable<Integer> ids) throws SQLException {

        List<Klijent> list = new ArrayList<>();

        String query = "SELECT mbrKl, emailKl, brtelKl, imeKl, przKl, Dispecer_mbrZ FROM Klijent WHERE mbrKl=?";

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (Integer id : ids) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        list.add(mapResultSetToKlijent(rs));
                    }
                }
            }
        }

        return list;
    }


    @Override
    public boolean save(Klijent entity) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return saveTransactional(conn, entity);
        }
    }

    @Override
    public int saveAll(Iterable<Klijent> entities) throws SQLException {
        int rowsSaved = 0;

        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            conn.setAutoCommit(false);

            for (Klijent entity : entities) {
                if (saveTransactional(conn, entity)) rowsSaved++;
            }

            conn.commit();
        }

        return rowsSaved;
    }

    private boolean saveTransactional(Connection conn, Klijent entity) throws SQLException {

        String insert = "INSERT INTO Klijent (mbrKl, emailKl, brtelKl, imeKl, przKl, Dispecer_mbrZ) VALUES (?, ?, ?, ?, ?, ?)";

        String update = "UPDATE Klijent SET emailKl=?, brtelKl=?, imeKl=?, przKl=?, Dispecer_mbrZ=? WHERE mbrKl=?";

        boolean exists = existsByIdTry(conn, entity.getMbrKl());

        try (PreparedStatement ps = conn.prepareStatement(exists ? update : insert)) {

            if (exists) {
                int i = 1;
                ps.setString(i++, entity.getEmailKl());
                ps.setString(i++, entity.getBrtelKl());
                ps.setString(i++, entity.getImeKl());
                ps.setString(i++, entity.getPrzKl());
                ps.setInt(i++, entity.getDispecerMbrZ());
                ps.setInt(i++, entity.getMbrKl());
            } else {
                int i = 1;
                ps.setInt(i++, entity.getMbrKl());
                ps.setString(i++, entity.getEmailKl());
                ps.setString(i++, entity.getBrtelKl());
                ps.setString(i++, entity.getImeKl());
                ps.setString(i++, entity.getPrzKl());
                ps.setInt(i++, entity.getDispecerMbrZ());
            }

            return ps.executeUpdate() == 1;
        }
    }

    private Klijent mapResultSetToKlijent(ResultSet rs) throws SQLException {
        return new Klijent(
                rs.getInt("mbrKl"),
                rs.getString("emailKl"),
                rs.getString("brtelKl"),
                rs.getString("imeKl"),
                rs.getString("przKl"),
                rs.getInt("Dispecer_mbrZ")
        );
    }
}
