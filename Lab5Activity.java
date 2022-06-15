package com.example.jeffr.eecs1022w18lab5extendedbankapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Lab5Activity extends AppCompatActivity
{
    Bank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        bank = new Bank();
        setContentView(R.layout.activity_lab5);
    }

    private void setContentofTextView(int id, String newContents)
    {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }


    private String getInputOfTextField(int id)
    {
        View view = findViewById(id);
        EditText editText = (EditText) view;
        String input = editText.getText().toString();
        return input;
    }

    private String getItemSelected(int id)
    {
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String string = spinner.getSelectedItem().toString();
        return string;
    }

    public void computeAccountButtonClicked(View view)
    {
        String clientName = getInputOfTextField(R.id.inputName1);
        String textBalance = getInputOfTextField(R.id.inputBalance);
        double balance = Double.parseDouble(textBalance);

        bank.createClient(clientName,balance);
        setContentofTextView(R.id.theResult,bank.toStr());

    }

    public void computeTransactionButtonClicked(View view)
    {
        String service = getItemSelected(R.id.serviceOption);
        String fromClient = getInputOfTextField(R.id.inputFrom);
        String toClient = getInputOfTextField(R.id.inputTo);
        String textAmount = getInputOfTextField(R.id.inputAmount);
        double amount = Double.parseDouble(textAmount);

        bank.transaction(service,fromClient,toClient,amount);
        setContentofTextView(R.id.theResult,bank.toStr());
    }

    public void computeStatementButtonClicked(View view)
    {
        String client = getInputOfTextField(R.id.inputName2);

        //bank.outputStatement(client);
        setContentofTextView(R.id.theResult,bank.outputStatement(client));
    }

}
