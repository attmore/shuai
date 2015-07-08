package hu.zhuren.shuai.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import butterknife.ButterKnife;

/**
 * Created by hu.zhuren on 8/7/15.
 * 一个WebView的扩展实现
 * 1. 加载进度条
 * 2. 出错提示 404/500错误处理
 * 3. 没有本地网络的时候需要重拾
 * 4. 开启JS支持，优化加载速度
 * 5. ProgressBar 支持
 * 6. ToolBar 支持
 */
public class WebViewEx extends LinearLayout {
    private Context mContext;

    private View mTopProgressBarView;
    private View mErrorView;
    private View mToolBarView;
    private View mLoadingView;


    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;
    private WebViewConfiguration mWebViewConfiguration;

    private WebView mWebView;

    public WebViewEx(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public WebViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.view_web_view_ex, null);
        ButterKnife.bind(this, view);
        ViewGroup.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view, params);
        mWebViewClient = new WebViewClientEx();
        mWebChromeClient = new WebChromeCientEx();
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);

    }

    /**
     * 设置网络请求配置
     *
     * @param wvc
     */
    public void setWebViewConfiguration(WebViewConfiguration wvc) {
        mWebViewConfiguration = wvc;
        if (!mWebViewConfiguration.isLongClickEnable()) {
            //屏蔽长按事件
            mWebView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }
            });
        }
        if (mWebViewConfiguration.isJavascriptEnable()) {
            mWebView.getSettings().setJavaScriptEnabled(true);
        } else {
            mWebView.getSettings().setJavaScriptEnabled(false);
        }
        if (mWebViewConfiguration.isViewportSupportEnable()) {
            //viewport支持
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
        }
        if (!TextUtils.isEmpty(mWebViewConfiguration.getUserAgent())) {
            //拼接useragent字符串
            mWebView.getSettings().setUserAgentString(mWebView.getSettings().getUserAgentString() + mWebViewConfiguration.getUserAgent());
        }
    }

    /**
     * @param v
     */
    public void setLoadingView(View v) {
        if (v != mLoadingView && mWebViewConfiguration.isLoadingBarEnable()) {
            mLoadingView = v;
        }
    }

    /**
     * @param v
     */
    public void setToolBarView(View v) {
        if (v != mToolBarView && mWebViewConfiguration.isToolBarEnable()) {
            mToolBarView = v;
        }
    }

    /**
     * @param v
     */
    public void setErrorView(View v) {
        if (v != mErrorView) {

        }
    }

    /**
     * @param v
     */
    public void setToolProgressBarView(View v) {
        if (v != mTopProgressBarView && mWebViewConfiguration.isTopProgressBarEnable()) {
            mTopProgressBarView = v;
        }
    }

    /**
     * 加载 URL
     * @param url 资源地址
     */
    public void loadUrl(String url) {
        mWebView.loadUrl(url);
    }

    /**
     * 重新加载当前的页面
     */
    public void reload() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        mWebView.reload();
    }

    /**
     * WebViewClientEx
     * 处理页面点击等事件
     */
    private class WebViewClientEx extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //处理页面上按钮点击事件
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String url) {
            //是否开启图片懒加载 http://www.pedant.cn/2014/09/10/webview-optimize-points/
            if (mWebViewConfiguration.isImageLazyLoadEnable()) {
                if (!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
            }
        }

        @Override
        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            super.onReceivedError(webView, errorCode, description, failingUrl);
            //先将界面制空
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            //出错了的话，加载错误界面
            if(null != mErrorView){
                mErrorView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //TODO 开始加载的时候
        }
    }

    /**
     * 主要处理JS事件
     * TODO 增加JS-Java Bridge
     */
    private class WebChromeCientEx extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //进度条更新
        }
    }


    //TODO custom
//    /**
//     * @param webViewClient
//     */
//    public void setWebViewClient(WebViewClient webViewClient) {
//        mWebViewClient = webViewClient;
//        mWebView.setWebViewClient(mWebViewClient);
//    }

//    /**
//     * @param webChromeClient
//     */
//    public void setWebChromeClient(WebChromeClient webChromeClient) {
//        mWebChromeClient = webChromeClient;
//        mWebView.setWebChromeClient(mWebChromeClient);
//    }
}
