package cz.muni.fi.pv239.porenut.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.entities.Item;
import io.realm.OrderedRealmCollection;

/**
 * Created by pato on 2.4.2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {
    public Context context;
    public List<Item> mDataSet;

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

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_card_view, parent,false);
        context = parent.getContext();
        final ViewHolder vh = new ViewHolder(v);

        // Set a click listener for the current item of RecyclerView
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                final Item clickedItem = mDataSet.get(vh.getAdapterPosition());

                Log.d("ItemAdapter","Click on item with id "+clickedItem.getId());

                // Display the RecyclerView clicked item serial and label
                Toast.makeText(
                        context,
                        "Clicked item: " + clickedItem.getAudioFileId(),
                        Toast.LENGTH_SHORT
                ).show();

                MediaPlayer mp = MediaPlayer.create(context, clickedItem.getAudioFileId());
                mp.start();
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        // Get the current item from the data set
        Item item = mDataSet.get(position);
        holder.mTextView.setText(item.getText());
        holder.mTextView.setTextColor(item.getTextColor());
        holder.mCardView.setCardBackgroundColor(item.getCardColor());
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
