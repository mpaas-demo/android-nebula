package com.mpaas.demo.nebula;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.mobile.h5container.api.H5ImageListener;
import com.alipay.mobile.nebula.util.H5ImageUtil;
import com.alipay.mobile.nebula.util.H5StatusBarUtils;
import com.alipay.mobile.nebula.view.AbsTitleView;
import com.alipay.mobile.nebula.view.H5TitleBarFrameLayout;
import com.mpaas.demo.R;

import java.util.List;

public class NewH5TitleViewImpl extends AbsTitleView {


    private H5TitleBarFrameLayout content;

    private TextView mainTitleView;

    private TextView subTitleView;

    private View btnBack;

    private View optionContainer;

    private View options1;

    private View btHome;

    private Context context;


    public NewH5TitleViewImpl(Context context) {
        ViewGroup parent = null;
        this.context = context;
        if (context instanceof Activity) {
            parent = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        }
        content = (H5TitleBarFrameLayout) LayoutInflater.from(context).inflate(R.layout.h5_new_title_layout, parent, false);
        content.getContentBgView().setColor(context.getResources().getColor(R.color.h5_default_titlebar_color));
        content.setTag("h5_titlebar");
        mainTitleView = (TextView) content.findViewById(R.id.mainTitle);
        subTitleView = (TextView) content.findViewById(R.id.subTitle);
        btnBack = content.findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokePageBackEvent();
            }
        });
        optionContainer = content.findViewById(R.id.options);
        btHome = content.findViewById(R.id.home);
        int statusBarHeight = H5StatusBarUtils.getStatusBarHeight(context);
        content.setPadding(0, statusBarHeight, 0, 0);
        btHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeHomeClickEvent();
            }
        });
        options1 = content.findViewById(R.id.options1);
        options1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeOptionClickEvent(1, false);
            }
        });
    }

    @Override
    public int getBackgroundColor() {
        return content.getContentBgView().getColor();
    }

    @Override
    public void setBackgroundAlphaValue(int i) {
        content.getContentBgView().setAlpha(i);
    }

    @Override
    public void setBackgroundColor(int i) {
        content.getContentBgView().setColor(i);
    }

    @Override
    public String getTitle() {
        return mainTitleView.getText().toString();
    }

    @Override
    public void setTitle(String s) {
        mainTitleView.setText(s);
    }

    @Override
    public void setSubTitle(String s) {
        subTitleView.setVisibility(TextUtils.isEmpty(s) ? View.GONE : View.VISIBLE);
        subTitleView.setText(s);
    }

    @Override
    public void setTitleImage(Bitmap bitmap) {

    }

    @Override
    public TextView getMainTitleView() {
        return mainTitleView;
    }

    @Override
    public TextView getSubTitleView() {
        return subTitleView;
    }

    @Override
    public void resetTitle() {
        content.getContentBgView().setColor(context.getResources().getColor(R.color.h5_default_titlebar_color));
    }

    @Override
    public void showCloseButton(boolean b) {

    }

    @Override
    public View getContentView() {
        return content;
    }

    @Override
    public void showBackButton(boolean b) {
        btnBack.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showBackHome(boolean b) {
        btHome.setVisibility(b ? View.VISIBLE : View.GONE);
    }


    @Override
    public void showOptionMenu(boolean b) {
        options1.setVisibility(b ? View.VISIBLE : View.GONE);
    }

    @Override
    public View getOptionMenuContainer(int i) {
        if (i == 1) {
            return options1;
        }
        return optionContainer;
    }

    @Override
    public void setOptionMenu(boolean reset, boolean override, boolean isTinyApp, List<MenuData> menus) {
        for (int i = 0; i < 2 && i < menus.size(); i++) {
            MenuData menuData = menus.get(i);
            if (isTinyApp) {
                String iconUrl = menuData.getIcon();
                if (!TextUtils.isEmpty(iconUrl)) {
                    H5ImageUtil.loadImage(iconUrl, new H5ImageListener() {
                        @Override
                        public void onImage(Bitmap bitmap) {
                            ((ImageView) options1.findViewById(R.id.o1image)).setImageBitmap(bitmap);
                        }
                    });
                }
            }
        }
    }


    @Override
    public void showTitleLoading(boolean b) {

    }

    @Override
    public View getPopAnchor() {
        return optionContainer;
    }
}
