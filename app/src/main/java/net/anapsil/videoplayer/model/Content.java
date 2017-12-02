package net.anapsil.videoplayer.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * @author anapsil
 * @since 1.0.0
 */

@Parcel(Parcel.Serialization.BEAN)
public class Content {
    private String name;
    private String bg;
    private String im;
    private String sg;
    private List<Text> txts;

    public Content() {
    }

    @ParcelConstructor
    public Content(String name, String bg, String im, String sg, List<Text> txts) {
        this.name = name;
        this.bg = bg;
        this.im = im;
        this.sg = sg;
        this.txts = txts;
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
}
