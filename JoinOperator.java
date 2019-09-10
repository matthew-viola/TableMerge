import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinOperator {

    // Assignment 5:
    //
    // Consider tables A and B that share a single column whose name
    // is stored in the variable uniqueKey.
    // Every cell in this column has a unique value.
    //
    // GOAL:
    //
    // It is your job to build a new table that joins the data from
    // A and B based on the column uniqueKey.
    //
    // SUBPROBLEMS:
    //
    // (a) The new table must have a map consisting of the column names
    // from A and B (10 pts)
    //
    // (b) In order to match rows from A and B that agree on column uniqueKey,
    // you must use a HashMap (15 pts)
    //
    // (c) After matching rows, you must build new rows that join together
    // the data from A and B (10 pts)
    //
    // (d) Complete the join method and pass the sanity check (10 pts)
    
	public static Table join(Table A, Table B, String uniqueKey) {
		
    	Map<String, Integer> columnNames = joinColumnNames(A.columnNamesMap(), B.columnNamesMap());
    	Map<String, Integer> rowsin = createHashMap(B, uniqueKey);
    	List<Row> rows = new ArrayList<Row>();
    	for(int i = 0; i < A.size(); i++) {
    		rows.add(joinSingleRow(columnNames, A, B, i, rowsin.get(A.getCell(i, uniqueKey))));
    	}
        return new Table(columnNames , rows);
    }

    // (Optional) You may use the following as a helper method for part (a)
    // Build and return a new map such that the new map contains all of the
    // keys from columnNamesFromA and all of the keys from columnNamesFromB.
    // The new map sends each key to an integer index. If there are n keys,
    // then each integer index from 0 to n - 1 is used once.
    private static Map<String, Integer> joinColumnNames(Map<String, Integer> columnNamesFromA,
            Map<String, Integer> columnNamesFromB) {
    	
    	 Map<String, Integer> columns = new HashMap<String, Integer>();
    	 List<String> names = new ArrayList<String>();
    	 for(String columnName : columnNamesFromA.keySet()) {
    		 names.add(columnName);
    	 }
    	 for(String columnNameB : columnNamesFromB.keySet()) {
    		 if(!names.contains(columnNameB)) {
    			 	names.add(columnNameB);
    		 }
    	 }
    	 for(int x=0; x < names.size(); x++) {
    		columns.put(names.get(x), x);
    	}
        return columns;
    }

    // (Optional) You may use the following as a helper method for part (b)
    // Build and return a HashMap that sends strings to row indexes such
    // that the HashMap allows you to lookup the index of a row that
    // contains a specified string value in column uniqueKey.
    private static Map<String, Integer> createHashMap(Table A, String uniqueKey) {
    	Map<String, Integer> rows = new HashMap<String, Integer>();
    	for(int i = 0; i < A.size() ; i++) {
    		rows.put(A.getCell(i, uniqueKey), i);
    	}
        return rows;
    }

    // (Optional) You may use the following as a helper method for part (c)
    // Build and return a row that joins together data from the specified
    // row from A and the specified row from B.
    private static Row joinSingleRow(Map<String, Integer> columnNamesForNewTable, Table A, Table B, int rowIndexFromA,
            int rowIndexFromB) {
    	String[] joined = new String[columnNamesForNewTable.keySet().size()]; 
    	
    	for(String names : columnNamesForNewTable.keySet()) {
    	
    			int columnIndex = columnNamesForNewTable.get(names);
    		
    			if(A.getCell(rowIndexFromA, names) == null) {
    				joined[columnIndex] = B.getCell(rowIndexFromB, names);
    			}
    			else {
    				joined[columnIndex] = A.getCell(rowIndexFromA, names);
    			}
    		}
    	return new Row(joined);
    }

}
