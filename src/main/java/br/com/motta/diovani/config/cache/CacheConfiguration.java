package br.com.motta.diovani.config.cache;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public interface CacheConfiguration {

    String getHost();

    int getPort();

    String getPassword();

    String getPrefix();
}
