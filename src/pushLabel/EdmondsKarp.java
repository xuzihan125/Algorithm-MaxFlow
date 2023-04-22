package pushLabel;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class EdmondsKarp extends MaxFlowSolution{

    List<int[]> path;

    public EdmondsKarp(){
        super("Edmonds-Karp algorithm");
    }

    @Override
    int getMaxFlow(int start, int end) {
        int value = findPathBFS(start,end);
        while(value > 0){
            for(int[] edge : path){
                map[edge[0]][edge[1]] -= value;
                map[edge[1]][edge[0]] += value;
            }
            value = findPathBFS(start, end);
        }
        return result();
    }

    private int findPathBFS(int start, int end){

        int[] reached = new int[n];
        Arrays.fill(reached,-1);

        Queue<Integer> queue = new LinkedBlockingQueue<>();
        queue.add(start);

       flag: while (!queue.isEmpty()){
            int node = queue.poll();
            for(int i=0;i<n;i++){
                if(map[node][i] == 0 || reached[i] != -1){
                    continue;
                }
                queue.add(i);
                reached[i] = node;
                if(i == end){
                    break flag;
                }
            }
        }
        path = new LinkedList<>();
        int min = Integer.MAX_VALUE;
        if(reached[end] == -1){
            return -1;
        }
        while(end != start){
            path.add(new int[]{reached[end], end});
            min = Math.min(min, map[reached[end]][end]);
            end = reached[end];
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
