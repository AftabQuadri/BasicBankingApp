package quadri.aftab.basic_banking_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class All_Customer_Activity extends AppCompatActivity {
    ListView listView;
    DatabaseHelper helper;
    List<CustomerModel> list;
    List<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__customer_);
        listView=findViewById(R.id.listView);
        list=new ArrayList<CustomerModel>();
        helper=new DatabaseHelper(this);
        list.addAll(helper.getEveryone());
        arrayList=new ArrayList<>();
        arrayList.addAll(getArray(list));
        Bundle b=new Bundle();
        b.putSerializable("listarray",(Serializable)arrayList);
        Log.d("helper",helper.getEveryone().toString());
        if(list.isEmpty())
        {
            Log.d("khali","list");
            Toast.makeText(this, "khali List", Toast.LENGTH_SHORT).show();
        }
//        Log.d("listdata_name","listdata null hai "+ list.get(0).toString());
//        Log.d("listdata_email","listdata null hai but size"+list.size());
//        Log.d("listdata_amount","listdata null hai "+String.valueOf(list.get(0).getAmount()));
        CustomAdapter adapter=new CustomAdapter(this,list);
        listView.setAdapter(adapter);
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(All_Customer_Activity.this,SingleTransaction.class);
                intent.putExtra("name",list.get(i).getName());
                intent.putExtra("email",list.get(i).getEmail());
                intent.putExtra("amount",list.get(i).getAmount());
                intent.putExtras(b);
                intent.putExtra("index",i);
                startActivity(intent);

            }
        });

    }
    public List<String> getArray(List<CustomerModel> list)
    {
        List<String> arrayList0=new ArrayList<>();
        for (int i=0;i<list.size();i++)
        {
            arrayList0.add(list.get(i).getName());
        }
        return arrayList0;
    }
}