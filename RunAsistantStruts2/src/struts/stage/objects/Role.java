package struts.stage.objects;

import java.io.File;

public class Role {
	
	private String username;
	private String role;
	private int id;
	private String names;
	private String f_name;
	private String l_name;
	private String profilePicUrl;

	
	private File picture;

	
	public Role(String role, String user, int id){
		this.role = role;
		this.username = user;
		this.id = id;
	}
	
	public Role(String role, String user, int id, String names){
		this.role = role;
		this.username = user;
		this.id = id;
		this.names=names;
	}
	
	public Role(String role, String user, int id, String f_name, String l_name, String profilePicUrl){
		this.role = role;
		this.username = user;
		this.id = id;
		this.f_name = f_name;
		this.l_name = l_name;
		this.profilePicUrl = profilePicUrl;
	}
	
	public Role(String role, String user, int id, String names, File pic){
		this.role = role;
		this.username = user;
		this.id = id;
		this.names = names;
		this.picture = pic;
	}
	
	public Role(){
	}
	
	public String getRole() {
		return role;
	}

	public String getUsername(){
		return username;
	}
	
	public int getId(){
		return id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	
	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}


	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

}
