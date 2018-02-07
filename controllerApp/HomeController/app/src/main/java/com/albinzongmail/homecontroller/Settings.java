package com.albinzongmail.homecontroller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.albinzongmail.homecontroller.SettingsFiles.ConnectionPreset;
import com.albinzongmail.homecontroller.SettingsFiles.PresetListAdapter;
import com.albinzongmail.homecontroller.Util.Exceptions.InvalidIpAddress;
import com.albinzongmail.homecontroller.Util.ValidateIP;

public class Settings extends AppCompatActivity {

    private static final String TAG = "----Settings----";
    private static final String ERRORTAG = "----SettingsError----";

    private ListView presetListView;

    private static ConnectionPreset currentPreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Log.d(TAG, "Creating FAB");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Log.d(TAG, "Setting FAB image");
        fab.setImageResource(R.mipmap.ic_add_black);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addPresetBtnClicked();
            }
        });

        // Setup preset-listview
        PresetListAdapter adapter = new PresetListAdapter(this);
        presetListView = (ListView) findViewById(R.id.settings_presetListView);
        presetListView.setAdapter(adapter);
        presetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get and pass clicked item to clickHandler
                PresetListAdapter adapter = (PresetListAdapter) presetListView.getAdapter();
                setCurrentPreset(adapter.getItem(position));
            }
        });

        Log.d(TAG, "OnCreate finished");
    }

    private void addPresetBtnClicked() {
        Toast.makeText(this, "Adding new preset!", Toast.LENGTH_SHORT).show();

        // Setup form container
        LinearLayout popoverContainer = new LinearLayout(this);
        popoverContainer.setOrientation(LinearLayout.VERTICAL);

        // Setup inputfield for new preset value
        final EditText newNameET = new EditText(this);
        final EditText newIpET   = new EditText(this);
        final EditText newPortET = new EditText(this);

        newNameET.setHint(R.string.settings_newPresetNameHint);
        newNameET.setInputType(InputType.TYPE_CLASS_TEXT);

        newIpET.setHint(R.string.settings_newIpHint);
        newIpET.setInputType(InputType.TYPE_CLASS_TEXT);

        newPortET.setHint(R.string.settings_newPortHint);
        newPortET.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Add to container
        popoverContainer.addView(newNameET);
        popoverContainer.addView(newIpET);
        popoverContainer.addView(newPortET);

        // Create new popover builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.settings_newPresetTitle);
        builder.setView(popoverContainer);

        // Handle user answers (setup buttons)
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PresetListAdapter adapter = (PresetListAdapter) presetListView.getAdapter();
                String newName = newNameET.getText().toString();
                String newIP = newIpET.getText().toString();
                int newPort = Integer.parseInt(newPortET.getText().toString());

                // Validate IP
                try { ValidateIP.IsValid(newIP); }
                catch (InvalidIpAddress e) {
                    Log.d(ERRORTAG, e.getMessage());
                    Toast.makeText(Settings.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                // Add new preset to list
                adapter.add(new ConnectionPreset(newName, newIP, newPort));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { dialogInterface.cancel(); }
        });

        // Display form to user
        builder.show();

    }

    public void setCurrentPreset(ConnectionPreset preset) {
        currentPreset = preset;
    }

    public ConnectionPreset getCurrentPreset() {
        return currentPreset;
    }



}
