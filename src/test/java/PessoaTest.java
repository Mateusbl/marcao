import com.example.Pessoa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PessoaTest {

    // Classe auxiliar concreta (já que Pessoa é abstrata)
    private static class PessoaFake extends Pessoa {
        public PessoaFake() {
            super("00000000000", "Teste", "Rua", "00000000", "email@teste.com");
        }
    }

    @Test
    public void testPerfilMenorDeIdadeDesempregadoSemBens() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(16, false, false, false, 0, 0, false, false, false, false);
        assertTrue(resultado.contains("Menor de idade."));
        assertTrue(resultado.contains("Desempregado."));
        assertTrue(resultado.contains("Não possui carro nem casa."));
    }

    @Test
    public void testPerfilJovemAdultoEmpregadoAltaRendaComTudo() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(25, true, true, true, 6000, 2, true, true, true, true);
        assertTrue(resultado.contains("Jovem adulto."));
        assertTrue(resultado.contains("Empregado."));
        assertTrue(resultado.contains("Alta renda."));
        assertTrue(resultado.contains("Possui carro e casa."));
        assertTrue(resultado.contains("Tem dependentes."));
        assertTrue(resultado.contains("Possui plano de saúde."));
        assertTrue(resultado.contains("Possui dívidas."));
        assertTrue(resultado.contains("Faz faculdade."));
        assertTrue(resultado.contains("Pratica esportes."));
    }

    @Test
    public void testPerfilAdultoEmpregadoRendaMediaApenasCasa() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(35, true, false, true, 3000, 0, false, false, false, false);
        assertTrue(resultado.contains("Adulto."));
        assertTrue(resultado.contains("Empregado."));
        assertTrue(resultado.contains("Renda média."));
        assertTrue(resultado.contains("Possui apenas casa."));
    }

    @Test
    public void testPerfilIdosoEmpregadoBaixaRendaApenasCarro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(65, true, true, false, 1500, 0, false, false, false, false);
        assertTrue(resultado.contains("Idoso."));
        assertTrue(resultado.contains("Empregado."));
        assertTrue(resultado.contains("Baixa renda."));
        assertTrue(resultado.contains("Possui apenas carro."));
    }

    @Test
    public void testPerfilAdultoDesempregadoSemBensComDependentesEOutros() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(45, false, false, false, 0, 3, true, true, true, true);
        assertTrue(resultado.contains("Adulto."));
        assertTrue(resultado.contains("Desempregado."));
        assertTrue(resultado.contains("Tem dependentes."));
        assertTrue(resultado.contains("Possui plano de saúde."));
        assertTrue(resultado.contains("Possui dívidas."));
        assertTrue(resultado.contains("Faz faculdade."));
        assertTrue(resultado.contains("Pratica esportes."));
    }

    @Test
    public void testApenasEmpregadoVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, true, false, false, 1000, 0, false, false, false, false);
        assertTrue(resultado.contains("Empregado."));
    }

    @Test
    public void testApenasPossuiCarroVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, true, false, 0, 0, false, false, false, false);
        assertTrue(resultado.contains("Possui apenas carro."));
    }

    @Test
    public void testApenasPossuiCasaVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, false, true, 0, 0, false, false, false, false);
        assertTrue(resultado.contains("Possui apenas casa."));
    }

    @Test
    public void testApenasPossuiPlanoSaudeVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, false, false, 0, 0, true, false, false, false);
        assertTrue(resultado.contains("Possui plano de saúde."));
    }

    @Test
    public void testApenasPossuiDividasVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, false, false, 0, 0, false, true, false, false);
        assertTrue(resultado.contains("Possui dívidas."));
    }

    @Test
    public void testApenasFazFaculdadeVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, false, false, 0, 0, false, false, true, false);
        assertTrue(resultado.contains("Faz faculdade."));
    }

    @Test
    public void testApenasPraticaEsporteVerdadeiro() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, false, false, 0, 0, false, false, false, true);
        assertTrue(resultado.contains("Pratica esportes."));
    }

    @Test
    public void testApenasTemDependentes() {
        Pessoa pessoa = new PessoaFake();
        String resultado = pessoa.avaliarPerfil(40, false, false, false, 0, 2, false, false, false, false);
        assertTrue(resultado.contains("Tem dependentes."));
    }
}
