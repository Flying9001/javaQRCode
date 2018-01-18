package com.ljq.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
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
     * generate QRCode image,String content length <= 500
     * @param content the content of the QRCode
     * @param qrCodePath the path of generated QRCode image without filename' extension
     * @param format the extention of the generated QRCode image file
     * @param width the width of the generated QRCode image
     * @param height the height of the generated QRCode image
     *
     * @return boolean whether generating the QRCode image successfully
     * */
    public static boolean createQRCode(String content, String qrCodePath, Format format,int width, int height ){

        // limit content length
        if(content == null || content.equals("") || content.length() > 500){
            return false;
        }
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
