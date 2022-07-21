package com.example.tasbeehapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TasbeehShowRecyclerViewAdapter extends RecyclerView.Adapter<TasbeehShowRecyclerViewAdapter.ViewHolder>{

    ArrayList<Tasbeeh> tasbeehs = new ArrayList<>();
    Context context;

    public TasbeehShowRecyclerViewAdapter(ArrayList<Tasbeeh> tasbeehs, Context context1) {
        this.tasbeehs = tasbeehs;
        this.context = context1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasbeeh_list_layout,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tasbeeh tasbeeh = tasbeehs.get(position);
        holder.tasbeehImg.setImageResource(tasbeeh.getImage());
    }

    @Override
    public int getItemCount() {
        return tasbeehs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView tasbeehImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tasbeehImg = itemView.findViewById(R.id.tasbeehimage_imageview);
            tasbeehImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int postion = this.getAdapterPosition();
            Tasbeeh tasbeeh = tasbeehs.get(postion);
            DatabaseQueryClass databaseQueryClass = new DatabaseQueryClass(tasbeehImg.getContext());
            tasbeeh = databaseQueryClass.getTasbeehByImageId(String.valueOf(tasbeeh.getImage()));
            Intent intent = new Intent(context,TasbeehMainActivity.class);
            intent.putExtra("tasbeehimage", String.valueOf(tasbeeh.getImage()));
            intent.putExtra("tasbeehcount", tasbeeh.getCount());
            context.startActivity(intent);
        }
    }
}
