package cz.muni.fi.pv239.porenut.entities;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class Category extends RealmObject {

    private String title;
//    private List<CategoryItem> items;

    public Category() {}

    public Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public List<CategoryItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<CategoryItem> items) {
//        this.items = items;
//    }
}
