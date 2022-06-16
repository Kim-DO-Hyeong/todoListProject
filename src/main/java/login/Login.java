package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import join.MemberInfo;
import util.DatabaseManager;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("로그인 기능 시작");
		
		// 전달 받은 값을 꺼낸다 	
		String id =request.getParameter("id");
		String pw =request.getParameter("pw");

		// 전달 받은 값을 하나의 로그인 정보로 구성한다 
		MemberInfo loginInfo = new MemberInfo(id,pw);
		
		// 로그인 정보와 일치하는 회원 정보를 DB 에서 조회한다 
		System.out.println("로그인 정보와 일치하는 회원정보를 DB에서 조희 ( 구현 예정 )");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
		
			conn=DatabaseManager.getConnection();
			
			String sql = "SELECT * FROM member_info WHERE memberID = ? AND memberPW = ? ";
			pstmt=DatabaseManager.getPreparedStatment(conn, sql);
			pstmt.setString(1, loginInfo.getId());
			pstmt.setString(2, loginInfo.getPw());
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				// 로그인 성공 상태 정보를 세션에 기록한다 
				HttpSession session = request.getSession();
				
				session.setMaxInactiveInterval(3600);
				session.setAttribute("isLogin",true);
				session.setAttribute("loginUserInfo", "로그인한 사용자의 정보");
				// 로그인 성공 시그널을 보낸다
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		} finally {
			DatabaseManager.closeResultSet(rs);
			DatabaseManager.closePreparedStatment(pstmt);
			DatabaseManager.closeConnection(conn);
		}
		
		
		
//		boolean loginResult = true; // 로그인 성공 / 실패 여부	
//		if(loginResult) {
//			//로그인에 성공했다면
//			
//			// 로그인 성공 상태 정보를 세션에 기록한다 
//			HttpSession session = request.getSession();
//			
//			session.setMaxInactiveInterval(3600);
//			session.setAttribute("isLogin",true);
//			session.setAttribute("loginUserInfo", "로그인한 사용자의 정보");
//			// 로그인 성공 시그널을 보낸다
//			response.setStatus(HttpServletResponse.SC_OK);
//		}else {
//			// 로그인에 실패했다면 
//			
//			// 로그인 실패 시그널을 보내다
//			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//		}
//		 
//		
//		
//		
//		System.out.println("로그인 기능 종료");
	}

}
