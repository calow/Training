package com.test.calow.training.ui.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.test.calow.training.R;
import com.test.calow.training.ui.model.PhotoInfo;
import com.test.calow.training.util.DensityUtil;

import java.util.List;

/**
 * Created by calow on 18/6/5.
 */

public class MultiImageView extends LinearLayout {

    public static int WIDTH = 0;

    private List<PhotoInfo> photos;
    private int imagePadding = DensityUtil.dip2px(getContext(), 3);
    private int onePicWandH;
    private int morePicWandH;

    private int volumCount = 3;

    private LayoutParams onePicParam;
    private LayoutParams morePicParamFirst, morePicParam;
    private LayoutParams rowParam;

    private OnItemClickListener itemClickListener;

    public MultiImageView(Context context) {
        super(context);
    }

    public MultiImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(List<PhotoInfo> list) throws IllegalArgumentException{
        if (list == null){
            throw new IllegalArgumentException("imageList is null...");
        }
        this.photos = list;

        if (WIDTH > 0){
            morePicWandH = (WIDTH - imagePadding * 2) / 3;
            onePicWandH = WIDTH * 2 / 3;
            initImageLayoutParams();
        }
        initView();
    }

    public void initView(){
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if (WIDTH == 0){
            addView(new View(getContext()));
            return;
        }
        if (photos == null || photos.size() == 0){
            return;
        }

        if (photos.size() == 1){
            addView(createImageView(0, false));
        } else {
            int photoCount = photos.size();
            if (photoCount == 2 || photoCount == 4){
                volumCount = 2;
            } else {
                volumCount = 3;
            }
            int rowCount = photoCount / volumCount + (photoCount % volumCount > 0 ? 1 : 0);
            for (int i = 0; i < rowCount; i++){
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(rowParam);

                if (i != 0){//beside first item
                    linearLayout.setPadding(0, imagePadding, 0, 0);
                }
                addView(linearLayout);

                int volumCursor = photoCount % volumCount == 0 ? volumCount : photoCount % volumCount;
                if (i != rowCount -1){
                    volumCursor = volumCount;
                }
                int offset = i * volumCount;
                for (int j = 0; j < volumCursor; j++){
                    int position = j + offset;
                    linearLayout.addView(createImageView(position, true));
                }
            }
        }
    }

    public ImageView createImageView(int position, boolean isMultiImage){
        PhotoInfo info = photos.get(position);
        ImageView imageView = new SelectorImageView(getContext());
        if (isMultiImage){
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(position % volumCount == 0 ? morePicParamFirst : morePicParam);
        } else {
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            int expectH = info.h;
            int expectW = info.w;

            if (expectW == 0 || expectH == 0){
                imageView.setLayoutParams(onePicParam);
            } else {
                int actualW;
                int actualH;
                float scale = ((float)expectH) / ((float)expectW);
                if(expectW > onePicWandH){
                    actualW = onePicWandH;
                    actualH = (int) (actualW * scale);
                } else if (expectW < morePicWandH) {
                    actualW = morePicWandH;
                    actualH = (int) (actualW * scale);
                } else {
                    actualW = expectW;
                    actualH = expectH;
                }
                imageView.setLayoutParams(new LayoutParams(actualW, actualH));
            }
        }
        imageView.setId(info.url.hashCode());
        imageView.setOnClickListener(new ImageViewOnClickListener(position));
        imageView.setBackgroundColor(getResources().getColor(R.color.im_font_color_text_hint));
        Glide.with(getContext()).load(info.url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        return imageView;
    }

    public void initImageLayoutParams(){
        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;
        onePicParam = new LayoutParams(wrap, wrap);

        morePicParamFirst = new LayoutParams(morePicWandH, morePicWandH);
        morePicParam = new LayoutParams(morePicWandH, morePicWandH);
        morePicParam.setMargins(imagePadding, 0, 0, 0);

        rowParam = new LayoutParams(match, wrap);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (WIDTH == 0){
            int width = measureWidth(widthMeasureSpec);
            if (width > 0){
                WIDTH = width;
                if (photos != null && photos.size() > 0){
                    setList(photos);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int measureWidth(int measureSpec){
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.EXACTLY){
            result = size;
        } else if (mode == MeasureSpec.AT_MOST){
            result = Math.min(result, size);
        }
        return result;
    }

    public OnItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    class ImageViewOnClickListener implements OnClickListener{

        private int position;

        public ImageViewOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v, position);
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

}
