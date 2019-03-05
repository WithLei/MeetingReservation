package com.android.renly.meetingreservation.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentUtils {
    public static void openBroswer(Context activity, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        activity.startActivity(intent);
    }

    public static void shareApp(Context context, String data) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, data);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "分享Plus客户端到:"));
    }

    public static void sharePost(Context context, String data) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, data);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, "分享帖子到:"));
    }
}
