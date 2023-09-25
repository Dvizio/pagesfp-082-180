package com.example.pagesfp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.SubjectViewHolder> {
    private Context context;
    private ArrayList<SubjectModel> subjectList;
    private dbHelper dbHelper;

    public subjectAdapter(Context context, ArrayList<SubjectModel> subjectList) {
        this.context = context;
        this.subjectList = subjectList;
        dbHelper = new dbHelper(context);
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject,parent,false);
        SubjectViewHolder vh = new SubjectViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
        SubjectModel subjectModel = subjectList.get(position);

        //get data
        //we need All data
        String sid = subjectModel.getSid();
        String scode = subjectModel.getScode();
        String sname = subjectModel.getSname();
        String sclass = subjectModel.getSclass();
        String stime = subjectModel.getStime();

        //set data in view
        holder.subjectname.setText(sname);
        holder.subjectcode.setText(scode);
        holder.subjectclass.setText(sclass);
        holder.subjecttime.setText(stime);

        //handle item click and show contact details
        holder.subjectLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create intent to move to ScanFoto Activity with contact id as reference
                Intent intent = new Intent(context, ScanFoto.class);
                intent.putExtra("subjectId", sid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder{

        //view for row_contact_item
        TextView subjectname,subjectcode, subjectclass, subjecttime;
        LinearLayout subjectLinearLayout;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            //init view
            subjectname = itemView.findViewById(R.id.sname);
            subjectcode = itemView.findViewById(R.id.scode);
            subjectclass = itemView.findViewById(R.id.sclass);
            subjecttime = itemView.findViewById(R.id.stime);
            subjectLinearLayout = itemView.findViewById(R.id.subjectLL);
        }
    }

}
