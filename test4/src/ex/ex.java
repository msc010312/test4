package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ex {
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
		return null;
	}

	public static BookDTO select(Long bookCode) throws SQLException {
		pstmt = conn.prepareStatement("select * from tbl_book");
		rs = pstmt.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				System.out.print(rs.getInt("bookCode") + " ");
				System.out.print(rs.getString("bookName") + " ");
				System.out.print(rs.getString("publisher") + "\n");
				System.out.print(rs.getString("isbn") + "\n");
				
			}
		}
		return null;
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
		pstmt = conn.prepareStatement("update tbl_book set BookName=?,Publisher=? where BookCode=2");
		pstmt.setLong(1, bookDto.getBookCode());
		pstmt.setString(2, bookDto.getBookName());
		pstmt.setString(3, bookDto.getPublisher());
		pstmt.setString(4, bookDto.getIsbn());

		return pstmt.executeUpdate();
	}

	public static int deleteBook(BookDTO dto) {
		return -1;
	}

	public static void main(String[] args) {
		try {
			conn();
			// insert
//			insertBook(new BookDTO(1L, "도서명1", "출판사명1", "isbn-1"));
//			insertBook(new BookDTO(2L, "도서명2", "출판사명2", "isbn-2"));
//			insertBook(new BookDTO(3L, "도서명3", "출판사명3", "isbn-3"));

			// selectAll
//			List<BookDTO> allBook = selectAll();
//			System.out.println("selectAll : ");
//			allBook.forEach(el -> System.out.println(el));

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
