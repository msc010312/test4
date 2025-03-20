package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ex {
	// 데이터베이스 접속 정보 선언
	private static String id = "root"; // 데이터베이스 로그인 아이디
	private static String pw = "1234"; // 데이터베이스 로그인 비밀번호
	private static String url = "jdbc:mysql://localhost:3306/tmpdb"; // MySQL 데이터베이스 URL

	// JDBC 참조 변수 선언
	private static Connection conn; // 데이터베이스 연결을 위한 Connection 객체 선언
	private static PreparedStatement pstmt; // SQL 실행을 위한 PreparedStatement 객체 선언
	private static ResultSet rs; // SQL 실행 결과를 저장하는 ResultSet 객체 선언

	// 데이터베이스 연결 메서드
	public static void conn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC 드라이버 로드
		System.out.println("Driver Loading Success..."); // 드라이버 로드 성공 메시지 출력
		conn = DriverManager.getConnection(url, id, pw); // 데이터베이스 연결 수행
		System.out.println("DB CONNECTED..."); // 데이터베이스 연결 성공 메시지 출력
	}

	// 모든 도서 정보 조회 메서드
	public static List<BookDTO> selectAll() throws SQLException {
		pstmt = conn.prepareStatement("select * from tbl_book"); // tbl_book에서 모든 데이터 조회하는 SQL 문 작성
		rs = pstmt.executeQuery(); // SQL 실행 및 결과 저장
		List<BookDTO> allBook = new ArrayList<>(); // 조회된 도서 정보를 저장할 리스트 생성
		if (rs != null) { // 결과가 존재하면
			while (rs.next()) { // 결과를 한 줄씩 가져와서 리스트에 추가
				BookDTO bd = new BookDTO(rs.getLong("BookCode"), rs.getString("BookName"), rs.getString("publisher"),
						rs.getString("isbn")); // 객체 생성
				allBook.add(bd); // 리스트에 추가
			}
		}
		return allBook; // 조회된 도서 리스트 반환
	}

	public static BookDTO select(Long bookCode) throws SQLException {
		pstmt = conn.prepareStatement("select * from tbl_book where bookcode = ?"); // 특정 BookCode 조건으로 조회하는 SQL 작성
		pstmt.setLong(1, bookCode); // 첫 번째 물음표에 bookCode 값 설정
		rs = pstmt.executeQuery(); // SQL 실행 및 결과 저장
		BookDTO bd2 = null; // 조회된 도서 정보를 저장할 변수 선언
		if (rs != null) { // 결과가 존재하면
			while (rs.next()) { // 결과를 한 줄씩 가져와서 객체 생성
				bd2 = new BookDTO(rs.getLong("BookCode"), rs.getString("BookName"), rs.getString("publisher"),
						rs.getString("isbn"));
			}
		}
		return bd2; // 조회된 도서 정보 반환
	}

	// 도서 정보 추가 메서드
	public static int insertBook(BookDTO bookDto) throws SQLException {
		pstmt = conn.prepareStatement("INSERT INTO tbl_book (bookCode, bookName, publisher, isbn) VALUES (?, ?, ?, ?)"); // 도서
																															// 추가
																															// SQL
																															// 작성
		pstmt.setLong(1, bookDto.getBookCode()); // bookCode 값 설정
		pstmt.setString(2, bookDto.getBookName()); // bookName 값 설정
		pstmt.setString(3, bookDto.getPublisher()); // publisher 값 설정
		pstmt.setString(4, bookDto.getIsbn()); // isbn 값 설정

		return pstmt.executeUpdate(); // SQL 실행 후 추가된 행의 개수 반환
	}

	// 도서 정보 수정 메서드
	public static int updateBook(BookDTO bookDto) throws SQLException {
		pstmt = conn.prepareStatement("update tbl_book set BookName=?,Publisher=? where BookCode=?"); // 도서 수정 SQL 작성
		pstmt.setString(1, bookDto.getBookName()); // bookName 값 설정
		pstmt.setString(2, bookDto.getPublisher()); // publisher 값 설정
		pstmt.setLong(3, bookDto.getBookCode()); // bookCode 값 설정

		return pstmt.executeUpdate(); // SQL 실행 후 수정된 행의 개수 반환
	}

	// 도서 정보 삭제 메서드
	public static int deleteBook(BookDTO dto) throws SQLException {
		pstmt = conn.prepareStatement("delete from tbl_book where BookCode = ?"); // 도서 삭제 SQL 작성
		dto.setBookCode(2L); // 삭제할 도서 코드 설정 (예제 코드로 2번 설정)
		pstmt.setLong(1, dto.getBookCode()); // 첫 번째 물음표에 bookCode 값 설정

		return pstmt.executeUpdate(); // SQL 실행 후 삭제된 행의 개수 반환
	}

	// 메인 메서드 (프로그램 실행 진입점)
	public static void main(String[] args) {
		try {
			conn(); // 데이터베이스 연결
			insertBook(new BookDTO(1L, "도서명1", "출판사명1", "isbn-1")); // 도서 추가
			insertBook(new BookDTO(2L, "도서명2", "출판사명2", "isbn-2"));
			insertBook(new BookDTO(3L, "도서명3", "출판사명3", "isbn-3"));

			List<BookDTO> allBook = selectAll(); // 모든 도서 조회
			allBook.forEach(System.out::println); // 조회된 도서 정보 출력
		} catch (Exception e) {
			e.printStackTrace(); // 예외 발생 시 에러 출력
		}
	}
}