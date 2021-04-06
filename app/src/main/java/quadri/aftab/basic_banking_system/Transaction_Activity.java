package quadri.aftab.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Transaction_Activity extends AppCompatActivity {
ListView listView;
DatabaseHelper2 helper;
List<CustomerModel> list;
Button go_to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_);
        listView=findViewById(R.id.listView2);
        go_to=findViewById(R.id.all_customers);
        helper=new DatabaseHelper2(this);
        list=new ArrayList<>();
        list.addAll(helper.getEveryTransaction());
        CustomAdapter adapter =new CustomAdapter(this,list);
        listView.setAdapter(adapter);
        go_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Transaction_Activity.this,All_Customer_Activity.class));
            }
        });
    }
}