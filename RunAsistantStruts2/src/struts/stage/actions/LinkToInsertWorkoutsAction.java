package struts.stage.actions;


public class LinkToInsertWorkoutsAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8402146923168922431L;

	public String execute() throws Exception {
		try {
			if (session.get("ID") != null) {				
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "error";
	}
}