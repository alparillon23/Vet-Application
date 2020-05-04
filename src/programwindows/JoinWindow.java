package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinWindow {

    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;

    JPanel panelt; JLabel panelL;
    JPanel table1; JLabel table1L; JComboBox table1C;
    JPanel table2; JLabel table2L; JComboBox table2C;
    JPanel button; JButton b; JLabel butL;

    JScrollPane scn;

    JPanel back; JButton backB;
    Window win;

    String[] tab1 = {"Animal","Vet"};
    String[] tab11 = {"Handler","Sanctuary"};
    String[] tab12 = {"Specialization","Examination"};

    JoinAction sas;

    static int WIDTH = 300;
    static int PANELW = 200;
    static int PANELH = 50;
    static int LABELW = 100;
    static int LABELH = 50;
    static int BUTTONW = 50;
    static int SCNWIDTH = 125;

    public JoinWindow(DatabaseHandler db){
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(2);

        title = ob.titleBar("Join");
        mainActivity = ob.jpBOX(WIDTH,WIDTH);
        panelt = ob.jpFLOW(300, PANELW);
        table1 = ob.jpFLOW(PANELH, PANELW);
        table2 = ob.jpFLOW(PANELH, PANELW);
        button = ob.jpFLOW(PANELH, PANELW);
        back = ob.jpFLOW(PANELH, PANELW);

        panelL = ob.details("<html>Animal options:<br>1) Find each animal's handler<br>2)Find each animal's sanctuary<br>" +
                "<br>Vet options: <br>1) Find each vet's specialization<br>2) Find which dates each vet has performed an examination </html>", 300, PANELW);
        table1L = ob.details("Table 1:", LABELH, LABELW);
        table2L = ob.details("Table 2:", LABELH, LABELW);
        butL = ob.details("", LABELH, LABELW);
        b = ob.thisButton(LABELH, BUTTONW,"SUBMIT");
        backB = ob.thisButton(LABELH, BUTTONW,"BACK");

        scn = new JScrollPane(new JTable());
        scn.setPreferredSize(new Dimension(SCNWIDTH,200));
        table1C = new JComboBox(tab1);
        table2C = new JComboBox();
        table1C.setPreferredSize(new Dimension(LABELW, LABELH));
        table2C.setPreferredSize(new Dimension(LABELW, LABELH));

        panelt.add(panelL);
        table1.add(table1L);
        table1.add(table1C);
        table2.add(table2L);
        table2.add(table2C);
        button.add(b);
        back.add(backB);


        mainActivity.add(title);
        mainActivity.add(panelt);
        mainActivity.add(table1);
        mainActivity.add(table2);
        mainActivity.add(button);
        mainActivity.add(scn);
        mainActivity.add(back);

        background.add(mainActivity);

        win = new Window(background, "Join", 2);

        table1C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int val = table1C.getSelectedIndex();
                int i = 0;
                switch (val) {
                    case 0:{
                        table2C.removeAllItems();
                        while(i < tab11.length){
                            table2C.addItem(tab11[i]);
                            i++;
                        }
                    }
                        break;
                    default:{
                        table2C.removeAllItems();
                        while(i < tab12.length){
                            table2C.addItem(tab12[i]);
                            i++;
                        }
                    }break;
                }
            }
        });

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
                sas = new JoinAction(dbe,scn,table1C,table2C);
                sas.actionPerformed();
            }
        });
    }


}
