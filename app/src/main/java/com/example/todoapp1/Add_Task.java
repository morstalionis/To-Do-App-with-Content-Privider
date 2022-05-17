package com.example.todoapp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Add_Task extends AppCompatActivity {

    Button btn_Color, btn_Save,task_color,btn_calendar;
    EditText txt_taskTitle, txt_taskDetail;
    TextView txt_modified;
    String Color_value;
    Boolean IsColorSelected = false;
    private Dialog progressDialoge;
    private long timeLeft;
    private  String Time;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        progressDialoge = new Dialog(Add_Task.this);
        progressDialoge.setContentView(R.layout.dialog_layout);
        progressDialoge.setCancelable(false);
        progressDialoge.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        progressDialoge.show();

        Cursor cursor = getContentResolver().query(CalendarContract.Events.CONTENT_URI,
                null, null, null, null);
        btn_calendar = findViewById(R.id.button_setColor2);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);

        txt_taskTitle = findViewById(R.id.input_text_taskTitle);
        txt_taskDetail = findViewById(R.id.input_text_taskDetails);
       task_color = findViewById(R.id.task_colorTV2);

        Date src_date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        String modified = df.format(src_date);
        String date = df.format(src_date);



        btn_Color = findViewById(R.id.button_setColor);
        btn_Save = findViewById(R.id.button_save);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_taskTitle.getText().length()==0){
                    txt_taskTitle.setError("Please Add Title");
                    txt_taskTitle.requestFocus();
                }
                else if (txt_taskDetail.getText().length()==0){
                    txt_taskDetail.setError("Please Add Details");
                    txt_taskDetail.requestFocus();
                }

                else if (IsColorSelected==false){
                    Toast.makeText(Add_Task.this,"Please Select Task Color First",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                Class_Database add_db = new Class_Database(Add_Task.this);
                add_db.p_add_task(txt_taskTitle.getText().toString().trim(),
                                    date.toString().trim(),
                                    modified.toString().trim(),
                                    txt_taskDetail.getText().toString().trim(),
                                    Color_value.toString().trim());
                finish();
            }}
        });
        btn_Color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Color_Picker();

            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.app.AlertDialog.Builder dialognew = new android.app.AlertDialog.Builder(Add_Task.this);
                dialognew.setTitle("Import Task");
                dialognew.setMessage("Import Events from Calendar?");
                dialognew.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        while(cursor.moveToNext()){
                            if (cursor!=null){
                                progressDialoge.show();
                                int id_1 = cursor.getColumnIndex(CalendarContract.Events._ID);
                                int title = cursor.getColumnIndex(CalendarContract.Events.TITLE);
                                int desc = cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION);

                                String id_val = cursor.getString(id_1);
                                String event_title = cursor.getString(title);
                                String event_desc = cursor.getString(desc);
                                String color = "#D500F9";

                                Class_Database add_db = new Class_Database(Add_Task.this);
                                add_db.p_add_task_cal(event_title.toString().trim(), date.toString().trim(), modified.toString().trim(), event_desc.toString().trim(),color.toString().trim());
                                dialogInterface.dismiss();
                                startTimer();

                            }else{
//                                progressDialoge.dismiss();
                                break;
                            }

                        }
                    }
                });
                dialognew.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                dialognew.create().show();

            }

        });

    }

    public boolean onOptionsItemSelected(MenuItem back){
        switch (back.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(back);

    }
    private void Color_Picker(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_Task.this);
        builder.setCancelable(false);
        View view = getLayoutInflater().inflate(R.layout.alertdialouge_layout,null);
        Button cancel = view.findViewById(R.id.Cancel);

        Button C1 = view.findViewById(R.id.c1);
        Button C2 = view.findViewById(R.id.c2);
        Button C3 = view.findViewById(R.id.c3);
        Button C4 = view.findViewById(R.id.c4);
        Button C5 = view.findViewById(R.id.c5);
        Button C6 = view.findViewById(R.id.c6);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        C1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 1 Selected",Toast.LENGTH_SHORT).show();
                Color_value = "#00E676";
                IsColorSelected = true;
                task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();
            }
        });
        C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 2 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#D500F9";
                IsColorSelected = true;
                task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 3 Selected",Toast.LENGTH_SHORT).show();
                Color_value = "#2979FF";
                IsColorSelected = true;
                task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 4 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#FFEA00";
                IsColorSelected = true;
                task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 5 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#00E5FF";
                IsColorSelected = true;
                task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 6 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#F50057";
                IsColorSelected = true;
                task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }
    private void startTimer(){
        progressDialoge.show();
        long total_time = 8*1000;

        timer = new CountDownTimer(total_time,1000) {
            @Override
            public void onTick(long remaining_Time) {
                timeLeft = remaining_Time;

                Time = String.format("%02d:%02d sec ",
                        TimeUnit.MILLISECONDS.toMinutes(remaining_Time),
                        TimeUnit.MILLISECONDS.toSeconds(remaining_Time)-
                                TimeUnit.MINUTES.toSeconds( TimeUnit.MILLISECONDS.toMinutes(remaining_Time)));
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(Add_Task.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                progressDialoge.dismiss();



            }
        };
        timer.start();


    }
}
