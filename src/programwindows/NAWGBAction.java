package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class NAWGBAction extends GenericAction {
    DatabaseHandler db;
    JScrollPane js;

    public NAWGBAction(DatabaseHandler db, JScrollPane js) {
        this.db = db;
        this.js = js;
    }

    public void actionPerformed(){
        /*TODO: Add the Nested Aggregation Function in this Method.
         * The database handler should contain the 'NAWGB' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * */
        String query = "select a.aid, a.name, count(distinct em.medid) as maxMedCount from animal a, vet_examination_animal vea, medication_examination em " +
                "where a.aid = vea.aid and vea.eid = em.eid " +
                "Group by (a.aid, a.name) " +
                "having count(distinct em.medid) = " +
                "(select max(medCount) from (" +
                "select count(distinct em1.medid) as medCount from animal a1, vet_examination_animal vea1, medication_examination em1 " +
                "where a1.aid = vea1.aid and vea1.eid = em1.eid " +
                "group by vea1.aid))";
        try {
            ResultSet res = db.runQuery(query);
            ResultSetMetaData metaData = res.getMetaData();

            // get column names
            int numColumns = metaData.getColumnCount();
            Vector<Object> columns = getColumns(numColumns, metaData);

            // get row data
            Vector<Vector<Object>> rows = getRows(numColumns, res);

            JTable table = new JTable(new DefaultTableModel(rows, columns));
            js.getViewport().setView(table);

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
