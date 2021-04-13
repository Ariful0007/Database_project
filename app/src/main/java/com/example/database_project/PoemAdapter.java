package com.example.database_project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PoemAdapter extends RecyclerView.Adapter<PoemAdapter.myview> {
    List<Poem_model>data;

    public PoemAdapter(List<Poem_model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.poem_layout,parent,false);
        return new myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myview holder, final int position) {
        holder.poem_name_layout.setText(data.get(position).getPoemname());
        holder.poet_name_layout.setText(data.get(position).getPoetname());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String poem_name=data.get(position).getPoemname();
                final  String poet_name=data.get(position).getPoetname();
                final  String poem=data.get(position).getMainpoem();
                Intent intent1=new Intent(v.getContext(),Show_poem.class);
               intent1.putExtra("poem_name",poem_name);
                intent1.putExtra("poet_name",poet_name);
                intent1.putExtra("poem",poem);
               v.getContext().startActivity(intent1);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView poem_name_layout,poet_name_layout;

        public myview(@NonNull View itemView) {

            super(itemView);
            poem_name_layout=itemView.findViewById(R.id.poem_name_layout);
            poet_name_layout=itemView.findViewById(R.id.poet_name_layout);
        }
    }
}
