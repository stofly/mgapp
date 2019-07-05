package xlr.com.mgapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.TextView;
import xlr.com.mgapp.R;

/**
 * @author 青铜骑士
 * @ClassName: ConfirmDialog
 * @ProjectName mgapp
 * @Description: TODO
 * @date 2019/6/271:06
 */
public class ConfirmDialog extends Dialog {
    private Context context;
    private TextView titleTv,contentTv;
    private View okBtn,cancelBtn;
    private OnDialogClickListener dialogClickListener;

    public ConfirmDialog(Context context,String title,String content) {
        super(context);
        this.context = context;
        initalize(title,content);
    }

    //初始化View
    private void initalize(String title,String content) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.confirm_dialog_download, null);
        setContentView(view);
        initWindow();

        titleTv = (TextView) findViewById(R.id.title_name);
        titleTv.setText(title);
        contentTv = (TextView) findViewById(R.id.text_view);
        contentTv.setText(content);
        okBtn = findViewById(R.id.btn_ok);
        cancelBtn = findViewById(R.id.btn_cancel);
        okBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                if(dialogClickListener != null){
                    dialogClickListener.onOKClick();
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
                if(dialogClickListener != null){
                    dialogClickListener.onCancelClick();
                }
            }
        });
    }

    /**
     *添加黑色半透明背景
     */
    private void initWindow() {
        Window dialogWindow = getWindow();
        dialogWindow.setBackgroundDrawable(new ColorDrawable(0));//设置window背景
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//设置输入法显示模式
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();//获取屏幕尺寸
        lp.width = (int) (d.widthPixels * 0.8); //宽度为屏幕80%
        lp.gravity = Gravity.CENTER;  //中央居中
        dialogWindow.setAttributes(lp);
    }

    public void setOnDialogClickListener(OnDialogClickListener clickListener){
        dialogClickListener = clickListener;
    }

    /**
     *添加按钮点击事件
     */
    public interface OnDialogClickListener{
        void onOKClick();
        void onCancelClick();
    }
}
