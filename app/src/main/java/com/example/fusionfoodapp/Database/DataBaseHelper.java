package com.example.fusionfoodapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.fusionfoodapp.Model.OrderListModel;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String ORDER_TABLE = "ORDER_TABLE";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String MOBILE_NO = "MOBILE_NO";
    public static final String EMAIL = "EMAIL";
    public static final String ORDERED_FOOD = "ORDERED_FOOD";
    public static final String TOTAL = "TOTAL";
    public static final String ID = "ID";
    public DataBaseHelper(@Nullable Context context) {
        super(context,"orderlist.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String createTable="CREATE TABLE " + ORDER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CUSTOMER_NAME + " TEXT," + MOBILE_NO + " TEXT,"
                 + EMAIL + " TEXT," + ORDERED_FOOD + " TEXT ,"+ TOTAL + " TEXT )";
        sqLiteDatabase.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(OrderListModel orderListModel){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(CUSTOMER_NAME,orderListModel.getCustomerName());
        cv.put(MOBILE_NO,orderListModel.getMobileNo());
        cv.put(EMAIL,orderListModel.getEmail());
        cv.put(ORDERED_FOOD,orderListModel.getFoods());
        cv.put(TOTAL,orderListModel.getTotal());
        long insert = sqLiteDatabase.insert( ORDER_TABLE , null, cv);
        if(insert==-1){
            return false;
        }else{
            return true;
        }
    }
}
