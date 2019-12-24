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
import java.util.List;

public class MilkmanListAdapter extends RecyclerView.Adapter<MilkmanListAdapter.MilkmanHolder> {
    ItemClickListener mClickListener;
    private final LayoutInflater mInflater;
    private List<MilkmanData> milkmanDataList; // Cached copy of words

    class MilkmanHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewName, textViewContactNoOne, textViewContactNoTwo;
        private final ImageView imageViewMessage,imageViewEdit,imageViewDelete;

        private MilkmanHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewContactNoOne = itemView.findViewById(R.id.textViewContact);
            textViewContactNoTwo = itemView.findViewById(R.id.textViewContactTwo);
            imageViewMessage = itemView.findViewById(R.id.imageViewContactWhatsApp);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            itemView.setOnClickListener(this);
            imageViewMessage.setOnClickListener(this);
            imageViewEdit.setOnClickListener(this);
            imageViewDelete.setOnClickListener(this);
        }

        public MilkmanData getmItem(int position) {
            MilkmanData repos = milkmanDataList.get(position);
            return repos;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                if (v.getId() == R.id.imageViewContactWhatsApp) {
                    mClickListener.onItemMessageClick(v, getmItem(getAdapterPosition()));
                } if (v.getId() == R.id.imageViewEdit) {
                    mClickListener.onItemEditClick(v, getmItem(getAdapterPosition()));
                } if (v.getId() == R.id.imageViewDelete) {
                    mClickListener.onItemDeleteClick(v, getmItem(getAdapterPosition()));
                }else {
                    mClickListener.onItemClick(v, getmItem(getAdapterPosition()));
                }
            }

        }
    }


    public MilkmanListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MilkmanHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_customer_item, parent, false);
        return new MilkmanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MilkmanHolder holder, int position) {
        if (milkmanDataList != null) {
            MilkmanData current = milkmanDataList.get(position);
            holder.textViewName.setText(current.getMilkmanName());

            holder.textViewContactNoOne.setText(current.getContactOne());

            holder.textViewContactNoTwo.setText(current.getContactTwo());
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewName.setText("No Word");
        }
    }

    public void setCustomers(List<MilkmanData> milkmans) {
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
        void onItemClick(View view, MilkmanData milkmanData);

        void onItemMessageClick(View view, MilkmanData milkmanData);
        void onItemEditClick(View view, MilkmanData milkmanData);
        void onItemDeleteClick(View view, MilkmanData milkmanData);

    }
}

