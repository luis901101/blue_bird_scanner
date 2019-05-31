package com.plugin.flutter.bluebirdscanner;

import android.content.Context;

/**
 * Created by luis901101 on 05/31/19.
 */

public abstract class BlueBirdScanner implements ScannerCallBack
{
    protected final Context context;
    private ScannerCallBack scannerCallBack;

    public BlueBirdScanner(Context context)
    {
        this.context = context;
    }

    public void setScannerCallBack(ScannerCallBack scannerCallBack)
    {
        this.scannerCallBack = scannerCallBack;
    }

    /**
     * Called when decoder has successfully decoded the code
     * <br>
     * Note that this method always called on a worker thread
     *
     * @param code Encapsulates the result of decoding a barcode within an image
     */
    @Override
    public void onDecoded(String code)
    {
        if(scannerCallBack != null) scannerCallBack.onDecoded(code);
    }

    /**
     * Called when error has occurred
     * <br>
     * Note that this method always called on a worker thread
     *
     * @param error Exception that has been thrown
     */
    @Override
    public void onError(Exception error)
    {
        if(scannerCallBack != null) scannerCallBack.onError(error);
    }

    public abstract boolean startScanner();
    public abstract boolean resumeScanner();
    public abstract boolean pauseScanner();
    public abstract boolean stopScanner();
}