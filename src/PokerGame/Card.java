package PokerGame;

/**
 * This class implements a playing card with one of 52 possible values.
 * @author Andriy
 */
public class Card {
	private CardValue value;
	private CardSuit suit;

	/**
	 * Creates a new card with the specified Value (Rank) and Suit
	 * @param cardValue rank
	 * @param suit card suit
	 */
	public Card (CardValue cardValue, CardSuit suit)
	{
		this.value = cardValue;
		this.suit = suit;
	}

	/**
	 * @return card value (rank)
	 */
	public CardValue getCardValue()
	{
		return value;
	}

	/**
	 * @return card suit
	 */
	public CardSuit getSuit()
	{
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit != other.suit)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString(){
		return this.value + " " + this.suit;
	}
}
