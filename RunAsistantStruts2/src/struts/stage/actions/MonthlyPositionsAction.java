package struts.stage.actions;

import java.util.ArrayList;
import java.util.Map;

import struts.stage.database.UserRepository;

public class MonthlyPositionsAction extends BaseAction {

	private static final long serialVersionUID = 2059560711797240832L;
	
	private Map<String, MonthlyPositionsAction> ranking;
	private ArrayList<MonthlyPositionsAction> arr;
	private int ID;
	private String name;
	private Float km;

	public MonthlyPositionsAction(){
		
	}
	
	public MonthlyPositionsAction(String name, float km){
		this.name = name;
		this.km = km;
	}
	
	public MonthlyPositionsAction(int id, String name, float km){
		this.ID = id;
		this.name = name;
		this.km = km;
	}
	
	
	public String execute() throws Exception {
		arr = UserRepository.getLastMonthRanking();
//		if(ranking != null){
//			Iterator<String> iter = ranking.keySet().iterator();
//			while (iter.hasNext()) {  
//				   String key = iter.next();  
//				   MonthlyPositionsAction values = ranking.get(key);
//				   System.out.println(values.getFirst_name() + " " + values.last_name + " " + values.getKm());
//				}  
//		}
		//arr = sort();
		
		// get top 10;
		
//		if(arr.size() > 10){
//			ArrayList<MonthlyPositionsAction> resizedList = new ArrayList<MonthlyPositionsAction>();
//			for (int i = 0; i < 10; i++) {
//				resizedList.add(i, arr.get(i));
//			}
//			arr = resizedList;
//		}
//		
//		for (int j = 0; j < arr.size(); j++) {
//			System.out.println(arr.get(j).getName() + " " +arr.get(j).getKm());
//		}// end for
		
		return "success";
	}
	
//	public ArrayList<MonthlyPositionsAction> sort() {
//		ArrayList<MonthlyPositionsAction> arr = new ArrayList<MonthlyPositionsAction>();
//		MonthlyPositionsAction person = new MonthlyPositionsAction("",0);
//		arr.add(person);
//
//		Collection<MonthlyPositionsAction> col = this.ranking.values();
//		Iterator<MonthlyPositionsAction> it = col.iterator();
//
//		while (it.hasNext()) {
//			MonthlyPositionsAction nextPerson = it.next();
//
//			for (int i = 0; i < arr.size(); i++) {
//				MonthlyPositionsAction p = arr.get(i);
//				if (nextPerson.getKm() > p.getKm()) {
//					arr.add(i, nextPerson);
//					break;
//				}// end if
//			}// end for
//			if (!arr.contains(nextPerson)) {
//				arr.add(nextPerson);
//			}
//		}// end while
//		
//		int size = arr.size();
//		arr.remove(size - 1);
////		for (int j = 0; j < arr.size(); j++) {
////			System.out.println(arr.get(j).getFirst_name() + " " + arr.get(j).getKm());
////		}// end for
//		return arr;
//	}// end sort
	
	public Map<String, MonthlyPositionsAction> getRanking() {
		return ranking;
	}

	public void setRanking(Map<String, MonthlyPositionsAction> ranking) {
		this.ranking = ranking;
	}

	public ArrayList<MonthlyPositionsAction> getArr() {
		return arr;
	}

	public void setArr(ArrayList<MonthlyPositionsAction> arr) {
		this.arr = arr;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getKm() {
		return km;
	}

	public void setKm(Float km) {
		this.km = km;
	}
}
