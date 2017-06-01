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
import rx.subjects.PublishSubject;

/**
 * Created by Josef Pavelec on 29/03/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Category> mDataSet;
    private final PublishSubject<Category> onClickSubject = PublishSubject.create();



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


        /* TODO delete
        final TextView textView = (TextView) v.findViewById(R.id.category_text_view);
        final ImageView imageView = (ImageView) v.findViewById(R.id.category_image_view);
        imageView.setImageResource(R.mipmap.ic_launcher);*/

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        // Get the current item from the data set
        final Category category = mDataSet.get(position);

        holder.mTextView.setText(category.getTitle());
        holder.mTextView.setTextColor(category.getTextColor());
        holder.mCardView.setCardBackgroundColor(category.getCardColor());
        holder.mImageView.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSubject.onNext(category);
                Toast.makeText(mContext, "pocet itemu "+category.getItems().size(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, ItemActivity.class);
                //intent.putExtra("categoryTitle", category.getTitle());
                Log.d("CategoryAdapter","passing out id "+category.getId());
                intent.putExtra("categoryId", category.getId());
                mContext.startActivity(intent);
            }
        });

    }

    /* TODO delete when will be unused
    public Observable<Category> getPositionClicks(){
        return onClickSubject.asObservable();
    }*/

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
