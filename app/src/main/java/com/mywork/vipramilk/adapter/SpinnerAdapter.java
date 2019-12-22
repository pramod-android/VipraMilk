package com.mywork.vipramilk.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mywork.vipramilk.entity.RouteData;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<RouteData> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (RouteData)
    private List<RouteData>  values;

    public SpinnerAdapter(Context context, int textViewResourceId,
                          List<RouteData> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        if(values!=null) {
            return values.size();
        }else {
            return 0;
        }
    }

    @Override
    public RouteData getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    public void setData(List<RouteData> routeDataList){
        values=routeDataList;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (RouteDatas array) and the current position
        // You can NOW reference each method you has created in your bean object (RouteData class)
        label.setText(values.get(position).getRouteName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getRouteName());

        return label;
    }
}