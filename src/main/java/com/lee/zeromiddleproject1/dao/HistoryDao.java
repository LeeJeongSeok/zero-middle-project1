package com.lee.zeromiddleproject1.dao;

import com.lee.zeromiddleproject1.conn.DBConnection;
import com.lee.zeromiddleproject1.dto.HistoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDao {
    static DBConnection connection = DBConnection.getInstance();

    public static ArrayList<HistoryDto> selectAllHistories() throws SQLException {
        ArrayList<HistoryDto> histories = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();
            pstmt = conn.prepareStatement("select * from tb_search_history_list where use_flag = 'Y'");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                HistoryDto historyDto = new HistoryDto();
                historyDto.setId(rs.getInt("id"));
                historyDto.setLat(rs.getString("lat"));
                historyDto.setLnt(rs.getString("lnt"));
                historyDto.setSearch_date(rs.getString("search_date"));
                historyDto.setUse_flag(rs.getString("use_flag"));

                histories.add(historyDto);
            }
        }finally {
            connection.close(rs, pstmt, conn);
        }

        return histories;
    }

    public static void deleteHistory(String id) throws SQLException{

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnection();
            pstmt = conn.prepareStatement("update tb_search_history_list set use_flag = 'N' where id = (?)");

            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
        }finally {
            connection.close(rs, pstmt, conn);
        }

    }
}
