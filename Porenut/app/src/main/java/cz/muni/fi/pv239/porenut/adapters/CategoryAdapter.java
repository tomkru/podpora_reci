package cz.muni.fi.pv239.porenut.adapters;

import android.content.Context;
import android.content.Intent;
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
import cz.muni.fi.pv239.porenut.entities.Category;
import cz.muni.fi.pv239.porenut.activities.ItemActivity;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Category> mDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public ImageView mImageView;

        public ViewHolder(View v){
            super(v);
            mCardView = (CardView) v.findViewById(R.id.category_card_view);
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
        final ViewHolder vh = new ViewHolder(v);

        Realm.init(mContext);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        final Realm mRealm = Realm.getInstance(config);

        // Set a click listener for the current item of RecyclerView
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category clickedCategory = mDataSet.get(vh.getAdapterPosition());
                Log.d("Category adapter","Click on category with id "+clickedCategory.getId());
                mRealm.beginTransaction();
                clickedCategory.setCounter(clickedCategory.getCounter()+1);
                mRealm.commitTransaction();
                Toast.makeText(
                        mContext,
                        "Clicked : " + clickedCategory.getId() +
                        ", counter: " + clickedCategory.getCounter() +
                        ", order: " + clickedCategory.getOrder(),
                        Toast.LENGTH_SHORT
                ).show();
                Intent intent = new Intent(mContext, ItemActivity.class);
                intent.putExtra("categoryId", clickedCategory.getId());

                mContext.startActivity(intent);
            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        // Get the current item from the data set
        final Category category = mDataSet.get(position);

        holder.mTextView.setTextColor(category.getTextColor());
        holder.mTextView.setText(category.getTitle());
        holder.mCardView.setCardBackgroundColor(category.getCardColor());
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
