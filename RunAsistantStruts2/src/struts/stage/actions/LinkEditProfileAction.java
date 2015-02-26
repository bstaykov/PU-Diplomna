package struts.stage.actions;

import struts.stage.database.UserRepository;

public class LinkEditProfileAction extends BaseAction {

	private static final long serialVersionUID = -1823897033131938489L;

	private String firstName;
	private String lastName;
	private String city;
	private String age;
	private String gender;
	private String url;

	public LinkEditProfileAction(){
	}
	
	public LinkEditProfileAction(String firstName, String lastName, String city, String age, String gender, String url){
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.age = age;
		this.gender = gender;
		this.url = url;
	}

	public String execute() throws Exception {
	
		LinkEditProfileAction info = UserRepository.getProfileInfo((int)session.get("ID"));
		if( info != null){
			firstName = info.getFirstName();
			lastName = info.getLastName();
			city = info.city;
			age = info.age;
			gender = info.gender;
			return "success";
		} else {
			return "error";
		}
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
