package todayAdd;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/todo/update")
public class TodoUpdate extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("할 일 수정 시작");
		
		// 로그인이 되지 않은 상태라면 
		
		
		// 전달 받은 값을 꺼낸다 
		String t_date = request.getParameter("date");
		String t_projectId = request.getParameter("projectId");
		String t_title = request.getParameter("title");
		String t_contents = request.getParameter("contents");
		
		LocalDate date = LocalDate.parse(t_date);
		int projectId = Integer.parseInt(t_projectId);
		
		// 전달받은 값을 하나의 할 일 정보로 구성 한다 
		TodoInfo todoInfo = new TodoInfo(date,projectId,t_title,t_contents);
		
		// 기존의 할 일 정보를 새로운 할 일 정보로 변경한다  
		System.out.println("기존의 할 일 정보를 새로운 할 일 정보로 변경한다");
		
		// 할 일 성공 시그널을 보낸다 
		response.sendRedirect("http://localhost/todolist/index-today.html?date="+t_date);
		
		System.out.println("할 일 수정 종료");
	}

}
