package com.aditya.shareapp;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

public class MainActivity extends AppCompatActivity {

    private Button twitterShareBtn, shareOnFb;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!DeviceUtil.isEmulator()) {
            if (CommonUtils.isRooted()) {
                Toast.makeText(this, "The phone is rooted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "The phone IS NOT rooted", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "This Device is an Emulator", Toast.LENGTH_SHORT).show();
        }

        AppEventsLogger.activateApp(getApplication());
        twitterShareBtn = findViewById(R.id.twitterShareBtn);
        callbackManager = CallbackManager.Factory.create();
        shareOnFb = findViewById(R.id.shareOnFb);
        shareDialog = new ShareDialog(this);

        clickEvents();


    }

    private void clickEvents() {
        twitterShareBtn.setOnClickListener(v -> {
            try {
                TweetComposer.Builder builder = new TweetComposer.Builder(this)
                        .text("https://youtu.be/kcmZX8-A98A");//any sharing text here
                builder.show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "exception occurred in tweet composer");
            }
        });

        shareOnFb.setOnClickListener(v -> {
            ShareDialog shareDialog = new ShareDialog(this);

            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://youtu.be/kcmZX8-A98A"))
                        .setShareHashtag(new ShareHashtag.Builder().setHashtag("#video").build())
                        .setQuote("Quote")
                        .build();

                shareDialog.show(linkContent);
            }
        });
    }
}