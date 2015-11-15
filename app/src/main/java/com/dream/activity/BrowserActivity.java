package com.dream.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.dream.R;


public class BrowserActivity extends FragmentActivity implements
        OnClickListener {
    private WebView wb;
    private String httpUrl = "", title;
    private WebSettings settings;
    private Button home, back, top, refresh, close;
    /**
     * 异步处理消息
     */
    private boolean flag = false;
    /**
     * 进度条类
     */
//	private ProgressDialog pd;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        // TODO Auto-generated method stub
        handler = new Handler() {
            public void handleMessage(Message msg) {// 定义一个Handler，用于处理下载线程与UI间通讯
                if (!Thread.currentThread().isInterrupted()) {
                    switch (msg.what) {
                        case 0:
//                            if (gress != null) {
//                                gress.setVisibility(View.VISIBLE);
//                            }
                            //	if(!BrowserActivity.this.isFinishing())
                            //	pd.show();// 显示进度对话框
                            break;
                        case 1:
//                            if (gress != null)
//                                gress.setVisibility(View.GONE);
                            //	if(!BrowserActivity.this.isFinishing())
                            //pd.hide();// 隐藏进度对话框，不可使用dismiss()、cancel(),否则再次调用show()时，显示的对话框小圆圈不会动。
                            break;
                        case 2:
                            flag = false;
                            break;
                    }
                }
                super.handleMessage(msg);
            }
        };
        httpUrl = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");

        wb = (WebView) this.findViewById(R.id.wv_web);
        settings = wb.getSettings();

        setWeb();
      //  gress = (RelativeLayout) findViewById(R.id.baseLoadingView);
        //TextView tv = (TextView) gress.findViewById(R.id.tv_loadingmsg);
       // tv.setText("加载中...");
//		wb.setWebViewClient(new WebViewClient() {
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				// TODO Auto-generated method stub
//
//				wb.loadUrl(url);
//				handler.sendEmptyMessage(0);
//				return true;
//			}
//		});
        wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {
                    handler.sendEmptyMessage(1);
                    // 1，如果全部载入,隐藏进度对话框；2，显示加载进度
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           android.webkit.GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }
        });
        setButton();
        if (httpUrl != null)

            wb.loadUrl(httpUrl);
        handler.sendEmptyMessage(0);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);
        if (!TextUtils.isEmpty(title)) {

            setTitle(title);

        }
        //pd.show();
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("SetJavaScriptEnabled")
    private void setWeb() {

        settings.setDatabaseEnabled(true);
        String dir = this.getApplicationContext()
                .getDir("database", Context.MODE_PRIVATE).getPath();
        settings.setSupportZoom(true);
        // 启用地理定位
        settings.setGeolocationEnabled(true);
        // 设置定位的数据库路径
        settings.setGeolocationDatabasePath(dir);
        // 最重要的方法，一定要设置，这就是出不来的主要原因
        settings.setDomStorageEnabled(true);
        // 配置权限（同样在WebChromeClient中实现）
        settings.setJavaScriptEnabled(true);
        settings.setPluginState(PluginState.ON);
        settings.setAllowFileAccess(true);
        settings.setPluginState(PluginState.ON);
        //pd = new ProgressDialog(BrowserActivity.this);
        //	pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //pd.setMessage("数据载入中，请稍候！");
    }

    private void setButton() {
//		home = (Button) this.findViewById(R.id.home);
//		back = (Button) this.findViewById(R.id.back);
//		top = (Button) this.findViewById(R.id.top);
//		refresh = (Button) this.findViewById(R.id.refresh);
//		close = (Button) this.findViewById(R.id.close);
//		home.setOnClickListener(this);
//		back.setOnClickListener(this);
//
//		top.setOnClickListener(this);
//		refresh.setOnClickListener(this);
//		close.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

//		case R.id.back:
//			if (wb.canGoBack())
//				wb.goBack();
//			break;
//		case R.id.home:
//
//			if (httpUrl != null)
//				wb.loadUrl(httpUrl);
//			break;
//		case R.id.top:
//			if (wb.canGoForward())
//				wb.goForward();
//			break;
//		case R.id.refresh:
//			pd.show();
//			wb.reload();
//			break;
//		case R.id.close:
//			ConfirmExit();
//			break;
            default:
        }
    }

    public void ConfirmExit() {// 退出确认
        AlertDialog.Builder ad = new AlertDialog.Builder(BrowserActivity.this);

        ad.setTitle("关闭");
        ad.setMessage("是否关闭页面?");
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {// 退出按钮
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                dialog.dismiss();
                BrowserActivity.this.finish();// 关闭activity

            }
        });
        ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // 不退出不用执行任何操作
            }
        });
        ad.show();// 显示对话框
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub\
        switch (item.getItemId()) {

            case android.R.id.home:
                if (wb.canGoBack()) {
                    wb.goBack();
                    return true;
                } else {
                    // ConfirmExit();// 按了返回键，但已经不能返回，则执行退出确认
                    this.finish();

                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {// 捕捉返回键
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wb.canGoBack()) {
            wb.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            // ConfirmExit();// 按了返回键，但已经不能返回，则执行退出确认

            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        wb.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        wb.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        wb.onPause();

        super.onPause();
    }
}
