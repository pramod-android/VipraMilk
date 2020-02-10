package com.mywork.vipramilk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerHolder> {
    ItemClickListener mClickListener;
    private final LayoutInflater mInflater;
    Context mContext;
    private List<CustomerData> customerDataList; // Cached copy of words

    class CustomerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewName, textViewContactNoOne, textViewContactNoTwo;
        private final ImageView imageViewDelete;

        private CustomerHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewContactNoOne = itemView.findViewById(R.id.textViewContact);
            textViewContactNoTwo = itemView.findViewById(R.id.textViewContactTwo);
           // imageViewMessage = itemView.findViewById(R.id.imageViewContactWhatsApp);
           // imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            itemView.setOnClickListener(this);
           // imageViewMessage.setOnClickListener(this);
           // imageViewEdit.setOnClickListener(this);
           // imageViewDelete.setOnClickListener(this);
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


    public CustomerListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext=context;
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

        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.imageViewDelete);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.menu1:
                            mClickListener.onItemEditClick(view, customerDataList.get(position));
                            break;
                        case R.id.menu2:
                            mClickListener.onItemMessageClick(view, customerDataList.get(position));
                            break;
                        case R.id.menu3:
                            mClickListener.onItemDeleteClick(view, customerDataList.get(position));
                            break;
                    }
                    return false;
                });
                //displaying the popup
                popup.show();

            }
        });
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
        void onItemEditClick(View view, CustomerData customerData);
        void onItemDeleteClick(View view, CustomerData customerData);

    }
}
