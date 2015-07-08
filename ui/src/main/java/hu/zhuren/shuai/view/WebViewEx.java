package hu.zhuren.shuai.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
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

    private View mErrorView;
    private View mToolBarView;
    private View mLoadingView;

    private WebView mWebView;
    private WebViewClient mWebViewClient;
    private WebChromeClient mWebChromeClient;
    private WebViewConfiguration mWebViewConfiguration;

    public WebViewEx(Context context) {
        super(context);
        mContext = context;
        initView();
        initWebView();
    }

    public WebViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
        initWebView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.view_web_view_ex, null);
        ButterKnife.bind(this, view);
        ViewGroup.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view, params);
    }

    private void initWebView() {
        //屏蔽掉长按事件
        mWebView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString(webSettings.getUserAgentString());
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        //enable viewport mata tag support
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//        mWebViewClientEx = new WebViewClientEx();
//        mWebView.setWebViewClient(mWebViewClientEx);
//        mWebView.setWebChromeClient(new WebChromeCientEx());
    }

    /**
     * 设置网络请求配置
     * @param wvc
     */
    public void setWebViewConfiguration(WebViewConfiguration wvc){
        mWebViewConfiguration = wvc;
    }

}
