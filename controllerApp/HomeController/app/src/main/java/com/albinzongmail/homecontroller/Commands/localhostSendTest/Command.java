package com.albinzongmail.homecontroller.Commands.localhostSendTest;

import android.content.Context;
import android.widget.LinearLayout;

/**
 * Created by hannes on 2017-04-18.
 *
 * Superclass for command-modules
 */

abstract public class Command {



    public void Show(Context c) {
        //createBasicPopover(c, 1).
    }

    abstract public void Run();
}
