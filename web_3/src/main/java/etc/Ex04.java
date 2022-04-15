package etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import vo.MemberInfo;

public class Ex04 {
	public static int inputMenuNumber() {
		Scanner scanf = new Scanner(System.in);
		
		System.out.println("<< 뫄뫄뫄 프로그램 >>");
		System.out.println("1. 회원 가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원정보 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 프로그램 종료");
		System.out.print("메뉴 선택 -> ");
		
		int menu = scanf.nextInt();
		
		return menu;
	}
	
	// 회원 가입시 정보를 입력받는 메서드
	public static MemberInfo joinInput() {
		Scanner scanf = new Scanner(System.in);
		
		System.out.println("<< 회원 가입 >>");
		
		System.out.print("아이디 -> ");
		String id = scanf.next();
		
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
		
		System.out.print("이름 -> ");
		String name = scanf.next();
		
		MemberInfo memberInfo = new MemberInfo(id, pw, name);
		
		return memberInfo;
	}
	// 회원 가입 쿼리를 실행하는 메서드
	public static boolean executeJoinQuery (MemberInfo memberInfo) throws SQLIntegrityConstraintViolationException{
		Connection conn = null;
		Statement stmt = null;
		boolean isJoin = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO userInfo(id,pw,name) VALUES ('" + memberInfo.getId() + "', '" + memberInfo.getPw() + "', '" + memberInfo.getName() + "')";
			
			int count = stmt.executeUpdate(sql);
			
			if (count == 1) {
				isJoin = true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}		
		
		return isJoin;
	}
	// 회원 가입 전체 흐름을 관리하는 메서드
	public static void join() {
		// 1. 회원 정보 입력 받기
		MemberInfo memberInfo = joinInput();
		
		// 2. 회원 가입 쿼리 실행
		boolean isJoin;
		try {
			isJoin = executeJoinQuery(memberInfo);
			
		// 3. 회원 가입 결과 출력
			if(isJoin) {
				System.out.println("회원 가입 성공");
			} else {
				System.out.println("회원 가입 실패");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("이미 사용 중인 아이디 입니다.");
		}
	}
	
	public static MemberInfo loginInPut() {
			Scanner scanf = new Scanner(System.in);
			
			System.out.println("<< 로그인 >>");
			System.out.print("아이디 -> ");
			String id = scanf.next();
				
			System.out.print("비밀번호 -> ");
			String pw = scanf.next();
				
			MemberInfo memberInfo = new MemberInfo (id, pw, null);
				
			return memberInfo;
	}
	public static boolean executeLoginQuery(MemberInfo memberInfo) {
		Connection conn = null;
		Statement stmt = null;

		boolean isLogin = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM userinfo WHERE id = '" + memberInfo.getId() + "' AND pw = '" + memberInfo.getPw() + "';";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				isLogin = true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		return isLogin;			
		
	}
	public static void login() {
		// 1. 로그인 정보(id, pw)를 입력 받는 부분
		MemberInfo memberInfo = loginInPut();
		// 2. 로그인 쿼리를 실행하고 결과를 받아오는 부분
		boolean isLogin = executeLoginQuery(memberInfo);
		// 3. 결과를 출력하는 부분
		if (isLogin) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("로그인 실패");
		}
	}
	
