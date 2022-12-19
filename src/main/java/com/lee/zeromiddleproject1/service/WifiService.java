package com.lee.zeromiddleproject1.service;

import com.lee.zeromiddleproject1.dao.WifiDao;
import com.lee.zeromiddleproject1.dto.HistoryDto;
import com.lee.zeromiddleproject1.dto.WifiDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class WifiService {


    public ArrayList<WifiDto> selectAll() throws SQLException {
        return WifiDao.selectAll();
    }

    public void insertHistory(HistoryDto historyDto) {
        WifiDao.insertHistory(historyDto);
    }

    public void insertWifiList(ArrayList<WifiDto> wifiList) throws SQLException {

        if (wifiList.size() == 0 || wifiList.isEmpty()) {
            throw new IllegalArgumentException();
        }

        WifiDao.insertWifiList(wifiList);
    }

}
