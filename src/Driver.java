import controller.Controller;
import controller.ControllerImpl;
import controller.GuiController;
import controller.GuiControllerImpl;
import model.GameModel;
import model.GameModelImpl;

import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.in;
import static java.lang.System.out;

/**
 * This is a Driver class that shows how the model works.
 */
public class Driver {
  /**
   * The main function that shows how player and dungeon work.
   *
   * @param args 5 command-line arguments. 0: The number of row.
   *             1: The number of column. 2: The connectivity of the
   *             dungeon. 3: whether the dungeon wrapped. 4: how many
   *             caves should be assigned treasures.
   */
  public static void main(String[] args) {
    int rowNum = 0;
    int colNum = 0;
    int interconnectivity = 0;
    boolean isWrap = true;
    int percentage = 0;
    int otyNum = 0;

    if (args.length == 0) {
      GameModel model = new GameModelImpl(5, 5, 0, false, 20, 5, true);
      GuiController controller = new GuiControllerImpl(model);
      controller.playGame(model);
    } else {
      out.println("Please input the rowNum, colNum, " +
              "Interconnectivity, isWrap, percentage of treasure." +
              "Be sure to enter both rowNum and colNum greater than 5.");
      if (args.length > 0) {
        try {
          rowNum = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
          err.println("Argument" + args[0] + " must be an integer.");
          exit(1);
        }

        try {
          colNum = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
          err.println("Argument" + args[1] + " must be an integer.");
          exit(1);
        }

        try {
          interconnectivity = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
          err.println("Argument" + args[2] + " must be an integer.");
          exit(1);
        }

        try {
          isWrap = Boolean.parseBoolean(args[3]);
        } catch (NumberFormatException e) {
          err.println("Argument" + args[3] + " must be an integer.");
          exit(1);
        }

        try {
          percentage = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
          err.println("Argument" + args[4] + " must be an integer.");
          exit(1);
        }

        try {
          otyNum = Integer.parseInt(args[5]);
        } catch (NumberFormatException e) {
          err.println("Argument" + args[5] + "must be an integer.");
          exit(1);
        }
      }

      try {
        GameModel gameModel = new GameModelImpl(rowNum, colNum,
                interconnectivity, isWrap, percentage, otyNum, true);
        Readable reader = new InputStreamReader(in);
        Controller controller = new ControllerImpl(reader, out);
        controller.playGame(gameModel, true);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


}
