public class Parry implements Action {
	private int actionPower;
	private String name = "Parry";
	
	public Parry(Character c) {
		rollPower(c.Finesse());
	}
	
	private void rollPower(int stat) {
		actionPower = (int) (Math.random() * 2 * (stat + 1)) + 1;
	}
	
	public void reroll(Character c) {
		rollPower(c.Finesse());
	}
	
	public boolean isAttack() {
		return false;
	}
	
	public int getActionPower() {
		return actionPower;
	}
	
	public String getName() {
		return name;
	}
}
