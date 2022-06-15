package com.example.jeffr.eecs1022w18lab5extendedbankapplication;

/**
 * Created by jeffr on 2018-07-28.
 */
public class Transaction
{
    String type;
    double amount;

    //String from;
    //String to;

    Transaction()
    {

    }

    Transaction(String type)
    {
        this.type = type;
    }

    Transaction(String type,double amount)
    {
        this.type = type;
        this.amount = amount;
    }

    /*
    Transaction(String type,double amount,String from,String to)
    {
        this.type = type;
        this.amount = amount;
        this.from = from;
        this.to = to;


    }
    */

    void setType(String type)
    {
        this.type = type;
    }

    void setAmount(double amount)
    {
        this.amount = amount;
    }


    public String toStr()
    {
        return "Transaction's type is " +
                this.type +
                " and the amount is "
                + this.amount;
    }

}
