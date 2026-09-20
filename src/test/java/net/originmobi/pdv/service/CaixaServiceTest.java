package net.originmobi.pdv.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.repository.CaixaRepository;

@RunWith(MockitoJUnitRunner.class)
public class CaixaServiceTest {

    @Mock
    private CaixaRepository caixas;

    @InjectMocks
    private CaixaService service;

    @Test
    public void caixaIsAbertoDeveRetornarTrue() {
        when(caixas.caixaAberto()).thenReturn(Optional.of(mock(Caixa.class)));

        assertTrue(service.caixaIsAberto());
        verify(caixas).caixaAberto();
    }

    @Test
    public void caixaIsAbertoDeveRetornarFalse() {
        when(caixas.caixaAberto()).thenReturn(Optional.empty());

        assertFalse(service.caixaIsAberto());
    }

    @Test
    public void listaTodosDeveRetornarListaDoRepository() {
        List<Caixa> lista = Arrays.asList(mock(Caixa.class), mock(Caixa.class));
        when(caixas.findByCodigoOrdenado()).thenReturn(lista);

        assertEquals(lista, service.listaTodos());
        verify(caixas).findByCodigoOrdenado();
    }

    @Test
    public void buscaDeveRetornarCaixaPeloCodigo() {
        Caixa caixa = mock(Caixa.class);
        when(caixas.findById(10L)).thenReturn(Optional.of(caixa));

        Optional<Caixa> resultado = service.busca(10L);

        assertTrue(resultado.isPresent());
        assertEquals(caixa, resultado.get());
    }
}