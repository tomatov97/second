package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/notice/header")
public class Header extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {						
			boolean isLogin = (boolean) session.getAttribute("isLogin");
			String loginUserName = (String) session.getAttribute("loginUserName");
			String userLevel = (String) session.getAttribute("userLevel");
			
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// jquery한테 줄거니까 json으로 전달하면 좋다!!
			out.print("{\"isLogin\":true"
					+ ", \"loginUserName\":\"" + loginUserName
					+ "\", \"userLevel\":\"" + userLevel + "\"}");
			
			out.close();
			
		} catch (NullPointerException e) {
			// 로그인하지 않은 경우
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("{\"isLogin\":false}");
			
			out.close();
		}
				
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
