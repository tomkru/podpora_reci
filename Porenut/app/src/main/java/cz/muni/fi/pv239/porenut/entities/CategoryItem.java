package cz.muni.fi.pv239.porenut.entities;

import io.realm.RealmObject;

/**
 * Created by pcyprian on 2.4.2017.
 */

public class CategoryItem extends RealmObject {
    public String text;

    public CategoryItem(String text) {
        this.text = text;
    }
}
