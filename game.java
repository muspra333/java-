import java.util.*;

class Player {
    private String name;
    private String color;
    private int position;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.position = 0;
    }

    public int rollDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public void move(int steps) {
        this.position += steps;
        if (this.position > 100) {
            this.position = 100;
        }
    }

    public boolean hasWon() {
        return this.position == 100;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }
}

class SnakeLadderGame {
    private List<Player> players;
    private Map<Integer, Integer> snake;
    private Map<Integer, Integer> ladder;

    public SnakeLadderGame(List<Player> players) {
        this.players = players;
        this.snake = new HashMap<>();
        this.ladder = new HashMap<>();
        initializeSnakesAndLadders();
    }

    private void initializeSnakesAndLadders() {
        // Snakes positions
        snake.put(36, 6);
        snake.put(32, 10);
        snake.put(48, 26);
        snake.put(62, 18);
        snake.put(88, 24);
        snake.put(95, 56);
        snake.put(97, 78);

        // Ladders positions
        ladder.put(1, 38);
        ladder.put(4, 14);
        ladder.put(9, 31);
        ladder.put(21, 42);
        ladder.put(28, 74);
        ladder.put(51, 67);
        ladder.put(71, 91);
        ladder.put(80, 99);
    }

    public void playTurn(Player player) {
        System.out.println(player.getName() + "'s turn.");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to roll the dice...");
        scanner.nextLine(); // Waiting for player to roll

        int diceValue = player.rollDice();
        System.out.println(player.getName() + " rolled a " + diceValue + ".");

        player.move(diceValue);
        System.out.println(player.getName() + " moved to position " + player.getPosition() + ".");

        // Check if the player lands on a ladder or a snake
        if (ladder.containsKey(player.getPosition())) {
            int newPosition = ladder.get(player.getPosition());
            System.out.println(player.getName() + " climbed a ladder to position " + newPosition + ".");
            player.move(newPosition - player.getPosition());
            System.out.println(player.getName() + " moved to position " + player.getPosition() + ".");
        } else if (snake.containsKey(player.getPosition())) {
            int newPosition = snake.get(player.getPosition());
            System.out.println(player.getName() + " bitten by a snake and moved to position " + newPosition + ".");
            player.move(newPosition - player.getPosition());
            System.out.println(player.getName() + " moved to position " + player.getPosition() + ".");
        }

        if (player.hasWon()) {
            System.out.println("Congratulations! " + player.getName() + " has won the game!");
        }
    }

    public void startGame() {
        boolean gameWon = false;
        while (!gameWon) {
            for (Player player : players) {
                playTurn(player);
                if (player.hasWon()) {
                    gameWon = true;
                    break;
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Player 1", "Red"));
        players.add(new Player("Player 2", "Blue"));

        SnakeLadderGame game = new SnakeLadderGame(players);
        game.startGame();
    }
}
