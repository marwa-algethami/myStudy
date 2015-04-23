package com.example.toshiba.mystudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TOSHIBA on 14/04/15.
 */
public class CourseRepo {

    private DBHelper dbHelper;
    private Context myContext;



    public CourseRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public int insert(Course course) {
        long course_Id = 0;

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //if(db!=null){\

        ContentValues values = new ContentValues();


        values.put(Course.KEY_end , course.end);
        values.put(Course.KEY_email, course.email);
        values.put(Course.KEY_name, course.courseName);
        values.put(Course.KEY_assignment, course.assignmentName);
        values.put(Course.KEY_teacher, course.teacher);

        // Inserting Row
         //db.insert(Course.TABLE, null, values);
          /*  if(course_Id != 0){
                Toast.makeText(myContext, "Record added successfully",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(myContext, "Record added failed...! ",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(myContext, "could Not connected database..!!! ",Toast.LENGTH_LONG).show();
        }
*/

       // db.close(); // Closing database connection
       course_Id = db.insert(Course.TABLE, null, values);


        db.close();
        return  (int) course_Id;

    }


    public void delete(int course_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Course.TABLE, Course.KEY_ID + "= ?", new String[]{String.valueOf(course_Id)});
        db.close(); // Closing database connection
    }

    public void update(Course course) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Course.KEY_end, course.end);
        values.put(Course.KEY_email, course.email);
        values.put(Course.KEY_name, course.courseName);
        values.put(Course.KEY_assignment, course.assignmentName);
        values.put(Course.KEY_teacher, course.teacher);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Course.TABLE, values, Course.KEY_ID + "= ?", new String[]{String.valueOf(course.course_ID)});
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Course.KEY_ID + "," +
                Course.KEY_name + "," +
                Course.KEY_assignment + "," +
                Course.KEY_teacher + "," +
                Course.KEY_email + "," +
                Course.KEY_end  + " FROM " + Course.TABLE ;

        //Course course = new Course();
        ArrayList<HashMap<String, String>> courseList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery( selectQuery , null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> course = new HashMap<String, String>();
                course.put("id", cursor.getString(cursor.getColumnIndex(Course.KEY_ID)));
                course.put("name", cursor.getString(cursor.getColumnIndex(Course.KEY_name)));
                course.put("Assignment",cursor.getString(cursor.getColumnIndex(Course.KEY_assignment)));

                courseList.add(course);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return courseList;

    }

 //////////////////////////////////////////
    public Course getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Course.KEY_ID + "," +
                Course.KEY_name + "," +
                Course.KEY_assignment + "," +
                Course.KEY_teacher + "," +
                Course.KEY_email + "," +
                Course.KEY_end  +
                " FROM " + Course.TABLE
                + " WHERE " +
                Course.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Course course = new Course();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                course.course_ID =cursor.getInt(cursor.getColumnIndex(Course.KEY_ID));
                course.courseName =cursor.getString(cursor.getColumnIndex(Course.KEY_name));
                course.assignmentName  =cursor.getString(cursor.getColumnIndex(Course.KEY_assignment));
                course.email  =cursor.getString(cursor.getColumnIndex(Course.KEY_email));
                course.teacher  =cursor.getString(cursor.getColumnIndex(Course.KEY_teacher));
                course.end =  cursor.getInt(cursor.getColumnIndex(Course.KEY_end));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return course;
    }



}
