public class DFS_DiGraph{
    private int [] edgeTo;
    private boolean [] marked;
    private int s;

    public DFS_DiGraph(Digraph g, int vertex) {
        this.s=vertex;
        edgeTo=new int [g.V()];
        marked=new boolean [g.V()];
        dfs(g,s);
    }

    private void dfs(Digraph dg, int vertex) {

        marked[vertex]=true;
        for(int adj: dg.adj(vertex)){
            if(!marked[adj]){
                edgeTo[adj]=vertex;
                dfs(dg, adj);
            }
        }

    }

    public boolean hasPath(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!marked[v]) return null;

        Bag b = new Bag();
        b.add(v);
        while(v!=edgeTo[v]){
            v=edgeTo[v];
            b.add(v);
        }
        return b;

    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
        StdOut.println();
        StdOut.println(G.toDot());

        System.out.println("Estou na classe Caminhamento Em Profundidade...");

        DFS_DiGraph cep = new DFS_DiGraph(G, 0);
        System.out.println("Existe um caminho para o 3? "+(cep.hasPath(3)?"SIM":"NÃO"));
        if(cep.hasPath(3)){
            for(int p: cep.pathTo(3))
                System.out.print(p+"; ");
            System.out.println();
        }
            

    }



}