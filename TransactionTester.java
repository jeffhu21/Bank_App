package com.example.jeffr.eecs1022w18lab5extendedbankapplication;

/**
 * Created by jeffr on 2018-07-29.
 */
public class TransactionTester
{
    public static void main(String[] args)
    {
        Transaction t1 = new Transaction();

        Transaction t2 = new Transaction("DEPOSIT");

        System.out.println(t1.toStr());
        System.out.println(t2.toStr());

        System.out.println("----------------------------");

        t1.setType("WITHDRAW");
        t1.setAmount(78.0);

        t2.setAmount(90.0);

        System.out.println(t1.toStr());
        System.out.println(t2.toStr());

        System.out.println("----------------------------");

        t1.setAmount(83.0);
        t2.setAmount(100.0);

        System.out.println(t1.toStr());
        System.out.println(t2.toStr());
    }

}
