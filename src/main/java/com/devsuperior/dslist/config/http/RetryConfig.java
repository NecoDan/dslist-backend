package com.devsuperior.dslist.config.http;

import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;

@Configuration
@Slf4j
public class RetryConfig {

    @Bean
    public RetryOperationsInterceptor retryOperationsInterceptorExternal(
            @Value("${retry.maxAttempts:3}") int maxAttempts,
            @Value("${retry.delay:3}") int delay,
            @Value("${retry.multiplier:3}") int multiplier,
            @Value("${retry.maxDelay:3}") int maxDelay
    ) {
        return buildRetryOperationsInterceptor(
                maxAttempts,
                delay,
                multiplier,
                maxDelay,
                Map.of(
                        FailedServiceExternalException.class, true,
                        RetryableException.class, true,
                        FeignException.class, true,
                        FeignException.FeignServerException.class, true,
                        SocketTimeoutException.class, true,
                        ConnectException.class, true
                )
        );
    }

    @Bean
    public RetryOperationsInterceptor retryOperationsInterceptorConnectionExternal(
            @Value("${retry.maxAttempts:3}") int maxAttempts,
            @Value("${retry.delay:3}") int delay,
            @Value("${retry.multiplier:3}") int multiplier,
            @Value("${retry.maxDelay:3}") int maxDelay
    ) {
        return buildRetryOperationsInterceptor(
                maxAttempts,
                delay,
                multiplier,
                maxDelay,
                Map.of(
                        SocketTimeoutException.class, true,
                        ConnectException.class, true
                )
        );
    }


    @Bean
    public RetryListener retryListener() {
        return new RetryListener() {
            @Override
            public <T, E extends Throwable> void onError(
                    RetryContext context,
                    RetryCallback<T, E> callback,
                    Throwable throwable) {

                final var strMessageVar1 = throwable.getMessage() != null ? throwable.getMessage() : "Erro desconhecido";

                log.warn("Retry - Tentativa: {} falhou para {}. Erro: {}",
                        context.getRetryCount(),
                        context.getAttribute("context.name"),
                        strMessageVar1
                );
            }

            @Override
            public <T, E extends Throwable> void close(
                    RetryContext context,
                    RetryCallback<T, E> callback,
                    Throwable throwable) {

                var strMessageVar2 = "Erro ao efetuar tentativas de conexão para "
                        + context.getAttribute("context.name")
                        + ". Último erro: "
                        + (throwable != null ? throwable.getMessage() : "Erro desconhecido");

                log.error("Retry - Todas as tentativas {} esgotadas para {}. Último erro: {}",
                        context.getRetryCount(),
                        context.getAttribute("context.name"),
                        strMessageVar2
                );
            }
        };
    }

    private RetryOperationsInterceptor buildRetryOperationsInterceptor(
            int maxAttempts,
            int delay,
            double multiplier,
            long maxDelay,
            Map<Class<? extends Throwable>, Boolean> retryableExceptions
    ) {
        final var retryPolicy = new SimpleRetryPolicy(maxAttempts, retryableExceptions, Boolean.TRUE);

        var backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(delay);
        backOffPolicy.setMultiplier(multiplier);
        backOffPolicy.setMaxInterval(maxDelay);

        var retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.registerListener(retryListener());

        return RetryInterceptorBuilder.stateless()
                .retryOperations(retryTemplate)
                .build();
    }
}
