package cn.liangxiwen.picpresser;

import android.app.Activity;
import android.content.Context;

import java.io.File;

/**
 * Created by wen on 2017/7/30.
 */

public class SP {
    public static String FOLDER_FUFFIX = "_PicPresser";
    private static String SP_NAME = "cn.liangxiwen.picpresser.SP.SP_NAME";
    private static String EXTRA_NAME_LAST_PATH = "cn.liangxiwen.picpresser.SP.EXTRA_NAME_LAST_PATH";

    public static File getLastPath(Activity act) {
        String pathStr = act.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(EXTRA_NAME_LAST_PATH, "");
        if (pathStr != null && pathStr.trim().length() > 0) {
            try {
                File file = new File(pathStr);
                if (file.exists()) {
                    return file;
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static void saveLastPath(File file, Activity act) {
        try {
            act.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(EXTRA_NAME_LAST_PATH, file.toString()).commit();
        } catch (Exception e) {
        }
    }
}
