package programwindows;

import javax.swing.*;
import java.awt.*;

public class GraphicsObj {
    //A dedicated class which returns panels for desired assets (Name Fields, List Fields etc)
    static int WIDTH = 500;
    static int BOXWIDTH = 400;
    Color butColor = new Color(99,77,24);
    // Creates a Panel with The Desired Title
    // Outputs a label with the "name"
    public JPanel titleBar(String name){
        JPanel jp = new JPanel();
        jp.setSize(BOXWIDTH, 25);
        JLabel n = new JLabel(name);
        n.setFont(new Font("Impact", Font.LAYOUT_LEFT_TO_RIGHT,40));
        n.setForeground(Color.WHITE);
        jp.add(n);
        jp.setOpaque(false);
        return jp;
    }
    // Creates a Window with a background color (or pic) set
    // Accepts a single argument - tall?
    // If tall = true outputs a tall window else makes a smaller window
    public JPanel makeWindow(int tall){
        JPanel jp = new BackDropPanel(tall);
        int height = 0;
        switch(tall){
            case 0:
                height = 300;
                break;
            case 1:
                height = 500;
                break;
            default:
                height = 700;
        }
        jp.setPreferredSize(new Dimension(WIDTH, height));
        jp.setLayout(new OverlayLayout(jp));
        try{
            jp.setOpaque(true);
            jp.repaint();
       } catch(Exception ex) {
            jp.setBackground(new Color(56, 99, 24));
        }
        return jp;
    }
    // Button - Drawn Buttons To Be Used
    // Accepts the Dimensions and Name and Outputs the button
    // 3 arguments if only text, 4 arguments (optional) for symbol
    public JButton thisButton(int height, int width, String name){
        JButton but = new JButton(name);
        but.setBackground(butColor);
        but.setForeground(Color.WHITE);
        but.setSize(width, height);
        return but;
    }
    public JButton thisButton(int height, int width, String sympath, String alt){
        JButton but;
        try{
            but = new JButton(new ImageIcon(sympath));
        } catch(Exception ex) {
            but = new JButton(alt);
        }
        but.setBackground(butColor);
        but.setForeground(Color.WHITE);
        but.setSize(width, height);
        return but;
    }
    public JButton thisButtonBigFont(int height, int width, String name){
        JButton but = new JButton(name);
        but.setFont(new Font(Font.SANS_SERIF, Font.LAYOUT_LEFT_TO_RIGHT,20));
        but.setBackground(butColor);
        but.setForeground(Color.WHITE);
        but.setSize(width, height);
        but.setPreferredSize(new Dimension(width,height));
        return but;
    }

    public JLabel details(String label, int height, int width){
        JLabel lab = new JLabel(label);
        lab.setForeground(Color.WHITE);
        lab.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
        lab.setAlignmentX(Component.LEFT_ALIGNMENT);
        lab.setPreferredSize(new Dimension(width, height));
        return lab;
    }

    public JTextField textField(int height, int width){
        JTextField te = new JTextField();
        te.setPreferredSize(new Dimension(width, height));
        te.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        return te;
    }

    public JPanel jpFLOW(int height, int width){
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(width, height));
        jp.setOpaque(false);
        return jp;
    }

    public JPanel jpBOX(int height, int width){
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
        jp.setPreferredSize(new Dimension(width, height));
        jp.setOpaque(false);
        return jp;
    }

}
