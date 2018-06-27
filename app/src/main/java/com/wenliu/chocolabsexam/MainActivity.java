package com.wenliu.chocolabsexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    //region "BindView"
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_drama_list)
    RecyclerView mRecyclerDramaList;
    @BindView(R.id.et_search_input)
    EditText mEtSearchInput;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    //endregion

    private static final String TAG = Constants.TAG_MAIN_ACTIVITY;
    private MainContract.Presenter mPresenter;
    private DramaAdapter mDramaAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<Drama> mDramas = new ArrayList<>();
    private ArrayList<Drama> mQueryDramas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: ");
        setSupportActionBar(mToolbar);

        mPresenter = new MainPresenter(this);
        mPresenter.start();

        setRecyclerView();
    }

    private void setRecyclerView() {
        mDramaAdapter = new DramaAdapter(this, mPresenter, mDramas);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerDramaList.setLayoutManager(mLinearLayoutManager);
        mRecyclerDramaList.setAdapter(mDramaAdapter);
    }


    @Override
    public void showDramas(ArrayList<Drama> dramas) {
        mDramas = new ArrayList<>(dramas);
        mDramaAdapter.updateData(dramas);
    }

    @Override
    public void isShowLoading(boolean isShow) {

    }

    @Override
    public void transToDetail(Drama drama) {
        Intent intent = new Intent(MainActivity.this, DramaInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.TAG_BUNDLE_DRAMA, drama);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @OnClick(R.id.btn_search)
    public void onViewClicked() {
        String searchInput = mEtSearchInput.getText().toString().trim();

        if (searchInput.length() > 0) {
            mQueryDramas = new ArrayList<>(mPresenter.searchData(searchInput));
            mDramaAdapter.updateData(mQueryDramas);
        } else {
            mDramaAdapter.updateData(mDramas);
        }
    }
}
