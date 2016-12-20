package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dto.LoginView;
import manager.UserManager;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		response.setContentType("application/json;charset=utf-8");
		
		String loginId = request.getParameter("id");
		String loginPwd = request.getParameter("password");
		
		//String userAgent = request.getHeader("User-Agent").split("/")[0];
		
		//System.out.println(userAgent);
		System.out.println("Login ID : "+loginId);
		System.out.println("Login PWD  : "+loginPwd);
		
		UserManager userM = UserManager.getInstance();
		LoginView loginView = userM.login(loginId, loginPwd);
		//LoginView loginView = new LoginView("0", loginId);
		
		System.out.println(" loginView : " + loginView);
 		
 		JSONObject loginJson = new JSONObject();
		/*---------------¸ð¹ÙÀÏÀ» »ç¿ëÇÏ´Â °æ¿ì --------------------*/
		//if ( userAgent.toLowerCase().equals("okhttp")  ) {
			try {
				loginJson.put("resultCode", "1");
				loginJson.put("timestamp", System.currentTimeMillis());
				loginJson.put("userCode", loginView.getUserCode());
				loginJson.put("id", loginView.getId());
				
			} catch(NullPointerException npe) {
				npe.printStackTrace();
				loginJson.put("resultCode", "0");
				loginJson.put("errorCode", "");
				loginJson.put("errorDescription", "");
				
			} finally {
				System.out.println("Login Server Response : OK\n-----------------------------------------\n");
				
				PrintWriter pw = response.getWriter();
				pw.print(loginJson.toString());
				pw.close();
			}
			return ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
