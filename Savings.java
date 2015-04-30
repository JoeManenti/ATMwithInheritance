
package umsl.edu;

/*
        INFSYS 3816
	Assignment 1

        Team:
	Joseph Manenti
      Mark Meyer
      Eshraq Salahuddin

*/

public class Savings extends Account
{
    @Override
    protected void getInterest()
        {
            int year_gap = final_year - initial_year;
            int date_gap = final_date - initial_date;
            double year_tot = year_gap * 365;
            interest_rate = .05/365;
            double combo_gap = year_tot + date_gap;
            double new_rate = Math.pow(1+interest_rate,combo_gap);
            balance = balance * new_rate;
            initial_date = final_date;
            initial_year = final_year;
        }
}
