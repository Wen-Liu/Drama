package com.wenliu.chocolabsexam.object;

public class DramaObject {

    private int mId;
    private int mTotalViews;
    private String mName;
    private String mCreateTime;
    private String mThumb;
    private double mRating;

    public DramaObject() {
        mId = -1;
        mTotalViews = -1;
        mName = "";
        mCreateTime = "";
        mThumb = "";
        mRating = -1.0;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getTotalViews() {
        return mTotalViews;
    }

    public void setTotalViews(int totalViews) {
        mTotalViews = totalViews;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String createTime) {
        mCreateTime = createTime;
    }

    public String getThumb() {
        return mThumb;
    }

    public void setThumb(String thumb) {
        mThumb = thumb;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(double rating) {
        mRating = rating;
    }
}
