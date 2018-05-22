package com.test.calow.training.ui.custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test.calow.training.R;
import com.test.calow.training.util.SystemMethod;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by calow on 18/5/18.
 */

public class TitleBarView extends RelativeLayout {

    public static final String TAG = "TitleBarView";
    private Context mContext;
    private OnClickListener leftListener, rightListener;
    @InjectView(R.id.btn_left)
    Button btnLeft;
    @InjectView(R.id.btn_right)
    Button btnRight;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.rl_common_title)
    RelativeLayout rlCommonTitle;

    public TitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.common_title_bar, this);
        ButterKnife.inject(view);
    }

    @OnClick({R.id.btn_left, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_left:
                if (leftListener != null){
                    leftListener.onClick(view);
                }
                break;
            case R.id.btn_right:
                if (rightListener != null){
                    rightListener.onClick(view);
                }
                break;
        }
    }

    public void setCommonTitle(int leftVisibility,int titleVisibility,int rightVisibility){
        btnLeft.setVisibility(leftVisibility);
        btnRight.setVisibility(rightVisibility);
        tvTitle.setVisibility(titleVisibility);
    }

    public void setBtnLeft(int icon, int texRes){
        Drawable drawable = mContext.getResources().getDrawable(icon);
        int height = SystemMethod.dip2px(mContext, 20);
        int width = drawable.getIntrinsicWidth() * height / drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, width, height);
        btnLeft.setText(texRes);
        btnLeft.setCompoundDrawables(drawable, null, null, null);
    }

    public void setBtnLeft(int txtRes){
        btnLeft.setText(txtRes);
    }

    public void setBtnRight(int icon){
        Drawable img=mContext.getResources().getDrawable(icon);
        int height=SystemMethod.dip2px(mContext, 30);
        int width=img.getIntrinsicWidth()*height/img.getIntrinsicHeight();
        img.setBounds(0, 0, width, height);
        btnRight.setCompoundDrawables(img, null, null, null);
    }

    public void setTitleText(int txtRes){
        tvTitle.setText(txtRes);
    }

    public void setTitleText(String txtRes){
        tvTitle.setText(txtRes);
    }

    public void setBtnLeftClickListener(OnClickListener listener){
        leftListener = listener;
    }

    public void setBtnRightClickListener(OnClickListener listener){
        rightListener = listener;
    }

}
