package org.example.service;

import org.example.dao.TarifnaGrupaDao;
import org.example.dao_impl.TarifnaGrupaDaoImpl;
import org.example.model.TarifnaGrupa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifnaGrupaService {

    private static final TarifnaGrupaDao tarifnaGrupaDao = new TarifnaGrupaDaoImpl();

    public ArrayList<TarifnaGrupa> getAll() throws SQLException {
        return (ArrayList<TarifnaGrupa>) tarifnaGrupaDao.findAll();
    }

    public TarifnaGrupa getById(String id) throws SQLException {
        return tarifnaGrupaDao.findById(id);
    }

    public boolean existsById(String id) throws SQLException {
        return tarifnaGrupaDao.existsById(id);
    }

    public boolean save(TarifnaGrupa tarifnaGrupa) throws SQLException {
        return tarifnaGrupaDao.save(tarifnaGrupa);
    }

    public boolean deleteById(String id) throws SQLException {
        return tarifnaGrupaDao.deleteById(id);
    }

    public int saveAll(List<TarifnaGrupa> tgList) throws SQLException {
        return tarifnaGrupaDao.saveAll(tgList);
    }

    public Iterable<TarifnaGrupa> getAllById(Iterable<String> ids) throws SQLException {
        return tarifnaGrupaDao.findAllById(ids);
    }
}
