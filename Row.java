
public class Row {

    String[] data;

    public Row(String[] data) {
        this.data = data;
    }

    public String get(int i) {
        return data[i];
    }

    public int size() {
        return data.length;
    }

}
