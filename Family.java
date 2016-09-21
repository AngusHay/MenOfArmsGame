

class Family {
	private Character currentCharacter;
	private CharacterListNode head;
	private boolean isPlayerFamily = false;
	private String familyName;
	
	public Action currentAction;
	
    public Family() {
		generateFamilyName();
		isPlayerFamily = false;
		currentCharacter = new Character(isPlayerFamily, familyName);
		head = null;
	}
	
    public Family(boolean b) {
		generateFamilyName();
		isPlayerFamily = b;
		currentCharacter = new Character(isPlayerFamily, familyName);
		head = null;
	}
	
    public Family(boolean b, String n) {
		isPlayerFamily = b;
		currentCharacter = new Character(isPlayerFamily, familyName);
		head = null;
		familyName = n;
	}
	
	public Character getCurrentCharacter() {
		return currentCharacter;
	}
	
	public void newCharacter() {
		CharacterListNode newNode = new CharacterListNode(currentCharacter, head);
		currentCharacter = new Character(isPlayerFamily, familyName);
	}
	
	private void generateFamilyName() {
		String[] surnameList = {"Archer", "Blackadder", "Carter", "Dendric", "Edwinson", "Farren", "Gallant", "Hill", "Ironforger", "Jordan", "Kirk", "Lancaster", "Mormont",
			"Norett", "Osworth", "Pim", "Quort", "Rickardson", "Scalderfish", "Tooth", "Umber", "Varlo", "Wolf", "Xendas", "Zurt"};
		familyName = surnameList[(int) (Math.random() * surnameList.length)];
	}
}
