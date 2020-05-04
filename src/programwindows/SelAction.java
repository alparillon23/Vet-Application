package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class SelAction extends GenericAction {
    DatabaseHandler db;
    String aid;
    String name;
    String species;
    JScrollPane js;

    public SelAction(DatabaseHandler db, JTextField aide,
                     JTextField namee, JTextField specie, JScrollPane sc) {
        this.db = db;
        this.aid = aide.getText();
        this.name = namee.getText();
        this.species = specie.getText();
        js = sc;
    }

    public void actionPerformed(){
        /*TODO: Add the Select Function in this Method.
         * The database handler should contain the 'select' implementation, this handler is provided as db
         * The arguments are provided as instance variables, feel free to use
         * */

        StringBuilder statement = new StringBuilder("SELECT aid, name, species FROM animal");

        if(!this.aid.equals("") || !this.name.equals("") || !this.species.equals("")) {
            statement.append(" WHERE");
            int numProjections = 0;

            if(!this.aid.equals("")){
                statement.append(" aid = ").append(this.aid);
                numProjections++;
            }

            if(!this.name.equals("")) {
                if (numProjections > 0) {
                    statement.append(" AND name = \'").append(this.name).append("\'");
                } else {
                    statement.append(" name = \'").append(this.name).append("\'");
                    numProjections++;
                }
            }

            if(!this.species.equals("")) {
                if (numProjections > 0) {
                    statement.append(" AND species = \'").append(this.species).append("\'");
                } else {
                    statement.append(" species = \'").append(this.species).append("\'");
                    numProjections++;
                }
            }
        }
        System.out.println(statement.toString());

        // execute queries and return result set
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
