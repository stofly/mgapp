package xlr.com.mgapp.fragment;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.*;

import xlr.com.mgapp.R;
import xlr.com.mgapp.bean.QuestBean;
import xlr.com.mgapp.db.LoveDao;
import xlr.com.mgapp.utils.DeleteComUtils;
import xlr.com.mgapp.utils.LogUtils;

import java.util.Set;

/**
 * @ 描述： 答题fragment
 */

@SuppressLint("ValidFragment")
public class AnswerFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    private LinearLayout linearLayout2;
    private CheckBox cb_option_a, cb_option_b, cb_option_c, cb_option_d;
    private RadioButton rb_option_a;
    private RadioButton rb_option_b;
    private RadioButton rb_option_c;
    private RadioButton rb_option_d;
    private String option = "";
    private String option1 = "";
    private String option2 = "";
    private RadioGroup rg_base;
    private TextView tv_title;
    QuestBean questBean = null;
    View view;

    public AnswerFragment(QuestBean questBean) {
        this.questBean = questBean;
    }

    @Override
    protected View initView() {
        view = View.inflate(mActivity, R.layout.fragment_quest, null);
        tv_title = (TextView) view.findViewById(R.id._tv_title);
        linearLayout2 = view.findViewById(R.id.ll_chosses);
        rg_base = (RadioGroup) view.findViewById(R.id._rg_base);
        LogUtils.e("initView: " + questBean.getType());
        //获取资源单选
        rb_option_a = (RadioButton) view.findViewById(R.id._rb_option_a);
        rb_option_b = (RadioButton) view.findViewById(R.id._rb_option_b);
        rb_option_c = (RadioButton) view.findViewById(R.id._rb_option_c);
        rb_option_d = (RadioButton) view.findViewById(R.id._rb_option_d);
        //获取资源多选
        cb_option_a = view.findViewById(R.id._cb_option_a);
        cb_option_b = view.findViewById(R.id._cb_option_b);
        cb_option_c = view.findViewById(R.id._cb_option_c);
        cb_option_d = view.findViewById(R.id._cb_option_d);
        //如果是选择题，找id,设置监听事件
        if ("1".equals(questBean.getType() + "")) {
            linearLayout2.setVisibility(View.GONE);
            LogUtils.e("initView: " + questBean.getType());
            rg_base.setOnCheckedChangeListener(this);
        }
        //如果是多选题，找id,设置监听事件
        if ("4".equals(questBean.getType() + "")) {
            rg_base.setVisibility(View.GONE);
            LogUtils.e("initView: " + questBean.getType());
            cb_option_a.setOnCheckedChangeListener(this);
            cb_option_b.setOnCheckedChangeListener(this);
            cb_option_c.setOnCheckedChangeListener(this);
            cb_option_d.setOnCheckedChangeListener(this);

        }
        //如果是判断题，找id,使C,D选项不可见，设置监听事件
        else if ("2".equals(questBean.getType() + "")) {
            linearLayout2.setVisibility(View.GONE);
            LogUtils.e("initView: " + questBean.getType());
            //CD不可见
            rb_option_c.setVisibility(View.GONE);
            rb_option_d.setVisibility(View.GONE);
            //监听事件
            rg_base.setOnCheckedChangeListener(this);
        }

        //如果是简答题，找id,使选项组不可见，使EditText出现。
        else if ("3".equals(questBean.getType() + "")) {
            linearLayout2.setVisibility(View.GONE);
            rg_base.setVisibility(View.GONE);
            LogUtils.e("initView: " + questBean.getType());
        }
        return view;
    }

    @Override
    public void initData() {
        tv_title.setText("" + questBean.getTitle());
        //如果没有传递数据，则退出
        if (questBean == null) {
            LogUtils.e("initData: questBean==null");
            return;
        }
//        如果是单项选择题，对应选项赋值
        if ("1".equals(questBean.getType() + "")) {
            rb_option_a.setText("" + questBean.getOptiona());
            rb_option_b.setText("" + questBean.getOptionb());
            rb_option_c.setText("" + questBean.getOptionc());
            rb_option_d.setText("" + questBean.getOptiond());
        }
        // 如果是多项选择题，对应选项赋值
        else if ("4".equals(questBean.getType() + "")) {
            cb_option_a.setText("" + questBean.getOptiona());
            cb_option_b.setText("" + questBean.getOptionb());
            cb_option_c.setText("" + questBean.getOptionc());
            cb_option_d.setText("" + questBean.getOptiond());
        }
        //对应判断题
        else if ("2".equals(questBean.getType() + "")) {
            rb_option_a.setText("对");
            rb_option_b.setText("错");
        }
        //如果是简答题,答案填充
        else {
        }
    }

    //单选
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == rb_option_a.getId()) {
            option = "A";
        } else if (checkedId == rb_option_b.getId()) {
            option = "B";
        } else if (checkedId == rb_option_c.getId()) {
            option = "C";
        } else if (checkedId == rb_option_d.getId()) {
            option = "D";
        }
//        设置答案
        questBean.setMyanswer(option);
//      数据库更新数据
        LoveDao.updateLove(questBean);
    }
    //多选按钮
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            //用户点击的复选框的内容
            String text = buttonView.getText().toString();
            option1 += text.substring(0, text.indexOf("、"));
            System.out.println("text+=" + option1);

        }
        if (!isChecked) {
            String text = buttonView.getText().toString();
            option2 += text.substring(0, text.indexOf("、"));
            System.out.println("text-=" + option2);
        }
//        option= DeleteComUtils.DeleteCom(option1, option2);
//        //设置答案
        questBean.setMyanswer(option);
//        //数据库更新数据
        LoveDao.updateLove(questBean);
    }
}
