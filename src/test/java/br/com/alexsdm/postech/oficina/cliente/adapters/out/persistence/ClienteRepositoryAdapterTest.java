package br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence;

import br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence.jpa.ClienteJpaEntity;
import br.com.alexsdm.postech.oficina.cliente.adapters.out.persistence.jpa.ClienteJpaRepository;
import br.com.alexsdm.postech.oficina.cliente.core.domain.entity.Cliente;
import br.com.alexsdm.postech.oficina.cliente.core.domain.entity.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteRepositoryAdapterTest {

    @Mock
    private ClienteJpaRepository repository;

    @InjectMocks
    private ClienteRepositoryAdapter adapter;

    private UUID clienteId;
    private Cliente cliente;
    private ClienteJpaEntity entity;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
        var endereco = Endereco.builder()
                .rua("Rua")
                .numero("123")
                .bairro("Bairro")
                .cep("12345-678")
                .cidade("Cidade")
                .uf("TS")
                .build();
        cliente = Cliente.builder()
                .id(clienteId)
                .nome("Nome")
                .sobrenome("Sobrenome")
                .cpfCnpj("12345678909")
                .email("email@test.com")
                .telefone("11999999999")
                .endereco(endereco)
                .build();
        entity = new ClienteJpaEntity(clienteId, "Nome", "Sobrenome", "12345678909", "email@test.com", null, "11999999999", Collections.emptyList());
    }

    @Test
    @DisplayName("Deve salvar cliente com sucesso")
    void deveSalvarCliente() {
        when(repository.save(any(ClienteJpaEntity.class))).thenReturn(entity);

        var result = adapter.salvar(cliente);

        assertNotNull(result);
        assertEquals(clienteId, result.getId());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve buscar cliente por id")
    void deveBuscarPorId() {
        when(repository.findById(clienteId)).thenReturn(Optional.of(entity));

        var result = adapter.buscarPorId(clienteId);

        assertTrue(result.isPresent());
        assertEquals(clienteId, result.get().getId());
    }

    @Test
    @DisplayName("Deve buscar cliente por documento")
    void deveBuscarPorDocumento() {
        String documento = "12345678909";
        when(repository.findByCpfCnpj(documento)).thenReturn(Optional.of(entity));

        var result = adapter.buscarPorDocumento(documento);

        assertTrue(result.isPresent());
        assertEquals(documento, result.get().getCpfCnpj());
    }

    @Test
    @DisplayName("Deve deletar cliente")
    void deveDeletar() {
        doNothing().when(repository).deleteById(clienteId);

        adapter.deletar(clienteId);

        verify(repository, times(1)).deleteById(clienteId);
    }

    @Test
    @DisplayName("Deve verificar se veiculo ja existe")
    void deveVerificarSeVeiculoExiste() {
        String placa = "ABC1234";
        when(repository.existsVeiculoByPlaca(placa)).thenReturn(true);

        var result = adapter.isVeiculoJaExistente(placa);

        assertTrue(result);
    }
}
