package com.wenliu.chocolabsexam.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wenliu.chocolabsexam.Constants;
import com.wenliu.chocolabsexam.DramaApplication;
import com.wenliu.chocolabsexam.DramaInfoActivity;
import com.wenliu.chocolabsexam.R;
import com.wenliu.chocolabsexam.base.BaseActivity;
import com.wenliu.chocolabsexam.database.SearchRecord;
import com.wenliu.chocolabsexam.image.GridSpacingItemDecoration;
import com.wenliu.chocolabsexam.object.Drama;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {
    //region "BindView"
    @BindView(R.id.appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_drama_list)
    RecyclerView mRecyclerDramaList;
    @BindView(R.id.et_search_input)
    EditText mEtSearchInput;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.btn_clear)
    Button mBtnClear;
    //endregion

    private static final String TAG = Constants.TAG_MAIN_ACTIVITY;
    private MainContract.Presenter mPresenter;
    private DramaAdapter mDramaAdapter;
    private GridLayoutManager mGridLayoutManager;
    private ArrayList<Drama> mDramas = new ArrayList<>();
    private ArrayList<Drama> mQueryDramas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Log.d(TAG, "onCreate: ");
        setSupportActionBar(mToolbar);
        mAppbar.setPadding(0, getStatusBarHeight(), 0, 0);
        mPresenter = new MainPresenter(this);
        setRecyclerView();
        setEditTextListener();

        mPresenter.start();
    }

    private void setRecyclerView() {
        mDramaAdapter = new DramaAdapter(this, mPresenter, mDramas);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerDramaList.addItemDecoration(new GridSpacingItemDecoration(2, DramaApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.space_drama_item), true));
        mRecyclerDramaList.setLayoutManager(mGridLayoutManager);
        mRecyclerDramaList.setAdapter(mDramaAdapter);
    }

    private void setEditTextListener() {

        mEtSearchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchInput = mEtSearchInput.getText().toString().trim();

                if (searchInput.length() > 0) {
                    mQueryDramas = new ArrayList<>(mPresenter.searchData(searchInput));
                    mDramaAdapter.updateData(mQueryDramas);
                    SearchRecord.getInstance().setSearch(searchInput);
                    mBtnClear.setVisibility(View.VISIBLE);
                } else {
                    mDramaAdapter.updateData(mDramas);
                    mBtnClear.setVisibility(View.GONE);
                    SearchRecord.getInstance().clearSearch();
                }
            }
        });
    }


    @Override
    public void showDramas(ArrayList<Drama> dramas) {
        mDramas = new ArrayList<>(dramas);
        Log.d(TAG, "showDramas: " + SearchRecord.getInstance().haveSearchRecord());

        if (SearchRecord.getInstance().haveSearchRecord()) {
            Log.d(TAG, "showDramas: " + SearchRecord.getInstance().getSearch());
            String searchSting = SearchRecord.getInstance().getSearch();
            mQueryDramas = new ArrayList<>(mPresenter.searchData(searchSting));
            mEtSearchInput.setText(searchSting);
            mDramaAdapter.updateData(mQueryDramas);
        } else {
            mDramaAdapter.updateData(dramas);
        }
    }

    @Override
    public void isShowLoading(boolean isShow) {

    }

    @Override
    public void transToDetail(Drama drama) {
        Intent intent = new Intent(MainActivity.this, DramaInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.BUNDLE_DRAMA, drama);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
    }


    @OnClick({R.id.btn_search, R.id.btn_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:

                break;

            case R.id.btn_clear:
                mEtSearchInput.setText("");
                SearchRecord.getInstance().clearSearch();

                break;
        }
    }
}
