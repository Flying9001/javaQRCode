package com.ljq.utils;

import org.junit.Test;

public class QRCodeUtilTest {


    @Test
    public void createQRCode() throws Exception {

        String content = "Hello World !!!\n\n----by 魔术师";
        String qrCodePath = "src\\resources\\qrCodeDemo-1";
        int width = 300;
        int height = 300;
 

        QRCodeUtil.createQRCode(content,qrCodePath,null,width,height);
    }

}