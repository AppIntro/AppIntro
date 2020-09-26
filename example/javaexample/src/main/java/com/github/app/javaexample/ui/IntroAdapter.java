package com.github.app.javaexample.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.app.javaexample.R;

import java.util.List;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.IntroAdapterViewHolder> {

    private List<IntroEntry> entryList;
    private Context ctx;

    public IntroAdapter(List<IntroEntry> entryList, Context ctx) {
        this.entryList = entryList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public IntroAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_intro,parent,false);
        return new IntroAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroAdapterViewHolder holder, int position) {
        final IntroEntry entry = entryList.get(position);
        holder.title.setText(entry.title);
        holder.description.setText(entry.description);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, entry.activityClass);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    public class IntroAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView button;

        public IntroAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
              title = itemView.findViewById(R.id.item_title);
              description = itemView.findViewById(R.id.item_description);
              button = itemView.findViewById(R.id.item_button);
        }
    }
}
