package com.example.toshiba.mystudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class FirstActivity extends Activity   {

    private Spinner spinner;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_first);
            spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.semester_array,android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);}

    public void onClick(View view) {
        int position = spinner.getSelectedItemPosition();
        Intent intent = null;
        switch (position) {
            case 0:
                intent = new Intent(this,SecondActivity.class);
                break;
            case 1:
                intent = new Intent(this, SecondActivity1.class);

        }

        if (intent != null) {
            startActivity(intent);
        }

    }


}