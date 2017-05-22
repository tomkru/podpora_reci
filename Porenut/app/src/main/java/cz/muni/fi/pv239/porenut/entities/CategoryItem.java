package cz.muni.fi.pv239.porenut.entities;

import io.realm.RealmObject;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class CategoryItem extends RealmObject {
    private String text;
    private int audioFileId;

    public CategoryItem(){}

    public CategoryItem(String text) { this.text = text; }

    public CategoryItem(String text, int audioFileId) {
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
