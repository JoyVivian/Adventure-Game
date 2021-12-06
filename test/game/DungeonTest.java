package game;

import bfs.Bfs;
import bfs.BfsImpl;
import graph.Graph;
import unionfind.Uf;
import unionfind.UfImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class is used to test Dungeon to
 * find whether a dungeon with wrapping or non-wrapping
 * or with some specific connectivity works.
 */
public class DungeonTest {
  private Dungeon noWrapWithConZeroPerFull;
  private Dungeon noWrapWithConNonZeroPerHalf;
  private Dungeon wrapWithConZeroPerFull;
  private Dungeon wrapWithConNonZeroPerHalf;

  @org.junit.Before
  public void setUp() throws IllegalArgumentException {
    noWrapWithConZeroPerFull = new DungeonImpl(5, 5, 0, false, 100, 5, false);
    noWrapWithConNonZeroPerHalf = new DungeonImpl(5, 5, 3, false, 50, 5, false);

    wrapWithConZeroPerFull = new DungeonImpl(8, 8, 0, true, 100, 4, false);
    wrapWithConNonZeroPerHalf = new DungeonImpl(8, 8, 11, true, 50, 5, false);
  }

  @org.junit.Test
  public void printDungeon() {
    String expected = "The graph is: GraphImpl{vertexNum=25, edgeNum=24}\n" +
            "adjacentList={1=[2, 6], 2=[1, 3, 7], 3=[2, 4, 8], 4=[3, 5, 9], 5=[4, 10]," +
            " 6=[1, 11], 7=[2, 12], 8=[3, 13], 9=[4, 14], 10=[5, 15], " +
            "11=[6, 16]," +
            " 12=[7, 17], 13=[8, 18], 14=[9, 19], 15=[10, 20], 16=[11, 21]," +
            " 17=[12, 22]," +
            " 18=[13, 23], 19=[14, 24], 20=[15, 25], 21=[16], 22=[17], 23=[18]," +
            " 24=[19], 25=[20]}}\n" +
            "The information on this location is: \n" +
            "Location{col=1, row=1, isCave=false, treasureList=[], " +
            "otyughList=[], " +
            "arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], " +
            "arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh]," +
            " arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[Otyugh]," +
            " arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=1, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=2, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=2, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=2, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=2, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=2, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=4, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=5, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=5, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=5, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=5, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=5, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh, Otyugh], " +
            "arrowNum=1}\n" +
            "\n";

    assertEquals(expected, noWrapWithConZeroPerFull.printDungeon());

    expected = "The graph is: GraphImpl{vertexNum=25, edgeNum=27}\n" +
            "adjacentList={1=[2, 6], 2=[1, 3, 7], 3=[2, 4, 8], 4=[3, 5, 9]," +
            " 5=[4, 10], 6=[1, 11, 7], 7=[2, 12, 6, 8], 8=[3, 13, 7, 9], " +
            "9=[4, 14, 8], 10=[5, 15], 11=[6, 16], 12=[7, 17], " +
            "13=[8, 18], 14=[9, 19], 15=[10, 20], 16=[11, 21], " +
            "17=[12, 22], 18=[13, 23], 19=[14, 24], 20=[15, 25], " +
            "21=[16], 22=[17], 23=[18], 24=[19], 25=[20]}}\n" +
            "The information on this location is: \n" +
            "Location{col=1, row=1, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=1, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=2, isCave=true, treasureList=[Diamond], " +
            "otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=2, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=2, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=2, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=1, row=4, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=2, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=3, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=4, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=4, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=1, row=5, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=2, row=5, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=3, row=5, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=4, row=5, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=5, isCave=true, treasureList=[], " +
            "otyughList=[Otyugh, Otyugh]," +
            " arrowNum=0}\n" +
            "\n";

    assertEquals(expected, noWrapWithConNonZeroPerHalf.printDungeon());

    expected = "The graph is: GraphImpl{vertexNum=64, edgeNum=79}\n" +
            "adjacentList={1=[2, 9, 8, 57], 2=[1, 3, 10, 58], " +
            "3=[2, 4, 11, 59]," +
            " 4=[3, 5, 12, 60], 5=[4, 6, 13, 61], 6=[5, 7, 14, 62], " +
            "7=[6, 8, 15, 63]," +
            " 8=[7, 16, 1, 64], 9=[1, 17, 16], 10=[2, 18], 11=[3, 19], " +
            "12=[4, 20], " +
            "13=[5, 21], 14=[6, 22], 15=[7, 23], 16=[8, 24, 9], " +
            "17=[9, 25, 24]," +
            " 18=[10, 26], 19=[11, 27], 20=[12, 28], 21=[13, 29]," +
            " 22=[14, 30], " +
            "23=[15, 31], 24=[16, 32, 17], 25=[17, 33, 32], " +
            "26=[18, 34], 27=[19, 35], " +
            "28=[20, 36], 29=[21, 37], 30=[22, 38], 31=[23, 39]," +
            " 32=[24, 40, 25], " +
            "33=[25, 41, 40], 34=[26, 42], 35=[27, 43], " +
            "36=[28, 44], 37=[29, 45], " +
            "38=[30, 46], 39=[31, 47], 40=[32, 48, 33], " +
            "41=[33, 49, 48], 42=[34, 50], " +
            "43=[35, 51], 44=[36, 52], 45=[37, 53], 46=[38, 54]," +
            " 47=[39, 55], " +
            "48=[40, 56, 41], 49=[41, 57, 56], 50=[42, 58], " +
            "51=[43, 59], 52=[44, 60], " +
            "53=[45, 61], 54=[46, 62], 55=[47, 63]," +
            " 56=[48, 64, 49], 57=[49, 64, 1], " +
            "58=[50, 2], 59=[51, 3], 60=[52, 4], 61=[53, 5]," +
            " 62=[54, 6], 63=[55, 7], " +
            "64=[56, 57, 8]}}\n" +
            "The information on this location is: \n" +
            "Location{col=1, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh], arrowNum=1}\n"
            +
            "\n" +
            "Location{col=3, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[Otyugh], arrowNum=1}\n"
            +
            "\n" +
            "Location{col=4, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=2, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=2, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=2, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=2, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=2, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=2, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=2, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=3, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=3, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=3, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=4, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=4, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=4, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=4, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=5, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=5, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=5, isCave=true, treasureList=[Diamond]," +
            " otyughList=[Otyugh, Otyugh]," +
            " arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=6, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=6, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=6, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=6, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=6, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=7, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=7, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=7, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=7, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=8, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=8, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=8, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=8, isCave=false, treasureList=[]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=8, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n";

    assertEquals(expected, wrapWithConZeroPerFull.printDungeon());

    expected = "The graph is: GraphImpl{vertexNum=64, edgeNum=90}\n" +
            "adjacentList={1=[2, 9, 8, 57], 2=[1, 3, 10, 58], " +
            "3=[2, 4, 11, 59], " +
            "4=[3, 5, 12, 60], 5=[4, 6, 13, 61], 6=[5, 7, 14, 62], " +
            "7=[6, 8, 15, 63], " +
            "8=[7, 16, 1, 64], 9=[1, 17, 10, 16], 10=[2, 18, 9, 11], " +
            "11=[3, 19, 10, 12]," +
            " 12=[4, 20, 11, 13], 13=[5, 21, 12, 14], 14=[6, 22, 13, 15]," +
            " 15=[7, 23, 14, 16]," +
            " 16=[8, 24, 15, 9], 17=[9, 25, 18, 24], 18=[10, 26, 17, 19], " +
            "19=[11, 27, 18, 20], " +
            "20=[12, 28, 19, 21], 21=[13, 29, 20], 22=[14, 30], 23=[15, 31]," +
            " 24=[16, 32, 17]," +
            " 25=[17, 33, 32], 26=[18, 34], 27=[19, 35], 28=[20, 36], " +
            "29=[21, 37], 30=[22, 38], " +
            "31=[23, 39], 32=[24, 40, 25], 33=[25, 41, 40], 34=[26, 42]," +
            " 35=[27, 43], 36=[28, 44]," +
            " 37=[29, 45], 38=[30, 46], 39=[31, 47], 40=[32, 48, 33], " +
            "41=[33, 49, 48], 42=[34, 50]," +
            " 43=[35, 51], 44=[36, 52], 45=[37, 53], 46=[38, 54], " +
            "47=[39, 55], 48=[40, 56, 41], " +
            "49=[41, 57, 56], 50=[42, 58], 51=[43, 59], 52=[44, 60]," +
            " 53=[45, 61], 54=[46, 62], 55=[47, 63]," +
            " 56=[48, 64, 49], 57=[49, 64, 1], 58=[50, 2], 59=[51, 3]," +
            " 60=[52, 4], 61=[53, 5], 62=[54, 6], " +
            "63=[55, 7], 64=[56, 57, 8]}}\n" +
            "The information on this location is: \n" +
            "Location{col=1, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[Otyugh], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=1, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=1, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=2, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=2, isCave=true, treasureList=[Diamond], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=2, isCave=true, treasureList=[Diamond]," +
            " otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=3, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=3, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=3, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=3, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=3, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=3, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=3, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=4, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=2, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=3, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=4, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=5, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=6, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=7, row=4, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=8, row=4, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=1}\n" +
            "\n" +
            "Location{col=1, row=5, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=2, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=3, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=4, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=6, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=7, row=5, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=8, row=5, isCave=true, treasureList=[], " +
            "otyughList=[Otyugh, Otyugh], arrowNum=0}\n"
            +
            "\n" +
            "Location{col=1, row=6, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=2, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=3, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=4, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=6, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=7, row=6, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=8, row=6, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=1, row=7, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=2, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=3, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=4, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=6, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=7, row=7, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=8, row=7, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=1, row=8, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=2, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=3, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=4, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=5, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=6, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=7, row=8, isCave=false, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n" +
            "Location{col=8, row=8, isCave=true, treasureList=[], " +
            "otyughList=[], arrowNum=0}\n" +
            "\n";

    assertEquals(expected, wrapWithConNonZeroPerHalf.printDungeon());
  }

