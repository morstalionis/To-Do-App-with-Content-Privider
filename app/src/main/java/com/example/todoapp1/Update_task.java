package com.example.todoapp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Update_task extends AppCompatActivity {
    Button btn_Color, Update_Btn,Update_Task_Color,Show_task_color;
    EditText txt_taskTitle, txt_taskDetail;
    TextView txt_modified;
    Class_Database  g;
    String Color_value;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        ActionBar actionBar = getSupportActionBar();
        String TID =getIntent().getStringExtra("TID");
        String My_Title =getIntent().getStringExtra("TITLE");
        String My_Details =getIntent().getStringExtra("DETAILS");
        String Last_Modify =getIntent().getStringExtra("MODIFY");
        String Modify_Color =getIntent().getStringExtra("MODIFY_COLOR");
        Color_value = Modify_Color;
//        Toast.makeText(Update_task.this, "Your Task ID is "+TID+" "+My_Title+" "+My_Details, Toast.LENGTH_SHORT).show();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        actionBar.setTitle("Update Task");

        txt_taskTitle = findViewById(R.id.input_text_taskTitle);
        txt_taskDetail = findViewById(R.id.input_text_taskDetails);
        txt_modified = findViewById(R.id.text_lastEdit);
        Update_Task_Color = findViewById(R.id.button_setColor);
        Show_task_color = findViewById(R.id.task_colorTV);

        txt_taskTitle.setSelection(txt_taskTitle.getText().length());
        txt_taskTitle.setText(My_Title);
        txt_taskDetail.setText(My_Details);
        txt_modified.setText("Last Update "+Last_Modify);
        Show_task_color.setBackgroundColor(Color.parseColor(Modify_Color));
//        holder.BtnTask_color.setBackgroundColor(Color.parseColor(String.valueOf(task_color.get(position))));

        Date src_date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        String modified = df.format(src_date);
        String date = df.format(src_date);

        btn_Color = findViewById(R.id.button_setColor);
        Update_Btn = findViewById(R.id.button_update);

        Update_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_taskTitle.getText().length()==0){
                    txt_taskTitle.setError("Please Add Title");
                    txt_taskTitle.requestFocus();
                }
                else if (txt_taskDetail.getText().length()==0){
                    txt_taskDetail.setError("Please Add Details");
                    txt_taskDetail.requestFocus();
                }

                else{

                    String TitleStr = txt_taskTitle.getText().toString();
                    String DetailsStr = txt_taskDetail.getText().toString();
                    Class_Database Update_db = new Class_Database(Update_task.this);

                   Update_db.p_updateData(TID,TitleStr,DetailsStr,Color_value);
                    finish();


                }}
        });
        btn_Color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Color_Picker();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Update_task.this);
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
                Show_task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();
            }
        });
        C2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 2 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#D500F9";
                Show_task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 3 Selected",Toast.LENGTH_SHORT).show();
                Color_value = "#2979FF";
                Show_task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 4 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#FFEA00";
                Show_task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 5 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#00E5FF";
                Show_task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });
        C6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Add_Task.this,"Color 6 Selected",Toast.LENGTH_SHORT).show();
                Color_value= "#F50057";
                Show_task_color.setBackgroundColor(Color.parseColor(Color_value));
                alertDialog.dismiss();

            }
        });


        alertDialog.show();
    }

}