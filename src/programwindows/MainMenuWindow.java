package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow {
    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;
    JPanel whole;
    JPanel side1;
    JPanel side2;
    JPanel button;
    JButton but1;
    JButton but2;
    JButton but3;
    JButton but4;
    JButton but5;
    JButton but6;
    JButton but7;
    JButton but8;
    static int WIDTH = 300;
    Window win;

    public MainMenuWindow(DatabaseHandler db){
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(1);

        mainActivity = new JPanel();
        mainActivity.setLayout(new BoxLayout(mainActivity,BoxLayout.Y_AXIS));
        mainActivity.setOpaque(false);

        title = ob.titleBar("Main Menu");

        whole = new JPanel();
        whole.setPreferredSize(new Dimension(WIDTH, 300));
        whole.setOpaque(false);

        side1 = new JPanel();
        side1.setLayout(new BoxLayout(side1,BoxLayout.Y_AXIS));
        side1.setPreferredSize(new Dimension(WIDTH/2, 300));
        side1.setOpaque(false);

        side2 = new JPanel();
        side2.setLayout(new BoxLayout(side2,BoxLayout.Y_AXIS));
        side2.setPreferredSize(new Dimension(WIDTH/2, 300));
        side2.setOpaque(false);

        but1 = ob.thisButtonBigFont(75, 100, "<html><center>Animal<br>DML</center></html>");
        but2 = ob.thisButtonBigFont(100, 100, "<html>Selection</html>");
        but3 = ob.thisButtonBigFont(75, 100, "Join");
        but4 = ob.thisButtonBigFont(75, 100, "<html>Aggregation</html>");
        but5 = ob.thisButtonBigFont(150, 100, "<html><center>Nested<br>Aggregation<br>with<br>Group By</center></html>");
        but6 = ob.thisButtonBigFont(75, 100, "Division");
        but8 = ob.thisButtonBigFont(100, 100, "<html>Projection</html>");

        but1.setAlignmentX(Component.CENTER_ALIGNMENT);
        but2.setAlignmentX(Component.CENTER_ALIGNMENT);
        but3.setAlignmentX(Component.CENTER_ALIGNMENT);
        but4.setAlignmentX(Component.CENTER_ALIGNMENT);
        but5.setAlignmentX(Component.CENTER_ALIGNMENT);
        but6.setAlignmentX(Component.CENTER_ALIGNMENT);
        but8.setAlignmentX(Component.CENTER_ALIGNMENT);

        side1.add(but1);
        side1.add(Box.createRigidArea(new Dimension(5,10)));
        side1.add(but2);
        side1.add(Box.createRigidArea(new Dimension(5,10)));
        side1.add(but8);
        side1.add(Box.createRigidArea(new Dimension(5,10)));
        side1.add(but3);
        side1.add(Box.createRigidArea(new Dimension(5,10)));
        side2.add(but4);
        side2.add(Box.createRigidArea(new Dimension(5,10)));
        side2.add(but5);
        side2.add(Box.createRigidArea(new Dimension(5,10)));
        side2.add(but6);
        side2.add(Box.createRigidArea(new Dimension(5,10)));


        whole.add(side1);
        whole.add(side2);

        button = ob.jpFLOW(30, 300);
        but7 = ob.thisButton(30, 50, "LOG OUT");
        button.add(but7);

        mainActivity.add(title);
        mainActivity.add(whole);
        mainActivity.add(button);

        background.add(mainActivity);

        win = new Window(background, "Main Menu", 1);

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AnimalDMLWindow(dbe);
                win.closeWindow();
            }
        });

        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectionWindow(dbe);
                win.closeWindow();
            }
        });

        but8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProjectionWindow(dbe);
                win.closeWindow();
            }
        });

        but3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinWindow(dbe);
                win.closeWindow();
            }
        });

        but4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AggregationWindow(dbe);
                win.closeWindow();
            }
        });

        but5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NAWGBWindow(dbe);
                win.closeWindow();
            }
        });

        but6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DivisionWindow(dbe);
                win.closeWindow();
            }
        });

        but7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbe.close();
                new LoginWindow();
                win.closeWindow();
            }
        });
    }
}
