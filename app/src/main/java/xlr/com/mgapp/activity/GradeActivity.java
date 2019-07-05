package xlr.com.mgapp.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import xlr.com.mgapp.R;
import xlr.com.mgapp.bean.QuestBean;
import xlr.com.mgapp.db.LoveDao;


/**
 * @ 描述：分数界面
 */
public class GradeActivity extends BaseActivity {

    @BindView(R.id.tv_grade_score)
    TextView tvGradeScore;
    @BindView(R.id.lv_grade_score_detail)
    ListView lvGradeScoreDetail;

    private ArrayList<CharSequence> titleName;
    private String grade;

    @Override
    int getLayoutId() {
        return R.layout.activity_grade;
    }

    @Override
    void getPreIntent() {
//        接收传递来的数据
        titleName = getIntent().getCharSequenceArrayListExtra("timu");
        grade = getIntent().getStringExtra("grade");
    }

    @Override
    void initData() {
//        设置显示成绩分数
        tvGradeScore.setText("您的成绩是： " + grade);
//        设置适配器
        lvGradeScoreDetail.setAdapter(new MyAdapter());
    }

    /**
     * 题目列表适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titleName != null ? titleName.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return titleName.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            listview优化，复用布局以及id
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(GradeActivity.this).inflate(R.layout.list_item_activity_score, null);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_activty_score_title);
                holder.tvOptionA = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionA);
                holder.tvOptionB = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionB);
                holder.tvOptionC = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionC);
                holder.tvOptionD = (TextView) convertView.findViewById(R.id.tv_item_activty_score_optionD);
                holder.tvRightAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_right);
                holder.tvWrongAnswer = (TextView) convertView.findViewById(R.id.tv_item_activty_score_wrong);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            查询数据库
            QuestBean questBeenC = LoveDao.queryLove(Integer.parseInt(titleName.get(position).toString()));
            String title = questBeenC.getTitle();
            holder.tvTitle.setText(position + 1 + "." + title);
//            设置题目数据
            //            显示正确答案以及填写答案
            String rightAnswer = questBeenC.getAnswer();
            holder.tvRightAnswer.setText("正确答案：" + rightAnswer);

//            选择
            if (("1".equals(questBeenC.getType() + ""))) {
                String optionA = questBeenC.getOptiona();
                holder.tvOptionA.setText(optionA);
                String optionB = questBeenC.getOptionb();
                holder.tvOptionB.setText(optionB);
                String optionC = questBeenC.getOptionc();
                holder.tvOptionC.setText(optionC);
                String optionD = questBeenC.getOptiond();
                holder.tvOptionD.setText(optionD);
                String YourAnswer = questBeenC.getMyanswer();
                if(!TextUtils.isEmpty(YourAnswer)){
                    holder.tvWrongAnswer.setText("你的答案：" + YourAnswer);
                }else{
                    holder.tvWrongAnswer.setText("您未进行选择");
                }
                if (!rightAnswer.equals(YourAnswer)) {
                    holder.tvTitle.setTextColor(Color.RED);
                }else{
                    holder.tvTitle.setTextColor(Color.GREEN);
                }

            }
            //多选
            if (("4".equals(questBeenC.getType() + ""))) {
                String optionA = questBeenC.getOptiona();
                holder.tvOptionA.setText(optionA);
                String optionB = questBeenC.getOptionb();
                holder.tvOptionB.setText(optionB);
                String optionC = questBeenC.getOptionc();
                holder.tvOptionC.setText(optionC);
                String optionD = questBeenC.getOptiond();
                holder.tvOptionD.setText(optionD);
                String YourAnswer = questBeenC.getMyanswer();
                if(!TextUtils.isEmpty(YourAnswer)){
                    holder.tvWrongAnswer.setText("你的答案：" + YourAnswer);
                }else{
                    holder.tvWrongAnswer.setText("您未进行选择");
                }

                if (!rightAnswer.equals(YourAnswer)) {
                    holder.tvTitle.setTextColor(Color.RED);
                }else{
                    holder.tvTitle.setTextColor(Color.GREEN);
                }
            }
//            判断
            else if ("2".equals(questBeenC.getType() + "")) {
                holder.tvOptionA.setVisibility(View.GONE);
                holder.tvOptionB.setVisibility(View.GONE);
                holder.tvOptionC.setVisibility(View.GONE);
                holder.tvOptionD.setVisibility(View.GONE);
                String YourAnswer = questBeenC.getMyanswer();

                if (!TextUtils.isEmpty(YourAnswer)){
                    if ("A".equals(YourAnswer)) {
                        holder.tvWrongAnswer.setText("你选择了：对");
                    } else if("B".equals(YourAnswer)) {
                        holder.tvWrongAnswer.setText("你选择了：错");
                    }
                }else{
                    holder.tvWrongAnswer.setText("您未进行选择");
                }
                if (!rightAnswer.equals(YourAnswer)) {
                    holder.tvTitle.setTextColor(Color.RED);
                }else{
                    holder.tvTitle.setTextColor(Color.GREEN);
                }
            }
            return convertView;
        }
        class ViewHolder {
            TextView tvTitle, tvRightAnswer, tvWrongAnswer, tvOptionA,
                    tvOptionB, tvOptionC, tvOptionD;
        }
    }
}
