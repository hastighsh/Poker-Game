package PokerGame;

public class CardDemo {
	private static final int ITERATIONS = 100_000;
	public static void main(String[] args) {
		CardDeck deck = new CardDeck();
		deck.shuffle();
		//System.out.println(deck);
		
		System.out.println("Drawing one card: " + deck.drawCard());
		System.out.println("Deck size before drawing 5: " + deck.size());
		PokerHand hand = new PokerHand(deck.draw5Cards());
		System.out.println("Deck size  after drawing 5: " + deck.size());
		System.out.println(hand);
		
		System.out.println("Has a pair: " + Poker.hasThreeOfAKind(hand));
		
		System.out.println();
		
		//System.out.println("Searching for Three of a kind");
		int threeOfKindFound = 0;
		for (int i = 0; i < ITERATIONS; i++){
			deck = new CardDeck();
			deck.shuffle();
			hand = new PokerHand(deck.draw5Cards());
			if (Poker.hasThreeOfAKind(hand)){
				threeOfKindFound++;
				//System.out.println("Three of a kind found: " + hand);
			}
		}
		System.out.println("Total Three of a kind found: " + threeOfKindFound);

		//System.out.println("Searching for flushes");
		int flushesFound = 0;
		for (int i = 0; i < ITERATIONS; i++){
			deck = new CardDeck();
			deck.shuffle();
			hand = new PokerHand(deck.draw5Cards());
			if (Poker.hasFlush(hand)){
				flushesFound++;
				//System.out.println("Flush found: " + hand);
			}
		}
		System.out.println("Flushes found: " + flushesFound);
}
}
