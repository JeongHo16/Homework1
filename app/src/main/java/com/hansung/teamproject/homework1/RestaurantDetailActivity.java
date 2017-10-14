package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

<<<<<<< HEAD
        ArrayList<MyItem> data = new ArrayList<MyItem>();
=======
<<<<<<< HEAD
=======
>>>>>>> e53d7c5e5d8fb3ea93c48805fb0ea1238447301d

        data.add(new MyItem(R.drawable.noodle_soup, "손칼국수", "5.000", "4.5"));
        data.add(new MyItem(R.drawable.bossam_formality, "보쌈 정식", "7.000", "4.0"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 중", "25.000", "4.1"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 대", "30.000", "3.7"));
<<<<<<< HEAD
=======
=======
>>>>>>> 3442466593f231b122db4c691cfe24e696c5bab0
        ArrayList<MyItem> data = new ArrayList<MyItem>();
        data.add(new MyItem(R.drawable.noodle_soup, "손칼국수", "5.000"));
        data.add(new MyItem(R.drawable.bossam_formality, "보쌈 정식", "7.000"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 중", "25.000"));
        data.add(new MyItem(R.drawable.bossam_m, "보쌈 대", "30.000"));
>>>>>>> 3385f2f74eccf98ae009f8e609c5b4d61576d389
>>>>>>> e53d7c5e5d8fb3ea93c48805fb0ea1238447301d

        final CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_view_lay, data);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        listView.setDivider(new ColorDrawable(Color.BLACK));
        listView.setDividerHeight(5);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int imageView = ((MyItem)adapter.getItem(i)).image;
                String name = ((MyItem)adapter.getItem(i)).name;
                String price = ((MyItem)adapter.getItem(i)).price;
                String point = ((MyItem)adapter.getItem(i)).point;

                Intent intent = new Intent(getApplicationContext(), MenuDetailActivity.class);

                intent.putExtra("name", name.toString());
                intent.putExtra("price", price.toString());
                intent.putExtra("image", imageView);
                intent.putExtra("point", point);
                startActivity(intent);
<<<<<<< HEAD
=======
=======
                intent.putExtra("item_image", imageView);
                intent.putExtra("item_name", name);
                intent.putExtra("item_price", price);
                //startActivity(intent);
<<<<<<< HEAD
=======
>>>>>>> 3385f2f74eccf98ae009f8e609c5b4d61576d389

>>>>>>> e53d7c5e5d8fb3ea93c48805fb0ea1238447301d

>>>>>>> 3442466593f231b122db4c691cfe24e696c5bab0
            }
        });
    }

        public void call(View v){
            TextView textView = (TextView)findViewById(R.id.phonenumber);
            String callNum = textView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+callNum));
            startActivity(intent);
        }

}
