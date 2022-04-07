package notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.NoticeInfo;

@WebServlet("/notice/form")
public class Form extends HttpServlet {
	// 용도 : 공지 사항 쓰기로 들어갈 때 접근해야할 서블릿
	// 		권한이 있는 사용자가 접근했을 때만 공지사항 쓰기 페이지가 보이도록 하고,
	//		권한이 없는 사용자(로그인을 하지 않았거나, 일반 사용자로 로그인)가 접근했을 때는 메인 페이지가 보이도록 하는 서블릿
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userLevel = (String) session.getAttribute("userLevel");

		// 권한이 있는 사용자가 접근했을 때만 공지사항 쓰기 페이지가 보이도록
		try {
			if (userLevel.equals("admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("/notice/form.html");
				rd.forward(request, response);
			} else {
		// 권한이 없는 사용자(로그인을 하지 않았거나, 일반 사용자로 로그인)가 접근했을 때는 메인 페이지가 보이도록
				response.sendRedirect("/web3/main");
			}
		} catch (NullPointerException e) {
				response.sendRedirect("/web3/main");
		}
		

		
	}


}
