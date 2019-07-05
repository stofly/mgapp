package xlr.com.mgapp.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import xlr.com.mgapp.R;


public class MarqueeTextView extends LinearLayout {

    private Context mContext;
    private ViewFlipper viewFlipper;
    private View marqueeTextView;
    private String[] textArrays;


    public MarqueeTextView(Context context) {
        super(context);
        mContext = context;
        initBasicView();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBasicView();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setTextArraysAndClickListener(String[] textArrays) {
        this.textArrays = textArrays;
        initMarqueeTextView(textArrays);
    }

    public void initBasicView() {
        marqueeTextView = LayoutInflater.from(mContext).inflate(R.layout.marquee_textview_layout, null);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(marqueeTextView, layoutParams);

        viewFlipper = (ViewFlipper) marqueeTextView.findViewById(R.id.viewFlipper);
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
        viewFlipper.startFlipping();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void initMarqueeTextView(String[] textArrays) {
        if (textArrays.length == 0) {
            return;
        }

        int i = 0;
        viewFlipper.removeAllViews();
        while (i < textArrays.length) {
            TextView textView = new TextView(mContext);
            textView.setText(textArrays[i]);
            textView.setTextColor(Color.BLACK);
            //将字体居中
            textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            viewFlipper.addView(textView, lp);
            i++;
        }
    }

    public void releaseResources() {
        if (marqueeTextView != null) {
            if (viewFlipper != null) {
                viewFlipper.stopFlipping();
                viewFlipper.removeAllViews();
                viewFlipper = null;
            }
            marqueeTextView = null;
        }
    }

}
