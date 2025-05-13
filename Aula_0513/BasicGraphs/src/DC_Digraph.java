public class DC_Digraph {

    private boolean cycle;
    private enum color {WHITE, GREY, BLACK};
    private color[] mark;

    public DC_Digraph(Digraph dg) {
        cycle=false;

        mark = new color[dg.V()];

        for(int i=0; i<mark.length; i++)
            mark[i]=color.WHITE;

        for(int vertex=0; vertex<dg.V(); vertex++)
            if(mark[vertex]==color.WHITE)
                visit(dg, vertex);
    }

    private void visit(Digraph dg, int vertex){
        mark[vertex]=color.GREY;

        for(int adj: dg.adj(vertex)){
            if(mark[adj]==color.GREY)
                cycle=true;
            else if(mark[adj]==color.WHITE)
                visit(dg, adj);
        }
        if(!cycle) mark[vertex]=color.BLACK;

    }

    public boolean hasCycle(){
        return cycle;

    }
    
}