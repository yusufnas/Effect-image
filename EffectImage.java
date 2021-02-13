/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgisleme;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author YUSUFNAS
 */


public class EffectImage { 

    public static void SpecScan(String path, int band) {
        BufferedImage img = null;
        File yol = null;
        try {
            yol = new File(path);

            img = ImageIO.read(yol);
        } catch (IOException hata) {
            System.out.println(hata.getMessage());
        }
        int w = img.getWidth();
        int h = img.getHeight();
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                int p = img.getRGB(i, j);
                int a = (p >> band) & 0xff;
                int r = (p >> band / 2) & 0xff;
                int g = (p >> band / 4) & 0xff;
                int b = p & band;
                int rgb = (r + g + b) / band;
                p = (a << band) | (rgb << band / 2) | (rgb << band / 4) | rgb;
                img.setRGB(i, j, p);
            }
        }
        System.out.println("Fotografin kaydedilecegi adresi giriniz: "); //Exm: C:\ysfnas\Desktop\
        Scanner kayit = new Scanner(System.in);
        path = kayit.nextLine();
        try {
            yol = new File(path + "scan.jpg");
            ImageIO.write(img, "jpeg", yol);
        } catch (IOException hata) {
            System.out.println(hata.getMessage());
        }

    }

    public static void main(String[] args) {
        System.out.println("Fotografin yolunu giriniz: "); //Exm: C:\ysfnas\Desktop\image.jpg
        Scanner oku = new Scanner(System.in);
        String path = oku.nextLine();

        System.out.println("Renk derecesini giriniz: "); //Start Blue(1) to Red (10000)
        oku = new Scanner(System.in);
        int band = oku.nextInt();

        SpecScan(path, band);

    }
}
