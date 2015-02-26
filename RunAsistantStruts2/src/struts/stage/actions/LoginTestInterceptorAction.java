package struts.stage.actions;

import java.sql.SQLException;
import java.util.Map;

import struts.stage.database.UserRepository;
import struts.stage.objects.Role;
import struts.stage.utils.MD5;

import com.opensymphony.xwork2.ActionContext;

public class LoginTestInterceptorAction extends BaseAction{

	
	private static final long serialVersionUID = 8049921800472386388L;

	private String username;
	private String password;
	
	public LoginTestInterceptorAction(){
	}
	
	public LoginTestInterceptorAction(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String execute() throws Exception {
		try {
			if (session.get("ID") != null) {
				addFieldError("username", "You are logged in as " + session.get("NAMES"));
				return "login";
			}
			
			boolean userExist = false;
			try {
				userExist = UserRepository.checkUser(username);
				if(userExist == false){
					//addFieldError("username", "Incorrect username");
					addFieldError("username", "Incorrect username or password");
					
					return "login";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MD5 passToHash = new MD5(password);
			String md5 = passToHash.hashing();
			Role role = UserRepository.checkUserAndPass(username,
					md5);
			if (role != null) {
				// set SESSION properties
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				session.put("ID", role.getId());
				session.put("NAMES", role.getF_name() + " " + role.getL_name());
				session.put("ProfilePicUrl", role.getProfilePicUrl());
				
				// login and forward to page
				if(session.get("forward") != null){
					String resultToForward = session.get("forward").toString();
					session.remove("forward");
					return resultToForward;
				}
				return "success";
			}
			
			addFieldError("password", "Incorrect password!");
			
			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public void validate(){
		
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "The username is required");
		} else if (username.length() > 25) {
			addFieldError("username", "Maximum 25 simbols");
		} else if(!username.matches("^[a-zA-Z0-9]+$")){
			addFieldError("username", "Incorrect symbols");	
		}
		
		if (password == null || password.trim().equals("")) {
			addFieldError("password", "The password is required");
		} else if (password.length() > 25) {
			addFieldError("password", "Maximum 25 simbols");
		} else if(!password.matches("^[a-zA-Z0-9]+$")){
			addFieldError("password", "Incorrect symbols");	
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
