package net.anapsil.videoplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author anapsil
 * @since 1.0.0
 */

public class Content implements Parcelable {
    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel source) {
            return new Content(source);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };
    private String name;
    private String bg;
    private String im;
    private String sg;
    private List<Text> txts;
    private boolean isLoading;
    private boolean isDownloaded;

    public Content() {
    }

    protected Content(Parcel in) {
        this.name = in.readString();
        this.bg = in.readString();
        this.im = in.readString();
        this.sg = in.readString();
        this.txts = in.createTypedArrayList(Text.CREATOR);
        this.isLoading = in.readByte() != 0;
        this.isDownloaded = in.readByte() != 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getSg() {
        return sg;
    }

    public void setSg(String sg) {
        this.sg = sg;
    }

    public List<Text> getTxts() {
        return txts;
    }

    public void setTxts(List<Text> txts) {
        this.txts = txts;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.bg);
        dest.writeString(this.im);
        dest.writeString(this.sg);
        dest.writeTypedList(this.txts);
        dest.writeByte(this.isLoading ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDownloaded ? (byte) 1 : (byte) 0);
    }
}
