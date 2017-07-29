package cn.liangxiwen.picpresser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class PicPressMainActivity extends Activity {
    private File picPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private TextView tvPath;
    private ListView lvPicList;
    private PicureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_press_main);
        initPicPresserViews();
    }

    private void initPicPresserViews() {
        tvPath = (TextView) findViewById(R.id.et_pic_presser_main);
        tvPath.setText(picPath.toString());

        lvPicList = (ListView) findViewById(R.id.lv_pic_presser_main);
        adapter = new PicureAdapter(this, picPath);
        lvPicList.setAdapter(adapter);
    }

}
