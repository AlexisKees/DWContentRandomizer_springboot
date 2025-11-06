package dw.randomizer.presentation;

import dw.randomizer.model.IPWClass;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.ClassIdentifier;
import dw.randomizer.service.util.Factory;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Scanner;

import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Component
public class DBMenu<T extends IPWClass> {
    @Autowired private ClassIdentifier classIdentifier;
    @Autowired private SessionManager sessionManager;
    @Autowired private Factory factory;

    public void run(Scanner dataInput, T object){


        IGenericCRUDService serviceFile = classIdentifier.getCRUDFile((Class<T>) object.getClass());
        int option = 0;

        printWithFlair("*** WELCOME TO THE DATABASE MANAGER ***");
        try {
            do {
                System.out.print("""
                        Please select an option:
                        
                        1) VIEW EXISTING ELEMENTS IN DB
                        2) INSERT NEW ELEMENT
                        3) UPDATE EXISTING ELEMENT
                        4) DELETE AN EXISTING ELEMENT
                        0) GO BACK
                        \tOPTION:\s""");
                option = Integer.parseInt(dataInput.nextLine());

                switch (option){
                    case 1 -> {
                        System.out.println("\n\n");
                        List<T> list = serviceFile.listCRUD();
                        for(T o : list){
                            System.out.println(String.format("Element id: %d\n%s\n\n",o.getId(), o));
                        }
                    }
                    case 2 -> {
                        T element = (T) sessionManager.getSelected(object.getClass());
                        System.out.println(element);
                        serviceFile.saveCRUD(element);
                    }
                    case 3 ->{}
                    case 4 -> {
                        System.out.println("Element id:\s");
                        Integer id = Integer.parseInt(dataInput.nextLine());

                        serviceFile.deleteCRUD(sessionManager.getSelected(object.getClass()));
                    }
                    case 0 -> System.out.println("GOING BACK...");
                }
            } while (option != 0);
        } catch (Exception e){
            printWithFlair("ERROR MANAGING DB: "+e.getMessage());
        }
    }
}
