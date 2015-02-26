package struts.stage.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class Logout2Action extends BaseAction {

	private static final long serialVersionUID = -3529925624822270690L;

	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
//		session.remove("logined");
//		session.remove("context");
//		session.remove("USERNAME");
//		session.remove("ID");
//		session.remove("ROLE");
//		session.remove("NAMES");
//		session.remove("forward");
//		
		session.clear();
		
		return SUCCESS;
	}

}