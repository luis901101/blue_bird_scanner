package com.plugin.flutter.bluebirdscanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by krrigan on 12/07/18.
 */

public class EF400_500 extends BlueBirdScanner
{
    class Constants {
        public static final String STATUS_CLOSE = "STATUS_CLOSE";
        public static final String STATUS_OPEN = "STATUS_OPEN";
        public static final String STATUS_TRIGGER_ON = "STATUS_TRIGGER_ON";

        public static final int SEQ_BARCODE_OPEN = 100;
        public static final int SEQ_BARCODE_CLOSE = 200;
        public static final int SEQ_BARCODE_GET_STATUS = 300;
        public static final int SEQ_BARCODE_SET_TRIGGER_ON = 400;
        public static final int SEQ_BARCODE_SET_TRIGGER_OFF = 500;

        public static final String ACTION_BARCODE_OPEN = "kr.co.bluebird.android.bbapi.action.BARCODE_OPEN";
        public static final String ACTION_BARCODE_CLOSE = "kr.co.bluebird.android.bbapi.action.BARCODE_CLOSE";
        public static final String ACTION_BARCODE_SET_TRIGGER = "kr.co.bluebird.android.bbapi.action.BARCODE_SET_TRIGGER";
        public static final String ACTION_BARCODE_SET_DEFAULT_PROFILE = "kr.co.bluebird.android.bbapi.action.BARCODE_SET_DEFAULT_PROFILE";
        public static final String ACTION_BARCODE_SETTING_CHANGED = "kr.co.bluebird.android.bbapi.action.BARCODE_SETTING_CHANGED";
        public static final String ACTION_BARCODE_CALLBACK_REQUEST_SUCCESS = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_REQUEST_SUCCESS";
        public static final String ACTION_BARCODE_CALLBACK_REQUEST_FAILED = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_REQUEST_FAILED";
        public static final String ACTION_BARCODE_CALLBACK_DECODING_DATA = "kr.co.bluebird.android.bbapi.action.BARCODE_CALLBACK_DECODING_DATA";
        public static final String ACTION_MDM_BARCODE_SET_SYMBOLOGY = "kr.co.bluebird.android.bbapi.action.MDM_BARCODE_SET_SYMBOLOGY";
        public static final String ACTION_MDM_BARCODE_SET_MODE = "kr.co.bluebird.android.bbapi.action.MDM_BARCODE_SET_MODE";
        public static final String ACTION_MDM_BARCODE_SET_DEFAULT = "kr.co.bluebird.android.bbapi.action.MDM_BARCODE_SET_DEFAULT";

        //default profile setting done.
        public static final String ACTION_BARCODE_CALLBACK_DEFAULT_PROFILE_SETTING_COMPLETE = "kr.co.bluebird.android.bbapi.action.BARCODE_DEFAULT_PROFILE_SETTING_COMPLETE";
        //request barcode status
        public static final String ACTION_BARCODE_GET_STATUS = "kr.co.bluebird.android.action.BARCODE_GET_STATUS";
        //repond barcode status
        public static final String ACTION_BARCODE_CALLBACK_GET_STATUS = "kr.co.bluebird.android.action.BARCODE_CALLBACK_GET_STATUS";

        //barcode status
        public static final int BARCODE_CLOSE = 0;
        public static final int BARCODE_OPEN = 1;
        public static final int BARCODE_TRIGGER_ON = 2;

        public static final String EXTRA_BARCODE_BOOT_COMPLETE = "EXTRA_BARCODE_BOOT_COMPLETE";
        public static final String EXTRA_BARCODE_PROFILE_NAME = "EXTRA_BARCODE_PROFILE_NAME";
        public static final String EXTRA_BARCODE_TRIGGER = "EXTRA_BARCODE_TRIGGER";
        public static final String EXTRA_BARCODE_DECODING_DATA = "EXTRA_BARCODE_DECODING_DATA";
        public static final String EXTRA_HANDLE = "EXTRA_HANDLE";
        public static final String EXTRA_INT_DATA2 = "EXTRA_INT_DATA2";
        public static final String EXTRA_STR_DATA1 = "EXTRA_STR_DATA1";
        public static final String EXTRA_INT_DATA3 = "EXTRA_INT_DATA3";

        public static final int ERROR_FAILED = -1;
        public static final int ERROR_NOT_SUPPORTED = -2;
        public static final int ERROR_NO_RESPONSE = -4;
        public static final int ERROR_BATTERY_LOW = -5;
        public static final int ERROR_BARCODE_DECODING_TIMEOUT = -6;
        public static final int ERROR_BARCODE_ERROR_USE_TIMEOUT = -7;
        public static final int ERROR_BARCODE_ERROR_ALREADY_OPENED = -8;

