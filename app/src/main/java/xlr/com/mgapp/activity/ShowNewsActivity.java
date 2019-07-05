package xlr.com.mgapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import xlr.com.mgapp.R;


public class ShowNewsActivity extends AppCompatActivity {

    private WebView show_news;

    // 添加用户等待显示控件
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        initView();
        mDialog = new ProgressDialog(ShowNewsActivity.this);
        mDialog.setMessage("玩命加载ing");
        show_news.setWebViewClient(new WebViewClient() {
            //网页加载时的回调
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!mDialog.isShowing()) {
                    mDialog.show();
                }
            }
            //网页停止加载时的回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 如果没有显示，则显示
                if (mDialog.isShowing())
                    mDialog.dismiss();
            }
        });
        setWebView();
    }

    private void initView() {
        show_news =(WebView) findViewById(R.id.show_news);
    }

    public void setWebView(){
        show_news.loadUrl(getPreIntent());
        show_news.getSettings().setJavaScriptEnabled(true);//可以和javaScript交互
        show_news.setWebViewClient(new WebViewClient());
    }
    String getPreIntent() {
//        获取传递来的变量
        return getIntent().getExtras().get("url").toString().trim();
    }
}
