package cz.muni.fi.pv239.porenut;

import java.util.Locale;
import java.util.UUID;

import cz.muni.fi.pv239.porenut.entities.Category;
import io.realm.Realm;

/**
 * Created by pato on 1.5.2017.
 */

public class Initializer {
    public static void initCategory(Realm realm, long id, String name) {
        realm.beginTransaction();
        Category cat = realm.createObject(Category.class);
        cat.setId(id);
        cat.setTitle(name);
        realm.commitTransaction();
    }

    public static void initCategoryItem(Realm realm, String text) {

    }

    public static void initCategoryItem(Realm realm, String text, int audioFileId) {

    }
}
