package com.phonegap.plugins.cameraplugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;


import android.content.Intent;
import android.graphics.Bitmap;
import 	android.net.Uri;
import 	java.io.File;
import 	android.content.Context;
import 	java.io.ByteArrayOutputStream;
import 	android.provider.MediaStore.Images;
import 	android.database.Cursor;
import 	android.provider.MediaStore;
import 	org.apache.cordova.api.PluginResult;






public class Cameraplugin extends CordovaPlugin {
    private static final int CAMERA_REQUEST = 1888;

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
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        cordova.startActivityForResult(this,cameraIntent, CAMERA_REQUEST);

        PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
        r.setKeepCallback(true);
        callbackContext.sendPluginResult(r);

        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CallbackContext callbackContext;
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(photo);
            Uri tempUri = getImageUri(this.cordova.getActivity().getApplicationContext(), photo);

            File finalFile = new File(getRealPathFromURI(tempUri));
            String thePath = finalFile.getAbsolutePath();
            String theName = finalFile.getName();
           
            //  Toast.makeText(FirstApp.this,"theName", Toast.LENGTH_LONG).show();
            //Toast.makeText(Testcamera.this,"file:/"+thePath, Toast.LENGTH_LONG).show();
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor =  this.cordova.getActivity().getContentResolver().query(uri, null, null, null, null);

        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }



}
