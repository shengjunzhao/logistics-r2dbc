package com.haole.logistics.r2dbc.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BeanMapper {

    private MapperFacade mapperFacade;

    public <S, T> T map(S source, Class<T> targetClass) {
        return source == null ? null : this.mapperFacade.map(source, targetClass);
    }

    public <S, T> void map(S source, T targetObject) {
        if (source != null) {
            this.mapperFacade.map(source, targetObject);
        }
    }

    public <S, T> List<T> mapAsList(Iterable<S> source, Class<T> targetClass) {
        return this.mapperFacade.mapAsList(source, targetClass);
    }

    public <S, T> Set<T> mapAsSet(Iterable<S> source, Class<T> targetClass) {
        return this.mapperFacade.mapAsSet(source, targetClass);
    }




    @Autowired
    public void setMapperFacade(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }
}
