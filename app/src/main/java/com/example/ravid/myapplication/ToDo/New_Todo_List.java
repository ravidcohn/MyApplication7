package com.example.ravid.myapplication.ToDo;

import android.content.Context;
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

public class New_Todo_List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_todo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        List<ExpandableListAdapter_New_Todo_List.Item> data = new ArrayList<>();

        data.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.List_Name));
        ExpandableListAdapter_New_Todo_List.Item add_tak = new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Add_Tasks_Button);
        add_tak.invisibleChildren = new ArrayList<>();
        add_tak.invisibleChildren.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Add_Tasks_Item));
        add_tak.invisibleChildren.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Add_Tasks_Child_Item));
        add_tak.invisibleChildren.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Add_Tasks_Child_Item));
        add_tak.invisibleChildren.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Add_Tasks_Child_Item));
        add_tak.invisibleChildren.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Add_Tasks_Child_Buttons));
        data.add(add_tak);

        ExpandableListAdapter_New_Todo_List.Item task = new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Task);
        task.invisibleChildren = new ArrayList<>();
        for (int i=0;i<20;i++){
            task.invisibleChildren.add(new ExpandableListAdapter_New_Todo_List.Item(ExpandableListAdapter_New_Todo_List.Task_Child));
        }
        data.add(task);

        recyclerview.setAdapter(new ExpandableListAdapter_New_Todo_List(data));

    }

}

class ExpandableListAdapter_New_Todo_List extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int List_Name = 0;
    public static final int Add_Tasks_Button = 1;
    public static final int Add_Tasks_Item = 2;
    public static final int Add_Tasks_Child_Item = 3;
    public static final int Add_Tasks_Child_Buttons = 4;
    public static final int Task = 5;
    public static final int Task_Child = 6;

    private List<Item> data;

    public ExpandableListAdapter_New_Todo_List(List<Item> data) {
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
            case List_Name: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_todo_list_name, parent, false);
                ViewHolder_Add_Tasks_Buttons viewHolder_add_tasks_buttons = new ViewHolder_Add_Tasks_Buttons(view);
                return viewHolder_add_tasks_buttons;
            }
            case Add_Tasks_Button: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_event_tasks_add_buttons, parent, false);
                ViewHolder_Add_Tasks_Buttons viewHolder_add_tasks_buttons = new ViewHolder_Add_Tasks_Buttons(view);
                return viewHolder_add_tasks_buttons;
            }
            case Add_Tasks_Item: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_event_tasks_add_item, parent, false);
                ViewHolder_Add_Tasks_Item viewHolder_add_tasks_item = new ViewHolder_Add_Tasks_Item(view);
                return viewHolder_add_tasks_item;
            }
            case Add_Tasks_Child_Item: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_event_tasks_add_child_item, parent, false);
                ViewHolder_Add_Tasks_Child_Item viewHolder_add_tasks_child_item = new ViewHolder_Add_Tasks_Child_Item(view);
                return viewHolder_add_tasks_child_item;
            }
            case Add_Tasks_Child_Buttons: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_event_tasks_add_child_buttons, parent, false);
                ViewHolder_Add_Tasks_Child_Button viewHolder_add_tasks_child_button = new ViewHolder_Add_Tasks_Child_Button(view);
                return viewHolder_add_tasks_child_button;
            }
            case Task: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_event_task, parent, false);
                ViewHolder_Task viewHolder_task = new ViewHolder_Task(view);
                return viewHolder_task;
            }
            case Task_Child: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.new_event_task_child, parent, false);
                ViewHolder_Task_Child viewHolder_task_child = new ViewHolder_Task_Child(view);
                return viewHolder_task_child;
            }
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
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
                            while (data.size() > pos + 1 && data.get(pos + 1).type != Task) {
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
            case Add_Tasks_Button: {
                final ViewHolder_Add_Tasks_Buttons itemController = (ViewHolder_Add_Tasks_Buttons) holder;
                itemController.refferalItem = item;
                itemController.add_task.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Item>();
                            int pos = data.indexOf(itemController.refferalItem);
                            int count = 0;
                            while (data.size() > pos + 1 && data.get(pos + 1).type != Task) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);

                            int index = pos + 1;
                            for (Item i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
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

    private static class ViewHolder_Add_Tasks_Buttons extends RecyclerView.ViewHolder {
        public Item refferalItem;
        public ImageButton add_task;

        public ViewHolder_Add_Tasks_Buttons(View itemView) {
            super(itemView);
            add_task = (ImageButton) itemView.findViewById(R.id.add_task);
        }
    }

    private static class ViewHolder_Add_Tasks_Item extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Add_Tasks_Item(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Add_Tasks_Child_Button extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Add_Tasks_Child_Button(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Add_Tasks_Child_Item extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_Add_Tasks_Child_Item(View itemView) {
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
