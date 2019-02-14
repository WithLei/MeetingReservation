package com.android.renly.meetingreservation.utils;

import com.android.renly.meetingreservation.R;

public class FileUtils {
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
}
