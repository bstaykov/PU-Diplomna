package struts.stage.actions;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.util.ValueStack;

public class LinkSetTargetsAction extends BaseAction{

	private static final long serialVersionUID = -1264798701849967480L;

	public String execute() throws Exception {
		return "success";
//		try {
//			if (session.get("ID") != null) {				
//				return "success";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error";
//		}
//		ValueStack stack = ActionContext.getContext().getValueStack();
//		Map<String, Object> context = new HashMap<String, Object>();
//		// login and forward to page
//		session.put("forward", "targets");
//		context.put("login", "YOU MUST LOGIN!");
//		stack.push(context);
//		return "error";
	}
}
