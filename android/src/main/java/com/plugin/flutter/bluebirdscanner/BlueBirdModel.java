package com.plugin.flutter.bluebirdscanner;

import android.content.Context;

/**
 * Created by krrigan on 5/31/19.
 */

public enum BlueBirdModel
{
    ef400_500;

    public BlueBirdScanner getScanner(Context context){
        switch(this){
            case ef400_500: return new EF400_500(context);
        }
        return null;
    }
}
