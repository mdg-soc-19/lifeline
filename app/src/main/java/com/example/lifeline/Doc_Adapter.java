package com.example.lifeline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Doc_Adapter extends RecyclerView.Adapter<Doc_Adapter.MyViewHolder> {


    List<Doctor> mylist;
    Context context;

    public Doc_Adapter(List<Doctor> mylist, Context context) {
        this.mylist = mylist;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Doctor doctor = mylist.get(position);
        holder.name.setText(doctor.getDoc_name());
        holder.graduate.setText(doctor.getDoc_graduate());
        holder.dpt.setText(doctor.getDoc_dpt());
        holder.image.setImageDrawable(context.getResources().getDrawable(doctor.getImg()));

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,graduate,dpt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            graduate = itemView.findViewById(R.id.graduate);
            dpt = itemView.findViewById(R.id.dpt);



        }
    }
}
