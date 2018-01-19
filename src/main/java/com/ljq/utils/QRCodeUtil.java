package com.ljq.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Desccription: 二维码读写工具类
 * @author: Thaumaturge
 * @email: lujunqiang@ancun.com
 * @date: 2018/1/17
 */
public class QRCodeUtil {

    // default size of QRCode
    private static final int WIDTH_DEFUALT = 300;
    private static final int HEIGHT_DEFUALT = 300;


    /**
     * generate QRCode image,String content length <= 500
     * @param content the content of the QRCode
     * @param qrCodePath the path of generated QRCode image without filename' extension
     * @param width the width of the generated QRCode image
     * @param height the height of the generated QRCode image
     *
     * @return boolean whether generating the QRCode image successfully
     * */
    public static boolean createQRCode(String content, String qrCodePath, Map<EncodeHintType,Object> hintTypeMap,
                                       int width, int height ){

        // check the data
        if(content == null || content.equals("") || content.length() > 500){
            return false;
        }
        if(width <= 0 || height <= 0){
            width = WIDTH_DEFUALT;
            height = HEIGHT_DEFUALT;
        }
        BufferedImage image = null;

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hintTypeMap);


        } catch (WriterException e) {
            e.printStackTrace();
        }


        // QRcode image format
//        String qrCodeFormat = "png";
//        Map<EncodeHintType,String> hintType  = new HashMap<>();
//        hintType.put(EncodeHintType.CHARACTER_SET,"utf-8");
//        try {
//            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,width,height,hintType);
//            BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//            File qrCodeFile = new File(qrCodePath + "." + qrCodeFormat);
//            Path path = qrCodeFile.toPath();
//            MatrixToImageWriter.writeToPath(bitMatrix,qrCodeFormat,path);
//
//            return true;
//        } catch (WriterException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    /**
     * generate QRCode image, and add logo picture to the QRCode,String content length <= 500 ,and String title <= 20
     * @param content the content of the QRCode
     * @param title title of the QRCode
     * @param qrCodePath the path of generated QRCode image without filename' extension
     * @param logoPath the logo picture,which is on the center of the QRCode image
     * @param width the width of the generated QRCode image
     * @param height the height of the generated QRCode image
     *
     * @return boolean
     * */
    public static boolean createQRCode(String content, String title, String qrCodePath, String logoPath, int width,
           int height){
        // check the data
        if(content == null || content.equals("") || content.length()>500
                || title.length() > 20){
            return false;
        }
        if(width <= 0 || height <= 0){
            width = WIDTH_DEFUALT;
            height = HEIGHT_DEFUALT;
        }









        return false;

    }

    /**
     * decode QRCode from an image file
     * @param qrCodePath QRCode image file path
     *
     * @return string content of QRCode
     * */
    public static String decodeQRCode(String qrCodePath){

        if(FileUtil.checkFilePath(qrCodePath).equals("file")){
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(qrCodePath));
                if(img != null && img.getWidth() > 0 && img.getHeight() > 0){
                    LuminanceSource souce = new BufferedImageLuminanceSource(img);
                    Binarizer binarizer = new HybridBinarizer(souce);
                    BinaryBitmap bitmap = new BinaryBitmap(binarizer);
                    Map<DecodeHintType,Object> hintTypeMap  = new HashMap<>();
                    hintTypeMap.put(DecodeHintType.CHARACTER_SET,"utf-8");
                    Result result = new MultiFormatReader().decode(bitmap,hintTypeMap);
                    return result.getText();
                }
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (NotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                img = null;
            }
        }
        return null;
    }




}
