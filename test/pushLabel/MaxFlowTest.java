package pushLabel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxFlowTest {

    @Test
    @DisplayName("normal node add")
    void addNode() {
        MaxFlow maxFlow = new MaxFlow();
        maxFlow.addNode(0,"a");
        maxFlow.addNode(1,"b");
        maxFlow.addNode(2,"c");
        maxFlow.addNode(3,"d");
        assertEquals("a(0)\n" +
                "b(1)\n" +
                "c(2)\n" +
                "d(3)\n", maxFlow.toString());
    }

    @Test
    @DisplayName("error node add")
    void addNode2() {
        MaxFlow maxFlow = new MaxFlow();
        maxFlow.addNode(0,"a");
        assertThrows(IllegalArgumentException.class, ()->maxFlow.addNode(0,"1"));
        assertThrows(IllegalArgumentException.class, ()->maxFlow.addNode(1,"a"));
    }

    @Test
    void addEdge() {
        MaxFlow maxFlow = new MaxFlow();
        maxFlow.addNode(0,"a");
        maxFlow.addNode(1,"b");
        maxFlow.addNode(2,"c");
        maxFlow.addNode(3,"d");
        maxFlow.addEdge(0,1,100);
        maxFlow.addEdge(0,2,100);
        maxFlow.addEdge(1,3,100);
        maxFlow.addEdge(2,3,100);
        maxFlow.addEdge(1,2,1);
        assertEquals("a(0)\n" +
                "b(1)\n" +
                "c(2)\n" +
                "d(3)\n" +
                "a(0) -> b(1) : 100\n" +
                "a(0) -> c(2) : 100\n" +
                "b(1) -> c(2) : 1\n" +
                "b(1) -> d(3) : 100\n" +
                "c(2) -> d(3) : 100\n",maxFlow.toString());
        assertThrows(IllegalArgumentException.class, ()->maxFlow.addEdge(0,0,100));
        assertThrows(IllegalArgumentException.class, ()->maxFlow.addEdge(1,0,100));
    }

    @Test
    @DisplayName("small map")
    void maxFlow01(){
        MaxFlow maxFlow = new MaxFlow();
        maxFlow.addNode(0,"a");
        maxFlow.addNode(1,"b");
        maxFlow.addNode(2,"c");
        maxFlow.addNode(3,"d");
        maxFlow.addEdge(0,1,100);
        maxFlow.addEdge(0,2,100);
        maxFlow.addEdge(1,3,100);
        maxFlow.addEdge(2,3,100);
        maxFlow.addEdge(1,2,1);
        assertEquals(200, maxFlow.maxFlow(0,3,new FordForkerson()));
        assertEquals(200, maxFlow.maxFlow(0,3,new PushLabel()));

    }

    @Test
    @DisplayName("big map")
    void maxFlow(){
        RandomCreator creator = new RandomCreator(0);
        MaxFlow maxFlow = creator.generateMap(10,10,100);
        assertEquals(49494,maxFlow.maxFlow(0,10000+1, new FordForkerson()));
        assertEquals(49494,maxFlow.maxFlow(0,10000+1, new PushLabel()));
    }
}