package cz.muni.fi.pv239.porenut.entities;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private long id;
    private String title;
    private long order;
    private long counter;
    private int cardColor;
    private int textColor;
    private int icon;
    private RealmList<Item> items;

    public Category() {}

    /*
    public Category(long id, String title, int cardColor, int textColor, int icon, RealmList items) {
        this.id = id;
        this.title = title;
        this.order = id;
        this.counter = 0;
        this.cardColor = cardColor;
        this.textColor = textColor;
        this.icon = icon;
        this.items = items;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public RealmList<Item> getItems() {
        return items;
    }

    public void setItems(RealmList<Item> items) {
        this.items = items;
    }
}
