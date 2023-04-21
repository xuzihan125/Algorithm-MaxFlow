package pushLabel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FordForkerson extends MaxFlowSolution{
    List<int[]> path;
    Set<Integer> reached;

    public FordForkerson(){
        super("Ford-forkerson algorithm");
        path = new LinkedList<>();
    }
    @Override
    int getMaxFlow(int start, int end) {
        int value = findPathDFS(start,end);
        while(value != -1){
            for(int[] edge : path){
                map[edge[0]][edge[1]] -= value;
                map[edge[1]][edge[0]] += value;
            }
            value = findPathDFS(start, end);
        }
        return result();
    }

    private int findPathDFS(int start, int end){
        path = new LinkedList<>();
        reached = new HashSet<>();
        return findPathDFS(start, end, Integer.MAX_VALUE);
    }

    private int findPathDFS(int start, int end, int min){
        reached.add(start);
        if(start == end) return min==Integer.MAX_VALUE ? 0 : min ;
        for(int i=0;i<n;i++){
            if(map[start][i]==0 || reached.contains(i)){
                continue;
            }
            path.add(new int[]{start,i});
            int result = findPathDFS(i,end,Math.min(min,Math.min(min, map[start][i])));
            if(result != -1){
                return result;
            }
            reached.remove(i);
            path.remove(path.size()-1);
        }
        return -1;
    }
}
