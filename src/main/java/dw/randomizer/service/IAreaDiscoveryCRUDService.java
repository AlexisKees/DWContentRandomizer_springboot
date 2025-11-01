package dw.randomizer.service;

import dw.randomizer.model.AreaDiscovery;

import java.util.List;

public interface IAreaDiscoveryCRUDService {
    List<AreaDiscovery> listAreaDiscoveries();
    //metodo para read
    AreaDiscovery searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveAreaDiscovery(AreaDiscovery areaDiscovery);
    //metodo para delete
    void deleteAreaDiscovery(AreaDiscovery areaDiscovery);
}
