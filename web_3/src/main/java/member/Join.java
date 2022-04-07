package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.MemberInfo;

@WebServlet("/member/join")
public class Join extends HttpServlet {
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		// Parameter 검증
		// 1. id 입력 여부
		// 2. pw 입력 여부
		// 3. name 입력 여부
		// 4. id, pw, name의 길이가 적절한지 체크
		// 5. id에 반드시 들어가야할 것, 들어가지 말아야할 것
		// 6. pw에 반드시 들어가야할 것, 들어가지 말아야할 것
		// 7. name에 반드시 들어가야할 것, 들어가지 말아야할 것
		
		// 회원 정보 생성
		MemberInfo m1 = new MemberInfo(id, pw, name);
					
		// 아이디 중복 체크
		
		// 회원정보 table에 저장
		Database.memberInfoTable.add(m1);
		response.sendRedirect("/web3/member/joinSuccess.html");
		
		
	}

}
