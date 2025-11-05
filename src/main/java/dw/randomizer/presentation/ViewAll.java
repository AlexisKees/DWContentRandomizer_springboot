package dw.randomizer.presentation;

import dw.randomizer.model.IPWClass;
import dw.randomizer.service.GenericFunctions;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ViewAll {

    @Autowired
    SessionManager sessionManager;

    public <T extends IPWClass> T run(Scanner dataInput, T object){
        int option;
        int itemNumber;
        String label = object.getClass().getSimpleName();
        List<T> list = (List<T>) sessionManager.getList(object.getClass());

        String labelPlural = switch (label){
            case "Discovery" -> "Discoveries";
            default -> label+"s";
        };

        int counter = 1;
        System.out.printf("""
                        *** LIST OF %s ***
                        """,labelPlural.toUpperCase());
        for (IPWClass o : list){
            System.out.printf("%d) %s\n", counter,o.getOneLiner());
            counter++;
        }
        System.out.println("\n");

        try{
            do {
                System.out.print("""
                        Please select an option:
                        1) View one element in detail
                        2) Go back
                        \tOption:\s""");

                option=Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 ->{
                        System.out.printf("\nPlease insert %s number:\s",label.toLowerCase());
                        itemNumber=Integer.parseInt(dataInput.nextLine());
                        System.out.println();
                        sessionManager.select(object.getClass(),itemNumber-1);
                        T selectedItem=(T) sessionManager.getSelected(object.getClass());
                        if (itemNumber < 0 || itemNumber>list.size()){
                            System.out.println("Please insert a valid number");
                            continue;
                        } else {
                            System.out.println("______________________________________");
                            System.out.printf("TAKE A LOOK AT YOUR CHOSEN %s:%n%n", label.toUpperCase());
                            System.out.println(selectedItem+"\n");
                            System.out.println("______________________________________\n");
                        }

                        int secondOption;
                        do {
                            System.out.printf("""
                                Please choose an option:
                                1) Export %s
                                2) Select this %s to keep editing
                                3) Go back
                                \tOption:\s""",label.toLowerCase(),label.toLowerCase());
                            secondOption=Integer.parseInt(dataInput.nextLine());
                            System.out.println();

                            switch (secondOption){
                                case 1 -> GenericFunctions.exportPW(object);
                                case 2 -> {
                                    object = selectedItem;
                                    System.out.printf("\n%s SUCCESSFULLY LOADED\n",label.toUpperCase());
                                    System.out.println();
                                    return object;
                                }
                                case 3 -> {
                                    System.out.println("\nGoing back...\n");
                                    return object;
                                }
                            }
                        } while (secondOption != 3);


                    }
                }
            } while (option!=2);

        } catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }

        return object;
    }
}
