package com.spring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.spring.dto.CommentVO;

public class Comment {
	ConnectionMaker connectionMaker;
	private Comment() { }
	public Comment(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	public CommentVO insert(CommentVO vo) {
		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;
		CommentVO vo2 = null;
		int article_id = vo.getAt_id();
		String str_article_id = Integer.toString(article_id);
		String comment_writer = vo.getCmt_writer();
		String comment_data = vo.getCmt_data();
		ResultSet rs;
		int changed = 0;
		try {
			String Query = "INSERT INTO meta_comment "
					+ "(ARTICLE_ID, COMMENT_WRITER, COMMENT_DATA, COMMENT_DATE, COMMENT_TIME)"
					+ " VALUES(?, ?, ?, DATE_FORMAT(NOW(),'%Y-%m-%d'), "
					+ "DATE_FORMAT(NOW(),'%H:%i:%s'))";
			pstmt = conn.prepareStatement(Query);

			pstmt.setInt(1, article_id);
			pstmt.setString(2, comment_writer);
			pstmt.setString(3, comment_data);
			pstmt.executeUpdate();
			
			Query = "update meta_board set article_reple = (article_reple + 1) where article_id = " + article_id;
			pstmt = conn.prepareStatement(Query);
			changed = pstmt.executeUpdate();
			
			Query = "select * from meta_comment order by comment_date desc, comment_time desc limit 0, 1";
			
			pstmt = conn.prepareStatement(Query);
			rs = pstmt.executeQuery(Query);
			while (rs.next()) {
				vo2 = new CommentVO();
				vo2.setAt_id(rs.getInt("article_id"));
				vo2.setCmt_id(rs.getInt("comment_id"));
				vo2.setCmt_data(rs.getString("comment_data"));
				vo2.setCmt_date(rs.getString("comment_date"));
				vo2.setCmt_time(rs.getString("comment_time"));
				vo2.setCmt_writer(rs.getString("comment_writer"));
			}
		} catch (SQLException ee1) {
			ee1.printStackTrace();
			System.out.println("insert()");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vo2;
	}

	public Vector<CommentVO> select_comment(String article_id) {
		Vector<CommentVO> vec = new Vector<CommentVO>();

		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;
		ResultSet rs;

		try {
			String Query = null;
			
				Query = "select * from meta_comment where article_id = "
						+ article_id
							+ " and comment_delete = 'N' order by comment_id desc";
			
			pstmt = conn.prepareStatement(Query);
			rs = pstmt.executeQuery(Query);
			while (rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setAt_id(rs.getInt("article_id"));
				vo.setCmt_id(rs.getInt("comment_id"));
				vo.setCmt_data(rs.getString("comment_data"));
				vo.setCmt_date(rs.getString("comment_date"));
				vo.setCmt_time(rs.getString("comment_time"));
				vo.setCmt_writer(rs.getString("comment_writer"));
				vec.add(vo);
			}
			rs.close();
		} catch (SQLException ee1) {
			System.out.println("select_comment()");
			ee1.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vec;

	}
	
	public void comment_delete(String comment_id, String article_id) {

		Connection conn = connectionMaker.makeConnection();
		PreparedStatement pstmt = null;
		int changed = 0;
		try {
			
			String Query = "UPDATE meta_comment SET comment_delete = 'Y' WHERE comment_id = "
					+ comment_id;

			pstmt = conn.prepareStatement(Query);
			pstmt.executeUpdate(Query);
			
			Query = "update meta_board set article_reple = (article_reple - 1) where article_id = " + article_id;
			pstmt = conn.prepareStatement(Query);
			changed = pstmt.executeUpdate();
			
			
		} catch (SQLException ee1) {
			System.out.println("delete()");
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
	
	public static void main(String[] args) {
		
	}
}
