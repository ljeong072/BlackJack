/**
 * BlackJack Project
 * File Name: Hand.java
 * 
 * This class contains the hand of the user
 */

package BlackJack;

import java.util.ArrayList;

/**
 * This class contains hand and hand methods for the user 
 * (choices for user to select).
 * 
 * @author Lucas Jeong
 * @version 2023 August 21
 */
public class Hand 
{
	private Cards cards;
	private Deck deck;
	public ArrayList<Cards> userD;
	public ArrayList<Cards> userDS;
	
	public Hand(Deck deck)
	{	
		userD = new ArrayList<Cards>(2);
		userDS = new ArrayList<Cards>(0);
		this.deck = deck;
	}
	
	/** 
	 * This method checks whether the user
	 * is able to split.
	 *
	 * @return true if the user can split or false if the user
	 * cannot split.
	 */
	public boolean user2split()
	{
		Cards c1 = userD.get(0);
		Cards c2 = userD.get(1);
		
		if (c1.equals(c2)){
			return true;
		}else {
			return false;
		}
	}
	
	/** 
	 * Changes the value of the ace accordingly.
	 */
	public void acechecker()
	{	
		for (int i = 0; i < userD.size(); i++)
		{
			Cards card1 = userD.get(i);
		
			if (card1.gethandview().equals("ACE"))
			{
				card1.acesetrank(11);
			}
		}
		
		for (int l = 0; l < userD.size(); l++)
		{
			Cards card2 = userD.get(l);
			
			if (processval() > 21 && card2.gethandview().equals("ACE"))
			{
				card2.SetRank(1);
			}
		}
	}
	
	/**
	 * Reads the hand of the player
	 */
	public void handreader()
	{
		int sizer = userD.size();
				
		System.out.println("Your cards are: ");
				
		for (int i = 0; i < sizer; i++)
		{
			Cards card = userD.get(i);
					
			System.out.print(card.gethandview() + " of " + 
			card.getsuite());
					
			if (i < sizer - 1)
			{
				System.out.print(" and ");
			}
		}
	}

	/**
	 * removes a card from the player's hand.
	 * @param remove the card in the given integer position.
	 */
	public void remove(int i)
	{
		Cards voidcard = userD.remove(i);
		
		deck.cardD.add(voidcard);
	}
	
	/**
	 * This card removes a card from the player's hand
	 * and puts that card in the split deck. This second
	 * remove method prevents duplicate cards if the user
	 * chooses to split his hand.
	 */
	public void transfersplit()
	{
		Cards voidcard = userD.get(1);
		
		userDS.add(voidcard);
		
		userD.remove(1);
	}
	public void hit()
	{
		Cards drawncard = deck.drawCard();
			
		userD.add(drawncard);
	}
		
	public boolean stand()
	{
		return false;
	}
	
	public int processval()
	{
		int a = 0; 
		
		for (int i = 0; i < userD.size(); i++)
		{
			Cards card1 = userD.get(i);
				
			a += card1.rank;
		}
		return a;
	}
	
	public void resethand()
	{
		for (int i = userD.size() - 1; i >= 0; i--)
		{
			Cards voidcard = userD.remove(i);
			
			deck.cardD.add(voidcard);
		}
		
	}

	//from here on initialize the Split methods
		
	public void handreaderS()
	{
		int sizer = userDS.size();
			
		System.out.println("Your cards are: ");
			
		for (int i = 0; i < sizer; i++)
		{
			Cards card = userDS.get(i);
				
			System.out.print(card.handview2 + " of " + card.suite);
				
			if (i < sizer - 1)
			{
				System.out.print(" and ");
			}
		}
	}		
	
	public void removeS(int i)
	{
		Cards voidcard = userDS.remove(i);
		
		deck.cardD.add(voidcard);
	}

	public void hitS()
	{
		Cards drawncard = deck.drawCard();
		
		userDS.add(drawncard);
	}

	public int processvalS()
	{
		int v = 0; 
	
		for(int o = 0; o < userDS.size(); o++)
	{
		Cards card1 = userDS.get(o);
		v += card1.rank;
	}
	return v;
	}
	
	public void acecheckerS()
	{	
		int cardlimit = userDS.size();
		
		for(int i = 0; i < userDS.size(); i++)
		{
			Cards card1 = userDS.get(i);
		
			if (card1.gethandview().equals("ACE"))
			{
				card1.acesetrank(11);
			}
		}
		
		while (processval() > 21 && cardlimit != 0)
		{
			for(int k = 0; k < userDS.size(); k++)
			{
				cardlimit--;
				Cards card1 = userDS.get(k);
			
				if (card1.gethandview().equals("ACE") && processval() > 21)
				{
					card1.acesetrank(1);
				}
			}
		}
	}
	
	public void resethandS()
	{
		for (int i = userDS.size() - 1; i >= 0; i--)
		{
			Cards voidcard = userDS.remove(i);
			
			deck.cardD.add(voidcard);
		}
	}
}
	

