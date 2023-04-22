import pushLabel.FordForkerson;
import pushLabel.MaxFlow;
import pushLabel.RandomCreator;

public class Main {

    public static void main(String[] args){
        RandomCreator creator = new RandomCreator(0);
        MaxFlow maxFlow = creator.generateMap(5,5,10);
//        maxFlow.removeEdge(21,26);
//        maxFlow.removeEdge(22,26);
//        maxFlow.removeEdge(23,26);
//        maxFlow.removeEdge(24,26);
//        maxFlow.removeEdge(25,26);
        maxFlow.maxFlow(0,26, new FordForkerson());
    }
}
