package cz.muni.fi.pv239.porenut;

import android.content.Context;

import cz.muni.fi.pv239.porenut.entities.Category;
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
        Category category = new Category();
        category.setId(1);
        category.setTitle(mContext.getResources().getString(R.string.cat1));
        category.setOrder(1);
        category.setCounter(0);
        category.setTextColor(mContext.getResources().getColor(R.color.colorCategoryTextDefault));
        category.setCardColor(mContext.getResources().getColor(R.color.colorCategoryCardDefault));
        category.setIcon(R.mipmap.ic_launcher);
        mRealm.copyToRealm(category);
        mRealm.commitTransaction();

        mRealm.beginTransaction();
        category = new Category();
        category.setId(2);
        category.setTitle(mContext.getResources().getString(R.string.cat2));
        category.setOrder(1);
        category.setCounter(0);
        category.setTextColor(mContext.getResources().getColor(R.color.colorCategoryTextDefault));
        category.setCardColor(mContext.getResources().getColor(R.color.colorCategoryCardDefault));
        category.setIcon(R.mipmap.ic_launcher);
        mRealm.copyToRealm(category);
        mRealm.commitTransaction();
    }


}
