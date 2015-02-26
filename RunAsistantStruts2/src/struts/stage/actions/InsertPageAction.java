package struts.stage.actions;

public class InsertPageAction extends BaseAction{

	private static final long serialVersionUID = 8696165597741302701L;
	
	// inputs from chronometer
	private int hours;
	private int minutes;
	private int seconds;
	
	public String execute() throws Exception {
		return "success";
	}
	
	public void validate() {
		if (hours < 0 || hours >= 40)
			addFieldError("hours", "Invalid value!");
		if (minutes < 0 || minutes >= 40)
			addFieldError("minutes", "Invalid value!");
		if (seconds < 0 || seconds >= 40)
			addFieldError("seconds", "Invalid value!");		
	}

	public int getHours() {
		return this.hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return this.seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}
