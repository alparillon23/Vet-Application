package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AggregationWindow {
    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;

    JPanel panelt; JLabel panelL;
    JPanel vPanel; JLabel vPanelL; JTextField vPanelT;
    JPanel button; JButton b; JLabel butL;

    JScrollPane scn;

    JPanel back; JButton backB;
    Window win;

    AggregationAction sas;

    static int WIDTH = 300;
    static int PANELW = 200;
    static int PANELH = 50;
    static int LABELW = 100;
    static int LABELH = 50;
    static int BUTTONW = 50;
    static int SCNWIDTH = 125;

    public AggregationWindow(DatabaseHandler db){
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(1);

        title = ob.titleBar("Aggregation");
        mainActivity = ob.jpBOX(WIDTH,WIDTH);
        panelt = ob.jpFLOW(PANELH, PANELW);
        vPanel = ob.jpFLOW(PANELH, PANELW);
        button = ob.jpFLOW(PANELH, PANELW);
        back = ob.jpFLOW(PANELH, PANELW);

        panelL = ob.details("<html>Find the number of examinations, grouped by year, for animal with: </html>", LABELH, PANELW);
        vPanelL = ob.details("aid = ", LABELH, LABELW);
        butL = ob.details("", LABELH, LABELW);
        b = ob.thisButton(LABELH, BUTTONW,"SUBMIT");
        backB = ob.thisButton(LABELH, BUTTONW,"BACK");

        scn = new JScrollPane(new JTable());
        scn.setPreferredSize(new Dimension(SCNWIDTH,75));
        vPanelT = ob.textField(LABELH, LABELW);

        panelt.add(panelL);
        vPanel.add(vPanelL);
        vPanel.add(vPanelT);
        button.add(b);
        back.add(backB);


        mainActivity.add(title);
        mainActivity.add(panelt);
        mainActivity.add(vPanel);
        mainActivity.add(button);
        mainActivity.add(scn);
        mainActivity.add(back);

        background.add(mainActivity);

        win = new Window(background, "Aggregation Screen", 1);

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
                sas = new AggregationAction(dbe, vPanelT, scn);
                sas.actionPerformed();
            }
        });



    }


}
