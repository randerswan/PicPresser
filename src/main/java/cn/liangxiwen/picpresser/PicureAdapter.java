package cn.liangxiwen.picpresser;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.LinkedList;

public class PicureAdapter extends BaseAdapter {
    private LinkedList<PictureItem> picList = new LinkedList<PictureItem>();
    private Activity activity;

    public PicureAdapter(Activity activity, LinkedList<PictureItem> picList) {
        this.activity = activity;
        this.picList = picList;
    }

    public PicureAdapter(Activity activity, File parent) {
        this.activity = activity;
        resetFile(parent);
    }

    public void resetFile(File parent) {
        if (parent != null) {
            picList.clear();
        }
        File[] files = parent == null ? null : parent.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            File file = files[i];
            PictureItem item = new PictureItem(file);
            picList.add(item);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public PictureItem getItem(int i) {
        return picList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        PictureItem item = picList.get(i);
        View itemView = view;
        if (itemView == null) {
            itemView = View.inflate(activity, R.layout.item_pic_list, null);
        }
        TextView tv = (TextView) itemView.findViewById(R.id.tv_item_pic_list_name);
        tv.setText(item.getPicFile().getName());
        TextView tvFolder = (TextView) itemView.findViewById(R.id.tv_item_pic_list_folder_icon);
        tvFolder.setVisibility(item.isFolder() ? View.VISIBLE : View.GONE);
        return itemView;
    }
}
