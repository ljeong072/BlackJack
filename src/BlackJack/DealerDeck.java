/**
 * BlackJack Project
 * File Name: DealerDeck.java
 * 
 * This class contains the methods and data
 * for the dealer to play.
 */

package BlackJack;

import java.util.ArrayList;
/**
 * This class contains the behavior of the dealer 
 * 
 * @author Lucas Jeong
 * @version 2023 August 21
 */
public class DealerDeck 
{
	private Cards cards;
	private Deck deck;
	
	/**
	 * The cards in the dealer's hand
	 */
	private Hand hand;
	
	/**
	 * The cards in the dealer's deck
	 */
	public ArrayList<Cards> dealerD;
	
	/** 
	 * This is the dealer's starting hand and it gets two 
	 * cards, one of which is unknown to the player
	 */
	public DealerDeck(Hand hand, Deck deck)
	{	
		this.hand = hand;
		this.deck = deck;
		dealerD = new ArrayList<Cards>(2);	
	}
	
	/**
	 * This method reads the dealer's hand and gives the player
	 * information to make a move from there.
	 */
	public void dealerstart()
	{
		//obj1 must be index 0.
		Cards obj1 = dealerD.get(0);
		System.out.println("The dealer's cards are: ");
		System.out.println(obj1.handview2 + " of " 
				+ obj1.suite + " and 'X'.");
	}
	
	/**
	 * This method reads the dealer's hand after the player
	 * makes a move.
	 */
	public void dealerplay()
	{
		int sizer = dealerD.size();
		
		System.out.println("The dealer's cards are: ");
		
		for(int i = 0; i < sizer; i++)
		{
			Cards card = dealerD.get(i);
			
			System.out.print(card.handview2 + " of " + card.suite);
			
			if (i < sizer - 1)
			{
				System.out.print(" and ");
			}
		}
	}
	
	/**
	 * This method allows the dealer to hit 
	 */
	public void hitD()
	{
		Cards drawncard = deck.drawCard();
				
		dealerD.add(drawncard);
	}
	
	/**
	 * This comparison method compares the total values
	 * of the ranks in each hand to determine who wins
	 * or whether it is a push.
	 * 
	 * @param user1 is the player.
	 * @param user2 is the dealer.
	 * @return an integer which represents whether the user won, lost,
	 * or if it was a push.
	 */
	public int compare(int user1, int user2) 
	{
		//NOTE TO SELF, Try to make 1 return statement
		
		if (user1 <= 21 && user2 <= 21) {
			if (user1 > user2){
				return 1;
			} else if(user1 < user2) {
				return 2;
			} else {
				return 3;
			}
		//1 == user1 loss, 2 == user2 loss, 3 == push.
		} else if (user1 <= 21 && user2 > 21){
			return 1;
		}else if (user1 > 21 && user2 <= 21){
			return 2;
		}else if (user1 > 21 && user2 > 21){				
			return 3;
		}
		return 0;
	}
	
	/**
	 * this method processes the sum of all cards in the dealer's deck.
	 * 
	 * @return the total value of all the cards in the dealer's deck.
	 */
	public int processvalD()
	{
		int ranksum = 0; 
	
		for(int i = 0; i < dealerD.size(); i++)
		{
			Cards card1 = dealerD.get(i);
			ranksum += card1.rank;
		}
		return ranksum;
	}
	
	/**
	 * This method checks for all the aces in the dealer's hand
	 * and changes their value to either 1 or 11. It does this
	 * by changing all aces in the hand to 11 and then changing them
	 * back into 1 or 11.
	 */
	public void acecheckerD()
	{	
		int cardlimit = dealerD.size();
				
		for(int i = 0; i < dealerD.size(); i++)
		{
			Cards card1 = dealerD.get(i);
		
			if (card1.gethandview().equals("ACE"))
			{
				card1.acesetrank(11);
			}
		}
		
		while (processvalD() > 21 && cardlimit != 0 )
		{
			for (int k = 0; k < dealerD.size(); k++)
			{
				cardlimit--;
				
				Cards card1 = dealerD.get(k);
			
				if (card1.gethandview().equals("ACE") && processvalD() > 21)
				{
					card1.acesetrank(1);
				}
			}
		}
	}
	public void resethandD()
	{
		for (int i = dealerD.size() - 1; i >= 0; i--)
		{
			Cards voidcard = dealerD.remove(i);
			
			deck.cardD.add(voidcard);
		}
	}
}





























