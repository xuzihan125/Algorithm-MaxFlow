package pushLabel;

public abstract class MaxFlowSolution {
    int[][] original;
    int[][] map;
    int n;

    public int getMaxFlow(int start, int end, MaxFlow maxFlow){
        original = maxFlow.getMatrix();
        //init residual network
        map = maxFlow.getMatrix();
        n = map.length;
        long time = System.nanoTime();
        int result = getMaxFlow(start, end);
        long endTime = System.nanoTime();
        System.out.println(endTime-time+" nanosecond");
        return result;
    }

    abstract int getMaxFlow(int start, int end);
}