  @org.junit.Test
  public void getStart() {
    assertEquals(2, noWrapWithConZeroPerFull.getStart().
            getId(noWrapWithConNonZeroPerHalf.getGraph().getColNum()));

    assertEquals(2, noWrapWithConNonZeroPerHalf.getStart().
            getId(noWrapWithConNonZeroPerHalf.getGraph().getColNum()));

    assertEquals(1, wrapWithConZeroPerFull.getStart().
            getId(wrapWithConZeroPerFull.getGraph().getColNum()));

    assertEquals(1, wrapWithConNonZeroPerHalf.getStart().
            getId(wrapWithConNonZeroPerHalf.getGraph().getColNum()));
  }

  @org.junit.Test
  public void getEnd() {
    assertEquals(25, noWrapWithConZeroPerFull.getEnd().
            getId(noWrapWithConZeroPerFull.getGraph().getColNum()));

    assertEquals(25, noWrapWithConNonZeroPerHalf.getEnd().
            getId(noWrapWithConZeroPerFull.getGraph().getColNum()));

    assertEquals(40, wrapWithConZeroPerFull.getEnd().
            getId(wrapWithConZeroPerFull.getGraph().getColNum()));

    assertEquals(40, wrapWithConNonZeroPerHalf.getEnd().
            getId(wrapWithConNonZeroPerHalf.getGraph().getColNum()));
  }

