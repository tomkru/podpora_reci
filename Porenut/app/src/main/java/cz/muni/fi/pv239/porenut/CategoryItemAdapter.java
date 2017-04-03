package cz.muni.fi.pv239.porenut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pato on 2.4.2017.
 */

public class CategoryItemAdapter extends BaseAdapter {
    public Context context;
    public List<CategoryItem> categoryItemList;

    public CategoryItemAdapter(Context context, List<CategoryItem> userList) {
        this.context = context;
        this.categoryItemList = userList;
    }

    @Override
    public int getCount() {
        return categoryItemList.size();
    }

    @Override
    public CategoryItem getItem(int i) {
        return categoryItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        }

        CategoryItem item = (CategoryItem) getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.text);
        name.setText(item.text);

        return convertView;
    }
}
