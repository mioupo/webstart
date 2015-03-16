package com.xa3ti.webstart.base.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
 

public class QRCode {
    private static final int BLACK = 0xff000000;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int SMALL=200;
    public static final int MIDDLE=400;
    public static final int LARGE=600;
    public static final int XLARGE=800;
 
    /**
     * 生成二维码
     * @param path 二维码路径
     * @param text 二维码内容
     * @return
     */
    public static boolean createQRCode(String fileFullName,String text,int size)
    {
    	boolean isok=false;
        try {
			File file = new File(fileFullName);
			QRCode test = new QRCode();
			test.encode(text, file, BarcodeFormat.QR_CODE, size, size, null);
			isok=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return isok; 
    }
    
    /**
     * 生成二维码
     * @param path 二维码路径
     * @param text 二维码内容
     * @return
     */
    public static boolean createQRCode(HttpServletRequest request,String fileName, String text,int size)
    {
    	boolean isok=false;
        try {
        	///创建前缀带时间的文件名
        	//String dateFileName=new Date().getTime()+"__"+fileName;
        	//寻找存放的目录
        	String dicPath=FolderPath.CreateFilePath(request,FolderPath.Qrcode);
        	File file=new File(dicPath,fileName); 
			QRCode test = new QRCode();
			test.encode(text, file, BarcodeFormat.QR_CODE, size, size, null);
			isok=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return isok; 
    }

    /**
     * 生成QRCode二维码<br> 
     * 在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
     *  static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
     *  修改为UTF-8，否则中文编译后解析不了<br>
     */
    public void encode(String contents, File file, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height);
            writeToFile(bitMatrix, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码图片<br>
     * 
     * @param matrix
     * @param format 图片格式
     * @param file 生成二维码图片位置
     * @throws IOException
     */
    public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        ImageIO.write(image, format, file);
    }

    /**
     * 生成二维码内容<br>
     * 
     * @param matrix
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        //创建一张bitmap图片，采用图片效果TYPE_INT_ARGB  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
            }
        }
        return image;
    }

     public static void main(String[] args) {
    	 QRCode.createQRCode("e:/aa.png", "http://www.baidu.com", SMALL);
	}
}
