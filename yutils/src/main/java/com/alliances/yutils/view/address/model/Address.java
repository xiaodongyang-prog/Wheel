package com.alliances.yutils.view.address.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Address implements Parcelable{

    /**
     * id。
     */
    @JSONField(name = "id")
    private String id;

    /**
     * 名称。
     */
    @JSONField(name = "name")
    private String name;

    /**
     * 子项。
     */
    @JSONField(name = "children")
    private List<Address> mCityList;

    /**
     * 是否选中。
     */
    private boolean isSelect;

    public Address() {
    }

    protected Address(Parcel in) {
        id = in.readString();
        name = in.readString();
        mCityList = in.createTypedArrayList(Address.CREATOR);
        isSelect = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeTypedList(mCityList);
        dest.writeByte((byte) (isSelect ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String mId) {
        id = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        name = mName;
    }

    public List<Address> getCityList() {
        return mCityList;
    }

    public void setCityList(List<Address> mCityList) {
        this.mCityList = mCityList;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean mSelect) {
        isSelect = mSelect;
    }

    public Address(String mId, String mName, List<Address> mCityList, boolean mIsSelect) {

        id = mId;
        name = mName;
        this.mCityList = mCityList;
        isSelect = mIsSelect;
    }

}
