package BlackJack;

import java.util.ArrayList;
import java.util.Scanner;


public class MainClass 
{
	static Scanner sc = new Scanner(System.in);	
	
	private static int better;
	private static int userval;
	private static int uservalS = -1;
	private static int dealerval;
	private static boolean insurancebool = false;
	
	public static Deck deck;
	public static Hand hand;
	public static VerifyBet bet; 
	public static DealerDeck deckD;
	
	public static void main(String[] args) throws Exception
	{
		
		deck = new Deck();
		hand = new Hand(deck);
		bet = new VerifyBet();
		deckD = new DealerDeck(hand,deck);
				
		System.out.println("BLACKJACK 21!");
		System.out.println();
		System.out.println("You have $" + bet.getuseramt());
		System.out.println();

		do {
			int thebet = 0;

			boolean betbool = true;
			do
			{
				System.out.print("Bet: $");
				
				try {
					
					better = sc.nextInt();
					betbool = bet.verifyBet(better);
					
				} catch (Exception e) {
					System.out.println("Invalid character; Try again! ");
					betbool = true;
					sc.nextLine();
				} finally {
				}
				
			} while(betbool);

			deck.shuffleone();
			
			bet.setbet(better);
			
			hand.hit();
			hand.hit();
			
			boolean stage1 = true;
			
			//Player turn 
			boolean check;
			boolean checkD;
			
			//fix this, dealerstart + insurance should be a one time thing.
			
			deckD.hitD();
			deckD.hitD();
			System.out.println();
			deckD.dealerstart();
			System.out.println();
			
			Cards firstcard = deckD.dealerD.get(0);
	
			if (firstcard.rank == 1 || firstcard.rank == 11)
			{
				//offer insurance
				System.out.println("Press I for insurance, or any other key to "
						+ "continue.");
				Scanner sc22 = new Scanner(System.in);
				String y = sc22.next();
				
				if (y.equals("I"))
				{
					bet.setinsurancebet(bet.getbet()/2);
					setinsurancebool(true);
				}
			}
			
			hand.handreader();
			System.out.println();
			
			boolean check1 = true;
			do 
			{	
				try {
					check1 = stg1();
				}
				catch(Exception e) 
				{
					System.out.println(e);
					System.out.println("Please enter a valid choice");	
				}	
			}while (check1);
			
			System.out.println();
			System.out.println("\nIt's the dealer's turn.\n");
			System.out.println();
			
			try {
				stg2();
			}
			catch(Exception e)
			{
				System.out.print("stage 2 error.");
			}
			
			if (getuservalS() != -1)
			{
				int result = deckD.compare(getuserval(),getdealerval());
				System.out.println("Your first hand: ");
				System.out.println(getuserval() + " vs. " +
						deckD.processvalD());
				stg3(result);
				int result2 = deckD.compare(hand.processvalS(),getdealerval());
				System.out.println("Your second hand: ");
				System.out.println(hand.processvalS() + " vs. " +
						deckD.processvalD());
				stg3(result2);
			} else {
				int mainresult = deckD.compare(hand.processval(), 
				deckD.processvalD());
				System.out.println(getuserval() + " vs. " +
						deckD.processvalD());
				stg3(mainresult);
			}
			
			//return all cards from all hands to the deck and then shuffle
			hand.resethand();
			hand.resethandS();
			deckD.resethandD();
		} while(bet.getuseramt() > 0); 
	System.out.println("Game Over!");
	}
	//end of program
		
	private static boolean stg1() throws Exception
	{	
		boolean checkers = true;
		hand.acechecker();
		int rawval = hand.processval();

		if (rawval == 21) {
			System.out.println("Player Blackjack!");
			setuserval(rawval);
			return false;
		} else if (hand.user2split() && hand.userD.size() == 2) {
			Scanner sc5 = new Scanner(System.in);
			
			do {
				if (checkers && getuserval() > 21)
				{
					checkers = false;
				} else if (checkers && (bet.getbet()*2 
						<= bet.getuseramt())) {
					System.out.println();
					System.out.println("Press H to hit, "
							+ "S to stand, " +
							"DB to double, SS to split " 
							+ "or D to see the Dealer's hand.\n");
					String t = sc5.next();
					checkers = usersplit(t);

				} else if (checkers && rawval < 21) {
					System.out.println();
					System.out.println("Press H to hit, "
							+ "S to stand, " 
							+ "or D to see the Dealer's hand.\n");
					String t = sc5.next();
					checkers = useroption(t);	
				} else {
					setuserval(rawval);
					return false;
				}
			} while(checkers);
			return false;
		}else {
			Scanner sc22 = new Scanner(System.in);
			
			do {
				hand.acechecker();
				int rawval2 = hand.processval();
				
				if (rawval2 > 21) {
					setuserval(rawval2);
					return false;
				} else if (rawval2 == 21) {
					System.out.println("Player Blackjack!");
					setuserval(rawval);
					return false;
				} else if (hand.userD.size() == 2 && rawval2 < 21 &&
				(bet.getbet()*2 <= bet.getuseramt())) {
					System.out.println("\nPress H to hit, "
							+ "S to stand, " 
							+ "DB to double "
							+ "or D to see the Dealer's hand.\n");
					String t = sc22.next();
					checkers = useroption(t);
				}else {
					if (checkers) {
						System.out.println("\nPress H to hit, "
								+ "S to stand," 
								+ " or D to see the Dealer's hand. \n");
						String t = sc22.next();
						checkers = useroption(t);
					}else {
						setuserval(rawval2);
						return false;
					}
				}
			} while (checkers);
		return false;
		}
	}
	
