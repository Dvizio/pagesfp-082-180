package com.example.pagesfp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class subjectList extends AppCompatActivity {
    private RecyclerView subjectRV;

    private dbHelper dbHelper;

    private subjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjectlist);

        dbHelper = new dbHelper(this);

        subjectRV = findViewById(R.id.subjectRV);

        subjectRV.setHasFixedSize(true);

        loadSubjectData();
    }

    private void loadSubjectData() {
        subjectAdapter = new subjectAdapter(this, dbHelper.getAllSubjectData());
        subjectRV.setAdapter(subjectAdapter);
    }
}
