package com.test.calow.training.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.calow.training.R;
import com.test.calow.training.ui.viewholder.DynamicViewHolder;
import com.test.calow.training.ui.viewholder.ImageViewHolder;
import com.test.calow.training.ui.viewholder.URLViewHolder;
import com.test.calow.training.ui.viewholder.VideoViewHolder;
import com.test.calow.training.ui.model.CommentEntity;
import com.test.calow.training.ui.model.DynamicEntity;
import com.test.calow.training.ui.model.FavoriteItem;
import com.test.calow.training.ui.model.PhotoInfo;
import com.test.calow.training.ui.presenter.DynamicCirclePresenter;
import com.test.calow.training.ui.widgets.CommentListView;
import com.test.calow.training.ui.widgets.ExpandTextView;
import com.test.calow.training.ui.widgets.GlideDynamicTransform;
import com.test.calow.training.ui.widgets.MultiImageView;
import com.test.calow.training.ui.widgets.PraiseListView;
import com.test.calow.training.util.DataUtil;

import java.util.List;

/**
 * Created by calow on 18/5/31.
 */

public class DynamicCircleAdapter extends BaseRecyclerViewAdapter {

    public static final String TAG = "DynamicCircleAdapter";
    public final static int TYPE_HEAD = 0;
    public static final int HEADVIEW_SIZE = 1;

    private Context mContext;
    private DynamicCirclePresenter presenter;


