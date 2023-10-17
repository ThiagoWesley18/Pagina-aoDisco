import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PaginaçaoMain {
    public static void main(String[] args) {
        LinkedList<Pagina> memoria = new LinkedList<>();

        System.out.println("<-------Entre com a Cadeia de referencia------>");
        Scanner scan = new Scanner(System.in);
        String entradaString = scan.next();
        String[] entradaPagina = entradaString.split(";");

        // Cria a cadeia de referência.
        for (int i = 0; i < entradaPagina.length; i++) {
            String[] pagina = entradaPagina[i].split(",");
            Pagina pag = new Pagina( Integer.parseInt(pagina[1]), Integer.parseInt(pagina[0]), i+1);
            memoria.addLast(pag);
        }

        // Faltas do FIFO
        Fifo paginacaoFIFO = new Fifo((LinkedList<Pagina>) memoria.clone());
        System.out.println("Page Faults FIFO: " + paginacaoFIFO.getFaltasFifo());

        // Faltas do LRU
        LRU paginacaoLRU = new LRU((LinkedList<Pagina>) memoria.clone());
        System.out.println("Page Faults LRU: " + paginacaoLRU.getFaltasLRU());

        // Faltas do Segunda Chance
        SegundaChance paginacaoSEG = new SegundaChance((LinkedList<Pagina>) memoria.clone());
        System.out.println("Page Faults Segunda Chance: " + paginacaoSEG .getFaltasSegundaChance());
    }
}
