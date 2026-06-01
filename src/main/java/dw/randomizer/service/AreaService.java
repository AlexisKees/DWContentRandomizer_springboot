package dw.randomizer.service;

import dw.randomizer.data.DungeonArrays;
import dw.randomizer.model.Area;
import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.AreaDiscovery;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.AreaRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.*;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class AreaService implements IGenericService<Area>, IGenericCRUDService<Area> {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private ViewAll viewAll;

    @Autowired
    private AreaDangerService areaDangerService;
    @Autowired
    private AreaDiscoveryService areaDiscoveryService;
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public List<Area> list() {
        return areaRepository.findAll();
    }

    @Override
    public Area searchById(Integer id) {

        return areaRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void delete(Area area) {
        areaRepository.delete(area);
    }



    public void rollArea(Area area) {
        area.setAreaType(PickFrom(DungeonArrays.AREA_TYPE));
        area.setOneLiner(area.getAreaType());
        rollAreaDetails(area);

    }

    public void rollAreaDetails(Area area){
        area.setRarity(PickFrom(DungeonArrays.AREA_RARITY));

        int rollRarity = UniversalRoll(DungeonArrays.AREA_RARITY);
        switch (rollRarity){
            case 0 ->{
                area.setDangersAmount(Roll1d4()+1);
                area.setDiscoveriesAmount(0);
            }
            case 1,2 ->{
                area.setDangersAmount(1);
                area.setDiscoveriesAmount(0);
            }
            case 3,4,5->{
                area.setDangersAmount(0);
                area.setDiscoveriesAmount(0);
            }
            case 6->{
                area.setDangersAmount(1);
                area.setDiscoveriesAmount(1);
            }
            case 7->{
                area.setDangersAmount(Roll1d4()+1);
                area.setDiscoveriesAmount(1);
            }
            case 8->{
                area.setDangersAmount(0);
                area.setDiscoveriesAmount(1);
            }
            case 9->{
                area.setDangersAmount(Roll1d4()+1);
                area.setDiscoveriesAmount(Roll1d4()+1);
            }
            case 10->{
                area.setDangersAmount(1);
                area.setDiscoveriesAmount(Roll1d4()+1);
            }
            case 11->{
                area.setDangersAmount(0);
                area.setDiscoveriesAmount(Roll1d4()+1);
            }

        }

        area.setRarity(DungeonArrays.AREA_RARITY[rollRarity]);

        area.setAreaDressing(PickFrom(DungeonArrays.AREA_DRESSING));
        if (Objects.equals(area.getAreaDressing(),"roll twice") || Objects.equals(area.getAreaDressing(),"ROLL TWICE")) area.setAreaDressing(rollTwice(DungeonArrays.AREA_DRESSING));

        addDangers(area);
        addDiscoveries(area);
    }

    public void addDiscoveries(Area area){
        List<AreaDiscovery> list = new ArrayList<>();

        if (area.getDiscoveriesAmount()>0) {
            int i;
            for (i = 1; i <= area.getDiscoveriesAmount(); i++) {
                AreaDiscovery d = new AreaDiscovery();
                areaDiscoveryService.rollAreaDiscovery(d);
                list.add(d.clone());
            }
            area.setDiscoveries(list);
        }

    }


    public void addDangers(Area area){
        List<AreaDanger> list = new ArrayList<>();

        if (area.getDangersAmount()>0) {
            int i;
            for (i = 1; i <= area.getDangersAmount(); i++) {
                AreaDanger d = new AreaDanger();
                areaDangerService.rollAreaDanger(d);
                list.add(d.clone());
            }
        }
        area.setDangers(list);
    }

    @Override
    public String showOptions(Scanner dataInput, Class<Area> parameterClass) {
        Area area;
        if(sessionManager.getSelected(parameterClass)==null) {
            area = new Area();
        } else {
            area = sessionManager.getSelected(parameterClass);
        }

        int option;
        System.out.println("WELCOME TO THE AREA GENERATOR\n");
        String menu = "MAIN_MENU";
        try{
            do {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random area
                        2) View current area
                        3) Reroll this area
                        4) View list of generated area
                        5) Export current area
                        6) MANAGE DB
                        0) Main menu
                        
                        \tOption:\s""");

                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 -> {
                        rollArea(area);
                        sessionManager.add(Area.class,area.clone());
                        printWithFlair(sessionManager.getSelected(Area.class));
                    }
                    case 2 ->{
                        if (area.getAreaType()==null){
                            sessionManager.add(Area.class,area.clone());
                            rollArea(area);
                        }
                        printWithFlair(area);
                    }
                    case 3 ->{
                        if (area.getAreaType()==null){
                            rollArea(area);
                            sessionManager.add(Area.class,area.clone());
                        } else {
                            rollAreaDetails(area);
                            sessionManager.add(Area.class,area.clone());
                        }
                        printWithFlair(area);
                    }
                    case 4 -> area = viewAll.run(dataInput, Area.class);
                    case 5 -> {
                        if (area.getAreaType()==null){
                            rollArea(area);
                            sessionManager.add(Area.class,area);
                        }
                        GenericFunctions.exportPW(area);
                    }
                    case 6 -> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");
                }
            } while (option!=0);


        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }

        return menu;
    }
}
