package com.phonegap.plugins.cameraplugin;


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;



import android.hardware.Camera;






public class Cameraplugin extends CordovaPlugin {
    Camera cameraObject = null;

    @Override

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals("openCamera")) {
            String message = args.getString(0);
            this.openCamera(message, callbackContext);
            return true;
        }
        return false;
    }

    private void openCamera(String message, CallbackContext callbackContext) {
        try {
            cameraObject = Camera.open();
        }

        catch (RuntimeException e) {
            System.err.println(e);
            return;
        }
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
