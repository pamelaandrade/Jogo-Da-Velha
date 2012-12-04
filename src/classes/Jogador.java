
package classes;

public class Jogador {
    
    // ATRIBUTOS
    private char simbolo; // contem o simbolo do jogador
    private int vitorias; // contem o numero de vitorias do jogador
    
    
    // CRIANDO NOVA INSTANCIA 
    public Jogador(char simbolo) {
        // recebe o simbolo para o jogador
        this.simbolo = simbolo;
    }
    
    // Incrementar em uma unidade o numero de vitorias do jogador 
    public void incrementaVitorias() {
        this.vitorias++;
    }
    
    // Zerar o numero de vitorias do jogador 
    public void zerarVitorias() {
        this.vitorias = 0;
    }
    
    // Retornar o simbolo do jogador 
    public char getSimbolo() {
        return simbolo;
    }
    
    // Retornar o numero de vitorias do jogador 
    public int getVitorias() {
        return this.vitorias;
    }
    
    
    
}
