package struts.stage.actions;

import java.io.File;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import struts.stage.database.UserRepository;

public class EditProfileAction extends BaseAction {

	private static final long serialVersionUID = 3189199820219851913L;
	
	private String password;
	private String firstName;
	private String lastName;
	private String city;
	private int age;
	private String gender;
	private File myFile;
    private String myFileContentType;
    private String myFileFileName;
	
    public EditProfileAction() {

	}

	public EditProfileAction(
			String firstName, String lastName, String city,	int age, String gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.age = age;
		this.gender = gender;
	}
    
	public String execute() throws Exception {
		try {
			int id = (int) session.get("ID");
			String md5 = passHashing(password);
			boolean checkPassAndID = UserRepository.checkPassAndID(md5,	id);
			if (checkPassAndID == false) {
				addFieldError("password", "Incorrect password!");
				return "input";
			}
			
			String result;
			if(myFile != null) {
				result = UserRepository.editProfileInfo(firstName, lastName, city, age, gender, myFile, id);
			} else {
				result = UserRepository.editProfileInfoNoPic(firstName, lastName, city, age, gender, id);
			}
			if(result.equalsIgnoreCase("success")){
				session.remove("NAMES");
				session.put("NAMES", firstName + " " + lastName);
				ValueStack stack = ActionContext.getContext().getValueStack();
				Map<String, Object> context = new HashMap<String, Object>();
				context.put("updated", "Profile updated successfully!");
				this.password = "";
				stack.push(context);
				
				//Map<Integer, Object> count = new HashMap<Integer, Object>();
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	public void validate() {

		if (password == null || password.trim().equals("")) {
			addFieldError("password", "The password is required");
		} else if (password.length() > 25) {
			addFieldError("password", "Maximum 25 simbols");
		} else if (!password.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("password", "The password is incorect");
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

		if (!gender.equalsIgnoreCase("men")
				&& !gender.equalsIgnoreCase("women")) {
			addFieldError("gender", "Gender is incorect");
		}
		if ((age <= 0 || age > 129)) {
			addFieldError("age", "Age is incorect");
		}
		
		if(myFile != null && myFile.getTotalSpace() > 107324530688l){
			addFieldError("myFile", "Max size 100kb");
		}
	}

	public String passHashing(String pass) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
