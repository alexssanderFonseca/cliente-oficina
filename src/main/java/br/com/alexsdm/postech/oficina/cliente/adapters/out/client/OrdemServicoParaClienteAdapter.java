package br.com.alexsdm.postech.oficina.cliente.adapters.out.client;

import br.com.alexsdm.postech.oficina.cliente.core.domain.entity.OrdemServicoStatus;
import br.com.alexsdm.postech.oficina.cliente.core.port.out.ClienteOrdemServicoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrdemServicoParaClienteAdapter implements ClienteOrdemServicoPort {


    @Override
    public List<OrdemServicoStatus> buscarStatusPorCliente(UUID clienteId) {

        return null;
    }
}
