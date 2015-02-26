package struts.stage.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import struts.stage.database.UserRepository;

public class InsertWorkoutAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int km;
	private int meters;
	private float distanceFloat;
	private int hours;
	private int minutes;
	private int seconds;
	private String place;
	private int year;
	private int month;
	private int day;
	private String time;
	private String date;
	private String pace;
	private String kmHour;
	private int wo_id;
	private int timeInSeconds;

	public InsertWorkoutAction() {
	}

	public InsertWorkoutAction(String date, String time, Float km, String place) {
		this.date = date;
		this.time = time;
		this.distanceFloat = km;
		this.place = place;
	}

	public InsertWorkoutAction(String date, String time, Float km,
			String place, String pace, String kmHour, int wo_id) {
		this.date = date;
		this.time = time;
		this.distanceFloat = km;
		this.place = place;
		this.pace = pace;
		this.kmHour = kmHour;
		this.wo_id = wo_id;
	}

	public InsertWorkoutAction(String date, String time, Float km,
			String place, String pace, String kmHour, int wo_id,
			int timeInSeconds) {
		this.date = date;
		this.time = time;
		this.distanceFloat = km;
		this.place = place;
		this.pace = pace;
		this.kmHour = kmHour;
		this.wo_id = wo_id;
		this.timeInSeconds = timeInSeconds;
	}
	
	public InsertWorkoutAction(int km, int meters, int hours, int minutes, int seconds, String place, int year, int month, int day) {
		this.km = km;
		this.meters = meters;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.place = place;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String execute() throws Exception {
		try {
			int ID = (int) session.get("ID");
			String time = hours + ":" + minutes + ":" + seconds;
			String date = year + "-" + month + "-" + day;
			if (!date.matches("^\\d{4}[-/.]\\d{1,2}[-/.]\\d{1,2}$")) {
				return "error";
			}
			int timeInSec = timeInSeconds(hours, minutes, seconds);
			float distance = distance(km, meters);
			String pace = calcPace(timeInSec, distance);
			String kmPerHour = kmPerHour(timeInSec, distance);
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(Calendar.getInstance().getTime());
			ValueStack stack = ActionContext.getContext().getValueStack();
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("inserted", "Workout inserted!");
			stack.push(context);
			String result = UserRepository.insertWorkout(ID, distance, time, place,
					date, pace, kmPerHour, timeStamp, timeInSec);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public void validate() {
		
		if (km < 0 || km > 999) {
			addFieldError("km", "1 to 999 km. ");
		}
		if (meters < 0 || meters > 999) {
			addFieldError("meters", "1 to 999 m. ");
		}
		if (meters == 0 && km == 0) {
			addFieldError("km", "Insert positive distance. ");
		}
		if (hours == 0 && minutes == 0 && seconds == 0) {
			addFieldError("minutes", "Insert positive time! ");
		}
		if (hours < 0 || hours > 39) {
			addFieldError("hours", "0 to 39 hours! ");
		}
		if (minutes < 0 || minutes > 59) {
			addFieldError("minutes", "0 to 59 minutes! ");
		}
		if (seconds < 0 || seconds > 59) {
			addFieldError("seconds", "0 to 59 seconds! ");
		}
		if (day == 0 && month == 0 && year == 0) {
			addFieldError("year", "Incorrect! ");
		}
		if (day < 1 || day > 31) {
			addFieldError("day", "Month has maximum 31 days! ");
		}
		if (month < 1 || month > 12) {
			addFieldError("month", "Year has 12 months! ");
		}
		if (year < 2013 || year > 2015) {
			addFieldError("year", "2013, 2014, 2015 only! ");
		}

		String date = month + "/" + day + "/" + year;
		if (!isValidDate(date)) {
			addFieldError("day", "Invalid date! ");
		}

		if (!place.matches("^[a-zA-Z0-9\\s]+$")) {
			addFieldError("place", "Use letters and numbers only! ");
		}
		if (place.length() > 25) {
			addFieldError("place", "To much symbols! ");
		}
	}

	public int timeInSeconds(int hours, int minutes, int seconds) {
		return hours * 3600 + minutes * 60 + seconds;
	}

	private float distance(float km, float m) {
		float result = km + m / 1000;
		return result;
	}

	public String calcPace(int timeInSeconds, float distance) {
		StringBuilder sb = new StringBuilder();
		int kmPace = (int) (timeInSeconds / distance);
		if (kmPace >= 3600) {
			sb.append(kmPace / 3600);
			if(timeInSeconds < 7200){
				sb.append(" hour ");
			}
			else {
				sb.append(" hours ");
			}
			kmPace = kmPace % 3600;
		}
		if (kmPace >= 60) {
			sb.append(kmPace / 60);
			sb.append(" min ");
			sb.append(kmPace % 60);
			sb.append(" sec ");
		} else {
			sb.append(kmPace % 60);
			sb.append(" sec");
		}
		return sb.toString();
	}

	public String kmPerHour(int timeInSeconds, float distance) {
		String kmPerH = String
				.format("%.2f", (distance * 3600) / timeInSeconds);
		return kmPerH;
	}

	private static boolean isValidDate(String input) {
		String formatString = "MM/dd/yyyy";

		try {
			SimpleDateFormat format = new SimpleDateFormat(formatString);
			format.setLenient(false);
			format.parse(input);
		} catch (ParseException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	public int getWo_id() {
		return wo_id;
	}

	public void setWo_id(int wo_id) {
		this.wo_id = wo_id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	// month += 1; cause the JS in JSP (months value 0 to 11)
	public void setMonth(int month) {
		this.month = month + 1;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getMeters() {
		return meters;
	}

	public void setMeters(int meters) {
		this.meters = meters;
	}

	public float getDistanceFloat() {
		return distanceFloat;
	}

	public void setDistanceFloat(float distance) {
		this.distanceFloat = distance;
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

	public String getPace() {
		return pace;
	}

	public String getKmHour() {
		return kmHour;
	}

	public int getTimeInSeconds() {
		return timeInSeconds;
	}

	public void setTimeInSeconds(int timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}
}
