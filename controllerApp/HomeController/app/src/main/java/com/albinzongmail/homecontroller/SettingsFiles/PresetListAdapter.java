package com.albinzongmail.homecontroller.SettingsFiles;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hannes on 2017-03-29.
 *
 * Adapter containing connection presets
 */

public class PresetListAdapter extends ArrayAdapter<ConnectionPreset> {

    private Context context;

    public PresetListAdapter(Context context) { this(context, new ArrayList<ConnectionPreset>()); }
    public PresetListAdapter(Context context, List<ConnectionPreset> items) {
        super(context, 0, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PresetListAdapter shoppingListView = (PresetListAdapter)convertView;
        Log.d("SH_L_ADAPTER", "INFLATE");
        if (shoppingListView == null) shoppingListView = PresetListAdapter.inflate(parent);
        shoppingListView.setItem(getItem(position));
        return shoppingListView;
    }

}
