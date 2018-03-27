package com.bsb.utils.ImageUtil;

import java.io.File;

public class ImagesUtil {

    private static final String defaultImagePath = "/image";
    private static final String imageSavePath = "C:\\Users\\66490\\Desktop\\user_image\\";

    public static void test() {
        File file = new File(defaultImagePath + "zsz.jpg");
//        file.length();
        System.out.println(file.getAbsolutePath());
    }


}
