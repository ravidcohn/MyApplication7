package com.example.ravid.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Archive extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<ExpandableListAdapter_Archive.Item> data = new ArrayList<>();
        for(int i=0;i<20;i++) {
            data.add(new ExpandableListAdapter_Archive.Item(ExpandableListAdapter_Archive.Event));
        }
        recyclerview.setAdapter(new ExpandableListAdapter_Archive(data));
    }

}

class ExpandableListAdapter_Archive extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Event = 0;

    private List<Item> data;

    public ExpandableListAdapter_Archive(List<Item> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View view = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int) (18 * dp);
        int subItemPaddingTopAndBottom = (int) (5 * dp);
        LayoutInflater inflater;
        switch (type) {
            case Event: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.main_small_event, parent, false);
                ViewHolder_Event viewHolder_event = new ViewHolder_Event(view);
                return viewHolder_event;
            }
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case Event: {
                /*
                final ListChildViewHolder2 itemController2 = (ListChildViewHolder2) holder;
                itemController2.refferalItem = item;
                itemController2.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "This sample shows how to make a row only expand upon clicking our custom arrow view", Toast.LENGTH_SHORT).show();
                    }
                });
                */
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

    private static class ViewHolder_Event extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Event(View itemView) {
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
