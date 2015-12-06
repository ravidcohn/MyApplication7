package com.example.ravid.myapplication.Groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ravid.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Groups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        List<ExpandableListAdapter_Groups.Item> data = new ArrayList<>();

        ExpandableListAdapter_Groups.Item group1;
        for(int i = 0; i < 2; i++){
            group1 = new ExpandableListAdapter_Groups.Item(ExpandableListAdapter_Groups.Group);
            group1.invisibleChildren = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                group1.invisibleChildren.add(new ExpandableListAdapter_Groups.Item(ExpandableListAdapter_Groups.User));
            }
            data.add(group1);
        }

        recyclerview.setAdapter(new ExpandableListAdapter_Groups(data));

        ImageButton new_group = (ImageButton) findViewById(R.id.new_group);
        new_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),New_Group_Step1.class);
                startActivity(intent);
            }
        });


    }

}

class ExpandableListAdapter_Groups extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Group = 0;
    public static final int User = 1;

    private List<Item> data;

    public ExpandableListAdapter_Groups(List<Item> data) {
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
            case Group: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.grups_group, parent, false);
                ViewHolder_Group viewHolder_group = new ViewHolder_Group(view);
                return viewHolder_group;
            }
            case User: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.grups_user, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case User: {

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
            case Group: {
                final ViewHolder_Group itemController = (ViewHolder_Group) holder;
                itemController.refferalItem = item;
                if (item.invisibleChildren == null) {
                    itemController.expand_arrow.setImageResource(android.R.drawable.arrow_up_float);
                } else {
                    itemController.expand_arrow.setImageResource(android.R.drawable.arrow_down_float);
                }
                itemController.expand_arrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int pos = data.indexOf(itemController.refferalItem);
                            int count = 0;
                            while (data.size() > pos + 1 && data.get(pos + 1).type != Group) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_down_float);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);

                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_up_float);
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

    private static class ViewHolder_User extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_User(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Group extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_Group(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.group_arrow);
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

