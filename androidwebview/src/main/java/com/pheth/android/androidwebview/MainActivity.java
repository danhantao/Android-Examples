package com.pheth.android.androidwebview;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        webView = new WebView(this);
        webView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        webView.setWebViewClient(new WebViewClient());
        frameLayout.addView(webView);
//        String summary = "<html><body>You scored <b>192</b> points.</body></html>";
//        webView.loadData(summary, "text/html", null);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this),"Android");
//        webView.loadData(loadAssetSource(),"text/html",null);
        webView.loadUrl("file:///android_asset/testJs.html");

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                webView.loadUrl("http://www.baidu.com");
                webView.loadUrl("javascript:javaCallJsHasParamsMethod('" + 123 + "')");
            }
        });
    }

    public String loadAssetSource(){
        final AssetManager assets = this.getResources().getAssets();
        InputStream open = null;
        String url = null;
        try {
            open = assets.open("testJs.html");
            url = convertStreamToString(open);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (open != null){
                try {
                    open.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i("url","url:"+url);
        return url;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }


    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }


    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }



}
