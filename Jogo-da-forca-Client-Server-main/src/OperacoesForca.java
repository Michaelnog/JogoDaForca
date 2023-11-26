import java.util.Locale;
import java.util.Scanner;

public class OperacoesForca {

    private String[][] palavras;
    private String palavraVazia;
    private int errosRestantes;

    private String palavra;
    public void novoJogo() {
        this.errosRestantes = 5;
        this.bibliotecaDePalavras();
        this.constroiTela();
    }
    private void constroiTela() {
        //sorteia palavra
        int sorteada = this.sortearPalavra();
        //palavra sorteada
        palavra = this.palavras[sorteada][0];
        //dica da palavra sorteada
        String dica = this.palavras[sorteada][1];
        //substitui as letras da palavra sorteada por '_'
        this.palavraVazia = this.palavraVazia(palavra.length());
        //entrada de dados para começar o jogo
        Scanner in = new Scanner(System.in);
        //aqui diz que enquanto você não ganha nem perde terá que mostrar quantas chances você tem
        while (!this.ganhou(palavra) && !this.perdeu(palavra)) {

            System.out.printf("====================================" +
                             "\n       Forca Cliente/Servidor:      " +
                             "\n       Você tem %d chances          " +
                             "\n       Dica da palavra: %s          " +
                             "\n       Palavra: %s                 " +
                             "\n       Digite uma letra:"
                             , this.errosRestantes, dica, palavraVazia);
            this.tentativa(in.nextLine(), palavra);
            System.out.println("====================================");
        }
    }
    private void bibliotecaDePalavras() {
        //Adicionar Palavras e Dicas a nossa Array de Strings Palavras onde 0 é a palavra e 1 é a dica
        this.palavras = new String[][]{
                {"Sorte", "O que você precisa ter para ganhar um sorteio!"},
                {"Amor", "O que sentimos por alguem especial!"},
                {"Matematica", "Matéria de numeros!"},
                {"Alfabeto", "Contem muitas letras!"},
                {"VideoGame", "Aparelho muito divertido que se usa controles!"}
        };
    }
    private int sortearPalavra() {
        //Random de 0 até o Length da nossa Array de Palavras
        return (int) (Math.random() * this.palavras.length);
    }

    //Função retorna que voce perdeu o jogo
    private boolean perdeu(String palavra) {
        if (this.errosRestantes == 0) {
            System.out.println("==================================" +
                               "\n||         VOCÊ PERDEU         ||" +
                               "\n||    A PALAVRA CORRETA ERA:   \n||" +
                                              palavra+
                               "\n||                             ||" +
                             "\n===================================");
            return true;
        }
        return false;
    }
    //Função retorna que voce ganhou o jogo
    private boolean ganhou(String palavra) {
        for (int i = 0; i < this.palavraVazia.length(); i++) {
            if (palavraVazia.charAt(i) == '_') {
                return false;
            }
        }
        System.out.println( "==================================" +
                            "\n||         VOCÊ GANHOU         ||" +
                            "\n||    A PALAVRA CORRETA ERA:   ||" +
                            "\n||      \n"+palavra+ "           ||" +
                            "\n||                             ||" +
                            "\n==================================");
        return true;
    }
    private void tentativa(String letra, String palavra) {
        //verifica se a palavra possui a letra inserida
        if (palavra.toLowerCase().contains(letra.toLowerCase())) {
            //se sim, vai verificar a posição dela
            for (int i = 0; i < palavra.length(); i++) {
                if (letra.toLowerCase().charAt(0) == palavra.toLowerCase().charAt(i)) {
                    StringBuilder local = new StringBuilder(this.palavraVazia);
                    //ao encontrar, muda o campo pela letra correspondente
                    local.setCharAt(i, letra.toUpperCase().charAt(0));
                    this.palavraVazia = local.toString();
                }
            }
        } else {
            //se não, diminui as chances restantes
            this.errosRestantes--;
        }
    }
    //Função que substitui as letras da palavra escolhida por '_'
    private String palavraVazia(int tamanho) {
        String palavra = "";
        for (int i = 0; i < tamanho; i++) {
            palavra += "_";
        }
        return palavra;
    }
    public String toString(){
        if (this.ganhou(palavra)){
            return "o jogador ganhou! A palavra correta é"  + palavra;
        }else{
            return "O jogador perdeu! A palavra correta é"  + palavra;
        }

    }

}
