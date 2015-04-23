package com.example.toshiba.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CourseDetail1 extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose, btnDate;
    EditText editTextName;
    EditText editTextAssignmentName;
    EditText editTextTeacherName;
    EditText editTextEmail;
    EditText editTextDate;
    private int _Course_Id=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail1);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnDate = (Button) findViewById(R.id.buttonDate);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAssignmentName = (EditText) findViewById(R.id.editTextAssignmentName);
        editTextTeacherName = (EditText) findViewById(R.id.editTextTeacherName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextDate = (EditText) findViewById(R.id.editTextDate);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnDate.setOnClickListener(this);



        _Course_Id =0;
        Intent intent = getIntent();
        _Course_Id =intent.getIntExtra("course_Id", 0);
        CourseRepo repo = new CourseRepo(this);
        Course course1 = new Course();
        course1 = repo.getStudentById(_Course_Id);



        /////////////
        // public  void initView(){
        //Course course = new Course();
        //CourseRepo repo = new CourseRepo(this);
        // course = repo.getStudentById(_Course_Id);

        editTextDate.setText(String.valueOf (course1.end));

        // editTextDate.setInputType(course.end);
        //editTextAge.setText(String.valueOf(student.age));
        editTextName.setText(course1.courseName);
        editTextAssignmentName.setText(course1.assignmentName);
        editTextTeacherName.setText(course1.teacher);
        editTextEmail.setText(course1.email);
    }


    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            CourseRepo repo = new CourseRepo(this);
            Course course = new Course();
            course.end= Integer.parseInt(editTextDate.getText().toString());
            course.email=editTextEmail.getText().toString();
            course.courseName=editTextName.getText().toString();
            course.teacher = editTextTeacherName.getText().toString();
            course.assignmentName = editTextAssignmentName.getText().toString();
            course.course_ID=_Course_Id;

            if (_Course_Id == 0){
                _Course_Id = repo.insert(course);
                Toast.makeText(this, "New Course Insert", Toast.LENGTH_SHORT).show();
            }else{

                repo.update(course);
                Toast.makeText(this,"Course Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            CourseRepo repo = new CourseRepo(this);
            repo.delete(_Course_Id);
            Toast.makeText(this, "Course Record Deleted", Toast.LENGTH_SHORT).show();
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        } else if(view== findViewById(R.id.buttonDate)){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);}


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
