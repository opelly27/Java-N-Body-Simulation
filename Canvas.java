// A wrapper class for the buffered image object that adds functionality to 
// draw shapes.
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;


public class Canvas {
    int height;
    int width;
    BufferedImage image;
    private static JFrame frame;
    private static JLabel label;

    public static void main(String[] args){
        Canvas test = new Canvas(500, 500, 255, 255, 255, 255);
        int radius = 100;
        int x = 250;
        int y = 250;
        int a = 255;

        for(int i = 0; i < 50000; i++){
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            test.drawSquare(radius, x, y, r, g, b, a);
            test.display();
        }
    }

    public Canvas(int height, int width, int r, int g, int b, int a){
        this.height = height;
        this.width = width;
        this.image = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
    }

    public void display(){
        if(frame==null){
            frame=new JFrame();
            frame.setTitle("stained_image");
            frame.setSize(image.getWidth(), image.getHeight());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            label=new JLabel();
            label.setIcon(new ImageIcon(image));
            frame.getContentPane().add(label,BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setVisible(true);
        }else label.setIcon(new ImageIcon(this.image));
    }

    public void setPixel(int x, int y, int r, int g, int b, int a){
        int p = (a<<24) | (r<<16) | (g<<8) | b;
        this.image.setRGB(x, y, p);
    }

    public void drawSquare(int radius, int x, int y, int r, int g, int b, int a){
        for(int sign = 0; sign < 4; sign ++){
            for(int offsetY = 0; offsetY < radius; offsetY ++){
                for(int offsetX = 0; offsetX < radius; offsetX ++ ){
                    int xPoint = offsetX;
                    int yPoint = offsetY;
                    switch(sign){
                        case 0: xPoint = xPoint * - 1;
                                break;
                        case 1: yPoint = yPoint * -1;
                                break;
                        case 2: xPoint = xPoint * -1;
                                yPoint = yPoint * -1;
                                break;
                    }
                    this.setPixel(x + xPoint, y + yPoint, r, g, b, a);
                }
            }
        }
    }
}
