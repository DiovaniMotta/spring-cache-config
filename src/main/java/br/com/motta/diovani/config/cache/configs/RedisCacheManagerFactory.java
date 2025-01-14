package br.com.motta.diovani.config.cache.configs;

import br.com.motta.diovani.config.cache.CacheConfiguration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cache.CacheManager;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisCacheManagerFactory {

    public static CacheManager create(CacheConfiguration configuration){
        Objects.requireNonNull(configuration, "Configuration is required");
        Objects.requireNonNull(configuration.getPrefix(), "Prefix is required");
        var prefix = configuration.getPrefix();
        var connection = new ConfigurationRedisFactory(configuration).execute();
        var serializer = new ConfigurationRedisSerializer().execute();
        return new ConfigurationCacheManager(prefix, serializer, connection).execute();
    }
}
