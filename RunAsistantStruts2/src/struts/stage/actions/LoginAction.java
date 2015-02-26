package struts.stage.actions;

import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import struts.stage.database.UserRepository;
import struts.stage.objects.Role;

public class LoginAction extends BaseAction {
	
	private static final long serialVersionUID = -8778961726692205690L;
	private String username;
	private String password;

	public LoginAction() {
	}
	
	public LoginAction(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String execute() throws Exception {
		System.out.println("Inside LoginAction");
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();
		String result = validateUserPass( username, password);
		if (result.equalsIgnoreCase("")) {
			
			if (session.get("ID") != null) {
				context.put("loggedIn", "You are logged in!");
				stack.push(context);
				return "error";
			}
			
			try {
				String md5 = passHashing(password);
				Role role = UserRepository.checkUserAndPass(username,
						md5);

				
				if (role != null) {
					// set SESSION properties
					Map<String, Object> session = ActionContext.getContext()
							.getSession();
					session.put("Date", new Date());
					session.put("ID", role.getId());
					session.put("ROLE", role.getRole());
					session.put("USERNAME", role.getUsername());					
					session.put("PASSWORD", convertPass(password));
					session.put("NAMES", role.getNames());
					
					// login and forward to page
					if(session.get("forward") != null){
						String resultToForward = session.get("forward").toString();
						session.remove("forward");
						System.out.println("resultToForward = " + resultToForward);
						return resultToForward;
					}
					return "success";
				}
				context.put("login", "Wrong user or pass");
				stack.push(context);
				return "error";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}
		context.put("incorectSimbol", result);
		stack.push(context);
		return "error";
	}

	public String logout() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
//		session.remove("Date");
//		session.remove("ID");
//		session.remove("ROLE");
//		session.remove("USERNAME");
//		session.remove("PASSWORD");
		session.clear();
		return SUCCESS;
	}

	// works with struts form!!!

/*	public void validate() {
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "The username is required");
		}
		if (password == null || password.trim().equals("")) {
			addFieldError("password", "The password is required");
		}

		if (username.length() > 25) {
			addFieldError("username", "Maximum 25 simbols");
		}
		if (password.length() > 25) {
			addFieldError("password", "Maximum 25 simbols");
		}
		boolean check = false;
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == password.toUpperCase().charAt(i)) {
				check = true;
				break;
			}
		}
		if (!check) {
			addFieldError("password", "Password is case sensitive!");

		}
		check = false;
		for (int i = 0; i < username.length(); i++) {
			if (username.charAt(i) == username.toUpperCase().charAt(i)) {
				check = true;
				break;
			}
		}
		if (!check) {
			addFieldError("username", "Username is case sensitive!");
		}

	}
*/
	public String validateUserPass(String user, String pass) {
		String result = "";
		if (user.length() > 25 || user == null
				|| user.trim().equals("")) {
			result += "To much simbols for username! \n";
		}
		if (pass.length() > 25 || pass == null || pass.trim().equals("")) {
			result += "To much simbols for password! \n";
		}
		boolean check = user.matches("^[a-zA-Z0-9]+$");
		if(!check){
			result += "Illegal symbols for user!\n";
			}
		check = pass.matches("^[a-zA-Z0-9]+$");
		if(!check){
			result += "Illegal symbols for user!\n";
			}
		return result;
	}
	
	public String convertPass(String password){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < password.length(); i++) {
			sb.append('*');
		}
		return sb.toString();
	}

	public String passHashing(String pass) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        
        byte byteData[] = md.digest();
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
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
