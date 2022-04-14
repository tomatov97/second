package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 접속 정보 구성 요소
		// 프로토콜 : jdbc:mariadb
		// DBMS서버 도메인 또는 주소 : localhost, 127.0.0.1, 서버의 도메인 또는 IP 주소
		// 포트 번호 : mysql이나 mariadb는 일반적으로 3306번 포트를 사용
		// 접속할 DB명
		// 접속할 사용자 계정과 비밀번호 : GET Parameter 형식으로 접속 정보의 마지막에 ?가 붙고 name=value로 넣음
		
		// jdbc:mariadb://localhost:3306/employees?user=root&password=1234
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/employees?user=root&password=1234");
			
			System.out.println("Statement 생성 전");
			// 우리의 쿼리를 실행시켜주고, 쿼리의 실행 결과를 가져오는 객체
			stmt = conn.createStatement();
			System.out.println("Statement 생성 전");
			
			String sql = "SELECT * FROM employees LIMIT 5";
			
			System.out.println("쿼리 실행 전");
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("쿼리 실행 후");
			
			// 위 ResultSet 안에는 SELECT의 결과들이 들어있는 것
			
			int count = 1;
			
			while(rs.next()) {
				System.out.println("<< " + count + "번째 데이터 출력 >>");
				
				int empNo = rs.getInt("emp_no");
				String birthDate = rs.getString("birth_date");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				char gender = rs.getString("gender").charAt(0);
				String hireDate = rs.getString("hire_date");
				
				System.out.println("emp_no => " + empNo);
				System.out.println("birth_date => " + birthDate);
				System.out.println("first_name => " + firstName);
				System.out.println("last_name => " + lastName);
				System.out.println("gender => " + gender);
				System.out.println("hire_date => " + hireDate);
				
				count++;
			}
			
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// DB와 관련된 자원을 해제해줄 때에는 반드시 finally 안에서 해제해야함
			try {
				if (stmt != null) {
					stmt.close();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
