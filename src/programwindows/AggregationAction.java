package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class AggregationAction extends GenericAction {

    DatabaseHandler db;
    String aid;
    JScrollPane js;

    public AggregationAction(DatabaseHandler db, JTextField tf, JScrollPane js) {
        this.db = db;
        aid = tf.getText();
        this.js = js;
    }

    public void actionPerformed(){
        /*TODO: Add the Aggregation Function in this Method.
         * The database handler should contain the 'aggregate' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * */

        StringBuilder statement = new StringBuilder("SELECT extract(year from e.examDate) as year, count(DISTINCT vea.eid) as examCount " +
                "FROM vet_examination_animal vea, examination e WHERE vea.eid = e.eid and vea.aid = ");
        statement.append(this.aid);
        statement.append(" group by extract(year from e.examDate)");

        try {
            ResultSet res = db.runQuery(statement.toString());
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