        public static final int MDM_MSR_MODE__SET_READING_TIMEOUT = 0;
    }

    class ScanBroadcastReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent) {
            try
            {
                int seq = intent.getIntExtra(Constants.EXTRA_INT_DATA3, 0);

                switch(intent.getAction()){
                    case Constants.ACTION_BARCODE_CALLBACK_DECODING_DATA:
                        int handle = intent.getIntExtra(Constants.EXTRA_HANDLE, 0);
                        byte[] data = intent.getByteArrayExtra(Constants.EXTRA_BARCODE_DECODING_DATA);
                        String code = "";
                        if(data != null) code = new String(data);
                        onDecoded(code);
                        break;

                    case Constants.ACTION_BARCODE_CALLBACK_REQUEST_SUCCESS:
                        barcodeHandle = intent.getIntExtra(Constants.EXTRA_HANDLE, 0);
//                        if(seq == Constants.SEQ_BARCODE_OPEN) isOpened = true; else
//                        if(seq == Constants.SEQ_BARCODE_CLOSE) isOpened = false;
                        break;

                    case Constants.ACTION_BARCODE_CALLBACK_REQUEST_FAILED:
                        int result = intent.getIntExtra(Constants.EXTRA_INT_DATA2, 0);

                        switch(result){
                            case Constants.ERROR_BARCODE_DECODING_TIMEOUT:
                                onError(new Exception("Decode Timeout" + " / seq : " + seq));
                                break;
                            case Constants.ERROR_NOT_SUPPORTED:
                                onError(new Exception("Not Supoorted" + " / seq : " + seq));
                                break;
                            case Constants.ERROR_BARCODE_ERROR_USE_TIMEOUT:
                                onError(new Exception("Use Timeout" + " / seq : " + seq));
                                break;

                            default:
                                new Exception("Unknown error "+ result + " / seq : " + seq);
                                break;
                        }
                        break;

                    case Constants.ACTION_BARCODE_CALLBACK_GET_STATUS:
                        int status = intent.getIntExtra(Constants.EXTRA_INT_DATA2, 0);

//                        switch(status)
//                        {
//                            case 0:
//                                mCurrentStatus = Constants.STATUS_CLOSE;
//                                break;
//                            case 1:
//                                mCurrentStatus = Constants.STATUS_OPEN;
//                                break;
//                            case 2:
//                                mCurrentStatus = Constants.STATUS_TRIGGER_ON;
//                                break;
//                        }
                        break;
                }
            }
            catch(Exception e)
            {
                onError(e);
            }
        }
    }

    private ScanBroadcastReceiver scanBroadcastReceiver;
    private IntentFilter intentFilter;
    private int barcodeHandle;
    private boolean isOpened;

    public EF400_500(Context context)
    {
        super(context);
        init();
        listeners();
    }

    private void init()
    {
        scanBroadcastReceiver = new ScanBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_BARCODE_CALLBACK_DECODING_DATA);
        intentFilter.addAction(Constants.ACTION_BARCODE_CALLBACK_REQUEST_SUCCESS);
        intentFilter.addAction(Constants.ACTION_BARCODE_CALLBACK_REQUEST_FAILED);
        intentFilter.addAction(Constants.ACTION_BARCODE_CALLBACK_GET_STATUS);
    }

    private void listeners()
    {
    }

    @Override
    public boolean resumeScanner()
    {
        startScanner();
        return true;
    }

    @Override
    public boolean pauseScanner()
    {
        stopScanner();
        return true;
    }


    @Override
    public boolean startScanner()
    {
        registerReceiver();
        try
        {
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_BARCODE_OPEN);
            intent.putExtra(Constants.EXTRA_HANDLE, barcodeHandle);
            intent.putExtra(Constants.EXTRA_INT_DATA3, Constants.SEQ_BARCODE_OPEN);
            context.sendBroadcast(intent);
            isOpened = true;
        }catch(Exception e)
        {
            e.getMessage();
        }
        return true;
    }

    @Override
    public boolean stopScanner()
    {
        unregisterReceiver();
        try
        {
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_BARCODE_CLOSE);
            intent.putExtra(Constants.EXTRA_HANDLE, barcodeHandle);
            intent.putExtra(Constants.EXTRA_INT_DATA3, Constants.SEQ_BARCODE_CLOSE);
            context.sendBroadcast(intent);
            isOpened = false;
        }
        catch(Exception e)
        {
            e.getMessage();
        }
        return true;
    }

    private void registerReceiver(){

        try
        {
            context.registerReceiver(scanBroadcastReceiver, intentFilter);
        }catch(Exception e)
        {
            e.getMessage();
        }
    }

    private void unregisterReceiver(){
        try
        {
            context.unregisterReceiver(scanBroadcastReceiver);
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }
}