/**
 * Created by TOSHIBA on 14/04/15.
 */
package com.example.toshiba.mystudy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 17;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_COURSE = "CREATE TABLE " + Course.TABLE + "("
                + Course.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Course.KEY_name + " TEXT, "
                + Course.KEY_assignment + " TEXT, "
                + Course.KEY_teacher + " TEXT, "
                + Course.KEY_email + " TEXT, "
                + Course.KEY_end    +  "   INTEGER   " + "); " ;




        db.execSQL(CREATE_TABLE_COURSE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
       //db.execSQL("DROP TABLE IF EXISTS " + Course.TABLE);


       // if (oldVersion < 17) {
           //     final String ALTER_TBL =
                       // "ALTER TABLE " + Course.TABLE +
                        //        " ADD COLUMN "  +  Course.KEY_end  +  " INTEGER;";
                  //      db.execSQL(ALTER_TBL);
           // }//////


        // Create tables again
      // onCreate(db);

    }

}
