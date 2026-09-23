import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendaDiaTest {
    
    VendaDia vendaDia;

    @BeforeEach
    public void SetUp(){
        vendaDia = new VendaDia(LocalDate.of(2026, 9, 23));
    }
    
    @Test 
    public void adicionaProdutoCorretamente(){
        Produto bebida = new Produto("suco", 10, 0.2, "bebida");
        Produto alimento = new Produto("arroz", 20, 0.5, "alimento");
        vendaDia.adicionarProduto(bebida);
        double total = vendaDia.adicionarProduto(alimento);
        double totalPos = vendaDia.adicionarProduto(null);
        assertEquals(48.6, total, 0.01);
        assertEquals(48.6, totalPos, 0.01);
    }

    @Test 
    public void classificaDiaBomCorretamente(){
        Produto bebida = new Produto("agua", 1000, 0.2, "bebida");
        vendaDia.adicionarProduto(bebida);
        assertEquals("bom", vendaDia.classificacao());
    }

    @Test 
    public void verificaDiaMelhorQueOOutro(){
        vendaDia.adicionarProduto(new Produto("agua", 1000, 0.2, "bebida"));
        VendaDia dia1 = new VendaDia(LocalDate.of(2026, 9, 22));
        dia1.adicionarProduto(new Produto("Tampa", 500, 0.2, "material"));
        assertTrue(vendaDia.melhorQue(dia1));
        assertFalse(dia1.melhorQue(vendaDia));
    }
}
