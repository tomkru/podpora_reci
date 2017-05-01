package cz.muni.fi.pv239.porenut.entities;

import io.realm.RealmObject;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class Category extends RealmObject {

    public String title;

    public Category(String title) {
        this.title = title;
    }
}
