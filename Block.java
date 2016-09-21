public class Block implements Action {
	private int actionPower;
	private String name = "Block";
	
	public Block(Character c) {
		rollPower(c.Strength());
	}
	
	private void rollPower(int stat) {
		actionPower = (int) (Math.random() * 2 * (stat + 1)) + 1;
	}
	
	public void reroll(Character c) {
		rollPower(c.Strength());
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
