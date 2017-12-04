package A6_Dijkstra;

public class DiGraphPlayground {

  public static void main (String[] args) {
  
      // thorough testing is your responsibility
      //
      // you may wish to create methods like 
      //    -- print
      //    -- sort
      //    -- random fill
      //    -- etc.
      // in order to convince yourself your code is producing
      // the correct behavior
    exTest();
    }
  
    public static void exTest(){
      DiGraph d = new DiGraph();
      //test case 1
//      d.addNode(0, "R");
//      d.addNode(1, "D");
//      d.addNode(2, "H");
//      d.addNode(3, "CH");
//      d.addNode(4, "G");
//      d.addNode(5, "CB");
//      d.addNode(6, "CA");
//      d.addNode(7, "P");
//      d.addNode(8, "S");
//      d.addNode(9, "LA");
//      
//      d.addEdge(0, "R", "D", 14, null);
//      d.addEdge(1, "D", "H", 9, null);
//      d.addEdge(2, "CH", "G", 20, null);
//      d.addEdge(3, "CH", "CB", 1, null);
//      d.addEdge(4, "CB", "CA", 32, null);
//      d.addEdge(5, "CA", "R", 3, null);
//      d.addEdge(6, "P", "CA", 17, null);
//      d.addEdge(7, "P", "S", 15, null);
//      d.addEdge(8, "S", "LA", 3012, null);
      
      
      
      d.addNode(0, "h");
      d.addNode(1, "u");
      d.addNode(2, "s");
      d.addNode(3, "k");
      d.addNode(4, "y");
      d.addNode(5, "z");
      
      d.addEdge(0, "h", "u", 5, null);
      d.addEdge(1, "h", "s", 10, null);
      d.addEdge(2, "u", "k", 7, null);     
      d.addEdge(3, "s", "y", 5, null);
      d.addEdge(4, "y", "z", 5, null);  
 
      
      //test 3
//      d.addNode(0, "b");
//      d.addNode(1, "r");
//      d.addNode(2, "a");
//      d.addNode(3, "n");
//      d.addNode(4, "d");
//      d.addNode(5, "o");
//     
//     d.addEdge(0, "b", "r", 1, null);
   
    
      
      
      
      System.out.println("numEdges: "+d.numEdges());
      System.out.println("numNodes: "+d.numNodes());
      printSPI(d.shortestPath("h"));
      
    }
    public static void printTOPO(String[] toPrint){
      System.out.print("TOPO Sort: ");
      for (String string : toPrint) {
      System.out.print(string+" ");
    }
      System.out.println();
    }

    public static void printSPI(ShortestPathInfo[] spi){
        System.out.print("SPI Sort: ");
        System.out.println();
        for (int i = 0; i < spi.length; i++) {
        System.out.print("source is:"+ spi[i].getDest() + " ");
        System.out.print("Weight is:" + (int) spi[i].getTotalWeight() + " ");
        System.out.println();
      }
        System.out.println();
      }

  
}