  @org.junit.Test
  public void testIAE() {
    try {
      new DungeonImpl(-1, -2, 0,
              false, 20, 1, true);
      fail("The instance above should throw an IAE.");
    } catch (Exception e) {
      //test successful.
    }

    try {
      new DungeonImpl(2, 3, -4,
              false, 20, 1, true);
      fail("The instance above should throw an IAE.");
    } catch (Exception e) {
      //test successful.
    }

    try {
      new DungeonImpl(2, 3, 0,
              false, -10, 1, true);
      fail("The instance above should throw an IAE");
    } catch (Exception e) {
      //test successful.
    }
  }

  @org.junit.Test
  public void testReachable() {
    Graph graph = noWrapWithConZeroPerFull.getGraph();
    Uf uf = new UfImpl(graph.getVnum());
    for (int i = 1; i <= graph.getVnum(); i++) {
      for (int j = 1; j <= graph.getVnum() && j != i; j++) {
        if (!uf.connected(i, j)) {
          uf.union(i, j);
        }
      }
    }

    boolean allReachable = true;
    for (int i = 1; i <= graph.getVnum(); i++) {
      for (int j = 1; j <= graph.getVnum(); j++) {
        if (!uf.connected(i, j)) {
          allReachable = false;
        }
      }
    }

    assertTrue(allReachable);
  }

  @org.junit.Test
  public void testPath() {
    Graph graph = noWrapWithConZeroPerFull.getGraph();

    Location startLoc = noWrapWithConZeroPerFull.getStart();
    Location endLoc = noWrapWithConZeroPerFull.getEnd();

    int colNum = graph.getColNum();
    Bfs bfs = new BfsImpl(graph, startLoc.getId(colNum));
    int pathLen = bfs.pathLen(endLoc.getId(colNum));

    assertTrue(pathLen >= 5);
  }

  @org.junit.Test
  public void testTreasures() {
    int actualCaveNum = 0;
    int expectTreasureCaveNum = 0;
    int actualTreasureCaveNum = 0;

    Graph graph = noWrapWithConZeroPerFull.getGraph();
    for (int i = 1; i <= graph.getVnum(); i++) {
      Location location = graph.getLocation(i);
      if (location.getIsCave()) {
        actualCaveNum++;

        if (!location.getTreasureList().isEmpty()) {
          actualTreasureCaveNum++;
        }
      }
    }


    expectTreasureCaveNum = (int) Math.ceil(100 / 100.0 * actualCaveNum);

    assertEquals(expectTreasureCaveNum, actualTreasureCaveNum);
  }

}

