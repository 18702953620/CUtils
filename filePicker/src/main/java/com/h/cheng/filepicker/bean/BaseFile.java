package com.h.cheng.filepicker.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author ch
 * @date 2020/8/21-15:02
 * @desc
 */
public class BaseFile implements Parcelable {
    private long id;
    private String name;
    private String path;
    /**
     * byte
     */
    private long size;
    /**
     * Directory ID
     */
    private String bucketId;
    /**
     * Directory Name
     */
    private String bucketName;
    /**
     * Added Date
     */
    private long date;
    private boolean isSelected;

    public BaseFile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    protected BaseFile(Parcel in) {
        id = in.readLong();
        name = in.readString();
        path = in.readString();
        size = in.readLong();
        bucketId = in.readString();
        bucketName = in.readString();
        date = in.readLong();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<BaseFile> CREATOR = new Creator<BaseFile>() {
        @Override
        public BaseFile createFromParcel(Parcel in) {
            return new BaseFile(in);
        }

        @Override
        public BaseFile[] newArray(int size) {
            return new BaseFile[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(name);
        parcel.writeString(path);
        parcel.writeLong(size);
        parcel.writeString(bucketId);
        parcel.writeString(bucketName);
        parcel.writeLong(date);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseFile)) {
            return false;
        }

        BaseFile file = (BaseFile) o;
        return this.path.equals(file.path);
    }

}
