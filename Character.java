

class Character {
	private boolean isPlayer = false;
	private String firstName;
	private String lastName;
	private String title;
	private int level;
	private int[] stats;	// In order: strength, finesse, speed, cunning, vitality,
/*	int strength;
	int finesse;
	int speed;
	int cunning;
	int vitality;	*/
	private int health;	// Max health is 5 + vitality * 5
	private boolean alive;
	public boolean isStunned;
	private ActionOptionHand hand;
	
	public Action currentAction;
	
    public Character() {
		createCharacter();
	}
	
    public Character(String s) {
		createCharacter();
		lastName = s;
	}
	
    public Character(boolean p, String s) {
		createCharacter();
		isPlayer = p;
		lastName = s;
	}
	
	public Character(String fn, String s) {
		createCharacter();
		firstName = fn;
		lastName = s;
		title = null;
	}
	
	public Character(String fn, String ln, int lv) {
		createCharacter();
		firstName = fn;
		lastName = ln;
		title = null;
		
	}
	
	public Character(int lv) {
		createCharacter();
		title = null;
		for(int i = 0; i < lv; i++) {
			levelUp();
		}
	}
	
	public Character(int lv, String s) {
		createCharacter();
		title = null;
		for(int i = 0; i < lv; i++) {
			levelUp();
		}
		lastName = s;
	}
	
    private void createCharacter() {
		alive = true;
		
		//firstName = "Balthazar";
		lastName = "Condorbeak";
		
		generateName();
		
		title = null;
		
		level = 0;
		
		stats = new int[5];
		stats[0] = 1;	// Strength
		stats[1] = 1;	// Finesse
		stats[2] = 1;	// Speed
		stats[3] = 1;	// Cunning
		stats[4] = 1;	// Vitality
		
		for(int i = 0; i < 5; i++) {
			stats[(int) (Math.random() * 5)]++;
		}
		
		reset();
		
		currentAction = null;
    }
	
	public void printStats() {
		System.out.print("Name: " + firstName + " " + lastName);
		if (alive == false) {
			System.out.println("(DECEASED)");
		} else {
			System.out.println();
		}
		System.out.println("Level " + level);
		System.out.println("Strength: " + stats[0]);
		System.out.println("Finesse: " + stats[1]);
		System.out.println("Speed: " + stats[2]);
		System.out.println("Cunning: " + stats[3]);
		System.out.println("Vitality: " + stats[4]);
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public int maxHealth() {
		return 10 + 5 * stats[2];
	}
	
	public int Strength() {
		return Math.min(stats[0], 5);
	}
	
	public int Finesse() {
		return Math.min(stats[1], 5);
	}
	
	public int Speed() {
		return Math.min(stats[2], 5);
	}
	
	public int Cunning() {
		return Math.min(stats[3], 5);
	}
	
	public int Vitality() {
		return Math.min(stats[4], 5);
	}
	
	public int rollInitiative() {
		if(isStunned) {	// Note: it should be impossible for both characters to be stunned, but if this happens, then initiative will be re-rolled as normal
			isStunned = false;
			return 0;
		} else {
			return (int) (Math.random() * 2 * (stats[4] + 1) + 1);
		}
	}
	
	public void reset() {
		health = maxHealth();
		isStunned = false;
		hand = new ActionOptionHand(this);
	}
	
	public int getHealth() {
		return health;
	}
	
	public String getName() {
		if(title == null) {
			return firstName;
		} else {
			return (title + " " + firstName);
		}
	}
	
	public String getFullName() {
		if(title == null) {
			return firstName + " " + lastName;
		} else {
			return title + " " + firstName + " " + lastName;
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public void damage(int x) {
		if(x > 0) {
			health -= x;
			System.out.println(firstName + " takes " + x + " damage!");
		}
		if(health <= 0) {
			health = 0;
			alive = false;
			System.out.println(firstName + " has been struck down!");
		}
	}
	
	public void levelUp() {
		level++;
		stats[(int) (Math.random() * 5)]++;
		if(isPlayer) {
			System.out.println(firstName + " has gained a level!");
			printStats();
		}
	}
	
	public void defenseEffect(Action defense) {
		System.out.println(firstName + " deals no damage!");
		if(defense.getName().equals("Block")) {
			isStunned = true;
			System.out.println(firstName + " is stunned!");
		}else if(defense.getName().equals("Parry")) {
			isStunned = true;
			System.out.println(firstName + " is stunned!");
		}
	}
	
	private void generateName() {
		String[] firstNameList = {"Arron", "Brienne", "Cedric", "Dunban", "Emmett", "Franklin", "Garth", "Hodor", "Isabel", "Jon", "Kyle", "Luke", "Margaery", "Norett", "Otto",
			"Podrick", "Quillion", "Rickard", "Shulk", "Tibalt", "Ulfric", "Vasgord", "Will", "Xaro", "Zanzibar"};
		firstName = firstNameList[(int) (Math.random() * firstNameList.length)];
		
		String[] surnameList = {"Archer", "Blackadder", "Carter", "Dendric", "Edwinson", "Farren", "Gallant", "Hill", "Ironforger", "Jordan", "Kirk", "Lancaster", "Mormont",
			"Norett", "Osworth", "Pim", "Quort", "Rickardson", "Scalderfish", "Tooth", "Umber", "Varlo", "Wolf", "Xendas", "Zurt"};
		lastName = surnameList[(int) (Math.random() * surnameList.length)];
	}
	
	public Action getAction(boolean hasInitiative) {
		if(isPlayer) {
			currentAction = hand.chooseAction();
		} else {
			currentAction = hand.chooseActionAI(hasInitiative);
		}
		return currentAction;
	}
	
	public void draw() {
		hand.drawCard();
	}
}
