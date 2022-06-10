package projectAdd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/project/add")
public class ProjectAdd extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("프로젝트 추가 기능 시작");
		
		// 로그인이 되지 않은 상태라면 
		
		// 전달 받은 값을 꺼낸다 
		String projectName = request.getParameter("projectName");
		
		// 전달 받은 값을 하나의 프로젝트로 정보를 구성한다 
		ProjectInfo newProjcetInfo = new ProjectInfo(projectName);
		
		// 프로젝트 정보를 DB에 저장한다
		
		System.out.println("프로젝트를 DB 에 저장함 ");
		
		// 프로젝트 등록 성공 시그널을 보낸다 
		response.sendRedirect("http://localhost/todolist/index-project.html");
	
		System.out.println("프로젝트 추가 기능 종료");
	}

}
