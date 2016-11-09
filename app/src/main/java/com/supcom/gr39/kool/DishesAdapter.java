package com.supcom.gr39.kool;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DishesAdapter extends RecyclerView
        .Adapter<DishesAdapter
        .DishHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Dish> mDataset;
    private static MyClickListener myClickListener;
    Context mContext;

    public static class DishHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView dateTime;
        public ImageView thumbnail , overflow;

        public DishHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.itemTitle);
            dateTime = (TextView) itemView.findViewById(R.id.priceItem);
            thumbnail = (ImageView)  itemView.findViewById(R.id.imageView);
            //overflow = (ImageView)  itemView.findViewById(R.id.overflow);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public DishesAdapter(Context context,ArrayList<Dish> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public DishHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dishes_card, parent, false);

        DishHolder dishHolder = new DishHolder(view);
        return dishHolder;
    }

    @Override
    public void onBindViewHolder(final DishHolder holder, int position) {
        Dish dish = mDataset.get(position);
        holder.label.setText(mDataset.get(position).getTitleDish());
        holder.dateTime.setText("Price = "+mDataset.get(position).getDesc() + " DT");
        Picasso.with(mContext).load(dish.getUrl()).into(holder.thumbnail);
        /*holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);

            }
        });*/

    }

    public void addItem(Dish dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    /*class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new DishesAdapter.MyMenuItemClickListener());
        popup.show();
    }*/

}