package PokerGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/** 
 * Implements a deck of 52 cards, e.g., for a poker game
 * @author Andriy
 *
 */
public class CardDeck implements Iterable <Card>{
	private List<Card> deck;

	/**
	 * Creates a new deck of cards. The deck will contain 52 distinct cards.
	 * the deck may or may not be shuffled
	 */
	public CardDeck ()
	{
		this.deck = new ArrayList<>();
		for (int i = 0; i < CardSuit.values().length; i++)
			for (int j=0; j < CardValue.values().length; j++)
			{
				Card card = new Card(CardValue.values()[j], CardSuit.values()[i]);
				this.deck.add(card);
			}
	}

	/**
	 * Shuffles the deck
	 */
	public void shuffle(){
		Collections.shuffle(deck);
	}

	/**
	 * returns a collection of 5 cards taken from the top of deck.
	 * After the operation those 5 cards should be removed from the deck
	 * and its size is decreased by 5.
	 * @return a collection of 5 cards taken from the top of deck
	 */
	public Collection<Card> draw5Cards (){
		Collection<Card> result = new ArrayList<Card> (this.deck.subList(this.deck.size() - 5, this.deck.size()));
		this.deck.removeAll(result);
		return result;
	}

	/**
	 * After the operation this card should be removed from the deck
	 * and its size is decreased by 1.
	 * @return a card from the top of the deck
	 */
	public Card drawCard (){
		Card card = this.deck.remove(this.deck.size() - 1);
		return card;
	}

	/**
	 * @return current deck size, a value between 0 and 52 inclusive
	 */
	public int size(){
		return this.deck.size();
	}

	@Override
	public String toString (){
		return deck.toString();
	}

	@Override
	public Iterator <Card> iterator() {
		return deck.iterator();
	}


}
