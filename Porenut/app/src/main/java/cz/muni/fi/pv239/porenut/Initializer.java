package cz.muni.fi.pv239.porenut;

import android.content.Context;
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
        int currentOrder;

        // TODO load items from json and add items to Category constructor
        mRealm.beginTransaction();

        // init categories
        Category categoryGreetings = new Category(
                1,
                mContext.getResources().getString(R.string.greetings),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_greetings);
        Category managedCategoryGreetings = mRealm.copyToRealm(categoryGreetings);

        Category categoryFood = new Category(
                2,
                mContext.getResources().getString(R.string.food),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_food);
        Category managedCategoryFood = mRealm.copyToRealm(categoryFood);

        Category categoryDrink = new Category(
                3,
                mContext.getResources().getString(R.string.drink),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_drinks);
        Category managedCategoryDrink = mRealm.copyToRealm(categoryDrink);

        Category categoryNeed = new Category(
                4,
                mContext.getResources().getString(R.string.need),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_launcher);
        Category managedCategoryNeed = mRealm.copyToRealm(categoryNeed);

        Category categoryCustom = new Category(
                5,
                mContext.getResources().getString(R.string.custom),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_launcher);
        Category managedCategoryCustom = mRealm.copyToRealm(categoryCustom);

        Category categoryHealth = new Category(
                6,
                mContext.getResources().getString(R.string.health),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_health);
        Category managedCategoryHealth = mRealm.copyToRealm(categoryHealth);

        Category categoryFavourite = new Category(
                7,
                mContext.getResources().getString(R.string.favourite),
                mContext.getResources().getColor(R.color.colorCategoryCardDefault),
                mContext.getResources().getColor(R.color.colorTextDefault),
                R.mipmap.ic_favourite);
        mRealm.copyToRealm(categoryFavourite);
        // end of init categories


        // init greetings items
        currentOrder = 0;

        Item item = new Item("hi", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        Item managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("hello", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("good", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("how", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("how_u", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("i_ok", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("bye", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("good_time", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("good_time_u", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("next", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);

        item = new Item("next_time", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryGreetings.getItems().add(managedItem);


        // init food items
        currentOrder = 0;

        item = new Item("hungry", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("non_hungry", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("food_i", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("salt", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("delic", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("add_on", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("no_soup", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("chicken", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("cake", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("sauce", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("schn", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("roast", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("pasta", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("tomato", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("pork", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("hal", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("dump", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        item = new Item("soup", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryFood.getItems().add(managedItem);

        // init drink items
        currentOrder = 0;

        item = new Item("thirsty", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("non_thirsty", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("tea", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("coffee", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("water", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("sirup", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("milk", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        item = new Item("beer", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryDrink.getItems().add(managedItem);

        // init need items
        currentOrder = 0;

        item = new Item("toilet", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("tired", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("sleep", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("shower", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("home", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("cold", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("hot", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("walk", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("non_walk", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("tv", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        item = new Item("news", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryNeed.getItems().add(managedItem);

        // init custom items
        currentOrder = 0;

        item = new Item("fran", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        item = new Item("vel", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        item = new Item("fera", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        item = new Item("pavel", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        item = new Item("hanka", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        item = new Item("radejov", currentOrder++, R.color.colorItemCardSecondaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        item = new Item("rez", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryCustom.getItems().add(managedItem);

        // init health items
        currentOrder = 0;

        item = new Item("sick", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("weak", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("head", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("hand", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("leg", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("belly", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("back", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("fine", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("no_hurt", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);

        item = new Item("doctor", currentOrder++, R.color.colorItemCardPrimaryDefault, R.color.colorTextDefault);
        item.setTextAudioByItemId(mContext);
        managedItem = mRealm.copyToRealm(item);
        managedCategoryHealth.getItems().add(managedItem);


        mRealm.commitTransaction();

    }



}
