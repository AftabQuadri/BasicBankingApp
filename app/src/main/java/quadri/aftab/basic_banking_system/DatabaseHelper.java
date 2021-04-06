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

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_BALANCE = "CUSTOMER_BALANCE";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList=new ArrayList<>();
        String queryString="SELECT * FROM "+CUSTOMER_TABLE;
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
    public CustomerModel getSingleData(String string)
    {
        CustomerModel customerModel1 = null;
                String query;
        Cursor cursor=null;
        SQLiteDatabase  database=this.getReadableDatabase();
        if (string!=null) {
            query = "SELECT * FROM "+ CUSTOMER_TABLE +" WHERE  CUSTOMER_NAME ='"+string+"'";
           cursor= database.rawQuery(query, null);
            if(cursor.moveToFirst()) {
                do {
                    String first=cursor.getString(1);
                    String second=cursor.getString(2);

                    customerModel1=new CustomerModel(string,first,Integer.parseInt(second));
                }while (cursor.moveToNext());
            }
        }
        else
        {
            Log.d("null string","nalla"+string);
        }
        return customerModel1;
    }
    public boolean deleteOne(String string)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        int del= database.delete(CUSTOMER_TABLE,"CUSTOMER_NAME=?",new String[]{string});
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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT," + COLUMN_CUSTOMER_BALANCE+ " INT)";
        sqLiteDatabase.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS "+CUSTOMER_TABLE);
        onCreate(database);
    }
    public boolean addOne(CustomerModel customerModel)
    {
        //this is basically inserting data into database
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME,customerModel.getName());
        cv.put(COLUMN_CUSTOMER_EMAIL,customerModel.getEmail());
        cv.put(COLUMN_CUSTOMER_BALANCE,customerModel.getAmount());
        long insert1 =database.insert(CUSTOMER_TABLE,null,cv);
        if(insert1== -1)
        {

            return false;
        }
        else
            return true;
    }
    public boolean updateOne(CustomerModel customerModel)
    {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CUSTOMER_NAME,customerModel.getName());
        cv.put(COLUMN_CUSTOMER_EMAIL,customerModel.getEmail());
        cv.put(COLUMN_CUSTOMER_BALANCE,customerModel.getAmount());
        SQLiteDatabase database=this.getWritableDatabase();
        int update = database.update(CUSTOMER_TABLE,cv,"CUSTOMER_NAME=?",new String[]{customerModel.getName()});
        if (update== -1)
        {
            return false;
        }
        else
        {

            return true;
        }
    }
    public void deleteDatabase(Context customerModel)
    {
        customerModel.deleteDatabase("customer.db");
    }
    public boolean deleteOne(CustomerModel customerModel)
    {
        SQLiteDatabase database=this.getWritableDatabase();
         int del= database.delete(CUSTOMER_TABLE,"CUSTOMER_NAME=?",new String[]{customerModel.getName()});
         if(del== -1)
         {
             return false;
         }
         else
         {
             return true;
         }
    }

}
