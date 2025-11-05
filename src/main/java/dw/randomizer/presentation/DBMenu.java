package dw.randomizer.presentation;

import dw.randomizer.model.IPWClass;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.ClassIdentifier;
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

    public void run(Scanner dataInput, T object){


        IGenericCRUDService serviceFile = classIdentifier.getCRUDFile((Class<T>) object.getClass());

        int option = 0;

        printWithFlair("*** WELCOME TO THE DATABASE MANAGER ***");
        try {
            do {
                System.out.print("""
                        Please select an option:
                        
                        1) VIEW EXISTING ELEMENTS IN DB
                        2) UPDATE OR INSERT NEW ELEMENT
                        3) DELETE AN EXISTING ELEMENT
                        4) GO BACK
                        \tOPTION:\s""");
                option = Integer.parseInt(dataInput.nextLine());

                switch (option){
                    case 1 -> {
                        List<T> list = serviceFile.listCRUD();
                        int i=1;
                        for(T o : list){
                            System.out.println(String.format("%d) %s\n\n",i, o));
                            i++;
                        }
                    }
                    case 2 -> serviceFile.saveCRUD(sessionManager.getSelected(object.getClass()));
                    case 3 -> serviceFile.deleteCRUD(sessionManager.getSelected(object.getClass()));
                    case 4 -> System.out.println("GOING BACK...");
                }
            } while (option != 4);
        } catch (Exception e){
            printWithFlair("ERROR MANAGING DB: "+e.getMessage());
        }
    }
}
