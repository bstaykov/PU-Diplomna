package struts.stage.actions;

import java.util.HashMap;
import java.util.Map;

import struts.stage.database.UserRepository;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

public class LinkMyTargetsAction extends BaseAction {

	private static final long serialVersionUID = 5805005881423918883L;
	private int fiveKmTime;
	private int tenKmTime;
	private float km;

	public LinkMyTargetsAction(int fiveKm, int tenKm, float kmLast30Days) {
		this.fiveKmTime = fiveKm;
		this.tenKmTime = tenKm;
		this.km = kmLast30Days;
	}

	public LinkMyTargetsAction() {

	}

	public String execute() throws Exception {
		try {
			String ID = session.get("ID") + "";
			LinkMyTargetsAction bestResults = UserRepository.getBestResults(ID);
			LinkMyTargetsAction targets = UserRepository.getTargets(ID);
			String time5km = calcTime(targets.fiveKmTime);
			String time10km = calcTime(targets.tenKmTime);

			double fiveKmResult = 0;
			if (bestResults.fiveKmTime != 0) {
				fiveKmResult = (double) targets.fiveKmTime
						/ bestResults.fiveKmTime * 100;
			}
			double tenKmResult = 0;
			if (bestResults.tenKmTime != 0) {
				tenKmResult = (double) targets.tenKmTime
						/ bestResults.tenKmTime * 100;
			}
			double monthResult = 0;
			if (bestResults.km != 0 && targets.km != 0) {
				monthResult = (double) bestResults.km / targets.km * 100;
			}

			ValueStack stack = ActionContext.getContext().getValueStack();
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("fiveKM", time5km);
			context.put("tenKM", time10km);
			context.put("km", targets.km);
			context.put("fiveKMTarget", fiveKmResult);
			context.put("tenKMTarget", tenKmResult);
			context.put("kmTarget", monthResult);
			context.put("fiveKMTargetPercent",
					String.format("%.2f", fiveKmResult));
			context.put("tenKMTargetPercent",
					String.format("%.2f", tenKmResult));
			context.put("kmTargetPercent", String.format("%.2f", monthResult));
			stack.push(context);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String calcTime(int seconds) {
		String result = "";
		if (seconds / 60 > 0) {
			result += seconds / 60 + "min";
			seconds = seconds % 60;
		}
		result += seconds + "sec";
		return result;
	}

	public int getFiveKmTime() {
		return fiveKmTime;
	}

	public void setFiveKmTime(int fiveKmTime) {
		this.fiveKmTime = fiveKmTime;
	}

	public int getTenKmTime() {
		return tenKmTime;
	}

	public void setTenKmTime(int tenKmTime) {
		this.tenKmTime = tenKmTime;
	}

	public float getKm() {
		return km;
	}

	public void setKm(float km) {
		this.km = km;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
