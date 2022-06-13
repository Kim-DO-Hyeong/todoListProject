package join;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc.mariadb://localhost:3306/todolist?user=root&password=1234");
			
			String sql = "INSERT INTO member_info(memberID,memberPW,name,joinDate) VALUES(?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, LocalDateTime.now().toString());
			
			int count =pstmt.executeUpdate();
			
			
			// 회원 가입 성공 시그널을 보낸다 
			// 생략 가능
			
			boolean joinResult = count ==1 ;
			if(joinResult) {
				// 회원 가입 성공 시그널을 보낸다 
				response.setStatus(HttpServletResponse.SC_OK);
			}else {
				response.setStatus(HttpServletResponse.SC_CONFLICT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn !=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}

}
