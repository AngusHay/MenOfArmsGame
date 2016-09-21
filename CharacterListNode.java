
public class CharacterListNode {
	public Character storedCharacter;
	public CharacterListNode next;
	
	public CharacterListNode() {
		storedCharacter = null;
		next = null;
	}
	
	public CharacterListNode(Character c) {
		storedCharacter = c;
		next = null;
	}
	
	public CharacterListNode(Character c, CharacterListNode n) {
		storedCharacter = c;
		next = n;
	}
}
