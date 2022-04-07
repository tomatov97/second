package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import etc.Database;
import vo.NoticeInfo;

/**
 * Servlet implementation class ReadNotice
 */
@WebServlet("/notice/readnotice")
public class ReadNotice extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<String> noticeList = new ArrayList<>();
		
		for (NoticeInfo notice : Database.noticeInfoTable) {
			String title = notice.getTitle();
			String contents = notice.getContents();
			
			String noticeInfo = "{\"title\":\"" + title + "\", \"contents\":\"" + contents + "\"}";	
			noticeList.add(noticeInfo);
		}
		out.print("{\"noticeList\":" + noticeList.toString() + "}");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
