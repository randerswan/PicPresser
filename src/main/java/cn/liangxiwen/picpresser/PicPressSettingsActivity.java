package cn.liangxiwen.picpresser;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class PicPressSettingsActivity extends Activity {
    private TextView tvSize;
    private TextView tvQuality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_press_settings);
        tvSize = (TextView) findViewById(R.id.et_picpresser_setting_size);
        tvQuality = (TextView) findViewById(R.id.et_picpresser_setting_quality);

        tvSize.addTextChangedListener(sizeWatcher);
        tvQuality.addTextChangedListener(qualityWatcher);

        tvSize.setText(Integer.toString(SP.getPicSize(this)));
        tvQuality.setText(Integer.toString(SP.getPicQuality(this)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvSize.addTextChangedListener(null);
        tvQuality.addTextChangedListener(null);
        tvSize = null;
        tvQuality = null;
    }

    private TextWatcher sizeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            try {
                SP.saveSize(PicPressSettingsActivity.this, Integer.parseInt(editable.toString()));
            } catch (Exception e) {
            }
        }
    };

    private TextWatcher qualityWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            try {
                SP.savePicQuality(PicPressSettingsActivity.this, Integer.parseInt(editable.toString()));
            } catch (Exception e) {
            }
        }
    };

}
