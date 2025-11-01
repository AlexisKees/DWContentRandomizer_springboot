package dw.randomizer.service;

import dw.randomizer.model.Area;
import dw.randomizer.model.Dungeon;
import dw.randomizer.presentation.ViewAll;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class DungeonAreaService {

    public static void showOptions(Scanner dataInput, Area area, Dungeon dungeon,List<Area> areaList) {
        int option;
        System.out.println("WELCOME TO THE AREA GENERATOR\n");

        try{

            do {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random area
                        2) View current area
                        3) Reroll this area
                        4) View list of generated areas
                        5) Add area to dungeon
                        6) Back to Dungeon menu
                        
                        \tOption:\s""");

                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 -> {
                        area = new Area();
                        AreaService.rollArea(area);
                        areaList.add(area.clone());
                        printWithFlair(area);
                    }
                    case 2 ->{
                        if (area==null){
                            area = new Area();
                            AreaService.rollArea(area);
                        }
                        printWithFlair(area);
                    }
                    case 3 ->{
                        if (area==null){
                            area = new Area();
                            AreaService.rollArea(area);
                        } else {
                            AreaService.rollAreaDetails(area);
                        }
                        areaList.add(area.clone());
                        printWithFlair(area);
                    }
                    case 4 -> area = new ViewAll().run(dataInput, areaList, area, Area.class);
                    case 5 -> {
                        if (area==null){
                            area = new Area();
                            AreaService.rollArea(area);
                        }
                        dungeon.addArea(area.clone());
                    }
                    case 6 -> System.out.println("Going back to DUNGEON GENERATOR");
                }
            } while (option!=6);


        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }
    }


}