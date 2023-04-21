package pushLabel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomCreatorTest {

    @Test
    void generateMap() {
        RandomCreator creator = new RandomCreator(0);
        MaxFlow maxFlow = creator.generateMap(2,2,1000);
        assertEquals("0(0)\n" +
                "1(1)\n" +
                "2(2)\n" +
                "3(3)\n" +
                "4(4)\n" +
                "5(5)\n" +
                "0(0) -> 1(1) : 361\n" +
                "0(0) -> 2(2) : 30\n" +
                "1(1) -> 3(3) : 516\n" +
                "1(1) -> 4(4) : 54\n" +
                "2(2) -> 3(3) : 492\n" +
                "2(2) -> 4(4) : 762\n" +
                "3(3) -> 5(5) : 448\n" +
                "4(4) -> 5(5) : 949\n",maxFlow.toString());
    }
}