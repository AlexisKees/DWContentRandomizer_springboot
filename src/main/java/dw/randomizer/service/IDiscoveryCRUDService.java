package dw.randomizer.service;

import dw.randomizer.model.Discovery;

import java.util.List;

public interface IDiscoveryCRUDService {

    List<Discovery> listDiscoveries();
    //metodo para read
    Discovery searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveDiscovery(Discovery discovery);
    //metodo para delete
    void deleteDiscovery(Discovery discovery);
}
