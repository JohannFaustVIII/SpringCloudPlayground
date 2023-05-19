package joh.faust.cb;

import joh.faust.EighthApiClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class FallbackEightApiClient implements EighthApiClient {
    @Override
    public List<Integer> getIntegers() {
        return Collections.EMPTY_LIST;
    }
}
