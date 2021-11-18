import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import game.Direction;
import game.Treasure;
import jdk.jshell.spi.ExecutionControlProvider;

public class ControllerImpl implements Controller {
  private final Appendable out;
  private final Readable in;

  ControllerImpl(Readable in, Appendable out) throws IllegalArgumentException {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable cannot be null.");
    }
    this.in = in;
    this.out = out;
  }

  @Override
  public void playGame(GameModel gameModel) throws IOException {
    Objects.requireNonNull(gameModel);

    Scanner scan = new Scanner(this.in);
    while (!gameModel.isEnd()) {
      if (gameModel.isEaten(true)) {
        out.append("Chomp, chomp, chomp, you are eaten by an Otyugh!\n");
        out.append("Better luck next time");
      }

      out.append(gameModel.getDangerType());
      out.append(String.format("You are in a %s\n", gameModel.isCave() ? "cave" : "tunnel"));
      out.append(String.format("Door leads to: %s\n", gameModel.getNextDirs()));
      if (gameModel.getTreasure() != "") {
        out.append(String.format("You find %s here\n", gameModel.getTreasure()));
      }
      if (gameModel.getArrowNum() != 0) {
        out.append(String.format("You find %d arrow here\n", gameModel.getArrowNum()));
      }
      out.append(String.format("Move, Pickup, or Shoot(M-P-S)?\n"));
      String option = scan.next();
      switch (option) {
        case "M":
          while (true) {
            out.append("Where to? ");
            String dir = scan.next();
            if (dir.equals("N") && gameModel.getNextDirs().contains("N")) {
              gameModel.move(Direction.North);
              break;
            } else if (dir.equals("E") && gameModel.getNextDirs().contains("E")) {
              gameModel.move(Direction.East);
              break;
            } else if (dir.equals("S") && gameModel.getNextDirs().contains("S")) {
              gameModel.move(Direction.South);
              break;
            } else if (dir.equals("W") && gameModel.getNextDirs().contains("W")) {
              gameModel.move(Direction.West);
              break;
            } else {
              out.append("You entered a wrong direction. Please enter a direction according to " +
                      "the prompt.\n");
            }
          }
          break;
        case "P":
          String treasures = gameModel.getTreasure();
          int arrowNum = gameModel.getArrowNum();
          while (true) {
            out.append("What? ");
            option = scan.next();
            if (option.equals("Rubie") && treasures.contains("Rubie")) {
              gameModel.pickUpTreasure(Treasure.RUBIE);
              out.append("You picked up a Rubie.\n");
              break;
            } else if (option.equals("Diamond") && treasures.contains("Diamond")) {
              gameModel.pickUpTreasure(Treasure.DIAMOND);
              out.append("You picked up a Diamond.\n");
              break;
            } else if (option.equals("Sapphire") && treasures.contains("Sapphire")) {
              gameModel.pickUpTreasure(Treasure.SAPPHIRE);
              out.append("You picked up a Sapphire\n");
              break;
            } else if (option.equals("arrow") && arrowNum > 0) {
              out.append(String.format("You picked up %d arrows.\n", gameModel.getArrowNum()));
              gameModel.pickUpArrows();
              break;
            } else if (option.equals("q")) {
              break;
            } else {
              out.append("What you entered is not in the location. Please enter according to the prompt." +
                      "Enter q to give a pickup.\n");
            }
          }
          break;
        case "S":
          arrowNum = gameModel.getPlayArrowNum();
          if (arrowNum == 0) {
            out.append("You are run out of your arrow. Please explore to find more.\n");
          } else {
            gameModel.decreaseArrows();
            int distance = 0;
            while (true) {
              out.append("No. of caves(1-5)?\n");
              String dis = scan.next();
              try {
                distance = Integer.parseInt(dis);
                break;
              } catch (Exception e) {
                out.append("Please enter an integer according to the prompt.\n");
              }
            }

            Direction direction;
            out.append("Where to? ");
            while (true) {
              String dir = scan.next();
              if (dir.equals("N") && gameModel.getNextDirs().contains("N")) {
                if (gameModel.slayOty(Direction.North, distance)) {
                  out.append("You hear a great howl in the distance.\n");
                } else {
                  out.append("You shoot an arrow into the darkness.\n");
                }
                break;
              } else if (dir.equals("E") && gameModel.getNextDirs().contains("E")) {
                if (gameModel.slayOty(Direction.East, distance)) {
                  out.append("You hear a great howl in the distance.\n");
                } else {
                  out.append("You shoot an arrow into the darkness.\n");
                }
                break;
              } else if (dir.equals("S") && gameModel.getNextDirs().contains("S")) {
                if (gameModel.slayOty(Direction.South, distance)) {
                  out.append("You hear a great howl in the distance.\n");
                } else {
                  out.append("You shoot an arrow into the darkness.\n");
                }
                break;
              } else if (dir.equals("W") && gameModel.getNextDirs().contains("W")) {
                if (gameModel.slayOty(Direction.West, distance)) {
                  out.append("You hear a great howl in the distance.\n");
                } else {
                  out.append("You shoot an arrow into the darkness.\n");
                }
                break;
              } else {
                out.append("You entered a wrong direction. Please enter a direction according to " +
                        "the prompt.\n");
                out.append("Where to? ");
              }
            }
          }

          break;
        default:
          out.append("You can only choose M, P or S!\n");
          break;
      }
    }
    if (gameModel.isEaten(true)) {
      out.append("Chomp, chomp, chomp, you are eaten by an Otyugh!\n");
      out.append("Better luck next time");
      System.exit(0);
    } else {
      out.append("Congratulations! You win!");
    }
  }
}
