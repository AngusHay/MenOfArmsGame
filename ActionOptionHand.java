import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class ActionOptionHand {
	private LinkedList<ActionOption> handList = null;
	private Random rand;
	private int maxOptions;
	Character charac;
	private ActionOptionDeck deck;
	
	public ActionOptionHand(Character c) {
		handList = new LinkedList<ActionOption>();
		charac = c;
		deck = new ActionOptionDeck(charac);
		rand = new Random();
		drawNewHand();
	}
	
	public void printAllDeck() {
		deck.printAll();
	}
	
	public void drawNewHand() {
		maxOptions = 2 + charac.Cunning();
		for(int i = 0; i < maxOptions; i++) {
			drawCard();
		}
	}
	
	public void drawCard() {
		handList.add(deck.removeRandomCard());
	}
	
    /*public Action removeHead() {
		if(head != null) {
			ActionOption result = head;
			if(head.next != null) {
				head = head.next;
			} else {	
				head = null;
			}
			deck.discard(result);
			return result.getStoredAction();
		} else {
			System.out.println("Out of cards");
			return null;
		}
	}*/
	
	public Action removeByIndex(int index) {
		if(handList.isEmpty()) {
			System.out.println("Error");
			return null;
		} else {
			return handList.remove(index).getStoredAction();
		}
	}
	
	public void printOptions() {
		if(handList.isEmpty()) {
			System.out.println("No actions");
		} else {
			for(ActionOption a : handList) {
				System.out.println(a.getStoredAction().getName());
			}
		}
	}
	
	public Action chooseAction() {
		Scanner input = new Scanner(System.in);
		if(handList.isEmpty()) {
			drawNewHand();
		}
		if(!handList.isEmpty()) {
			System.out.println("Select action:");
			int i = 0;
			for(ActionOption a : handList) {
				i++;
				System.out.println(i + ": " + a.getStoredAction().getName());
			}
			while(true) {
				int choice = -1;
				try {
					choice = input.nextInt();
				} catch (InputMismatchException e) {
					input.next();
					System.out.println("Error: Invalid input. Input new choice");
				}
				if(choice > i || choice < 0) {
					System.out.println("Not a valid choice. Input new choice");
				} else {
					ActionOption result = handList.remove(choice-1);
					System.out.println(result.getStoredAction().getName());
					result.getStoredAction().reroll(charac);
					deck.discard(result);
					return result.getStoredAction();
				}
			}
		} else {
			System.out.println("Error: No hand (player)");
		}
		return null;
	}
	
	public Action chooseActionAI(boolean hasInitiative) {
		if(handList.isEmpty()) {
			drawNewHand();
		}
		if(!handList.isEmpty()) {
			int i = 0;
			ActionOption result = null;
			printOptions();
			for(ActionOption a : handList) {
				if(hasInitiative && a.getStoredAction().isAttack()) {
					result = a;
					break;
				}
				if(!hasInitiative && !a.getStoredAction().isAttack()) {
					result = a;
					break;
				}
				if(i == handList.size() - 1) {
					result = a;
					break;
				} else {
					i++;
				}
			}
			handList.remove(result);
			result.getStoredAction().reroll(charac);
			deck.discard(result);
			return result.getStoredAction();
		} else {
			System.out.println("Error: No hand (AI)");
		}
		return null;
	}
	
	public boolean isEmpty() {
		return (handList.isEmpty());
	}
}
