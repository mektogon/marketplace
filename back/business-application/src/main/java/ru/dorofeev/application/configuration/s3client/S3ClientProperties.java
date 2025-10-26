package ru.dorofeev.application.configuration.s3client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "file-manager.s3")
public class S3ClientProperties {

    /**
     * Идентификатор ключа доступа.
     */
    private String s3CredentialsKeyAccessId;

    /**
     * Секретный ключ доступа.
     */
    private String s3CredentialsKeyAccessSecret;

    /**
     * Регион хранилища.
     */
    private String region;

    /**
     * Максимальное количество одновременных HTTP соединений.
     * <br/> DEFAULT: 100 <br/>
     */
    private int httpNettyClientMaxConcurrency = 100;

    /**
     * Таум-аут {@link software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient} на соединение.
     * <br/> DEFAULT: 10s <br/>
     */
    private Duration httpNettyClientConnectionTimeOut = Duration.ofSeconds(10);

    /**
     * Таум-аут {@link software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient} на чтение.
     * <br/> DEFAULT: 60s <br/>
     */
    private Duration httpNettyClientReadTimeOut = Duration.ofSeconds(60);

    /**
     * Тайм-аут {@link software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient} на запись.
     * <br/> DEFAULT: 60s <br/>
     */
    private Duration httpNettyClientWriteTimeOut = Duration.ofSeconds(60);

    /**
     * Тайм-аут {@link software.amazon.awssdk.services.s3.S3AsyncClient} на всю операцию.
     * <br/> DEFAULT: 10m <br/>
     */
    private Duration s3ClientApiCallTimeOut = Duration.ofMinutes(10);

    /**
     * Тайм-аут {@link software.amazon.awssdk.services.s3.S3AsyncClient} на одну попытку.
     * <br/> DEFAULT: 1m <br/>
     */
    private Duration s3ClientApiCallAttemptTimeout = Duration.ofMinutes(1);

    /**
     * Количество попыток операции для {@link software.amazon.awssdk.services.s3.S3AsyncClient}.
     * <br/> DEFAULT: 3 <br/>
     */
    private int s3ClientRetryPolicyMaxAttempts = 3;

    /**
     * Фиксированная задержка перед попыткой вызова операции для {@link software.amazon.awssdk.services.s3.S3AsyncClient}.
     * <br/> DEFAULT: 3s <br/>
     */
    private Duration s3ClientRetryPolicyBackoffFixedDelay = Duration.ofSeconds(3);
}
