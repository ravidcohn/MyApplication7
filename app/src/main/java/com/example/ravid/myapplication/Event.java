package com.example.ravid.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Event extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Main";
                case 1:
                    return "Attending";
                case 2:
                    return "ToDo";
                case 3:
                    return "Chat";
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1: {
                    View rootView = inflater.inflate(R.layout.fragment_event_details, container, false);
                    RecyclerView recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerView);
                    recyclerview.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    List<ExpandableListAdapter_Event_Details.Item> data = new ArrayList<>();

                    data.add(new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_New_Event_Details.Image));
                    data.add(new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_New_Event_Details.Event_Name));

                    ExpandableListAdapter_Event_Details.Item description = new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Description);
                    description.invisibleChildren = new ArrayList<>();
                    description.invisibleChildren.add(new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Description_Child));
                    data.add(description);

                    ExpandableListAdapter_Event_Details.Item location = new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Location);
                    data.add(location);

                    ExpandableListAdapter_Event_Details.Item date = new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Date);
                    date.invisibleChildren = new ArrayList<>();
                    date.invisibleChildren.add(new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Date_Child));
                    data.add(date);

                    ExpandableListAdapter_Event_Details.Item repeat = new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Repeat);
                    repeat.invisibleChildren = new ArrayList<>();
                    repeat.invisibleChildren.add(new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Repeat_Child));
                    data.add(repeat);

                    ExpandableListAdapter_Event_Details.Item alert = new ExpandableListAdapter_Event_Details.Item(ExpandableListAdapter_Event_Details.Alert);
                    data.add(alert);

                    recyclerview.setAdapter(new ExpandableListAdapter_Event_Details(data));

                    //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
                    //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                    return rootView;
                }
                case 2: {
                    View rootView = inflater.inflate(R.layout.fragment_event_friends, container, false);
                    final RecyclerView recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerView);
                    recyclerview.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    List<ExpandableListAdapter_Event_Friends.Item> data = new ArrayList<>();
                    data.add(new ExpandableListAdapter_Event_Friends.Item(ExpandableListAdapter_Event_Friends.Summarize));
                    data.add(new ExpandableListAdapter_Event_Friends.Item(ExpandableListAdapter_Event_Friends.User_My));
                    for (int i = 0; i < 3; i++) {
                        data.add(new ExpandableListAdapter_Event_Friends.Item(ExpandableListAdapter_Event_Friends.User_Yes));
                    }
                    for (int i = 0; i < 4; i++) {
                        data.add(new ExpandableListAdapter_Event_Friends.Item(ExpandableListAdapter_Event_Friends.User_Maybe));
                    }
                    for (int i = 0; i < 6; i++) {
                        data.add(new ExpandableListAdapter_Event_Friends.Item(ExpandableListAdapter_Event_Friends.User_No));
                    }
                    recyclerview.setAdapter(new ExpandableListAdapter_Event_Friends(data));


                    return rootView;
                }
                case 3: {
                    View rootView = inflater.inflate(R.layout.fragment_event_tasks, container, false);

                    RecyclerView recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerView);
                    recyclerview.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    List<ExpandableListAdapter_Event_Tasks.Item> data = new ArrayList<>();

                    ExpandableListAdapter_Event_Tasks.Item task;
                    for (int j = 0; j < 5; j++) {
                        task = new ExpandableListAdapter_Event_Tasks.Item(ExpandableListAdapter_Event_Tasks.Task);
                        task.invisibleChildren = new ArrayList<>();
                        for (int i = 0; i < 4; i++) {
                            task.invisibleChildren.add(new ExpandableListAdapter_Event_Tasks.Item(ExpandableListAdapter_Event_Tasks.Task_Child));
                        }
                        data.add(task);
                    }

                    recyclerview.setAdapter(new ExpandableListAdapter_Event_Tasks(data));

                    return rootView;
                }
                case 4: {
                    View rootView = inflater.inflate(R.layout.fragment_event_chat, container, false);

                    final RecyclerView recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerView);
                    recyclerview.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
                    List<ExpandableListAdapter_Event_Chat.Item> data = new ArrayList<>();
                    for (int i = 0; i < 30; i++) {
                        data.add(new ExpandableListAdapter_Event_Chat.Item(ExpandableListAdapter_Event_Chat.Chat_My));
                        data.add(new ExpandableListAdapter_Event_Chat.Item(ExpandableListAdapter_Event_Chat.Chat_Friend));

                    }

                    recyclerview.setAdapter(new ExpandableListAdapter_Event_Chat(data));
                    recyclerview.scrollToPosition(data.size()-1);
                    return rootView;
                }
            }

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return null;
        }
    }
}

