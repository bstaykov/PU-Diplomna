package struts.stage.actions;
import struts.stage.database.UserRepository;

public class DeleteWorkoutAction extends BaseAction {

	private static final long serialVersionUID = 1524791613660391488L;
	private int wo_id;
	
	public DeleteWorkoutAction(int wo_id)
	{
		this.wo_id = wo_id;
	}
	
	public DeleteWorkoutAction()
	{
	}
	
	public String execute() throws Exception {
		try {
			
			if (!UserRepository.findWorkout(wo_id)) {
				return "error";
			} else {
				if(UserRepository.deleteWorkout(wo_id))
				{
					return "success";
				}
				else {
					return "error";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
	}

	public int getWo_id() {
		return wo_id;
	}

	public void setWo_id(int wo_id) {
		this.wo_id = wo_id;
	}
}
