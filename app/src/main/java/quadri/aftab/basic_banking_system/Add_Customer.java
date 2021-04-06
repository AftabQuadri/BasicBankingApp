package quadri.aftab.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_Customer extends AppCompatActivity {
EditText customer_name,customer_email,customer_balance;
Button add_customer,delete_customer;
CustomerModel customerModel;
DatabaseHelper helper;
DatabaseHelper2 helper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__customer);
        customer_name=findViewById(R.id.name_customer);
        customer_email=findViewById(R.id.email_customer);
        customer_balance=findViewById(R.id.balance_cusomer);
        add_customer=findViewById(R.id.add_btn);
        delete_customer=findViewById(R.id.delete_btn);
        helper=new DatabaseHelper(this);
        helper2=new DatabaseHelper2(this);
        add_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(customer_name.getText().toString()) && !TextUtils.isEmpty(customer_email.getText().toString()) && !TextUtils.isEmpty(customer_balance.getText().toString())) {
                    String name = customer_name.getText().toString();
                    String email = customer_email.getText().toString();
                    String balance = customer_balance.getText().toString();
                    if (name != null && email != null && balance != null) {
                        customerModel = new CustomerModel(name, email, Integer.parseInt(balance));
                        boolean b = helper.addOne(customerModel);
                        String msg = "";
                        if (b) {
                            msg = "success";
                        } else {
                            msg = "fail";
                        }
                        Toast.makeText(Add_Customer.this, msg, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Customer.this,All_Customer_Activity.class));

                    } else {
                        Toast.makeText(Add_Customer.this, "You must fill all of the boxes", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Add_Customer.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }

        });
        delete_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(customer_name.getText().toString())) {
                    String name = customer_name.getText().toString();
                    boolean b2 = helper2.deleteOne(customer_name.getText().toString());
                    helper.deleteOne(customer_name.getText().toString());
                    if (b2) {
                        Toast.makeText(Add_Customer.this, "deleted entry " + customer_name.getText().toString(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Add_Customer.this, All_Customer_Activity.class));
                    } else {
                        Toast.makeText(Add_Customer.this, "cannot delete item", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Add_Customer.this, "Name is required", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}