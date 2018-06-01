package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.calow.training.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by calow on 18/5/31.
 */

public class ExpandTextView extends LinearLayout {

    public static final String TAG = "ExpandTextView";
    public static final int DEFAULT_MAX_LINES = 3;

    private boolean isExpand;
    private ExpandStatusListener mListener;

    @InjectView(R.id.tv_content)
    TextView tvContent;
    @InjectView(R.id.tv_plus)
    TextView tvPlus;

    private int showLines;

    public ExpandTextView(Context context) {
        super(context);
        initView();
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        initView();
    }

    public ExpandTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
    }

    public void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandTextView, 0, 0);
        try {
            showLines = a.getInt(R.styleable.ExpandTextView_showLines, DEFAULT_MAX_LINES);
        } finally {
            a.recycle();
        }
    }

    public void initView() {
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_magic_text, this);
        ButterKnife.inject(this);

        if (showLines > 0) {
            tvContent.setMaxLines(showLines);
        }
    }

    @OnClick({R.id.tv_content, R.id.tv_plus})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_content:
                break;
            case R.id.tv_plus:
                String text = tvPlus.getText().toString().trim();
                if (text.equals("全文")){
                    tvContent.setMaxLines(Integer.MAX_VALUE);
                    tvPlus.setText("收起");
                    setExpand(true);
                } else {
                    tvContent.setMaxEms(showLines);
                    tvPlus.setText("全文");
                    setExpand(false);
                }
                if (mListener != null){
                    mListener.statusChange(isExpand());
                }
                break;
        }
    }

    public void setText(CharSequence content){
        tvContent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                tvContent.getViewTreeObserver().removeOnPreDrawListener(this);
                int lineCount = tvContent.getLineCount();
                if (lineCount > showLines){
                    if (isExpand){
                        tvContent.setMaxLines(Integer.MAX_VALUE);
                        tvPlus.setText("收起");
                    } else {
                        tvContent.setMaxLines(showLines);
                        tvPlus.setText("全文");
                    }
                    tvPlus.setVisibility(View.VISIBLE);
                } else {
                    tvPlus.setVisibility(View.GONE);
                }
                return true;
            }
        });
        tvContent.setText(content);
        tvContent.setMovementMethod(new CircleMovementMethod(getContext(), getResources().getColor(R.color.name_selector_color)));
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public void setmListener(ExpandStatusListener mListener) {
        this.mListener = mListener;
    }

    public interface ExpandStatusListener{

        void statusChange(boolean isExpand);
    }

}
