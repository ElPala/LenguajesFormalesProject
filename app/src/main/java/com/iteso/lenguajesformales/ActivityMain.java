package com.iteso.lenguajesformales;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.lenguajesformales.beans.GraphL;
import com.iteso.lenguajesformales.beans.ResultDialogFragment;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ActivityMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner spinner;
    private Button see;
    private Button start;
    private String book;
    private String uri;
    private final int MYREQUEST = 7855;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        see = findViewById(R.id.buttonsee);
        start = findViewById(R.id.buttonstart);
        editText = findViewById(R.id.editText);
        see.setOnClickListener(this);
        start.setOnClickListener(this);
        editText.setText("");
    }

    public void getBook(String fileName) {
        try {

            int id = getResources().getIdentifier(fileName, "raw", getPackageName());

            InputStream iS = getResources().openRawResource(id);
            byte[] buffer = new byte[iS.available()];
            iS.read(buffer);
            ByteArrayOutputStream oS = new ByteArrayOutputStream();
            oS.write(buffer);
            oS.close();
            iS.close();
            book = oS.toString();
        } catch (Exception e) {
            book = e.getMessage();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                getBook("dracula");
                uri=getString(R.string.dracula);
                break;
            case 1:
                getBook("dorian");
                uri=getString(R.string.dorian);
                break;
            case 2:
                getBook("quixote");
                uri=getString(R.string.quixote);
                break;
            case 3:
                getBook("sherlock");
                uri=getString(R.string.sherlock);
                break;
            case 4:
                getBook("legend");
                uri=getString(R.string.legend);
                break;
            default:
                book = "none";
                uri=getString(R.string.top);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        book = "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonsee:
                int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.INTERNET}, MYREQUEST);
                } else {
                    Intent intent = new Intent(this,ActivityBook.class);
                    intent.putExtra("URI",uri);
                    Log.d("error", "onCreate: "+uri.toString());
                    startActivity(intent);
                }
                break;
            case R.id.buttonstart:
                if(!editText.getText().toString().equals("")){
                    start.setClickable(false);
                    see.setClickable(false);
                    View view = getCurrentFocus();
                    String s[] = editText.getText().toString().split("//");
                    GraphL graphL = new GraphL(s);
                    graphL.check(book);
                    for (int i =0; i<s.length;i++){
                        s[i]+=" - "+graphL.getMatch()[i];
                    }
                    Bundle bundle = new Bundle();
                    bundle.putCharSequenceArray("RESULTS",s);
                    ResultDialogFragment dialog =  ResultDialogFragment.newInstance();
                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(),"DIALOG");
                    start.setClickable(true);
                    see.setClickable(true);
                    if (view != null) {
                        findViewById(R.id.main).invalidate();
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MYREQUEST:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(this,ActivityBook.class);
                    intent.putExtra("URI",uri);
                    startActivity(intent);
                }
                break;
        }
    }




}
