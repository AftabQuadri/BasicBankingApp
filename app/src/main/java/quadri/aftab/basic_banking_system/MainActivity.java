package quadri.aftab.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toCustomerList(View view) {
        startActivity(new Intent(MainActivity.this,All_Customer_Activity.class));
    }

    public void transactions(View view) {
        startActivity(new Intent(MainActivity.this,Transaction_Activity.class));
    }

    public void toSingleTransaction(View view) {
        startActivity(new Intent(MainActivity.this,SingleTransaction.class));
    }

    public void toNewCustomer(View view) {
        startActivity(new Intent(MainActivity.this,Add_Customer.class));
    }
}