	//dealer's turn	
	private static void stg2()
	{
		boolean checkDD = true;
		deckD.dealerplay();
		System.out.println();
		
		do
		{
			deckD.acecheckerD();
			int dval = deckD.processvalD();
			System.out.println();

			try 
			{
				checkDD = false;
				setdealerval(dval);

				if (dval == 21) {
					System.out.println("Dealer Blackjack!");

				} else if (dval < 17){
					deckD.hitD();
					deckD.dealerplay();
					System.out.println();
					checkDD = true;
				} else {}
				
			} catch(Exception e){
				System.out.print("Dealer error, "
				+ "please restart program.");
			}
		} while (checkDD);
	}
	
	private static void stg3(int wol)
	{
		System.out.println();
		int totalamt = bet.getuseramt();
		int userbet = bet.getbet();
		int insurance = bet.getinsurancebet();
		
		
		if (getinsurancebool())
		{
			Cards d1 = deckD.dealerD.get(0);
			Cards d2 = deckD.dealerD.get(1);
			
			System.out.println("Your insurance was: $" + 
					insurance);
			
			if (d1.gethandview().equals("ACE") && d2.getrank() == 10) 
			{	
				System.out.println("Dealer BlackJack, won $" + insurance * 2);
				bet.setuseramt(totalamt + insurance * 2);
			} else {
				System.out.println("No Dealer BlackJack, lost: $" + 
						insurance);
				bet.setuseramt(totalamt - insurance);
			}
			setinsurancebool(false);
			System.out.println();
			totalamt = bet.getuseramt();
		}
		
		switch(wol) 
		{
		case 1: 
			System.out.println("User won! Got: $" + userbet);
			bet.setuseramt(totalamt + userbet);
				
			System.out.println("You now have: $" + bet.getuseramt());
			System.out.println();
			break;
			
		case 2:
			System.out.println("Dealer won! Lost: $" + userbet);
			bet.setuseramt(totalamt - userbet);
			System.out.println("You now have: $" + bet.getuseramt());
			System.out.println();
			break;
			
		case 3:
			System.out.println("Push!");
			System.out.println("You now have: $" + totalamt);
			System.out.println();
			break;
			
		default:
			System.out.println("There was an error."
					+ " Please reset the program.\n");
		}
	}	

