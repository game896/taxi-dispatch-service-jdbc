package org.example.service;

import org.example.dao.ComplexDao;
import org.example.dao_impl.ComplexDaoImpl;
import org.example.dto.HardQueryOneDto;
import org.example.dto.HardQueryTwoDto;
import org.example.dto.SimpleQueryDto;

import java.sql.SQLException;
import java.util.List;

public class ComplexService {

    private final ComplexDao complexDao;

    public ComplexService() {
        this.complexDao = new ComplexDaoImpl();
    }

    public List<SimpleQueryDto> getVozaciSaBrojemVoznjiIUkupnomZaradom() throws SQLException {
        return complexDao.jednostavanQuery();
    }

    public List<HardQueryOneDto> getTarifneGrupeSaPrihodom() throws SQLException {
        return complexDao.tezakQueryJedan();
    }

    public List<HardQueryTwoDto> getHardQueryTwo() throws SQLException {
        return complexDao.tezakQueryDva();
    }

    public boolean delKlijentTransactional(int mbrKl) throws SQLException {
        return complexDao.deleteKlijentTransactional(mbrKl);
    }
}
