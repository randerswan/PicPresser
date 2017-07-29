package cn.liangxiwen.picpresser;

import java.io.File;

public class PictureItem {
    private File picFile;

    public PictureItem(File picFile) {
        this.picFile = picFile;
    }

    public File getPicFile() {
        return picFile;
    }

    public void setPicFile(File picFile) {
        this.picFile = picFile;
    }
}
