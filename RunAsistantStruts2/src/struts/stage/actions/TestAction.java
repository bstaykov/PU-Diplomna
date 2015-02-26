package struts.stage.actions;

public class TestAction extends BaseAction{

	private static final long serialVersionUID = 8049921800472386388L;

	public TestAction(){
		
	}
	
	public String execute() throws Exception {
//		if (session.get("ID") != null) {
//			return "success";
//		}
//		ValueStack stack = ActionContext.getContext().getValueStack();
//		Map<String, Object> context = new HashMap<String, Object>();
//		context.put("login", "YOU MUST LOGIN!!");
//		stack.push(context);
//		// login and forward to page
//		session.put("forward", "test");
//		return "error";
		return "success";
	}
}
