package me.jaxbot.viscendroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

import java.io.ByteArrayOutputStream;

/**
 * Created by jonathan on 2/12/15.
 */
public class WearAsset {
   static String TAG = "WearAsset";

   static GoogleApiClient mGoogleApiClient;

   public static void init(Context context) {
        mGoogleApiClient = new GoogleApiClient.Builder(context)
               .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                   @Override
                   public void onConnected(Bundle connectionHint) {
                       Log.d(TAG, "onConnected: " + connectionHint);
                       // Now you can use the Data Layer API
                   }
                   @Override
                   public void onConnectionSuspended(int cause) {
                       Log.d(TAG, "onConnectionSuspended: " + cause);
                   }
               })
               .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                   @Override
                   public void onConnectionFailed(ConnectionResult result) {
                       Log.d(TAG, "onConnectionFailed: " + result);
                   }
               })
               // Request access only to the Wearable API
               .addApi(Wearable.API)
               .build();
   }

    private static Asset createAssetFromBitmap(Bitmap bitmap) {
        final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
        return Asset.createFromBytes(byteStream.toByteArray());
    }

    public static void sendAsset(Bitmap bitmap) {
        Asset asset = createAssetFromBitmap(bitmap);
        PutDataRequest request = PutDataRequest.create("/image");
        request.putAsset("profileImage", asset);
        Wearable.DataApi.putDataItem(mGoogleApiClient, request);
        System.out.println("Did more stuff");
    }
}
