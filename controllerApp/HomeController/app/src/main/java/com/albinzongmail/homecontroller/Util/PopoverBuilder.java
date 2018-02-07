package com.albinzongmail.homecontroller.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.albinzongmail.homecontroller.R;

/**
 * Created by hannes on 2017-04-18.
 *
 * Provides tools for building a popover
 */

public class PopoverBuilder {

    public static AlertDialog.Builder createPopover(final Context c, String title, final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(title);
        builder.setView(view);

        // Set default handlers that can be overwritten later
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(c, R.string.default_notYetImplemented, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { dialogInterface.cancel(); }
        });

        return builder;
    }

    public static LinearLayout addInputField(Context c, LinearLayout view, String id, String hint) {
        EditText newInputField = new EditText(c);
        newInputField.setId(id); ///GSDLKJFsad
        newInputField.setText(hint);
        view.addView(newInputField);
        return view;
    }

}
