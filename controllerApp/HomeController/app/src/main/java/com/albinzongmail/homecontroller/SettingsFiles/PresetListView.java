package com.albinzongmail.homecontroller.SettingsFiles;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.albinzongmail.homecontroller.R;
import com.albinzongmail.homecontroller.Settings;

/**
 * Created by hannes on 2017-03-29.
 *
 */

public class PresetListView extends RelativeLayout {

    private TextView presetIP;
    private RadioButton radioBtn;

    public PresetListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.presetlist_item_view_children, this, true);
        setupChildren();
    }

    public static PresetListView inflate(ViewGroup parent) {
        PresetListView presetListView = (PresetListView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.presetlist_item_view, parent, false);
        return presetListView;
    }

    private void setupChildren() {
        presetIP = (TextView) findViewById(R.id.settings_itemPresetIP);
        radioBtn = (RadioButton) findViewById(R.id.settings_itemPresetRadiobutton);
        radioBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) { radioButtonClicked(); }
        });
    }

    private void radioButtonClicked() {
        Log.d("---RADIOBUTTON----", "Radiobutton toggled");
        radioBtn.toggle();
    }


    public void setItem(ConnectionPreset item) {
        presetIP.setText(item.getName());
        radioBtn.setChecked(item.isSelected());
    }
}
