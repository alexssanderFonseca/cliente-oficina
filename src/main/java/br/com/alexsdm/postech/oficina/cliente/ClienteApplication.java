package br.com.alexsdm.postech.oficina.cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class ClienteApplication {

    private static final Logger logger = LoggerFactory.getLogger(ClienteApplication.class);

    public static void main(String[] args) {
        logger.info("Aplicação iniciada com sucesso.");
        SpringApplication.run(ClienteApplication.class, args);
    }

}
