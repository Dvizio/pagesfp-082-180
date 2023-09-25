package com.example.pagesfp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class logList extends AppCompatActivity {

    private RecyclerView logRV;

    private dbHelper dbHelper;

    private logAdapter logAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_list);

        dbHelper = new dbHelper(this);

        logRV = findViewById(R.id.logRV);

        logRV.setHasFixedSize(true);

        loadLogData();
    }

    private void loadLogData() {
        logAdapter = new logAdapter(this, dbHelper.getAllLogData());
        logRV.setAdapter(logAdapter);
    }
}