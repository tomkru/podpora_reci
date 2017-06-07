package cz.muni.fi.pv239.porenut.entities;

import android.content.Context;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import cz.muni.fi.pv239.porenut.R;
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
    private String userAudioFileId;
    private boolean isUser;
    private Date lastUse;


    public Item(){
        isUser = false;
    }

    public Item(String id, int order, int cardColor, int textColor) {
        this.id = id;
        this.order = order;
        this.cardColor = cardColor;
        this.textColor = textColor;
        this.counter = 0;
        this.lastUse = Calendar.getInstance().getTime();
    }

    public Date getLastUse() {
        return lastUse;
    }

    public void setLastUse(Date lastUse) {
        this.lastUse = lastUse;
    }

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

    public void setTextAudioByItemId(Context mContext) {

            this.text = mContext.getResources().getString(
                    mContext.getResources().getIdentifier(this.getId(), "string", mContext.getPackageName()));
            Log.d("Item","Text " + this.text + " for item with id "+this.id+"was set");

            this.audioFileId = mContext.getResources().getIdentifier(this.getId(), "raw", mContext.getPackageName());
            Log.d("Item","Audio file with id " + this.audioFileId +" for item with id "+this.id+"was set");

    }

    public void setAudioFileId(int audioFileId) {
        this.audioFileId = audioFileId;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getUserAudioFileId() {
        return userAudioFileId;
    }

    public void setUserAudioFileId(String userAudioFileId) {
        this.userAudioFileId = userAudioFileId;
    }
}
