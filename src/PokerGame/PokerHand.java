package PokerGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Implements a poker hand containing exactly 5 distinct cards 
 * @author Andriy
 *
 */
public class PokerHand implements Iterable <Card>{
	private ArrayList <Card> hand;
	public final int HAND_SIZE = 5; 

	/**
	 * Creates a new hand from a collection of 5 cards
	 * the hand must contain exactly 5 cards, and they must be distinct
	 * @param hand
	 * @throws IllegalArgumentException if the conditions above a violated
	 */
	public PokerHand(Collection<Card> hand) {
		//check for size
		if (hand.size() != 5){
			throw new IllegalArgumentException();
		}

		//check for duplicates
		Set <Card> set = new HashSet<>(hand);
		if (set.size() != 5){
			throw new IllegalArgumentException();
		}
		//System.out.println(set);

		this.hand = new ArrayList<> (hand);
	}

	/**
	 * Creates a new hand from 5 separate card objects
	 * there should be exactly 5 parameters, and they must be distinct
	 * @param hand 5 card objects
	 * @throws IllegalArgumentException if the conditions above a violated
	 */
	public PokerHand(Card... hand) {
		this(Arrays.asList(hand));
	}

	/**
	 * @return a list of cards currently held
	 */
	@SuppressWarnings("unchecked")
	public List <Card> getHand (){
		return (List <Card>)(this.hand.clone());
	}

	@Override
	public String toString (){
		return hand.toString();
	}

	@Override
	public Iterator <Card> iterator() {
		return hand.iterator();
	}

}
