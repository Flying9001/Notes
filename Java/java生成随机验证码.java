package cn.mike.javase.vefification_code;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
 
import javax.imageio.ImageIO;
 
import org.junit.Test;
 
/**
 * @author : Administrator
 * @function : 这是用来测试随机生成验证码图片的类；
 */
public class VerificationCode {
 
    /**
     * 单元测试，试一下能不能自动生成验证码图片
     */
    @Test
    public void test_fun() {
        VerificationCode vc = new VerificationCode();
        BufferedImage image = vc.getImage();
        try {
            // 生成验证码图片，并保存到指定的路径
            VerificationCode.output(image, new FileOutputStream(new File(
                    ".\\image\\vcode_2.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
 
        // 将随机生成的文本内容输出到控制台，用于校验
        System.out.println(vc.getText());
    }
 
    private int w = 70;// 宽
    private int h = 35;// 高
    private String text;// 文本内容(验证码字符串)
    private Random r = new Random();
    private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
    // 随机字符集合中不包括0和o，O，1和l，因为这些不易区分
    private String codes = "23456789abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYXZ";
    // 验证码图片的背景色：白色
    private Color bgColor = new Color(255, 255, 255);
 
    /**
     * 返回一个验证码图片buffer对象：BufferedImage
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        // 获取绘图环境（画笔工具）
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        // sb ： 用来保存验证码字符串文本内容
        StringBuilder sb = new StringBuilder();
 
        for (int i = 0; i < 4; ++i) {// 随机生成4个字符
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * w / 4;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x, h - 5);
        }
 
        this.text = sb.toString();// 记录验证码文本内容
        drawLine(image);// 画干扰线
        return image;
 
    }
 
    /**
     * @return 获取验证码文本内容
     */
    public String getText() {
        return text;
    }
 
    /**
     * @param image
     * @param out
     *            将文本写到指定的输出流。比如本测试中FileOutputStream指定的保存路径
     */
    public static void output(BufferedImage image, OutputStream out) {
        try {
            ImageIO.write(image, "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private void drawLine(BufferedImage image) {
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < 3; ++i) {// 画3条干扰线
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2, y2);
        }
    }
 
    private Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }
 
    private Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 24;
        return new Font(fontName, style, size);
    }
 
    private char randomChar() {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }
 
    private BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, w, h);
 
        return image;
    }
 
}