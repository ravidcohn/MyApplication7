package com.example.ravid.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Location_Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_vote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<ExpandableListAdapter_Location_Vote.Item> data = new ArrayList<>();
        ExpandableListAdapter_Location_Vote.Item location;
        for (int i = 0; i < 6; i++) {
            location = new ExpandableListAdapter_Location_Vote.Item(ExpandableListAdapter_Location_Vote.Location);
            location.invisibleChildren = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                location.invisibleChildren.add(new ExpandableListAdapter_Location_Vote.Item(ExpandableListAdapter_Location_Vote.User));
            }
            data.add(location);
        }
        recyclerview.setAdapter(new ExpandableListAdapter_Location_Vote(data));
    }

}

class ExpandableListAdapter_Location_Vote extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Location = 0;
    public static final int User = 1;


    private List<Item> data;

    public ExpandableListAdapter_Location_Vote(List<Item> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view;
        LayoutInflater inflater;
        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (type) {
            case Location: {
                view = inflater.inflate(R.layout.location_vote_location, parent, false);
                final ViewHolder_Location viewHolder_location = new ViewHolder_Location(view);
                return viewHolder_location;
            }
            case User: {
                view = inflater.inflate(R.layout.location_vote_user, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        View view = holder.itemView;
        switch (item.type) {
            case Location: {
                final ViewHolder_Location itemController = (ViewHolder_Location) holder;
                itemController.refferalItem = item;
                if (item.invisibleChildren == null) {
                    itemController.expand_arrow.setImageResource(android.R.drawable.arrow_up_float);
                } else {
                    itemController.expand_arrow.setImageResource(android.R.drawable.arrow_down_float);
                }
                //itemController.expand_arrow.setOnClickListener(new View.OnClickListener() {
                view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int pos = data.indexOf(itemController.refferalItem);
                            int count = 0;
                            while (data.size() > pos + 1 && data.get(pos + 1).type != Location) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.expand_arrow.setImageResource(R.mipmap.ic_expand_arrow);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.expand_arrow.setImageResource(R.mipmap.ic_collapse_arrow);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ViewHolder_Location extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_Location(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.expand_arrow);
            expand_arrow.setBackgroundResource(android.R.color.transparent);

        }
    }

    private static class ViewHolder_User extends RecyclerView.ViewHolder {

        public ViewHolder_User(View itemView) {
            super(itemView);
        }
    }

    public static class Item {
        public int type;
        public List<Item> invisibleChildren;

        public Item() {
        }

        public Item(int type) {
            this.type = type;
        }
    }
}
