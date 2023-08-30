/**
 * BlackJack Project
 * File Name: Cards.java
 * 
 * This class contains and creates the data necessary
 * for objects of a card deck to materialize. 
 */

package BlackJack;

/**
 * This class contains the methods and data necessary
 * to materialize cards in a deck. 
 * 
 * @author Lucas Jeong
 * @version 2023 August 21
 */
public class Cards 
{
	/*
	 * The rank is the value of the card
	 */
	int rank;
	
	/*
	 * The handview is how the card is "received"
	 * and interpreted by the player.
	 */
	String handview2;
	
	/*
	 * The suit is the suite (Hearts, Spades, etc.) of the given card
	 */
	String suite;
	
	/**
	 * This constructor must be passed processed values
	 * in order to make a card without causing any errors.
	 * 
	 * @param suite is the suit of the card (hearts, spades, etc.)
	 * 
	 * @param rank is the integer "value" of the card which should be processed
	 * before initializing its value.
	 * 
	 * @param handview2 is a String representing how the card will be
	 * "written" out to the player.
	 * 
	 * @return a Card object.
	 */
	public Cards(String suite, int rank, String handview2) 
	{
		this.suite = suite;
		this.rank = rank;
		this.handview2 = handview2;
	}
	
	/**
	 * This method takes an integer value of the card and then 
	 * returns its respective "actual value"
	 * 
	 * @param rawrank is the unprocessed integer value attached to the
	 * card.
	 * 
	 *  @return returns an integer value of the card.
	 */
	public static int SetRank(int rawrank)
	{
		int tester = rawrank%13 + 1;
		
		if (tester > 10) {
			tester = 10;
		}
		return tester;
	}
	
	/**
	 * This method returns the name/value of a given card.
	 * 
	 * @param rawval is the unprocessed value of the card
	 * 
	 */
	public static String HandView(int rawval)
	{
		int checker2 = rawval%13 + 1;
		
		switch(checker2)
		{
		case 1:
			return "ACE";
		case 2:
			return "TWO";
		case 3:
			return "THREE";
		case 4:
			return "FOUR";
		case 5:
			return "FIVE";
		case 6:
			return "SIX";
		case 7:
			return "SEVEN";
		case 8:
			return "EIGHT";
		case 9:
			return "NINE";
		case 10:
			return "TEN";
		case 11:
			return "JACK";
		case 12:
			return "KING";
		case 13:
			return "QUEEN";
		default:
			return null;
		}
	}
	
	/**
	 * This method takes in the integer value and assigns it a 
	 * suit based on the value/set it is in.
	 * 
	 * @param suite is the unprocessed integer value
	 */
	public static String SetSuit(int suite)
	{
		int checker = (int)suite/13 + 1;
		
		switch(checker)
		{
		case 1:
			return "Clubs";
		case 2:
			return "Diamonds";
		case 3:
			return "Hearts";
		case 4:
			return "Spades";
		default:
			return null;
		}
	}
	
	/**
	 * Sets the value of an ace card to 
	 * the given integer parameter value.
	 * 
	 * @param rank is the integer rank to be set to 
	 */
	public void acesetrank(int rank)
	{
		this.rank = rank;
	}
	
	/**
	 * getter method for rank
	 * 
	 * @return integer value of the rank.
	 */
	public int getrank()
	{
		return this.rank;
	}
	
	/**
	 * getter method for the suit
	 * 
	 * @return the String name of the suite 
	 */
	public String getsuite()
	{
		return this.suite;
	}
	
	/**
	 * getter method for the hand (from player's point of view).
	 * 
	 * @return the hand
	 */
	public String gethandview() 
	{
		return this.handview2;
	}
	
	/**
	 * equals method overriden to find whether two cards are
	 * identical
	 * 
	 * @param obj is the card object
	 * 
	 * @return a boolean true if the cards are identical and false
	 * if the cards are not identical.
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (this == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		
		Cards card1 = (Cards) obj;
		
		return this.getsuite().equals(card1.getsuite())
				&& this.gethandview().equals(card1.gethandview());
	}
}