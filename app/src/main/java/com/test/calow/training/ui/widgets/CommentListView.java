package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.calow.training.R;
import com.test.calow.training.ui.model.CommentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calow on 18/5/31.
 */

public class CommentListView extends LinearLayout {

    public static final String TAG = CommentListView.class.getSimpleName();
    private int itemColor;
    private int itemSelectorColor;

    private List<CommentEntity> mDatas;
    private LayoutInflater layoutInflater;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public CommentListView(Context context) {
        super(context);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public CommentListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

    public OnItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public OnItemLongClickListener getItemLongClickListener() {
        return itemLongClickListener;
    }

    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    public List<CommentEntity> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<CommentEntity> mDatas) {
        if (mDatas == null){
            mDatas = new ArrayList<CommentEntity>();
        }
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged(){
        removeAllViews();
        if (mDatas == null || mDatas.size() == 0){
            return;
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        for (int i  = 0; i < mDatas.size(); i++){
            final int index = i;
            View view = getView(index);
            if (view == null){
                throw new NullPointerException("ListView item layout is null, please check getView()...");
            }
            addView(view, index, lp);
        }
    }

    public View getView(final int index){
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(getContext());
        }
        View converView = layoutInflater.inflate(R.layout.item_comment, null, false);

        TextView tv = converView.findViewById(R.id.tv_comment_item);
        final CircleMovementMethod method = new CircleMovementMethod(itemSelectorColor, itemSelectorColor);

        final CommentEntity entity = mDatas.get(index);
        String id = entity.getId();

        String fromName = entity.getUser().getName();
        String replyName = "";
        if (entity.getToReplyUser() != null){
            replyName = entity.getToReplyUser().getName();
        }
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(getClickableSpan(fromName, entity.getUser().getId()));

        if (!TextUtils.isEmpty(replyName)){
            builder.append(" 回复 ");
            builder.append(getClickableSpan(replyName, entity.getToReplyUser().getId()));
        }
        builder.append(": ");
        //for emoji
        builder.append(entity.getContent());
        tv.setText(builder);
        tv.setMovementMethod(method);

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (method.isPassToTextView()) {
                    if (itemClickListener != null){
                        itemClickListener.onItemClick(index);
                    }
                }
            }
        });

        tv.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (method.isPassToTextView()){
                    if (itemLongClickListener != null){
                        itemLongClickListener.onItemLongClick(index);
                    }
                    return true;
                }
                return false;
            }
        });

        return converView;
    }

    public SpannableString getClickableSpan(final String name, final String id){
        SpannableString clickableSpan = new SpannableString(name);
        clickableSpan.setSpan(new SpannableClickable(itemColor) {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getContext(), name + "&id = " + id, Toast.LENGTH_SHORT).show();
            }
        }, 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return clickableSpan;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }

}
