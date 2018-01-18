package com.ljq.utils;

import org.junit.Test;

public class QRCodeUtilTest {


    @Test
    public void decodeQRCode() throws Exception {
        String qrCodePath = "src\\resources\\qrCodeDemo-1.png";

        String result = QRCodeUtil.decodeQRCode(qrCodePath);
        System.out.println("QRCode content: " + result);
    }

    @Test
    public void createQRCode() throws Exception {

        String content = "Hello world !!! \n\n ----by 魔术师";
        StringBuilder content2 = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            content2.append("藏");
        }
        String qrCodePath = "src\\resources\\qrCodeDemo-1";
        int width = 300;
        int height = 300;
        // limit content length
        System.out.println("content length: " + (content2.toString().length()));
        boolean flag = QRCodeUtil.createQRCode(content2.toString(),qrCodePath,null,width,height);
        System.out.println("generate QRCode: " + flag);

    }



}