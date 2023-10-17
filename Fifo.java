import java.util.ArrayList;
import java.util.LinkedList;

public class Fifo {
    private final int  TAM_QUADRO = 3;
    private ArrayList<Pagina> quadros = new ArrayList<>();
    private LinkedList<Pagina> memoria;

    public Fifo(LinkedList<Pagina> memoria){
        this.memoria = memoria;
    }

    public int getFaltasFifo(){
        int faltas = 0;
        do {
            // Inicia os quadros da memoria.
            if (quadros.size() < TAM_QUADRO) {
                quadros.add(memoria.removeFirst());
                faltas++;
            // Implementa os quadros de acordo com o menor tempo de chegada, ou seja, com mais tempo na memoria.
            } else if ( !paginaExiste(memoria.getFirst()) ) {
                quadros.remove( minQuadro() );
                quadros.add( memoria.removeFirst() );
                faltas++;
            // Caso ja estaja no quadro.
            }else{
                memoria.removeFirst();
            }
        // A execuçao para com a pagina e o processo 0,0.
        } while ((memoria.getFirst().pagina != 0) || (memoria.getFirst().processo != 0));
        return faltas;
    }

    // Retorna a pagina com o maior tempo na memoria, ou seja, com o menor tempo de chegada.
    private Pagina minQuadro(){
        int min = quadros.get(0).tempoChagada;
        Pagina pag = quadros.get(0);
        for (Pagina i: quadros) {
            if(i.tempoChagada < min){
                min = i.tempoChagada;
                pag = i;
            }
        }
        return pag;
    }
    // Caso a pagina ja esteja no quadro retorna true.
    private boolean paginaExiste(Pagina pag){
        boolean flag = false;
        for (Pagina i: quadros) {
            if (i.pagina == pag.pagina) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
