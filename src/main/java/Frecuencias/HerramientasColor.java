package Frecuencias;

import java.awt.*;

public class HerramientasColor {
    public enum CanalColor {ROJO(2), VERDE(1), AZUL(0);
        private int index;

        private CanalColor(int index){
            this.index = index;
        }

        public int getColorIndex() {
            return index;
        }
    }

    public static int obtenerValorPorCanal(int rgb, CanalColor canal) {
        Color color = new Color(rgb);
        int val = 0;

        switch(canal) {
            case ROJO:
                val = color.getRed();
                break;
            case VERDE:
                val = color.getGreen();
                break;
            case AZUL:
                val = color.getBlue();
                break;
        }

        return val;
    }

    public static int obtenerRGBPorCanal(int val, CanalColor canal) {
        int rgb = 0;

        switch(canal) {
            case ROJO:
                rgb = new Color(val, 0, 0).getRGB();
                break;
            case VERDE:
                rgb = new Color(0, val, 0).getRGB();
                break;
            case AZUL:
                rgb = new Color(0, 0, val).getRGB();
                break;
        }

        return rgb;
    }

    public static int acumularColor(int color1, int color2) {
        return color1 | color2;
    }
}
