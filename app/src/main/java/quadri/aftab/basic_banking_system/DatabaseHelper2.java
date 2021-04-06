package quadri.aftab.basic_banking_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper2 extends SQLiteOpenHelper {
    public static final String TRANSACTION_TABLE = "TRANSACTION_TABLE";
    public static final String COLUMN_SENDER = "SENDER";
    public static final String COLUMN_RECEIVER = "RECEIVER";
    public static final String COLUMN_CUSTOMER_BALANCE = "SENT_AMOUNTINT";

    public DatabaseHelper2(@Nullable Context context) {
        super(context, "transaction.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TRANSACTION_TABLE+"(" +COLUMN_SENDER +" TEXT, "+COLUMN_RECEIVER+" TEXT, "+COLUMN_CUSTOMER_BALANCE+ "INT)");

    }
    public List<CustomerModel> getEveryTransaction(){
        List<CustomerModel> returnList=new ArrayList<>();
        String queryString="SELECT * FROM "+TRANSACTION_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery(queryString,null);
        if(cursor.moveToFirst())
        {
            do{
                String name=cursor.getString(0);
                String email=cursor.getString(1);
                int amount=cursor.getInt(2);
                CustomerModel customerModel=new CustomerModel(name,email,amount);
                returnList.add(customerModel);
                Log.d("geteveryone_name",""+customerModel.getName());
                Log.d("geteveryone_email",""+customerModel.getEmail());
                Log.d("geteveryone_balance",""+String.valueOf(customerModel.getAmount()));
            }while (cursor.moveToNext());
        }
        else {
            Log.d("emppty cursor",cursor.toString());
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public boolean addOneTransaction(CustomerModel customerModel)
    {
        //this is basically inserting data into database
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_SENDER,customerModel.getName());
        cv.put(COLUMN_RECEIVER,customerModel.getEmail());
        cv.put(COLUMN_CUSTOMER_BALANCE,customerModel.getAmount());
        long insert1 =database.insert(TRANSACTION_TABLE,null,cv);
        if(insert1== -1)
        {
            return false;
        }
        else
        return true;

//        Cursor c=database.rawQuery("SELECT * FROM "+TRANSACTION_TABLE+" WHERE "+null,null);
//        String[] arr=c.getColumnNames();
//        Log.d("column2", arr[2]); to get column names from a db table
          }
    public boolean deleteOne(String string)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        int del= database.delete(TRANSACTION_TABLE,"SENDER=?",new String[]{string});
        Log.d("del",""+del);
        if(del== -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
