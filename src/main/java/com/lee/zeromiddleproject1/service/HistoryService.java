package com.lee.zeromiddleproject1.service;

import com.lee.zeromiddleproject1.dao.HistoryDao;
import com.lee.zeromiddleproject1.dto.HistoryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryService {

    public ArrayList<HistoryDto> selectAllHistories() throws SQLException {
        return HistoryDao.selectAllHistories();
    }

    public void deleteHistory(String id) throws SQLException {

        if (id == null) {
            throw new IllegalArgumentException();
        }

        HistoryDao.deleteHistory(id);
    }
}
