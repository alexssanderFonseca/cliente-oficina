package br.com.alexsdm.postech.oficina.cliente.adapters.in.controller;

import br.com.alexsdm.postech.oficina.cliente.adapters.in.controller.mapper.ClienteDTOMapper;
import br.com.alexsdm.postech.oficina.cliente.adapters.in.controller.request.AdicionarDadosVeiculoRequest;
import br.com.alexsdm.postech.oficina.cliente.adapters.in.controller.request.AtualizarClienteRequest;
import br.com.alexsdm.postech.oficina.cliente.adapters.in.controller.request.CadastrarClienteRequest;
import br.com.alexsdm.postech.oficina.cliente.adapters.in.controller.request.EnderecoRequest;
import br.com.alexsdm.postech.oficina.cliente.core.port.in.*;
import br.com.alexsdm.postech.oficina.cliente.core.usecase.input.AdicionarVeiculoInput;
import br.com.alexsdm.postech.oficina.cliente.core.usecase.input.AtualizarClienteInput;
import br.com.alexsdm.postech.oficina.cliente.core.usecase.input.CadastrarClienteInput;
import br.com.alexsdm.postech.oficina.cliente.core.usecase.output.AdicionarVeiculoOutput;
import br.com.alexsdm.postech.oficina.cliente.core.usecase.output.BuscarClientePorIdOutput;
import br.com.alexsdm.postech.oficina.cliente.core.usecase.output.CadastrarClienteOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteControllerTest {

    @Mock
    private CadastrarClienteUseCase cadastrarClienteUseCase;
    @Mock
    private BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    @Mock
    private AdicionarVeiculoUseCase adicionarVeiculoUseCase;
    @Mock
    private DeletarClienteUseCase deletarClienteUseCase;
    @Mock
    private AtualizarClienteUseCase atualizarClienteUseCase;
    @Mock
    private ListarStatusOsClienteUseCase listarStatusOsClienteUseCase;
    @Mock
    private ClienteDTOMapper clienteDTOMapper;

    @InjectMocks
    private ClienteController clienteController;

    private UUID clienteId;

    @BeforeEach
    void setUp() {
        clienteId = UUID.randomUUID();
    }

    @Test
    @DisplayName("Deve cadastrar um cliente com sucesso")
    void deveCadastrarCliente() {
        var enderecoRequest = new EnderecoRequest("Rua", "123", "Bairro", "Cidade", "CEP", "UF");
        var request = new CadastrarClienteRequest("Nome", "Sobrenome", "12345678909", "email@test.com", "11999999999", enderecoRequest);
        var output = new CadastrarClienteOutput(clienteId);

        when(clienteDTOMapper.toInput(any())).thenReturn(mock(CadastrarClienteInput.class));
        when(cadastrarClienteUseCase.executar(any())).thenReturn(output);

        var response = clienteController.cadastrar(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(cadastrarClienteUseCase, times(1)).executar(any());
    }

    @Test
    @DisplayName("Deve buscar um cliente por id com sucesso")
    void deveBuscarClientePorId() {
        var output = BuscarClientePorIdOutput.builder().id(clienteId).build();
        when(buscarClientePorIdUseCase.executar(clienteId)).thenReturn(output);

        var response = clienteController.buscar(clienteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
    }

    @Test
    @DisplayName("Deve deletar um cliente com sucesso")
    void deveDeletarCliente() {
        var response = clienteController.deletar(clienteId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(deletarClienteUseCase, times(1)).executar(any());
    }

    @Test
    @DisplayName("Deve atualizar um cliente com sucesso")
    void deveAtualizarCliente() {
        var request = new AtualizarClienteRequest(null, null, null);
        var output = BuscarClientePorIdOutput.builder().id(clienteId).build();

        when(clienteDTOMapper.toAtualizarClienteInput(any(), any())).thenReturn(mock(AtualizarClienteInput.class));
        when(atualizarClienteUseCase.executar(any())).thenReturn(output);

        var response = clienteController.atualizar(clienteId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(output, response.getBody());
    }

    @Test
    @DisplayName("Deve adicionar um veiculo com sucesso")
    void deveAdicionarVeiculo() {
        var request = new AdicionarDadosVeiculoRequest("Placa", "Cor", "Marca", "Modelo", "2020");
        var veiculoId = UUID.randomUUID();
        var output = new AdicionarVeiculoOutput(veiculoId);

        when(clienteDTOMapper.toAdicionarVeiculoInput(any(), any())).thenReturn(mock(AdicionarVeiculoInput.class));
        when(adicionarVeiculoUseCase.executar(any())).thenReturn(output);

        var response = clienteController.adicionarVeiculo(clienteId, request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(output, response.getBody());
    }

    @Test
    @DisplayName("Deve listar status das ordens de serviço")
    void deveListarStatusOs() {
        when(listarStatusOsClienteUseCase.executar(any())).thenReturn(Collections.emptyList());

        var response = clienteController.statusByCliente(clienteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(listarStatusOsClienteUseCase, times(1)).executar(any());
    }
}
