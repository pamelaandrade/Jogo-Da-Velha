package classes;

public class Tabuleiro {
    
    // ATRIBUTOS
	private int nrLinhas;  // DEFINE O NUMERO DE LINHAS
	private int nrColunas; // DEFINE O NUMERO DE COLUNAS
	private char tabuleiro[][]; // DEFINE UMA MATRIZ PARA AS PEï¿½AS DO TABULEIRO
    
    
    /** CRIA NOVA INSTANCIA DO TABULEIRO **/
    public Tabuleiro(int nrLinhas, int nrColunas) {
        // RECEBE O TAMANHO DO TABULEIRO
        this.nrLinhas = nrLinhas;
        this.nrColunas = nrColunas;
        
        // INSTANCIA A MATRIZ
        tabuleiro = new char [nrLinhas][nrColunas];
        
        this.esvaziarTabuleiro();
    }
    
    // Coloca uma peça numa determinada posicao do tabuleiro
    public void setPega (int linha, int coluna, char pega) {
        this.tabuleiro[linha][coluna] = pega;
    }
    
    // Retorna o simbolo de uma determinada posicao do tabuleiro
    public char getPega (int linha, int coluna) {
        return this.tabuleiro[linha][coluna];
    }
    
    
    // Retorna se uma determinada posicao do tabuleiro nao tem simbolo 
    public boolean estaLivre (int linha, int coluna) {
        return (this.tabuleiro[linha][coluna] == ' ');
    }
    
    // Limpa todo o tabuleiro 
    public void esvaziarTabuleiro() {
        
        int i,j;
        for (i=0; i<this.nrLinhas; i++)
            for (j=0; j<this.nrColunas; j++)
                this.tabuleiro[i][j] = ' ';
    }
    
    // Retorna o numero de Colunas do tabuleiro
    public int getNrColunas() {
        return nrColunas;
    }
    
    // Retorna o numero de Linhas do tabuleiro
    public int getNrLinhas() {
        return nrLinhas;
    }
    
    // Retorna quantidade de posicoes livres no tabuleiro
    public int getCountLivres () {
        int i,j,count=0;
            for (i=0; i<this.nrLinhas; i++){
                for (j=0; j<this.nrColunas; j++){
                    if (this.estaLivre(i,j)){
                        count++;
                    }
                }
            }
        return count;
    }
   
    // Mï¿½todo que mostrarï¿½ a lista de posiï¿½ï¿½es livres no tabuleio;
    public String[] getLista(boolean bLivres) {
        String lista[];
        if (bLivres) {
            lista = new String[getCountLivres()]; /* lista recebe uma string, chamando um metodo anterior para contar 
            quantas posiï¿½ï¿½es estao livres no tabuleiro*/
        } else { 
            lista = new String[nrColunas*nrLinhas]; // recebe uma string mostrando a posiï¿½ï¿½o que esta ocupada
        }
        
        int z = 0; //declaraï¿½ï¿½o de uma nova variavel iteira
        
        //lista os lugares vazios
        for(int i = 0; i < nrLinhas; i++) {
            for(int j = 0; j < nrColunas; j++) {
                if (estaLivre(i,j) || !bLivres) {
                    lista[z] = new String(Integer.toString(i)+Integer.toString((j)));
                    z++;
                }
            }
        }
        return(lista);
    }
    
}