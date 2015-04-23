package com.example.toshiba.mystudy;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class SecondActivity1 extends ListActivity  implements android.view.View.OnClickListener{

    Button btnAdd,btnGetAll;
    TextView course_Id;

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.btnAdd)) {

            Intent intent = new Intent(this, CourseDetail.class);
            intent.putExtra("course_Id", 0);
            startActivity(intent);

        } else {


            CourseRepo repo = new CourseRepo(this);

            ArrayList<HashMap<String, String>> courseList = repo.getStudentList();
            if (courseList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        course_Id = (TextView) view.findViewById(R.id.course_Id);
                        String courseId = course_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), CourseDetail.class);
                        objIndent.putExtra("course_Id", Integer.parseInt(courseId));
                        startActivity(objIndent);
                    }
                });

                ListAdapter adapter = new SimpleAdapter(SecondActivity1.this, courseList, R.layout.view_course_entry1, new String[]{"id", "name"}, new int[]{R.id.course_Id, R.id.course_name});
                setListAdapter(adapter);
            } else {
                Toast.makeText(this, "No course!", Toast.LENGTH_SHORT).show();
            }

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity1);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);


    }

    }
