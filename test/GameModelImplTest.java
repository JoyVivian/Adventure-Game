import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import game.Danger;
import game.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameModelImplTest {
  private GameModel gameModel;
  private GameModel gameModelWithConnectivity;
  private GameModel gameModelWrapping;
  private GameModel getGameModelWrappingWithConnectivity;

  @Before
  public void setUp() throws Exception {
    gameModel = new GameModelImpl(5, 5, 0, false, 100, 10,false);
    gameModelWithConnectivity = new GameModelImpl(5, 5, 2, false, 50, 4, false);
    gameModelWrapping = new GameModelImpl(8, 8, 0, true, 100, 6, false);
    getGameModelWrappingWithConnectivity = new GameModelImpl(8, 8, 2, true, 50, 10, false);
  }

//  @Test
//  public void getNextDir() {
//    List<Direction> actual = gameModel.getNextDir(gameModel.getPlayer().getLocation());
//
//    List<Direction> expected = new ArrayList<>();
//    expected.add(Direction.West);
//    expected.add(Direction.East);
//    expected.add(Direction.South);
//
//    assertEquals(expected, actual);
//
//    actual = gameModelWithConnectivity.getNextDir(gameModelWithConnectivity.getPlayer().getLocation());
//
//    expected = new ArrayList<>();
//    expected.add(Direction.West);
//    expected.add(Direction.East);
//    expected.add(Direction.South);
//
//    assertEquals(expected, actual);
//
//    actual = gameModelWrapping.getNextDir(gameModelWrapping.getPlayer().getLocation());
//
//    expected = new ArrayList<>();
//
//    expected.add(Direction.East);
//    expected.add(Direction.South);
//    expected.add(Direction.West);
//    expected.add(Direction.North);
//
//    assertEquals(actual, expected);
//  }

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
    assertTrue(gameModel.slayOty(Direction.South, 1));
    assertTrue(!gameModel.slayOty(Direction.South, 3));
    assertTrue(gameModel.slayOty(Direction.East, 3));
    assertTrue(gameModel.slayOty(Direction.West, 1));
    assertTrue(!gameModel.slayOty(Direction.West, 2));
  }

  @Test
  public void isEaten() {
    assertTrue(!gameModel.isEaten(false));
    gameModel.move(Direction.East);
    assertTrue(gameModel.isEaten(false));
  }


  @Test
  public void getDangerType() {
    assertEquals(Danger.MORESMELL.toString(), gameModel.getDangerType());

    gameModel.move(Direction.South);
    assertEquals(Danger.LESSSMELL.toString(), gameModel.getDangerType());

    gameModel.move(Direction.North);
    gameModel.move(Direction.East);

    for (int i = 0; i < 3; i++) {
      gameModel.move(Direction.South);
    }
    assertEquals(Danger.NODANGER.toString(), gameModel.getDangerType());
  }
}