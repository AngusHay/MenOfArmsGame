import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

public class ActionOptionDeck {
	private LinkedList<ActionOption> deckList = null;
	private LinkedList<ActionOption> discardPile = null;
	private Random rand;
	Character charac;
	private int handSize;	// Deck size is 10
	
	public ActionOptionDeck(Character c) {
		charac = c;
		deckList = new LinkedList<ActionOption>();
		discardPile = new LinkedList<ActionOption>();
		rand = new Random();
		handSize = Math.min(2 + charac.Cunning(), 7);
		for(int i = 0; i < 2; i++) {
			deckList.add(new ActionOption(new Slash(charac)));
			deckList.add(new ActionOption(new Chop(charac)));
			deckList.add(new ActionOption(new Thrust(charac)));
			deckList.add(new ActionOption(new Block(charac)));
			deckList.add(new ActionOption(new Parry(charac)));
		}
	}
	
	public void discard(ActionOption newAction) {
		discardPile.add(newAction);
	}
	
	public void reshuffle() {	// Used when deck is out of cards. Returns the discard pile to the deck.
		System.out.println(charac.getName() + " deck reshuffled");
		if(deckList.isEmpty()) {
			for(ActionOption a : discardPile) {
				deckList.add(a);
			}
			discardPile.clear();
		} else {
			System.out.println("Error: Tried to refill a deck that wasn't empty");
		}
	}
	
/*	public void printDiscardHead() {
		if(discardPileHead == null) {
			System.out.println("Problem");
		} else {
			System.out.println("+++" + discardPileHead.getStoredAction().getName() + "+++");
		}
	}*/
	
    public ActionOption removeRandomCard() {
		if(deckList.isEmpty()) {
			reshuffle();
		}
		
		int r = rand.nextInt(this.deckSize());
		
		return this.removeByIndex(r);
	}
	
	public ActionOption removeByIndex(int index) {
		return deckList.remove(index);
	}
	
	public void printAll() {
		System.out.println("Deck: ");
		
		for(ActionOption a : deckList) {
			System.out.println(a.getStoredAction().getName());
		}
		
		System.out.println();
		System.out.println("Discard pile: ");
		
		for(ActionOption a : discardPile) {
			System.out.println(a.getStoredAction().getName());
		}
		
		System.out.println();
	}
	
	private int deckSize() {
		return deckList.size();
	}
	
	public boolean isEmpty() {
		return (deckList.isEmpty());
	}
}
