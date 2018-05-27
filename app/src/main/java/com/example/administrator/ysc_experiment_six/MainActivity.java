package com.example.administrator.ysc_experiment_six;

import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    protected EditText editText;

    protected String url;
    public static String NAME="value";
    protected WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inview();
        setWebview();
        setListern();
    }

    private void setWebview() {
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webview.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webview.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webview.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webview.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webview.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webview.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webview.getSettings().setDomStorageEnabled(true);//DOM Storage
// displayWebview.getSettings().setUserAgentString("User-Agent:Android");//设置用户代理，一般不用
    }

    private void setListern()
    {   editText.setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == keyEvent.KEYCODE_ENTER) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                }
                webview.loadUrl(editText.getText().toString());
                webview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        webview.loadUrl(url);//告诉我们用webview来加载网页;
                        return true;
                    }
                });

            }

            return false;
        }
    });

    }

    private void inview() {
        editText = findViewById(R.id.edit_url);
        webview=findViewById(R.id.webview_);


    }
}
