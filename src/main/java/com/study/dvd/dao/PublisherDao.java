package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Publisher;
import com.study.dvd.util.DBConnectionMgr;

public class PublisherDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance();
	
	public static List<Publisher> searchPublisherByPublisherName(String searchText) {
		List<Publisher> publishers = new ArrayList<Publisher>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from publisher_tb where publisher_name like ? limit 0, 50";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,"%" + searchText + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()){
				Publisher publisher = Publisher.builder()
						.publisherId(rs.getInt(1))
						.publisherName(rs.getString(2))
						.build();
				
				publishers.add(publisher);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return publishers;
	}

}
