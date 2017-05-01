package cz.muni.fi.pv239.porenut.entities;

import io.realm.RealmObject;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class CategoryItem extends RealmObject {
    private String text;

    public CategoryItem(){}

    public CategoryItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
