package struts.stage.actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import struts.stage.database.UserRepository;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class UpdateWorkoutAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private int km;
	private int meters;
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

	public UpdateWorkoutAction() {

	}

	public String execute() throws Exception {
		if (UserRepository.checkWOID(wo_id)) {
			int ID = (int) session.get("ID");
			String time = hours + ":" + minutes + ":" + seconds;
			String date = year + "-" + month + "-" + day;
			if (date.matches("^\\d{4}[-/.]\\d{1,2}[-/.]\\d{1,2}$")) {
				int timeInSec = timeInSeconds(hours, minutes, seconds);
				float distance = distance(km, meters);
				String pace = calcPace(timeInSec, distance);
				String kmPerHour = kmPerHour(timeInSec, distance);
				String actualDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.format(Calendar.getInstance().getTime());
				return UserRepository.updateWorkout(ID, distance, time, place,
						date, pace, kmPerHour, actualDate, wo_id, timeInSec);
			}
		}
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("UpdateError", "UPDATE ERROR");
		stack.push(context);
		return "error";
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

	public int timeInSeconds(int hours, int minutes, int seconds) {
		return hours * 3600 + minutes * 60 + seconds;
	}

	public String calcPace(int timeInSeconds, float distance) {
		StringBuilder sb = new StringBuilder();		
		int kmPace = (int)(timeInSeconds / distance);
		if (kmPace >= 3600) {
			sb.append(kmPace / 3600);
			sb.append("hours");
			kmPace = kmPace % 3600;
		}
		if (kmPace >= 60) {
			sb.append(kmPace / 60);
			sb.append("min");
			sb.append(kmPace % 60);
			sb.append("sec");
		} else {
			sb.append(kmPace % 60);
			sb.append("sec");
		}
		return sb.toString();
	}
	
	private float distance(int km, int m) {
		float result = km + (float)m / 1000;
		return result;
	}

	public String kmPerHour(int timeInSeconds, float distance) {
		String kmPerH = String.format("%.2f", (distance * 3600) / timeInSeconds);
		return kmPerH;
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

	public void setMonth(int month) {
		this.month = month;
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

	public void setKm(String km) {
		if(km.isEmpty())
			this.km = 0;
		else{
			try {
				this.km = Integer.parseInt(km);

			   } catch (NumberFormatException ex) {
				   this.km = 0;
			   }
		}
	}

	public int getMeters() {
		return meters;
	}

	public void setMeters(String meters) {
		if(meters.isEmpty())
			this.meters = 0;
		else{
			try {
				this.meters = Integer.parseInt(meters);

			   } catch (NumberFormatException ex) {
				   this.meters = 0;
			   }
		}
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
}