	private static boolean usersplit(String s) throws Exception
	{			
		hand.acechecker();
		int rval = hand.processval();
				
		if (rval%2 == 0 && hand.userD.size() == 2 
		&& rval < 21 && s.equals("SS") && 
		bet.getbet() * 2 <= bet.getuseramt()) {
			
			hand.transfersplit();
			boolean maintrigger = false;
			boolean trigger1 = false;
						
			do {		
				if (trigger1 == false) 
				{
					hand.acechecker();
					int rval1 = hand.processval();
					System.out.println();
					System.out.println("\nYour first hand is: ");
					System.out.println();
					hand.handreader();
								
					if (rval1 > 21) 
					{
						setuserval(rval1);
						trigger1 = true;
					} else if (rval1 == 21) {
						System.out.println("Player Blackjack!");
						setuserval(rval1);
						trigger1 = true;
					} else {
						System.out.println("\nPress H to hit, S to stand"
						+ " or D to see the Dealer's hand.\n");
										
						Scanner sc1 = new Scanner(System.in);
						String y = sc1.next();
											
						if (y.equals("D"))
						{
							deckD.dealerstart();
							continue;
						} else if (y.equals("S")) {
							setuserval(rval1);		
							trigger1 = true;
								
						} else if (y.equals("H")) {								
							hand.hit();
							continue;
						} else {
							System.out.println("Please enter a valid"
							+ "option.");
							continue;
						}
					}
				} else {
					hand.acecheckerS();
					int rval2 = hand.processvalS();
					System.out.println();
					System.out.println("\nYour second hand is: ");
					System.out.println();
					hand.handreaderS();
								
					if (rval2 > 21)
					{
						setuservalS(rval);
						maintrigger = true;
					} else if (rval2 == 21){
						System.out.println("BlackJack!");
						setuservalS(rval);
						maintrigger = true;
					} else {
						System.out.println("\nPress H to hit, "
						+ "S to stand" 
						+ " or D to see the Dealer's hand.\n");
											
						Scanner sc1 = new Scanner(System.in);
						String y = sc1.next();
										
						if (y.equals("D")){
							deckD.dealerstart();
							System.out.println();
							continue;
						} else if (y.equals("S")) {
							setuservalS(rval);		
							maintrigger = true;
						} else if (y.equals("H")) {
							hand.hitS();
							hand.handreaderS();
							continue;
						} else {
							System.out.println("Please enter a valid" 
							+ "option");
							continue;
						}
					}
				}
			} while(maintrigger == false);	
			return false; //ends user turn	
		} else if (rval < 21 && s.equals("H")) {
			hand.hit();
			hand.handreader();
			return true;
		} else if (rval < 21 && bet.getbet() * 2 <= bet.getuseramt() 
				&& s.equals("DB")) {
			hand.hit();
			hand.handreader();
			rval = hand.processval();
			setuserval(rval);
			int doublebet = bet.getbet() * 2;
			bet.setbet(doublebet);
			return false;
		} else if (rval < 21 && s.equals("S")) {
			setuserval(rval);
			hand.handreader();
			return false;
		} else if (rval < 21 && s.equals("D")) {
			deckD.dealerstart();
			System.out.println();
			hand.handreader();
			return true;
		} else if (rval > 21) {
			setuserval(rval);
		} else if (rval == 21) {
			System.out.println("Player BlackJack!");
			return false;
		} else {
			System.out.println("Please select a viable option.");
			System.out.println();
			hand.handreader();
			return true;
		}
		return false;
	}
	
	private static boolean useroption(String s) throws Exception 
	{
			boolean checker = true;
			
			hand.acechecker();
			int rval = hand.processval();
				
			if (hand.userD.size() == 2 && rval < 21 && 
			(bet.getbet() * 2) <= bet.getuseramt()) {
				
				if (s.equals("DB")) {
					hand.hit();
					hand.handreader();
					setuserval(hand.processval());
					bet.setbet(bet.getbet() * 2);
					return false;
					
				}else if (s.equals("D")) {
					deckD.dealerstart();
					System.out.println();
					hand.handreader();
					return true;
					
				}else if (s.equals("S")) {
					hand.handreader();
					setuserval(rval);
					return false;
					
				}else if (s.equals("H")) {
					hand.hit();
					hand.handreader();
					return true;
					
				} else {
					System.out.println("Please select a viable option.");
					System.out.println();
					hand.handreader();
					return true;
				}
			}else if (rval < 21) {	
				if (s.equals("D")) {
					deckD.dealerstart();
					System.out.println();
					hand.handreader();
					return true;
					
				} else if (s.equals("S")) {
					hand.handreader();
					setuserval(rval);
					return false;
					
				} else if (s.equals("H")) {
					hand.hit();
					hand.acechecker();
						if (hand.processval() == 21)
						{
							hand.handreader();
							setuserval(rval);
							return false;
							
						}else if (hand.processval() > 21){
							hand.handreader();
							setuserval(rval);
							return false;
							
						} else {
							hand.handreader();
							return true;
						}
						
				} else {
					System.out.println("Please select a viable option.");
					System.out.println();
					hand.handreader();
					return true;
				}
			} else {
				return false;
			}
	}
				
		private static void setuserval(int j)
		{
			userval = j;
		}
		private static int getuserval()
		{
			return userval;
		}
		
		private static void setuservalS(int j)
		{
			uservalS = j;
		}
		private static int getuservalS()
		{
			return uservalS;
		}
		
		private static void setdealerval(int j)
		{
			dealerval = j;
		}
		private static int getdealerval()
		{
			return dealerval;
		}
		private static void setinsurancebool(boolean x)
		{
			insurancebool = x;
		}
		private static boolean getinsurancebool()
		{
			return insurancebool;
		}
}