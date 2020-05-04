package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ProjAction extends GenericAction {
    DatabaseHandler db;
    boolean isAidSelected;
    boolean isNameSelected;
    boolean isSpeciesSelected;
    JScrollPane js;

    public ProjAction(DatabaseHandler db, JCheckBox aid, JCheckBox name, JCheckBox species,
                      JScrollPane sc) {
        this.db = db;
        this.isAidSelected = aid.isSelected();
        this.isNameSelected = name.isSelected();
        this.isSpeciesSelected = species.isSelected();
        this.js = sc;
    }

    public void actionPerformed() {
        StringBuilder statement = new StringBuilder("SELECT");
        int numSelections = 0;

        // Selection clauses
        if(isAidSelected){
            statement.append(" aid");
            numSelections++;
        }

        if(isNameSelected){
            if(numSelections > 0){
                statement.append(", name");
            } else {
                statement.append(" name");
                numSelections++;
            }
        }

        if(isSpeciesSelected){
            if(numSelections > 0){
                statement.append(", species");
            } else {
                statement.append(" species");
                numSelections++;
            }
        }

        statement.append(" FROM animal");


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
