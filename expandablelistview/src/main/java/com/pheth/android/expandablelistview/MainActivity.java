package com.pheth.android.expandablelistview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> groupArray;
    private  List<List<String>> childArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        groupArray = new  ArrayList<String>();
        childArray = new  ArrayList<List<String>>();

        groupArray.add("第一行" );
        groupArray.add("第二行" );

        List<String> tempArray = new ArrayList<String>();
        tempArray.add("第一条---" );
        tempArray.add("第二条---" );
        tempArray.add("第三条---" );

        for (int  index = 0 ; index <groupArray.size(); ++index) {
            childArray.add(tempArray);
        }

        final ExpandableListView expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new  ExpandableAdapter(this));
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                // 此处可以处理view事件
                return false;
            }
        });


    }


    //ExpandableListView的Adapter
    public  class  ExpandableAdapter extends BaseExpandableListAdapter
    {
        Activity activity;

        public  ExpandableAdapter(Activity a)
        {
            activity = a;
        }
        public  Object getChild(int  groupPosition, int  childPosition)
        {
            return  childArray.get(groupPosition).get(childPosition);
        }
        public  long  getChildId(int  groupPosition, int  childPosition)
        {
            return  childPosition;
        }
        public  int  getChildrenCount(int  groupPosition)
        {
            return  childArray.get(groupPosition).size();
        }
        public  View getChildView(int  groupPosition, int  childPosition,
                                  boolean  isLastChild, View convertView, ViewGroup parent)
        {
            String string = childArray.get(groupPosition).get(childPosition);
            return  getGenericView(string);
        }
        // group method stub
        public  Object getGroup(int  groupPosition)
        {
            return  groupArray.get(groupPosition);
        }
        public  int  getGroupCount()
        {
            return  groupArray.size();
        }
        public  long  getGroupId(int  groupPosition)
        {
            return  groupPosition;
        }
        public  View getGroupView(int  groupPosition, boolean  isExpanded,
                                  View convertView, ViewGroup parent)
        {
            String string = groupArray.get(groupPosition);
            final TextView genericView = getGenericView(string);
            if (isExpanded){
                genericView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }else {
                genericView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
            return genericView;
        }
        // View stub to create Group/Children 's View
        public TextView getGenericView(String string)
        {
            // Layout parameters for the ExpandableListView
            AbsListView.LayoutParams layoutParams = new  AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, 64 );
            TextView text = new  TextView(activity);
            text.setLayoutParams(layoutParams);
            // Center the text vertically
            text.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
            // Set the text starting position
            text.setPadding(36 , 0 , 0 , 0 );
            text.setText(string);
            return  text;
        }
        public  boolean  hasStableIds()
        {
            return  false ;
        }
        public  boolean  isChildSelectable(int  groupPosition, int  childPosition)
        {
            return  true ;
        }
    }
}
