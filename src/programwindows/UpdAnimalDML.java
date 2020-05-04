package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.sql.SQLException;

public class UpdAnimalDML {
            DatabaseHandler db;
            String aid;
            String name;
            JLabel message; // This allows you to Present a small Success or Error message in the GUI (optional)

        public UpdAnimalDML(DatabaseHandler db, String aid, String name, JLabel message){
            this.db = db;
            this.aid = aid;
            this.name = name;
            this.message = message;
        }

        public void actionPerformed() {
        /*TODO: Add the Update Function in this Method.
         * The database handler should contain the 'update' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * Optionally add a short success or error message by 'message.setText("Your Message")'
         * This will appear in the GUI.
         * */
        try {
            db.updateAnimalName(this.name, Integer.parseInt(this.aid));
        } catch (SQLException se) {
            se.printStackTrace();
            message.setText(se.getMessage());
        }
        }
}
