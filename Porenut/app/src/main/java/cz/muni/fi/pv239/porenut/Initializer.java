package cz.muni.fi.pv239.porenut;

import android.content.Context;
import android.util.Log;

import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.Realm;


/**
 * Created by pato on 1.5.2017.
 */
public class Initializer {
    private Realm mRealm;
    private Context mContext;

    public Initializer(Realm mRealm, Context mContext) {
        this.mRealm = mRealm;
        this.mContext = mContext;

    }
    public void initData() {
        // TODO load items from json and add items to Category constructor
        mRealm.beginTransaction();
        Item item = new Item();
        item.setId("dobry_den");
        item.setText("Dobry den");
        item.setCounter(0);
        item.setOrder(1);
        item.setAudioFileId(mContext.getResources().getIdentifier(item.getId(), "raw", mContext.getPackageName()));
        item.setTextColor(R.color.colorCategoryTextDefault);
        item.setCardColor(R.color.colorCategoryCardDefault);
        Item managedItem = mRealm.copyToRealm(item);

        Category category = new Category();
        category.setId(1);
        category.setTitle(mContext.getResources().getString(R.string.cat1));
        category.setOrder(1);
        category.setCounter(0);
        category.setTextColor(mContext.getResources().getColor(R.color.colorCategoryTextDefault));
        category.setCardColor(mContext.getResources().getColor(R.color.colorPrimary));
        category.setIcon(R.mipmap.ic_launcher);
        Category managedCategory = mRealm.copyToRealm(category);
        managedCategory.getItems().add(managedItem);


        item = new Item();
        item.setId("kava");
        item.setText("Muzu poprosit o kavu");
        item.setCounter(0);
        item.setOrder(2);
        item.setAudioFileId(mContext.getResources().getIdentifier(item.getId(), "raw", mContext.getPackageName()));
        item.setTextColor(R.color.colorPrimary);
        item.setCardColor(R.color.colorCategoryCardDefault);
        managedItem = mRealm.copyToRealm(item);

        category = new Category();
        category.setId(2);
        category.setTitle(mContext.getResources().getString(R.string.cat2));
        category.setOrder(1);
        category.setCounter(0);
        category.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        category.setCardColor(mContext.getResources().getColor(R.color.colorCategoryCardDefault));
        category.setIcon(R.mipmap.ic_launcher);
        managedCategory = mRealm.copyToRealm(category);
        managedCategory.getItems().add(managedItem);

        item = new Item();
        item.setId("kava2");
        item.setText("Muzu poprosit o caj");
        item.setCounter(0);
        item.setOrder(3);
        item.setAudioFileId(mContext.getResources().getIdentifier("kava", "raw", mContext.getPackageName()));
        item.setTextColor(R.color.colorCategoryTextDefault);
        item.setCardColor(R.color.colorPrimary);
        managedItem = mRealm.copyToRealm(item);
        managedCategory.getItems().add(managedItem);
        mRealm.commitTransaction();
    }



}
