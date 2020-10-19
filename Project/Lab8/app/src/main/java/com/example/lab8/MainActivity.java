package com.example.lab8;



import android.app.TabActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity {


    /* Lab nao day khong phai Lab8 */
    /*private List<Restaurant> restaurantList = new ArrayList<Restaurant>();*/

    Cursor curRestaurant = null;
    RestaurantAdapter adapter = null;
    /*lab 5 private ArrayAdapter<Restaurant> adapter = null;*/

    /*lab 6*/
    /*RestaurantAdapter adapter = null;*/
    RestaurantHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_list);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave);


        // khoi tao doi duong RestaurantHelper
        helper = new RestaurantHelper(this);

        ListView list = (ListView) findViewById(R.id.restaurants);

       /* lab 5 adapter = new ArrayAdapter<Restaurant>(this,
                android.R.layout.simple_list_item_1, restaurantList);*/

        /*lab 6*/
      /* adapter = new RestaurantAdapter();*/

        /* lab 7 */
        list.setOnItemClickListener(onListClick);


        // lay du lieu tu CSDL
        curRestaurant = helper.getAll();
        startManagingCursor(curRestaurant);
        adapter = new RestaurantAdapter(curRestaurant);
        list.setAdapter(adapter);





        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details",
                getResources().getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
// Đóng cơ sở dữ liệu
        helper.close();
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();

            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            RadioGroup type = (RadioGroup)findViewById(R.id.type);
            switch (type.getCheckedRadioButtonId())
            {
                case R.id.take_out:
                    r.setType("Take out");
                    break;
                case R.id.sit_down:
                    r.setType("Sit down");
                    break;
                case R.id.delivery:
                    r.setType("Delivery");
                    break;
            }
            String result = "You choose: ";
            result += r.getName() +" "+ r.getAddress() + " " + r.getType();
            Toast.makeText( MainActivity.this
            , result , Toast.LENGTH_SHORT).show();


            /* day la mot bai nao do khong phai lab 8 */
            /*restaurantList.add(r);*/

            helper.insert(r.getName(), r.getAddress(), r.getType());


            // refesh lai du lieu
            curRestaurant.requery();
        }

    };





    /* Lab nao do khong phai Lab 8 */
    /*class RestaurantAdapter extends ArrayAdapter<Restaurant> {





        public RestaurantAdapter(Context context, int textViewResourceId){
            super(context, textViewResourceId);
        }

        public RestaurantAdapter(){
            super(MainActivity.this,
                    android.R.layout.simple_list_item_1, restaurantList);
        }


        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {
            *//*return super.getView(position, convertView, parent);*//*
            View row = convertView;

            if( row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);

            }


            Restaurant r = restaurantList.get(position);

            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
            ImageView icon = (ImageView) row.findViewById(R.id.icon);

            String type = r.getType();
            if(type.equals("Take out"))
                icon.setImageResource(R.drawable.t);
            else if(type.equals("Sit down"))
                icon.setImageResource(R.drawable.s);
            else
                icon.setImageResource(R.drawable.d);
            return  row;
        }
    }*/

    class RestaurantAdapter extends CursorAdapter
    {
        public RestaurantAdapter(Cursor c)
        {
            super(MainActivity.this, c);
        }
        public RestaurantAdapter(Context context, Cursor c) {
            super(context, c);
// TODO Auto-generated constructor stub
        }
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
// TODO Auto-generated method stub
            View row = view;
            ((TextView)row.findViewById(R.id.title)).
                    setText(helper.getName(cursor));
            ((TextView)row.findViewById(R.id.address)).
                    setText(helper.getAddress(cursor));
            ImageView icon = (ImageView)row.findViewById(R.id.icon);
            String type = helper.getType(cursor);
            if (type.equals("Take out"))
                icon.setImageResource(R.drawable.t);
            else if (type.equals("Sit down"))
                icon.setImageResource(R.drawable.s);
            else
                icon.setImageResource(R.drawable.d);
        }// end bindView

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
// TODO Auto-generated method stub
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            return row;
        }
    }// end class RestaurantAdapter


    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            /* Lab nao do khong phai Lab 8 */
            /* Restaurant r = restaurantList.get(position); // lấy item được chọn*/
            curRestaurant.moveToPosition(position);
            EditText name;
            EditText address;
            RadioGroup types;
// Tham chiếu đến các view trong details
            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.type);

            /* Lab nao do khong phai Lab 8 */
         /*   // thiết lập thông tin tương ứng
            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("Sit down"))
                types.check(R.id.sit_down);
            else if (r.getType().equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);
// sinh viên có thể bổ sung lệnh sau để chuyển view về tab details

            */

            /* Lab 8 */
            // sử dụng đối tượng helper để lấy các thông tin nhà hàng
            name.setText(helper.getName(curRestaurant));
            address.setText(helper.getAddress(curRestaurant));
            if (helper.getType(curRestaurant).equals("Sit down"))
                types.check(R.id.sit_down);
            else if (helper.getType(curRestaurant).equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);

            getTabHost().setCurrentTab(1);
        }
    };

}

