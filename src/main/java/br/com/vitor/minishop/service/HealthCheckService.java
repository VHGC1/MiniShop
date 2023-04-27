package br.com.vitor.minishop.service;

import br.com.vitor.minishop.component.DatabaseSession;
import br.com.vitor.minishop.component.MiniShopS3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final DatabaseSession databaseSession;

    private final MiniShopS3Client miniShopS3Client;

    /**
     * Health Check manual
     * caso queira implementar um healthcheck mais robusto veja o link abaixo
     * @see <a href="https://www.baeldung.com/spring-boot-actuators">Spring Boot Actuator</a>
     */
    public void check() {
        checkDatabase();
        checkPresigner();
        checkS3();
    }

    private void checkS3() {
        var bucketRequest = HeadBucketRequest.builder()
                .bucket(miniShopS3Client.getBucketName())
                .build();

        miniShopS3Client.getS3Client().headBucket(bucketRequest);
    }

    private void checkPresigner() {
        var getObjetctRequest = GetObjectRequest.builder()
                .bucket(miniShopS3Client.getBucketName())
                .key("teste")
                .build();

        var buckerRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjetctRequest)
                .signatureDuration(Duration.ofMinutes(10))
                .build();

        miniShopS3Client.getPreSigner().presignGetObject(buckerRequest);
    }

    private void checkDatabase() {
        //
        var session = databaseSession.getHibernateFactory().openSession();
        session.createSQLQuery("SELECT 1").getSingleResult();
        session.close();
    }
}
