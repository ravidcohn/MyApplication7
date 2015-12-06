package com.example.ravid.myapplication.ToDo;

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

public class Todo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<ExpandableListAdapter_Todo_Tasks.Item> data = new ArrayList<>();
        ExpandableListAdapter_Todo_Tasks.Item list;
        ExpandableListAdapter_Todo_Tasks.Item task;
        for (int i = 0; i < 5; i++) {
            list = new ExpandableListAdapter_Todo_Tasks.Item(ExpandableListAdapter_Todo_Tasks.List);
            list.invisibleChildren = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                task = new ExpandableListAdapter_Todo_Tasks.Item(ExpandableListAdapter_Todo_Tasks.Task);
                task.invisibleChildren = new ArrayList<>();
                for (int k = 0; k < 6; k++) {
                    task.invisibleChildren.add(new ExpandableListAdapter_Todo_Tasks.Item(ExpandableListAdapter_Todo_Tasks.Task_Child));
                }
                list.invisibleChildren.add(task);
            }
            data.add(list);
        }
        recyclerView.setAdapter(new ExpandableListAdapter_Todo_Tasks(data));
        ImageButton new_list = (ImageButton) findViewById(R.id.new_list);
        new_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),New_Todo_List.class);
                startActivity(intent);
            }
        });
    }

}

class ExpandableListAdapter_Todo_Tasks extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int List = 0;
    public static final int Task = 1;
    public static final int Task_Child = 2;

    private List<Item> data;

    public ExpandableListAdapter_Todo_Tasks(List<Item> data) {
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
            case List: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.todo_list, parent, false);
                ViewHolder_List viewHolder_list = new ViewHolder_List(view);
                return viewHolder_list;
            }
            case Task: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.todo_task, parent, false);
                ViewHolder_Task viewHolder_task = new ViewHolder_Task(view);
                return viewHolder_task;
            }
            case Task_Child: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.todo_task_child, parent, false);
                ViewHolder_Task_Child viewHolder_task_child = new ViewHolder_Task_Child(view);
                return viewHolder_task_child;
            }
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case List: {
                final ViewHolder_List itemController = (ViewHolder_List) holder;
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
                            while (data.size() > pos + 1 && data.get(pos + 1).type != List) {
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
            case Task: {
                final ViewHolder_Task itemController = (ViewHolder_Task) holder;
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
                            while (data.size() > pos + 1 && data.get(pos + 1).type == Task_Child) {
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
            case Task_Child: {
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

    private static class ViewHolder_List extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_List(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.task_list);
        }
    }

    private static class ViewHolder_Task extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_Task(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.task_list);
        }
    }

    private static class ViewHolder_Task_Child extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Task_Child(View itemView) {
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
