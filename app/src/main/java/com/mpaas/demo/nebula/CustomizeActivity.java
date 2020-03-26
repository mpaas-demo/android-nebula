package com.mpaas.demo.nebula;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.alipay.mobile.h5container.api.H5Plugin;
import com.mpaas.demo.R;
import com.mpaas.nebula.adapter.api.MPNebula;

/**
 * Created by omg on 2018/7/23.
 * 此页面所有涉及到的设置通常应在应用启动后执行，且只需执行一次。
 * 此处仅为演示。
 */

public class CustomizeActivity extends BaseActivity {

    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        mTitle = (TextView) findViewById(R.id.titlebar);

        findViewById(R.id.btn_ua_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableUa();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_title_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableCustomizedTitle();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_menu_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableCustomizedMenu();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_progress_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableCustomizedProgress();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_pullrefresh_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableCustomizedPullRefresh();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_bg_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableCustomizedBackground();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_plugin_url_intercept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUrlInterceptPlugin();
                Toast.makeText(CustomizeActivity.this, R.string.setting_success, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_embed_webview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CustomizeActivity.this, EmbedActivity.class));
            }
        });
        mTitle.setText(getString(R.string.customize));
    }

    private void enableUa() {
        MPNebula.setUa(new H5UaProviderImpl());
    }

    private void enableCustomizedTitle() {
        MPNebula.setCustomViewProvider(new CustomTitleH5ViewProvider());
    }

    private void enableCustomizedMenu() {
        MPNebula.setCustomViewProvider(new CustomNaviMenuH5ViewProvider());
    }

    private void enableCustomizedProgress() {
        MPNebula.setCustomViewProvider(new CustomProgressH5ViewProvider());
    }

    private void enableCustomizedPullRefresh() {
        MPNebula.setCustomViewProvider(new CustomPullHeaderH5ViewProvider());
    }

    /**
     * 完全自定义背景
     * <p>
     * 若仅需修改背景颜色，无需调用此方法
     * 只需在打开 H5 时传递参数：
     * Bundle bundle = new Bundle();
     * bundle.putInt(H5Param.LONG_BOUNCE_TOP_COLOR, getResources().getColor(R.color.xxxx));
     */
    private void enableCustomizedBackground() {
        MPNebula.setCustomViewProvider(new CustomBackgroundH5ViewProvider());
    }

    private void registerUrlInterceptPlugin() {
        MPNebula.registerH5Plugin(
                MyUrlInterceptPlugin.class.getName(),
                null,
                "page",
                new String[]{H5Plugin.CommonEvents.H5_PAGE_SHOULD_LOAD_URL}
        );
    }
}
