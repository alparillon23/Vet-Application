package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivisionWindow {
    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;

    JPanel panelt; JLabel panelL;
    JPanel button; JButton b; JLabel butL;

    JScrollPane scn;

    JPanel back; JButton backB;
    Window win;

    DivisionAction sas;

    static int WIDTH = 300;
    static int PANELW = 200;
    static int PANELH = 50;
    static int LABELW = 100;
    static int LABELH = 50;
    static int BUTTONW = 50;
    static int SCNWIDTH = 125;

    public DivisionWindow(DatabaseHandler db){
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(1);

        title = ob.titleBar("Division Query");
        mainActivity = ob.jpBOX(WIDTH,WIDTH);
        panelt = ob.jpFLOW(PANELH, PANELW);
        button = ob.jpFLOW(PANELH, PANELW);
        back = ob.jpFLOW(PANELH, PANELW);

        panelL = ob.details("<html>Find vet id and name who has examined every animal</html>", LABELH, PANELW);
        butL = ob.details("", LABELH, LABELW);
        b = ob.thisButton(LABELH, BUTTONW,"SUBMIT");
        backB = ob.thisButton(LABELH, BUTTONW,"BACK");

        scn = new JScrollPane(new JTable());
        scn.setPreferredSize(new Dimension(SCNWIDTH,250));

        panelt.add(panelL);
        button.add(b);
        back.add(backB);


        mainActivity.add(title);
        mainActivity.add(panelt);
        mainActivity.add(button);
        mainActivity.add(scn);
        mainActivity.add(back);

        background.add(mainActivity);

        win = new Window(background, "Division Query", 1);

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuWindow(dbe);
                win.closeWindow();
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sas = new DivisionAction(dbe, scn);
                sas.actionPerformed();
            }
        });



    }

}
