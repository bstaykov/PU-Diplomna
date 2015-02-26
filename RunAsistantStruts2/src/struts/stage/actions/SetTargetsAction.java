package struts.stage.actions;

import struts.stage.database.UserRepository;

public class SetTargetsAction extends BaseAction{

	private static final long serialVersionUID = 6798504558691318018L;

	private int minutesFive;
	private int secondsFive;
	private int minutesTen;
	private int secondsTen;
	private int km;
	private String submit;// no use

	public String execute() throws Exception {
		try {
			int ID = (int) session.get("ID");
			int totalSecondsFive = minutesFive*60 + secondsFive;
			int totalSecondsTen = minutesTen*60 + secondsTen;
			
			boolean updTargets = UserRepository.updateTargets(ID, totalSecondsFive, totalSecondsTen, km);
			
			if(updTargets == false){
				return "error";
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	} 

	public void validate() {
		
		if (minutesFive < 0 || minutesFive > 59) {
			addFieldError("minutesFive", "Insert target 0..59min");
		}
		if (secondsFive < 0 || secondsFive > 59) {
			addFieldError("secondsFive", "Insert target 0..59sec");
		}
		if (minutesTen < 0 || minutesTen > 59) {
			addFieldError("minutesTen", "Insert target 0..59min");
		}
		if (secondsTen < 0 || secondsTen > 59) {
			addFieldError("secondsTen", "Insert target 0..59sec");
		}
		if (km < 0 || km > 999) {
			addFieldError("km", "Insert target 0..999km");
		}
		
		if(km == 0 && minutesFive == 0 && minutesTen == 0 && secondsFive == 0 && secondsTen == 0){
			addFieldError("km", "0 is not a target!");
		}
	}

	public int getMinutesFive() {
		return minutesFive;
	}

	public void setMinutesFive(int minutesFive) {
		this.minutesFive = minutesFive;
	}

	public int getSecondsFive() {
		return secondsFive;
	}

	public void setSecondsFive(int secondsFive) {
		this.secondsFive = secondsFive;
	}

	public int getMinutesTen() {
		return minutesTen;
	}

	public void setMinutesTen(int minutesTen) {
		this.minutesTen = minutesTen;
	}

	public int getSecondsTen() {
		return secondsTen;
	}

	public void setSecondsTen(int secondsTen) {
		this.secondsTen = secondsTen;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
