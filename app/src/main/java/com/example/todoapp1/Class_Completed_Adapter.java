package com.example.todoapp1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Class_Completed_Adapter extends RecyclerView.Adapter<Class_Completed_Adapter.MyViewHolder> {

    private final Class_Interface classInterface;

    Context context;
    Activity activity;
    ArrayList task_id, task_title, task_details, task_date, task_mod,task_color;

    Class_Completed_Adapter(Context context,
                            ArrayList task_id,
                            ArrayList task_title,
                            ArrayList task_date,
                            ArrayList task_mod,
                            ArrayList task_details,
                            ArrayList <String>task_color,
                            Class_Interface classInterface){

        this.context = context;
        this.task_id = task_id;
        this.task_title = task_title;
        this.task_date = task_date;
        this.task_mod = task_mod;
        this.task_details = task_details;
        this.task_color = task_color;
        this.classInterface = classInterface;

    }

    @NonNull
    @Override
    public Class_Completed_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layout_completed, parent, false);
        return new MyViewHolder(view, classInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Class_Completed_Adapter.MyViewHolder holder, int position) {
        //holder.task_id_txt.setText(String.valueOf(task_id.get(position)));
        holder.task_title_txt.setText(String.valueOf(task_title.get(position)));
        //holder.task_details_txt.setText(String.valueOf(task_details.get(position)));
        holder.task_date_txt.setText(String.valueOf(task_date.get(position)));
//        holder.BtnTask_color.setText((CharSequence) task_color.get(position));
//        Toast.makeText(getContext(),""+task_color,Toast.LENGTH_SHORT).show();
        holder.BtnTask_color.setBackgroundColor(Color.parseColor(String.valueOf(task_color.get(position))));

    }

    @Override
    public int getItemCount() {
        return task_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView task_id_txt, task_title_txt, task_date_txt, task_modified_txt, task_details_txt,BtnTask_color;

        public MyViewHolder(@NonNull View itemView, Class_Interface classInterface) {
            super(itemView);
            task_title_txt = itemView.findViewById(R.id.text_TaskName);
            task_date_txt = itemView.findViewById(R.id.text_dateCompleted);
            BtnTask_color = itemView.findViewById(R.id.button_TaskType);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (classInterface != null){
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            classInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    public Context getContext() {
        return activity;
    }

    public void editItem(int position) {

    }

}
