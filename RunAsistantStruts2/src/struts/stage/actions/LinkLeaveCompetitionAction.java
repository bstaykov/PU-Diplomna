package struts.stage.actions;

import java.sql.SQLException;

import struts.stage.database.UserRepository;

public class LinkLeaveCompetitionAction extends BaseAction{
	
	private static final long serialVersionUID = 5660247357341055608L;
	private int competition_id;
	
	public LinkLeaveCompetitionAction(){
		
	}
	
	public String execute() {
		int id = (int) session.get("ID");
		String join = null;
		try {
			join = UserRepository.leaveCompetition(id, competition_id);
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
