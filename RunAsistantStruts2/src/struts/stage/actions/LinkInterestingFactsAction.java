package struts.stage.actions;

import java.sql.SQLException;

import struts.stage.database.UserRepository;

public class LinkInterestingFactsAction extends BaseAction {

	private static final long serialVersionUID = -7874416875446058076L;

	public String execute(){
		try {
			return UserRepository.updateFactsCharts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}		
	}
}
