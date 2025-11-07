package dw.randomizer.service;

import dw.randomizer.model.Area;
import dw.randomizer.model.Dungeon;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class DungeonAreaService {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ViewAll viewAll;
    @Autowired
    private AreaService areaService;

    public String showOptions(Scanner dataInput) {
        Area area;
        if(sessionManager.getSelected(Area.class)==null) {
            area = new Area();
        } else {
            area = sessionManager.getSelected(Area.class);
        }

        Dungeon dungeon = sessionManager.getSelected(Dungeon.class);
        int option;
        System.out.println("WELCOME TO THE DUNGEON AREA GENERATOR\n");
        String menu = "MAIN_MENU";
        try{

            do {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random area
                        2) View current area
                        3) Reroll this area
                        4) View list of generated areas
                        5) Add area to dungeon
                        0) Back to Dungeon menu
                        
                        \tOption:\s""");

                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 -> {
                        areaService.rollArea(area);
                        sessionManager.add(Area.class,area.clone());
                        printWithFlair(area);
                    }
                    case 2 ->{
                        if (area.getAreaType()==null){
                            areaService.rollArea(area);
                        }
                        printWithFlair(area);
                    }
                    case 3 ->{
                        if (area.getAreaType()==null){
                            areaService.rollArea(area);
                        } else {
                            areaService.rollAreaDetails(area);
                        }
                        sessionManager.add(Area.class,area.clone());
                        printWithFlair(area);
                    }
                    case 4 -> area = viewAll.run(dataInput, Area.class);
                    case 5 -> {
                        if (area.getAreaType()==null){
                            areaService.rollArea(area);
                            sessionManager.add(Area.class,area.clone());
                        }
                        dungeon.addArea(area.clone());
                    }
                    case 0 -> System.out.println("Going back to dungeon generator");
                }
            } while (option!=0);


        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }
        return menu;
    }


}