package me.jaxbot.viscendroid;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class WearDataService extends WearableListenerService {
    public WearDataService() {
    }


    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        System.out.println("Yikes!");
        for (DataEvent event : dataEvents) {
            if (event.getType() == DataEvent.TYPE_CHANGED &&
                    event.getDataItem().getUri().getPath().equals("/image")) {
                DataMapItem dataMapItem = DataMapItem.fromDataItem(event.getDataItem());
                Asset profileAsset = dataMapItem.getDataMap().getAsset("webcamImage");
                System.out.println("Got image");
                //Bitmap bitmap = loadBitmapFromAsset(profileAsset);
                // Do something with the bitmap
            }
        }
    }
}
