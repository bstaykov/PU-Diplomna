package struts.stage.objects;

public class Competition {

	private int competition_id;
	private String competition_name;
	
	public Competition(int competition_id, String competition_name){
		this.competition_id = competition_id;
		this.competition_name = competition_name;
	}
	
	public int getCompetition_id() {
		return competition_id;
	}

	public void setCompetiton_id(int competition_id) {
		this.competition_id = competition_id;
	}

	public String getCompetition_name() {
		return competition_name;
	}

	public void setCompetition_name(String competition_name) {
		this.competition_name = competition_name;
	}
}
