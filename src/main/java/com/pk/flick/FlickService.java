package com.pk.flick;

public interface FlickService {

    Flick add(Flick flick);

    Flick get(Integer flickId);

    Flick getByHash(String globalHash);

}
