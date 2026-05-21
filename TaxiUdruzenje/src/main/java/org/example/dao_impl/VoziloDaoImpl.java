package org.example.dao_impl;

import org.example.dao.VoziloDao;
import org.example.model.Vozilo;
import org.example.taxi_udruzenje_connection.ConnectionUtil_HikariCP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoziloDaoImpl implements VoziloDao {

    @Override
    public int count() throws SQLException {
        String query = "SELECT COUNT(*) FROM Vozilo";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return -1;
        }
    }

    @Override
    public boolean delete(Vozilo entity) throws SQLException {
        return deleteById(entity.getRegBr());
    }

    @Override
    public int deleteAll() throws SQLException {
        String query = "DELETE FROM Vozilo";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return ps.executeUpdate();
        }
    }

    @Override
    public boolean deleteById(String regBr) throws SQLException {
        String query = "DELETE FROM Vozilo WHERE regBr=?";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regBr);
            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean existsById(String regBr) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return existsByIdTry(conn, regBr);
        }
    }

    private boolean existsByIdTry(Connection conn, String regBr) throws SQLException {
        String query = "SELECT 1 FROM Vozilo WHERE regBr=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regBr);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.isBeforeFirst();
            }
        }
    }

    @Override
    public Iterable<Vozilo> findAll() throws SQLException {
        String query = "SELECT regBr FROM Vozilo";
        List<Vozilo> list = new ArrayList<>();
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToVozilo(rs));
            }
        }
        return list;
    }

    @Override
    public Vozilo findById(String regBr) throws SQLException {
        String query = "SELECT regBr FROM Vozilo WHERE regBr=?";
        Vozilo vozilo = null;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, regBr);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) vozilo = mapResultSetToVozilo(rs);
            }
        }
        return vozilo;
    }

    @Override
    public Iterable<Vozilo> findAllById(Iterable<String> regBrs) throws SQLException {
        List<Vozilo> list = new ArrayList<>();
        String query = "SELECT regBr FROM Vozilo WHERE regBr=?";
        try (Connection conn = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            for (String regBr : regBrs) {
                ps.setString(1, regBr);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) list.add(mapResultSetToVozilo(rs));
                }
            }
        }
        return list;
    }

    @Override
    public boolean save(Vozilo entity) throws SQLException {
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            return saveTransactional(conn, entity);
        }
    }

    @Override
    public int saveAll(Iterable<Vozilo> entities) throws SQLException {
        int rowsSaved = 0;
        try (Connection conn = ConnectionUtil_HikariCP.getConnection()) {
            conn.setAutoCommit(false);
            for (Vozilo entity : entities) {
                if (saveTransactional(conn, entity)) rowsSaved++;
            }
            conn.commit();
        }
        return rowsSaved;
    }

    private boolean saveTransactional(Connection conn, Vozilo entity) throws SQLException {
        boolean exists = existsByIdTry(conn, entity.getRegBr());

        String insert = "INSERT INTO Vozilo (regBr) VALUES (?)";
        String update = "UPDATE Vozilo SET regBr=? WHERE regBr=?"; // u ovom slučaju update praktično menja regBr

        try (PreparedStatement ps = conn.prepareStatement(exists ? update : insert)) {
            if (exists) {
                ps.setString(1, entity.getRegBr());
                ps.setString(2, entity.getRegBr());
            } else {
                ps.setString(1, entity.getRegBr());
            }
            return ps.executeUpdate() == 1;
        }
    }

    private Vozilo mapResultSetToVozilo(ResultSet rs) throws SQLException {
        return new Vozilo(rs.getString("regBr"));
    }
}
