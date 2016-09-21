public class Chop implements Action {
	private int actionPower;
	private String name = "Chop";
	
	public Chop(Character c) {
		rollPower(c.Strength());
	}
	
	private void rollPower(int stat) {
		actionPower = (int) (Math.random() * 2 * (stat + 1)) + 1;	// Power range is 1 to 2 + (stat * 2)
	}
	
	public void reroll(Character c) {
		rollPower(c.Strength());
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
