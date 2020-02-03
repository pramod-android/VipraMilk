package com.mywork.vipramilk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.MilkmanData;
import com.mywork.vipramilk.entity.ScheduleData;

import java.util.List;

public class ScheduleListAdapter extends RecyclerView.Adapter<ScheduleListAdapter.ViewHolder> {
    ItemClickListener mClickListener;
    private final LayoutInflater mInflater;
    private List<ScheduleData> milkmanDataList; // Cached copy of words

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewName, textViewContactNoOne, textViewContactNoTwo;
       // private final ImageView imageViewMessage, imageViewEdit, imageViewDelete;

        private ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewContactNoOne = itemView.findViewById(R.id.textViewContact);
            textViewContactNoTwo = itemView.findViewById(R.id.textViewContactTwo);
//            imageViewMessage = itemView.findViewById(R.id.imageViewContactWhatsApp);
//            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
//            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            itemView.setOnClickListener(this);
//            imageViewMessage.setOnClickListener(this);
//            imageViewEdit.setOnClickListener(this);
//            imageViewDelete.setOnClickListener(this);
        }

        public ScheduleData getmItem(int position) {
            ScheduleData repos = milkmanDataList.get(position);
            return repos;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {

                mClickListener.onItemClick(v, getmItem(getAdapterPosition()));

            }

        }
    }


    public ScheduleListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.schedule_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (milkmanDataList != null) {
            ScheduleData current = milkmanDataList.get(position);
            holder.textViewName.setText(String.valueOf(current.getDate()));

            if(current.getOnltr()!=0)
            holder.textViewContactNoOne.setText(String.valueOf(current.getOnltr()));
            if(current.getTwoltr()!=0)
            holder.textViewContactNoTwo.setText(String.valueOf(current.getTwoltr()));
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewName.setText("No Word");
        }
    }

    public void setScheduleData(List<ScheduleData> milkmans) {
        milkmanDataList = milkmans;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (milkmanDataList != null)
            return milkmanDataList.size();
        else return 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, ScheduleData milkmanData);

        void onItemMessageClick(View view, ScheduleData milkmanData);

        void onItemEditClick(View view, ScheduleData milkmanData);

        void onItemDeleteClick(View view, ScheduleData milkmanData);

    }
}


