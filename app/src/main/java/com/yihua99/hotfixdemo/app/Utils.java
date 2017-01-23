package com.yihua99.hotfixdemo.app;

import android.content.res.AssetManager;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by linoli on 2017/1/19.
 */

public class Utils {

    public static final String CHANNEL_FILENAME = "channel.ini";

    /**
     * 获得渠道号
     *
     * @return 渠道号
     */
    public static String getLC() {
        String strChannel = "";
        try {
            AssetManager am = MyApplicationLike.getContext().getAssets();
            InputStream is = am.open(CHANNEL_FILENAME);
            byte[] buffer = readFull(is);
            is.close();

            String fileContent = new String(buffer);
            strChannel = fileContent.trim().substring(fileContent.indexOf("=") + 1).trim();
        } catch (Exception e) {
        }
        return strChannel;
    }

    public static byte[] readFull(InputStream is) throws IOException {
        byte[] buffer = new byte[10240];
        int readLen = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((readLen = is.read(buffer)) != -1) {
                bos.write(buffer, 0, readLen);
            }
        } catch (EOFException e) {
            // 发生文件结尾异常 正常返回
            return bos.toByteArray();
        } finally {
            bos.close();
        }

        return bos.toByteArray();
    }

}
