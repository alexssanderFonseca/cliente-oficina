CREATE TABLE endereco
(
    id     UUID PRIMARY KEY,
    rua    VARCHAR(255) NOT NULL,
    numero varchar(10)  NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    cep    VARCHAR(20)  NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    uf     VARCHAR(2)   NOT NULL
);

CREATE TABLE cliente
(
    id          UUID PRIMARY KEY,
    nome        VARCHAR(255)       NOT NULL,
    sobrenome   VARCHAR(255)       NOT NULL,
    cpf_cnpj     VARCHAR(14) UNIQUE NOT NULL,
    email       VARCHAR(255),
    telefone    VARCHAR(20),
    endereco_id UUID UNIQUE,
    CONSTRAINT fk_endereco
        FOREIGN KEY (endereco_id)
            REFERENCES endereco (id)
            ON DELETE CASCADE
);

CREATE TABLE veiculo
(
    id         UUID PRIMARY KEY,
    placa      VARCHAR(8) UNIQUE NOT NULL,
    cor        VARCHAR(30) NOT NULL,
    ano        VARCHAR(4) NOT NULL,
    marca      VARCHAR(50)  NOT NULL,
    modelo     VARCHAR(50)  NOT NULL,
    cliente_id UUID NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY (cliente_id)
            REFERENCES cliente (id)
            ON DELETE CASCADE
);
