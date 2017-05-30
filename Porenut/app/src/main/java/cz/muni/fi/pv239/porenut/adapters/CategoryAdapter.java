package cz.muni.fi.pv239.porenut.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cz.muni.fi.pv239.porenut.R;
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.activities.ItemActivity;
import io.realm.OrderedRealmCollection;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Category> mDataSet;
    private int mCounter = 1;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v){
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.category_text_view);
            mImageView = (ImageView) v.findViewById(R.id.category_image_view);
        }
    }

    public CategoryAdapter(Context mContext, OrderedRealmCollection<Category> mDataSet) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.card_view, parent,false);
        mContext = parent.getContext();
        // Get the TextView reference from RecyclerView current item
        final TextView textView = (TextView) v.findViewById(R.id.category_text_view);
        final ImageView imageView = (ImageView) v.findViewById(R.id.category_image_view);
        imageView.setImageResource(R.mipmap.ic_launcher);

        // Set a click listener for the current item of RecyclerView
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the RecyclerView current item serial and text
                final String string = textView.getText().toString();


                // Display the RecyclerView clicked item serial and label
                Toast.makeText(
                        mContext,
                        "Clicked : " + string,
                        Toast.LENGTH_SHORT
                ).show();
                Intent intent = new Intent(mContext, ItemActivity.class);
                intent.putExtra("category", textView.getText().toString());
                mContext.startActivity(intent);
            }
        });

        ViewHolder vh = new ViewHolder(v);
        // Return the ViewHolder
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        // Get the current item from the data set
        String string = mDataSet.get(position).getTitle();

        // Set the TextView widgets text
        holder.mTextView.setText(string);

        // Increase the counter
        mCounter +=1;
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
