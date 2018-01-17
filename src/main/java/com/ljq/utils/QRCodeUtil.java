package com.ljq.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.Format;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desccription: 二维码读写工具类
 * @author: Thaumaturge
 * @email: lujunqiang@ancun.com
 * @date: 2018/1/17
 */
public class QRCodeUtil {


    /**
     * generate QRCode image
     * @param content the content of the QRCode
     * @param qrCodePath the path of generated QRCode image without filename' extension
     * @param format the extention of the generated QRCode image file
     * @param width the width of the generated QRCode image
     * @param height the height of the generated QRCode image
     *
     * @return boolean whether generating the QRCode image successfully
     * */
    public static boolean createQRCode(String content, String qrCodePath, Format format,int width, int height ){

        // QRcode image format
        String qrCodeFormat = "png";
        Map<EncodeHintType,String> hintType  = new HashMap<>();
        hintType.put(EncodeHintType.CHARACTER_SET,"utf-8");
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hintType);
            BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
            File qrCodeFile = new File(qrCodePath + "." + qrCodeFormat);
            Path path = qrCodeFile.toPath();
            MatrixToImageWriter.writeToPath(bitMatrix,qrCodeFormat,path);

            return true;
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }




}
