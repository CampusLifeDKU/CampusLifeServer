package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.LoginView;
import module.DBConnection;

public class UserDAO {

	private final String LOGIN_SQL = "select * from user where id=? and password=?";
	
	private static UserDAO userDAO;
	static {
		userDAO = new UserDAO();
	}
	public static UserDAO getInstance() {
		return userDAO;
	}
	private UserDAO(){
		
	}
	
	public LoginView login(String userId, String password) {
		
		PreparedStatement pstmt = null;
		LoginView loginView = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getInstance().getConn();
			
			pstmt = conn.prepareStatement(LOGIN_SQL);
			pstmt.setString(1,userId);
			pstmt.setString(2,password);
			rs = pstmt.executeQuery();
			
			//System.out.println("여기 : " + userId);
			
			if (rs.next()) {
				loginView = new LoginView(rs.getString("user_code"), rs.getString("id"));
				System.out.println("저기 : " + loginView);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginView;
	}

	
}
