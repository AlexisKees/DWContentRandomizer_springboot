package dw.randomizer.presentation;

import dw.randomizer.model.*;
import dw.randomizer.service.DungeonAreaService;
import dw.randomizer.service.DungeonService;
import dw.randomizer.service.QuestService;
import dw.randomizer.service.util.ClassIdentifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class SubMenu {

    private final ClassIdentifier classIdentifier;
    private final DungeonAreaService dungeonAreaService;
    private final DungeonService dungeonService;
    private final QuestService questService;

    public SubMenu(ClassIdentifier classIdentifier,
                   DungeonAreaService dungeonAreaService,
                   DungeonService dungeonService,
                   QuestService questService) {
        this.classIdentifier = classIdentifier;
        this.dungeonAreaService = dungeonAreaService;
        this.dungeonService = dungeonService;
        this.questService = questService;
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

    public String run(Scanner dataInput, Area area, Dungeon dungeon, List<Area> areaList){
        return dungeonAreaService.showOptions(dataInput, area, dungeon, areaList);
    }

    public String run(Scanner dataInput, Quest object, List<Quest> questList,
                    List<NPC> npcList, List<Dungeon> dungeonList, List<Biome> biomeList){
        return questService.showOptions(dataInput, object, questList, npcList, dungeonList, biomeList);
    }

    public String run(Scanner dataInput, Dungeon dungeon, List<Dungeon> dungeonList, List<Area> areaList){
        return dungeonService.showOptions(dataInput, dungeon, dungeonList, areaList);
    }
}


