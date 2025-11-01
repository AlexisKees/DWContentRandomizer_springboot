package dw.randomizer.service;

import dw.randomizer.model.Follower;

import java.util.List;

public interface IFollowerCRUDService {
    List<Follower> listFollowers();
    //metodo para read
    Follower searchById(Integer id);
    //el siguiente caso save se usa tanto como para INSERT como para UPDATE
    //si id es null, se hace insert, caso contrario se hace update
    void saveFollower(Follower follower);
    //metodo para delete
    void deleteFollower(Follower follower);
}
