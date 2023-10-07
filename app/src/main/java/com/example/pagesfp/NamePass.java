package com.example.pagesfp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pagesfp.server.ApiClientAttendance;
import com.example.pagesfp.server.ResponseApi;
import com.example.pagesfp.server.serverApi;

import java.io.ByteArrayOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NamePass extends AppCompatActivity {
    private Button masuk;
    private EditText nrp;
    private EditText pass;
    private Button btnSend;
    String nrp1;
    String pass1;
    private String status, sid;
    private dbHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namepass);

        dbHelper = new dbHelper(this);

        Intent intent = getIntent();
        sid = intent.getStringExtra("subjectId");
        status = intent.getStringExtra("status");

        nrp = findViewById(R.id.nrp);
        pass = findViewById(R.id.pass);
        btnSend = findViewById(R.id.btnsend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nrp1 = nrp.getText().toString();
                pass1 = pass.getText().toString();

                if (nrp1.trim().length() > 5) {
                    if (pass1.length() == 3) {
                        sendImg(nrp1, status, pass1);
                    }
                    else {
                        Toast.makeText(NamePass.this, "Periksa Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(),"Lengkapi NRP",Toast.LENGTH_LONG).show();
//                    nrp.requestFocus();
                }
            }
        });
    }

    protected  void sendImg(String txt,String st,String ps){
        final SweetAlertDialog pDialog = new SweetAlertDialog(NamePass.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
        Bitmap result = Bitmap.createScaledBitmap(ScanFoto.bitmap, 96,96, true);
        String myBase64Image = encodeToBase64(result, Bitmap.CompressFormat.JPEG, 100);
        ApiClientAttendance api =  serverApi.builder().create(ApiClientAttendance.class);
        ResponseApi responseApi = new ResponseApi(txt,st,ps,"data:image/jpeg;base64,"+myBase64Image);
        Call<ResponseApi> upload = api.kirim(responseApi);

        upload.enqueue(new Callback<ResponseApi>() {
            @Override
            public void onResponse(Call<ResponseApi> call, Response<ResponseApi> response) {
                String current = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                if (response.code() == 200) {
                    pDialog.dismiss();
                    new SweetAlertDialog(NamePass.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Hasil")
                            .setContentText(response.body().getNrp())
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    if (response.body().toString().contains("Accepted")){
                                        finish();
                                    }

                                }
                            }).show();

                    String sname = dbHelper.getSubjectName(sid);
                    long id =  dbHelper.insertLog(
                            ""+sname,
                            ""+current
                    );
                } else {
                    pDialog.dismiss();
                    new SweetAlertDialog(NamePass.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Error")
                            .setContentText(response.toString())
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                    //finish();
                                }
                            }).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseApi> call, Throwable t) {
                pDialog.dismiss();

                // Create a custom dialog with a ScrollView
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(NamePass.this);
                alertDialogBuilder.setTitle("Hasil");
                alertDialogBuilder.setCancelable(false);

                // Create a ScrollView and add it to the dialog
                ScrollView scrollView = new ScrollView(NamePass.this);
                alertDialogBuilder.setView(scrollView);

                // Create a TextView to display the error message within the ScrollView
                TextView errorMessageTextView = new TextView(NamePass.this);
                errorMessageTextView.setText(t.getMessage());
                scrollView.addView(errorMessageTextView);

                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                // Show the custom dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
//            public void onFailure(Call<ResponseApi> call, Throwable t) {
//                pDialog.dismiss();
//                new SweetAlertDialog(NamePass.this, SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("Hasil")
//                        .setContentText(t.getMessage())
//                        .setConfirmText("OK")
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismissWithAnimation();
//                                //finish();
//                            }
//                        }).show();
//            }
        });
    }

    private String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }
}