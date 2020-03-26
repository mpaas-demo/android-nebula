package com.mpaas.demo.nebula;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.alipay.mobile.h5container.api.H5Param;
import com.mpaas.demo.R;
import com.mpaas.nebula.adapter.api.MPNebula;

/**
 * Created by mengfei on 2018/10/22.
 */

public class EmbedActivity extends BaseActivity {
    private TextView mTitle;
    FrameLayout mEmbeddedWebViewParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embed);
        mTitle = (TextView) findViewById(R.id.titlebar);
        mTitle.setText(getString(R.string.embed_webview));
        mEmbeddedWebViewParent = (FrameLayout) findViewById(R.id.embedded_webview_parent);
        initEmbeddedWebView();
    }

    private void initEmbeddedWebView() {
        Bundle param = new Bundle();
        param.putString(H5Param.LONG_URL, "https://tech.antfin.com/products/MPAAS");
        MPNebula.getH5View(this, param);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(20, 0, 20, 0);
        // 添加view到父布局中
        mEmbeddedWebViewParent.addView(MPNebula.getH5View(this, param), lp);
    }
}

