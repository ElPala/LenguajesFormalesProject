package com.iteso.lenguajesformales;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityBook extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        webView = findViewById(R.id.web);
        webView.loadUrl(this.getIntent().getStringExtra("URI"));
    }
}