	public static MemberInfo updateInput() {
		Scanner scanf = new Scanner(System.in);
		
		System.out.println("<< 회원 정보 수정 >>");
		System.out.print("아이디 -> ");
		String id = scanf.next();
			
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
			
		MemberInfo memberInfo = new MemberInfo (id, pw, null);
			
		return memberInfo;
	}
	public static MemberInfo executeUpdateQuery (MemberInfo memberInfo) {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM userinfo WHERE id = '" + memberInfo.getId() + "' AND pw = '" + memberInfo.getPw() + "';";
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				String name = rs.getString("name");
				
				memberInfo.setName(name);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		
		return memberInfo;
	}
	public static boolean executeNameUpdateQuery(String newName, MemberInfo memberInfo) {
		Connection conn = null;
		Statement stmt = null;
		boolean isUpdate = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			
			String sql = "UPDATE userInfo SET name = '" + newName + "' WHERE id = '" + memberInfo.getId() + "';";
			int count = stmt.executeUpdate(sql);
			
			if (count == 1) {
				isUpdate = true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		return isUpdate;
	}
	public static void update() {
		// 1. 회원 정보 수정을 하기 위한 아이디, 비밀번호 입력 받기
		MemberInfo memberInfo = updateInput();
		// 2. 사용자가 입력한 아이디, 비밀번호를 사용해서 수정할 회원의 정보 찾기
		memberInfo = executeUpdateQuery(memberInfo);
		if(memberInfo.getName() == null) {
			System.out.println("존재하지 않는 계정입니다.");
		} else {
		// 3. 찾은 회원의 정보 출력
		System.out.println("회원 이름 -> " + memberInfo.getName());
		}		
		// 4. 수정할 이름 입력 받기 (이름만 수정 가능)
		Scanner scanf = new Scanner(System.in);
		
		System.out.print("수정할 이름을 입력하세요 -> ");
		String newName = scanf.next();
		// 5. 사용자가 입력한 이름으로 회원 정보 수정
		boolean isUpdate = executeNameUpdateQuery(newName, memberInfo);
		// 6. 수정 결과 출력
		if (isUpdate) {
			System.out.println("이름을 성공적으로 수정했습니다.");
		} else {
			System.out.println("이름을 수정하지 못 했습니다.");
		}
	}
	public static boolean executeDeleteQuery(MemberInfo memberInfo) {
		Connection conn = null;
		Statement stmt = null;
		boolean isUpdate = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/shopdb?user=root&password=1234");
			stmt = conn.createStatement();
			
			String sql = "DELETE FROM userInfo WHERE id = '" + memberInfo.getId() + "';";
			int count = stmt.executeUpdate(sql);
			
			if (count == 1) {
				isUpdate = true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
		return isUpdate;
	}
	public static MemberInfo deleteInput() {
		Scanner scanf = new Scanner(System.in);
		
		System.out.println("<< 회원 탈퇴 >>");
		System.out.print("아이디 -> ");
		String id = scanf.next();
			
		System.out.print("비밀번호 -> ");
		String pw = scanf.next();
			
		MemberInfo memberInfo = new MemberInfo (id, pw, null);
			
		return memberInfo;
	}

	public static void delete() {
		Scanner scanf = new Scanner(System.in);
		
		// 1. 삭제할 회원 정보(id, pw)를 입력받기
		MemberInfo memberInfo = deleteInput();
		// 2. 사용자가 입력한 회원 정보와 일치하는 회원 정보 찾기
		boolean isLogin = executeLoginQuery(memberInfo);
		// 3. 정말 탈퇴할 것인지 묻기
		if (isLogin) {
			System.out.println("정말 탈퇴하시겠습니까? ( y / n )");
			char answer = scanf.next().charAt(0);
		// 4. 정말 탈퇴한다면, 탈퇴(회원 정보 삭제) 처리	
			if (answer == 'y') {
				boolean isDelete = executeDeleteQuery(memberInfo);
				if (isDelete) {
					System.out.println("성공적으로 삭제되었습니다.");
				} else {
					System.out.println("문제가 생겼습니다!");
				}
			}
			
		} else {
			System.out.println("존재하지 않는 계정입니다.");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		boolean isRunning = true;
		
		while (isRunning) {
			
			int menu = inputMenuNumber();
			switch(menu) {
			
			case MenuNumber.JOIN:
				join();
				break;
			case MenuNumber.LOGIN: 
				login();
				break;
			case MenuNumber.UPDATE:
				update();
				break;
			case MenuNumber.DELETE:
				delete();
				break;
			case MenuNumber.EXIT: 
				System.out.println("프로그램을 종료합니다.");
				isRunning = false;
				break;
			default: 
				System.out.println("번호를 잘못 입력하셨습니다.");
			} // end switch
		} // end while
		
		
		
		
	}

}
