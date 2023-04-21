package pushLabel;

public abstract class MaxFlowSolution {
    int[][] original;
    int[][] map;
    int n;
    String name;

    protected MaxFlowSolution(String name){
        this.name = name;
    }

    public int getMaxFlow(int start, int end, MaxFlow maxFlow){
        original = maxFlow.getMatrix();
        //init residual network
        map = maxFlow.getMatrix();
        n = map.length;
        long time = System.nanoTime();
        int result = getMaxFlow(start, end);
        long endTime = System.nanoTime();
        System.out.println(name+":\t"+ (endTime-time)+" nanosecond");
        return result;
    }

    abstract int getMaxFlow(int start, int end);

    protected int result(){
        int sum = 0;
        for(int i=0;i<n;i++){
            sum += map[i][0];
        }
        return sum;
    }
}
