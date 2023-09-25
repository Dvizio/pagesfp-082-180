package com.example.pagesfp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class logAdapter extends RecyclerView.Adapter<logAdapter.LogViewHolder> {

    private Context context;
    private ArrayList<LogModel> logList;
    private dbHelper dbHelper;

    public logAdapter(Context context, ArrayList<LogModel> logList) {
        this.context = context;
        this.logList = logList;
        dbHelper = new dbHelper(context);
    }


    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.log,parent,false);
        LogViewHolder vh = new LogViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        LogModel logModel = logList.get(position);

        //get data
        //we need only All data
        String lid = logModel.getLid();
        String lname = logModel.getLname();
        String ltimestamp = logModel.getLtimestamp();

        //set data in view
        holder.logName.setText(lname);
        holder.logTimestamp.setText(ltimestamp);
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    class LogViewHolder extends RecyclerView.ViewHolder{

        //view for row_contact_item
        TextView logName,logTimestamp;
        RelativeLayout logRelativeLayout;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);

            //init view
            logName = itemView.findViewById(R.id.lname);
            logTimestamp = itemView.findViewById(R.id.ltimestamp);
            logRelativeLayout = itemView.findViewById(R.id.logRV);
        }
    }
}
