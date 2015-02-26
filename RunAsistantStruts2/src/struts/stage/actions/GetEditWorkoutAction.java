package struts.stage.actions;

import java.util.HashMap;
import java.util.Map;

import struts.stage.database.UserRepository;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class GetEditWorkoutAction extends BaseAction {

	private static final long serialVersionUID = 578654502944907296L;

	private int km;
	private int meters;
	private int hours;
	private int minutes;
	private int seconds;
	private String time; // input
	private int year;
	private int month;
	private int day;
	private String date; // input
	private int wo_id; // input
	private String place; // input
	private String distance; // input

	public GetEditWorkoutAction() {

	}

	public String execute() throws Exception {

		if (UserRepository.checkWOID(wo_id)) {
			return "success";
		} else {
			ValueStack stack = ActionContext.getContext().getValueStack();
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("goToLog", "Wrong Workout_ID");
			stack.push(context);
			return "error";
		}
	}

	public void validate() {
		if (Float.parseFloat(distance) < 0
				|| Float.parseFloat(distance) > 999.999)
			addFieldError("distance", "you are chaeting!");
		if (!date.matches("^\\d{4}[-/.]\\d{1,2}[-/.]\\d{1,2}$"))
			addFieldError("date", "you are chaeting!");
		if (wo_id < 0)
			addFieldError("wo_id", "you are chaeting!");
		if (!place.matches("^[a-zA-Z0-9\\s]+$"))
			addFieldError("place", "you are chaeting!");
		if (!time.matches("([0-3][0-9]):([0-5][0-9]):([0-5][0-9])"))
			addFieldError("time", "you are chaeting!");
	}

	public int getWo_id() {
		return wo_id;
	}

	public void setWo_id(int wo_id) {
		this.wo_id = wo_id;
	}

	public int getYear() {
		this.year = Integer.parseInt(date.substring(0, 4));
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		this.month = Integer.parseInt(this.date.substring(5, 7));
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		this.day = Integer.parseInt(this.date.substring(8, 10));
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHours() {
		this.hours = Integer.parseInt(this.time.substring(0, 2));
		return this.hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		this.minutes = Integer.parseInt(this.time.substring(
				this.time.indexOf(":") + 1, this.time.lastIndexOf(":")));
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		this.seconds = Integer.parseInt(this.time.substring(this.time
				.lastIndexOf(":") + 1));
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getKm() {
		this.km = Integer.parseInt(this.distance.substring(0,
				this.distance.indexOf(".")));
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getMeters() {
		this.meters = Integer.parseInt(this.distance.substring(this.distance
				.indexOf(".") + 1));
		int count = this.distance.substring(this.distance
				.indexOf(".") + 1).length();
		
		if(count == 3){
			return this.meters;
		}
		if(count == 2){
			this.meters *= 10;
			return this.meters;
		}
		this.meters *= 100;
		return this.meters;
	}

	public void setMeters(int meters) {
		this.meters = meters;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
}
