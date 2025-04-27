public class Program {
    public static boolean MOCK = false;
    public static void main(String[] args) throws Exception {
        if (args[0].equals("mock")) {
            MOCK = true;
        }
        Game game = new Game();
    }
}
