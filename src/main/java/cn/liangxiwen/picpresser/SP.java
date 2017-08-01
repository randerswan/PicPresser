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
    private static String EXTRA_SIZE = "cn.liangxiwen.picpresser.SP.EXTRA_SIZE";
    private static String EXTRA_QUALITY = "cn.liangxiwen.picpresser.SP.EXTRA_QUALITY";
    private static final int DEFAULT_SIZE = 1080;
    private static final int DEFAULT_QUALITY = 80;

    public static File getLastPath(Activity act) {
        String file = getString(act, EXTRA_NAME_LAST_PATH, "");
        return new File(file);
    }

    public static void saveLastPath(File file, Activity act) {
        saveString(act, file.toString(), EXTRA_NAME_LAST_PATH);
    }

    public static int getPicQuality(Activity act) {
        int size = getInt(act, EXTRA_QUALITY, DEFAULT_QUALITY);
        return size;
    }

    public static void savePicQuality(Activity act, int quality) {
        saveInt(act, EXTRA_QUALITY, quality);
    }

    public static int getPicSize(Activity act) {
        int size = getInt(act, EXTRA_SIZE, DEFAULT_SIZE);
        return size;
    }

    public static void saveSize(Activity act, int size) {
        saveInt(act, EXTRA_SIZE, size);
    }

    public static int getInt(Activity act, String extra, int defalutInt) {
        int value = act.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getInt(extra, defalutInt);
        return value;
    }

    public static String getString(Activity act, String extra, String defaultStr) {
        String value = act.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(extra, defaultStr);
        return value;
    }

    public static void saveString(Activity act, String extra, String value) {
        try {
            act.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(extra, value).commit();
        } catch (Exception e) {
        }
    }

    public static void saveInt(Activity act, String extra, int value) {
        try {
            act.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putInt(extra, value).commit();
        } catch (Exception e) {
        }
    }
}
