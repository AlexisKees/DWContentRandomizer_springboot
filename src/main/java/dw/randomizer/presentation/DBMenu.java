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

    public void run(Scanner dataInput, Class<T> parameterClass){


        IGenericCRUDService serviceFile = classIdentifier.getCRUDFile(parameterClass);
        int option = 0;
        String label = parameterClass.getSimpleName().toUpperCase();
        String labelPlural;
        if
            (label.equals("DISCOVERY")) labelPlural = "DISCOVERIES ";
        else
            labelPlural = label + "S";

        printWithFlair("*** WELCOME TO THE DATABASE MANAGER ***");
        try {
            do {
                System.out.print(String.format("""
                        Please select an option:
                        
                        1) VIEW ALL %s IN DB
                        2) SELECT %s BY ID
                        3) VIEW SELECTED %s
                        4) ADD SELECTED %s TO DB (only if it doesn't exist already)
                        5) UPDATE EXISTING %s
                        6) DELETE AN EXISTING %s
                        0) GO BACK
                        \tOPTION:\s""",labelPlural,label,label,label,label,label));
                option = Integer.parseInt(dataInput.nextLine());

                switch (option){
                    case 1 -> {
                        System.out.println("\n\n");
                        List<T> list = serviceFile.list();
                        for(T o : list){
                            System.out.println(String.format("Element id: %d\n%s\n\n",o.getId(), o));
                        }
                    }
                    case 2 ->{
                        T object = factory.create(parameterClass);
                        Integer id;
                        System.out.print("\n\tPlease insert ID:\s");
                        id = Integer.parseInt(dataInput.nextLine());
                        object = (T) serviceFile.searchById(id);
                        sessionManager.add(parameterClass, object);
                        printWithFlair(object);
                    }
                    case 3 -> printWithFlair(sessionManager.getSelected(parameterClass));
                    case 4 -> {
                        T element = sessionManager.getSelected(parameterClass);
                        System.out.println(element);
                        serviceFile.save(element);
                    }
                    case 5 ->{
                        System.out.print("\n\tPlease insert: ID\s");
                        Integer id = Integer.parseInt(dataInput.nextLine());
                        T object = (T) serviceFile.searchById(id);
                        System.out.println("You will replace this element:\n\n"+object+"\n\n"+
                                            "With this one:\n\n"+sessionManager.getSelected(parameterClass)+
                                            "\n\n\tAre you sure (y/n)?\s");
                        String sure = dataInput.nextLine();
                        switch (sure){
                            case "y", "Y", "yes", "Yes", "YES" -> {
                                sessionManager.getSelected(parameterClass).setId(id);
                                serviceFile.save(sessionManager.getSelected(parameterClass));
                                System.out.println("ELEMENT HAS BEEN REPLACED");
                            }
                            case "n", "N", "no", "No", "NO" ->{
                                System.out.println("ELEMENT WAS NOT REPLACED.");
                            }
                            default -> System.out.println("Insert a valid option: Are you sure (y/n)?\s");
                        }
                    }
                    case 6 -> {
                        System.out.print("\n\tPlease insert: ID\s");
                        Integer id = Integer.parseInt(dataInput.nextLine());
                        T object = (T) serviceFile.searchById(id);
                        System.out.println("You will delete this element from the Database:\n\n"+object
                                +"\n\n\tAre you sure (y/n)?\s");
                        String sure = dataInput.nextLine();
                        switch (sure){
                            case "y", "Y", "yes", "Yes", "YES" -> {
                                serviceFile.delete(object);
                                sessionManager.removeSelected(parameterClass);
                                System.out.println("ELEMENT HAS BEEN DELETED");
                            }
                            case "n", "N", "no", "No", "NO" ->{
                                System.out.println("ELEMENT WAS NOT DELETED.");
                            }
                            default -> System.out.println("Insert a valid option: Are you sure (y/n)?\s");
                        }
                        serviceFile.delete(sessionManager.getSelected(parameterClass));
                    }
                    case 0 -> System.out.println("GOING BACK...");
                }
            } while (option != 0);
        } catch (Exception e){
            printWithFlair("ERROR MANAGING DB: "+e.getMessage());
        }
    }
}
