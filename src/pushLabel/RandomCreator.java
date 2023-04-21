package pushLabel;

import java.util.Random;

public class RandomCreator {
    Random random;

    public RandomCreator(){
        random = new Random();
    }

    public RandomCreator(int seed){
        random = new Random(seed);
    }

    public MaxFlow generateMap(int level, int nodes, int range){
        if(level <=0 || nodes <=0){
            throw new IllegalArgumentException("invalid parameter");
        }
        MaxFlow maxFlow = new MaxFlow();
        for(int i=0;i<level*nodes+2;i++){
            maxFlow.addNode(i, ""+i);
        }
        for(int i=0;i<nodes;i++){
            maxFlow.addEdge(0,1+i,1+random.nextInt(range));
            maxFlow.addEdge(level*nodes-i,1+level*nodes,1+random.nextInt(range));
        }
        for(int i=0;i<level-1;i++){
            for(int j=0;j<nodes;j++){
                for(int k=0;k<nodes;k++){
                    maxFlow.addEdge(1+i*nodes+j,1+(i+1)*nodes+k,1+random.nextInt(range));
                }
            }
        }
        return maxFlow;
    }
}
