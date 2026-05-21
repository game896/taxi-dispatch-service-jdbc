package org.example.service;

import org.example.dao.ZaposleniDao;
import org.example.dao_impl.ZaposleniDaoImpl;
import org.example.model.Zaposleni;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZaposleniService {

    private static final ZaposleniDao zaposleniDao = new ZaposleniDaoImpl();

    public ArrayList<Zaposleni> getAll() throws SQLException {
        return (ArrayList<Zaposleni>) zaposleniDao.findAll();
    }

    public Zaposleni getById(int id) throws SQLException {
        return zaposleniDao.findById(id);
    }

    public boolean existsById(int id) throws SQLException {
        return zaposleniDao.existsById(id);
    }

    public boolean save(Zaposleni zaposleni) throws SQLException {
        return zaposleniDao.save(zaposleni);
    }

    public boolean deleteById(int id) throws SQLException {
        return zaposleniDao.deleteById(id);
    }

    public int saveAll(List<Zaposleni> zaposleniList) throws SQLException {
        return zaposleniDao.saveAll(zaposleniList);
    }

    public Iterable<Zaposleni> getAllById(Iterable<Integer> ids) throws SQLException {
        return zaposleniDao.findAllById(ids);
    }
}
