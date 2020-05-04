package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalDMLWindow {

    DatabaseHandler dbe;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;

    JPanel labelan; JLabel anlabel;

    JPanel insAid; JLabel insAidL; JTextField insAidT;
    JPanel insName; JLabel insNameL; JTextField insNameT;
    JPanel insSpec; JLabel insSpecL; JTextField insSpecT;
    JPanel insType; JLabel insTypeL; JTextField insTypeT;
    JPanel insSub; JButton insSubB; JLabel inserro;

    JPanel delAid; JLabel delAidL; JTextField delAidT;
    JPanel delSub; JButton delSubB; JLabel delerr;

    JPanel upName; JLabel upNameL; JTextField upNameT;
    JPanel upAid; JLabel upAidL; JTextField upAidT;
    JPanel upSub; JButton upSubB; JLabel uperr;

    JPanel back; JButton backB;
    Window win;

    AddAnimalDML ad;
    DelAnimalDML de;
    UpdAnimalDML up;

    static int WIDTH = 300;
    static int PANELW = 200;
    static int PANELH = 50;
    static int LABELW = 100;
    static int LABELH = 50;
    static int BUTTONW = 50;

    public AnimalDMLWindow(DatabaseHandler db){
        dbe = db;
        ob = new GraphicsObj();
        background = ob.makeWindow(2);

        title = ob.titleBar("Animal DML Screen");
        mainActivity = ob.jpBOX(WIDTH,WIDTH);
        labelan = ob.jpFLOW(PANELH, PANELW);
        insAid = ob.jpFLOW(PANELH, PANELW);
        insName = ob.jpFLOW(PANELH, PANELW);
        insSpec = ob.jpFLOW(PANELH, PANELW);
        insSub = ob.jpFLOW(PANELH, PANELW);
        insType = ob.jpFLOW(PANELH, PANELW);
        delAid = ob.jpFLOW(PANELH, PANELW);
        delSub = ob.jpFLOW(PANELH, PANELW);
        upAid = ob.jpFLOW(PANELH, PANELW);
        upName = ob.jpFLOW(PANELH, PANELW);
        upSub = ob.jpFLOW(PANELH, PANELW);
        back = ob.jpFLOW(PANELH, PANELW);

        anlabel = ob.details("<html>Insert new animal with<br>values</html>", LABELH, PANELW);
        insAidL = ob.details("aid: ", LABELH, LABELW);
        insNameL = ob.details("name: ", LABELH, LABELW);
        insSpecL = ob.details("species: ", LABELH, LABELW);
        insTypeL = ob.details("animal type: ", LABELH, LABELW);
        inserro = ob.details("", LABELH, LABELW);
        delAidL = ob.details("<html>delete animal<br>with aid: </html>", LABELH, LABELW);
        delerr = ob.details("", LABELH, LABELW);
        upAidL = ob.details("where aid: ", LABELH, LABELW);
        upNameL = ob.details("<html>update animal<br>name to:</html>", LABELH, LABELW);
        uperr = ob.details("", LABELH, LABELW);

        insNameT = ob.textField(LABELH, LABELW);
        insTypeT = ob.textField(LABELH, LABELW);
        insSpecT = ob.textField(LABELH, LABELW);
        insAidT = ob.textField(LABELH, LABELW);
        upNameT = ob.textField(LABELH, LABELW);
        upAidT = ob.textField(LABELH, LABELW);
        delAidT = ob.textField(LABELH, LABELW);

        insSubB = ob.thisButton(LABELH, BUTTONW, "SUBMIT");
        delSubB = ob.thisButton(LABELH, BUTTONW, "SUBMIT");
        upSubB = ob.thisButton(LABELH, BUTTONW, "SUBMIT");
        backB = ob.thisButton(LABELH, BUTTONW, "BACK");

        labelan.add(anlabel);

        insAid.add(insAidL);insAid.add(insAidT);
        insName.add(insNameL);insName.add(insNameT);
        insSpec.add(insSpecL);insSpec.add(insSpecT);
        insType.add(insTypeL);insType.add(insTypeT);
        insSub.add(insSubB);insSub.add(inserro);

        delAid.add(delAidL);delAid.add(delAidT);
        delSub.add(delSubB);delSub.add(delerr);
        upAid.add(upAidL);upAid.add(upAidT);
        upName.add(upNameL);upName.add(upNameT);
        upSub.add(upSubB);upSub.add(uperr);
        back.add(backB);

        System.out.println(insTypeT.getText());


        mainActivity.add(title);
        mainActivity.add(labelan);
        mainActivity.add(insAid);
        mainActivity.add(insName);
        mainActivity.add(insType);
        mainActivity.add(insSpec);
        mainActivity.add(insSub);
        mainActivity.add(delAid);
        mainActivity.add(delSub);
        mainActivity.add(upName);
        mainActivity.add(upAid);
        mainActivity.add(upSub);
        mainActivity.add(back);

        background.add(mainActivity);



        win = new Window(background, "Animal DML Screen", 2);

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuWindow(dbe);
                win.closeWindow();
            }
        });

        // public AddAnimalDML(DatabaseHandler db, JTextField aid, JTextField name, JTextField type, JTextField spec, JLabel message)
        insSubB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ad = new AddAnimalDML(dbe, insAidT.getText(), insNameT.getText(), insTypeT.getText(), insSpecT.getText(), inserro);
                ad.actionPerformed();
            }
        });

        delSubB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                de = new DelAnimalDML(dbe, delAidT.getText(), delerr);
                de.actionPerformed();
            }
        });

        upSubB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                up = new UpdAnimalDML(dbe, upAidT.getText(), upNameT.getText(), uperr);
                up.actionPerformed();
            }
        });


    }
}
