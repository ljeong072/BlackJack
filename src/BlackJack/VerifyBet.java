/**
 * BlackJack Project
 * File Name: VerifyBet.java
 * 
 * This class contains the methods for tracking and verifying
 * whether or not the bets are correct or not.
 */

package BlackJack;

/**
 * This class contains the methods and data necessary
 * to materialize cards in a deck. 
 * 
 * @author Lucas Jeong
 * @version 2023 August 21
 */
public class VerifyBet 
{	
	int usertotal;	
	int userbet; 
	int insurancebet;
	
	public VerifyBet()
	{
		usertotal = 1000;
		userbet = 0;
		insurancebet = 0;
	}

	public int getuseramt()
	{
		return usertotal;
	}
	
	public int getbet() 
	{
		return userbet;
	}
	
	public int getinsurancebet()
	{
		return insurancebet;
	}
	
	public void setuseramt(int usertotal)
	{
		this.usertotal = usertotal;
	}
	public void setbet(int userbet) 
	{
		this.userbet = userbet;	
	}
	public void setinsurancebet(int insurancebet)
	{
		this.insurancebet = insurancebet;
	}
	
	public boolean verifyBet(int userbet) throws Exception
	{	
		//true will keep you locked in, false will let you out.
		boolean result = false;
		if ((userbet <= 0)) 
		{
			System.out.println("Can't bet 0 or below $. Try again!");
			System.out.println();
			result = true;
		} else if (userbet > getuseramt()) {
			System.out.println("Can't bet more than what you have."
					+ "Try again!");
			System.out.println();
			result = true;
		}
		return result;
	}
	
	public void setresult(int moneybet)
	{
		if (moneybet < 0)
		{
			System.out.println("You lost $" + moneybet);
		}else if (moneybet > 0){
			System.out.println("You won $" + moneybet);
		}else {
			System.out.println("Push");
		}
			
		setuseramt(getuseramt() + moneybet);
	}
	

	public int WoLamount(VerifyBet bet, int checker)
	{
		switch(checker)
		{
		case 1:
			return bet.getbet();
		case 2:
			return  -1 * bet.getbet();
		case 3:
			return 0;
		default:
			return -1;
		}
	}	
}