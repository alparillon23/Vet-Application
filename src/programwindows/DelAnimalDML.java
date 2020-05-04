package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.sql.SQLException;

public class DelAnimalDML {
    DatabaseHandler db;
    String aid;
    JLabel message;

    public DelAnimalDML(DatabaseHandler db, String aid, JLabel message){
        this.db = db;
        this.aid = aid;
        this.message = message;
    }

    public void actionPerformed(){
        /*TODO: Add the Delete Function in this Method.
         * The database handler should contain the 'delete' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * Optionally add a short success or error message by 'message.setText("Your Message")'
         * This will appear in the GUI.
         * */
        try {
            db.deleteAnimal(Integer.parseInt(this.aid));
        } catch (SQLException se) {
            se.printStackTrace();
            message.setText(se.getMessage());
        }
    }
}
