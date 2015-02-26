package struts.stage.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

import struts.stage.database.UserRepository;

public class LinkToWorkoutsAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ArrayList<InsertWorkoutAction> myWorkouts; //output
	private int workoutsCount;
	
	public String execute() throws Exception {
		ValueStack stack = ActionContext.getContext().getValueStack();
		Map<String, Object> context = new HashMap<String, Object>();
		try {
			int ID = (int) session.get("ID");
			myWorkouts = UserRepository.getMyWorkouts(ID);
			workoutsCount = myWorkouts.size();
			
			String fiveKM = "";
			String resultKM = UserRepository.best5KM(ID);
			if(resultKM != null){
				fiveKM = resultKM;
			}

			String tenKM = "";
			resultKM = UserRepository.best10KM(ID);
			if(resultKM != null){
				tenKM = resultKM;
			}
			
			float totalKilometars = totalKM(myWorkouts);
			int totalTime = totalTimeSeconds(myWorkouts);
			String totalTimeString = totalTime(totalTime);
			float averageTime = 0f;
			
			if(totalKilometars != 0 && totalKilometars != 0.0) {
				averageTime = kmPerHour(totalTime, totalKilometars); 
			}
			
			context.put("fiveKM", fiveKM);
			context.put("tenKM", tenKM);
			context.put("totalKM", totalKilometars);
			context.put("totalTime", totalTimeString);
			context.put("averageTime", String.format("%.2f", averageTime));
			if(session.get("inserted") != null){
				context.put("inserted", session.get("inserted"));
				session.remove("inserted");
			}
			stack.push(context);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public float totalKM(ArrayList<InsertWorkoutAction> myWorkouts){
		float totalKm = 0;
		for (InsertWorkoutAction insertWorkoutAction : myWorkouts) {
			totalKm += insertWorkoutAction.getDistanceFloat();
		}
		return totalKm;
	}
	public int totalTimeSeconds(ArrayList<InsertWorkoutAction> myWorkouts){
		int totalTime = 0;
		for (InsertWorkoutAction insertWorkoutAction : myWorkouts) {
			totalTime += insertWorkoutAction.getTimeInSeconds();
		}
		return totalTime;
	}
	
	public float kmPerHour(int timeInSeconds, float distance) {
		float kmPerH = (distance * 3600) / timeInSeconds;
		return kmPerH;
	}
	
	public String totalTime(int timeInSeconds) {
		StringBuilder sb = new StringBuilder();
		if (timeInSeconds >= 3600) {
			sb.append(timeInSeconds / 3600);
			if(timeInSeconds < 7200){
				sb.append(" hour ");
			}
			else {
				sb.append(" hours ");
			}
			timeInSeconds = timeInSeconds % 3600;
		}
		if (timeInSeconds >= 60) {
			sb.append(timeInSeconds / 60);
			sb.append(" min ");
			sb.append(timeInSeconds % 60);
			sb.append(" sec");
		} else {
			sb.append(timeInSeconds % 60);
			sb.append(" sec");
		}
		return sb.toString();
	}
	
	public ArrayList<InsertWorkoutAction> getMyWorkouts() {
		return myWorkouts;
	}

	public int getWorkoutsCount() {
		return workoutsCount;
	}

	
}
