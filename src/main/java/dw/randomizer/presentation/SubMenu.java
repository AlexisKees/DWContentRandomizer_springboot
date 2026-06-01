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

    public <T extends IPWClass> String run(Scanner dataInput, Class<T> parameterClass){
        var serviceInterface = classIdentifier.getServiceFile(parameterClass);
        String menu="MAIN_MENU";
        if (serviceInterface != null) {
            menu = serviceInterface.showOptions(dataInput, parameterClass);
        } else {
            System.out.println("No service registered for class '" + parameterClass + "'.");
        }
        return menu;
    }

}


