package com.example.jeffr.eecs1022w18lab5extendedbankapplication;

/**
 * Created by jeffr on 2018-07-28.
 */
public class Client
{
    String name;
    double balance;
    Transaction[] history;
    int not;


    final int MAX_NUM_TRANSACTIONS = 10;

    boolean error;
    String errorMsg;

    Client ()
    {

    }

    Client(String name,double balance)
    {
        this.name = name;
        this.balance = balance;
        history = new Transaction[MAX_NUM_TRANSACTIONS];
        not = 0;

        error = false;
        errorMsg = "";
    }

    void setError(String errorMsg)
    {
        this.error = true;
        this.errorMsg = errorMsg;
        System.out.println(this.errorMsg);
    }

    void resetError()
    {
        this.error = false;
        this.errorMsg = "";
        System.out.println(this.errorMsg);
    }

    void increment()
    {
        this.not++;
    }

    boolean hasReachedMaximum()
    {
        boolean result = this.not == MAX_NUM_TRANSACTIONS;
        return result;
    }

    void addTransaction(Transaction t)
    {
        this.history[not] = t;
        increment();
    }

    void createTransaction(String type,double amount)
    {
        if (!hasReachedMaximum())
        {
            Transaction t = new Transaction(type,amount);
            this.addTransaction(t);
            resetError();
        }
        else
        {
            setError("Error: Maximum Number of Transactions Reached");
        }
    }

    int indexOfTransaction(String type)
    {
        int index = -1;

        if(this.not > 0)
        {
            for (int i = 0; i < this.not; i++)
            {
                if (history[i].type.equals(type))
                {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    double depositMoney(double amount)
    {
        this.balance += amount;
        Transaction tran = new Transaction("DEPOSIT",amount);
        createTransaction(tran.type,amount);

        return this.balance;
    }

    double withdrawMoney(double amount)
    {
        this.balance -= amount;
        Transaction tran = new Transaction("WITHDRAW",amount);
        createTransaction(tran.type,amount);

        return this.balance;
    }


}
