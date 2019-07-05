package xlr.com.mgapp.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import xlr.com.mgapp.R;
import xlr.com.mgapp.bean.QuestBean;
import xlr.com.mgapp.utils.DeleteComUtils;
import xlr.com.mgapp.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ 描述： 答题fragment
 */

@SuppressLint("ValidFragment")
public class practiseFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private LinearLayout caselinearLayout;
    private ScrollView weatherLayout;
    private CheckBox cb_option_a, cb_option_b, cb_option_c, cb_option_d;
    private RadioButton rb_option_a;
    private RadioButton rb_option_b;
    private RadioButton rb_option_c;
    private RadioButton rb_option_d;
    private String option = "";
    private RadioGroup rg_base;
    private Button optionsubmit, optionck,exist;
    private TextView tv_title, yourans, trueans, anlyze33, anlyze44;
    private ImageView andtrue, anserror;
    List<String> arraySouList = null;
    List<String> lis=null;
    QuestBean questBean = null;
    View view;


    //消息机制
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            //单选
            if (msg.what == 1) {
                //解析模块出现
                caselinearLayout.setVisibility(View.VISIBLE);
                //正确标志出现
                andtrue.setVisibility(View.VISIBLE);
                //错误标志消失
                anserror.setVisibility(View.GONE);
                //点击完所有选项禁止点击
//                rb_option_a.setEnabled(false);
//                rb_option_b.setEnabled(false);
//                rb_option_c.setEnabled(false);
//                rb_option_d.setEnabled(false);
                //显示正确答案
                yourans.setText("【你的选择】:" + option);
                //显示题库答案
                trueans.setText("【正确答案】:" + questBean.getAnswer());
                //显示指导解析
                anlyze33.setText("【解析指导】" + questBean.getExplain());
            }
            if (msg.what == 2) {
                caselinearLayout.setVisibility(View.VISIBLE);
                andtrue.setVisibility(View.GONE);
                anserror.setVisibility(View.VISIBLE);
//                rb_option_a.setEnabled(false);
//                rb_option_b.setEnabled(false);
//                rb_option_c.setEnabled(false);
//                rb_option_d.setEnabled(false);
                yourans.setText("【你的选择】:" + option);
                trueans.setText("【正确答案】:" + questBean.getAnswer());
                anlyze33.setText("【解析指导】" + questBean.getExplain());
            }
            //简答
            if (msg.what == 3) {
                caselinearLayout.setVisibility(View.VISIBLE);
                andtrue.setVisibility(View.VISIBLE);
                anlyze44.setText(questBean.getAnswer());
                weatherLayout.setVisibility(View.VISIBLE);
            }
            //多选
            if (msg.what == 4) {
                caselinearLayout.setVisibility(View.VISIBLE);
                andtrue.setVisibility(View.VISIBLE);
                anserror.setVisibility(View.GONE);
                cb_option_a.setEnabled(false);
                cb_option_b.setEnabled(false);
                cb_option_c.setEnabled(false);
                cb_option_d.setEnabled(false);
                yourans.setText("【你的选择】:" + arraySouList);
                trueans.setText("【正确答案】:" + lis);
                anlyze33.setText("【解析指导】" + questBean.getExplain());
                weatherLayout.setVisibility(View.VISIBLE);
            }
            if (msg.what == 5) {
                caselinearLayout.setVisibility(View.VISIBLE);
                andtrue.setVisibility(View.GONE);
                anserror.setVisibility(View.VISIBLE);
                cb_option_a.setEnabled(false);
                cb_option_b.setEnabled(false);
                cb_option_c.setEnabled(false);
                cb_option_d.setEnabled(false);
                yourans.setText("【你的选择】:" + arraySouList);
                trueans.setText("【正确答案】:" + lis);
                anlyze33.setText("【解析指导】" + questBean.getExplain());
                weatherLayout.setVisibility(View.VISIBLE);
            }
        }
    };

    public practiseFragment(QuestBean questBean) {
        this.questBean = questBean;
    }

    @Override
    protected View initView() {
        //选择题
        if ("1".equals(questBean.getType() + "")) {
            view = View.inflate(mActivity, R.layout.fragment_choose, null);
            tv_title = view.findViewById(R.id._tv_title);
            rg_base = (RadioGroup) view.findViewById(R.id._rg_base);
            rb_option_a = view.findViewById(R.id._rb_option_a);
            rb_option_b = view.findViewById(R.id._rb_option_b);
            rb_option_c = view.findViewById(R.id._rb_option_c);
            rb_option_d = view.findViewById(R.id._rb_option_d);
            yourans = view.findViewById(R.id.tv_yourans);
            trueans = view.findViewById(R.id.tv_trueans);
            andtrue = view.findViewById(R.id.ig_andtrue);
            anserror = view.findViewById(R.id.ig_anserror);
            anlyze33 = view.findViewById(R.id.tv_anlyze33);
            caselinearLayout = view.findViewById(R.id.ll_chosses_case);
            rg_base.setOnCheckedChangeListener(this);
        }
        //多选题
        if ("4".equals(questBean.getType() + "")) {
            view = View.inflate(mActivity, R.layout.fragment_chooses, null);
            weatherLayout = view.findViewById(R.id.weather_layout);
            tv_title = view.findViewById(R.id._tv_title);
            cb_option_a = view.findViewById(R.id._cb_option_a);
            cb_option_b = view.findViewById(R.id._cb_option_b);
            cb_option_c = view.findViewById(R.id._cb_option_c);
            cb_option_d = view.findViewById(R.id._cb_option_d);
            yourans = view.findViewById(R.id.tv_yourans);
            trueans = view.findViewById(R.id.tv_trueans);
            andtrue = view.findViewById(R.id.ig_andtrue);
            anserror = view.findViewById(R.id.ig_anserror);
            anlyze33 = view.findViewById(R.id.tv_anlyze33);
            optionsubmit = view.findViewById(R.id._cb_option_submit);
            caselinearLayout = view.findViewById(R.id.ll_chosses_case);
            //定义存储池
            arraySouList = new ArrayList();
            //对选项A监听
            cb_option_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        arraySouList.add("A");
                    }
                    if (!isChecked) {
                        arraySouList.remove("A");
                    }
                }
            });
            //对选项B监听
            cb_option_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        arraySouList.add("B");
                    }
                    if (!isChecked) {
                        arraySouList.remove("B");
                    }
                }
            });
            //对选项C监听
            cb_option_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //用户点击的复选框的内容
                        arraySouList.add("C");
                    }
                    if (!isChecked) {
                        arraySouList.remove("C");
                    }
                }
            });
            //对选项D监听
            cb_option_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //用户点击的复选框的内容
                        arraySouList.add("D");
                    }
                    if (!isChecked) {
                        arraySouList.remove("D");
                    }
                }
            });
            //提交判断
            optionsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lis = Arrays.asList(questBean.getAnswer().split(","));
                    boolean b = DeleteComUtils.equals(arraySouList, lis);
                    if (b) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = Message.obtain();
                                msg.what = 4;
                                handler.sendMessage(msg);
                            }
                        }).start();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = Message.obtain();
                                msg.what = 5;
                                handler.sendMessage(msg);
                            }
                        }).start();
                    }
                }
            });
        }
        //判断题
        if ("2".equals(questBean.getType() + "")) {
            view = View.inflate(mActivity, R.layout.fragment_judge, null);
            tv_title = view.findViewById(R.id._tv_title);
            rg_base = (RadioGroup) view.findViewById(R.id._rg_base);
            rb_option_a = view.findViewById(R.id._rb_option_a);
            rb_option_b = view.findViewById(R.id._rb_option_b);
            yourans = view.findViewById(R.id.tv_yourans);
            trueans = view.findViewById(R.id.tv_trueans);
            andtrue = view.findViewById(R.id.ig_andtrue);
            anserror = view.findViewById(R.id.ig_anserror);
            anlyze33 = view.findViewById(R.id.tv_anlyze33);
            caselinearLayout = view.findViewById(R.id.ll_chosses_case);
            rg_base.setOnCheckedChangeListener(this);
        }

        //简答题
        if ("3".equals(questBean.getType() + "")) {
            view = View.inflate(mActivity, R.layout.fragment_discr, null);
            tv_title = view.findViewById(R.id._tv_title);
            weatherLayout = view.findViewById(R.id.weather_layout);
            andtrue = view.findViewById(R.id.ig_andtrue);
            anlyze44 = view.findViewById(R.id.tv_anlyze44);
            optionck = view.findViewById(R.id._cb_option_ck);
            caselinearLayout = view.findViewById(R.id.ll_chosses_case);
            optionck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = Message.obtain();
                            msg.what = 3;
                            handler.sendMessage(msg);
                        }
                    }).start();
                }
            });

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

        }
        //如果是简答题,答案填充
        else {

        }
    }

    //单选
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if ("1".equals(questBean.getType() + "")) {
            if (checkedId == rb_option_a.getId()) {
                option = "A";
            } else if (checkedId == rb_option_b.getId()) {
                option = "B";
            } else if (checkedId == rb_option_c.getId()) {
                option = "C";
            } else if (checkedId == rb_option_d.getId()) {
                option = "D";
            }
        }
        if ("2".equals(questBean.getType() + "")) {
            if (checkedId == rb_option_a.getId()) {
                option = "错";
            }
            if (checkedId == rb_option_b.getId()) {
                option = "对";
            }
        }
        if (option.equals(questBean.getAnswer())) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = Message.obtain();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = Message.obtain();
                    msg.what = 2;
                    handler.sendMessage(msg);
                }
            }).start();
        }
    }
}
