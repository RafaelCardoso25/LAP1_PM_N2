/** 
* MIT License
*
* Copyright(c) 2026 João Caram <caram@pucminas.br>
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

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    Produto bebida;
    
    @BeforeEach 
    public void setUp(){
        //Arrange
        bebida = new Produto("Meque Xate",10 , .2, "bebida");
    }

    @Test
    public void calculaPrecoCorretamente(){
        //Act
        double preco = bebida.valorVenda();
        //Assert
        assertEquals(10*1.2*1.25, preco, 0.01); //preco + margem + imposto
    }
    
    @Test 
    public void naoAceitaMargemInvalida(){
        bebida = new Produto("Meque Xate",10 , 0, "bebida");
        double preco = bebida.valorVenda();

        assertEquals(10*1.1*1.25, preco, 0.01);     //preco + margem + imposto
    }

    @Test 
    public void calculaImpostoBebida(){
        //Act
        double imposto = bebida.valorImposto();
        //Assert 
        assertEquals(3d, imposto, 0.01);
    }

    @Test 
    public void calculaImpostoAlimento(){
        //Arrange
        Produto alimento = new Produto("Muito Arroz", 10, 0.5, "alimento");
        //Act
        double imposto = alimento.valorImposto();
        //Assert 
        assertEquals(1.8d, imposto, 0.01);
    }

    @Test 
    public void calculaImpostoIndustrializado(){
        //Arrange
        Produto caderno = new Produto("Caderno", 16, .25 , "industrializado");
        //Act
        double imposto = caderno.valorImposto();
        //Assert 
        assertEquals(3.6, imposto, 0.01);
    }

    @Test 
    public void cupomComDetalhes(){
        //Arrange
        double preco = 10*1.2*1.25;
        //Act
        String cupom = bebida.cupomVenda();
        //Assert
        assertTrue(cupom.contains("Meque") && cupom.contains(String.format("%.2f",preco)));
    }

}
