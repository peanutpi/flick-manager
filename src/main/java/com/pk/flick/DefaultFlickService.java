package com.pk.flick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DefaultFlickService implements FlickService {

    private final FlickStorageService storageService;

    @Autowired
    public DefaultFlickService(FlickStorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public Flick add(Flick flick) {
        return storageService.add(flick);
    }

    @Override
    public Flick getByHash(String globalHash) {
        return storageService.getByHash(globalHash);
    }

    @Override
    public Flick get(Integer flickId) {
        return storageService.get(flickId);
    }

}
