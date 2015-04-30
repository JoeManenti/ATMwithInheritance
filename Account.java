package umsl.edu;

import java.io.*;
import java.text.*;
import java.util.*;

/*
     INFSYS 3816
	Assignment 1
	
	Team:
	Joseph Manenti
     Mark Meyer
	Eshraq Salahuddin
*/


public abstract class Account 
{

    public double balance; 
    public String acctNum;
    public int initial_date;
    public int final_date;
    public boolean date_marker;
    public double interest_rate;
    public final Calendar date_1 = new GregorianCalendar();
    public final Calendar date_2 = new GregorianCalendar();
    public final Calendar year_1 = new GregorianCalendar();
    public final Calendar year_2 = new GregorianCalendar();
    public int initial_year;
    public int final_year;
    
    ATM myParent;
    
	public Account ()
        {
            double beg_balance = 100.00;
            balance = beg_balance;

	}

        //Account Menu
        public void menu()throws IOException
        {
           
            while (true) 
            {
                System.out.println("");
                System.out.println("Please make a selection from the menu below:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. CheckBalance");
                System.out.println("4. Select Different Account or Exit");
                
                String char1;
                Scanner sc = new Scanner(System.in);
                char1 = sc.next();          

                if (null != char1) 
                switch (char1) {
                    case "1":
                        if (date_marker == true)
                        {
                            getDate_2();
                            getInterest();
                            deposit();
                        }
                        else
                        {
                            getDate_1();
                            deposit();
                        }   break;
                    case "2":
                        if (date_marker == true)
                        {
                            getDate_2();
                            getInterest();
                            withdraw();
                        }
                        else
                        {
                            getDate_1();
                            withdraw();
                        }   break;
                    case "3":
                        if (date_marker != true)
                        {
                            getInterest();
                            System.out.println("");
                            System.out.println("Your current balance is: " + getBalance());
                            System.out.println("");
                        }
                        else
                        {
                            getDate_2();
                            getInterest();
                            System.out.println("");
                            System.out.println("Your current balance is: " + getBalance());
                            System.out.println("");
                        }   break;
                    case "4":
                        return;
                    default:
                        System.out.println("");
                        System.out.println("Invalid Entry, Please Try Again!");
                        System.out.println("");
                        break;
                }  
            }
        }
        //To Get Account Number
        public String getAcctNum() 
        {
            return acctNum;
        }
        
        //To Set Account Number
        public void setAcctNum(String acctNum) 
        {
            this.acctNum = acctNum;
        }
        
        //To Set The Balance
        public void setBalance(double balance) 
        {
            this.balance = balance;
        }
        
	//To Get The Balance
        public String getBalance()
        {
		NumberFormat currencyFormatter;
		String currencyOut;
		 
		currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
		currencyOut = currencyFormatter.format(balance);
                
                return currencyOut;
	}
        
        //To Re-Format For Writing To File
        public String writeBalance()
        {
            String currencyOut;
            
            currencyOut = String.format("%.2f", balance);
                
            return currencyOut;
        }        

	// For Deposit

	private void deposit() throws IOException 
        {
		BufferedReader br;
		String entered_amount;

                System.out.println("");
                System.out.print("Please enter the amount you would like to deposit: ");
		br = new BufferedReader(new InputStreamReader(System.in));
		entered_amount = br.readLine();
		double amount = Double.parseDouble(entered_amount);
		balance = balance + amount;
                
                if (amount <= 0)
                {    
                            System.out.println("");
                            System.out.println("Can't deposit negative amounts.");
                            System.out.println("");
                }
                else
                 
                System.out.println("");    
                    System.out.println("Your updated balance is: " + getBalance());
                    System.out.println("");
		
	} 	

        //For Withdraw
        
	private void withdraw() throws IOException 
        {
		BufferedReader br;
		String entered_amount;

                System.out.println("");
		System.out.print("Please enter the amount you would like to withdraw: ");
		br = new BufferedReader(new InputStreamReader(System.in));
		entered_amount = br.readLine();
		double amount = Double.parseDouble(entered_amount);
		
		if (balance < amount) 
                {    
                        System.out.println("");
			System.out.println("Withdrawal Request Denied. Insufficient Funds!");
                        System.out.println("Please Enter New Amount.");
                        System.out.println("Your current balance is: " + getBalance());
                        System.out.println("");
                }        
		else  
                {    
			balance = balance - amount;
	
                        System.out.println("");        
                        System.out.println("Your updated balance is: " + getBalance());
                        System.out.println("");
                }
        }     
        
        //For Marking Initial Date
        
        private void getDate_1() throws IOException
        {
            System.out.println("");
            System.out.print("Please Enter Today's Date (mm/dd/yyyy): ");
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(System.in));
            String inputText = br.readLine();
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            ParsePosition ypos = new ParsePosition(0);
            ParsePosition pos = new ParsePosition(0);
            
            Date date;
            Date year;
            year = formatter.parse(inputText, ypos);
            date = formatter.parse(inputText, pos);
            year_1.setTime(year);
            date_1.setTime(date);
            initial_year = year_1.get(Calendar.YEAR);
            initial_date = date_1.get(Calendar.DAY_OF_YEAR);
            date_marker = true;
        }
        
        //For Marking Final Date
        
        private void getDate_2() throws IOException
        {
            System.out.println("");
            System.out.print("Please Enter Today's Date (mm/dd/yyyy): ");
            BufferedReader br;
            br = new BufferedReader(new InputStreamReader(System.in));
            String inputText = br.readLine();
            
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            ParsePosition ypos = new ParsePosition(0);
            ParsePosition pos = new ParsePosition(0);
           
            Date date;
            Date year;
            year = formatter.parse(inputText, ypos);
            date = formatter.parse(inputText, pos);
            year_2.setTime(year);
            date_2.setTime(date);
            final_year = year_2.get(Calendar.YEAR);
            final_date = date_2.get(Calendar.DAY_OF_YEAR);
            
            if (initial_year > final_year)
            {
                System.out.println("Date entered must be a Future Date!");
                System.out.println("");
                getDate_2();
            }    
            else if ((initial_year == final_year) && (initial_date > final_date))
            {
                System.out.println("Date entered must be a Future Date!");
                System.out.println("");
                getDate_2();
            }    
        }
        
        //For Interest Calculation
        
        protected abstract void getInterest();
        

}