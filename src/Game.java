import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private final int MAX_PLAYERS = 10;
    private final int START_CHIP_COUNT = 25_000;
    public void StartGame(){
        players = new ArrayList<Player>();
        for(int i = 0; i < MAX_PLAYERS; i++){
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine();
            Player newPlayer  = new Player(name, START_CHIP_COUNT);
        }
    }
}
