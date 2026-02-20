package br.com.alexsdm.postech.oficina.cliente.core.domain.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorPlacaVeiculoTest {

    @ParameterizedTest
    @ValueSource(strings = {"ABC-1234", "XYZ-9876", "abc-1234"})
    @DisplayName("Deve validar placas de padrão antigo")
    void deveValidarPlacaAntiga(String placa) {
        assertTrue(ValidadorPlacaVeiculo.isValida(placa));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC1D23", "ZXY0E99", "abc1d23"})
    @DisplayName("Deve validar placas de padrão Mercosul")
    void deveValidarPlacaMercosul(String placa) {
        assertTrue(ValidadorPlacaVeiculo.isValida(placa));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC-123", "ABC-12345", "ABC1D2", "ABC1D234", "", " ", "abc"})
    @DisplayName("Deve invalidar placas incorretas")
    void deveInvalidarPlacasIncorretas(String placa) {
        assertFalse(ValidadorPlacaVeiculo.isValida(placa));
    }

    @Test
    @DisplayName("Deve invalidar nulo")
    void deveInvalidarNulo() {
        assertFalse(ValidadorPlacaVeiculo.isValida(null));
    }
}
