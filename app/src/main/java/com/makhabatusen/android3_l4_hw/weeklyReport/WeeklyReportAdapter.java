package com.makhabatusen.android3_l4_hw.weeklyReport;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makhabatusen.android3_l4_hw.databinding.WeeklyReportItemBinding;
import com.makhabatusen.android3_l4_hw.model.WeeklyReport;

import java.util.List;

public class WeeklyReportAdapter extends RecyclerView.Adapter<WeeklyReportAdapter.WeeklyHolder> {

    private final List<WeeklyReport> list;

    public WeeklyReportAdapter(List<WeeklyReport> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public WeeklyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        WeeklyReportItemBinding ui = WeeklyReportItemBinding.inflate(LayoutInflater.from( parent.getContext()), parent,false);
        return new WeeklyHolder(ui);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class WeeklyHolder extends RecyclerView.ViewHolder {
        private final WeeklyReportItemBinding ui;

        public WeeklyHolder(@NonNull WeeklyReportItemBinding ui) {
            super(ui.getRoot());
            this.ui = ui;
        }

        public void onBind(WeeklyReport info) {
            ui.tvWeekDay.setText(info.getDay());
            ui.tvMaxTempWeek.setText(info.getMaxDegree());
            ui.tvMinTempWeek.setText(info.getMinDegree());
            ui.ivWeekDay.setImageResource(info.getWeatherCondition());
        }
    }
}
