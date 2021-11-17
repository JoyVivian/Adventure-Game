import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import game.Direction;

import static org.junit.Assert.assertEquals;

public class IGameModelTest {
  private IGameModel gameModel;

  @Before
  public void setUp() throws Exception {
    gameModel = new GameModel(5, 5, 0, false, 100, 10,false);
  }

  @Test
  public void getNextDir() {
    List<Direction> actual = gameModel.getNextDir(gameModel.getPlayer().getLocation());

    List<Direction> expected = new ArrayList<>();
    expected.add(Direction.West);
    expected.add(Direction.East);
    expected.add(Direction.South);

    assertEquals(expected, actual);
  }

  @Test
  public void move() {
    //TODO: How to complete this test.
    Direction direction = Direction.West;
    System.out.println(gameModel.getCurLocation());
    gameModel.move(direction);
    System.out.println(gameModel.getCurLocation());
  }

  @Test
  public void slayOty() {
    System.out.println(gameModel.slayOty(Direction.South, 3));
    System.out.println(gameModel.slayOty(Direction.East, 3));
  }
}