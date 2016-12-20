package manager;

import dao.UserDAO;
import dto.LoginView;

public class UserManager {

	private static final UserManager instance = new UserManager();
	private static UserDAO userDao;
	
	private UserManager() {
		userDao = UserDAO.getInstance();
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public LoginView login(String userId, String password) {
		return userDao.login(userId, password);
	}
	
	
}
