package xlr.com.mgapp.activity;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ScrollView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Response;
import xlr.com.mgapp.R;
import xlr.com.mgapp.bean.JsonQuestBean;
import xlr.com.mgapp.bean.QuestBean;
import xlr.com.mgapp.bean.QuestBean2;
import xlr.com.mgapp.constants.Config;
import xlr.com.mgapp.constants.SPkey;
import xlr.com.mgapp.fragment.practise2Fragment;
import xlr.com.mgapp.fragment.practiseFragment;
import xlr.com.mgapp.utils.LogUtils;
import xlr.com.mgapp.utils.SPUtils;
import xlr.com.mgapp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class practise2Activity extends BaseActivity implements Chronometer.OnChronometerTickListener {
    @BindView(R.id.vp_answer)
    ViewPager vp_answer;
    @BindView(R.id._chro_exam)
    Chronometer chronometer;
    @BindView(R.id._btn_collect)
    Button collectBtn;

    @BindView(R.id.weather_layout)
    ScrollView weatherLayout;

    private practise2Fragment practisefragment;
    private QuestBean2 questBeanQ;
    private List<QuestBean2> questList;
    private ArrayList<Fragment> fragmentlists;
    private int minute = 0;
    private int second = 0;
    private ArrayList<String> a;
    private int nowpager = 0;
    private List<QuestBean> messages;
    private String type;

    @Override
    void getPreIntent() {
//        获取传递来的变量
        questList = (List<QuestBean2>) getIntent().getSerializableExtra("list");
    }

    @Override
    int getLayoutId() {
        return R.layout.activity_practise;
    }

    @Override
    void initView() {
        //联网获取数据
        vp_answer.setOnPageChangeListener(new MyOnPageChangeListener());
        weatherLayout.setVisibility(View.VISIBLE);
        setChronometer();
        collectBtn.setText("已收藏");
        collectBtn.setEnabled(false);
        cshdata();

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
     *
     */
    private void cshdata() {
        a = new ArrayList<>();
        fragmentlists = new ArrayList<>();
        for (int i = 0; i < questList.size(); i++) {
            questBeanQ = questList.get(i);
            questBeanQ.setId(questList.get(i).getId());
            practisefragment = new practise2Fragment(questBeanQ);
            fragmentlists.add(practisefragment);
            a.add(questBeanQ.getId() + "");
        }
        //设置适配器
        vp_answer.setAdapter(new practise2Activity.MainAdapter(getSupportFragmentManager()));
    }

    @OnClick({R.id._btn_previous, R.id._btn_collect, R.id._btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击上一题按钮
            case R.id._btn_previous:
                if (nowpager > 0) {
                    vp_answer.setCurrentItem(--nowpager);
                } else {
                    ToastUtils.showShort("已经到头啦!");
                }
                break;
            //收藏
            case R.id._btn_collect:
                String userName = (String) SPUtils.get(practise2Activity.this, SPkey.UserName, "");
                int id = (int) messages.get(nowpager).getId();
                delectCollect(userName, id);
                collectBtn.setText("取消收藏");
                Toast.makeText(practise2Activity.this, "取消成功", Toast.LENGTH_SHORT).show();
                break;
            //点击下一题按钮
            case R.id._btn_next:
                if (nowpager < fragmentlists.size() - 1) {
                    vp_answer.setCurrentItem(++nowpager);
                } else {
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

        }
    }

    //添加收藏
    private void delectCollect(String username, int id) {
        //联网
        OkGo.get(Config.URL_GET_noCollect)
                .params("useNmame", username)
                .params("qid", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        Toast.makeText(practise2Activity.this, "网络繁忙，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
