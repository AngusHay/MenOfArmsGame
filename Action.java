public interface Action {
	int actionStrength = 0;
	String name = null;
	
	boolean isAttack();
	
	public void reroll(Character c);
	
	int getActionPower();
	
	public String getName();
}
