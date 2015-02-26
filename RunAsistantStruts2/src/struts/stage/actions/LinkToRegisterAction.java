package struts.stage.actions;

public class LinkToRegisterAction extends BaseAction{

	private static final long serialVersionUID = -5936395529305830422L;

	public String execute() throws Exception {
		try {
			if (session.get("NAMES") != null) {
				session.clear();
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
