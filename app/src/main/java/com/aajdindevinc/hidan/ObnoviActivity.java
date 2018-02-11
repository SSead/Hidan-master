package com.aajdindevinc.hidan;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ObnoviActivity extends AppCompatActivity {

   Button obnovi;

   WebView v;
   WebView getSource;
   WebView view;
   String web = new String();

   Button dgm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obnovi);

        Intent i = getIntent();
        final Bundle bundle = i.getExtras();
        System.out.println(bundle.get("username"));

        //view = findViewById(R.id.web);
        view = new WebView(this);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDomStorageEnabled(true);

        view.canGoForward();
        view.loadUrl("https://www.olx.ba");
        view.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:$('#box-login').toggle();$('#box-login #username').focus();$('#loginbtn').toggleClass('sd');");
                view.loadUrl("javascript:var uselessvar =document.getElementById('username').value='" + bundle.get("username").toString() + "';");
                view.loadUrl("javascript:var uselessvar =document.getElementById('password').value='" + bundle.get("password").toString() + "';");
                view.loadUrl("javascript:document.getElementById('btnlogin1').click()");

                //view.loadUrl("https://www.olx.ba/mojpik/obnovite");
                //view.loadUrl("javascript:var uselessvar = obnoviartikal_mojpik(25819449);");

            }
        });


        getSource = new WebView(this);

        getSource.canGoForward();

        getSource.getSettings().setJavaScriptEnabled(true);
        getSource.getSettings().setLoadWithOverviewMode(true);
        getSource.getSettings().setUseWideViewPort(true);

        getSource.getSettings().setSupportZoom(true);
        getSource.getSettings().setBuiltInZoomControls(true);
        getSource.getSettings().setDisplayZoomControls(false);

        getSource.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        getSource.setScrollbarFadingEnabled(false);

        String newUA = "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
        getSource.getSettings().setUserAgentString(newUA);

        getSource.loadUrl("https://www.olx.ba/mojpik/obnovite");

        getSource.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {

                view.loadUrl("javascript:this.document.location.href = 'source://' + encodeURI(document.documentElement.outerHTML);");
                try {
                    String html = URLDecoder.decode(url, "UTF-8").substring(9);
                    web = html;
                    System.out.println(web);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        v = new WebView(this);
        obnovi = findViewById(R.id.button);
        obnovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                v.getSettings().setJavaScriptEnabled(true);
///sadasdasdas
                v.canGoForward();

                System.out.println(web);
                v.loadUrl("https://www.olx.ba/mojpik/obnovite");
                v.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView view, String url) {

                        v.loadUrl("javascript:var uselessvar = " +
                                web.substring(web.indexOf("onclick=\"obnoviartikal_mojpik(") + 9, web.indexOf(")\">", web.indexOf("onclick=\"obnoviartikal_mojpik(")) + 1) + ";");

                        System.out.println(web.substring(web.indexOf("onclick=\"obnoviartikal_mojpik(") + 9, web.indexOf(")\">", web.indexOf("onclick=\"obnoviartikal_mojpik(")) + 1));

                        getSource.loadUrl("https://www.olx.ba/mojpik/obnovite");
                    }
                });
            }
        });




    }
}
