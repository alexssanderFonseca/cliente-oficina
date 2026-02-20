package br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence.mapper;

import br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence.jpa.ClienteJpaEntity;
import br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence.jpa.EnderecoJpaEntity;
import br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence.jpa.VeiculoJpaEntity;
import br.com.alexsdm.postech.oficina.cliente.core.domain.entity.Cliente;
import br.com.alexsdm.postech.oficina.cliente.core.domain.entity.Endereco;
import br.com.alexsdm.postech.oficina.cliente.core.domain.entity.Veiculo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersistenceMappersTest {

    @Test
    @DisplayName("Deve retornar null quando passar null para ClienteMapper")
    void deveRetornarNullClienteMapper() {
        assertNull(ClienteMapper.toDomain(null));
        assertNull(ClienteMapper.toJpaEntity(null));
    }

    @Test
    @DisplayName("Deve retornar null quando passar null para EnderecoMapper")
    void deveRetornarNullEnderecoMapper() {
        assertNull(EnderecoMapper.toDomain(null));
        assertNull(EnderecoMapper.toJpaEntity(null));
    }

    @Test
    @DisplayName("Deve retornar null ou vazio quando passar null para VeiculoMapper")
    void deveRetornarNullVeiculoMapper() {
        assertNull(VeiculoMapper.toDomain((VeiculoJpaEntity) null));
        assertNull(VeiculoMapper.toJpaEntity((Veiculo) null));
        assertTrue(VeiculoMapper.toDomain((List<VeiculoJpaEntity>) null).isEmpty());
        assertTrue(VeiculoMapper.toJpaEntity((List<Veiculo>) null).isEmpty());
        assertTrue(VeiculoMapper.toDomain(Collections.emptyList()).isEmpty());
        assertTrue(VeiculoMapper.toJpaEntity(Collections.emptyList()).isEmpty());
    }
}
