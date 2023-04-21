package pushLabel;

import java.util.*;

public class MaxFlow {
    Map<Integer, Map<Integer, Integer>> map;
    Map<Integer, String> nodes;
    Map<String, Integer> match;

    public MaxFlow(){
        map = new HashMap<>();
        nodes = new HashMap<>();
        match = new HashMap<>();
    }

    public void addNode(int num, String name){
        if(map.containsKey(num) || match.containsKey(name)){
            throw new IllegalArgumentException("duplicate node");
        }
        nodes.put(num,name);
        match.put(name,num);
    }

    public void addEdge(String source, String target, int value){
        addEdge(match.get(source),target,value);
    }

    public void addEdge(String source, int target, int value){
        addEdge(match.get(source),target,value);
    }

    public void addEdge(int source, String target, int value){
        addEdge(source,match.get(target),value);
    }

    public void addEdge(int source, int target, int value){
        if(!nodes.containsKey(source) || !nodes.containsKey(target)){
            throw new IllegalArgumentException("node doesn't exists");
        }
        if(source == target){
            throw new IllegalArgumentException("same start and end");
        }
        if(map.getOrDefault(target, new HashMap<>()).containsKey(source)){
            throw new IllegalArgumentException("reverse edge exist");
        }
        if(!map.containsKey(source)){
            map.put(source, new HashMap<>());
        }
        map.get(source).put(target, value);
    }

    public int maxFlow(int start, int end, MaxFlowSolution solution){
        return solution.getMaxFlow(start, end, this);
    }

    @Override
    public String toString() {
        return getNode()+getEdge();
    }

    private String getNode(){
        StringBuilder builder = new StringBuilder();
        for(int node : nodes.keySet()){
            builder.append(String.format("%s(%d)\n", nodes.get(node), node));
        }
        return builder.toString();
    }

    private String getEdge(){
        StringBuilder builder = new StringBuilder();
        for(int start : map.keySet()){
            for(int end : map.get(start).keySet()){
                builder.append(String.format("%s(%d) -> %s(%d) : %d\n", nodes.get(start), start, nodes.get(end), end, map.get(start).get(end)));
            }
        }
        return builder.toString();
    }

    public Map<Integer, Map<Integer, Integer>> getMap(){
        Map<Integer, Map<Integer, Integer>> copy = new HashMap<>();
        for(Integer source : map.keySet()){
            copy.put(source, new HashMap<>());
            for(Integer end : map.get(source).keySet()){
                copy.get(source).put(end, map.get(source).get(end));
            }
        }
        return copy;
    }

    public int[][] getMatrix(){
        List<Integer> list = getNodeList();
        int n = list.size();
        Map<Integer, Integer> nodes = new HashMap<>();
        for(int i=0;i<n;i++){
            nodes.put(nodes.get(i),i);
        }
        int[][] result = new int[n][n];
        for(Integer source : map.keySet()){
            for(Integer end : map.get(source).keySet()){
                result[source][end] = map.get(source).get(end);
            }
        }
        return result;
    }

    public List<Integer> getNodeList(){
        return new ArrayList<>(nodes.keySet());
    }
}
