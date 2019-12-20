package com.mywork.vipramilk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mywork.vipramilk.entity.Customer;
import com.mywork.vipramilk.entity.CustomerData;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerHolder> {
    ItemClickListener mClickListener;
    private final LayoutInflater mInflater;
    private List<CustomerData> customerDataList; // Cached copy of words

    class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewName, textViewContactNoOne, textViewContactNoTwo;
        private final ImageView imageViewMessage;

        private CustomerHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewContactNoOne = itemView.findViewById(R.id.textViewContact);
            textViewContactNoTwo = itemView.findViewById(R.id.textViewContactTwo);
            imageViewMessage = itemView.findViewById(R.id.imageViewContactWhatsApp);

            itemView.setOnClickListener(this);
            imageViewMessage.setOnClickListener(this);
        }

        public CustomerData getmItem(int position) {
            CustomerData repos = customerDataList.get(position);
            return repos;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                if (v.getId() == R.id.imageViewContactWhatsApp) {
                    mClickListener.onItemMessageClick(v, getmItem(getAdapterPosition()));
                } else {
                    mClickListener.onItemClick(v, getmItem(getAdapterPosition()));
                }
            }

        }
    }


    public CustomerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CustomerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_customer_item, parent, false);
        return new CustomerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomerHolder holder, int position) {
        if (customerDataList != null) {
            CustomerData current = customerDataList.get(position);
            holder.textViewName.setText(current.getcustomerName());

            holder.textViewContactNoOne.setText(current.getContactOne());

            holder.textViewContactNoTwo.setText(current.getContactTwo());
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewName.setText("No Word");
        }
    }

    public void setCustomers(List<CustomerData> customers) {
        customerDataList = customers;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (customerDataList != null)
            return customerDataList.size();
        else return 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, CustomerData customerData);

        void onItemMessageClick(View view, CustomerData customerData);

    }
}
