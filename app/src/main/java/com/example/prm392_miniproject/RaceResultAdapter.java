package com.example.prm392_miniproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_miniproject.Object.Runner;
import java.util.List;

public class RaceResultAdapter extends RecyclerView.Adapter<RaceResultAdapter.ViewHolder> {

    private final List<Runner> runners;

    public RaceResultAdapter(List<Runner> runners) {
        this.runners = runners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_race_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Runner runner = runners.get(position);
        holder.bind(runner, position + 1);
    }

    @Override
    public int getItemCount() {
        return runners.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPosition;
        private final TextView tvName;
        private final TextView tvTime;
        private final ImageView ivHorse;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.tvPosition);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivHorse = itemView.findViewById(R.id.ivHorse);
        }

        void bind(Runner runner, int position) {
            tvPosition.setText(String.valueOf(position));
            tvName.setText(runner.getName());
            tvTime.setText(String.format("%.2f giây", runner.getFinishTime()));
            ivHorse.setImageResource(runner.getImage());
        }
    }
}