package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectionWindow {
    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;

    JPanel aidC; JLabel aidL; JCheckBox aidCh;
    JPanel nameC; JLabel nameL; JCheckBox nameCh;
    JPanel specC; JLabel specL; JCheckBox specCh;

    JPanel button; JButton b; JLabel butL;

    JScrollPane scn;

    JPanel back; JButton backB;
    Window win;

    ProjAction projAction;

    static int WIDTH = 300;
    static int PANELW = 125;
    static int PANELH = 50;
    static int LABELW = 75;
    static int LABELH = 50;
    static int BUTTONW = 50;
    static int SCNWIDTH = 125;

    public ProjectionWindow(DatabaseHandler db) {
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(2);

        title = ob.titleBar("Project Animal's");
        mainActivity = ob.jpBOX(WIDTH,WIDTH);
        aidC = ob.jpFLOW(PANELH, PANELW);
        nameC = ob.jpFLOW(PANELH, PANELW);
        specC = ob.jpFLOW(PANELH, PANELW);
        button = ob.jpFLOW(PANELH, PANELW);
        back = ob.jpFLOW(PANELH, PANELW);

        aidL = ob.details("aid: ", LABELH, LABELW);
        nameL = ob.details("name: ", LABELH, LABELW);
        specL = ob.details("species: ", LABELH, LABELW);
        butL = ob.details("", LABELH, LABELW);
        b = ob.thisButton(LABELH, BUTTONW,"SUBMIT");
        backB = ob.thisButton(LABELH, BUTTONW,"BACK");

        scn = new JScrollPane(new JTable());
        scn.setPreferredSize(new Dimension(SCNWIDTH,500));
        aidCh = new JCheckBox();
        nameCh = new JCheckBox();
        specCh = new JCheckBox();

        aidC.add(aidL);aidC.add(aidCh);aidC.add(scn);
        nameC.add(nameL);nameC.add(nameCh);
        specC.add(specL);specC.add(specCh);
        button.add(b);
        back.add(backB);


        mainActivity.add(title);
        mainActivity.add(aidC);
        mainActivity.add(nameC);
        mainActivity.add(specC);
        mainActivity.add(button);
        mainActivity.add(scn);
        mainActivity.add(back);

        background.add(mainActivity);

        win = new Window(background, "Projection Screen", 2);

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
                projAction = new ProjAction(dbe, aidCh, nameCh, specCh, scn);
                projAction.actionPerformed();
            }
        });

    }
}
