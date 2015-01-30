package com.spring.dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.spring.dto.BoardVO;
import com.spring.dto.FileVO;

public class AttachFile {
	ConnectionMaker connectionMaker;
	private AttachFile() { }
	public AttachFile(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	public void insert(FileVO vo) {
		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;

		int changned = 0;
	
		try {
			int article_id = vo.getArticle_id();
			String attach_file_path = vo.getAttach_file_path();
			String attach_file_name = vo.getAttach_file_name();
			double file_size = vo.getFile_size();
			
			String Query = "update meta_board set attach_file_num = (attach_file_num + 1) where article_id = " + article_id;
			pstmt = conn.prepareStatement(Query);
			changned = pstmt.executeUpdate();
			
			Query = "INSERT INTO meta_file "
					+ "(ARTICLE_ID,  ATTACH_FILE_PATH ,  ATTACH_FILE_NAME, FILE_SIZE, FILE_UPLOAD_DATE, FILE_UPLOAD_TIME)"
					+ "VALUES(?, ?, ?, ? ,DATE_FORMAT(NOW(),'%Y-%m-%d'), DATE_FORMAT(NOW(),'%H:%i:%s'))";

			pstmt = conn.prepareStatement(Query);

			pstmt.setInt(1, article_id);
			pstmt.setString(2, attach_file_path);
			pstmt.setString(3, attach_file_name);
			pstmt.setDouble(4, file_size);
			
			pstmt.executeUpdate();

		} catch (SQLException ee1) {
			System.out.println("insert()");
			ee1.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ;
	}
	
	public int get_fileNum(int article_id) {

		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		int result = 0;
		
		try {

			
			
			String Query = "select count(*) from meta_file where article_id = "
					+ article_id;
			
			pstmt = conn.prepareStatement(Query);
			rs = pstmt.executeQuery(Query);
			rs.next();
			result = rs.getInt(1);

			rs.close();
		} catch (SQLException ee1) {
			System.out.println("insert()");
			ee1.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int delete_file(int article_id, int file_id) {

		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		int changed = 0;
		int result = 0;
		
		try {

			
			String Query = "update meta_board set attach_file_num = (attach_file_num - 1) where article_id = " + article_id;
			pstmt = conn.prepareStatement(Query);
			changed = pstmt.executeUpdate();
			
			Query =  "DELETE FROM meta_file WHERE file_id= " + file_id;
			pstmt = conn.prepareStatement(Query);
			pstmt.executeUpdate();
			
			
		} catch (SQLException ee1) {
			System.out.println("insert()");
			ee1.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	public Vector<FileVO> select_file(int article_id) {
		Vector<FileVO> vec = new Vector<FileVO>();

		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		try {
			
			String Query = null;
			Query = "select * from meta_file where article_id = "
					+ article_id;
			pstmt = conn.prepareStatement(Query);
			rs = pstmt.executeQuery(Query);

			while (rs.next()) {
				FileVO vo = new FileVO();
				vo.setArticle_id(article_id);
				vo.setAttach_file_name(rs.getString("attach_file_name"));
				vo.setAttach_file_path(rs.getString("attach_file_path"));
				vo.setFile_size(rs.getDouble("file_size"));
				vo.setFile_upload_date(rs.getString("file_upload_date"));
				vo.setFile_upload_time(rs.getString("file_upload_time"));
				vo.setFile_id(rs.getInt("file_id"));
				vec.add(vo);
			}
			rs.close();
		} catch (SQLException ee1) {
			System.out.println("select_board()");
			ee1.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vec;
	}
	
	public static void main(String[] args) {

	}
}
