package todayAdd;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/todo/add")
public class TodoAdd extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("할일 추가 기능 시작");
		
		
		// 전달 받은 값을 꺼낸다 
		request.setCharacterEncoding("UTF-8");
		
		String t_date = request.getParameter("date");
		String t_projectId = request.getParameter("projectId");
		String t_title = request.getParameter("title");
		String t_contents = request.getParameter("contents");
		
		LocalDate date = LocalDate.parse(t_date);
		int projectId = Integer.parseInt(t_projectId);
		
		// 전달받은 값을 하나의 할 일 정보로 구성 한다 
		TodoInfo newTodoInfo = new TodoInfo(date,projectId,t_title,t_contents);
		
		// 할 일 정보를 DB 에 저장한다 
		System.out.println("할일 정보를 DB 에 저장( 구현 예정 )");
		// 할 일 성공 시그널을 보낸다 
		response.sendRedirect("http://localhost/todolist/index-today.html?date="+t_date);
	
		System.out.println("할일 추가 기능 종료");
	}

}
