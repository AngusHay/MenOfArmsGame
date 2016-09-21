
public class ActionOption {
	private Action storedAction;
	
	public ActionOption() {
		storedAction = null;
	}
	
	public ActionOption(Action a) {
		storedAction = a;
	}
	
	public Action getStoredAction() {
		return storedAction;
	}
}
