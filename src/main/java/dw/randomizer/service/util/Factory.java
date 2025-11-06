package dw.randomizer.service.util;

import dw.randomizer.model.IPWClass;
import org.springframework.stereotype.Component;

@Component
public class Factory  {
    public <T extends IPWClass> T create(Class<T> c){
        try{
            return c.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            System.out.println("no se pudo instanciar: "+e.getMessage());
        }
        return null;
    };
}