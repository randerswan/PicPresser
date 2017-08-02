package cn.liangxiwen.picpresser;

import android.media.ExifInterface;

import java.io.File;

public class ExifUtils {

    public static void copyExif(File newFile, File oldFile) {
        try {
            ExifInterface newExif = new ExifInterface(newFile.toString());
            ExifInterface oldExif = new ExifInterface(oldFile.toString());
            for (String strArg : strArr) {
                try {
                    String oldStr = oldExif.getAttribute(strArg);
                    newExif.setAttribute(strArg, oldStr);
                } catch (Exception e) {
                }
            }
            newExif.saveAttributes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] doubleArr = new String[]{
            ExifInterface.TAG_DIGITAL_ZOOM_RATIO
            ,//Type is double.
            ExifInterface.TAG_EXPOSURE_BIAS_VALUE
            ,//Type is double.
            ExifInterface.TAG_EXPOSURE_TIME
            ,//Type is double.
            ExifInterface.TAG_APERTURE
            ,//This constant was deprecated in API level 24. use TAG_F_NUMBER instead
            ExifInterface.TAG_F_NUMBER
            ,//Type is double.
            ExifInterface.TAG_SUBJECT_DISTANCE
            ,//Type is double.
    };

//    private static String [] undefArr = new String[]{
//            ExifInterface.TAG_ORF_THUMBNAIL_IMAGE
//            ,//Type is undefined.
//            ExifInterface.TAG_RW2_JPG_FROM_RAW
//            ,//Type is undefined.
//    };

    private static String[] strArr = new String[]{
            ExifInterface.TAG_ARTIST
            ,//Type is String.
            ExifInterface.TAG_CFA_PATTERN
            ,//Type is String.
            ExifInterface.TAG_COMPONENTS_CONFIGURATION
            ,//Type is String.
            ExifInterface.TAG_COPYRIGHT
            ,//Type is String.
            ExifInterface.TAG_DATETIME
            ,//Type is String.
            ExifInterface.TAG_DATETIME_DIGITIZED
            ,//Type is String.
            ExifInterface.TAG_DATETIME_ORIGINAL
            ,//Type is String.
            ExifInterface.TAG_DEVICE_SETTING_DESCRIPTION
            ,//Type is String.
            ExifInterface.TAG_EXIF_VERSION
            ,//Type is String.
            ExifInterface.TAG_FILE_SOURCE
            ,//Type is String.
            ExifInterface.TAG_FLASHPIX_VERSION
            ,//Type is String.
            ExifInterface.TAG_GPS_AREA_INFORMATION
            ,//Type is String.
            ExifInterface.TAG_GPS_DATESTAMP
            ,//Type is String.
            ExifInterface.TAG_GPS_DEST_BEARING_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_DEST_DISTANCE_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_DEST_LATITUDE_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_DEST_LONGITUDE_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_IMG_DIRECTION_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_LATITUDE_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_LONGITUDE_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_MAP_DATUM
            ,//Type is String.
            ExifInterface.TAG_GPS_MEASURE_MODE
            ,//Type is String.
            ExifInterface.TAG_GPS_PROCESSING_METHOD
            ,//Type is String.
            ExifInterface.TAG_GPS_SATELLITES
            ,//Type is String.
            ExifInterface.TAG_GPS_SPEED_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_STATUS
            ,//Type is String.
            ExifInterface.TAG_GPS_TIMESTAMP
            ,//Type is String.
            ExifInterface.TAG_GPS_TRACK_REF
            ,//Type is String.
            ExifInterface.TAG_GPS_VERSION_ID
            ,//Type is String.
            ExifInterface.TAG_IMAGE_DESCRIPTION
            ,//Type is String.
            ExifInterface.TAG_IMAGE_UNIQUE_ID
            ,//Type is String.
            ExifInterface.TAG_INTEROPERABILITY_INDEX
            ,//Type is String.
            ExifInterface.TAG_MAKE
            ,//Type is String.
            ExifInterface.TAG_MAKER_NOTE
            ,//Type is String.
            ExifInterface.TAG_MODEL
            ,//Type is String.
            ExifInterface.TAG_OECF
            ,//Type is String.
            ExifInterface.TAG_RELATED_SOUND_FILE
            ,//Type is String.
            ExifInterface.TAG_SCENE_TYPE
            ,//Type is String.
            ExifInterface.TAG_SOFTWARE
            ,//Type is String.
            ExifInterface.TAG_SPATIAL_FREQUENCY_RESPONSE
            ,//Type is String.
            ExifInterface.TAG_SPECTRAL_SENSITIVITY
            ,//Type is String.
            ExifInterface.TAG_SUBSEC_TIME
            ,//Type is String.
            ExifInterface.TAG_SUBSEC_TIME_DIG
            ,//This constant was deprecated in API level 24. use TAG_SUBSEC_TIME_DIGITIZED instead
            ExifInterface.TAG_SUBSEC_TIME_DIGITIZED
            ,//Type is String.
            ExifInterface.TAG_SUBSEC_TIME_ORIG
            ,//This constant was deprecated in API level 24. use TAG_SUBSEC_TIME_ORIGINAL instead
            ExifInterface.TAG_SUBSEC_TIME_ORIGINAL
            ,//Type is String.
            ExifInterface.TAG_USER_COMMENT
            ,//Type is String.


            ExifInterface.TAG_APERTURE_VALUE
            ,//Type is rational.
            ExifInterface.TAG_BRIGHTNESS_VALUE
            ,//Type is rational.
            ExifInterface.TAG_COMPRESSED_BITS_PER_PIXEL
            ,//Type is rational.
            ExifInterface.TAG_EXPOSURE_INDEX
            ,//Type is rational.
            ExifInterface.TAG_FLASH_ENERGY
            ,//Type is rational.
            ExifInterface.TAG_FOCAL_LENGTH
            ,//Type is rational.
            ExifInterface.TAG_FOCAL_PLANE_X_RESOLUTION
            ,//Type is rational.
            ExifInterface.TAG_FOCAL_PLANE_Y_RESOLUTION
            ,//Type is rational.
            ExifInterface.TAG_GPS_DEST_BEARING
            ,//Type is rational.
            ExifInterface.TAG_GPS_DEST_DISTANCE
            ,//Type is rational.
            ExifInterface.TAG_GPS_DEST_LATITUDE
            ,//Type is rational.
            ExifInterface.TAG_GPS_DEST_LONGITUDE
            ,//Type is rational.
            ExifInterface.TAG_GPS_DOP
            ,//Type is rational.
            ExifInterface.TAG_GPS_IMG_DIRECTION
            ,//Type is rational.
            ExifInterface.TAG_GPS_LATITUDE
            ,//Type is rational.
            ExifInterface.TAG_GPS_LONGITUDE
            ,//Type is rational.
            ExifInterface.TAG_GPS_SPEED
            ,//Type is rational.
            ExifInterface.TAG_GPS_TRACK
            ,//Type is rational.
            ExifInterface.TAG_MAX_APERTURE_VALUE
            ,//Type is rational.
            ExifInterface.TAG_PRIMARY_CHROMATICITIES
            ,//Type is rational.
            ExifInterface.TAG_REFERENCE_BLACK_WHITE
            ,//Type is rational.
            ExifInterface.TAG_SHUTTER_SPEED_VALUE
            ,//Type is rational.
            ExifInterface.TAG_WHITE_POINT
            ,//Type is rational.
            ExifInterface.TAG_X_RESOLUTION
            ,//Type is rational.
            ExifInterface.TAG_Y_CB_CR_COEFFICIENTS
            ,//Type is rational.
            ExifInterface.TAG_Y_RESOLUTION
            ,//Type is rational.
    };

    private static String[] intArr = new String[]{
            ExifInterface.TAG_BITS_PER_SAMPLE
            ,//Type is int.
            ExifInterface.TAG_COLOR_SPACE
            ,//Type is int.
            ExifInterface.TAG_COMPRESSION
            ,//Type is int.
            ExifInterface.TAG_CONTRAST
            ,//Type is int.
            ExifInterface.TAG_CUSTOM_RENDERED
            ,//Type is int.
//    ExifInterface.TAG_DEFAULT_CROP_SIZE
//,//Type is int.
//    ExifInterface.TAG_DNG_VERSION
//,//Type is int.
            ExifInterface.TAG_EXPOSURE_MODE
            ,//Type is int.
            ExifInterface.TAG_EXPOSURE_PROGRAM
            ,//Type is int.
            ExifInterface.TAG_FLASH
            ,//Type is int.
            ExifInterface.TAG_FOCAL_LENGTH_IN_35MM_FILM
            ,//Type is int.
            ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT
            ,//Type is int.
            ExifInterface.TAG_GAIN_CONTROL
            ,//Type is int.
            ExifInterface.TAG_GPS_ALTITUDE
            ,            //The altitude(in meters) based on the reference in TAG_GPS_ALTITUDE_REF.
            ExifInterface.TAG_GPS_ALTITUDE_REF
            ,//0if the altitude is above sea level.
            ExifInterface.TAG_GPS_DIFFERENTIAL
            ,//Type is int.
            ExifInterface.TAG_IMAGE_LENGTH
            ,//Type is int.
            ExifInterface.TAG_IMAGE_WIDTH
            ,//Type is int.
            ExifInterface.TAG_ISO
            ,//This constant was deprecated in API level 24. use TAG_ISO_SPEED_RATINGS instead
            ExifInterface.TAG_ISO_SPEED_RATINGS
            ,//Type is int.
            ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT
            ,//Type is int.
            ExifInterface.TAG_JPEG_INTERCHANGE_FORMAT_LENGTH
            ,//Type is int.
            ExifInterface.TAG_LIGHT_SOURCE
            ,//Type is int.
            ExifInterface.TAG_METERING_MODE
            ,//Type is int.
//    ExifInterface.TAG_NEW_SUBFILE_TYPE
//,//Type is int.
//    ExifInterface.TAG_ORF_ASPECT_FRAME
//,//Type is int.
//    ExifInterface.TAG_ORF_PREVIEW_IMAGE_LENGTH
//,//Type is int.
//    ExifInterface.TAG_ORF_PREVIEW_IMAGE_START
//,//Type is int.
            ExifInterface.TAG_ORIENTATION
            ,//Type is int.
            ExifInterface.TAG_PHOTOMETRIC_INTERPRETATION
            ,//Type is int.
            ExifInterface.TAG_PIXEL_X_DIMENSION
            ,//Type is int.
            ExifInterface.TAG_PIXEL_Y_DIMENSION
            ,//Type is int.
            ExifInterface.TAG_PLANAR_CONFIGURATION
            ,//Type is int.
            ExifInterface.TAG_RESOLUTION_UNIT
            ,//Type is int.
            ExifInterface.TAG_ROWS_PER_STRIP
            ,//Type is int.
//    ExifInterface.TAG_RW2_ISO
//,//Type is int.
//    ExifInterface.TAG_RW2_SENSOR_BOTTOM_BORDER
//,//Type is int.
//    ExifInterface.TAG_RW2_SENSOR_LEFT_BORDER
//,//Type is int.
//    ExifInterface.TAG_RW2_SENSOR_RIGHT_BORDER
//,//Type is int.
//    ExifInterface.TAG_RW2_SENSOR_TOP_BORDER
//,//Type is int.
            ExifInterface.TAG_SAMPLES_PER_PIXEL
            ,//Type is int.
            ExifInterface.TAG_SATURATION
            ,//Type is int.
            ExifInterface.TAG_SCENE_CAPTURE_TYPE
            ,//Type is int.
            ExifInterface.TAG_SENSING_METHOD
            ,//Type is int.
            ExifInterface.TAG_SHARPNESS
            ,//Type is int.
            ExifInterface.TAG_STRIP_BYTE_COUNTS
            ,//Type is int.
            ExifInterface.TAG_STRIP_OFFSETS
            ,//Type is int.
//    ExifInterface.TAG_SUBFILE_TYPE
//,//Type is int.
            ExifInterface.TAG_SUBJECT_AREA
            ,//Type is int.
            ExifInterface.TAG_SUBJECT_DISTANCE_RANGE
            ,//Type is int.
            ExifInterface.TAG_SUBJECT_LOCATION
            ,//Type is int.
            ExifInterface.TAG_THUMBNAIL_IMAGE_LENGTH
            ,//Type is int.
            ExifInterface.TAG_THUMBNAIL_IMAGE_WIDTH
            ,//Type is int.
            ExifInterface.TAG_TRANSFER_FUNCTION
            ,//Type is int.
            ExifInterface.TAG_WHITE_BALANCE
            ,//Type is int.
            ExifInterface.TAG_Y_CB_CR_POSITIONING
            ,//Type is int.
            ExifInterface.TAG_Y_CB_CR_SUB_SAMPLING
            ,//Type is int.
    };
}
