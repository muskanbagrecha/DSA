package org.example.graphs;

import org.example.pair.Pair;
import org.example.tri.Tri;

import java.util.*;

public class GraphProblems {
    public static ArrayList<Integer> bfsOfDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] vis = new boolean[V];
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;
        while (!q.isEmpty()) {
            int element = q.remove();
            ans.add(element);
            for (int neighbour : adj.get(element)) {
                if (vis[neighbour] != true) {
                    vis[neighbour] = true;
                    q.add(neighbour);
                }
            }
        }
        return ans;
    }
    //Space: O(V) for queue + O(V) for visited array + O(V) storage of output
    //Time: While will run for V times at max (each node is pushed into queue only once) + total degrees (for the inner for loop)
    //Total degrees = no of edges as it is directed graph
    //Total time: O(V) + O(E) = O(V+E)

    //So, for a directed graph, the overall time complexity of the BFS traversal remains O(V + E), acknowledging that each edge is considered once in its directed context, as opposed to the undirected scenario where each edge was considered twice (once from each of its two vertices).

    //https://www.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
    public ArrayList<Integer> dfsOfUnconnectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[V];
        dfsRecursionHelper(0, adj, vis, ans);
        return ans;
    }

    public void dfsRecursionHelper(int node, ArrayList<ArrayList<Integer>> adj, boolean[] vis, ArrayList<Integer> ans) {
        vis[node] = true;
        ans.add(node);
        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                dfsRecursionHelper(neighbour, adj, vis, ans);
            }
        }
    }
    //Space: O(V) worst case when we have a skewed tree like structure (recursion stack) + O(V) for visited graph + O(V) for output array => O(V)
    //Time: O(V) + O(2*E) => O(V+E)

    public static int findNumOfProvinces(int[][] roads, int n) {
        // Write your code here.
        boolean[] vis = new boolean[n];
        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfsFindNumOfProvincesHelper(roads, i, vis);
                provinces++;
            }
        }
        return provinces;
    }

    public static void dfsFindNumOfProvincesHelper(int[][] roads, int city, boolean[] vis) {
        vis[city] = true;
        for (int j = 0; j < roads[city].length; j++) {
            if (roads[city][j] == 1 && !vis[j]) {
                dfsFindNumOfProvincesHelper(roads, j, vis);
            }
        }
    }

    //Space: O(n) for visited array
    //Time: O(V^2) coz we are checking each element in the 2d matrix

    //https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1

    public static int numIslands(char[][] grid) {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        int islands = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    islands++;
                    numIslandsBFSHelper(grid, vis, i, j);
                }
            }
        }
        return islands;
    }

    public static void numIslandsBFSHelper(char[][] grid, boolean[][] vis, int i, int j) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        while (!q.isEmpty()) {
            Pair current = q.remove();
            for (int row = -1; row <= 1; row++) {
                for (int col = -1; col <= 1; col++) {
                    int nrow = row + current.first;
                    int ncol = col + current.second;
                    if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length && grid[nrow][ncol] == '1' && !vis[nrow][ncol]) {
                        vis[nrow][ncol] = true;
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
        }
    }

    //Time:
    // 1. O(m*n) for main two loops, in the worst case
    // 2. O(m*n) in bfs in the worst case. When all cells are 1, all neighbours will be checked once.
    //3. Inside bfs, check will we made for 8 neighbours but this will not increase the time complexity as it is a fixed set.
    //time complexity: O(m*n)
    //Space: O(m*n) for visited array and O(m*n) - worst case for queues when the entire matrix has 1. So total O(m*n).

    //Using DFS

    int[] drow = {-1, -1, -1, 0, 1, 1, 1, 0};
    int[] dcol = {-1, 0, 1, 1, 1, 0, -1, -1};

    public int numIslandsdfs(char[][] grid) {
        // Code here
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        int ctr = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !vis[i][j]) {
                    numIslandsDfsHelper(grid, i, j, vis);
                    ctr++;
                }
            }
        }
        return ctr;
    }

    public void numIslandsDfsHelper(char[][] grid, int i, int j, boolean[][] vis) {
        vis[i][j] = true;
        for (int k = 0; k < 8; k++) {
            int nrow = i + drow[k];
            int ncol = j + dcol[k];
            if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length) {
                if (grid[nrow][ncol] == '1' && !vis[nrow][ncol]) {
                    numIslandsDfsHelper(grid, nrow, ncol, vis);
                }
            }
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;
        boolean[][] vis = new boolean[m][n];
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = image[i][j];
            }
        }
        floodFillHelper(image, sr, sc, color, vis, result, image[sr][sc]);
        return result;
    }

    public static void floodFillHelper(int[][] image, int sr, int sc, int color, boolean[][] vis, int[][] result, int startingColor) {
        vis[sr][sc] = true;
        result[sr][sc] = color;
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nrow = sr + dRow[i];
            int ncol = sc + dCol[i];
            if (nrow >= 0 && nrow < image.length && ncol >= 0 && ncol < image[0].length && !vis[nrow][ncol] && image[nrow][ncol] == startingColor) {
                floodFillHelper(image, nrow, ncol, color, vis, result, startingColor);
            }
        }
    }

    // O(m*n) for initial copy, recursion worst case when all cells are initial color -> O(m*n) -> O(m*n)
    //Space: O(m*n) for vis, O(m*n) for result array if we want to include output array, recursion stack -> O(m * n) -> O(m*n)

    //Slightly faster but in this approach we are modifying input array which may not be recommended
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int currentColor = image[sr][sc];
        fill2(image, sr, sc, currentColor, newColor);
        return image;
    }

    private void fill2(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color || image[sr][sc] == newColor) {
            return; // Check bounds and if the current pixel is the color we're trying to change from
        }
        image[sr][sc] = newColor; // Change color
        fill2(image, sr - 1, sc, color, newColor); // Up
        fill2(image, sr + 1, sc, color, newColor); // Down
        fill2(image, sr, sc - 1, color, newColor); // Left
        fill2(image, sr, sc + 1, color, newColor); // Right
    }

    //Time: O(m*n) for recursion stack -> each cell is visited once
    //Space: O(m*n) worst case -> This is primarily due to the recursion stack. Although each call adds a frame to the stack, the depth of these calls—and therefore the maximum size of the stack—can in the worst case be proportional to the number of pixels in the image if the recursive filling process has to traverse a long path or cover a large area.

    //https://leetcode.com/problems/rotting-oranges/
    public int orangesRotting(int[][] grid) {
        Queue<Tri> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int minutes = 0;
        int fresh = 0;
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Tri(i, j, 0));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        while (!q.isEmpty() && fresh > 0) {
            Tri current = q.remove();
            minutes = Math.max(minutes, current.time);
            for (int i = 0; i < 4; i++) {
                int nrow = current.first + drow[i];
                int ncol = current.second + dcol[i];
                if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && grid[nrow][ncol] == 1) {
                    grid[nrow][ncol] = 2;
                    fresh--;
                    q.add(new Tri(nrow, ncol, current.time + 1));
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }

    //Time: O(m*n) for initial iteration and for BFS it will be O(m*n) as all cells can be processed once
    //Space: O(m*n) worst case when all are rotten queue will have all elements

    //Second approach: track time by incrementing minute at each level of BFS (for each rotten orange as starting point).
    public int orangesRotting2(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int minutes = 0;
        int fresh = 0;
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        // Initialize the queue with all initially rotten oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        while (!q.isEmpty() && fresh > 0) {
            int size = q.size(); // Oranges to rot this minute
            for (int i = 0; i < size; i++) {
                Pair current = q.poll();
                for (int k = 0; k < 4; k++) {
                    int newRow = current.first + dRow[k];
                    int newCol = current.second + dCol[k];
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Rotten now
                        fresh--; // Decrease the count of fresh oranges
                        q.add(new Pair(newRow, newCol)); // Add newly rotten orange for next minute
                    }
                }
            }
            minutes++; // minutes is incremented after processing all oranges that can rot in a single minute.
        }

        return fresh == 0 ? minutes : -1;
    }

    //
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                boolean hasCycle = isCycleBfs(adj, i, vis);
                if (hasCycle) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycleBfs(ArrayList<ArrayList<Integer>> adj, int node, boolean[] vis) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        vis[node] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            int ctr = 0;
            for (Integer neighbour : adj.get(curr)) {
                if (!vis[neighbour]) {
                    vis[neighbour] = true;
                    q.add(neighbour);
                } else {
                    ctr++;
                }
            }
            if (ctr > 1) return true;
        }
        return false;
    }

    //Approach 2
    public boolean isCycle2(int V, ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (isCycle2Bfs(adj, i, vis))
                    return true;
            }
        }
        return false;
    }

    public boolean isCycle2Bfs(ArrayList<ArrayList<Integer>> adj, int node, boolean[] vis) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(node, -1));
        vis[node] = true;
        while (!q.isEmpty()) {
            int curr = q.peek().first;
            int parent = q.poll().second;
            for (Integer neighbour : adj.get(curr)) {
                if (!vis[neighbour]) {
                    q.add(new Pair(neighbour, curr));
                    vis[neighbour] = true;
                } else if (neighbour != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    //Time complexity: same as bfs -> O(V + 2E) -> O(V + E) where E is no of edges

    public boolean isCycleDFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (isCycleDFSHelper(adj, vis, i, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycleDFSHelper(ArrayList<ArrayList<Integer>> adj, boolean[] vis, int node, int parent) {
        vis[node] = true;
        for (int neighbour : adj.get(node)) {
            if (!vis[neighbour]) {
                if (isCycleDFSHelper(adj, vis, neighbour, node)) {
                    return true;
                } else if (neighbour != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    //01 MATRIX
    //this solution involves doing a BFS for each cell at 1 so we can find the min distance but it is EXTREMELY SLOW
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] output = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    output[i][j] = distance(mat, i, j, visited);
                }
            }
        }
        return output;
    }

    public int distance(int[][] grid, int i, int j, boolean[][] vis) {
        // do bfs till we find 0
        for (int a = 0; a < grid.length; a++) {
            Arrays.fill(vis[a], false); // Reset visited before each BFS
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        int distance = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int t = 0; t < size; t++) {
                Pair curr = q.poll();
                for (int k = 0; k < 4; k++) {
                    int nrow = curr.first + drow[k];
                    int ncol = curr.second + dcol[k];
                    if (nrow >= 0 && nrow < grid.length && ncol >= 0 && ncol < grid[0].length && !vis[nrow][ncol]) {
                        vis[nrow][ncol] = true;
                        if (grid[nrow][ncol] == 0) {
                            return distance;
                        }
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
            distance++;
        }
        return distance;
    }

    //Approach 2
    //Floodfill from 0s (similar to rotten oranges algo. Multi source BFS approach)
    public int[][] updateMatrix2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] distance = new int[m][n];
        int sum = m + n;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Pair(i, j));
                } else {
                    distance[i][j] = sum;
                }
            }
        }
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();
                for (int k = 0; k < 4; k++) {
                    int nrow = curr.first + drow[k];
                    int ncol = curr.second + dcol[k];
                    if (nrow >= 0 && nrow < m && ncol >= 0 && ncol < n && mat[nrow][ncol] == 1
                            && distance[nrow][ncol] == sum) { //equivalent to not visited
                        distance[nrow][ncol] = level;
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
            level++;
        }
        return distance;
    }

    public void surroundedRegions(char[][] board){
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                surroundedRegionsDfs(board, i, 0);
            if (board[i][n - 1] == 'O')
                surroundedRegionsDfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                surroundedRegionsDfs(board, 0, j);
            if (board[m - 1][j] == 'O')
                surroundedRegionsDfs(board, m - 1, j);
        }
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(board[i][j]=='#'){
                    board[i][j] = 'O';
                }
                else if(board[i][j]=='O'){
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void surroundedRegionsDfs(char[][] board, int i, int j){
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!='O'){
            return;
        }
        board[i][j] = '#';
        surroundedRegionsDfs(board, i-1, j);
        surroundedRegionsDfs(board, i, j+1);
        surroundedRegionsDfs(board, i+1, j);
        surroundedRegionsDfs(board, i, j-1);
    }

    //Time: O(m*n) - worst case all elements are O
    //Space: O(m*n)

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i<m; i++){
            if(grid[i][0]==1){
                numEnclavesDfs(grid, i, 0);
            }
            if(grid[i][n-1]==1){
                numEnclavesDfs(grid, i, n-1);
            }
        }

        for(int j = 0; j<n; j++){
            if(grid[0][j]==1){
                numEnclavesDfs(grid, 0, j);
            }
            if(grid[m-1][j]==1){
                numEnclavesDfs(grid, m-1, j);
            }
        }
        int ctr = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j]==1){
                    ctr++;
                }
            }
        }
        return ctr;
    }

    public void numEnclavesDfs(int[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]!=1){
            return;
        }
        grid[i][j]=0;
        numEnclavesDfs(grid, i-1, j);
        numEnclavesDfs(grid, i, j+1);
        numEnclavesDfs(grid, i+1, j);
        numEnclavesDfs(grid, i, j-1);
    }

    public int numEnclavesBFS(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ctr = 0;
        Queue<Pair> q = new LinkedList<>();
        for(int i = 0; i<m; i++){
            if(grid[i][0]==1){
                q.add(new Pair(i, 0));
                grid[i][0] = 0;
            }
            if(grid[i][n-1]==1){
                q.add(new Pair(i, n-1));
                grid[i][n-1]=0;
            }
        }

        for(int j = 0; j<n; j++){
            if(grid[0][j]==1){
                q.add(new Pair(0, j));
                grid[0][j]=0;
            }
            if(grid[m-1][j]==1){
                q.add(new Pair(m-1, j));
                grid[m-1][j]=0;
            }
        }
        int[] drow = {-1, 0, 1, 0};
        int[] dcol = {0, 1, 0, -1};
        while(!q.isEmpty()){
            Pair curr = q.poll();
            for(int k = 0; k<4; k++){
                int nrow = drow[k] + curr.first;
                int ncol = dcol[k] + curr.second;

                if(nrow>=0 && nrow<m && ncol>=0 && ncol<n && grid[nrow][ncol]==1){
                    grid[nrow][ncol]=0;
                    q.add(new Pair(nrow, ncol));
                }
            }
        }
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j]==1){
                    ctr++;
                }
            }
        }
        return ctr;
    }

    //Function to return list containing vertices in Topological order.
    static int[] topoSortKahn(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // add your code here
        int[] indegree = new int[V];
        Queue<Integer> q = new LinkedList<>();
        int resultIndex = 0;
        int[] res = new int[V];
        for(int i = 0; i<V; i++){
            for(int j : adj.get(i)){
                indegree[j] = indegree[j] + 1;
            }
        }
        for(int i = 0; i<indegree.length; i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int curr = q.poll();
            res[resultIndex++] = curr;
            for(int j : adj.get(curr)){
                indegree[j]--;
                if(indegree[j]==0){
                    q.add(j);
                }
            }
        }

        if (resultIndex != V) {
            throw new RuntimeException("Cycle detected in the graph");
        }
        return res;
    }
    //tc: O(V+E)
    //SC: O(V)

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!dfs(i, graph, visited, onPath)) return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] onPath) {
        visited[node] = true;
        onPath[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (!dfs(neighbor, graph, visited, onPath)) {
                    return false;
                }
            } else if (onPath[neighbor]) {
                return false;
            }
        }
        onPath[node] = false;
        return true;
    }
}
