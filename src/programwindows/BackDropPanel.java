package programwindows;

import javax.swing.*;
import java.awt.*;


public class BackDropPanel extends JPanel {
    //THIS IS IMPLEMENTED SO WE CAN ADD A BACKGROUND IMAGE TO THE WINDOW - Rather than boring gray.

    Image img;
    public BackDropPanel(int level)
    {
        int tall = 0;
        switch(level){
            case 0:
                tall = 300;
                break;
            case 1:
                tall = 500;
                break;
            default:
                tall = 700;
        }
        img = Toolkit.getDefaultToolkit().createImage("src\\programwindows\\backdrop.jpg").getScaledInstance(500, tall, Image.SCALE_DEFAULT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
        repaint();
    }


}
