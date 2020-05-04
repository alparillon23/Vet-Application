package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionWindow {
    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;

    JPanel aidC; JLabel aidL; JCheckBox aidCh;
    JPanel nameC; JLabel nameL; JCheckBox nameCh;
    JPanel specC; JLabel specL; JCheckBox specCh;
    JPanel where; JLabel wheL;
    JPanel wAidC; JLabel wAidCL; JTextField wAidCT;
    JPanel wNameC; JLabel wNameCL; JTextField wNameCT;
    JPanel wSpecC; JLabel wSpecCL; JTextField wSpecCT;
    JPanel button; JButton b; JLabel butL;

    JScrollPane scn;

    JPanel back; JButton backB;
    Window win;

    SelAction sas;

    static int WIDTH = 300;
    static int PANELW = 125;
    static int PANELH = 50;
    static int LABELW = 75;
    static int LABELH = 50;
    static int BUTTONW = 50;
    static int SCNWIDTH = 125;

    public SelectionWindow(DatabaseHandler db){
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(2);

        title = ob.titleBar("Select Animal's");
        mainActivity = ob.jpBOX(WIDTH,WIDTH);
        wSpecC = ob.jpFLOW(PANELH, PANELW);
        wAidC = ob.jpFLOW(PANELH, PANELW);
        wNameC = ob.jpFLOW(PANELH, PANELW);
        where = ob.jpFLOW(PANELH, PANELW);
        button = ob.jpFLOW(PANELH, PANELW);
        back = ob.jpFLOW(PANELH, PANELW);

        wheL = ob.details("<html>id, name, and species<br>where</html>", LABELH, PANELW);
        wAidCL = ob.details("aid: ", LABELH, LABELW);
        wNameCL = ob.details("name: ", LABELH, LABELW);
        wSpecCL= ob.details("species: ", LABELH, LABELW);
        butL = ob.details("", LABELH, LABELW);
        b = ob.thisButton(LABELH, BUTTONW,"SUBMIT");
        backB = ob.thisButton(LABELH, BUTTONW,"BACK");

        scn = new JScrollPane(new JTable());
        scn.setPreferredSize(new Dimension(SCNWIDTH,500));
        wAidCT = ob.textField(LABELH, LABELW);
        wNameCT = ob.textField(LABELH, LABELW);
        wSpecCT = ob.textField(LABELH, LABELW);

        where.add(wheL);
        wAidC.add(wAidCL);wAidC.add(wAidCT);
        wNameC.add(wNameCL);wNameC.add(wNameCT);
        wSpecC.add(wSpecCL);wSpecC.add(wSpecCT);
        button.add(b);
        back.add(backB);


        mainActivity.add(title);
        mainActivity.add(where);
        mainActivity.add(wAidC);
        mainActivity.add(wNameC);
        mainActivity.add(wSpecC);
        mainActivity.add(button);
        mainActivity.add(scn);
        mainActivity.add(back);

        background.add(mainActivity);

        win = new Window(background, "Selection Screen", 2);

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
                sas = new SelAction(dbe, wAidCT, wNameCT, wSpecCT, scn);
                sas.actionPerformed();
            }
        });
    }
}
