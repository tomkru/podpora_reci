package cz.muni.fi.pv239.porenut.entities;

import io.realm.RealmObject;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class Item extends RealmObject {
    private String text;
    private int audioFileId;

    public Item(){}

    public Item(String text) { this.text = text; }

    public Item(String text, int audioFileId) {
        this.text = text;
        this.audioFileId = audioFileId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAudioFileId() { return this.audioFileId; }

    public void setAudioFileId(int audioFileId) { this.audioFileId = audioFileId; }

}
