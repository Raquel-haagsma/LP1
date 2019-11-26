
package main;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;

public class Controle {
    private List<Macarrao> lista = new ArrayList<>();

    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Macarrao macarrao) {
        lista.add(macarrao);
    }

    public List<Macarrao> listar() {
        return lista;
    }

    public Macarrao buscar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo()==(codigo)) {
                return lista.get(i);
            }
        }
        return null;

    }

    public void alterar(Macarrao macarrao, Macarrao macarraoAntigo) {
        lista.set(lista.indexOf(macarraoAntigo), macarrao);

    }

    public void excluir(Macarrao macarrao) {
        lista.remove(macarrao);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Macarrao macarrao : lista) {
            listaDeString.add(macarrao.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Macarrao
        Macarrao macarrao;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            macarrao = new Macarrao(Integer.valueOf(aux[0]), aux[1], (aux[2]), Double.valueOf(aux[3]), Double.valueOf(aux[4]));
            lista.add(macarrao);
        }
    }
}
