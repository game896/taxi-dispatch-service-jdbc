package org.example.service;

import org.example.dao.KlijentDao;
import org.example.dao_impl.KlijentDaoImpl;
import org.example.model.Klijent;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KlijentService {

    private static final KlijentDao klijentDao = new KlijentDaoImpl();

    public ArrayList<Klijent> getAll() throws SQLException {
        return (ArrayList<Klijent>) klijentDao.findAll();
    }

    public Klijent getById(int id) throws SQLException {
        return klijentDao.findById(id);
    }

    public boolean existsById(int id) throws SQLException {
        return klijentDao.existsById(id);
    }

    public boolean save(Klijent klijent) throws SQLException {
        return klijentDao.save(klijent);
    }

    public boolean deleteById(int id) throws SQLException {
        return klijentDao.deleteById(id);
    }

    public int saveAll(List<Klijent> klijentList) throws SQLException {
        return klijentDao.saveAll(klijentList);
    }

    public Iterable<Klijent> getAllById(Iterable<Integer> ids) throws SQLException {
        return klijentDao.findAllById(ids);
    }
}
