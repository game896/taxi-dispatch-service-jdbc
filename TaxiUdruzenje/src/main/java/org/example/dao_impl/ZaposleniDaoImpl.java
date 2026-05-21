package org.example.dao_impl;

import org.example.dao.ZaposleniDao;
import org.example.model.Zaposleni;
import org.example.taxi_udruzenje_connection.ConnectionUtil_HikariCP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ZaposleniDaoImpl implements ZaposleniDao {

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) FROM Zaposleni";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return -1;
        }
    }

    @Override
    public boolean delete(Zaposleni entity) throws SQLException {
        return deleteById(entity.getMbrZ());
    }

    @Override
    public int deleteAll() throws SQLException {
        String query = "DELETE FROM Zaposleni";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM Zaposleni WHERE mbrZ=?";
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
        String query = "SELECT 1 FROM Zaposleni WHERE mbrZ=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.isBeforeFirst();
            }
        }
    }

    @Override
    public Iterable<Zaposleni> findAll() throws SQLException {
        String query = "SELECT mbrZ, imeZ, preZ, pltZ, katZ FROM Zaposleni";
        List<Zaposleni> list = new ArrayList<>();
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToZaposleni(rs));
            }
        }
        return list;
    }

    @Override
    public Zaposleni findById(Integer id) throws SQLException {
        String query = "SELECT mbrZ, imeZ, preZ, pltZ, katZ FROM Zaposleni WHERE mbrZ=?";
        Zaposleni zap = null;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) zap = mapResultSetToZaposleni(rs);
            }
        }
        return zap;
    }

    @Override
    public Iterable<Zaposleni> findAllById(Iterable<Integer> ids) throws SQLException {
        List<Zaposleni> list = new ArrayList<>();
        String query = "SELECT mbrZ, imeZ, preZ, pltZ, katZ FROM Zaposleni WHERE mbrZ=?";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            for (Integer id : ids) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) list.add(mapResultSetToZaposleni(rs));
                }
            }
        }
        return list;
    }

    @Override
    public boolean save(Zaposleni entity) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return saveTransactional(conn, entity);
        }
    }

    @Override
    public int saveAll(Iterable<Zaposleni> entities) throws SQLException {
        int rowsSaved = 0;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            conn.setAutoCommit(false);
            for (Zaposleni entity : entities) {
                if (saveTransactional(conn, entity)) rowsSaved++;
            }
            conn.commit();
        }
        return rowsSaved;
    }

    private boolean saveTransactional(Connection conn, Zaposleni entity) throws SQLException {
        boolean exists = existsByIdTry(conn, entity.getMbrZ());

        String insert = "INSERT INTO Zaposleni (mbrZ, imeZ, preZ, pltZ, katZ) VALUES (?, ?, ?, ?, ?)";
        String update = "UPDATE Zaposleni SET imeZ=?, preZ=?, pltZ=?, katZ=? WHERE mbrZ=?";

        try (PreparedStatement ps = conn.prepareStatement(exists ? update : insert)) {
            if (exists) {
                int i = 1;
                ps.setString(i++, entity.getImeZ());
                ps.setString(i++, entity.getPreZ());
                ps.setDouble(i++, entity.getPltZ());
                ps.setString(i++, entity.getKatZ());
                ps.setInt(i, entity.getMbrZ());
            } else {
                int i = 1;
                ps.setInt(i++, entity.getMbrZ());
                ps.setString(i++, entity.getImeZ());
                ps.setString(i++, entity.getPreZ());
                ps.setDouble(i++, entity.getPltZ());
                ps.setString(i, entity.getKatZ());
            }
            return ps.executeUpdate() == 1;
        }
    }

    private Zaposleni mapResultSetToZaposleni(ResultSet rs) throws SQLException {
        return new Zaposleni(
                rs.getInt("mbrZ"),
                rs.getString("imeZ"),
                rs.getString("preZ"),
                rs.getDouble("pltZ"),
                rs.getString("katZ")
        );
    }
}
