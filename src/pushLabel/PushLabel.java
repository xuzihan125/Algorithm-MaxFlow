package pushLabel;

import java.util.*;

public class PushLabel extends MaxFlowSolution{

    int[] height;
    int[] excess;
    int[] seen;
    Stack<Integer> stack;

    public PushLabel(){
        super("Push-label algorithm");
    }

    @Override
    public int getMaxFlow(int start, int end) {
        //initial of map
        height = new int[n];
        excess = new int[n];
        seen = new int[n];
        stack = new Stack<>();
        // initial push
        height[start] = n;
        excess[start] = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            push(start,i);
        }

        while (!stack.isEmpty()){
            int node = stack.pop();
            if(height[node] >= n) continue;
            discharge(node);
        }
        return result();
    }



    private void push(int start, int end){
        int value = Math.min(map[start][end], excess[start]);
        if(value == 0){
            return;
        }
        stack.push(start);
        map[start][end] -= value;
        map[end][start] += value;
        excess[start] -= value;
        excess[end] += value;
    }

    private void relabel(int node){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            if(original[node][i] - map[i][node] > 0){
                min = Math.min(min, height[i]);
            }
        }
        height[node] = min;
    }

    private void discharge(int node){
        while(excess[node] > 0){
            if(seen[node] < n){
                int next = seen[node];
                if(original[node][next] - map[node][next] > 0 && height[node] > height[next]){
                    push(node,next);
                }
                else{
                    seen[node] += 1;
                }
            }
            else{
                relabel(node);
                seen[node] = 0;
            }
        }
    }

}
