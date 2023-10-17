import java.util.LinkedList;

public class SegundaChance {
    private final int TAM_QUADRO = 8000;
    private LinkedList<Pagina> quadros = new LinkedList<>();
    private LinkedList<Pagina> memoria;

    public SegundaChance(LinkedList<Pagina> memoria) {
        this.memoria = memoria;
    }

    public int getFaltasSegundaChance() {
        int faltas = 0;
        do {
            if (quadros.size() < TAM_QUADRO) {
                quadros.add(memoria.removeFirst());
                faltas++;
            } else if (!paginaExiste(memoria.getFirst())) {
                while (true) {
                    Pagina pagina = quadros.poll();
                    if (pagina.segundaChance) {
                        // Dê uma segunda chance para a página, movendo-a para o final da lista.
                        pagina.segundaChance = false;
                        quadros.add(pagina);
                    } else {
                        faltas++;
                        quadros.add(memoria.removeFirst());
                        break;
                    }
                }
            } else {
                memoria.removeFirst();
            }
        } while ((memoria.getFirst().pagina != 0) || (memoria.getFirst().processo != 0));
        return faltas;
    }

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
