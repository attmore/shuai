package hu.zhuren.shuai.view;

/**
 * Created by bobhu on 8/7/15.
 * WebView配置选项
 */
public class WebViewConfiguration {

    private boolean isToolBarEnable = false;
    private boolean isTopProgressBarEnable = true;
    private boolean isJavascriptEnable = true;
    private boolean isLoadingBarEnable = true;
    private boolean isViewportSupportEnable = true;
    private boolean isLongClickEnable = false;

    private WebViewConfiguration(final Builder builder) {
        isToolBarEnable = builder.isToolBarEnable;
        isTopProgressBarEnable = builder.isTopProgressBarEnable;
        isJavascriptEnable = builder.isJavascriptEnable;
        isLongClickEnable = builder.isLongClickEnable;
        isLoadingBarEnable = builder.isLoadingBarEnable;
        isViewportSupportEnable = builder.isViewportSupportEnable;
    }

    public static WebViewConfiguration newConfiguration() {
        return new Builder().build();
    }

    public boolean isToolBarEnable() {
        return isToolBarEnable;
    }

    public boolean isTopProgressBarEnable() {
        return isTopProgressBarEnable;
    }

    public boolean isJavascriptEnable() {
        return isJavascriptEnable;
    }

    public boolean isLoadingBarEnable() {
        return isLoadingBarEnable;
    }

    public boolean isViewportSupportEnable() {
        return isViewportSupportEnable;
    }

    public boolean isLongClickEnable() {
        return isLongClickEnable;
    }

    public static class Builder {
        private boolean isToolBarEnable = false;
        private boolean isTopProgressBarEnable = true;
        private boolean isJavascriptEnable = true;
        private boolean isLoadingBarEnable = true;
        private boolean isViewportSupportEnable = true;
        private boolean isLongClickEnable = false;

        private String mUserAgent = "hu.zhuren.shuai";  //default string

        public Builder() {

        }

        /**
         * @param isToolBarEnable
         * @return
         */
        public Builder setToolBaeEnable(boolean isToolBarEnable) {
            this.isToolBarEnable = isToolBarEnable;
            return this;
        }

        /**
         * @param isTopProgressBarEnable
         * @return
         */
        public Builder setTopProgressBarEnable(boolean isTopProgressBarEnable) {
            this.isTopProgressBarEnable = isTopProgressBarEnable;
            return this;
        }

        /**
         * @param isJavascriptEnable
         * @return
         */
        public Builder setJavascriptEnable(boolean isJavascriptEnable) {
            this.isJavascriptEnable = isJavascriptEnable;
            return this;
        }

        /**
         * @param isViewportSupportEnable
         * @return
         */
        public Builder setViewportSupportEnable(boolean isViewportSupportEnable) {
            this.isViewportSupportEnable = isViewportSupportEnable;
            return this;
        }

        /**
         * @param isLongClickEnable
         * @return
         */
        public Builder setLongClickEnable(boolean isLongClickEnable) {
            this.isLongClickEnable = isLongClickEnable;
            return this;
        }

        /**
         * @param userAgent
         * @return
         */
        public Builder setAppendUserAgent(String userAgent) {
            this.mUserAgent = userAgent;
            return this;
        }

        public WebViewConfiguration build() {
            return new WebViewConfiguration(this);
        }
    }
}
