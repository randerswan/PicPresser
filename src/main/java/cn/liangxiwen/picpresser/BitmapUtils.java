package cn.liangxiwen.picpresser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils {
    public static Bitmap toDesSizeBitmap(Bitmap bm, int desW, int desH) {
        if (bm == null) {
            return null;
        }
        Options opts = new Options();
        int bmW = bm.getWidth();
        int bmH = bm.getHeight();
        if (desW > 0 && desH > 0) {
            float desF = desW * 1f / desH;
            float bmF = bmW * 1f / bmH;

            boolean bmTaller = desF > bmF;
            float bmDescW = bmTaller ? bmW : bmH * desF;
            float bmDescH = bmDescW / desF;

            int x = bmTaller ? 0 : (int) ((bmW - bmDescW) / 2);
            int y = bmTaller ? (int) ((bmH - bmDescH) / 2) : 0;

            opts.inJustDecodeBounds = false;
            Matrix ma = new Matrix();
            if (bmDescW > desW) {
                float s = desW * 1f / bmDescW;
                ma.setScale(s, s);
            }
            bm = Bitmap.createBitmap(bm, x, y, (int) bmDescW, (int) bmDescH, ma, true);
        }
        return bm;
    }

    public static Bitmap toDesSizeBitmap(byte[] source, int desW, int desH) {
        Bitmap bm = BitmapFactory.decodeByteArray(source, 0, source.length);
        return toDesSizeBitmap(bm, desW, desH);
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation =
                    exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
        }
        return degree;
    }

    public static String savePhotoToSDCard(String path, String photoName, Bitmap photoBitmap) {

        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File dir = new File(path);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 创建.nomedia文件屏蔽扫描
            File nomedia = new File(path + ".nomedia");
            if (!nomedia.exists())
                try {
                    nomedia.createNewFile();
                } catch (IOException e1) {
                }

            File photoFile = new File(path, photoName); // 在指定路径下创建文件
            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {

                    if (photoBitmap.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream)) {
                        fileOutputStream.flush();
                    }

                    photoBitmap.recycle();
                    photoBitmap = null;
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                return null;
            } catch (IOException e) {
                photoFile.delete();
                return null;
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
            return path + photoName;
        }
        return null;
    }

    public static Bitmap matrixBitmap(Bitmap bitmap, int rotate, float scale) {
        if (bitmap == null)
            return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix mtx = new Matrix();
        mtx.postRotate(rotate);
        mtx.setScale(scale, scale);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    /**
     * 判断图片长宽之较短值是否小于或等于minWidthOrHeight
     *
     * @param fileStr
     * @param minWidthOrHeight
     * @return
     */
    public static boolean isMinWHLessThan(String fileStr, int minWidthOrHeight) {
        Options op = new Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileStr, op);
        int min1 = Math.min(op.outWidth, op.outHeight);
        return min1 <= minWidthOrHeight;
    }

    /**
     * 按比例得到小Bitmap，使其长宽之较短值为maxWidthOrHeight，长宽之短本身小于maxWidthOrHeight，则直接返回Bitmap
     *
     * @param fileStr
     * @param maxWidthOrHeight
     * @return
     */
    public static Bitmap getPicByMaxWidthOrHeight(String fileStr, int maxWidthOrHeight) {
        if (isMinWHLessThan(fileStr, maxWidthOrHeight)) {
            return BitmapFactory.decodeFile(fileStr);
        }

        Bitmap bm = null;
        Options lastOptions = new Options();
        lastOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileStr, lastOptions);
        for (int i = 1; i < 25; i++) {
            Options options = new Options();
            options.inSampleSize = (int) Math.pow(2, i);
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(fileStr, options);
            int minWH = Math.min(options.outWidth, options.outHeight);
            int lastMinWH = Math.min(lastOptions.outWidth, lastOptions.outHeight);
            if (maxWidthOrHeight < lastMinWH && maxWidthOrHeight >= minWH) {
                lastOptions.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(fileStr, lastOptions);
                break;
            } else {
                lastOptions = options;
            }
        }

        if (bm != null) {
            Bitmap bigBm = bm;
            int max = Math.max(lastOptions.outWidth, lastOptions.outHeight);
            int min = Math.min(lastOptions.outWidth, lastOptions.outHeight);
            boolean taller = lastOptions.outHeight > lastOptions.outWidth;
            float width = taller ? maxWidthOrHeight : (maxWidthOrHeight * (1f * max / min));
            float height = taller ? (maxWidthOrHeight * (1f * max / min)) : maxWidthOrHeight;
            bm = Bitmap.createScaledBitmap(bigBm, (int) width, (int) height, true);
            bigBm.recycle();
        }

        return bm;
    }

    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength,
                                        int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;

    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength,
                                                int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound =
                (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound =
                (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }

    }
}