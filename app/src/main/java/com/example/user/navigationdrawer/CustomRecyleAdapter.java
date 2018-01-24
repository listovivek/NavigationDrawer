package com.example.user.navigationdrawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by USER on 11-01-2018.
 */

public class CustomRecyleAdapter extends RecyclerView.Adapter<CustomRecyleAdapter.ViewHolder> {



    Context con;
    String[] strArray;


    //http://www.android4devs.com/2014/12/how-to-make-material-design-navigation-drawer.html

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txTextView;
        public ViewHolder(View itemView, int ViewType) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);
            txTextView = (TextView) itemView.findViewById(R.id.text1);
        }


    }
    public CustomRecyleAdapter(MainActivity mainActivity, String[] titles) {
        con = mainActivity;
        strArray = titles;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_custom_view,parent,false); //Inflating the layout

        ViewHolder vhHeader = new ViewHolder(v,viewType);//Creating ViewHolder and passing the object of type view

        v.setOnClickListener(MainActivity.mOnClickListener);
        return vhHeader;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txTextView.setText(strArray[position]);

    }

    @Override
    public int getItemCount() {
        return strArray.length;
    }
}
