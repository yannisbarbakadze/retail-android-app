package com.example.android.projectriver3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Johny on 10/7/2017.
 */

/**
 * An {@link ProductAdapter} knows how to create a list item layout for each product
 * in the data source (a list of {@link Product} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class ProductAdapter extends ArrayAdapter<Product>{

    //Create ViewHolder class
    static class ViewHolder {
        private ImageView imageView;
        private TextView nameTextView;
        private TextView priceTextView;
    }

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     *  Constructs a new {@link ProductAdapter}.
     *
     *  @param context of the app
     *  @param products is the list of products, which is the data source of the adapter
     */
    public ProductAdapter(Context context, List<Product> products) {
        super(context, 0, products);
    }

    /**
     *  Returns a list item view that displays information about the product about the
     *  given position in the list of products
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //David's suggestion for modification
        ViewHolder holder;

        /** Check if there is an existing list item view (called convertView) that we can reuse,
         *  otherwise, if convertView is null, then inflate a new list item layout.
         */
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.name_view);
            holder.priceTextView = (TextView) convertView.findViewById(R.id.price_view);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Find the product at the given position in the list of products
        Product currentProduct = getItem(position);

        try {
            Picasso.with(getContext()).load(currentProduct.getImageUrl()).into(holder.imageView);
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Blank url", e);
        }
        holder.nameTextView.setText(currentProduct.getName());
        holder.priceTextView.setText(currentProduct.getPrice());

        return convertView;

        //My way

        /** Check if there is an existing list item view (called convertView) that we can reuse,
         // otherwise, if convertView is null, then inflate a new list item layout.
         View listItemView = convertView;
         if (listItemView == null) {
         listItemView = LayoutInflater.from(getContext()).inflate(
         R.layout.list_item, parent, false);
         }*/

        /** Find the TextView with view ID name_view
        TextView nameView = (TextView) listItemView.findViewById(R.id.name_view);
        nameView.setText(currentProduct.getName());

        // Find the TextView with view ID price_view
        TextView priceView = (TextView) listItemView.findViewById(R.id.price_view);
        priceView.setText(currentProduct.getPrice());

        // Find the ImageView with view ID image
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        try {
            Picasso.with(getContext()).load(currentProduct.getImageUrl()).into(imageView);
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "Blank url", e);
        }

        return listItemView;*/
    }

}