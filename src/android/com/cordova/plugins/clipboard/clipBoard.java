package com.cordova.plugins.clipboard;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.ClipData;
import android.content.ClipboardManager;

import android.os.Build;
import android.util.Log;


public class clipBoard extends CordovaPlugin {

    private final String TAG = "ClipBoard";
    private String mPreviousText = "";
    private ClipboardManager cliboardManager = null;

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        try {
            cliboardManager = (ClipboardManager) cordova.getActivity().getSystemService(android.content.Context.CLIPBOARD_SERVICE);
            cliboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {

                @Override
                public void onPrimaryClipChanged() {

                    try {
                        ClipData clipData = cliboardManager.getPrimaryClip();
                        ClipData.Item item = clipData.getItemAt(0);
                        JSONObject json = null;
                        json = new JSONObject("{item:\"" + item.getText() + "\"}");
                        if (json != null) {
                            PluginResult res = new PluginResult(PluginResult.Status.OK, json);
                            res.setKeepCallback(true);
                            callbackContext.sendPluginResult(res);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Exception: " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }

                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
        }

        return true;

    }
}
