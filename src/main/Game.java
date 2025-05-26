package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> players;
    private final int MAX_PLAYERS = 10;
    private final int START_CHIP_COUNT = 25_000;
    private Round round;
    public Game() throws Exception {
        AddPlayers();
        StartGame();
    }

    private void AddPlayers() throws IOException {
        players = new ArrayList<Player>();

        Scanner sc = getScanner();
        for(int i = 0; i < MAX_PLAYERS; i++){
            if ( i > 1){
                System.out.println("Deseja inserir novo jogador? S ou N");
                String input = sc.nextLine();
                if(input.equals("N")){
                    break;
                }
            }
            System.out.print(String.format("Digite o nome do jogador %s: ", i+1));
            String name = sc.nextLine();
            Player newPlayer  = new Player(name, START_CHIP_COUNT);
            players.add(newPlayer);
            System.out.printf("Jogador %s entrou na partida.%n", newPlayer.getName());
        }
    }

    private void StartGame() throws Exception {
        round = new Round(players);
        round.startRound();
        round.printPlayersHand();
        round.preFlop();
        round.flop();
        System.out.println("=========================================================");
        round.turn();
        System.out.println("=========================================================");
        round.river();
        System.out.println("=========================================================");
        round.revealCards();
    }

    private Scanner getScanner() throws IOException {
        if( Program.MOCK){
            if(Files.notExists(Path.of("main/players.txt"))) {
                Path file = Files.createFile(Path.of("main/players.txt"));
                List<String> names = new ArrayList<String>();
                names.add("Renan");
                names.add("Luis");
                names.add("S");
                names.add("Fernando");
                names.add("S");
                names.add("João");
                names.add("N");

                Files.write(file, names);
            }
            Scanner sc = null;
            try {
                FileReader fr = new FileReader("main/players.txt");
                return new Scanner(fr);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            return new Scanner(System.in);
        }
    }
}
