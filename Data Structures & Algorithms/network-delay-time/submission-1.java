class Solution {
    private List<List<Pair<Integer, Integer>>> createGraph(int[][] edges, int vertices) {
        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= vertices; i++) {
            graph.add(new ArrayList<>());
        }
        int edgesLen = edges.length;
        for (int i = 0; i < edgesLen; i++) {
            graph.get(edges[i][0]).add(new Pair(edges[i][1], edges[i][2]));
        }
        return graph;
    }

    private void minTimeForAllNodes(List<List<Pair<Integer, Integer>>> graph, int[] minTime, int src) {
        Arrays.fill(minTime, Integer.MAX_VALUE);
        TreeSet<int[]> minTimeNode = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] n1, int[] n2) {
                if (n1[1] != n2[1]) {
                    return Integer.compare(n1[1], n2[1]);
                }
                return Integer.compare(n1[0], n2[0]);
            }
        });
        minTimeNode.add(new int[] { src, 0 });
        minTime[src] = 0;
        while (!minTimeNode.isEmpty()) {
            int node = minTimeNode.first()[0];
            int time = minTimeNode.first()[1];
            minTimeNode.pollFirst();
            for (Pair<Integer, Integer> next : graph.get(node)) {
                int nextNode = next.getKey();
                int timeReq = next.getValue() + time;
                if (timeReq < minTime[nextNode]) {
                    minTimeNode.remove(new int[] { nextNode, minTime[nextNode] });
                    minTimeNode.add(new int[] { nextNode, timeReq });
                    minTime[nextNode] = timeReq;
                }
            }
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<List<Pair<Integer, Integer>>> graph = createGraph(times, n);
        int[] minTime = new int[n + 1];
        minTimeForAllNodes(graph, minTime, k);
        int maxTime = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (maxTime < minTime[i]) {
                maxTime = minTime[i];
            }
        }
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }
}