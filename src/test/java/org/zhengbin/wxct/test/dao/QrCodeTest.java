package org.zhengbin.wxct.test.dao;


import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 1、QrCode 生成二维码图片
 * 2、生成带有 logo 的二维码图片
 * Created by zhengbinMac on 2017/4/27.
 */
public class QrCodeTest {
    /**
     * 生成二维码 Buffered
     * @param content 二维码内容
     * @return
     */
    public static BufferedImage QrcodeImage(String content) {
        // 二维码宽度
        int width = 140;
        // 二维码高度
        int height = 140;

        try {
            Qrcode qrcode = new Qrcode();

            // 设置二维码的排错率 'L'：7%，'M'：15%，'Q'：25%，'H'：30%
            qrcode.setQrcodeErrorCorrect('M');
            // B 表示二进制的方式
            qrcode.setQrcodeEncodeMode('B');

            // 设置二维码的尺寸，尺寸越大，可存储的信息量越大
            qrcode.setQrcodeVersion(7);
            // 设置图片的尺寸、格式
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 绘制二维码的图片
            Graphics2D graphics2D = bufferedImage.createGraphics();
            // 设置背景颜色
            graphics2D.setBackground(Color.WHITE);
            // 创建二维码的矩形区域
            graphics2D.clearRect(0, 0, width, height);

            // 设置二维码图片的颜色值
            graphics2D.setColor(Color.BLACK);
            // 二维码生成点阵的偏移量
            int pixoff = 2;
            // 获取二维码内容的字节数组，并设置编码
            byte[] contentBytes = content.getBytes("GBK");
            // 输出二维码
            if (contentBytes.length > 0 && contentBytes.length < 200) { // 如果二维码内容在规定长度内
                boolean[][] codeOut = qrcode.calQrcode(contentBytes);
                for (int i = 0;i < codeOut.length;i++) {
                    for (int j = 0;j < codeOut.length;j++) {
                        if (codeOut[j][i]) {
                            graphics2D.fillRect(j*3+pixoff, i*3+pixoff, 3, 3);
                        }
                    }
                }
            }
            graphics2D.dispose();
            bufferedImage.flush();
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在已有的二维码图片加上logo图片
     * @param twodimensioncodeImg 二维码图片文件
     * @param logoImg             logo图片文件
     * @return
     */
    public static BufferedImage encodeImgLogo(File twodimensioncodeImg,File logoImg){
        BufferedImage twodimensioncode = null;
        try{
            if(!twodimensioncodeImg.isFile() || !logoImg.isFile()){
                System.out.println("输入非图片");
                return null;
            }
            //读取二维码图片
            twodimensioncode = ImageIO.read(twodimensioncodeImg);
            //获取画笔
            Graphics2D g = twodimensioncode.createGraphics();
            //读取logo图片
            BufferedImage logo = ImageIO.read(logoImg);
            //设置二维码大小，太大，会覆盖二维码，此处20%
            int logoWidth = logo.getWidth(null) > twodimensioncode.getWidth()*3 /10 ? (twodimensioncode.getWidth()*3 /10) : logo.getWidth(null);
            int logoHeight = logo.getHeight(null) > twodimensioncode.getHeight()*3 /10 ? (twodimensioncode.getHeight()*3 /10) : logo.getHeight(null);
            // 确定二维码的中心位置坐标，设置logo图片放置的位置
            int x = (twodimensioncode.getWidth() - logoWidth) / 2;
            int y = (twodimensioncode.getHeight() - logoHeight) / 2;
            //开始合并绘制图片
            g.drawImage(logo, x, y, logoWidth, logoHeight, null);
            g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);
            //logo边框大小
            g.setStroke(new BasicStroke(2));
            //logo边框颜色
            g.setColor(Color.WHITE);
            g.drawRect(x, y, logoWidth, logoHeight);
            g.dispose();
            logo.flush();
            twodimensioncode.flush();
        }catch(Exception e){
            System.out.println("二维码绘制logo失败");
        }
        return twodimensioncode;
    }

    /**
     * 生成图片文件
     * @param bufferedImage 图片 buffered
     * @param imgPath       图片路径
     */
    public static void writeImage(BufferedImage bufferedImage, String imgPath) {
        // 生成二维码的图片
        File file = new File(imgPath);
        try {
            ImageIO.write(bufferedImage, "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析二维码
     */
    public static String decodeImg(String imgPath) {
        File imgFile = new File(imgPath);
        if (imgFile == null) {
            return null;
        }
        BufferedImage bufferedImage;
        String content = null;
        try {
            bufferedImage = ImageIO.read(imgFile);
            QRCodeDecoder decoder = new QRCodeDecoder();
            // decode 方法入参为 QRCodeImage，其是接口声明，需要实现该接口
            content = new String(decoder.decode(new CodeImg(bufferedImage)), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /**
     * 实现 QRCodeImage 接口
     */
    static class CodeImg implements QRCodeImage{
        private BufferedImage image;
        public CodeImg(BufferedImage image) {
            super();
            this.image = image;
        }

        @Override
        public int getWidth() {
            return image.getWidth();
        }
        @Override
        public int getHeight() {
            return image.getHeight();
        }
        @Override
        public int getPixel(int x, int y) {
            return image.getRGB(x, y);
        }
        public BufferedImage getImage() {
            return image;
        }
        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }
    public static void main(String[] args) {
        // 生成二维码
        BufferedImage qrCode = QrcodeImage("www.guoxiangxiang.com/郭香香");
        writeImage(qrCode, "/Users/zhengbinMac/Documents/qrcode/qecode3.png");
        String qrCodePath = "/Users/zhengbinMac/Documents/qrcode/qecode3.png";
        System.out.println(decodeImg(qrCodePath));
        // 生成带有图片logo的二维码
//        File qrcode = new File("/Users/zhengbinMac/Documents/qrcode/qecode3.png");
//        File logo = new File("/Users/zhengbinMac/Documents/qrcode/WechatIMG10.jpeg");
//        BufferedImage logoQrcode = encodeImgLogo(qrcode, logo);
//        writeImage(logoQrcode, "/Users/zhengbinMac/Documents/qrcode/logQrcode2.png");
    }
}
