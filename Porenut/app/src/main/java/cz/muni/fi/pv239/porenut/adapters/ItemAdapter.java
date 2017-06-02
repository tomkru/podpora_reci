package cz.muni.fi.pv239.porenut.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.activities.AdminModeActivity;
import cz.muni.fi.pv239.porenut.activities.MainActivity;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by pato on 2.4.2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {
    public Context context;
    public List<Item> mDataSet;
    private boolean isAll = false;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        // TODO delete
        // public ImageView mImageView;

        public ViewHolder(View v){
            super(v);
            mCardView = (CardView) v.findViewById(R.id.item_card_view);
            mTextView = (TextView) v.findViewById(R.id.text_view);

            // TODO delete
            //mImageView = (ImageView) v.findViewById(R.id.img);
        }
    }

    public ItemAdapter(Context context, OrderedRealmCollection<Item> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
        Log.d("ItemAdapt","Item list length "+mDataSet.size());
    }

    public ItemAdapter(Context context, OrderedRealmCollection<Item> mDataSet, boolean isAll) {
        this.context = context;
        this.mDataSet = mDataSet;
        this.isAll = isAll;
        Log.d("ItemAdapt","Item list length "+mDataSet.size());
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_card_view, parent,false);
        context = parent.getContext();
        final ViewHolder vh = new ViewHolder(v);

        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        final Realm mRealm = Realm.getInstance(config);

        // Set a click listener for the current item of RecyclerView
        if (!isAll) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the RecyclerView current item serial and text
                    final Item clickedItem = mDataSet.get(vh.getAdapterPosition());

                    Log.d("ItemAdapter", "Click on item with id " + clickedItem.getId());
                    mRealm.beginTransaction();
                    clickedItem.setCounter(clickedItem.getCounter() + 1);
                    mRealm.commitTransaction();

                    // Display the RecyclerView clicked item serial and label
                    Toast.makeText(
                            context,
                            "Clicked item: " + clickedItem.getAudioFileId() +
                                    ", counter: " + clickedItem.getCounter() +
                                    ", order: " + clickedItem.getOrder(),
                            Toast.LENGTH_SHORT
                    ).show();

                    MediaPlayer mp = MediaPlayer.create(context, clickedItem.getAudioFileId());
                    mp.start();

                    context.startActivity(new Intent(context, MainActivity.class));
                }
            });
        } else {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Get the RecyclerView current item serial and text
                    final Item clickedItem = mDataSet.get(vh.getAdapterPosition());

                    Log.d("ItemAdapter", "Click on item with id " + clickedItem.getId());

                    Intent intent = new Intent(context, AdminModeActivity.class);
                    intent.putExtra("name", clickedItem.getText());
                    intent.putExtra("audioFile", context.getResources().getResourceEntryName(clickedItem.getAudioFileId()));
                    intent.putExtra("id", clickedItem.getId());
                    intent.putExtra("cardColor", clickedItem.getCardColor());
                    intent.putExtra("textColor", clickedItem.getTextColor());
                    intent.putExtra("toFill", true);
                    context.startActivity(intent);
                }
            });
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        // Get the current item from the data set
        Item item = mDataSet.get(position);
        holder.mTextView.setText(item.getText());
        holder.mTextView.setTextColor(context.getResources().getColor(item.getTextColor()));
        holder.mCardView.setCardBackgroundColor(context.getResources().getColor(item.getCardColor()));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
