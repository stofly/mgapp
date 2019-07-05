package xlr.com.mgapp.activity.tablebar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import okhttp3.Call;
import okhttp3.Response;
import util.UpdateAppUtils;
import xlr.com.mgapp.R;
import xlr.com.mgapp.activity.LoginActivity;
import xlr.com.mgapp.activity.ShowNewsActivity;
import xlr.com.mgapp.activity.practise2Activity;
import xlr.com.mgapp.activity.practiseActivity;
import xlr.com.mgapp.bean.JsonVersionBean;
import xlr.com.mgapp.bean.JsonclectorBean;
import xlr.com.mgapp.bean.ReviewBean;
import xlr.com.mgapp.bean.ReviewListBean;
import xlr.com.mgapp.constants.Config;
import xlr.com.mgapp.constants.SPkey;
import xlr.com.mgapp.utils.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;


public class BlankFragment extends Fragment {

    //时评
    private static final String ARG_SHOW_TEXT = "text";
    Integer[] images = {R.mipmap.one, R.mipmap.two, R.mipmap.three};
    private String mContentText;

    public BlankFragment() {
    }


    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SHOW_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mContentText = getArguments().getString(ARG_SHOW_TEXT);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = null;
        if (mContentText.equals("首页")) {
            rootView = inflater.inflate(R.layout.new_manage, container, false);
            Banner banner = (Banner) rootView.findViewById(R.id.banner);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            banner.setImages(Arrays.asList(images));
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            getNet(rootView);
        } else if (mContentText.equals("练习")) {
            rootView = inflater.inflate(R.layout.activity_main, container, false);
            secondPage(rootView);
        } else if (mContentText.equals("收藏")) {
            rootView = inflater.inflate(R.layout.collect_manege, container, false);
            getClector(rootView);
        } else if (mContentText.equals("关于")) {
            rootView = inflater.inflate(R.layout.fragment_about, container, false);
            fourPage(rootView);
        }
        return rootView;
    }

    //首页实时政治
    public void firstPage(final View rootView, final List<ReviewBean> net) {
        //获取新闻管理界面
        LinearLayout newManageLayout = rootView.findViewById(R.id.newManage_layout);
        //加载下拉布局
        final SwipeRefreshLayout swipeRefresh = rootView.findViewById(R.id.swipe_refresh);
        //自动增填长度
        ScrollView weatherLayout = rootView.findViewById(R.id.weather_layout);
        newManageLayout.removeAllViews();
        //获取新闻列表也买你
        for (int i = 0; i < net.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.new_item, newManageLayout, false);
            final TextView title = view.<TextView>findViewById(R.id.news_item_title);
            TextView author = view.<TextView>findViewById(R.id.news_item_author);
            TextView from = view.<TextView>findViewById(R.id.news_item_from);
            TextView date = view.<TextView>findViewById(R.id.news_item_date);
            title.setText(net.get(i).getRtitle());
            author.setText(net.get(i).getRauthor());
            from.setText("来源:" + net.get(i).getFromsour());
            date.setText(net.get(i).getRdate() + "版");
            final String rurl = net.get(i).getRurl();
            //全体布局点击事件
            LinearLayout newShowLayout = view.findViewById(R.id.news_show);
            newShowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showNewsIntent = new Intent(getActivity(), ShowNewsActivity.class);
                    showNewsIntent.putExtra("url", rurl);
                    startActivity(showNewsIntent);
                }
            });
            //将子页面添加至管理页面
            newManageLayout.addView(view);
        }

        //开启页面自动增长
        weatherLayout.setVisibility(View.VISIBLE);
        //添加下拉刷新事件
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //相当于又进行重新请求
                getNet(rootView);
                //防止出现一直刷新一直旋转
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    //练习
    public void secondPage(View rootView) {
        //加载公告
        final MarqueeTextView marqueeTv = rootView.findViewById(R.id.marqueeTv);
        final Style3Activity style3Activity = (Style3Activity) getActivity();
        style3Activity.initNet(marqueeTv);
        //公告刷新按钮
        Button button = rootView.findViewById(R.id.frash);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style3Activity.initNet(marqueeTv);
            }
        });
        //判断是否更新
        getVersion();
        //获取界面上的按钮
        //单选
        Button choose = rootView.findViewById(R.id.btn_choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseIntent = new Intent(getActivity(), practiseActivity.class);
                chooseIntent.putExtra("type", 1);
                startActivity(chooseIntent);
                System.out.println("这是单选");
            }
        });
        //多选
        Button chooses = rootView.findViewById(R.id.btn_chooses);
        chooses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent choosesIntent = new Intent(getActivity(), practiseActivity.class);
                choosesIntent.putExtra("type", 4);
                startActivity(choosesIntent);
                System.out.println("这是多选");

            }
        });
        //判断
        Button judge = rootView.findViewById(R.id.btn_judge);
        judge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent judgeIntent = new Intent(getActivity(), practiseActivity.class);
                judgeIntent.putExtra("type", 2);
                startActivity(judgeIntent);
                System.out.println("这是判断");
            }
        });
        //填空
        Button blanks = rootView.findViewById(R.id.btn_blanks);
        blanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent judgeIntent = new Intent(getActivity(), practiseActivity.class);
                judgeIntent.putExtra("type", 3);
                startActivity(judgeIntent);
                System.out.println("这是简答题");
            }
        });
    }

    //收藏
    public void threePage(View rootView, final JsonclectorBean jsonclectorBean) {
        //选择题的数量
        TextView chosseData = rootView.findViewById(R.id.choose_data);
        chosseData.setText(jsonclectorBean.getChooseData().size() + "");
        Button chossejump = rootView.findViewById(R.id.choose_jump);
        chossejump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jsonclectorBean.getChooseData().size() != 0) {
                    LogUtils.e("onSuccess: ========---------======选择题被点击了");
                    Intent toMain = new Intent(getActivity(), practise2Activity.class);
                    toMain.putExtra("list", (Serializable) jsonclectorBean.getChooseData());
                    startActivity(toMain);
                } else {
                    Toast.makeText(getActivity(), "你未添加，请去添加哦", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //多选题的数量
        TextView chossesData = rootView.findViewById(R.id.chooses_data);
        chossesData.setText(jsonclectorBean.getChoosesData().size() + "");
        Button chossesjump = rootView.findViewById(R.id.chooses_jump);
        chossesjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jsonclectorBean.getChoosesData().size() != 0) {
                    LogUtils.e("onSuccess: ========---------======多选题被点击了");
                    Intent toMain = new Intent(getActivity(), practise2Activity.class);
                    toMain.putExtra("list", (Serializable) jsonclectorBean.getChoosesData());
                    startActivity(toMain);
                } else {
                    Toast.makeText(getActivity(), "你未添加，请去添加哦", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //判断题的数量
        TextView judgData = rootView.findViewById(R.id.judg_data);
        judgData.setText(jsonclectorBean.getJudgData().size() + "");
        Button judgjump = rootView.findViewById(R.id.judg_jump);
        judgjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jsonclectorBean.getJudgData().size() != 0) {
                    LogUtils.e("onSuccess: ========---------======判断题被点击了");
                    Intent toMain = new Intent(getActivity(), practise2Activity.class);
                    toMain.putExtra("list", (Serializable) jsonclectorBean.getJudgData());
                    startActivity(toMain);
                } else {
                    Toast.makeText(getActivity(), "你未添加，请去添加哦", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //简答题的数量
        TextView tianData = rootView.findViewById(R.id.tian_data);
        tianData.setText(jsonclectorBean.getDiscrData().size() + "");
        Button tianjump = rootView.findViewById(R.id.tian_jump);
        tianjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jsonclectorBean.getDiscrData().size() != 0) {
                    LogUtils.e("onSuccess: ========---------======简答题被点击了");
                    Intent toMain = new Intent(getActivity(), practise2Activity.class);
                    toMain.putExtra("list", (Serializable) jsonclectorBean.getDiscrData());
                    startActivity(toMain);
                } else {
                    Toast.makeText(getActivity(), "你未添加，请去添加哦", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //关于
    public void fourPage(View rootView) {
        //获取用户
        String userName = (String) SPUtils.get(getActivity(), SPkey.UserName, "");
        TextView textView = rootView.findViewById(R.id.tv_username);
        textView.setText("当前账户：" + "【"+userName + "】");

        //我的资料
        LinearLayout myresLayout = rootView.findViewById(R.id.ll_myres);
        myresLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "想法正在进行中。。。。。", Toast.LENGTH_SHORT).show();
            }
        });
        //消息展示
        LinearLayout msgLayout = rootView.findViewById(R.id.ll_msg);
        msgLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "想法正在进行中。。。。。", Toast.LENGTH_SHORT).show();
            }
        });
        //退出
        LinearLayout logoutLayout = rootView.findViewById(R.id.ll_logout);
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity(),"温馨提示","是否退出当前用户？");
                confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
                    @Override
                    public void onOKClick() {
                        System.exit(0);
                        confirmDialog.dismiss();
                    }

                    @Override
                    public void onCancelClick() {
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.setCancelable(false);//点击空白处不消失
                confirmDialog.show();
            }
        });
        //切换用户
        LinearLayout swapLayout = rootView.findViewById(R.id.ll_swap);
        swapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ConfirmDialog confirmDialog = new ConfirmDialog(getActivity(),"温馨提示","是否切换用户？");
                confirmDialog.setOnDialogClickListener(new ConfirmDialog.OnDialogClickListener() {
                    @Override
                    public void onOKClick() {
                        Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(loginIntent);
                        confirmDialog.dismiss();
                    }

                    @Override
                    public void onCancelClick() {
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.setCancelable(false);//点击空白处不消失
                confirmDialog.show();
            }
        });
    }

    //网络请求时评
    public void getNet(final View rootView) {
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("获取时评中...");
        progressDialog.show();
        //联网
        OkGo.get(Config.URL_GET_Review)
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.e("onSuccess: ========---------======" + s);
                        //gson解析公告
                        Gson gson = new Gson();
                        ReviewListBean reviewListBean = gson.fromJson(s, ReviewListBean.class);
                        List<ReviewBean> reviews = reviewListBean.getReviews();
                        firstPage(rootView, reviews);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(getActivity(), "网络繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getClector(final View rootView) {
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("获取收藏中...");
        progressDialog.show();
        //联网
        OkGo.get(Config.URL_GET_CollectL)
                .params("useNmame", (String) SPUtils.get(getActivity(), SPkey.UserName, ""))
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //gson解析公告
                        Gson gson = new Gson();
                        JsonclectorBean jsonclectorBean = gson.fromJson(s, JsonclectorBean.class);
                        LogUtils.e("onSuccess: ========---------======" + jsonclectorBean);
                        threePage(rootView, jsonclectorBean);
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(getActivity(), "网络繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //获取版本号
    public void getVersion() {
        //联网
        OkGo.get(Config.URL_GET_Version)
                .execute(new StringCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //gson解析公告
                        Gson gson = new Gson();
                        final JsonVersionBean jsonVersionBean = gson.fromJson(s, JsonVersionBean.class);
                        //获取本地版本号
                        int appLocalVersion = getAPPLocalVersion(getActivity());
                        Integer appversion = jsonVersionBean.getAppversion().getAppversion();
                        if (appLocalVersion < appversion) {
                            UpdateAppUtils.from(getActivity())
                                    .checkBy(UpdateAppUtils.CHECK_BY_VERSION_NAME) //更新检测方式，默认为VersionCode
                                    .serverVersionCode(appversion)
                                    .apkPath(jsonVersionBean.getAppversion().getAppurl())
                                    .showNotification(true) //是否显示下载进度到通知栏，默认为true
                                    .downloadBy(UpdateAppUtils.DOWNLOAD_BY_APP)
                                    .update();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(getActivity(), "网络繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //图片缓存加载
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

    //版本号获取
    public static int getAPPLocalVersion(Context ctx) {
        int currentVersionCode = 0;
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            String appVersionName = info.versionName; // 版本名
            currentVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return currentVersionCode;
    }
}
