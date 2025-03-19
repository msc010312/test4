package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class 문제2 {
	// db정보
	private static String id = "root";
	private static String pw = "1234";
	private static String url = "jdbc:mysql://localhost:3306/tmpdb";

	// JDBC 참조변수
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	// 연결코드
	public static void conn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loading Success...");
		conn = DriverManager.getConnection(url, id, pw);
		System.out.println("DB CONNECTED...");
	}

	public static List<BookDTO> selectAll() throws SQLException {
		pstmt = conn.prepareStatement("select * from tbl_book");
		rs = pstmt.executeQuery();
		List<BookDTO> allBook = new ArrayList<>();
		if (rs != null) {
			while (rs.next()) {
				BookDTO bd = new BookDTO(rs.getLong("BookCode"), rs.getString("BookName"), rs.getString("publisher"),
						rs.getString("isbn"));
				allBook.add(bd);
			}

		}
		return allBook;
	}

	public static BookDTO select(Long bookCode) throws SQLException {
		pstmt = conn.prepareStatement("select * from tbl_book where bookcode = ?");
		pstmt.setLong(1, bookCode);
		rs = pstmt.executeQuery();
		BookDTO bd2 = null;
		if (rs != null) {
			while (rs.next()) {
				bd2 = new BookDTO(rs.getLong("BookCode"), rs.getString("BookName"), rs.getString("publisher"),
						rs.getString("isbn"));
			}
		}
		return bd2;
	}

	public static int insertBook(BookDTO bookDto) throws SQLException {
		pstmt = conn.prepareStatement("INSERT INTO tbl_book (bookCode, bookName, publisher, isbn) VALUES (?, ?, ?, ?)");
		pstmt.setLong(1, bookDto.getBookCode());
		pstmt.setString(2, bookDto.getBookName());
		pstmt.setString(3, bookDto.getPublisher());
		pstmt.setString(4, bookDto.getIsbn());

		return pstmt.executeUpdate();
	}

	public static int updateBook(BookDTO bookDto) throws SQLException {
		pstmt = conn.prepareStatement("update tbl_book set BookName=?,Publisher=? where BookCode=?");
		pstmt.setString(1, bookDto.getBookName());
		pstmt.setString(2, bookDto.getPublisher());
		pstmt.setLong(3, bookDto.getBookCode());

		return pstmt.executeUpdate();
	}

	public static int deleteBook(BookDTO dto) throws SQLException {
		pstmt = conn.prepareStatement("delete from tbl_book where BookCode = ?");
		dto.setBookCode(2L);
		pstmt.setLong(1, dto.getBookCode());

		return pstmt.executeUpdate();
	}

	public static void main(String[] args) {
		try {
			conn();
			// insert
			insertBook(new BookDTO(1L, "도서명1", "출판사명1", "isbn-1"));
			insertBook(new BookDTO(2L, "도서명2", "출판사명2", "isbn-2"));
			insertBook(new BookDTO(3L, "도서명3", "출판사명3", "isbn-3"));

			// selectAll
			List<BookDTO> allBook = selectAll();
			System.out.println("selectAll : ");
			allBook.forEach(el -> System.out.println(el));

			// select
			BookDTO dto = select(1L);
			System.out.println("select : " + dto);

			// update
			dto.setBookName("수정도서명-2");
			dto.setPublisher("수정출판사명-2");
			int r1 = updateBook(dto);
			if (r1 > 0)
				System.out.println("수정완료 : " + r1);
			
			// delete
			int r2 = deleteBook(dto);
			if (r2 > 0)
				System.out.println("삭제완료 : " + r2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}