class ExpandableListAdapter_Event_Details extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Image = 0;
    public static final int Event_Name = 1;
    public static final int Description = 2;
    public static final int Description_Child = 3;
    public static final int Location = 4;
    public static final int Date = 5;
    public static final int Date_Child = 6;
    public static final int Repeat = 7;
    public static final int Repeat_Child = 8;
    public static final int Alert = 9;

    private List<Item> data;

    public ExpandableListAdapter_Event_Details(List<Item> data) {
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
        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        switch (type) {
            case Image: {
                view = inflater.inflate(R.layout.new_event_detail_image, parent, false);
                ViewHolder_Image viewHolder_image = new ViewHolder_Image(view);
                return viewHolder_image;
            }
            case Event_Name: {
                view = inflater.inflate(R.layout.new_event_detail_event_name, parent, false);
                ViewHolder_Event_Name viewHolder_event_name = new ViewHolder_Event_Name(view);
                return viewHolder_event_name;
            }
            case Description: {
                view = inflater.inflate(R.layout.new_event_detail_description, parent, false);
                ViewHolder_Description viewHolder_description = new ViewHolder_Description(view);
                return viewHolder_description;
            }
            case Description_Child: {
                view = inflater.inflate(R.layout.new_event_detail_description_child, parent, false);
                ViewHolder_Description_Child viewHolder_description_child = new ViewHolder_Description_Child(view);
                return viewHolder_description_child;
            }
            case Location: {
                view = inflater.inflate(R.layout.new_event_detail_location, parent, false);
                ViewHolder_Location viewHolder_location = new ViewHolder_Location(view);
                return viewHolder_location;
            }
            case Date: {
                view = inflater.inflate(R.layout.new_event_detail_date, parent, false);
                ViewHolder_Date viewHolder_date = new ViewHolder_Date(view);
                return viewHolder_date;
            }
            case Date_Child: {
                view = inflater.inflate(R.layout.new_event_detail_date_child, parent, false);
                ViewHolder_Date_Child viewHolder_date_child = new ViewHolder_Date_Child(view);
                return viewHolder_date_child;
            }
            case Repeat: {
                view = inflater.inflate(R.layout.new_event_detail_repeat, parent, false);
                ViewHolder_Repeat viewHolder_repeat = new ViewHolder_Repeat(view);
                return viewHolder_repeat;
            }
            case Repeat_Child: {
                view = inflater.inflate(R.layout.new_event_detail_repeat_child, parent, false);
                ViewHolder_Repeat_Child viewHolder_repeat_child = new ViewHolder_Repeat_Child(view);
                return viewHolder_repeat_child;
            }
            case Alert: {
                view = inflater.inflate(R.layout.new_event_detail_alert, parent, false);
                ViewHolder_Alert viewHolder_alert = new ViewHolder_Alert(view);
                return viewHolder_alert;
            }

        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case Image: {
                break;

            }
            case Event_Name: {
                break;
            }
            case Description: {
                final ViewHolder_Description itemController = (ViewHolder_Description) holder;
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
                            item.invisibleChildren.add(data.remove(pos + 1));
                            notifyItemRangeRemoved(pos + 1, 1);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_down_float);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            data.add(pos + 1, item.invisibleChildren.get(0));
                            notifyItemRangeInserted(pos + 1, 1);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_up_float);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            }
            case Date: {
                final ViewHolder_Date itemController = (ViewHolder_Date) holder;
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
                            item.invisibleChildren.add(data.remove(pos + 1));
                            notifyItemRangeRemoved(pos + 1, 1);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_down_float);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            data.add(pos + 1, item.invisibleChildren.get(0));
                            notifyItemRangeInserted(pos + 1, 1);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_up_float);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            }
            case Repeat: {
                final ViewHolder_Repeat itemController = (ViewHolder_Repeat) holder;
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
                            item.invisibleChildren.add(data.remove(pos + 1));
                            notifyItemRangeRemoved(pos + 1, 1);
                            itemController.expand_arrow.setImageResource(android.R.drawable.arrow_down_float);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            data.add(pos + 1, item.invisibleChildren.get(0));
                            notifyItemRangeInserted(pos + 1, 1);
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

    private static class ViewHolder_Image extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Image(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Event_Name extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Event_Name(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Description extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_Description(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.expand_arrow);
        }
    }

    private static class ViewHolder_Description_Child extends RecyclerView.ViewHolder {

        public ViewHolder_Description_Child(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Location extends RecyclerView.ViewHolder {

        public ViewHolder_Location(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Date extends RecyclerView.ViewHolder {
        public ImageButton expand_arrow;
        public Item refferalItem;

        public ViewHolder_Date(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.expand_arrow);
        }
    }

    private static class ViewHolder_Date_Child extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Date_Child(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Repeat extends RecyclerView.ViewHolder {
        public Item refferalItem;
        public ImageButton expand_arrow;

        public ViewHolder_Repeat(View itemView) {
            super(itemView);
            expand_arrow = (ImageButton) itemView.findViewById(R.id.expand_arrow);
        }
    }

    private static class ViewHolder_Repeat_Child extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Repeat_Child(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Alert extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Alert(View itemView) {
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

class ExpandableListAdapter_Event_Friends extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Summarize = 0;
    public static final int User_My = 1;
    public static final int User_Yes = 2;
    public static final int User_Maybe = 3;
    public static final int User_No = 4;


    private List<Item> data;

    public ExpandableListAdapter_Event_Friends(List<Item> data) {
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
            case Summarize: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_friend_summerize, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }
            case User_My: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_friend_user_my, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }
            case User_Yes: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_friend_user_yes, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }
            case User_Maybe: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_friend_user_maybe, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }
            case User_No: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_friend_user_no, parent, false);
                ViewHolder_User viewHolder_user = new ViewHolder_User(view);
                return viewHolder_user;
            }


        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case User_Yes:
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


            case Summarize: {
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

class ExpandableListAdapter_Event_Tasks extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Task = 0;
    public static final int Task_Child = 1;

    private List<Item> data;

    public ExpandableListAdapter_Event_Tasks(List<Item> data) {
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
            case Task: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_task, parent, false);
                ViewHolder_Task viewHolder_task = new ViewHolder_Task(view);
                return viewHolder_task;
            }
            case Task_Child: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_task_child, parent, false);
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

class ExpandableListAdapter_Event_Chat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int Chat_My = 0;
    public static final int Chat_Friend = 1;

    private List<Item> data;

    public ExpandableListAdapter_Event_Chat(List<Item> data) {
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
            case Chat_My: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_chat_my, parent, false);
                ViewHolder_Chat_My viewHolder_chat_my = new ViewHolder_Chat_My(view);
                return viewHolder_chat_my;
            }
            case Chat_Friend: {
                inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.event_chat_user, parent, false);
                ViewHolder_Chat_Friend viewHolder_chat_friend = new ViewHolder_Chat_Friend(view);
                return viewHolder_chat_friend;
            }
        }
        return null;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Item item = data.get(position);
        switch (item.type) {
            case Chat_My: {
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
            case Chat_Friend: {

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


    private static class ViewHolder_Chat_My extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Chat_My(View itemView) {
            super(itemView);
        }
    }

    private static class ViewHolder_Chat_Friend extends RecyclerView.ViewHolder {
        public Item refferalItem;

        public ViewHolder_Chat_Friend(View itemView) {
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