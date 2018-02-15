import java.util.*;
import java.io.*;
import java.net.*;;
import java.io.IOException;;
/**
 *TP05Q09-> Ordenacao Countinsort
 *@author: Ana Flavia Dias
 */
class Series{
  //metodos privados
  private String nome;

  private String formato;
  private String duracao;
  private String paisDeOrigem;
  private String idiomaOriginal;
  private String emissoraDeTvOriginal;
  private String transmissaoOriginal;
  private int numeroDeTemporadas;
  private int numeroDeEpisodios;


  /**
	 *Primeiro Construtor para inicializar os metodos privados
	 */
  public Series(){
    nome="";
    formato="";
    duracao="";
    paisDeOrigem="";
    idiomaOriginal="";
    emissoraDeTvOriginal="";
    transmissaoOriginal="";
    numeroDeTemporadas=0;
    numeroDeEpisodios=0;
  }//fim construtor 1


  /**
	 *Segundo Construtor para atribuir valores aos  metodos privados
   *@param atributos da classe Series
	 */
  public Series(String nome, String formato, String duracao, String paisDeOrigem, String idiomaOriginal, String emissoraDeTvOriginal,
  String transmissaoOriginal, int numeroDeTemporadas, int numeroDeEpisodios){
    this.nome= nome;
    this.formato= formato;
    this.duracao= duracao;
    this.paisDeOrigem= paisDeOrigem;
    this.idiomaOriginal= idiomaOriginal;
    this.emissoraDeTvOriginal= emissoraDeTvOriginal;
    this.transmissaoOriginal= transmissaoOriginal;
    this.numeroDeTemporadas= numeroDeTemporadas;
    this.numeroDeEpisodios= numeroDeEpisodios;
  }//fim construtor 2


  /**
	 *Metodo que protege a classe Objeto
   *@return nova instancia da classe
	 */
  public Series clone(){
    return new Series(getNome(), getFormato(), getDuracao(), getPaisDeOrigem(), getIdiomaOriginal(), getEmissoraDeTvOriginal(),
    getTransmissaoOriginal(), getNumeroDeTemporadas(), getNumeroDeEpisodios());
  }//fim clone


  /**
	 *Metodos GET e SET
   *GETTERS
   *@return os atributos
   *SETTERS
   *@param atributos
	 */

  public String getNome(){
    return nome;
  }//fim getNome
  public void setNome(String nome){
    this.nome= nome;
  }//fim setNome


  public String getFormato(){
    return formato;
  }//fim getFormato
  public void setFormato(String formato){
    this.formato= formato;
  }//fim setFormato


  public String getDuracao(){
    return duracao;
  }//fim getDuracao
  public void setDuracao(String duracao){
    this.duracao= duracao;
  }//fim setDuracao


  public String getPaisDeOrigem(){
    return paisDeOrigem;
  }//fim getPaisDeOrigem
  public void setPaisDeOrigem(String paisDeOrigem){
    this.paisDeOrigem= paisDeOrigem;
  }//fim setPaisDeOrigem


  public String getIdiomaOriginal(){
    return idiomaOriginal;
  }//fim getIdiomaOriginal
  public void setIdiomaOriginal(String idiomaOriginal){
    this.idiomaOriginal= idiomaOriginal;
  }//fim setIdiomaOriginal


  public String getEmissoraDeTvOriginal(){
    return emissoraDeTvOriginal;
  }//fim getEmissoraDeTvOriginal
  public void setEmissoraDeTvOriginal(String emissoraDeTvOriginal){
    this.emissoraDeTvOriginal= emissoraDeTvOriginal;
  }//fim setEmissoraDeTvOriginal


  public String getTransmissaoOriginal(){
    return transmissaoOriginal;
  }//fim getTransmissaoOriginal
  public void setTransmissaoOriginal(String transmissaoOriginal){
    this.transmissaoOriginal= transmissaoOriginal;
  }//fim setTransmissaoOriginal


  public int getNumeroDeTemporadas(){
    return numeroDeTemporadas;
  }//fim getNumeroDeTemporadas
  public void setNumeroDeTemporadas(int numeroDeTemporadas){
    this.numeroDeTemporadas= numeroDeTemporadas;
  }//fim setNumeroDeTemporadas


  public int getNumeroDeEpisodios(){
    return numeroDeEpisodios;
  }//fim getNumeroDeEpisodios
  public void setNumeroDeEpisodios(int numeroDeEpisodios){
    this.numeroDeEpisodios= numeroDeEpisodios;
  }//fim setNumeroDeEpisodios


