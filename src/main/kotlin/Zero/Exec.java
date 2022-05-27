package Zero;

public class Exec {

    public static void main(String[] args) {

        ReDo(args);
    }

    private static void ReDo (String[] args) {

        try {

            Main.Companion.main(args);

        } catch (Exception e) {
            e.printStackTrace();
            ReDo(args);
        }
    }

}
