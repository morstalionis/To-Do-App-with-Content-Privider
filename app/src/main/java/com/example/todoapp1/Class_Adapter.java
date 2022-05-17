package com.example.todoapp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Class_Adapter extends RecyclerView.Adapter<Class_Adapter.MyViewHolder> {

    private final Class_Interface classInterface;

    Context context;
    Activity activity;
    ArrayList task_id, task_title, task_details, task_date, task_mod,task_color;

    Class_Adapter(Context context,
                  ArrayList task_id,
                  ArrayList task_title,
                  ArrayList task_date,
                  ArrayList task_mod,
                  ArrayList task_details,
                  ArrayList<String> task_color,
                  Class_Interface classInterface){

        this.context = context;
        this.task_id = task_id;
        this.task_title = task_title;
        this.task_date = task_date;
        this.task_mod = task_mod;
        this.task_details = task_details;
        this.task_color = task_color;
        this.classInterface = classInterface;
        String TID;
    }

    @NonNull
    @Override
    public Class_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_pending, parent, false);

        return new MyViewHolder(view, classInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Class_Adapter.MyViewHolder holder, int position) {

//        holder.task_id_txt.(String.valueOf(task_id.get(position)));
        holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
        //holder.task_details_txt.setText(String.valueOf(task_details.get(position)));
        holder.task_date_txt.setText(String.valueOf(task_date.get(position)));
        holder.task_modified_txt.setText(String.valueOf(task_mod.get(position)));
//        Toast.makeText(activity,"Color Value is ",Toast.LENGTH_LONG).show();
//        holder.BtnTask_color.setText(String.valueOf(task_color.get(position)));
        holder.BtnTask_color.setBackgroundColor(Color.parseColor(String.valueOf(task_color.get(position))));

        String TID = String.valueOf(task_id.get(position));
        String Title = String.valueOf(task_title.get(position));
        String Details = String.valueOf(task_details.get(position));
        String Modified_date = String.valueOf(task_mod.get(position));
        String Modify_Color = String.valueOf(task_color.get(position));

//        String Title = String.valueOf(task_title.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Update_task.class);
//                String strName = "123456";
                intent.putExtra("TID", TID);
                intent.putExtra("TITLE", Title);
                intent.putExtra("DETAILS", Details);
                intent.putExtra("MODIFY", Modified_date);
                intent.putExtra("MODIFY_COLOR", Modify_Color);
                view.getContext().startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView task_id_txt, task_title_txt, task_date_txt, task_modified_txt, task_details_txt,BtnTask_color;


        public MyViewHolder(@NonNull View itemView, Class_Interface classInterface) {
            super(itemView);

//            String TID = String.valueOf(task_id.get(getPosition()));
            task_title_txt = itemView.findViewById(R.id.text_TaskName);
            task_date_txt = itemView.findViewById(R.id.text_dateCompleted);
            task_modified_txt = itemView.findViewById(R.id.text_dateMod);
            BtnTask_color = itemView.findViewById(R.id.button_TaskType);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    int TID = (int) task_id.get(getPosition());
//                    Intent intent = new Intent(view.getContext(),Update_task.class);
//
//                    view.getContext().startActivity(intent);

                }
            });
        }
    }

    public Context getContext() {
        return activity;
    }

    public void editItem(int position) {

    }
    public void Color_Picker() {

    }
}
