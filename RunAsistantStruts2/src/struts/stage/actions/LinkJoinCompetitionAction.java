package struts.stage.actions;

import java.sql.SQLException;

import struts.stage.database.UserRepository;

public class LinkJoinCompetitionAction extends BaseAction{
	
	private static final long serialVersionUID = 6040305325193304380L;
	private int competition_id;
	
	public LinkJoinCompetitionAction(){
		
	}
	
	public String execute() {
		int id = (int) session.get("ID");
		String join = null;
		try {
			join = UserRepository.joinCompetition(id, competition_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return join;
		}
		return join;
	}
	
	public void validate(){
		if (competition_id < 0)
			addFieldError("competition_id", "you are chaeting!");
	}
	
	public int getCompetition_id() {
		return competition_id;
	}

	public void setCompetition_id(int competition_id) {
		this.competition_id = competition_id;
	}
	
}
