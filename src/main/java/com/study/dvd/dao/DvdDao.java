package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Dvd;
import com.study.dvd.util.DBConnectionMgr;

public class DvdDao {
	private static DBConnectionMgr pool = DBConnectionMgr.getInstance(); 
	
	public static List<Dvd> searchDvdByTitle(String searchText) { // searchText : title을 검색
		List<Dvd> dvds = new ArrayList<>(); // 비어있는 리스트 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // ResultSet으로 초기화 시킴 
		
		try {
			con = pool.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * from dvd_view ");
			sql.append("where title like ? limit 0, 50"); // \"%?%\" -> setString을 하면 하나의 문자로 인식이 되기 때문에 그냥 ? 만 해준다 
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchText + "%");
			rs = pstmt.executeQuery(); // ResultSet을 한 select들을 executeQuery를 이용해 나타낸다.
			
			while (rs.next()) {
				Dvd dvd = Dvd.builder()
						.dvdId(rs.getInt(1))
						.registrationNumber(rs.getString(2))
						.title(rs.getString(3))
						.producerId(rs.getInt(4))
						.producerName(rs.getString(5))
						.publisherId(rs.getInt(6))
						.publisherName(rs.getString(7))
						.publicationYear(rs.getInt(8))
						.databaseDate(rs.getDate(9).toLocalDate())
						.build();
				
				dvds.add(dvd); // dvd list 안에 넣기 위함 
			}
			
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return dvds;
	}
	
	
}
