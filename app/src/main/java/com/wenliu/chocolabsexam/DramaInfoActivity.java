package com.wenliu.chocolabsexam;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenliu.chocolabsexam.api.GetSingleDataTask;
import com.wenliu.chocolabsexam.api.callback.GetDramaByIdCallback;
import com.wenliu.chocolabsexam.base.BaseActivity;
import com.wenliu.chocolabsexam.image.ImageManager;
import com.wenliu.chocolabsexam.mainpage.MainActivity;
import com.wenliu.chocolabsexam.object.Drama;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DramaInfoActivity extends BaseActivity {
    //region "BindView"
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
    //endregion

    private static final String TAG = Constants.TAG_DRAMA_INFO_ACTIVITY;
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

        if (mDrama != null) {
            Log.d(TAG, "initView: ");
            setView(mDrama);

        } else {
            Uri uri = getIntent().getData();
            String[] dramaId = uri.getPath().split("/");
            getDramaById(dramaId[dramaId.length - 1]);
        }
    }

    private void getDramaById(String dramaId) {
        new GetSingleDataTask(dramaId, new GetDramaByIdCallback() {
            @Override
            public void OnSuccess(Drama drama) {
                mDrama = drama;
                setView(drama);
            }

            @Override
            public void OnFailure(String errorMessage) {
                Log.d(TAG, "OnFailure: " + errorMessage);
                transToMain();
            }
        }).execute();

    }

    private void setView(Drama drama) {
        mImageManager.loadImageUrl(drama.getThumb(), mIvInfoDramaImage);
        mTvInfoDramaName.setText(drama.getName());
        String[] splitCreateTime = drama.getCreatedAt().split("T");
        mTvInfoDramaCreateTime.setText(splitCreateTime[0]);
        mTvInfoDramaRating.setText(String.valueOf(drama.getRating()));
        mTvInfoDramaTotalViews.setText(String.valueOf(drama.getTotalViews()));
    }

    private void transToMain() {
        Intent intent = new Intent(DramaInfoActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
