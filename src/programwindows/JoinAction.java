package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class JoinAction extends GenericAction {
    DatabaseHandler db;
    boolean animal; //true if animal gets selected, false if vet got selected in table 1
    boolean table2; // animal {true = handler, false = sanctuary} vet {true = specialization, false = examination}
    JScrollPane js;

    public JoinAction(DatabaseHandler db, JScrollPane js, JComboBox av, JComboBox option2) {
        this.db = db;
        switch(av.getSelectedIndex()) {
            case 0:
                animal = true;
            break;
            default:
                animal = false;
        }
        switch(option2.getSelectedIndex()) {
            case 0:
                table2 = true;
                break;
            default:
                table2 = false;
        }
        this.js = js;
    }

    public void actionPerformed() {
        /*TODO: Add the Join Function in this Method.
         * The database handler should contain the 'join' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * */
        StringBuilder query = new StringBuilder("select ");
        if (animal) {
            query.append("a.name, s.name from animal a");
            if (table2) {
                // join with handler
                query.append(", animal_handler ah, handler h, staff s " +
                        "where a.aid = ah.aid and ah.hid = h.sid and h.sid = s.sid");
            }
            else {
                // join with sanctuary
                query.append(", animal_sanctuary ast, sanctuary s " +
                        "where a.aid = ast.aid and ast.sanctid = s.sanctid");
            }
        } else {
            query.append("s.name");
            if(table2){
                // join with specialization
                query.append(", spec.name from veterinarian v, staff s, specialization spec, vet_specialization vs " +
                        "where v.sid = s.sid and vs.vid = v.sid and vs.specid = spec.specid");
            } else {
                // join with examination
                query.append(", e.examdate, e.examtype " +
                        "from veterinarian v, staff s, examination e, vet_examination_animal vea " +
                        "where v.sid = s.sid and e.eid = vea.eid and v.sid = vea.vid");
            }
        }

        try {
            ResultSet res = db.runQuery(query.toString());
            ResultSetMetaData metaData = res.getMetaData();

            // get column names
            int numColumns = metaData.getColumnCount();
            Vector<Object> columns = getColumns(numColumns, metaData);

            // get row data
            Vector<Vector<Object>> rows = getRows(numColumns, res);

            JTable table = new JTable(new DefaultTableModel(rows, columns));
            js.getViewport().setView(table);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
