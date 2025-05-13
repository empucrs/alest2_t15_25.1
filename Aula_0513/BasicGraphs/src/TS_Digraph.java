import java.util.Arrays;

public class TS_Digraph {

    private int[] result;
    private int pos;

    public TS_Digraph(Digraph dg) {

        //1. Garantir que não há ciclos
        DC_Digraph dcdg = new DC_Digraph(dg);
        if(dcdg.hasCycle())
            throw new IllegalArgumentException("O grafo passado contém ciclo");

        //2. Realizar a ordenação topológica
        result = new int[dg.V()];
        pos=0;
        //for(int vertex=0; vertex<dg.V(); vertex++)
        //    if(se vertex não foi visitado)
        //        SortingAlgorithm(dg, vertex)
    }

    public int[] TSort(){
        return Arrays.copyOf(result, result.length);
    }

    
}