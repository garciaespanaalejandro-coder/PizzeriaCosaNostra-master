package com.biblioteca.libro_spring;

import com.biblioteca.service.ServicioDummy;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.biblioteca") //Dentro del paquete com.biblioteca lea todo y todo lo que haya aquí lo carrgue
public class LibroSpringApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(LibroSpringApplication.class);

    @Autowired
    private ServicioDummy servicioDummy;


	public static void main(String[] args) {

		SpringApplication.run(LibroSpringApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Ejecutamos el metodo RUN");
        this.servicioDummy.hacerAlgo();
    }
}
