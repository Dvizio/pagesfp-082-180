package com.example.pagesfp;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] sname;
    private final String[] scode;
    private final String[] sclass;
    private final String[] stime;

    public MyListAdapter(Activity context, String[] sname,String[] scode, String[] sclass, String[] stime) {
        super(context, R.layout.subject, sname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.sname=sname;
        this.scode=scode;
        this.sclass=sclass;
        this.stime=stime;

    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.subject, null,true);

        TextView nameText = (TextView) rowView.findViewById(R.id.sname);
        TextView codeText = (TextView) rowView.findViewById(R.id.scode);
        TextView classText = (TextView) rowView.findViewById(R.id.sclass);
        TextView timeText = (TextView) rowView.findViewById(R.id.stime);

        nameText.setText(sname[position]);
        codeText.setText(scode[position]);
        classText.setText(sclass[position]);
        timeText.setText(stime[position]);

        return rowView;

    };
}
