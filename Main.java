
public class Main {

    public static void main(String[] args) {
        Table A = new Table("tableA.csv");
        Table B = new Table("tableB.csv");
        Table C = JoinOperator.join(A, B, "email");

        // Sanity Check
        if (C.toString().equals((new Table("expected.csv")).toString())) {
            System.out.println("Sanity Check Passed");
        } else {
            System.out.println("Sanity Check Failed");
        }
    }

}
