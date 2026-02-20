package br.com.alexsdm.postech.oficina.cliente.adapters.out.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNull;

class OrdemServicoParaClienteAdapterTest {

    private final OrdemServicoParaClienteAdapter adapter = new OrdemServicoParaClienteAdapter();

    @Test
    @DisplayName("Deve retornar null ao buscar status por cliente (não implementado)")
    void deveRetornarNull() {
        var result = adapter.buscarStatusPorCliente(UUID.randomUUID());
        assertNull(result);
    }
}
