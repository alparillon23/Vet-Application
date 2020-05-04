package programwindows;

import javax.swing.*;
import java.awt.*;

public class Window {
    JFrame jf;
    String defaultName = "ZOO App";
    static int WIDTH = 500;
    //Simply Returns a Window with the Preferred Panel - Setting that Window to Visible
    public Window(JPanel jp, String windowName, int smallwin){
        jf = new JFrame(windowName);
        int height = 0;
        switch(smallwin){
            case 0: {
                height = 300;
            }break;
            case 1: {
                height = 500;
            }break;
            default: {
                height = 700;
            }
        }
        jf.setIconImage(new ImageIcon("src\\programwindows\\icon.png").getImage());
        jf.setContentPane(jp);
        jf.setPreferredSize(new Dimension(WIDTH, height));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    public Window(JPanel jp, int smallwin){
        JFrame jf = new JFrame(defaultName);
        int height = 0;
        switch(smallwin){
            case 0: {
                height = 300;
            }break;
            case 1: {
                height = 500;
            }break;
            default: {
                height = 700;
            }
        }
        jf.setIconImage(new ImageIcon("src\\programwindows\\icon.png").getImage());
        jf.setContentPane(jp);
        jf.setPreferredSize(new Dimension(WIDTH, height));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    //Use this method to close the window - Action Listeners should use this method
    public void closeWindow(){
        jf.dispose();
    }
}