  /**
	 *Metodo que imprime o que foi lido na url
	 */
  public void imprimeHTML(){
    MyIO.println(getNome()+" "+getFormato()+" "+getDuracao()+" "+getPaisDeOrigem()+" "+getIdiomaOriginal()+" "+getEmissoraDeTvOriginal()+" "+getTransmissaoOriginal()+" "+getNumeroDeTemporadas()+" "+getNumeroDeEpisodios());
  }


  /**
	 *Metodo que abre e le o HTML
	 *@param html String
	 */
   public Series lerHTML(Series serie, String html){
     Arq.openRead("/tmp/" + html);//abre o arquivo da url
     int i;//ponteiro 1
     int j;//ponteiro 2
     String temp = Arq.readString();

     for(String linha = Arq.readLine(); Arq.hasNext(); linha = Arq.readLine()){//for que percorre toda o html

       if(linha.contains("class=\"firstHeading\"")){//if que pega o nome da serie
         i = linha.indexOf("<i>");
         j = linha.indexOf("</i>");
         serie.setNome(linha.substring(i+3, j));
         nome = nome.trim();
       }
       if(linha.contains("\">Formato<")){//if que acha o formato da serie
         linha = Arq.readLine();
         if(linha.contains("title=")){
           i = linha.indexOf("title=");
           j = linha.indexOf("</a>");
           serie.setFormato(linha.substring(i, j));
           i = formato.indexOf(">");
           serie.setFormato(formato.substring(i+1, formato.length()));
           formato = formato.trim();
           serie.setFormato(formato);
         }
         else if(linha.contains("<a>")){
           i = linha.indexOf("<a>");
           j = linha.indexOf("</a>");
           serie.setFormato(linha.substring(i+3, j));
           formato = formato.trim();
           serie.setFormato(formato);
         }
       }

       if(linha.contains("\">Duração</td>")){//if que acha a duracao da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         duracao = duracao.trim();
         serie.setDuracao(temp);
       }

       if(linha.contains(">País de origem<")){//if que acha a origem da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         paisDeOrigem = paisDeOrigem.trim();
         serie.setPaisDeOrigem(temp.trim());
       }

       if(linha.contains(">Idioma original<")){//if que acha o idioma da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         idiomaOriginal= idiomaOriginal.trim();
         serie.setIdiomaOriginal(temp.trim());
       }

       if(linha.contains(">Emissora de televisão original<")){//if que acha a emissora da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         emissoraDeTvOriginal= emissoraDeTvOriginal.trim();
         serie.setEmissoraDeTvOriginal(temp);
       }

       if(linha.contains(">Transmissão original<")){//if que acha a transmissao da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         transmissaoOriginal = transmissaoOriginal.trim();
         serie.setTransmissaoOriginal(temp);
       }

       if(linha.contains("\">N.º de temporadas<")){//if que acha o numero de temporadas da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         linha = temp;
         if(linha.contains(" ")){
           i = linha.indexOf(" ");
           linha = linha.substring(0, i);
         }
         i = Integer.parseInt(linha);
         serie.setNumeroDeTemporadas(i);
       }

       if(linha.contains("\">N.º de episódios<")){//if que acha o numero de episodios da serie
         linha = Arq.readLine();
         temp = linha.replaceAll("\\<[^>]*>","");
         temp = temp.replaceAll("&#160;","");
         temp = temp.replaceAll("&nbsp;","");
         linha = temp;
         if(linha.contains(" ")){
           i = linha.indexOf(" ");
           linha = linha.substring(0, i);
         }
         i = Integer.parseInt(linha);
         serie.setNumeroDeEpisodios(i);
       }
     }//fim for
     Arq.close();//fecha o arquivo da url
     return serie;
   }//fim lerHTML

   public boolean compara(Series serie){
     boolean resp= false;
     if(serie.getNome().equals(getNome())){
       resp= true;
     }
     return resp;
   }

}//fim class Series

//------------------------------------
class Lista {
   private Series[] array;
   private int n;

   /**
    * Construtor da classe.
    */
   public Lista () {
      this(6);
   }

   /**
    * Construtor da classe.
    * @param tamanho Tamanho da lista.
    */
   public Lista (int tamanho){
      array = new Series[tamanho];
      n = 0;
   }


   /**
    * Insere um elemento na primeira posicao da lista e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirInicio(Series x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }

      //levar elementos para o fim do array
      for(int i = n; i > 0; i--){
         array[i] = array[i-1];
      }

      array[0] = x;
      n++;
   }


   /**
    * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a lista estiver cheia.
    */
   public void inserirFim(Series x) throws Exception {

      //validar insercao
      if(n >= array.length){
         throw new Exception("Erro ao inserir!");
      }

      array[n] = x;
      n++;
   }


