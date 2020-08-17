package cn.liangxiwen.picpresser;

import java.io.File;

import it.sephiroth.android.library.exif2.ExifInterface;

public class ExifUtils {

    public static void copyExif(File newFile, File oldFile) {
        try {
            ExifInterface ei = new ExifInterface();
            ei.readExif(oldFile.getPath(), ExifInterface.Options.OPTION_ALL);
            ei.writeExif(newFile.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
