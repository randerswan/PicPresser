package cn.liangxiwen.picpresser;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.LinkedList;

public class PicureAdapter extends BaseAdapter {
    private LinkedList<PictureItem> picList = new LinkedList<PictureItem>();
    private Activity activity;
    private File currentFile;
    private PicPressListener listener;

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
        currentFile = parent;
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

        ProgressBar pb = (ProgressBar) itemView.findViewById(R.id.pb_item_pic_list_making);
        pb.setVisibility(item.isMaking() ? View.VISIBLE : View.GONE);

        return itemView;
    }

    public void startMakeSmallerPicture(PicPressListener listener) {
        this.listener = listener;
        if (makeThread == null) {
            File saveFile = new File(currentFile.getParentFile(), currentFile.getName() + SP.FOLDER_FUFFIX);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            makeThread = new MakeThread(saveFile);
            makeThread.start();
        }
    }

    private static MakeThread makeThread;

    private class MakeThread extends Thread {
        private Handler handler;
        private File saveFile;

        public MakeThread(File saveFile) {
            handler = new Handler();
            this.saveFile = saveFile;
        }

        @Override
        public void run() {
            handler.post(new PicPressStart());
            for (PictureItem item : picList) {
                item.setMaking(true);
                handler.post(new ItemNotifyRun());
                try {
                    File toSave = new File(saveFile, item.getPicFile().getName());
                    if (!toSave.exists()) {
                        Bitmap bm = BitmapUtils.getPicByMaxWidthOrHeight(item.getPicFile().toString(), 1080);
                        BitmapUtils.savePhotoToSDCard(saveFile.toString(), item.getPicFile().getName(), bm);
                        bm.recycle();
                    }
                } catch (Exception e) {
                }
                item.setMaking(false);
                handler.post(new ItemNotifyRun());
            }
            makeThread = null;
            handler.post(new PicPressEnd());
        }
    }

    private class ItemNotifyRun implements Runnable {
        @Override
        public void run() {
            notifyDataSetChanged();
        }
    }

    private class PicPressStart implements Runnable {
        @Override
        public void run() {
            if (listener != null) {
                listener.onPicPressStart();
            }
        }
    }

    private class PicPressEnd implements Runnable {
        @Override
        public void run() {
            if (listener != null) {
                listener.onPicPressEnd();
            }
        }
    }
}
