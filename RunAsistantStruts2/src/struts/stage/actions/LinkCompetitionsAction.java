package struts.stage.actions;

import java.sql.SQLException;
import java.util.ArrayList;

import struts.stage.database.UserRepository;
import struts.stage.objects.Competition;
import struts.stage.objects.CompetitionsData;

public class LinkCompetitionsAction extends BaseAction{
	
	private static final long serialVersionUID = 5703364023473308957L;
	private boolean isParticipating;
	
	private ArrayList<Competition> competitions;
	private ArrayList<CompetitionsData> competitionsData;

	public LinkCompetitionsAction(){
		
	}
	
	public String execute() {
		
		try {
			competitions = UserRepository.getCompetitionsList();
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		
		try {
			competitionsData = UserRepository.getCompetitionsData(competitions);
		} catch (SQLException e) {
			e.printStackTrace();
			return "error";
		}
		
		int competition_id = competitions.get(0).getCompetition_id();
		int id = (int) session.get("ID");
		try {
			isParticipating = UserRepository.isParticipating(competition_id, id);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	public ArrayList<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(ArrayList<Competition> competitions) {
		this.competitions = competitions;
	}
	
	public ArrayList<CompetitionsData> getCompetitionsData() {
		return competitionsData;
	}

	public void setCompetitonsData(ArrayList<CompetitionsData> competitionsData) {
		this.competitionsData = competitionsData;
	}

	public boolean getIsParticipating() {
		return isParticipating;
	}

	public void setIsParticipating(boolean isParticipating) {
		this.isParticipating = isParticipating;
	}

}
