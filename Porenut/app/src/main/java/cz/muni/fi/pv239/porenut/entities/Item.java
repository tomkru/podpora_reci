package cz.muni.fi.pv239.porenut.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class Item extends RealmObject {

    @PrimaryKey
    private String id;
    private String text;
    private int order;
    private long counter;
    private int cardColor;
    private int textColor;
    private int audioFileId;

    public Item(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public int getCardColor() {
        return cardColor;
    }

    public void setCardColor(int cardColor) {
        this.cardColor = cardColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getAudioFileId() {
        return audioFileId;
    }

    public void setAudioFileId(int audioFileId) {
        this.audioFileId = audioFileId;
    }
}
