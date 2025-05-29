public class FloydWarshall {

    private int nVertices;
    private double [][] ma_dist;
    private int    [][] ma_pred;

    public FloydWarshall(String filename) {        
        inicializacao(filename);
        imprimeMatrizes();
        executa();        
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        imprimeMatrizes();
    }

    private void executa(){
        for(int inter=0; inter<nVertices; inter++)
            for(int src=0; src<nVertices; src++)
                for(int tgt=0; tgt<nVertices; tgt++){
                    if(ma_dist[src][tgt] > (ma_dist[src][inter]+ma_dist[inter][tgt])){
                        ma_dist[src][tgt] = (ma_dist[src][inter]+ma_dist[inter][tgt]);
                        ma_pred[src][tgt] = ma_pred[inter][tgt];
                    }
                }
    }

    private void inicializacao(String filename){

        In entrada = new In(filename);

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //1º campo define o nro de vertices
        //String nVertices = entrada.readLine();
        //System.out.println("O nro de vertices é "+nVertices);
        this.nVertices = Integer.parseInt(entrada.readLine());
        // crias as matrizes
        ma_dist = new double[nVertices][nVertices];
        ma_pred = new int[nVertices][nVertices];
        // inicializar as matrizes com valores padrão
        for(int src=0; src<nVertices; src++)
            for(int tgt=0; tgt<nVertices; tgt++){
                ma_dist[src][tgt]=(src!=tgt)?Double.POSITIVE_INFINITY:0;
                ma_pred[src][tgt]=(src!=tgt)?-1:src;
            }

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //2º campo define o nro de arestas
        //String nArestas = entrada.readLine();
        //System.out.println("O nro de arestas é "+nArestas);
        entrada.readLine();

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        //3º campo define o conjunto de arestas
        //System.out.println("Arestas detalhadas");
        //int aresta=0;
        //while (entrada.hasNextLine()) {
            //origem destino peso
        //    String dados [] = entrada.readLine().split(" ");
        //    System.out.printf("  Aresta %2d: Origem(%s) Destino(%s) custo(%s)\n", aresta++, dados[0], dados[1], dados[2]);            
        //}
        int id=2;
        String linha="";
        while (entrada.hasNextLine()) {
            try{
                linha = entrada.readLine();
                String dados [] = linha.trim().split(" ");
                int    src  = Integer.parseInt(dados[0].trim());
                int    tgt  = Integer.parseInt(dados[1].trim());
                double peso = Double.parseDouble(dados[2].trim());

                ma_dist[src][tgt]=peso;
                ma_pred[src][tgt]=src;
                id++;
            }
            catch(Exception e){
                System.out.println("Erro na linha "+ id+": "+linha);
                System.exit(1);
            }
        
        }

        entrada.close();        
    }

    private void imprimeMatrizes(){

        System.out.println("Detalhes da matriz de distancia");
        System.out.print("   |");
        for(int tgt=0; tgt<nVertices; tgt++)
            System.out.printf("%2d   |", tgt);
        System.out.println();
        for(int src=0; src<nVertices; src++){
            System.out.printf("%2d |", src);
            for(int tgt=0; tgt<nVertices; tgt++){
                System.out.printf("%.2f |", ma_dist[src][tgt]);                
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Detalhes da matriz de predecessores");
        System.out.print("   |");
        for(int tgt=0; tgt<nVertices; tgt++)
            System.out.printf("%2d |", tgt);
        System.out.println();
        for(int src=0; src<nVertices; src++){
            System.out.printf("%2d |", src);
            for(int tgt=0; tgt<nVertices; tgt++){
                System.out.printf("%2d |", ma_pred[src][tgt]);                
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        FloydWarshall fw = new FloydWarshall(args[0]);

        // CRIAR MECANISMOS PARA CONSULTAR SE EXISTE UM CAMINHO ENTRE UMA ORIGEM E UM DESTINO
        // SE NÃO HOUVE, INFORMAR Q NÃO EXISTE
        // SE HOUVER
        //    -> MOSTRAR O CUSTO
        //    -> MOSTRAR O CAMINHO DESDE A ORIGEM ATÉ O DESTINO (NESTA ORDEM)
    }
    
}
