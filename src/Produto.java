import java.util.HashMap;

/** 
* MIT License
*
* Copyright(c) 2024-26 João Caram <caram@pucminas.br>
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*/

public class Produto {
 
    private static final HashMap<String, Double> imposto;

    //#region atributos
    private String descricao;
    private double precoCusto;
    private double margemLucro;
    private String categoria;
    //#endregion

    //#region construtores
    static{
        imposto = new HashMap<>();
        imposto.put("INDUSTRIALIZADO", 0.18);
        imposto.put("ALIMENTO", 0.12);
        imposto.put("BEBIDA", 0.25);
    }

    /**
     * Cria um produto: descricao, preco de custo e sua margem de lucro. 
     * Valores inválidos serão registrados como R$0,01 e margem de 10%
     * @param descricao String com o nome do produto. Strings vazias resultarão em "Produto sem descrição"
     * @param precoCusto Preco de compra do produto. Deve ser maior que 0.
     * @param margemLucro Margem a ser aplicada para venda. Deve estar entre 0.1 e 0.5 (10 a 50%)
     */
    public Produto(String descricao, double precoCusto, double margemLucro, String categoria){
        if(descricao.length()==0)
            descricao = "Produto sem descrição";
        if(precoCusto <= 0)
            precoCusto = 0.01;
        if(margemLucro < 0.1 || margemLucro > 0.5)
            margemLucro = 0.1;
        if(!imposto.containsKey(categoria.toUpperCase()))
            categoria = "INDUSTRIALIZADO";
        
        this.descricao = descricao;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.margemLucro = precoCusto * margemLucro;
    }
    //#endregion

    //#region métodos
    /**
     * Retorna o valor de venda do produto (margem incorporada ao preço de custo, mais imposto).
     * @return Double positivo com o preço do produto
     */
    public double valorVenda(){
        return precoCusto + margemLucro + valorImposto();
    }

    /**
     * Retorna o valor do imposto cobrado sobre este produto (porcentagem sobre margem incorporada ao preço).<br>
     * A alíquota de imposto varia de acordo com a categoria do produto.
     * @return Double positivo com o valor do imposto
     */
    public double valorImposto(){
        return  (precoCusto + margemLucro) * imposto.get(categoria.toUpperCase());
    }
    
    /**
     * Cria um cupom simplificado, de uma linha, para o produto. Contém sua descrição e o preço de venda com 
     * 2 casas decimais.
     * @return String com os dados descritos.
     */
    public String cupomVenda(){
        return String.format("%s: R$ %.2f", descricao, valorVenda());
    }
   
    //#endregion
}
