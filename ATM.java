package umsl.edu;

import java.io.*;
import java.util.*;

/*
	INFSYS 3816
      Assignment 1

	Team:
	Joseph Manenti
      Mark Meyer
      Eshraq Salahuddin
*/

public class ATM 
{
    Account myAccounts[] = new Account[3];   
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    //Main method to start program
    public static void main(String args[]) throws IOException
    {
        ATM myATM = new ATM();
        myATM.runATM();
    } 
    
    //Run ATM 
    public void runATM() throws IOException
    {        
        System.out.println("Welcome to M&M Bank!");
        ATM myATM = new ATM();
        // Populate the accounts with default data
        myATM.populateAccts();
        
        // Read account data from disk
        myATM.readAcct();
        
        // Enter the main choose account loop
        myATM.ChooseAcct();
        
        //Write account data to disk
        myATM.writeAcct();
        
        //Exit message with account balance totals
        System.out.println("");
        System.out.println("Account Balances:");
        System.out.println("");
        myATM.showAccts();
        System.out.println("");
        System.out.println("Thank you for Choosing M&M Bank.");
        System.out.println("Goodbye.");
    }

    //To Show Accounts At Exit   
    public void showAccts()
    {
        for(int i = 0; i < 3; i++)
        {
            System.out.println(myAccounts[i].getAcctNum() + " " + myAccounts[i].getBalance());
        }

    }

    //To Populate The Accounts
    public void populateAccts() throws IOException
    {   
        myAccounts[0] = new Checking();
        myAccounts[0].myParent = this;
        String s = Integer.toString(0);
        myAccounts[0].setAcctNum(s);
        
        myAccounts[1] = new Savings();
        myAccounts[1].myParent = this;
        s = Integer.toString(1);
        myAccounts[1].setAcctNum(s);
        
        myAccounts[2] = new MoneyMarket();
        myAccounts[2].myParent = this;
        s = Integer.toString(2);
        myAccounts[2].setAcctNum(s);   
    }

    //To Read From File
    public void readAcct()
    {
        try
        {
            BufferedReader buffread = new BufferedReader(new FileReader("accountINFO.txt"));
            int counter = 0;

            String sCurrentLine;
            
            while ((sCurrentLine = buffread.readLine()) != null) 
            {
                StringTokenizer st = new StringTokenizer(sCurrentLine, "|");

                while(st.hasMoreTokens())
                {
                    for(int i = 0; i < 2; i++)
                    {

                        if(i == 0)
                        {
                            String tempAcct = st.nextToken();
                            myAccounts[counter].setAcctNum(tempAcct);
                        }
                        else
                        {
                            myAccounts[counter].setBalance(Double.parseDouble(st.nextToken()));
                        }
                    }
                }
                counter++;
            }
        } 
        catch (IOException e)
        {

        }
    }

    //To Write Account to File
    public void writeAcct()
    {
        try 
        {
            File file = new File("accountINFO.txt");

            //if file doesnt exists, then create it
            if (!file.exists()) 
            {
                file.createNewFile();
            }
                    
            FileWriter fw = new FileWriter(file, false);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for(int input=0;input<3;input++)
                { 
                    String tempString = myAccounts[input].getAcctNum();
                    String tempStringNum = myAccounts[input].writeBalance();
                    
                    bw.write(tempString);
                    bw.write("|");
                    bw.write(tempStringNum);
                    bw.newLine();
                    bw.flush();
                }
            }
        } 
        catch (IOException e) 
        {
            
        }
    }
    
   //Choose Account Menu
   public void ChooseAcct() throws IOException
    {    
        int input = 0;
        
        //While loop for account menu
        while (input !=4)
        {   
            System.out.println("");
            System.out.println("Please Select an Account Number: ");
            System.out.println("Checking, Enter 1");
            System.out.println("Savings, Enter 2");
            System.out.println("Money Market, Enter 3");
            System.out.println("To Exit, Enter 4");
                        
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
            
            if(input == 4) 
            {
                
            }
            else if(input >= 1 && input <= 3) 
            {
                myAccounts[input - 1].menu();
            }
            else
            {
                System.out.println("Invalid Entry! Please Try Again.");
            }
            
        }

    }   
   
}