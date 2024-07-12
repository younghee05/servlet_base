package com.study.dvd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.study.dvd.entity.Producer;
import com.study.dvd.util.DBConnectionMgr;

public class ProducerDao {
	private static DBConnectionMgr pool =  DBConnectionMgr.getInstance();
	
	public static List<Producer> searchProducerByProducerName(String searchText) {
		List<Producer> producers = new ArrayList<Producer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select * from producer_tb where producer_name like ? limit 0, 50";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchText + "%"); // searchText를 포함하여 조회하겠다 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Producer producer = Producer.builder()
						.producerId(rs.getInt(1))
						.producerName(rs.getString(2))
						.build();
				
				producers.add(producer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs); // 객체들을 없애주기 위함 
		}
		
		return producers;
		
//		try {
//			con = pool.getConnection();
//			StringBuilder sql = new StringBuilder();
//			sql.append("select * from dvd_view");                            
//			sql.append("where title like ? limit 0, 50");
//			pstmt = con.prepareStatement(sql.toString());
//			pstmt.setString(1, "%" + searchText + "%");
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				Producer producer = Producer.builder()
//						.producerId(rs.getInt(1))
//						.producerName(rs.getString(2))
//						.build();
//				producers.add(producer);
//			}
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			pool.freeConnection(con, pstmt, rs);
//		}
		
//		return producers;
	
	}
	
	public static int save(Producer producer) {
		int successCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "insert into producer_tb values(0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, producer.getProducerName());
			successCount = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			while(rs.next()) {
				producer.setProducerId(rs.getInt(1));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return successCount;
		
	}

}
