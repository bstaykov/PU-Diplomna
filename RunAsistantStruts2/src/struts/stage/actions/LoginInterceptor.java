package struts.stage.actions;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3431526972325026885L;

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext()
				.getSession();
		String ID = String.valueOf(session.get("ID"));
		String currentAction = invocation.getAction().getClass().getName();
		String currentActionName = currentAction.substring(currentAction.lastIndexOf('.') + 1);
		if (ID.equalsIgnoreCase("null")){
			if(currentActionName.equalsIgnoreCase("LoginTestInterceptorAction")){
				String result = invocation.invoke();
				if(result == "success"){
					String forwardAction = session.get("forward").toString();
					session.remove("forward");
					return forwardAction;
				}
				return result;
			} else {
				session.put("forward", currentActionName);
				ValueStack stack = ActionContext.getContext().getValueStack();
				Map<String, Object> context = new HashMap<String, Object>();
				context.put("login", "YOU MUST LOGIN!");
				stack.push(context);
				return "loginInterceptorDispatch";
			}
		} else {
			if(session.get("forward") != null){
				String forwardAction = session.get("forward").toString();
				session.remove("forward");
				return forwardAction;
			}
			else {
				String result = invocation.invoke();
				return result;
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
		System.out.println("init()");

	}
}
