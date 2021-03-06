package Espacial;

public class Mascaras {
    public static int[][] enfoque = { { 0, 0, 0, 0, 0 }, { 0, 0, -1, 0, 0 }, { 0, -1, 5, -1, 0 }, { 0, 0, -1, 0, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] desenfoque = { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] realceBordes = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, -1, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] repujado = { { 0, 0, 0, 0, 0 }, { 0, -2, -1, 0, 0 }, { 0, -1, 1, 1, 0 }, { 0, 0, 1, 2, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] detectarBordes = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, -4, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] filtroSobel = { { 0, 0, 0, 0, 0 }, { 0, -1, 0, 1, 0 }, { 0, -2, 0, 2, 0 }, { 0, -1, 0, 1, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] filtroSharpen = { { 0, 0, 0, 0, 0 }, { 0, 1, -2, 1, 0 }, { 0, -2, 5, -2, 0 }, { 0, 1, -2, 1, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] filtroNorte = { { 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0 }, { 0, 1, -2, 1, 0 }, { 0, -1, -1, -1, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] filtroEste = { { 0, 0, 0, 0, 0 }, { 0, -1, 1, 1, 0 }, { 0, -1, -2, 1, 0 }, { 0, -1, 1, 1, 0 }, { 0, 0, 0, 0, 0 } };
    public static int[][] filtroGauss = { { 1, 2, 3, 1, 1 }, { 2, 7, 11, 7, 2 }, { 3, 11, 17, 11, 3 }, { 2, 7, 11, 7, 1 },  { 1, 2, 3, 2, 1} };
}
