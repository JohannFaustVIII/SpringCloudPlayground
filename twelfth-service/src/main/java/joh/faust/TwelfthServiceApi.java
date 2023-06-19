package joh.faust;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@CacheConfig(cacheNames = {"twelfthService"})
@RestController
public class TwelfthServiceApi {

    @Cacheable
    @GetMapping("/")
    public String getHello() {
        return "Twelfth Service: Hello World! Call time: " + LocalDateTime.now() + " CACHEABLE";
    }

    @CachePut
    @GetMapping("/put")
    public String getPutMessage() {
        return "Twelfth Service: Hello World! Call time: " + LocalDateTime.now() + " PUT";
    }

    @CacheEvict
    @GetMapping("/evict")
    public String getRemovalMessage() {
        return "Twelfth Service: Hello World! Call time: " + LocalDateTime.now() + " EVICT";
    }
}
