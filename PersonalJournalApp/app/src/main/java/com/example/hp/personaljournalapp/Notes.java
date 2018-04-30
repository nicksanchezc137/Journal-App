package com.example.hp.personaljournalapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Notes extends AppCompatActivity {
    DataBaseHelper dh;
    Date date = new Date();
    Button save;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dh = new DataBaseHelper(Notes.this);


        save = (Button)findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText title = (EditText)findViewById(R.id.title);
                EditText info = (EditText)findViewById(R.id.info);


                boolean result = dh.insertData(title.getText().toString(),info.getText().toString(),dateFormat.format(date).toString());
                if(result == true){
                    Toast.makeText(Notes.this, "Data has been saved", Toast.LENGTH_SHORT);

                }else{
                    Toast.makeText(Notes.this, "Data has not been saved", Toast.LENGTH_SHORT);
                }
                Intent i = new Intent(Notes.this,MainActivity.class);

                startActivity(i);
                MainActivity ma = new MainActivity();

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
