package etc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.MemberInfo;
import vo.NoticeInfo;

public class StartupProcessor extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	@Override
	public void init() throws ServletException {
		System.out.println("<< StartupProcessor 동작 >>");
		
		MemberInfo adminInfo = new MemberInfo("admin", "admin123", "관리자");
		MemberInfo memberInfo = new MemberInfo("id1", "pw1", "name1");
		
		Database.memberInfoTable.add(adminInfo);
		Database.memberInfoTable.add(memberInfo);
		
		NoticeInfo noticeInfo = new NoticeInfo("제목1", "내용1");
		Database.noticeInfoTable.add(noticeInfo);
		
		noticeInfo = new NoticeInfo("제목2", "내용2");
		Database.noticeInfoTable.add(noticeInfo);
		
		noticeInfo = new NoticeInfo("제목3", "내용3");
		Database.noticeInfoTable.add(noticeInfo);
		
		noticeInfo = new NoticeInfo("제목4", "내용4");
		Database.noticeInfoTable.add(noticeInfo);
	}
	
	
	
}
