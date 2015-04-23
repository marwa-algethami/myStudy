

/**
 * Created by TOSHIBA on 14/04/15.
 */
package com.example.toshiba.mystudy;

public class Course {
    public static final String TABLE = "Course";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "course";
    public static final String KEY_assignment = "Assignment";
    public static final String KEY_email = "email";
    public static final String KEY_teacher = "Teacher";
    public static final String KEY_end = "Due";

    // property help us to keep data
    public int course_ID;
    public String courseName;
    public String assignmentName;
    public  String teacher;
    public String email;
    public int end;


}