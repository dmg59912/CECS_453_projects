package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper
{

    //setting names for our tables
    public static final String DATABASE_NAME = "DataUsers.db";
    public static final String USERS_TABLE_CRED = "user_credentials";
    public static final String USERS_TABLE_USER = "user";
    public static final String USERS_TABLE_ACCOUNT = "account";
    public static final String USER_TABLE_EXPENSES = "expenses";


    //setting names for our columns
    public static final String CRED_ID = "user_id";
    public static final String CRED_USERNAME = "username";
    public static final String CRED_PASS = "password";
    public static final String CRED_EMAIL = "email";

    public static final String USER_ID = "user_id";
    public static final String USER_ACCOUNT = "account_id";
    public static final String USER_NAME = "name";

    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_NUM = "account_number";
    public static final String ACCOUNT_SAVING = "annual_saving";
    public static final String ACCOUNT_INCOME = "annual_income";


    public static final String EXPENSES = "account_number";
    public static final String EXPENSES_EXP = "expenses";

    public DBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String cred,user_info, account_id,expenses;

        expenses = "create table " + USER_TABLE_EXPENSES +
                "(account_number integer PRIMARY KEY, expenses decimal)";
        db.execSQL(expenses);


        account_id = "create table " + USERS_TABLE_ACCOUNT +
                "(account_id integer PRIMARY KEY, account_number integer, annual_saving decimal, annual_income decimal, FOREIGN KEY(account_number) REFERENCES " + USER_TABLE_EXPENSES + "(account_number) )";
        db.execSQL(account_id);



        user_info ="create table " + USERS_TABLE_USER +
                "(user_id integer PRIMARY KEY, account_id integer, name text, FOREIGN KEY(account_id) REFERENCES " + USERS_TABLE_ACCOUNT + "(account_id) )";
        db.execSQL(user_info);


        cred = "create table "   + USERS_TABLE_CRED +
                "(user_id integer  PRIMARY KEY AUTOINCREMENT , username text ,password text, email text, FOREIGN KEY (user_id) REFERENCES "+ USERS_TABLE_USER + "(user_id) )";
        db.execSQL(cred);







    }

    public void insertUser(String user_name, String password,String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(checkUserValidation(user_name) == false) {
            values.put(CRED_USERNAME, user_name);
            values.put(CRED_PASS, password);
            values.put(CRED_EMAIL, email);

            db.insert(USERS_TABLE_CRED, null, values);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"User Name Already Exist" ,Toast.LENGTH_LONG).show();
        }

    }

    private Context getApplicationContext() {
        return null;
    }

    //log user in if credentials are valid
    public boolean logIn_check(String username, String password)
    {
        boolean no_matching_userdata = false;
        String sql;
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "select username,password FROM " + USERS_TABLE_CRED;
        Cursor curs = db.rawQuery(sql,null);

        curs.moveToFirst();

        while (!curs.isAfterLast())
        {
            String Check_user = curs.getString(curs.getColumnIndex(DBHelper.CRED_USERNAME));
            String Check_password = curs.getString(curs.getColumnIndex(DBHelper.CRED_PASS));

            if(Check_user.compareTo(username) == 0 && Check_password.compareTo(password) == 0)
                return true;
            curs.moveToNext();


        }
        return no_matching_userdata;
    }

    //check if user name exist in the data when signing  up
    public boolean checkUserValidation(String user_name)
    {
        boolean account_exist = false;
        String sql;
        SQLiteDatabase db = this.getReadableDatabase();

        sql = "select * from " + USERS_TABLE_CRED;
        Cursor curs = db.rawQuery(sql,null);

        curs.moveToFirst();



     while(!curs.isAfterLast())
     {
         String Check_user = curs.getString(curs.getColumnIndex(DBHelper.CRED_USERNAME));
         if(Check_user.compareTo(user_name) == 0)
         {
             account_exist = true;
             break;
         }
         curs.moveToNext();
     }
     curs.close();
        return account_exist;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_version,int new_version)
    {

    }


}
