import java.io.Serializable;

public class AccountRecordSerializableKeyed extends hasKey implements Serializable //must be implemented
{
    //private int account;
    private String firstName;
    private String lastName;
    private double balance;

    public AccountRecordSerializableKeyed()
    {
        this(0,"","",0.0);
    }

    public AccountRecordSerializableKeyed(int acct, String first, String last, double bal)
    {
        //setAccount(acct);
        super(acct);
        setFirstName(first);
        setLastName(last);
        setBalance(bal);
    }

       /*public void setAccount(int acct)
       {
               account = acct;
       }*/

       /*public int getKey()
       {
               return account;
       }*/

    public void setFirstName(String first)
    {
        firstName = first;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setLastName(String last)
    {
        lastName = last;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setBalance(double bal)
    {
        balance = bal;
    }

    public double getBalance()
    {
        return balance;
    }

    public void addBalance(double amount)
    {
        balance += amount;
    }

    public String toString()
    {
        return String.format("%-10d%-12s%-12s%10.2f\n", getKey(), firstName, lastName, balance);
    }
}

