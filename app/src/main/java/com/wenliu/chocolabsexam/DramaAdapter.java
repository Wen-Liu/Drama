package com.wenliu.chocolabsexam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DramaAdapter extends RecyclerView.Adapter {
    private static final String TAG = Constants.TAG_MAIN_ADAPTER;
    private Context mContext;
    private MainContract.Presenter mPresenter;
    private ArrayList<Drama> mDramas = new ArrayList<>();
    private ImageManager mImageManager;


    public DramaAdapter(Context context, MainContract.Presenter presenter, ArrayList<Drama> dramas) {
        Log.d(TAG, "DramaAdapter: ");
        mContext = context;
        mPresenter = presenter;
        mDramas = dramas;
        mImageManager = new ImageManager(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drama, parent, false);
        return new DramaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mImageManager.loadImageUrl(mDramas.get(position).getThumb(), ((DramaViewHolder) holder).getIvDramaImage());
        ((DramaViewHolder) holder).getTvDramaName().setText(mDramas.get(position).getName());
        ((DramaViewHolder) holder).getTvDramaCreateTime().setText(mDramas.get(position).getCreatedAt());
        ((DramaViewHolder) holder).getTvDramaRating().setText(String.valueOf(mDramas.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return mDramas.size();
    }

    public class DramaViewHolder extends RecyclerView.ViewHolder {
        //region "BindView"
        @BindView(R.id.iv_drama_image)
        ImageView mIvDramaImage;
        @BindView(R.id.tv_drama_name)
        TextView mTvDramaName;
        @BindView(R.id.tv_drama_rating)
        TextView mTvDramaRating;
        @BindView(R.id.tv_drama_create_time)
        TextView mTvDramaCreateTime;
        @BindView(R.id.item_drama)
        ConstraintLayout mItemDrama;
        //endregion

        @OnClick(R.id.item_drama)
        public void onViewClicked() {
            Log.d(TAG, "onViewClicked: " + getAdapterPosition());
            mPresenter.transToDetail(mDramas.get(getAdapterPosition()));
        }

        public DramaViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public ImageView getIvDramaImage() {
            return mIvDramaImage;
        }

        public TextView getTvDramaName() {
            return mTvDramaName;
        }

        public TextView getTvDramaRating() {
            return mTvDramaRating;
        }

        public TextView getTvDramaCreateTime() {
            return mTvDramaCreateTime;
        }

        public ConstraintLayout getItemDrama() {
            return mItemDrama;
        }
    }


    public void updateData(ArrayList<Drama> dramas) {
        Log.d(TAG, "updateData, data count: " + dramas.size());
        mDramas = new ArrayList<>(dramas);
        notifyDataSetChanged();
    }


}
