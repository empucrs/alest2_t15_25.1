import java.util.ArrayList;

public class App {

    private static class Ponto {
        public double x, y;
        public Ponto(double x, double y) {
            this.x=x;
            this.y=y;
        }  
        @Override
        public String toString() {
            return "{"+x+", "+y+"}";
        }      
    }

    public static void main(String[] args) {
        
        ArrayList<Ponto> vertices = new ArrayList<>();

        //1 ler o conjunto de pontos para uma estrutura adequada
        In entrada = new In(args[0]);
        while (entrada.hasNextLine()) {
            String [] valores = entrada.readLine().split(";");
            double x = Double.parseDouble(valores[0]);
            double y = Double.parseDouble(valores[1]);
            vertices.add(new Ponto(x, y));
        }

        //2 criar o grafo valorado
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices.size());

        //  2.1 gerar as ligações
        for(int v=0; v<vertices.size(); v++){
            MinPQ<Edge> listaEdge = new MinPQ<>();
            for(int w=0; w<vertices.size(); w++){
                if(v!=w){
                    int auxv = v;
                    int auxw = w;
                    if(v>w){
                        auxv=w;
                        auxw=v;
                    }
                    Ponto pv = vertices.get(auxv);
                    Ponto pw = vertices.get(auxw);

                    // calculo a distancia euclidiana
                    double d = Math.sqrt( Math.pow((pv.x-pw.x), 2) + Math.pow((pv.y-pw.y),2));

                    Edge e = new Edge(auxv, auxw, d);
                    listaEdge.insert(e);
                }
            }
            //  2.2 considerar so os 3 mais proximos (distancia euclediana)
            for(int i=0; i<3; i++)
                ewg.addEdge(listaEdge.delMin());
        }

        //3 executar a arvore geradora minima
        KruskalMST kmst = new KruskalMST(ewg);

        //4 salvar resultado em arquivo
        Out saida = new Out("resultado.txt");
        for(Edge e: kmst.edges()){
            saida.printf("%d %d 1\n", e.either(), e.other(e.either()));
        }
        saida.close();


    }
    
}
