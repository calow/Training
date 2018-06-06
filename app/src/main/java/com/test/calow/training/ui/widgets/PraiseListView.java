package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.test.calow.training.R;
import com.test.calow.training.ui.model.FavoriteItem;

import java.util.List;

/**
 * Created by calow on 18/5/31.
 */

public class PraiseListView extends TextView {

    private int itemColor;
    private int itemSelectorColor;
    private List<FavoriteItem> datas;
    private OnItemClickListener onItemClickListener;

    public PraiseListView(Context context) {
        super(context);
    }

    public PraiseListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public PraiseListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }

    public void initAttrs(AttributeSet attrs){
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PraiseListView, 0, 0);

        try {
            itemColor = a.getColor(R.styleable.PraiseListView_item_color, getResources().getColor(R.color.praise_item_default));
            itemSelectorColor = a.getColor(R.styleable.PraiseListView_item_selector_color, getResources().getColor(R.color.praise_item_selector_default));
        } finally {
            a.recycle();
        }
    }

    public List<FavoriteItem> getDatas() {
        return datas;
    }

    public void setDatas(List<FavoriteItem> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        SpannableStringBuilder builder = new SpannableStringBuilder();
        if (datas != null && datas.size() > 0){
            builder.append(getImageSpan());
            for (int i = 0; i < datas.size(); i++){
                builder.append(getClickSpan(datas.get(i).getUser().getName(), i));
                if (i != datas.size() -1){
                    builder.append(", ");
                }
            }
        }
        setText(builder);
        setMovementMethod(new CircleMovementMethod(getContext(), itemSelectorColor));
    }

    public SpannableString getImageSpan(){
        String text = "  ";
        SpannableString imageSpan = new SpannableString(text);
        imageSpan.setSpan(new ImageSpan(getContext(), R.drawable.icon_praise, DynamicDrawableSpan.ALIGN_BASELINE), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return imageSpan;
    }

    public SpannableString getClickSpan(String name, final int position){
        SpannableString clickSpan = new SpannableString(name);
        clickSpan.setSpan(new SpannableClickable(itemColor) {
            @Override
            public void onClick(View widget) {
                if (onItemClickListener != null){
                    onItemClickListener.onclick(position);
                }
            }
        }, 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return clickSpan;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onclick(int position);
    }

}
