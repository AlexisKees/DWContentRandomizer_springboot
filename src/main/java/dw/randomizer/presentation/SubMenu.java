package dw.randomizer.presentation;

import dw.randomizer.model.*;
import dw.randomizer.service.DungeonAreaService;
import dw.randomizer.service.DungeonService;
import dw.randomizer.service.QuestService;
import dw.randomizer.service.util.ClassIdentifier;

import java.util.List;
import java.util.Scanner;

public class SubMenu {

    public static <T extends IPWClass<T>> void run(Scanner dataInput, T object, List<T> list){
        var serviceInterface = ClassIdentifier.getServiceFile((Class <T>) object.getClass());
        if (serviceInterface != null){
            serviceInterface.showOptions(dataInput,object,list);
        } else {
            System.out.println("No service registered for class '"+object.getClass()+"'.");
        }
    }
    //run para dungeon area
    public static  void run(Scanner dataInput, Area area, Dungeon dungeon,List<Area> areaList){
        DungeonAreaService.showOptions(dataInput,area,dungeon, areaList);
    }
    //run para quest
    public static void run(Scanner dataInput, Quest object, List<Quest>questList, List<NPC> npcList, List<Dungeon> dungeonList, List<Biome> biomeList){
        QuestService.showOptions(dataInput, object, questList,npcList, dungeonList, biomeList);
    }

    public static void run(Scanner dataInput, Dungeon dungeon, List<Dungeon> dungeonList,List<Area> areaList){
        DungeonService.showOptions(dataInput, dungeon, dungeonList, areaList);
    }

}


