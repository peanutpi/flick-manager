package com.pk.flick;

public interface FlickStorageService {

    Flick get(Integer flickId);

    Flick add(Flick flick);

    Flick getByHash(String globalHash);

}
