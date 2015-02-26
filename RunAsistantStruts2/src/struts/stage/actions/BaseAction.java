package struts.stage.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport implements Action, SessionAware {

	private static final long serialVersionUID = 7645834351522187005L;
	protected Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String error(String errorMessage) {
		session.put("errorMessage", errorMessage);
		return ERROR;
	}
 }