    public DynamicCircleAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        int itemType = 0;
        DynamicEntity entity = (DynamicEntity) mDatas.get(position - 1);
        if (entity.getType().equals(DynamicEntity.TYPE_URL)) {
            itemType = DynamicViewHolder.TYPE_URL;
        } else if (entity.getType().equals(DynamicEntity.TYPE_IMG)) {
            itemType = DynamicViewHolder.TYPE_IMAGE;
        } else if (entity.getType().equals(DynamicEntity.TYPE_VIDEO)) {
            itemType = DynamicViewHolder.TYPE_VIDEO;
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_head, parent, false);
            holder = new HeadViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_circle, parent, false);
            if (viewType == DynamicViewHolder.TYPE_URL) {
                holder = new URLViewHolder(view);
            } else if (viewType == DynamicViewHolder.TYPE_IMAGE) {
                holder = new ImageViewHolder(view);
            } else if (viewType == DynamicViewHolder.TYPE_VIDEO) {
                holder = new VideoViewHolder(view);
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEAD) {
            HeadViewHolder viewHolder = (HeadViewHolder) holder;
        } else {
            final int curPosition = position - HEADVIEW_SIZE;
            final DynamicViewHolder viewHolder = (DynamicViewHolder) holder;
            final DynamicEntity entity = (DynamicEntity) mDatas.get(curPosition);
            final String dynamicId = entity.getId();
            final String name = entity.getUser().getName();
            final String headImg = entity.getUser().getHeadUrl();
            final String content = entity.getContent();
            final String createTime = entity.getCreateTime();

            final List<FavoriteItem> favortites = entity.getFavorites();
            final List<CommentEntity> comments = entity.getComments();

            boolean hasFavorite = entity.hasFavort();
            boolean hasComment = entity.hasComment();

            Glide.with(mContext).load(headImg).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.bg_no_photo).transform(new GlideDynamicTransform(mContext)).into(viewHolder.headIv);

            viewHolder.nameTv.setText(name);
            viewHolder.timeTv.setText(createTime);

            if (!TextUtils.isEmpty(content)){
                viewHolder.contentTv.setExpand(entity.isExpand());
                viewHolder.contentTv.setmListener(new ExpandTextView.ExpandStatusListener() {
                    @Override
                    public void statusChange(boolean isExpand) {
                        entity.setExpand(isExpand);
                    }
                });
                viewHolder.contentTv.setText(content);
            }
            viewHolder.contentTv.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);

            if (DataUtil.curUser.getId().equals(entity.getUser().getId())){
                viewHolder.deleteTv.setVisibility(View.VISIBLE);
            } else {
                viewHolder.deleteTv.setVisibility(View.GONE);
            }
            viewHolder.deleteTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (presenter != null){
                        presenter.deleteDynamic(dynamicId);
                    }
                }
            });

            if (hasFavorite || hasComment){
                if (hasFavorite){
                    viewHolder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onclick(int position) {
                            String name = favortites.get(position).getUser().getName();
                            String id = favortites.get(position).getId();
                            Toast.makeText(mContext, name + " &id = " + id, Toast.LENGTH_SHORT).show();
                        }
                    });
                    viewHolder.praiseListView.setDatas(favortites);
                    viewHolder.praiseListView.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.praiseListView.setVisibility(View.GONE);
                }

                if (hasComment){
                    viewHolder.commentListView.setItemClickListener(new CommentListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            CommentEntity entity = comments.get(position);
                            String toast_text = "";
                            String name = entity.getUser().getName();
                            toast_text += name;
                            if (entity.getToReplyUser() != null){
                                toast_text += "回复";
                                toast_text += entity.getToReplyUser().getName();
                            }
                            Toast.makeText(mContext, toast_text + "--click---", Toast.LENGTH_SHORT).show();
                        }
                    });

                    viewHolder.commentListView.setItemLongClickListener(new CommentListView.OnItemLongClickListener() {
                        @Override
                        public void onItemLongClick(int position) {
                            CommentEntity entity = comments.get(position);
                            String toast_text = "";
                            String name = entity.getUser().getName();
                            toast_text += name;
                            if (entity.getToReplyUser() != null){
                                toast_text += "回复";
                                toast_text += entity.getToReplyUser().getName();
                            }
                            Toast.makeText(mContext, toast_text + "--long click---", Toast.LENGTH_SHORT).show();
                        }
                    });
                    viewHolder.commentListView.setmDatas(comments);
                    viewHolder.commentListView.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.commentListView.setVisibility(View.GONE);
                }
                viewHolder.commentBody.setVisibility(View.VISIBLE);
            } else {
                viewHolder.commentBody.setVisibility(View.GONE);
            }
            viewHolder.digView.setVisibility(hasFavorite && hasComment ? View.VISIBLE : View.GONE);

            viewHolder.urlTipTv.setVisibility(View.GONE);
            switch (viewHolder.viewType) {
                case DynamicViewHolder.TYPE_URL:
                    if (viewHolder instanceof URLViewHolder){
                        URLViewHolder urlHolder = (URLViewHolder) viewHolder;
                        String linkImg = entity.getLinkImg();
                        String linkTitle = entity.getLinkTitle();
                        Glide.with(mContext).load(linkImg).into(urlHolder.urlIv);
                        urlHolder.urlTv.setText(linkTitle);
                        urlHolder.body.setVisibility(View.VISIBLE);
                        urlHolder.urlTipTv.setVisibility(View.VISIBLE);
                    }
                    break;
                case DynamicViewHolder.TYPE_IMAGE:
                    if (viewHolder instanceof ImageViewHolder){
                        ImageViewHolder imgViewHolder = (ImageViewHolder) viewHolder;
                        List<PhotoInfo> photos = entity.getPhotos();
                        if (photos != null && photos.size() > 0){
                            imgViewHolder.multiImageView.setVisibility(View.VISIBLE);
                            imgViewHolder.multiImageView.setItemClickListener(new MultiImageView.OnItemClickListener() {
                                @Override
                                public void onItemClick(View v, int position) {
                                    Toast.makeText(mContext, "item = " + position + "was clicked", Toast.LENGTH_SHORT).show();
                                }
                            });
                            imgViewHolder.multiImageView.setList(photos);
                        }
                    }
                    break;
                case DynamicViewHolder.TYPE_VIDEO:

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = mDatas.size() + HEADVIEW_SIZE;
        return count;
    }

    public DynamicCirclePresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(DynamicCirclePresenter presenter) {
        this.presenter = presenter;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {

        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }
}
