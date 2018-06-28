package com.wenliu.chocolabsexam;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenliu.chocolabsexam.base.BaseActivity;
import com.wenliu.chocolabsexam.object.Drama;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DramaInfoActivity extends BaseActivity {

    @BindView(R.id.iv_info_drama_image)
    ImageView mIvInfoDramaImage;
    @BindView(R.id.tv_info_drama_name)
    TextView mTvInfoDramaName;
    @BindView(R.id.tv_info_drama_rating)
    TextView mTvInfoDramaRating;
    @BindView(R.id.tv_info_drama_create_time)
    TextView mTvInfoDramaCreateTime;
    @BindView(R.id.tv_info_drama_total_views)
    TextView mTvInfoDramaTotalViews;
    private Drama mDrama;
    private ImageManager mImageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama_info);
        ButterKnife.bind(this);

        mImageManager = new ImageManager(this);
        initView();
    }


    private void initView() {
        Bundle bundle = this.getIntent().getExtras();
        mDrama = (Drama) bundle.getSerializable(Constants.BUNDLE_DRAMA);

        mImageManager.loadImageUrl(mDrama.getThumb(), mIvInfoDramaImage);
        mTvInfoDramaName.setText(mDrama.getName());
        mTvInfoDramaCreateTime.setText(mDrama.getCreatedAt());
        mTvInfoDramaRating.setText(String.valueOf(mDrama.getRating()));
        mTvInfoDramaTotalViews.setText(String.valueOf(mDrama.getTotalViews()));
    }
}
