package org.example.dao_impl;

import org.example.dao.TarifnaGrupaDao;
import org.example.model.TarifnaGrupa;
import org.example.taxi_udruzenje_connection.ConnectionUtil_HikariCP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarifnaGrupaDaoImpl implements TarifnaGrupaDao {

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) FROM TarifnaGrupa";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) return rs.getInt(1);
            return -1;
        }
    }

    @Override
    public boolean delete(TarifnaGrupa entity) throws SQLException {
        return deleteById(entity.getNazTg());
    }

    @Override
    public int deleteAll() throws SQLException {
        String query = "DELETE FROM TarifnaGrupa";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            return ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(String id) throws SQLException {
        String query = "DELETE FROM TarifnaGrupa WHERE nazTg=?";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean existsById(String id) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return existsByIdTry(conn, id);
        }
    }

    private boolean existsByIdTry(Connection conn, String id) throws SQLException {
        String query = "SELECT 1 FROM TarifnaGrupa WHERE nazTg=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.isBeforeFirst();
            }
        }
    }

    @Override
    public Iterable<TarifnaGrupa> findAll() throws SQLException {
        String query = "SELECT nazTg, opisTG FROM TarifnaGrupa";
        List<TarifnaGrupa> list = new ArrayList<>();

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapResultSetToTarifnaGrupa(rs));
            }
        }

        return list;
    }

    @Override
    public TarifnaGrupa findById(String id) throws SQLException {
        String query = "SELECT nazTg, opisTG FROM TarifnaGrupa WHERE nazTg=?";
        TarifnaGrupa tg = null;

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tg = mapResultSetToTarifnaGrupa(rs);
                }
            }
        }

        return tg;
    }

    @Override
    public Iterable<TarifnaGrupa> findAllById(Iterable<String> ids) throws SQLException {
        List<TarifnaGrupa> list = new ArrayList<>();
        String query = "SELECT nazTg, opisTG FROM TarifnaGrupa WHERE nazTg=?";

        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            for (String id : ids) {
                ps.setString(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        list.add(mapResultSetToTarifnaGrupa(rs));
                    }
                }
            }
        }

        return list;
    }


    @Override
    public boolean save(TarifnaGrupa entity) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return saveTransactional(conn, entity);
        }
    }

    @Override
    public int saveAll(Iterable<TarifnaGrupa> entities) throws SQLException {
        int rowsSaved = 0;

        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            conn.setAutoCommit(false);

            for (TarifnaGrupa entity : entities) {
                if (saveTransactional(conn, entity)) rowsSaved++;
            }

            conn.commit();
        }

        return rowsSaved;
    }

    private boolean saveTransactional(Connection conn, TarifnaGrupa entity) throws SQLException {
        boolean exists = existsByIdTry(conn, entity.getNazTg());

        String insert = "INSERT INTO TarifnaGrupa (nazTg, opisTG) VALUES (?, ?)";
        String update = "UPDATE TarifnaGrupa SET opisTG=? WHERE nazTg=?";

        try (PreparedStatement ps = conn.prepareStatement(exists ? update : insert)) {
            if (exists) {
                ps.setString(1, entity.getOpisTG());
                ps.setString(2, entity.getNazTg());
            } else {
                ps.setString(1, entity.getNazTg());
                ps.setString(2, entity.getOpisTG());
            }

            return ps.executeUpdate() == 1;
        }
    }

    private TarifnaGrupa mapResultSetToTarifnaGrupa(ResultSet rs) throws SQLException {
        return new TarifnaGrupa(
                rs.getString("nazTg"),
                rs.getString("opisTG")
        );
    }
}
