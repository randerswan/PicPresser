package cn.liangxiwen.picpresser;

import java.io.File;

public class PictureItem {
    private File picFile;
    private boolean isMaking;

    public PictureItem(File picFile) {
        this.picFile = picFile;
    }

    public File getPicFile() {
        return picFile;
    }

    public void setPicFile(File picFile) {
        this.picFile = picFile;
    }

    public boolean isFolder() {
        return picFile != null && picFile.isDirectory();
    }

    public boolean isMaking() {
        return isMaking;
    }

    public void setMaking(boolean making) {
        isMaking = making;
    }
}
