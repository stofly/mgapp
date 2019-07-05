package xlr.com.mgapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Response;
import xlr.com.mgapp.R;
import xlr.com.mgapp.activity.tablebar.Style3Activity;
import xlr.com.mgapp.bean.JsonQuestBean;
import xlr.com.mgapp.bean.QuestBean;
import xlr.com.mgapp.constants.Config;
import xlr.com.mgapp.constants.SPkey;
import xlr.com.mgapp.db.LoveDao;
import xlr.com.mgapp.fragment.AnswerFragment;
import xlr.com.mgapp.fragment.practiseFragment;
import xlr.com.mgapp.utils.LogUtils;
import xlr.com.mgapp.utils.SPUtils;
import xlr.com.mgapp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class practiseActivity extends BaseActivity implements Chronometer.OnChronometerTickListener {
    @BindView(R.id.vp_answer)
    ViewPager vp_answer;
    @BindView(R.id._chro_exam)
    Chronometer chronometer;
    @BindView(R.id._btn_collect)
    Button collectBtn;
    @BindView(R.id. nav_button2_home)
    Button homeBtn;

    private practiseFragment practisefragment;
    private QuestBean questBeanQ;
    private ArrayList<Fragment> fragmentlists;
    private int minute = 0;
    private int second = 0;
    private ArrayList<String> a;
    private int nowpager = 0;
    private List<QuestBean> messages;
    private String type;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Bundle b = msg.getData();
            }
        }
    };

    @Override
    void getPreIntent() {
//        获取传递来的变量
        type = getIntent().getExtras().get("type").toString().trim();
    }

    @Override
    int getLayoutId() {
        System.out.println("这是练习的");
        return R.layout.activity_practise;
    }

    @Override
    void initView() {
        //联网获取数据
        initNet(type);
        vp_answer.setOnPageChangeListener(new MyOnPageChangeListener());
        setChronometer();
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActity = new Intent(practiseActivity.this, Style3Activity.class);
                startActivity(homeActity);
            }
        });
    }
    /**
     * 设置计时器
     */
    private void setChronometer() {
        chronometer.setText(nowtime());
        chronometer.start();
        chronometer.setOnChronometerTickListener(this);
    }

    /**
     * 计时器规则
     *
     * @param chronometer
     */
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        second++;
        if (second == 59) {
            minute++;
            second = 00;
        }
    }

    /**
     * 现在时间
     *
     * @return
     */
    private String nowtime() {
        if (second < 10) {
            return (minute + ":0" + second);
        } else {
            return (minute + ":" + second);
        }
    }

    /**
     * 初始化网络连接
     *
     * @param type
     */
    private void initNet(String type) {
        a = new ArrayList<>();
        fragmentlists = new ArrayList<>();
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(practiseActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("获取题目中...");
        progressDialog.show();
        //联网
        OkGo.get(Config.URL_GET_QUESTION)
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        //gson解析
                        Gson gson = new Gson();
                        JsonQuestBean jsonQuestBean = gson.fromJson(s, JsonQuestBean.class);
                        messages = jsonQuestBean.getMessages();
                        for (int i = 0; i < messages.size(); i++) {
                            questBeanQ = messages.get(i);
                            questBeanQ.setId(messages.get(i).getId());
                            practisefragment = new practiseFragment(questBeanQ);
                            fragmentlists.add(practisefragment);
                            a.add(questBeanQ.getId() + "");
                        }
                        //设置适配器
                        vp_answer.setAdapter(new practiseActivity.MainAdapter(getSupportFragmentManager()));
                        progressDialog.dismiss();
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(practiseActivity.this, "网络繁忙，请稍后重试", Toast.LENGTH_SHORT);
                    }
                });
    }

    @OnClick({R.id._btn_previous, R.id._btn_collect, R.id._btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击上一题按钮
            case R.id._btn_previous:
                if (nowpager > 0) {
                    vp_answer.setCurrentItem(--nowpager);
                }else{
                    ToastUtils.showShort("已经到头啦!");
                }
                break;
            //收藏
            case R.id._btn_collect:
                String userName = (String) SPUtils.get(practiseActivity.this, SPkey.UserName, "");
                int id = (int) messages.get(nowpager).getId();
                addCollect(userName,id);
                Toast.makeText(practiseActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                break;
            //点击下一题按钮
            case R.id._btn_next:
                if(nowpager<fragmentlists.size()-1){
                    vp_answer.setCurrentItem(++nowpager);
                }else {
                    ToastUtils.showShort("已经是最后一题了!");
                }
                break;
        }
    }

    /**
     * viewpager适配器
     */
    class MainAdapter extends FragmentPagerAdapter {

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        //获取条目
        @Override
        public Fragment getItem(int position) {
            return fragmentlists.get(position);
        }

        //数目
        @Override
        public int getCount() {
            return fragmentlists.size();
        }
    }

    /**
     * viewpager监听事件
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            nowpager = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            Toast.makeText(practiseActivity.this, nowpager+1+"/"+fragmentlists.size(), Toast.LENGTH_SHORT).show();
            System.out.println("当前题号"+nowpager);
            System.out.println("总数量"+fragmentlists.size());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = Message.obtain();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    Bundle b = new Bundle();
                    b.putInt("nowData",nowpager);
                    b.putInt("allData",fragmentlists.size());
                }
            }).start();
        }
    }

    //添加收藏
    private void addCollect(String username,int id) {
        //联网
        OkGo.get(Config.URL_GET_Collect)
                .params("useNmame", username)
                .params("qid", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(practiseActivity.this, "网络繁忙，请稍后重试", Toast.LENGTH_SHORT);
                    }
                });
    }

}
