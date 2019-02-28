package com.android.renly.meetingreservation.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.android.renly.meetingreservation.R;

import java.io.File;

public class FileUtils {
    private static final String[][] MIME_MapTable={
            //{后缀名，    MIME类型}
            {".3gp",    "video/3gpp"},
            {".apk",    "application/vnd.android.package-archive"},
            {".asf",    "video/x-ms-asf"},
            {".avi",    "video/x-msvideo"},
            {".bin",    "application/octet-stream"},
            {".bmp",      "image/bmp"},
            {".c",        "text/plain"},
            {".class",    "application/octet-stream"},
            {".conf",    "text/plain"},
            {".cpp",    "text/plain"},
            {".doc",    "application/msword"},
            {".exe",    "application/octet-stream"},
            {".gif",    "image/gif"},
            {".gtar",    "application/x-gtar"},
            {".gz",        "application/x-gzip"},
            {".h",        "text/plain"},
            {".htm",    "text/html"},
            {".html",    "text/html"},
            {".jar",    "application/java-archive"},
            {".java",    "text/plain"},
            {".jpeg",    "image/jpeg"},
            {".jpg",    "image/jpeg"},
            {".js",        "application/x-javascript"},
            {".log",    "text/plain"},
            {".m3u",    "audio/x-mpegurl"},
            {".m4a",    "audio/mp4a-latm"},
            {".m4b",    "audio/mp4a-latm"},
            {".m4p",    "audio/mp4a-latm"},
            {".m4u",    "video/vnd.mpegurl"},
            {".m4v",    "video/x-m4v"},
            {".mov",    "video/quicktime"},
            {".mp2",    "audio/x-mpeg"},
            {".mp3",    "audio/x-mpeg"},
            {".mp4",    "video/mp4"},
            {".mpc",    "application/vnd.mpohun.certificate"},
            {".mpe",    "video/mpeg"},
            {".mpeg",    "video/mpeg"},
            {".mpg",    "video/mpeg"},
            {".mpg4",    "video/mp4"},
            {".mpga",    "audio/mpeg"},
            {".msg",    "application/vnd.ms-outlook"},
            {".ogg",    "audio/ogg"},
            {".pdf",    "application/pdf"},
            {".png",    "image/png"},
            {".pps",    "application/vnd.ms-powerpoint"},
            {".ppt",    "application/vnd.ms-powerpoint"},
            {".prop",    "text/plain"},
            {".rar",    "application/x-rar-compressed"},
            {".rc",        "text/plain"},
            {".rmvb",    "audio/x-pn-realaudio"},
            {".rtf",    "application/rtf"},
            {".sh",        "text/plain"},
            {".tar",    "application/x-tar"},
            {".tgz",    "application/x-compressed"},
            {".txt",    "text/plain"},
            {".wav",    "audio/x-wav"},
            {".wma",    "audio/x-ms-wma"},
            {".wmv",    "audio/x-ms-wmv"},
            {".wps",    "application/vnd.ms-works"},
            //{".xml",    "text/xml"},
            {".xml",    "text/plain"},
            {".z",        "application/x-compress"},
            {".zip",    "application/zip"},
            {"",        "*/*"}
    };

    public static int getFileImgId(String file) {
        if (file.endsWith(".png"))
            return R.drawable.ic_png;
        if (file.endsWith(".psd"))
            return R.drawable.ic_psd;
        if (file.endsWith(".txt"))
            return R.drawable.ic_txt;
        if (file.endsWith(".ppt"))
            return R.drawable.ic_ppt;
        if (file.endsWith(".upload"))
            return R.drawable.ic_upload;
        if (file.endsWith(".video"))
            return R.drawable.ic_video;
        if (file.endsWith(".zip"))
            return R.drawable.ic_zip;
        if (file.endsWith(".html"))
            return R.drawable.ic_html;
        if (file.endsWith(".jpg"))
            return R.drawable.ic_jpg;
        if (file.endsWith(".mp3"))
            return R.drawable.ic_mp3;
        if (file.endsWith(".pdf"))
            return R.drawable.ic_pdf;
        if (file.endsWith(".excel"))
            return R.drawable.ic_excel;
        if (file.endsWith(".download"))
            return R.drawable.ic_download;
        if (file.endsWith(".ai"))
            return R.drawable.ic_ai;
        if (file.endsWith(".gif"))
            return R.drawable.ic_gif;
        return R.drawable.ic_white;
    }

    /**
     * 获取文件类型
     * @param file
     * @return
     */
    private static String getMIMEType(File file) {

        String type="*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if(dotIndex < 0)
            return type;
        /* 获取文件的后缀名 */
        String fileType = fName.substring(dotIndex,fName.length()).toLowerCase();
        if(fileType == null || "".equals(fileType))
            return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for(int i=0;i<MIME_MapTable.length;i++){
            if(fileType.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }

    public static void openFile(Context context, String fileDirectory) {
        Intent intent = new Intent();
        File file = new File(fileDirectory);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //设置标记
        intent.setAction(Intent.ACTION_VIEW); //动作，查看
        intent.setDataAndType(Uri.fromFile(file),getMIMEType(file)); //设置类型
        context.startActivity(intent);
    }
}
