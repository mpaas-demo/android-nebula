package com.mpaas.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.alipay.mobile.nebula.provider.H5AppCenterPresetProvider;
import com.alipay.mobile.nebula.provider.H5PublicRsaProvider;
import com.alipay.mobile.nebula.util.H5Utils;
import com.mpaas.demo.nebula.CustomizeActivity;
import com.mpaas.demo.nebula.H5AppCenterPresetProviderImpl;
import com.mpaas.demo.nebula.H5RsaProviderImpl;
import com.mpaas.demo.nebula.MyJSApiPlugin;
import com.mpaas.demo.nebula.NebulaAppActivity;
import com.mpaas.demo.nebula.OnlineActivity;
import com.mpaas.demo.nebula.PresetAmrPipeline;
import com.mpaas.nebula.adapter.api.MPNebula;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btn_online).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OnlineActivity.class));
            }
        });

        findViewById(R.id.btn_enable_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 设置离线包验签公钥
                H5Utils.setProvider(H5PublicRsaProvider.class.getName(), new H5RsaProviderImpl());
                Toast.makeText(HomeActivity.this, "设置离线包验签公钥成功", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_preset_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 预置离线包，包括普通离线包和公共资源包
                new Thread(new PresetAmrPipeline()).start();
                // 公共资源包返回 appid
                H5Utils.setProvider(H5AppCenterPresetProvider.class.getName(), new H5AppCenterPresetProviderImpl());

                Toast.makeText(HomeActivity.this, "预置离线包成功", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_offline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NebulaAppActivity.class));
            }
        });

        findViewById(R.id.btn_plugin_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerJSAPI();
                Toast.makeText(HomeActivity.this, R.string.custom_jsapi_setting_tips, Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.btn_customized_config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CustomizeActivity.class));
            }
        });

        // 内部测试使用，开发者无需关注
        test();
    }


    private void registerJSAPI() {
        MPNebula.registerH5Plugin(
                MyJSApiPlugin.class.getName(),
                null,
                "page",
                new String[]{"myapi1", "myapi2"}
        );
    }

    // 内部测试使用，开发者无需关注
    private void test(){
        try {
            Class healthActivity = Class.forName("com.mpaas.diagnose.ui.HealthBizSelectActivity");
            Intent intent = new Intent(this, healthActivity);
            startActivity(intent);
        } catch (Exception e) {
        }
    }
}
