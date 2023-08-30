/**
 * BlackJack Project
 * File Name: Deck.java
 * This class contains the playing deck containing 52 cards
 */

package BlackJack;

import java.util.Random;
import java.util.ArrayList;
/**
 * This class contains pack of 52 playing cards
 * 
 * @author Lucas Jeong
 * @version 2023 August 21
 */
public class Deck 
{
	public ArrayList<Cards> cardD;
	
	/** 
	 * when a new deck is created, all the cards 
	 * are created in an arraylist in order.
	 */
	public Deck()
	{
		cardD = new ArrayList<Cards>(52);
			
		//0 - 12 clubs, 13-25 diamonds 26 - 38 hearts 39 - 51 spades
		
		for (int i = 0; i < 52; i++) 
		{
			String suit = Cards.SetSuit(i);
			int rank = Cards.SetRank(i);
			String handview = Cards.HandView(i);
			
			Cards card1 = new Cards(suit,rank,handview);
			cardD.add(card1);
		}
	}
	
	/**
	 * This method takes all the cards in a deck
	 * and shuffles them all about 200 times randomly.
	 * 
	 * how it works is it takes two random numbers (indexes)
	 * and then swaps them out.
	 */
	public void shuffleone()
	{	
		for(int i = 0; i < 400; i++)
		{
		    int random1 = randomnumgen();
		    int random2 = randomnumgen();
			
			Cards hold1 = cardD.remove(random1);
			Cards hold2 = cardD.remove(random2);
			
			cardD.add(random1, hold1);
			cardD.add(random2, hold2);
		}
	}
	
	public int randomnumgen()
	{
		Random rand = new Random();
		
		int upperbound = 51;
		
		return rand.nextInt(upperbound);
	}
	
	//for testing
	public void testingdeck()
	{
		for (int i = 0; i < 52; i++)
		{
			Cards card1 = cardD.get(i);
			System.out.println(card1.getrank()); 
		}
		
	}

	//draws raw value of card and removes it (check if remove properly.
	public Cards drawCard()
	{
		int sizer = cardD.size() - 1 ;
		
		Cards drawncard = (Cards) cardD.remove(sizer);
		// 19
		
		return drawncard;
	}
}