package com.mywork.vipramilk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mywork.vipramilk.R;
import com.mywork.vipramilk.entity.CustomerData;
import com.mywork.vipramilk.entity.RouteData;

import java.util.List;

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.RouteHolder> {
    ItemClickListener mClickListener;
    private final LayoutInflater mInflater;
    private List<RouteData> routeDataList; // Cached copy of words

    class RouteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textViewName, textViewContactNoOne, textViewContactNoTwo;
        private final ImageView imageViewMessage, imageViewEdit;//, imageViewDelete;

        private RouteHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewRouteName);
            textViewContactNoOne = itemView.findViewById(R.id.textViewRouteNo);
            textViewContactNoTwo = itemView.findViewById(R.id.textViewRouteAddress);
            imageViewMessage = itemView.findViewById(R.id.imageViewContactWhatsApp);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
           // imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            itemView.setOnClickListener(this);
            imageViewMessage.setOnClickListener(this);
            imageViewEdit.setOnClickListener(this);
            //imageViewDelete.setOnClickListener(this);
        }

        public RouteData getmItem(int position) {
            RouteData repos = routeDataList.get(position);
            return repos;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) {
                if (v.getId() == R.id.imageViewContactWhatsApp) {
                    mClickListener.onItemMessageClick(v, getmItem(getAdapterPosition()));
                }
                if (v.getId() == R.id.imageViewEdit) {
                    mClickListener.onItemEditClick(v, getmItem(getAdapterPosition()));
                } else {
                    mClickListener.onItemClick(v, getmItem(getAdapterPosition()));
                }
            }

        }
    }


    public RouteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_route_item, parent, false);
        return new RouteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RouteHolder holder, int position) {
        if (routeDataList != null) {
            RouteData current = routeDataList.get(position);
            holder.textViewName.setText(current.getRouteName());

            holder.textViewContactNoOne.setText(current.getRouteNumber());

            holder.textViewContactNoTwo.setText(current.getRouteArea());
        } else {
            // Covers the case of data not being ready yet.
            holder.textViewName.setText("No Word");
        }
    }

    public void setRoutes(List<RouteData> routes) {
        routeDataList = routes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (routeDataList != null)
            return routeDataList.size();
        else return 0;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, RouteData routeData);

        void onItemMessageClick(View view,  RouteData routeData);

        void onItemEditClick(View view,  RouteData routeData);

    }
}

