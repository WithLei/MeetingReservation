package com.android.renly.meetingreservation.module.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.renly.meetingreservation.R;
import com.android.renly.meetingreservation.module.base.BaseActivity;

import butterknife.BindView;

/**
 * @author Renly
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webView;

    private String title;
    private String url;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initData() {
        getUrl();
        initWebView();
    }

    @Override
    protected void initView() {
        initSlidr();
        initToolBar(true,title);
        webView.loadUrl(url);
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.addJavascriptInterface(this,"android");//添加js监听 这样html就能调用客户端

        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);//允许使用js

        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
        webSettings.setDisplayZoomControls(false);
    }

    private void getUrl() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
