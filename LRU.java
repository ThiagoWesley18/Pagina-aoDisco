import java.util.LinkedList;

public class LRU {
    private final int TAM_QUADRO = 3;
    private LinkedList<Pagina> quadros = new LinkedList<>();
    private LinkedList<Pagina> pagAcessada = new LinkedList<>();
    private LinkedList<Pagina> memoria;

    public LRU(LinkedList<Pagina> memoria) {
        this.memoria = memoria;
    }

    public int getFaltasLRU() {
        int faltas = 0;
        do {
            // Inicia colocando no quadro as paginas faltando.
            if (quadros.size() < TAM_QUADRO) {
                pagAcessada.addFirst(memoria.getFirst());
                quadros.add(memoria.removeFirst());
                faltas++;
            // Se a pagina não existe no quadro, retira a pagina com menos acesso do quadro e substitui com a pagina da cadeia de recorrencia.
            } else if (!paginaExiste(memoria.getFirst())) {
                pagAcessada.addFirst(memoria.getFirst());
                Pagina pagSemAcesso = maiorTempoSemAcesso();
                quadros.remove(pagSemAcesso);
                quadros.add(memoria.removeFirst());
                faltas++;
            // Se a pagina da cadeia de recorrencia ja estiver no quadro.
            } else {
                pagAcessada.addFirst(memoria.getFirst());
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

    private Pagina maiorTempoSemAcesso(){
        int tempo;
        int maiorTempo = 0;
        Pagina pagSemAcesso = null;

        // Procura nas paginas acessadas a pagina mais distante, ou seja, a pagina de maior tempo sem acesso.
        for(Pagina i : quadros){
            tempo = 0;
            for(Pagina j : pagAcessada){
                tempo++;
                if(i.pagina == j.pagina){
                    break;
                }
            }
            if(tempo > maiorTempo){
                maiorTempo = tempo;
                pagSemAcesso = i;
            }
        }
        return pagSemAcesso;
    }
}
