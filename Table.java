import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private Map<String, Integer> columnNames;
    private List<Row> rows;

    public Table(Map<String, Integer> columnNames, List<Row> rows) {
        this.columnNames = columnNames;
        this.rows = rows;
    }

    public Table(String filename) {
        rows = new ArrayList<Row>();
        load(filename);
    }

    public Map<String, Integer> columnNamesMap() {
        return columnNames;
    }

    public String getCell(int index, String columnName) {
        if (columnNames.get(columnName) == null) {
            return null;
        } else {
            return rows.get(index).get(columnNames.get(columnName));
        }
    }

    public int size() {
        return rows.size();
    }

    private void load(String filename) {
        try {
            String currentDirectory = System.getProperty("user.dir") + "/";
            // System.out.println(currentDirectory);
            BufferedReader bf = new BufferedReader(new FileReader(currentDirectory + filename));

            // Parse column names
            columnNames = parseColumnNames(bf.readLine());

            // Parse rows
            String line;
            while ((line = bf.readLine()) != null) {
                rows.add(parseRow(line));
            }
            bf.close();
        } catch (IOException e) {
            System.out.println("File '" + filename + "' not found.");
        }
    }

    private Map<String, Integer> parseColumnNames(String line) {
        Map<String, Integer> columnNames = new HashMap<String, Integer>();
        String[] tokens = line.split(",");
        for (int i = 0; i < tokens.length; i++) {
            columnNames.put(tokens[i], i);
        }
        return columnNames;
    }

    private Row parseRow(String line) {
        return new Row(line.split(","));
    }

    // This is for debugging purposes
    public String toString() {
        String[] columnNamesArray = columnNames.keySet().toArray(new String[0]);
        Arrays.sort(columnNamesArray);

        String[] rowsArray = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            rowsArray[i] = "";
            for (int j = 0; j < columnNamesArray.length; j++) {
                rowsArray[i] += this.getCell(i, columnNamesArray[j]) + "|";
            }
        }
        Arrays.sort(rowsArray);

        return Arrays.toString(rowsArray);
    }

}
