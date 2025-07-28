package src;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ProcessamentoImagem {

    // Carrega PNG e retorna a imagem em tons de cinza [0.0 - 1.0]
    public static double[][] carregarImagemCinza(String filename) throws IOException {
        BufferedImage img = ImageIO.read(new File(filename));
        int w = img.getWidth();
        int h = img.getHeight();
        double[][] out = new double[h][w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                out[y][x] = (r + g + b) / 3.0 / 255.0;
            }
        }
        return out;
    }

    // Salva matriz de cinza em imagem PNG, 0.0=branco, 1.0=preto
    public static void salvarImagemBinaria(double[][] mat, String outfile) throws IOException {
        int h = mat.length, w = mat[0].length;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++) {
            int cor = (mat[y][x] > 0.5) ? 0x000000 : 0xFFFFFF; // preto ou branco
            img.setRGB(x, y, cor);
        }
        ImageIO.write(img, "png", new File(outfile));
    }

    // Suaviza a matriz usando Gaussiano simples (padrão 3x3 sigma=1) para simplificar
    public static double[][] filtroGaussiano(double[][] img) {
        double[][] kernel = {
                {1, 2, 1},
                {2, 4, 2},
                {1, 2, 1}
        };
        for (int j = 0; j < kernel.length; j++)
            for (int i = 0; i < kernel[0].length; i++)
                kernel[j][i] /= 16.0;
        return convoluir(img, kernel);
    }

    // Convolução 2D para matriz (cinza)
    public static double[][] convoluir(double[][] img, double[][] kernel) {
        int h = img.length, w = img[0].length;
        int ksz = kernel.length;
        int k2 = ksz / 2;
        double[][] out = new double[h][w];
        for (int y = k2; y < h - k2; y++) {
            for (int x = k2; x < w - k2; x++) {
                double acc = 0.0;
                for (int j = 0; j < ksz; j++) for (int i = 0; i < ksz; i++)
                    acc += img[y + j - k2][x + i - k2] * kernel[j][i];
                out[y][x] = Math.min(1, Math.max(0, acc));
            }
        }
        return out;
    }

    // Aplica Sobel e retorna matriz da derivada na direção x ou y
    public static double[][] sobel(double[][] img, boolean horizontal) {
        double[][] kx = {
                {-1, 0, 1},
                {-2, 0, 2},
                {-1, 0, 1}
        };
        double[][] ky = {
                {-1, -2, -1},
                { 0,  0,  0},
                { 1,  2,  1}
        };
        return convoluir(img, horizontal ? kx : ky);
    }

    // Aplica Laplaciano
    public static double[][] laplaciano(double[][] img) {
        double[][] kernel = {
                {0,  1, 0},
                {1, -4, 1},
                {0,  1, 0}
        };
        return convoluir(img, kernel);
    }

    // Algoritmo 1: Gradiente/Sobel
    public static double[][] algoritmo1(double[][] img, double threshold) {
        double[][] suav = filtroGaussiano(img);
        double[][] dx = sobel(suav, true);
        double[][] dy = sobel(suav, false);

        int h = img.length, w = img[0].length;
        double[][] c = new double[h][w];
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++)
            c[y][x] = Math.sqrt(dx[y][x]*dx[y][x] + dy[y][x]*dy[y][x]);

        double[][] finalmat = new double[h][w];
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++)
            finalmat[y][x] = (c[y][x] > threshold) ? 1.0 : 0.0;

        return finalmat;
    }

    // Algoritmo 2: Laplaciano
    public static double[][] algoritmo2(double[][] img, double tolerancia) {
        double[][] suav = filtroGaussiano(img);
        double[][] lap = laplaciano(suav);

        int h = img.length, w = img[0].length;
        double[][] b = new double[h][w];
        for (int y = 0; y < h; y++) for (int x = 0; x < w; x++)
            b[y][x] = (Math.abs(lap[y][x]) > tolerancia) ? 1.0 : 0.0;

        return b;
    }

    // Demo: Testa ambos os algoritmos em um PNG
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Uso: java ProcessamentoImagem arquivo.png");
            return;
        }
        String entrada = args[0];
        double[][] img = carregarImagemCinza(entrada);

        double[][] res1 = algoritmo1(img, 0.2); // threshold, ajuste conforme a imagem
        double[][] res2 = algoritmo2(img, 0.0001); // tolerância ajuste conforme a imagem

        salvarImagemBinaria(res1, "saida_sobel.png");
        salvarImagemBinaria(res2, "saida_laplace.png");
        System.out.println("Feito! Resultados em saida_sobel.png e saida_laplace.png");
    }
}