import java.util.Scanner;

public class MenOfArms {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);	// Create a scanner to read input
		
		Family playerFamily = new Family(true);	// Create the player's family, including a starting character
		
		Character pc = playerFamily.getCurrentCharacter();	// Get a reference the player's starting character
		pc.printStats();	// Print out the pc's stats
		
		boolean gameRunning = true;	// This changes to false when the player decides to quite
		
		while(gameRunning) {
			duel(pc, selectOpponent(input, pc.getLevel()), input);	// Run a duel between the player and an opponent (selected from a list of three randomly generated opponents)
			if(!playerFamily.getCurrentCharacter().isAlive()) {	// If the pc died, give the player an option to either start a new character or quit the game
				System.out.println("Continue playing? y/n");
				while(true) {	// Repeat until a valid choice is made
					int choice = input.next().charAt(0);
					if(choice == 'y') {
						break;	// Stop the loop and continue playing
					} else if(choice == 'n') {
						gameRunning = false;	// End the game
					} else {
						System.out.println("Invalid input. Try again");	// Prompt the user for a valid input
					}
				}
			
				playerFamily.newCharacter();	// Generate a new character to replace the dead one
				pc = playerFamily.getCurrentCharacter();	// Get a reference to the new character
				pc.printStats();
			}
		}
	}
	
	private static Character selectOpponent(Scanner input, int level) {
		Character opponent1 = new Character(level);	// Randomly generate three characters of the given level
		Character opponent2 = new Character(level);
		Character opponent3 = new Character(level);
		
		System.out.println("Select opponent to challenge");	// Let the player choose which opponent to face
		System.out.println("Input number and press enter:");
		System.out.println("1: " + opponent1.getFullName() + " - Level " + opponent1.getLevel());
		System.out.println("2: " + opponent2.getFullName() + " - Level " + opponent2.getLevel());
		System.out.println("3: " + opponent3.getFullName() + " - Level " + opponent3.getLevel());
		
		while(true) {	// Repeat until the player makes a valid choice
			int choice = input.nextInt();
			if(choice == 1) {
				return opponent1;
			} else if(choice == 2) {
				return opponent2;
			} else if(choice == 3) {
				return opponent3;
			} else {
				System.out.println("Invalid input. Try again");
			}
		}
	}
	
	private static void actionResult(Character initChar, Character nonInitChar) {
		// initChar = character with initiative
		// nonInitChar = character who doesn't have initiative
		
		if(initChar.currentAction.isAttack() && nonInitChar.currentAction.isAttack()) {	// Both players attacked
			// The character with initiative gets to attack; the other character's action is ignored
			System.out.println(initChar.getName() + " interrupts " + nonInitChar.getName() + "'s attack and strikes back!");
			int d = initChar.currentAction.getActionPower();
			nonInitChar.damage(d);
		}else if(initChar.currentAction.isAttack() && !nonInitChar.currentAction.isAttack()) {	// The player with initiative attacked and the other defended
			// The attacker rolls for damage and the defender rolls for defense. If attacker rolls higher, some damage goes through. Otherwise, the attacker has a negative effect applied to them
			System.out.println(nonInitChar.getName() + " defends against " + initChar.getName() + "'s attack!");
			int d = initChar.currentAction.getActionPower() - nonInitChar.currentAction.getActionPower();
			if(d > 0) {
				nonInitChar.damage(d);
			} else {
				initChar.defenseEffect(nonInitChar.currentAction);
			}
		}else if(!initChar.currentAction.isAttack() && nonInitChar.currentAction.isAttack()) {	// The player with initiative defender and the other attacked
			// The attacker rolls for damage and the defender rolls for defense. If attacker rolls higher, some damage goes through. Otherwise, the attacker has a negative effect applied to them
			System.out.println(initChar.getName() + " defends against " + nonInitChar.getName() + "'s attack!");
			int d = nonInitChar.currentAction.getActionPower() - initChar.currentAction.getActionPower();
			if(d > 0) {
				initChar.damage(d);
			} else {
				nonInitChar.defenseEffect(initChar.currentAction);
			}
		}else{	// Both players defended	
			// Each player draws a new card to replace the one they used
			System.out.println("Both fighters bide their time");
			initChar.draw();
			nonInitChar.draw();
		}
		
	}
	
	private static void duel(Character player, Character opponent, Scanner input) {
		
		opponent.printStats();
		
		player.reset();
		opponent.reset();
		
		ActionOptionDeck bleh = new ActionOptionDeck(player);	// So that Java remembers to freakin' compile ActionOptionDeck.java, seriously Java what is wrong with you
		
		while(player.isAlive() && opponent.isAlive()) {
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				
			}
			System.out.println("--- --- ---");
			System.out.println("A new round begins!");
			System.out.println("You have " + player.getHealth() + " health.");
			int playerInitiative = player.rollInitiative();
			int opponentInitiative = opponent.rollInitiative();
			
			while(playerInitiative == opponentInitiative) {
				playerInitiative = player.rollInitiative();
				opponentInitiative = opponent.rollInitiative();
			}
			
			boolean opponentHasInitiative;
			
			if(playerInitiative < opponentInitiative) {
				opponentHasInitiative = true;
				System.out.println("Your opponent has the initiative!");
			} else {
				opponentHasInitiative = false;
				System.out.println("You have the initiative!");
			}
			
			opponent.getAction(opponentHasInitiative);
			player.getAction(opponentHasInitiative);
			
			if(playerInitiative < opponentInitiative) {
				actionResult(opponent, player);
			} else {
				actionResult(player, opponent);
			}
			
			if(!player.isAlive()) {
				System.out.println("You have died.");
				opponent.levelUp();
			}
			
			if(!opponent.isAlive()) {
				System.out.println("You are victorious!");
				player.levelUp();
			}
		
			/*
			if(player.currentAction.isAttack()) {
				opponent.damage(player.currentAction.getActionPower());
			}
			if(opponent.currentAction.isAttack()) {
				player.damage(opponent.currentAction.getActionPower());
			}
			*/
			
			
			
		}
	}
	
	
}
