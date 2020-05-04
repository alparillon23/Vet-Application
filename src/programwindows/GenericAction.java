package programwindows;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class GenericAction {
    public Vector<Object> getColumns(int numColumns, ResultSetMetaData metaData) throws SQLException {
        Vector<Object> columns = new Vector<>();
        for(int i = 0; i < numColumns; i++){
            columns.add(metaData.getColumnName(i+1));
        }
        return columns;
    }

    public Vector<Vector<Object>> getRows(int numColumns, ResultSet res) throws SQLException {
        Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
        while (res.next()) {
            Vector<Object> data = new Vector<>();
            for (int i = 0; i < numColumns; i++) {
                data.add(res.getString(i+1));
            }
            rows.add(data);
        }
        return rows;
    }

}
