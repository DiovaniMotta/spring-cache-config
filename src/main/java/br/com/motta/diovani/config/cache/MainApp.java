package br.com.motta.diovani.config.cache;

import br.com.motta.diovani.config.cache.configs.RedisCacheManagerFactory;

public class MainApp {

    public static void main(String[] args) {
        CacheConfiguration configuration = new CacheConfiguration() {
            @Override
            public String getHost() {
                return "localhost";
            }

            @Override
            public int getPort() {
                return 6379;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getPrefix() {
                return "hcm:test_cache";
            }
        };
        var manager = RedisCacheManagerFactory.create(configuration);
    }
}
