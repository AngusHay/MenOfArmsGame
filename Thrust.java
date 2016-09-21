public class Thrust implements Action {
	private int actionPower;
	private String name = "Thrust";
	
	public Thrust(Character c) {
		rollPower(c.Finesse());
	}
	
	private void rollPower(int stat) {
		actionPower = (int) (Math.random() * 2 * (stat + 1)) + 1;
	}
	
	public void reroll(Character c) {
		rollPower(c.Finesse());
	}
	
	public boolean isAttack() {
		return true;
	}
	
	public int getActionPower() {
		return actionPower;
	}
	
	public String getName() {
		return name;
	}
}
