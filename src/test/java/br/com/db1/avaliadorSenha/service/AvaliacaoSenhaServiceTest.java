package br.com.db1.avaliadorSenha.service;

import br.com.db1.avaliadorSenha.entity.AvaliacaoSenha;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by rafaeldcfarias on 25/08/17.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AvaliadorSenhaApplication.class)
public class AvaliacaoSenhaServiceTest {

    //@Autowired
    private AvaliacaoSenhaService avaliacaoSenhaService;

    @Before
    public void prepare() {
        avaliacaoSenhaService = new AvaliacaoSenhaService();
    }

    @Test
    public void pontuacaoDeveSer0_() throws Exception {
        String senha = "";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(0L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer3_A() throws Exception {
        String senha = "a";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(3L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer3_1() throws Exception {
        String senha = "1";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(3L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer10_aA() throws Exception {
        String senha = "aA";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(10L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer14_a1a() throws Exception {
        String senha = "a1a";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(17L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer12_a1aa() throws Exception {
        String senha = "a1aa";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(12L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer12_A1AA() throws Exception {
        String senha = "A1AA";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(12L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer24_1A11() throws Exception {
        String senha = "1A11";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(24L, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer2_ABC() throws Exception {
        String senha = "ABC";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(2, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer4_123() throws Exception {
        String senha = "123";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(4, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer29_ShiftAll123() throws Exception {
        String senha = "!@#";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(29, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer87_Shift1Aa11111() throws Exception {
        String senha = "!Aa11111";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(87, avaliacaoSenha.getPontuacao().longValue());
    }

    @Test
    public void pontuacaoDeveSer100_Shifta1shift1B2shift2C3shift3() throws Exception {
        String senha = "!a1!B2@C3#";
        AvaliacaoSenha avaliacaoSenha = avaliacaoSenhaService.avaliarSenha(senha);
        Assert.assertEquals(100, avaliacaoSenha.getPontuacao().longValue());
    }

}