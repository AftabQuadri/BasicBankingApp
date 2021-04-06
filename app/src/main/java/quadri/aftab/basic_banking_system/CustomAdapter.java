package quadri.aftab.basic_banking_system;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static quadri.aftab.basic_banking_system.R.layout.custom_list;

public class CustomAdapter extends ArrayAdapter<CustomerModel> {
    private final Context context;
    List<CustomerModel> list;
    String customer_name;
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         convertView=inflater.inflate(custom_list,null,true);
        TextView name= convertView.findViewById(R.id.unique_name);
        name.setText(list.get(position).getName());
        Log.d("name_cust","you are fucked "+list.get(position).getName());
        TextView email= convertView.findViewById(R.id.unique_email);
        email.setText(list.get(position).getEmail());
        TextView amount= convertView.findViewById(R.id.unique_amount);
        amount.setText(String.valueOf(list.get(position).getAmount()));
        Log.d("amount_amount",String.valueOf(list.get(position).getAmount()));
        return convertView;
    }

    public CustomAdapter(@NonNull Context context, List<CustomerModel> list1) {
        super(context,custom_list, list1);
        this.context=context;
        list=new ArrayList<>();
        this.list.addAll(list1);
//

    }
}
