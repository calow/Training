package com.test.calow.training.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.calow.training.R;
import com.test.calow.training.ui.callback.ILoadCallback;
import com.test.calow.training.ui.model.MessageEntity;
import com.test.calow.training.ui.presenter.MessagePresenter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by calow on 18/5/21.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "MessageAdapter";
    private static final int PAGE_SIZE = 10;
    public static final int UPDATE = 1024;
    private List<MessageEntity> mDatas;
    private boolean hasMoreData = true;
    private int currentPage = 1;
    private boolean loading = false;
    private Context mContext;
    private MessagePresenter msgPresenter;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE:
                    List<MessageEntity> data = (List<MessageEntity>) msg.obj;
                    mDatas.addAll(data);
                    notifyDataSetChanged();
                    currentPage++;
                    loading = false;
                    break;
                default:
                    break;
            }
        }
    };

    public MessageAdapter(Context context) {
        this.mContext = context;
    }

    public void setPresenter(MessagePresenter presenter){
        this.msgPresenter = presenter;
    }

    public List<MessageEntity> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<MessageEntity> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == R.layout.list_item_loading){
            return new LoadingViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false));
        } else if (viewType == R.layout.list_item_no_more){
            return new NoMoreViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(viewType, parent, false));
        } else {
            return new MessageViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_list_item, null, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingViewHolder){
            if (!loading){
                loading = true;
                msgPresenter.loadMore(currentPage, PAGE_SIZE, new ILoadCallback<MessageEntity>() {
                    @Override
                    public void loadSuccess(List<MessageEntity> data) {
                        Message msg = Message.obtain();
                        msg.what = UPDATE;
                        msg.obj = data;
                        mHandler.sendMessageDelayed(msg, 100);
                    }

                    @Override
                    public void loadFailure() {
                        hasMoreData = false;
                        loading = false;
                    }
                });
            }
        } else if (holder instanceof NoMoreViewHolder){

        } else {
            MessageEntity entity = mDatas.get(position);
            MessageViewHolder messageHolder = (MessageViewHolder) holder;
            messageHolder.sender.setText(entity.getSender());
            messageHolder.content.setText(entity.getContent());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            messageHolder.data.setText(sdf.format(entity.getTime()));
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1){
            if (hasMoreData){
                return R.layout.list_item_loading;
            } else {
                return R.layout.list_item_no_more;
            }
        }
        return super.getItemViewType(position);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView sender;
        TextView content;
        TextView data;

        public MessageViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_head);
            sender = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            data = itemView.findViewById(R.id.tv_data);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

    class NoMoreViewHolder extends RecyclerView.ViewHolder {

        public NoMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

}
