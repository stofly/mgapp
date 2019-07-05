package xlr.com.mgapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import xlr.com.mgapp.R;
import xlr.com.mgapp.activity.tablebar.Style3Activity;
import xlr.com.mgapp.constants.SPkey;
import xlr.com.mgapp.utils.SPUtils;

/**
 * @ 描述：闪屏启动页面
 */

public class SplashActivity extends BaseActivity {

    @Override
    int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    void initData() {

        //延时2s，跳转。
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username = (String) SPUtils.get(SplashActivity.this, SPkey.UserName, "");
                if (!TextUtils.isEmpty(username)) {
                    //直接跳转主页面
                    Intent intent = new Intent(SplashActivity.this, Style3Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //跳转到登录页面
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 2000);
    }

}
