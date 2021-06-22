package Morfologico;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Transformer {

    public static BufferedImage traslate(int xi, int yi, BufferedImage canbas, BufferedImage image) {
        for(int x=0, x0=xi; x< image.getWidth(); x++, x0++) {
            for(int y=0, y0=yi; y<image.getHeight(); y++, y0++) {
                int color = image.getRGB(x, y);

                try {
                    canbas.setRGB(x0, y0, color);
                } catch(Exception e) {

                }
            }
        }

        return canbas;
    }

    public static BufferedImage rotate(int xi, int yi, BufferedImage canbas, BufferedImage image, int degrees) {
        int width = image.getWidth();
        int height = image.getHeight();

        double radians = Math.toRadians( -1 * degrees);

        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                int color = image.getRGB(x, y);

                int newX = (int) (x * Math.cos(radians) - y * Math.sin(radians)) + xi;
                int newY = (int) (x * Math.sin(radians) + y * Math.cos(radians)) + yi;

                //buffer_rotated.setRGB(newX, newY, color);
                try {
                    canbas.setRGB(newX, newY, color);
                } catch(Exception e) {

                }
            }
        }

        return canbas;
    }

    public static BufferedImage scale(int xi, int yi, BufferedImage canbas, BufferedImage image, double zoomX, double zoomY) {
        int newWidth = (int)(image.getWidth() * zoomX)+1;
        int newHeiht = (int)(image.getHeight() * zoomY)+1;

        BufferedImage buffer_scaled = new BufferedImage(newWidth, newHeiht, BufferedImage.TYPE_INT_ARGB);

        if(zoomX > 1 && zoomY > 1) {
            for(int x=0; x<image.getWidth(); x++) {
                for(int y=0; y<image.getHeight(); y++) {
                    for(int x1=0; x1<zoomX; x1++) {
                        for(int y1=0; y1<zoomY; y1++) {
                            int color = image.getRGB(x, y);

                            buffer_scaled.setRGB((int)(x * zoomX) + x1, (int)(y * zoomY) + y1, color);
                        }
                    }
                }
            }
        } else if(zoomX < 1 && zoomY < 1) {
            int intervalX = (int)Math.rint((double)image.getWidth() / (double)(image.getWidth() - buffer_scaled.getWidth()));
            int intervalY = (int)Math.rint((double)image.getHeight() / (double)(image.getHeight() - buffer_scaled.getHeight()));

            for(int x=0; x<buffer_scaled.getWidth(); x++) {
                for(int y=0; y<buffer_scaled.getHeight(); y++) {
                    int color = image.getRGB(x * intervalX, y*intervalY);

                    buffer_scaled.setRGB(x, y, color);
                }
            }
        }


        for(int x=0, x0=xi; x<buffer_scaled.getWidth(); x++, x0++) {
            for(int y=0, y0=yi; y<buffer_scaled.getHeight(); y++, y0++) {
                try {
                    int color = buffer_scaled.getRGB(x, y);

                    canbas.setRGB(x0, y0, color);
                } catch (Exception e) {
                    continue;
                }
            }
        }

        return canbas;
    }
}
