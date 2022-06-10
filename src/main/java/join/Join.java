package join;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class Join extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달 받은 값을 꺼낸다 
		request.setCharacterEncoding("UTF-8");
		
		String id =request.getParameter("id");
		String pw =request.getParameter("pw");
		String name =request.getParameter("name");
		// 전달 받은 값을 하나의 회원 정보로 구성한다 
		MemberInfo newMemberInfo = new MemberInfo(id,pw,name);
		
		// 회원 정보를 DB 에 저장한다 
		System.out.println("회원 정보를 DB에 저장했음");
		// 회원 가입 성공 시그널을 보낸다 
		// 생략 가능
		
		boolean joinResult = true;
		if(joinResult) {
			// 회원 가입 성공 시그널을 보낸다 
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		
		
	}

}
