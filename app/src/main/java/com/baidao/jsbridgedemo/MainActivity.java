package com.baidao.jsbridgedemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;

public class MainActivity extends BaseActivity {

    private WebView bridgeWebView;
    private Button button;
    private Button btnToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bridgeWebView = ((WebView) findViewById(R.id.webview));
        button = ((Button) findViewById(R.id.btn_click));
        btnToggle = ((Button) findViewById(R.id.btn_toggle));
        bridgeWebView.loadUrl("file:///android_asset/SchemeTest.html");
        setWebView(bridgeWebView);
    }

    private void setWebView(final WebView webView) {

        WebSettings webSettings = webView.getSettings();

        //支持js
        webSettings.setJavaScriptEnabled(true);

        //对webview页面加载管理
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (urlCanLoad(url.toLowerCase())) {

                    if(url.contains(".apk")){
                        //若是apk的下载地址,则通过浏览器或者应用市场打开下载
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }else {
                        webView.loadUrl(url);
                    }

                    return true;

                } else {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (getPackageManager().resolveActivity(intent, 0) == null) {
                        Toast.makeText(MainActivity.this,"未安装",Toast.LENGTH_SHORT).show();
                        //提示去应用市场下载 可给出提示框,点击跳转下载
                        //todo
                    }else {
                        startActivity(intent);
                    }

                    return false;
                }

            }

        });

    }

    /**
     * 列举正常情况下能正常加载的网页url
     *
     * @param url
     * @return
     */
    private boolean urlCanLoad(String url) {
        return url.startsWith("http://") || url.startsWith("https://") ||
                url.startsWith("ftp://") || url.startsWith("file://");
    }

}