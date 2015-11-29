package com.pk.user.version;

import java.util.ArrayList;
import java.util.List;

import com.pk.user.flick.FlickDto;

public class Version {

    private Integer version;
    private List<FlickDto> added = new ArrayList<FlickDto>();
    private List<FlickDto> removed = new ArrayList<FlickDto>();

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<FlickDto> getAdded() {
        return added;
    }

    public void setAdded(List<FlickDto> added) {
        this.added = added;
    }

    public List<FlickDto> getRemoved() {
        return removed;
    }

    public void setRemoved(List<FlickDto> removed) {
        this.removed = removed;
    }

}
