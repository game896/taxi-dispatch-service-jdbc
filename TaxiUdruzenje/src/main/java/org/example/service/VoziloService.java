package org.example.service;

import org.example.dao.VoziloDao;
import org.example.dao_impl.VoziloDaoImpl;
import org.example.model.Vozilo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoziloService {

    private static final VoziloDao voziloDao = new VoziloDaoImpl();

    public ArrayList<Vozilo> getAll() throws SQLException {
        return (ArrayList<Vozilo>) voziloDao.findAll();
    }

    public Vozilo getById(String regBr) throws SQLException {
        return voziloDao.findById(regBr);
    }

    public boolean existsById(String regBr) throws SQLException {
        return voziloDao.existsById(regBr);
    }

    public boolean save(Vozilo vozilo) throws SQLException {
        return voziloDao.save(vozilo);
    }

    public boolean deleteById(String regBr) throws SQLException {
        return voziloDao.deleteById(regBr);
    }

    public int saveAll(List<Vozilo> voziloList) throws SQLException {
        return voziloDao.saveAll(voziloList);
    }

    public Iterable<Vozilo> getAllById(Iterable<String> regBrList) throws SQLException {
        return voziloDao.findAllById(regBrList);
    }
}
