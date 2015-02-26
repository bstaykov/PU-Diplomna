package struts.stage.actions;

import java.io.File;
import java.sql.SQLException;

import struts.stage.database.UserRepository;
import struts.stage.utils.MD5;

public class RegistrationAction extends BaseAction {

	private static final long serialVersionUID = -6617962619693604683L;

	private String username;
	private String password;
	private String copyOfPassword;
	private String firstName;
	private String lastName;
	private String city;
	private String email;
	private int age;
	private String gender;	
	private File myFile;
    private String myFileContentType;
    private String myFileFileName;
    
	public RegistrationAction() {
	}

	public RegistrationAction(String username, String password,
			String firstName, String lastName, String city, String email,
			int age, String gender) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.email = email;
		this.age = age;
		this.gender = gender;
	}

	public String execute() throws Exception {
 
		try {
			// check DB for user and email
			try {
				boolean[] dbHasUserMail = UserRepository.checkUserMail(
						username, email);
				if (dbHasUserMail[0] == true) {
					addFieldError("username", "username is occupied!");
				}
				if (dbHasUserMail[1] == true) {
					addFieldError("email", "email is occupied!");
				}
				if (dbHasUserMail[0] == true || dbHasUserMail[1] == true) {
					return "notRegistered";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return "error";
			}
			
			MD5 passToHash = new MD5(password);
			String md5 = passToHash.hashing();
			String result = UserRepository.insertRegisterInfo(username, md5,
					firstName, lastName, 1, city, email, age, gender, myFile);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public void validate() {
		
		if (username == null || username.trim().equals("")) {
			addFieldError("username", "The username is required");
		} else if (username.length() > 25) {
			addFieldError("username", "Maximum 25 simbols");
		} else if (!username.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("username", "The username is incorect");
		}

		if (password == null || password.trim().equals("")) {
			addFieldError("password", "The password is required");
		} else if (password.length() > 25) {
			addFieldError("password", "Maximum 25 simbols");
		} else if (!password.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("password", "The password is incorect");
		}
		
		if (copyOfPassword == null || copyOfPassword.trim().equals("")) {
			addFieldError("copyOfPassword", "Repeat password");
		} else if (copyOfPassword.length() > 25) {
			addFieldError("copyOfPassword", "Maximum 25 simbols");
		} else if (!copyOfPassword.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("copyOfPassword", "The password is incorect");
		}	else if (!copyOfPassword.equals(password)) {
			addFieldError("copyOfPassword", "The passwords are not identicle");
		}
		
		if (firstName == null || firstName.trim().equals("")) {
			addFieldError("firstName", "First name is required");
		} else if (firstName.length() > 25) {
			addFieldError("firstName", "Maximum 25 simbols");
		} else if (!firstName.matches("^[a-zA-Z]{4,25}")) {
			addFieldError("firstName", "First name is incorect");
		}

		if (lastName == null || lastName.trim().equals("")) {
			addFieldError("lastName", "Last name is required");
		} else if (lastName.length() > 25) {
			addFieldError("lastName", "Maximum 25 simbols");
		} else if (!lastName.matches("^[a-zA-Z]{4,25}")) {
			addFieldError("lastName", "Last name is incorect");
		}

		if (city == null || city.trim().equals("")) {
			addFieldError("city", "City is required");
		} else if (city.length() > 25) {
			addFieldError("city", "Maximum 25 simbols");
		} else if (!city.matches("^[a-zA-Z\\s]{4,25}")) {
			addFieldError("city", "City is incorect");
		}

		if (email == null || email.trim().equals("")) {
			addFieldError("email", "Email is required");
		} else if (email.length() > 50) {
			addFieldError("email", "Maximum 25 simbols");
		} else if (!email
				.matches("[A-Za-z0-9!#$%&'*+-/=?^_`{|}~]+@[A-Za-z0-9-]+(.[A-Za-z0-9-]+)*")) {
			addFieldError("email", "Email is incorect");
		}

		if (!gender.equalsIgnoreCase("men")
				&& !gender.equalsIgnoreCase("women")) {
			addFieldError("gender", "Gender is incorect");
		}
		if ((age <= 0 || age > 129)) {
			addFieldError("age", "Age is incorect");
		}
		
		if(myFile == null){
			addFieldError("myFile", "Picture is required");
		} else if (myFile.getTotalSpace() > 107324530688l){
			addFieldError("myFile", "Max size 100kb");
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

	public String getCopyOfPassword() {
		return copyOfPassword;
	}

	public void setCopyOfPassword(String copyOfPassword) {
		this.copyOfPassword = copyOfPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}
	
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

}
