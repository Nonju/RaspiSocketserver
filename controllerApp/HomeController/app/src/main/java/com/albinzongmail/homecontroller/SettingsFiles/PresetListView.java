package com.albinzongmail.homecontroller.SettingsFiles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.albinzongmail.homecontroller.R;

/**
 * Created by hannes on 2017-03-29.
 */

public class PresetListView extends RelativeLayout {

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
        titleTextView = (TextView) findViewById(R.id.item_titleTextView);
        //descTextView = (TextView) findViewById(R.id.item_descriptionTextView);
    }


    public void setItem(ShoppingList item) {
        titleTextView.setText(item.getTitle());
        //descTextView.setText(item.getDescription());
    }
}
