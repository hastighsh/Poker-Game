package PokerGame;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class implements poker game-related methods
 * @author Andriy
 *
 */
public class Poker {
	private Poker(){};

	/**
	 * Checks if a hand contains a Three of a kind: https://en.wikipedia.org/wiki/List_of_poker_hands#Three_of_a_kind
	 * @param hand
	 * @return true if the hand contains three cards of one rank and two cards of two other ranks 
	 * NOTE: Four of a kind is excluded, https://en.wikipedia.org/wiki/List_of_poker_hands#Four_of_a_kind; 
	 * so is Full House: three cards of one rank and two cards of another rank)
	 * 
	 */
	public static boolean hasThreeOfAKind(PokerHand hand){
		Map<CardValue, Integer> map = new HashMap<>();
		for (Card card : hand){
			if (map.containsKey(card.getCardValue())){
				map.put(card.getCardValue(), map.get(card.getCardValue()) + 1);
			}
			else map.put(card.getCardValue(), 1);
		}

		int numPairs = 0;
		for (CardValue key : map.keySet()){
			if (map.get(key) == 2) numPairs++;
		}
		if (numPairs > 0) return false;

		for (CardValue key : map.keySet()){
			if (map.get(key) == 3) return true;
		}
		return false;
	}

	/**
	 * Checks if a hand contains a Flush: https://en.wikipedia.org/wiki/List_of_poker_hands#Flush
	 * @param hand
	 * @return true if the hand contains five cards all of the same suit, not all of sequential rank
	 * NOTE: Straight flush https://en.wikipedia.org/wiki/List_of_poker_hands#Straight_flush  
	 * are to be excluded
	 */
	public static boolean hasFlush(PokerHand hand){
		Map<CardSuit, Integer> suitMap = new HashMap<>();
		for (Card card : hand){
			if (suitMap.containsKey(card.getSuit())){
				suitMap.put(card.getSuit(), suitMap.get(card.getSuit()) + 1);
			}
			else suitMap.put(card.getSuit(), 1);
		}

		for (CardSuit suit : suitMap.keySet()){
			if (suitMap.get(suit) == 5) { //5 cards of the same suit
				//are they sequential?
				Map<CardValue, Integer> valueMap = new TreeMap<>();
				for (Card card : hand){
					if (valueMap.containsKey(card.getCardValue())){
						valueMap.put(card.getCardValue(), valueMap.get(card.getCardValue()) + 1);
					}
					else valueMap.put(card.getCardValue(), 1);
				}
				Object [] valueArray = (valueMap.keySet().toArray());
				for (int i = 0; i < 4; i++) {
					if(((CardValue)valueArray[i + 1]).getCardValue()
							- ((CardValue)valueArray[i]).getCardValue() > 1) return true;
				}
			}
		}
		return false;
	}
}
