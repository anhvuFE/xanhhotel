/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package DAO;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author PC
 */
public class capchaDAO {

    public String getCapcha() {
        Random rand = new Random();
        String str = "0123456789";
        char[] captcha = new char[6];
        for (int i = 0; i < 6; i++) {
            captcha[i] = str.charAt(rand.nextInt(str.length()));
        }
        return String.valueOf(captcha);
    }

    private BufferedImage generateCaptchaImage(String captcha) {
        int width = 200;
        int height = 50;

        // Create a new image with the specified size and format
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get a graphics context on the image
        Graphics2D g2d = image.createGraphics();

        // Set the background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Draw the captcha string
        Font font = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(font);
        for (int i = 0; i < captcha.length(); i++) {
            char c = captcha.charAt(i);
            g2d.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            g2d.drawString("" + c, 20 + i * 30, 35);
        }

        // Add random lines to the image
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 20; i++) {
            int x1 = (int) (Math.random() * width);
            int y1 = (int) (Math.random() * height);
            int x2 = (int) (Math.random() * width);
            int y2 = (int) (Math.random() * height);
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Cleanup
        g2d.dispose();

        return image;
    }

}
