package cn.liangxiwen.picpresser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class PicPressMainActivity extends Activity implements AdapterView.OnItemClickListener {
    private String FOLDER_FUFFIX = "_PicPresser";
    private File picPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    private TextView tvPath;
    private ListView lvPicList;
    private PicureAdapter adapter;
    private View bnParentFolder;
    private View bnMake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_press_main);
        File last = SP.getLastPath(this);
        if (last != null && last.exists()) {
            picPath = last;
        }
        initPicPresserViews();
    }

    private void initPicPresserViews() {
        tvPath = (TextView) findViewById(R.id.et_pic_presser_main);
        tvPath.setText(picPath.toString());

        lvPicList = (ListView) findViewById(R.id.lv_pic_presser_main);
        adapter = new PicureAdapter(this, picPath);
        lvPicList.setAdapter(adapter);
        lvPicList.setOnItemClickListener(this);

        bnParentFolder = findViewById(R.id.bn_picpresser_parent_folder);
        bnParentFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    File parent = picPath.getParentFile();
                    if (parent.exists()) {
                        picPath = parent;
                        adapter.resetFile(parent);
                        tvPath.setText(parent.toString());
                    }
                } catch (Exception e) {
                }
            }
        });

        bnMake = findViewById(R.id.bn_picpresser_create);
        bnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        PictureItem item = adapter.getItem(i);
        if (item.isFolder()) {
            File folder = item.getPicFile();
            SP.saveLastPath(folder, this);
            adapter.resetFile(folder);
            tvPath.setText(folder.toString());
        }
    }
}
