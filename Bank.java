package com.example.jeffr.eecs1022w18lab5extendedbankapplication;

/**
 * Created by jeffr on 2018-07-28.
 */
public class Bank
{
    Client[] clients;
    int noc;

    final int MAX_NUM_CLIENTS = 6;

    boolean error;
    String errorMsg;

    Bank()
    {
        clients = new Client[MAX_NUM_CLIENTS];
        noc = 0;

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

    void createClient(String name,double balance)
    {
        int index = indexOfClient(name);

        if(index < 0)
        {
            if(balance > 0)
            {
                if (!hasReachedMaximum())
                {
                    Client c = new Client(name, balance);
                    this.addClient(c);
                    resetError();
                }
                else
                {
                    setError("Error: Maximum Number of Clients Reached");
                }
            }
                else
                {
                    setError("Error: Non-Positive Initial Balance.");
                }
        }
            else
            {
                setError("Error: Client " + name + " already exists.");
            }
    }

    void addClient(Client c)
    {
        this.clients[noc] = c;
        increment();
    }

    void increment()
    {
        this.noc ++;
    }

    boolean hasReachedMaximum()
    {
        boolean result = this.noc == MAX_NUM_CLIENTS;
        return result;
    }

    int indexOfClient(String name)
    {
        int index = -1;

        if(this.noc > 0)
        {
            for (int i = 0; i < this.noc; i++)
            {
                if (clients[i].name.equals(name))
                {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    void transaction(String type,String fromName,String toName,double amount)
    {
        if (type.equalsIgnoreCase("DEPOSIT"))
        {
            int index = indexOfClient(toName);

            if(index >= 0)
            {
                if (amount > 0)
                {
                    if (!this.clients[index].hasReachedMaximum())
                    {
                        this.clients[index].depositMoney(amount);
                        resetError();
                    }
                    else
                    {
                        setError("Error: Maximum Number of Transactions Reached");
                    }
                }
                else
                {
                    setError("Error: Non-Positive Amount.");
                }
            }
            else
            {
                setError("Error: To-Client " + toName + " does not exist.");
            }
        }
        else if (type.equalsIgnoreCase("WITHDRAW"))
        {
            int index = indexOfClient(fromName);

            if(index >= 0)
            {
                if(amount > 0)
                {
                    if (amount <= this.clients[index].balance)
                    {
                        if (!this.clients[index].hasReachedMaximum())
                        {
                            this.clients[index].withdrawMoney(amount);
                            resetError();
                        }
                        else
                        {
                            setError("Error: Maximum Number of Transactions Reached");
                        }
                    }
                    else
                    {
                        setError("Error: Amount too large to withdraw.");
                    }
                }
                else
                {
                    setError("Error: Non-Positive Amount.");
                }
            }
            else
            {
                setError("Error: From-Client " + fromName + " does not exist.");
            }
        }
        else if (type.equalsIgnoreCase("TRANSFER"))
        {
            int fromIndex = indexOfClient(fromName);
            int toIndex = indexOfClient(toName);

            if(fromIndex >= 0)
            {
                if (toIndex >= 0)
                {
                    if (fromIndex != toIndex)
                    {
                        if (amount > 0)
                        {
                            if (amount <= this.clients[fromIndex].balance)
                            {
                                if (!this.clients[fromIndex].hasReachedMaximum() && !this.clients[toIndex].hasReachedMaximum())
                                {
                                    this.clients[fromIndex].withdrawMoney(amount);
                                    this.clients[toIndex].depositMoney(amount);
                                    resetError();
                                }
                                else
                                {
                                    setError("Error: Maximum Number of Transactions Reached");
                                }
                            }
                            else
                            {
                                setError("Error: Amount too large to withdraw.");
                            }
                        }
                        else
                        {
                            setError("Error: Non-Positive Amount.");
                        }

                    }
                    else
                    {
                        setError("From-Client and To-Client cannot be the same.");
                    }

                }
                else
                {
                    setError("Error: To-Client " + toName + " does not exist.");
                }
            }
            else
            {
                setError("Error: From-Client " + fromName + " does not exist.");
            }
        }
    }

    String outputStatement(String name)
    {
        int index = indexOfClient(name);
        String bal = "";
        String s = "";

        if(index >= 0)
        {
            bal = String.format("%.2f",this.clients[index].balance);
            s += "Statement of client " + name + "(current balance $" + bal + "):\n";

            for (int i = 0; i < this.clients[index].not;i++)
            {
                s += "Transaction " + this.clients[index].history[i].type + ":$" + this.clients[index].history[i].amount;
            }
        }
        else
        {
            s += "Error: Client " + name + " does not exist.";
        }
        return s;
    }

    public String toStr()
    {
        String bal = "";
        String s = "";

        s += "Updated Balances of Clients:\n";

        for (int i = 0; i < this.clients.length;i++)
        {
            if(this.clients[i] == null)
            {
                s += "";
            }
            else
            {
                bal = String.format("%.2f",this.clients[i].balance);
                s +=  this.clients[i].name + ":$" + bal;
            }

            if(i < this.clients.length - 1)
            {
                s += "\n";
            }
        }

        s += "";

        return s;
    }

}
