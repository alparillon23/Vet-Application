package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DivisionAction extends GenericAction {
    DatabaseHandler db;
    JScrollPane js;

    public DivisionAction(DatabaseHandler db, JScrollPane js) {
        this.db = db;
        this.js = js;
    }

    public void actionPerformed() {
        /*TODO: Add the Division Function in this Method.
         * The database handler should contain the 'Division' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * */

        try {
            ResultSet res = db.runQuery("SELECT s.sid, s.name FROM veterinarian v, staff s WHERE v.sid = s.sid AND NOT EXISTS (" +
                    "(SELECT a.aid FROM animal a) " +
                    "MINUS " +
                    "(SELECT vea.aid FROM vet_examination_animal vea WHERE s.sid = vea.vid))");
            ResultSetMetaData metaData = res.getMetaData();

            // get column names
            int numColumns = metaData.getColumnCount();
            Vector<Object> columns = getColumns(numColumns, metaData);

            // get row data
            Vector<Vector<Object>> rows = getRows(numColumns, res);

            JTable table = new JTable(new DefaultTableModel(rows, columns));
            js.getViewport().setView(table);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
