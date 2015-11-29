package com.pk.flick;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
class InMemoryFlickStorageService implements FlickStorageService {

    private Map<Integer, Flick> flickMap;
    private Map<String, Flick> flickHashMap;

    public InMemoryFlickStorageService() {
        this.flickMap = new HashMap<Integer, Flick>();
        this.flickHashMap = new HashMap<String, Flick>();
    }

    @Override
    public Flick get(Integer flickId) {
        return flickMap.get(flickId);
    }

    @Override
    public Flick getByHash(String globalHash) {
        return flickHashMap.get(globalHash);
    }

    @Override
    public Flick add(Flick flick) {
        flickMap.put(flick.getId(), flick);
        flickHashMap.put(flick.getHash(), flick);
        return flick;
    }

}
