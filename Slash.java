public class Slash implements Action {
	private int actionPower;
	private String name = "Slash";
	
	public Slash(Character c) {
		rollPower(c.Finesse() + c.Strength());
	}
	
	private void rollPower(int stat) {
		actionPower = (int) (Math.random() * (stat + 3)) + 1;	// Higher base damage than Parry or Block, and scales with both stats, but can't be specialized for
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
