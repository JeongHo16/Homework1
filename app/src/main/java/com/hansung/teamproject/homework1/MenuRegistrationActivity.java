package com.hansung.teamproject.homework1;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuRegistrationActivity extends AppCompatActivity {
    private File mPhotoFile;
    private String mPhotoFileName;
    final int REQUEST_IMAGE_CAPTURE = 200;
    Uri imageUri;
    int count = 0;

    EditText menuTitle;
    EditText menuPrice;
    EditText menuDescription;
    ImageButton menuImage;
    Button menuAdd;
    MenuHelper menuHelper;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registration);

        menuTitle = (EditText) findViewById(R.id.menutitle);
        menuPrice = (EditText) findViewById(R.id.menuprice);
        menuDescription = (EditText) findViewById(R.id.menudescription);
        menuHelper = new MenuHelper(this);

        Intent intent = getIntent();
        title= intent.getStringExtra("plusesName");

        menuImage = (ImageButton) findViewById(R.id.menuimage);
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        menuAdd = (Button) findViewById(R.id.menuadd);
        menuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuSaveToDB();
            }
        });
    }

    private void menuSaveToDB() {
        String restitle = title;
        String menutitle = String.valueOf(menuTitle.getText());
        String menuprice = String.valueOf(menuPrice.getText());
        String menudescription = String.valueOf(menuDescription.getText());
        String menuImage = String.valueOf(imageUri);

        Cursor cursor = menuHelper.getAllUsersBySQL();

        while(cursor.moveToNext()){     // cursor가 움직이면서 입력된 메뉴이름과 같은게 있는지 확인하고 있으면 count ++
            if(cursor.getString(1).equals(title)){
                if(cursor.getString(3).equals(menutitle)){
                    count++;
                    Toast.makeText(getApplicationContext(), "이미 등록된 메뉴입니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

        }
        if(count == 0){             //count가 만약 0이면 그대로 db에 입력해주기
            menuHelper.insertUserBySQL(restitle, menuImage, menutitle, menuprice, menudescription);
            Toast.makeText(getApplicationContext(), "메뉴가 등록되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            count = 0;
            return;
        }
        Intent intent = new Intent(getApplicationContext(), RestaurantDetailActivity.class);        // 인텐트 선언
        intent.putExtra("plusesName", title);                           // 데이터 넣기 (가게이름)
        setResult(RESULT_OK, intent);
        startActivity(intent);  // 인텐트 보내줌(어떤 가게인지)
    }

    private String currentDateFormat() { //10주차 실습 참고 코드
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }

    private void dispatchTakePictureIntent() {      // 카메라 찍기 //10주차 실습 참고 코드
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //1. 카메라 앱으로 찍은 이미지를 저장할 파일 객체 생성
            mPhotoFileName = "IMG" + currentDateFormat() + ".jpg";
            mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);
            if (mPhotoFile != null) {
                //2. 생성된 파일 객체에 대한 Uri 객체를 얻기
                imageUri = FileProvider.getUriForFile(this, "com.hansung.teamproject.homework1", mPhotoFile);
                //3. Uri 객체를 Extras를 통해 카메라 앱으로 전달
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else
                Toast.makeText(getApplicationContext(), "file null", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //10주차 실습 참고 코드
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (mPhotoFileName != null) {
                mPhotoFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), mPhotoFileName);

                ImageButton menuImage = (ImageButton) findViewById(R.id.menuimage);
                menuImage.setImageURI(Uri.fromFile(mPhotoFile));

                // 파일 추가
                File saveFile = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                File[] files = saveFile.listFiles();
                if (files != null) {
                    for (File f : files) {
                        Log.i("Save_pictures", "File name = " + f.getName());
                        Log.i("Save_pictures_path", "Picture_path = " + f.getPath());
                        Uri.parse("files://" + Environment.getExternalStorageDirectory().getPath() + "/Pictures/" + f.getName());
                    }
                }
            }
        }
    }
}
