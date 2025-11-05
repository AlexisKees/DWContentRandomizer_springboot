package dw.randomizer.presentation;

import dw.randomizer.model.*;
import dw.randomizer.service.util.ClassIdentifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SubMenu {

    private final ClassIdentifier classIdentifier;

    public SubMenu(ClassIdentifier classIdentifier) {
        this.classIdentifier = classIdentifier;
    }

    public <T extends IPWClass<T>> String run(Scanner dataInput, T object){
        var serviceInterface = classIdentifier.getServiceFile((Class<T>) object.getClass());
        String menu="MAIN_MENU";
        if (serviceInterface != null) {
            menu = serviceInterface.showOptions(dataInput, object);
        } else {
            System.out.println("No service registered for class '" + object.getClass() + "'.");
        }
        return menu;
    }

}


