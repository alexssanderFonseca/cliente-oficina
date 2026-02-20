package br.com.alexsdm.postech.oficina.cliente.core.domain.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidadorCpfCnpjTest {

    @ParameterizedTest
    @ValueSource(strings = {"12345678909", "52998224725", "123.456.789-09"})
    @DisplayName("Deve validar CPFs corretos")
    void deveValidarCpfCorretos(String cpf) {
        assertTrue(ValidadorCpfCnpj.isValido(cpf));
    }

    @ParameterizedTest
    @ValueSource(strings = {"00000000000191", "11222333000181", "00.000.000/0001-91"})
    @DisplayName("Deve validar CNPJs corretos")
    void deveValidarCnpjCorretos(String cnpj) {
        assertTrue(ValidadorCpfCnpj.isValido(cnpj));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11111111111", "12345678901", "1234567890", "123456789012345", "", " ", "abc"})
    @DisplayName("Deve invalidar CPFs ou CNPJs incorretos")
    void deveInvalidarCpfCnpjIncorretos(String valor) {
        assertFalse(ValidadorCpfCnpj.isValido(valor));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11111111111111", "00000000000000"})
    @DisplayName("Deve invalidar CNPJs com todos os digitos iguais")
    void deveInvalidarCnpjDigitosIguais(String cnpj) {
        assertFalse(ValidadorCpfCnpj.isValido(cnpj));
    }

    @Test
    @DisplayName("Deve invalidar nulo")
    void deveInvalidarNulo() {
        assertFalse(ValidadorCpfCnpj.isValido(null));
    }
}
