import framework.core.GameBoard;
import framework.patterns.builder.BoardBuilder;
import framework.patterns.builder.GameBoardDirector;
import games.jungle.patterns.builder.JungleBoardBuilder;

public class App {
    public static void main(String[] args) {
        //Board builder
        BoardBuilder builder = new JungleBoardBuilder();
        GameBoardDirector director = new GameBoardDirector(builder);
        GameBoard board = director.construct(7, 9);
    }
}
