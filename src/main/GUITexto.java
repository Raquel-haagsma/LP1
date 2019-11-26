package main;

//@author Radames J Halmeman  - rjhalmeman@gmail.com
import java.util.List;
import tools.Tools;

public class GUITexto {

    Tools tools = new Tools();

    Saida saida = new Saida();
    Controle controle = new Controle();
    
  

    public void telaList() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        List<Macarrao> lt = controle.listar();

        System.out.println("CÓDIGO;FORMATO;MOLHO;COMPRIMENTO;PRECO");
        for (Macarrao macarrao : lt) {
            System.out.println(macarrao);
        }

        entrada.pausaEnter();
    }

    public void telaRetrieve() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("RETRIEVE\n");
        int id = entrada.lerNumeroInteiro("Digite o Código do macarrão");
        Macarrao macarrao = controle.buscar(id);
        if (macarrao != null) {
            saida.imprimirNumeroInteiro("Código: ", macarrao.getId());
            saida.rotuloString("Nome:", macarrao.getNome());
            saida.rotuloString("Cor:", macarrao.getCor());
            saida.imprimirNumeroDouble("Peso:", macarrao.getPeso());
        } else {
            saida.rotuloString("Não encontrou esse ID", String.valueOf(id));
        }
        entrada.pausaEnter();
    }

    public void telaCreate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("INSERT\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do passaro");
        Macarrao pass = controle.buscar(id);
        if (pass == null) { //não achou, então pode adicionar
            Macarrao passaro = new Macarrao();
            passaro.setId(id);
            passaro.setNome(entrada.lerString("Digite o nome: "));
            passaro.setCor(entrada.lerString("Digite a cor: "));
            passaro.setPeso(entrada.lerNumeroDouble("Digite o peso: "));
            controle.adicionar(passaro);
        } else {
            System.out.println(pass);
            System.out.println("Macarrao já cadastrado");
            entrada.pausaEnter();
        }
    }

    public void telaUpdate() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("UPDATE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do passaro: ");
        Macarrao passaro = controle.buscar(id);
        if (passaro != null) { //achou, então pode alterar
            Macarrao passaroAntigo = passaro; //guarda dados para pesquisa no controle
            passaro.setId(id);
            saida.rotuloString("Nome atual: ", passaro.getNome());
            passaro.setNome(entrada.lerString("Digite o novo nome: "));
            saida.rotuloString("Cor atual: ", passaro.getCor());
            passaro.setCor(entrada.lerString("Digite a nova cor: "));
            saida.imprimirNumeroDouble("Peso: ", passaro.getPeso());
            passaro.setPeso(entrada.lerNumeroDouble("Digite o novo peso: "));
            controle.alterar(passaro, passaroAntigo);
        } else {
            System.out.println("Macarrao não cadastrado, impossível alterar");
            entrada.pausaEnter();
        }
    }

    public void telaDelete() {
        Entrada entrada = new Entrada();
        tools.clearScreen();
        System.out.println("");
        System.out.println("DELETE\n");
        entrada.teclado.reset();
        int id = entrada.lerNumeroInteiro("Digite o ID do pássaro: ");
        Macarrao passaro = controle.buscar(id);
        if (passaro != null) { //achou, então pode excluir
            passaro.setId(id);
            saida.imprimirNumeroInteiro("ID: ", id);
            saida.rotuloString("Nome: ", passaro.getNome());
            saida.rotuloString("Cor: ", passaro.getCor());
            saida.imprimirNumeroDouble("Peso: ", passaro.getPeso());
            if (entrada.lerConfirmacao("Excluir esse pássaro?")) {
                controle.excluir(passaro);
            }
        } else {
            System.out.println("Pássaro não cadastrado, exclusão impossível");
            entrada.pausaEnter();
        }
    }

    public GUITexto() {
        Entrada entrada = new Entrada();
        String caminho = "Macarrao.csv";
        //carregar dados do HD para memória RAM
        controle.carregarDados(caminho);
        
        
        int opcao = 0;
        while (opcao != 9) {
            tools.clearScreen();
            System.out.println("CRUD - Macarrao\n");
            System.out.println("\nMenu Principal\n");
            System.out.println("1 - Adicionar");
            System.out.println("2 - Buscar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Listar");
            System.out.println("9 - Sair");

            opcao = entrada.lerNumeroInteiro("Qual a opção");

            switch (opcao) {
                case 1:
                    telaCreate();
                    break;
                case 2:
                    telaRetrieve();
                    break;
                case 3:
                    telaUpdate();
                    break;
                case 4:
                    telaDelete();
                    break;
                case 5:
                    telaList();
                    break;
                case 9:
                    //antes de sair, transferir para o HD
                    controle.gravarLista(caminho);
                    System.out.println("\n\nBye");

                    break;

                default:
                    System.out.println("Opção errada, tente novamente");
            }

        }

    }

}
