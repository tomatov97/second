package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import etc.Database;
import vo.MemberInfo;

@WebServlet("/member/login")
public class Login extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String pw = (String) request.getParameter("pw");
		
		// id, pw 일치하는 정보 찾기		
		MemberInfo member = null;
		
		String loginUserName = null;
		
		for (MemberInfo nthMember : Database.memberInfoTable) {
			String nthId = nthMember.getId();
			String nthPw = nthMember.getPw();
			
			if (nthId.equals(id) && nthPw.equals(pw)) {
				member = nthMember;
				loginUserName = nthMember.getName();
				break;
					}
		}
		
		// 있다면 로그인 성공 아니면 실패
		if (member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			
			if (id.equals("admin")) {
				session.setAttribute("userLevel", "admin");
			} else {
				session.setAttribute("userLevel", "user");
			}
			
			session.setAttribute("loginUserName", loginUserName);
			
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print(loginUserName);
			
			out.close();
			
		} else {
			response.setStatus(400);
		}				
	}
		
	}
