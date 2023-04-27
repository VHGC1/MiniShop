package br.com.vitor.minishop.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Component
@Configuration
@RequiredArgsConstructor
public class MiniShopS3Client {

    /**
     * Informações de configuração do spring
     * @see <a href="https://www.baeldung.com/spring-profiles">Spring Profiles</a>
     */
    private final Environment environment;

    /**
     * Recupera nome do bucket das configurações
     * Caso não exista retorna a string "minishop-imagens"
     */
    @Value("${aws.bucket.name:minishop-imagens}")
    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    /**
     * Recupera conexão com AWS S3 de acordo com ambiente
     * @apiNote @Bean Para que ele seja criado ao inicializar o Spring
     * @see <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-s3.html">Working with Amazon S3</a>
     * @see <a href="https://sdk.amazonaws.com/java/api/latest/software/amazon/awssdk/services/s3/S3Client.html">Documentação</a>
     * @return S3Client cliente de conexão
     */
    @Bean
    public S3Client getS3Client() {
        // Em ambiente de desenvolvimento apontamos para o localstack
        if (environment.acceptsProfiles(Profiles.of("default"))) {
            return S3Client.builder()
                    .endpointOverride(URI.create("http://localhost:4566"))
                    .build();
        }

        // Em outros ambientes usamos a configuracao padrao
        return S3Client.create();
    }

    /**
     * Recupera AWS S3 Presigner de acordo com ambiente
     * @apiNote @Bean Para que ele seja criado ao inicializar o Spring
     * @see <a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-s3-presign.html">Working with Amazon S3 Presigner</a>
     * @return AWS S3 Presigner
     */
    @Bean
    public S3Presigner getPreSigner() {
        // Em ambiente de desenvolvimento apontamos para o localstack
        if (environment.acceptsProfiles(Profiles.of("default"))) {
            return S3Presigner.builder()
                    .endpointOverride(URI.create("http://localhost:4566"))
                    .build();
        }

        // Em outros ambientes usamos a configuracao padrao
        return S3Presigner.create();
    }
}
