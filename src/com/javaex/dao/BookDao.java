package com.javaex.dao;

import java.sql.*;
import java.util.*;

import com.javaex.vo.BookVo;

public class BookDao {
	public void InsertBook(BookVo vo) {
		// 0. import java.sql.*; ctrl + shift + o
		Connection conn = null;
		PreparedStatement pstmt = null; // 쿼리문 관련

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 중요 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO book VALUES(seq_book_id.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getPubs());
			pstmt.setString(3, vo.getPubDate());
			pstmt.setInt(4, vo.getAuthorId());

			int count = pstmt.executeUpdate();

			// 중요 4.결과처리
			System.out.println(count + "건 저장완료");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	}

	public List<BookVo> selectBookList() {
		List<BookVo> bookList = new ArrayList<BookVo>();
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " SELECT B.book_id, B.title, B.pubs, B.pub_date, B.author_id, A.author_name, A.author_desc "
					+ " FROM book B, author A " 
					+ " WHERE B.author_id = A.author_id ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while (rs.next()) {
			
				BookVo vo = new BookVo();

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
//				String authorName = rs.getString("author_name");
//				String authorDesc = rs.getString("author_desc");
				
				vo.setBookId(bookId);
				vo.setTitle(title);
				vo.setPubs(pubs);
				vo.setPubDate(pubDate);
				vo.setAuthorId(authorId);
								
				bookList.add(vo);
				
				//System.out.println(bookId + "	" + title + "	" + pubs + "	" + pubDate.substring(0, 10) + "	"
					//	+ authorId /*+ "	" + authorName + "	" + authorDesc */);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		return bookList;
	}

}
