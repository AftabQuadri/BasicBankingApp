package quadri.aftab.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SingleTransaction extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
TextView customer_name,customer_email,customer_amount;
ArrayList<String> newList;
Spinner spinner;
EditText editText;
Button transfer_button;
String spinner_data;
SQLiteDatabase db;
DatabaseHelper2 helper2;
DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_transaction);
        customer_name=findViewById(R.id.name1);
        customer_email=findViewById(R.id.email1);
        customer_amount=findViewById(R.id.amount1);
        spinner=findViewById(R.id.spinner0);
        editText=findViewById(R.id.amount2);
        transfer_button=findViewById(R.id.transfer_btn);
        helper=new DatabaseHelper(SingleTransaction.this);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String email=intent.getStringExtra("email");
        int amount=intent.getIntExtra("amount",0);
        int index=intent.getIntExtra("index",-1);
        Bundle b=intent.getExtras();
        newList=(ArrayList<String>)b.getSerializable("listarray");
        newList.remove(index);
        customer_name.setText(name);
        customer_email.setText(email);
        customer_amount.setText(String.valueOf(amount));
        helper2=new DatabaseHelper2(SingleTransaction.this);
        Log.d("newList", String.valueOf(newList.get(0)));
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) SingleTransaction.this);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_item,newList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);

        transfer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String transferable_balance=editText.getText().toString();
                int new_balance_sender,new_balance_receiver;
                if(!TextUtils.isEmpty(String.valueOf(transferable_balance)) && spinner_data!=null && amount<Integer.parseInt(transferable_balance))
                {
                    Toast.makeText(SingleTransaction.this, "Insufficient balance", Toast.LENGTH_SHORT).show();
                }
                else if(spinner_data!=null && !TextUtils.isEmpty(transferable_balance))
                {

                    CustomerModel customerModel;
                    int prevBalReceiver=helper.getSingleData(spinner_data).getAmount();//getting previous balance of the receiver
                      new_balance_sender=amount-Integer.parseInt(transferable_balance);//calculating new balance of the sender
                      new_balance_receiver=Integer.parseInt(transferable_balance)+prevBalReceiver;//calculating new balance of receiver
                    Log.d("receiver_balance",prevBalReceiver+" "+amount+" "+new_balance_receiver);
                     customerModel=new CustomerModel(name,spinner_data,Integer.parseInt(transferable_balance));
                    boolean b1 = helper2.addOneTransaction(customerModel);//adding transaction to transaction table
                    if(b1)
                    {
                        customerModel=new CustomerModel(name,email,new_balance_sender);
                        helper.updateOne(customerModel);//updating the sender balance
                        customerModel=new CustomerModel(null,null,prevBalReceiver);
                        String receiver_email=helper.getSingleData(spinner_data).getEmail();//getting receiver email
                        customerModel=new CustomerModel(spinner_data,receiver_email,new_balance_receiver);
                        helper.updateOne(customerModel);
                        Toast.makeText(SingleTransaction.this, "success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SingleTransaction.this,Transaction_Activity.class));
                    }
                    else
                        Toast.makeText(SingleTransaction.this, "failed", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SingleTransaction.this, "Please fill required details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, newList.get(i).toString(), Toast.LENGTH_SHORT).show();
        spinner_data=newList.get(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
