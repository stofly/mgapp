package xlr.com.mgapp.activity.tablebar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.canking.minipay.MiniPayUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Response;
import xlr.com.mgapp.R;
import xlr.com.mgapp.bean.AnnListBean;
import xlr.com.mgapp.bean.AnnouncementBean;
import xlr.com.mgapp.utils.MarqueeTextView;
import com.canking.minipay.Config;

import java.util.ArrayList;
import java.util.List;


public class Style3Activity extends AppCompatActivity {
    //公告
    List<AnnouncementBean> announcementBean;

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    // 要申请的权限
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};
    private AlertDialog dialog;


    public Style3Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style3);
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                showDialogTipUserRequestPermission();
            }
        }
        initView();
    }

    // 提示用户该请求权限的弹出框
    private void showDialogTipUserRequestPermission() {

        new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("由于更新app需要获取相关权限，否则，您将无法正常使用")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }
    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }


    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        showDialogTipUserGoToAppSettting();
                    } else
                        finish();
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // 提示用户去应用设置界面手动开启权限

    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("存储权限不可用")
                .setMessage("请在-应用设置-权限-中，允许使用存储权限来保存用户数据")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initView() {
        mTabRadioGroup = findViewById(R.id.tabs_rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.today_tab, BlankFragment.newInstance("首页"));
        mFragmentSparseArray.append(R.id.record_tab, BlankFragment.newInstance("练习"));
        mFragmentSparseArray.append(R.id.contact_tab, BlankFragment.newInstance("收藏"));
        mFragmentSparseArray.append(R.id.settings_tab, BlankFragment.newInstance("关于"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // 具体的fragment切换逻辑可以根据应用调整，例如使用show()/hide()
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        // 默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,
                mFragmentSparseArray.get(R.id.record_tab)).commit();
        findViewById(R.id.sign_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiniPayUtils.setupPay(Style3Activity.this, new Config.Builder("fkx07077wblesl0o39pvy2d", R.mipmap.ic_zhifubao, R.mipmap.ic_weixin).build());
            }
        });
    }

    //网络请求公告
    public void initNet(final MarqueeTextView marqueeTv) {
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(Style3Activity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("获取公告中...");
        progressDialog.show();
        //联网
        OkGo.get("http://101.132.145.89/api/announcement/list")
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //gson解析公告
                        Gson gson = new Gson();
                        AnnListBean annListBean = gson.fromJson(s, AnnListBean.class);
                        announcementBean = annListBean.getAnnouncements();
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < announcementBean.size(); i++) {
                            list.add(announcementBean.get(i).getContent() + "");
                        }
                        String[] textArrays = list.toArray(new String[list.size()]);
                        //将数组中的东西列再公告栏
                        marqueeTv.setTextArraysAndClickListener(textArrays);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(Style3Activity.this, "网络繁忙，请稍后重试", Toast.LENGTH_SHORT);
                    }
                });
    }
    //**

}
