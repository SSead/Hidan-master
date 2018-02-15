package com.aajdindevinc.hidan;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;

public class Obnavljac extends Service {

    String web;
    WebView v;
    WebView getSource;

    public Obnavljac() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        TinyDB database = new TinyDB(this);

        final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = 0;
        params.y = 0;
        params.width = 0;
        params.height = 0;




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
                System.out.println("KUGLA");

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

//        windowManager.addView(getSource, params);

        v = new WebView(this);
        v.getSettings().setJavaScriptEnabled(true);
        v.canGoForward();

        System.out.println(web);




        //windowManager.addView(v,params);


        //System.out.println(database.getInt("hour")+" "+database.getInt("minute"));

        return flags;
    }

    void obnovi (){

    }

}
