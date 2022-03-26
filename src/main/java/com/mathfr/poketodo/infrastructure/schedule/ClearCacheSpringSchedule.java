package com.mathfr.poketodo.infrastructure.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ClearCacheSpringSchedule {

    private final Logger logger = LoggerFactory.getLogger(ClearCacheSpringSchedule.class);
    @Autowired
    private CacheManager cacheManager;

    @Scheduled(cron = "0 0/5 * * * *")
    public void clearCacheSpring() {
        logger.info("Iniciando cronjob para limpar o Cache...");
        try {
            cacheManager.getCacheNames()
                    .forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
            logger.info("Cronjob executado com sucesso");
        } catch (RuntimeException exception) {
            logger.error("Ocorreu um erro no cronjob");
        }
    }

}
