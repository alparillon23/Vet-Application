package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.sql.SQLException;

public class AddAnimalDML {
    DatabaseHandler db;
    String aid;
    String name;
    String type;
    String spec;
    JLabel message; // This allows you to Present a small Success or Error message in the GUI (optional)

    public AddAnimalDML(DatabaseHandler db, String aid, String name, String type, String spec, JLabel message){
        System.out.println(type);

        this.db = db;
        this.aid = aid;
        this.name = name;
        this.type = type;
        this.spec = spec;
        this.message = message;
    }

    public void actionPerformed() {
        /*TODO: Add the Insert Function in this Method.
        * The database handler should contain the 'insert' implementation, this handler is provided as db
        * The arguments are provided as instance variables, feel free to use
        * Optionally add a short success or error message by 'message.setText("Your Message")'
        * This will appear in the GUI.
        * */
        try {
            db.insertAnimal(Integer.parseInt(this.aid), this.name, this.spec, this.type);
        }
        catch (SQLException se) {
            se.printStackTrace();
            message.setText(se.getMessage());
        }
    }
}
