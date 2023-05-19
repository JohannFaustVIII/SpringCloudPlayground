package joh.faust;


import joh.faust.cb.FallbackEightApiClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "8-eighth-service", fallback = FallbackEightApiClient.class)
public interface EighthApiClient {

    @GetMapping(value = "/integers")
    List<Integer> getIntegers();
}
