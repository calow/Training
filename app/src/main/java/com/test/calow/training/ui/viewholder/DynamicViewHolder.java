package com.test.calow.training.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.calow.training.R;
import com.test.calow.training.ui.widgets.CommentListView;
import com.test.calow.training.ui.widgets.ExpandTextView;
import com.test.calow.training.ui.widgets.PraiseListView;

/**
 * Created by calow on 18/6/5.
 */

public abstract class DynamicViewHolder extends RecyclerView.ViewHolder {

    public final static int TYPE_URL = 1;
    public final static int TYPE_IMAGE = 2;
    public final static int TYPE_VIDEO = 3;

    public int viewType;
    public ImageView headIv;
    public TextView nameTv;
    public TextView urlTipTv;

    public ExpandTextView contentTv;
    public TextView timeTv;
    public TextView deleteTv;
    public ImageView snsIv;

    public LinearLayout commentBody;

    public PraiseListView praiseListView;
    public CommentListView commentListView;
    public View digView;

    public DynamicViewHolder(View itemView, int viewType) {
        super(itemView);
        this.viewType = viewType;

        initView(itemView);
    }

    public void initView(View view){

        headIv = view.findViewById(R.id.iv_head);
        nameTv = view.findViewById(R.id.tv_name);
        urlTipTv = view.findViewById(R.id.tv_urlTip);

        contentTv = view.findViewById(R.id.etv_content);
        timeTv = view.findViewById(R.id.tv_time);
        deleteTv = view.findViewById(R.id.tv_delete);
        snsIv = view.findViewById(R.id.iv_sns);

        commentBody = view.findViewById(R.id.ll_common);

        praiseListView = view.findViewById(R.id.praiseListView);
        commentListView = view.findViewById(R.id.commentListView);

        digView = view.findViewById(R.id.lin_dig);

        ViewStub viewStub = view.findViewById(R.id.view_stub);
        initViewStub(viewType, viewStub);

    }

    public abstract void initViewStub(int viewType, ViewStub viewStub);

}
