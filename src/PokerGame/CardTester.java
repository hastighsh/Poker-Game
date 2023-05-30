package PokerGame;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTester {

	@Test
	public void testCardsPrivateFields() {
		final Field[] fields = Card.class.getDeclaredFields();
		for (Field field : fields) {
			assertTrue("Card contains a non-private field", Modifier.isPrivate(field.getModifiers()));
		}
	}

	@Test
	public void testCardsConstructors() {
		final Constructor<?>[] constructors = Card.class.getDeclaredConstructors();
		assertTrue("Card contains too many constructors", constructors.length == 1);

		@SuppressWarnings("unused")
		Card card = new Card(CardValue.ACE, CardSuit.SPADES);
	}

	@Test
	public void testCardsMethods() {
		Card card = new Card(CardValue.ACE, CardSuit.SPADES);
		assertTrue("Card getCardValue failed", card.getCardValue().toString().equals("ACE"));
		assertTrue("Card getSuit failed", card.getSuit().toString().equals("SPADES"));
		assertTrue("Card toString failed", card.toString().contains("ACE") && card.toString().contains("SPADES"));
	}

	@Test
	public void testCardDeckFieldsConstructors() {
		final Field[] fields = CardDeck.class.getDeclaredFields();
		for (Field field : fields) {
			assertTrue("CardDeck contains a non-private reference field or a non-final primitive field", 
					Modifier.isPrivate(field.getModifiers())|| 
					(Modifier.isFinal(field.getModifiers()) && field.getType().isPrimitive()));
		}

		final Constructor<?>[] constructors = CardDeck.class.getDeclaredConstructors();
		assertTrue("CardDeck contains too many constructors", constructors.length == 1);

		CardDeck deck= new CardDeck();
		assertTrue("CardDeck: new deck size is not 52", deck.size() == 52);
	}


	@Test
	public void testCardDeckMethods1_draw5Cards() {
		CardDeck deck= new CardDeck();
		assertTrue("CardDeck: new deck size is not 52", deck.size() == 52);
		Collection <Card> cards = deck.draw5Cards();
		assertTrue("CardDeck: deck size after drawing 5 cards is not 47", deck.size() == 47);
		assertTrue("CardDeck: hand of 5 cards is not 5", cards.size() == 5);
		HashSet <Card> hs = new HashSet<>(cards);
		assertTrue("CardDeck: hand of 5 cards contains duplicates", hs.size() == 5);
	}

	@Test
	public void testCardDeckMethods2_drawCard() {
		Card card1, card2;
		CardDeck deck = new CardDeck();
		card1 = deck.drawCard();

		deck = new CardDeck();
		deck.shuffle();
		card2 = deck.drawCard();
		assertTrue("CardDeck: deck size after drawing 1 card is not 51", deck.size() == 51);
		assertTrue("CardDeck: two random cards are the same", 
				card1.getCardValue() != card2.getCardValue() || card1.getSuit() != card2.getSuit());
	}


	@Test
	public void testPokerHandConstructor1() {
		final Field[] fields = PokerHand.class.getDeclaredFields();
		for (Field field : fields) {
			assertTrue("PokerHand contains a non-private reference field or a non-final primitive field", 
					Modifier.isPrivate(field.getModifiers()) || 
					(Modifier.isFinal(field.getModifiers()) && field.getType().isPrimitive()));
		}

		final Constructor<?>[] constructors = PokerHand.class.getDeclaredConstructors();
		assertTrue("PokerHand contains a wrong number of constructors", constructors.length == 2);
	}

	@Test
	public void testPokerHandConstructor2() {
		CardDeck deck = new CardDeck();
		Collection <Card> fiveCards = deck.draw5Cards();
		PokerHand hand = new PokerHand(fiveCards);
		assertTrue("PokerHand contains a wrong number of cards", hand.getHand().size() == 5);

		fiveCards = new ArrayList<Card>();
		Card card1 = new Card (CardValue.ACE, CardSuit.SPADES);
		Card card2 = new Card (CardValue.ACE, CardSuit.DIAMONDS);
		Card card3 = new Card (CardValue.ACE, CardSuit.HEARTS);
		Card card4 = new Card (CardValue.ACE, CardSuit.CLUBS);
		Card card5 = new Card (CardValue.ACE, CardSuit.SPADES);
		fiveCards.add(card1);
		fiveCards.add(card2);
		fiveCards.add(card3);
		fiveCards.add(card4);
		fiveCards.add(card5);

		try {
			hand = new PokerHand(fiveCards);
			fail("PokerHand failed to throw an exception1 (duplicates)");
		} catch (IllegalArgumentException x) {
			// ok
		} catch (Exception x) {
			fail("PokerHand: wrong type of exception thrown");
		}

		try {
			hand = new PokerHand(card1, card2, card3, card4, card5);
			fail("PokerHand failed to throw an exception2 (duplicates)");
		} catch (IllegalArgumentException x) {
			// ok
		} catch (Exception x) {
			fail("PokerHand: wrong type of exception thrown");
		}

		try {
			hand = new PokerHand(card1, card2, card3, card4, card5, card1);
			fail("PokerHand failed to throw an exception3 (6 cards)");
		} catch (IllegalArgumentException x) {
			// ok
		} catch (Exception x) {
			fail("PokerHand: wrong type of exception thrown");
		}

		try {
			hand = new PokerHand(card1, card2, card3, card4);
			fail("PokerHand failed to throw an exception4 (4 cards)");
		} catch (IllegalArgumentException x) {
			// ok
		} catch (Exception x) {
			fail("PokerHand: wrong type of exception thrown");
		}
	}


	@Test
	public void testPokerHandGetHand() {
		CardDeck deck = new CardDeck();
		Collection <Card> fiveCards = deck.draw5Cards();
		PokerHand hand = new PokerHand(fiveCards);

		assertTrue("PokerHand getHand: wrong size", hand.getHand().size() == 5);
		assertTrue("PokerHand getHand: no defensive copies", hand.getHand() != hand.getHand());
	}

	@Test
	public void testPoker3OfKind1() {
		Card card1 = new Card (CardValue.ACE, CardSuit.SPADES);
		Card card2 = new Card (CardValue.TEN, CardSuit.DIAMONDS);
		Card card3 = new Card (CardValue.JACK, CardSuit.HEARTS);
		Card card4 = new Card (CardValue.JACK, CardSuit.CLUBS);
		Card card5 = new Card (CardValue.JACK, CardSuit.SPADES);
		PokerHand hand = new PokerHand(card1, card2, card3, card4, card5);
		assertTrue("Failed to find three-of-a-kind", Poker.hasThreeOfAKind(hand));
	}

	@Test
	public void testPoker3OfKind2() {
		Card card1 = new Card (CardValue.ACE, CardSuit.SPADES);
		Card card2 = new Card (CardValue.JACK, CardSuit.DIAMONDS);
		Card card3 = new Card (CardValue.JACK, CardSuit.HEARTS);
		Card card4 = new Card (CardValue.JACK, CardSuit.CLUBS);
		Card card5 = new Card (CardValue.JACK, CardSuit.SPADES);
		PokerHand hand = new PokerHand(card1, card2, card3, card4, card5);
		assertTrue("Three-of-a-kind includes quads", !Poker.hasThreeOfAKind(hand));
	}

	@Test
	public void testPoker3OfKind3() {
		Card card1 = new Card (CardValue.ACE, CardSuit.SPADES);
		Card card2 = new Card (CardValue.ACE, CardSuit.DIAMONDS);
		Card card3 = new Card (CardValue.JACK, CardSuit.HEARTS);
		Card card4 = new Card (CardValue.JACK, CardSuit.CLUBS);
		Card card5 = new Card (CardValue.JACK, CardSuit.SPADES);
		PokerHand hand = new PokerHand(card1, card2, card3, card4, card5);
		assertTrue("Three-of-a-kind includes full house", !Poker.hasThreeOfAKind(hand));
	}

	@Test
	public void testPokerFlush1() {
		Card card1 = new Card (CardValue.ACE, CardSuit.SPADES);
		Card card2 = new Card (CardValue.QUEEN, CardSuit.SPADES);
		Card card3 = new Card (CardValue.JACK, CardSuit.SPADES);
		Card card4 = new Card (CardValue.TEN, CardSuit.SPADES);
		Card card5 = new Card (CardValue.NINE, CardSuit.SPADES);
		PokerHand hand = new PokerHand(card1, card2, card3, card4, card5);
		assertTrue("Failed to find flush", Poker.hasFlush(hand));
	}

	@Test
	public void testPokerFlush2() {
		Card card1 = new Card (CardValue.ACE, CardSuit.SPADES);
		Card card5 = new Card (CardValue.KING, CardSuit.SPADES);
		Card card2 = new Card (CardValue.QUEEN, CardSuit.SPADES);
		Card card3 = new Card (CardValue.JACK, CardSuit.SPADES);
		Card card4 = new Card (CardValue.TEN, CardSuit.SPADES);
		PokerHand hand = new PokerHand(card1, card2, card3, card4, card5);
		assertTrue("Flush includes a Straight Flush", !Poker.hasFlush(hand));
	}

	@SuppressWarnings("unused")
	@Test
	public void testIterators() {
		CardDeck deck = new CardDeck();
		Collection <Card> fiveCards = deck.draw5Cards();
		PokerHand hand = new PokerHand(fiveCards);

		Iterator<Card> iterator = deck.iterator();
		iterator = hand.iterator();
	}
}