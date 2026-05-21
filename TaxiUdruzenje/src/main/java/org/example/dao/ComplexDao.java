package org.example.dao;

import org.example.dto.HardQueryOneDto;
import org.example.dto.HardQueryTwoDto;
import org.example.dto.SimpleQueryDto;

import java.sql.SQLException;
import java.util.List;

public interface ComplexDao {
    List<SimpleQueryDto> jednostavanQuery() throws SQLException;
    public List<HardQueryOneDto> tezakQueryJedan() throws SQLException;
    public List<HardQueryTwoDto> tezakQueryDva() throws SQLException;
    public boolean deleteKlijentTransactional(int mbrKl) throws SQLException;
}
