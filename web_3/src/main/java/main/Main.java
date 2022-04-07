package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class Main extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		try {
			boolean isLogin = (boolean) session.getAttribute("isLogin");
			String loginUserName = (String) session.getAttribute("loginUserName");
			String userLevel = (String) session.getAttribute("userLevel");
			String writeNoticeBtnTag = "";
			if (userLevel.equals("admin")) {
				writeNoticeBtnTag = "<button type=\"button\" id=\"notice-write\">공지사항 작성</button>";
			}
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print("<!DOCTYPE html>"
					+ "<html>"
					+ "<head>"
					+ "<meta charset=\"UTF-8\">"
					+ "<title>Servlet Project</title>"
					+ "<link rel=\"stylesheet\" href=\"/web3/css/header.css\">"
					+ "<link rel=\"stylesheet\" href=\"/web3/css/footer.css\">"
					+ "<link rel=\"stylesheet\" href=\"/web3/css/main_index.css\">"
					+ "</head>"
					+ "<body>"
					+ "	<header>"
					+ "		<div id=\"login_area\">"
					+ 			loginUserName + "님 환영합니다~"
					+ " 	</div>"
					+ " 		<div id=\"join_area\">"
					+ 		writeNoticeBtnTag
					+ "				<button type=\"button\" id=\"logout\">로그아웃</button>"
					+ " 		</div>"
					+ "	</header>"
					+ "	"
					+ "	<main>"
					+ "		<h2>공지사항</h2>"
					+ "		<div id=\"notice_list\">"
					+ "			<p>공지사항이 없습니다.</p>"
					+ "		</div>"
					+ "	</main>"
					+ "	"
					+ "	<footer>메가스터디 IT 아카데미 웹개발 취업반 Servlet 프로젝트</footer>"
					+ "	"
					+ "	<script src=\"/web3/jquery/jquery-3.6.0.min.js\"></script>"
					+ "  	<script>"
					+ "  		$(\"#logout\").on(\"click\", function() {"
					+ "					location.href = \"/web3/member/logout\""
					+ "  		});"
					+ "  		$(\"#notice-write\").on(\"click\", function() {"
					+ "					location.href = \"/web3/notice/form\""
					+ "  		});"
					+ "  		$.ajax({"
					+ "  			url: \"/web3/notice/readnotice\","
					+ "  			type: \"get\","
					+ "  			dataType:\"json\","
					+ "  			success: function(noticeList) {"
					+ "				let length = noticeList[\"noticeList\"].length;"
					+ "  				for (let i = 0; i < length; i++) {"
					+ "					let title = noticeList.noticeList[i].title;"
					+ "					$(\"#notice_list\").prepend("
					+ "							  \"<div class=\"contents\">\""
					+ "		                    + \"	<a href=\"\">\""
					+ "		                    + \"    <span class=\"title\">\" + title + \"</span>\""
					+ "		                    + \"	</a>\""
					+ "		                    + \"</div>\");"
					+ "				}"
					+ "  			},"
					+ "  			error: function(response) {"
					+ "  				console.log(\"에러 동작!\");"
					+ "  				console.log(response);"
					+ "  			}  			"
					+ "  		})"
					+ "  	</script>"
					+ "</body>"
					+ "</html>");
			
		} catch (NullPointerException e) {
			RequestDispatcher rd = request.getRequestDispatcher("/main/index.html");
			rd.forward(request,response);
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
