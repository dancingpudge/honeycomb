package com.honeycomb.storage.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: com.honeycomb.storage.util <br>
 * @date: 2021/2/22 3:50 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
public class FileUtils {


    public static Map<String, String> parsePath(String path) {
        File f = new File(path);
        Map<String, String> pathMap = new HashMap<String, String>();
        pathMap.put("fileDir", f.getParent());
        pathMap.put("fileName", f.getName());
        if (!path.contains("."))
            path = path + ".jpg";
        pathMap.put("fileType", getContentType(path.substring(path.lastIndexOf("."))));
        return pathMap;
    }

    /**
     * 根据文件后缀获取文件类型
     *
     * @param FileExt
     * @return
     */
    public static String getContentType(String FileExt) {
        if (FileExt.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (FileExt.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (FileExt.equalsIgnoreCase(".jpeg") || FileExt.equalsIgnoreCase(".jpg")
                || FileExt.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (FileExt.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (FileExt.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (FileExt.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (FileExt.equalsIgnoreCase(".pptx") || FileExt.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FileExt.equalsIgnoreCase(".docx") || FileExt.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (FileExt.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
}
