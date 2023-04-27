package br.com.vitor.minishop.service;

import br.com.vitor.minishop.component.MiniShopS3Client;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadImageService {
    private static MiniShopS3Client miniShopS3Client;

    public UploadImageService(MiniShopS3Client miniShopS3Client) {
        this.miniShopS3Client = miniShopS3Client;
    }

    public String getPressignedURL(String fileName) {
        try {
            String bucketName = miniShopS3Client.getBucketName();
            S3Presigner presigner = miniShopS3Client.getPreSigner();

            // Criando objetos de requisição
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))
                    .putObjectRequest(objectRequest)
                    .build();

            // Gera URL e retorna
            PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
            return presignedRequest.url().toString();

        } catch (S3Exception e) {
            e.getStackTrace();
            throw e;
        }
    }

    public static void deleteFromBucket(List<String> urls) {
        try {
            String bucketName = miniShopS3Client.getBucketName();
            S3Client s3Client = miniShopS3Client.getS3Client();

            // Lista de objetos que serão deletados
            List<ObjectIdentifier> keys = urls.stream().map(url -> {
                String fileName = url.split("/")[4];
                return ObjectIdentifier.builder()
                        .key(fileName)
                        .build();
            }).collect(Collectors.toList());

            // Construindo requests
            Delete deleteRequest = Delete.builder()
                    .objects(keys)
                    .build();

            DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder()
                    .bucket(bucketName)
                    .delete(deleteRequest)
                    .build();

            // Deleta objetos
            s3Client.deleteObjects(multiObjectDeleteRequest);
        } catch (S3Exception ex) {
            // Garante que exceção será tratada
            ex.printStackTrace();
            throw ex;
        }
    }
}
