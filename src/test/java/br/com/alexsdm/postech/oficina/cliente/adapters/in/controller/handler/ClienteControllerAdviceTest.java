package br.com.alexsdm.postech.oficina.cliente.adapters.in.controller.handler;

import br.com.alexsdm.postech.oficina.cliente.core.domain.exception.ClienteException;
import br.com.alexsdm.postech.oficina.cliente.core.domain.exception.ClienteNaoEncontradoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ClienteControllerAdviceTest {

    private final ClienteControllerAdvice advice = new ClienteControllerAdvice();

    @Test
    @DisplayName("Deve tratar ClienteException como BAD_REQUEST")
    void deveTratarClienteException() {
        var ex = new ClienteException("Erro cliente");
        var response = advice.handleMyCustomException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Erro cliente", response.getBody().getMessage());
        assertEquals(400, response.getBody().getStatus());
    }

    @Test
    @DisplayName("Deve tratar ClienteNaoEncontradoException como NOT_FOUND")
    void deveTratarClienteNaoEncontradoException() {
        var ex = new ClienteNaoEncontradoException();
        var response = advice.handleMyCustomException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Deve tratar MethodArgumentNotValidException como BAD_REQUEST")
    void deveTratarMethodArgumentNotValidException() {
        var ex = mock(MethodArgumentNotValidException.class);
        var response = advice.handleMyCustomException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Deve tratar IllegalArgumentException como BAD_REQUEST")
    void deveTratarIllegalArgumentException() {
        var ex = new IllegalArgumentException("Argumento invalido");
        var response = advice.handleMyCustomException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Argumento invalido", response.getBody().getMessage());
    }
}
