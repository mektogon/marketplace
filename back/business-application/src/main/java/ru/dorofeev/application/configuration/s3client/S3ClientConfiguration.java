package ru.dorofeev.application.configuration.s3client;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.retries.api.BackoffStrategy;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Configuration
@RequiredArgsConstructor
public class S3ClientConfiguration {

    private final S3ClientProperties properties;

    @Bean
    public S3AsyncClient s3AsyncClient() {
        return S3AsyncClient.builder()
                .httpClient(nettyNioAsyncHttpClient())
                .region(Region.of(properties.getRegion()))
                .overrideConfiguration(currentProperties -> currentProperties
                        .apiCallTimeout(properties.getS3ClientApiCallTimeOut())
                        .apiCallAttemptTimeout(properties.getS3ClientApiCallAttemptTimeout())
                        .retryStrategy(policy ->
                                policy.maxAttempts(properties.getS3ClientRetryPolicyMaxAttempts())
                                        .backoffStrategy(
                                                BackoffStrategy.fixedDelay(
                                                        properties.getS3ClientRetryPolicyBackoffFixedDelay()
                                                )
                                        )
                        )
                ).credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(
                                        properties.getS3CredentialsKeyAccessId(),
                                        properties.getS3CredentialsKeyAccessSecret()
                                )
                        )
                )
                .build();
    }

    @Bean
    public SdkAsyncHttpClient nettyNioAsyncHttpClient() {
        return NettyNioAsyncHttpClient.builder()
                .connectionTimeout(properties.getHttpNettyClientConnectionTimeOut())
                .writeTimeout(properties.getHttpNettyClientWriteTimeOut())
                .readTimeout(properties.getHttpNettyClientReadTimeOut())
                .maxConcurrency(properties.getHttpNettyClientMaxConcurrency())
                .build();
    }
}