   /**
    * Insere um elemento em uma posicao especifica e move os demais
    * elementos para o fim da lista.
    * @param x int elemento a ser inserido.
    * @param pos Posicao de insercao.
    * @throws Exception Se a lista estiver cheia ou a posicao invalida.
    */
   public void inserir(Series x, int pos) throws Exception {

      //validar insercao
      if(n >= array.length || pos < 0 || pos > n){
         throw new Exception("Erro ao inserir!");
      }

      //levar elementos para o fim do array
      for(int i = n; i > pos; i--){
         array[i] = array[i-1];
      }

      array[pos] = x;
      n++;
   }


   /**
    * Remove um elemento da primeira posicao da lista e movimenta
    * os demais elementos para o inicio da mesma.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Series removerInicio() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      Series resp = array[0];
      n--;

      for(int i = 0; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }


   /**
    * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia.
    */
   public Series removerFim() throws Exception {

      //validar remocao
      if (n == 0) {
         throw new Exception("Erro ao remover!");
      }

      return array[--n];
   }


   /**
    * Remove um elemento de uma posicao especifica da lista e
    * movimenta os demais elementos para o inicio da mesma.
    * @param pos Posicao de remocao.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
    */
   public Series remover(int pos) throws Exception {

      //validar remocao
      if (n == 0 || pos < 0 || pos >= n) {
         throw new Exception("Erro ao remover!");
      }

      Series resp = array[pos];
      n--;

      for(int i = pos; i < n; i++){
         array[i] = array[i+1];
      }

      return resp;
   }

   /**
    * Mostra os elementos da lista separados por espacos.
    */

   public void mostrar (){
      for(int i = 0; i < n; i++){
         array[i].imprimeHTML();
      }
   }
   /**
    * Metodo que utiliza o algoritmo de ordenacao por selecao para ordenar as series por nome primeiramente
   */
   public void selecao(){
 		for(int i = 0; i < (n-1); i++){
 			int menor= i;
 			for(int j = (i+1); j < n; j++ ){
        if ((array[menor].getNome().compareTo(array[j].getNome())) > 0){
           menor= j;
        }
 			}
 			swap(menor, i);
 		}
  }

  /**
   * Algoritmo de ordenacao countingsort
   * ordena as series por numeroDeTemporadas
  */
  public void countingsort() {
    int[] count = new int[getMaior() + 1];  //Array para contar o numero de ocorrencias de cada elemento
    Series[] ordenado = new Series[n];

    //count[i] contem o numero de elemento iguais a i
    for (int i = 0; i < n; count[array[i].getNumeroDeTemporadas()]++, i++);

    //count[i] contem o numero de elemento menores OU iguais a i
    for (int i = 1; i < count.length; count[i] += count[i- 1], i++);

    //Ordenando
    for (int i = n-1; i >= 0; ordenado[count[array[i].getNumeroDeTemporadas()]-1] = array[i], count[array[i].getNumeroDeTemporadas()]--, i--);
    for (int i = 0; i < n; array[i] = ordenado[i], i++);
  }

  /**
    * Metodo para calcular o numero de ocorrencias de cada elemento
    * @return maior int
  */
  public int getMaior() {
    int maior= array[0].getNumeroDeTemporadas();
    for (int i= 0; i < n; i++) {
      if(maior < array[i].getNumeroDeTemporadas()){
        maior = array[i].getNumeroDeTemporadas();
      }
    }
    return maior;
  }

  /**
   * Inverte os parametros de posicao
   * @param i
   * @param j
  */
  public void swap(int i, int j){
    Series temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
//-----------------------------------------------------------------------------------------------------

class TP05Q09OrdenacaoCoutingsort{

	public static void main (String[]args) throws Exception{
    MyIO.setCharset("UTF-8");
    //String [] string= new String[1000];
		//int qt= 0;
    //int pos= 0;
    //String codigo= "";
    String html= MyIO.readLine();
    Lista lista= new Lista(2000);//referente a classe Lista
    Series series= new Series();//referente a classe Series
		//leitura de string padrao

    while(!html.equals("FIM")){
      series.lerHTML(series, html);
      lista.inserirFim(series);
      html= MyIO.readLine();
      series= new Series();
    }
    lista.selecao();
    lista.countingsort();
    lista.mostrar();

  }//fim main

}//fim TP05Q09OrdenacaoCoutingsort
