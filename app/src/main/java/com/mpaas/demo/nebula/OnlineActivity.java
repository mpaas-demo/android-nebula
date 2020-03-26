package com.mpaas.demo.nebula;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.alipay.mobile.h5container.api.H5Param;
import com.mpaas.demo.R;
import com.mpaas.nebula.adapter.api.MPNebula;


public class OnlineActivity extends BaseActivity {

    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = (TextView) findViewById(R.id.titlebar);
        Button button = (Button) findViewById(R.id.btn_scan_open_page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(H5Param.LONG_SHOW_PROGRESS, true);
                // 开启 LONG_PULL_REFRESH 后，可自定义下拉刷新
//                bundle.putBoolean(H5Param.LONG_PULL_REFRESH, true);
                MPNebula.startUrl("https://tech.antfin.com", bundle);
            }
        });
        mTitle.setText(getString(R.string.title));
    }
}
