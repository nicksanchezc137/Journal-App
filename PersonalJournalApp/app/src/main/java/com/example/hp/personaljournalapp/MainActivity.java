package com.example.hp.personaljournalapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
FloatingActionButton fab;
    DataBaseHelper dh;


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dh = new  DataBaseHelper(MainActivity.this);

        setContentView(R.layout.activity_main);
fab = (FloatingActionButton)findViewById(R.id.fab);

fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

Intent i = new Intent(MainActivity.this,Notes.class);
startActivity(i);
    }
});


    }
    @Override
    protected  void onResume(){
        super.onResume();
        try{
            clearViews();
            updateInfo();
        }catch (Exception e){

        }
        Toast.makeText(MainActivity.this,"Calling on resume",Toast.LENGTH_SHORT).show();


    }
    void clearViews(){
        ViewGroup main = (ViewGroup) findViewById(R.id.container);
        main.removeAllViews();
    }
    void addJournal(String title, String info,String date){

            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.fragment_part1, null);
            LinearLayout ll = (LinearLayout)v;
           TextView title_text = (TextView)v.findViewById(R.id.title_text);
            TextView info_text = (TextView)v.findViewById(R.id.info_text);
            TextView date_text = (TextView)v.findViewById(R.id.date_text);



            ViewGroup main = (ViewGroup) findViewById(R.id.container);
            main.addView(v, 0);

            title_text.setText(title);
        info_text.setText(info);
        date_text.setText(date);


    }
    void updateInfo(){
        Cursor res = dh.getAllData();
        if (res!= null && res.getCount()>0){
            while (res.moveToNext()){
                addJournal(res.getString(1),res.getString(2),res.getString(3));
            }
        }
    }




}
