package dw.randomizer.service;

import dw.randomizer.data.DungeonArrays;
import dw.randomizer.model.Area;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.*;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

public class AreaService implements IGenericService<Area>, IAreaCRUDService {

    @Autowired
    AreaRepository areaRepository;

    @Override
    public List<Area> listAreas() {
        List<Area> areaList = areaRepository.findAll();
        return areaList;
    }

    @Override
    public Area searchById(Integer id) {
        Area area = areaRepository.findById(id).orElse(null);
        return area;
    }

    @Override
    public void saveArea(Area area) {
        areaRepository.save(area);
    }

    @Override
    public void deleteArea(Area area) {
        areaRepository.delete(area);
    }

    public static void rollArea(Area area) {
        area.setAreaType(PickFrom(DungeonArrays.AREA_TYPE));
        area.setOneLiner(area.getAreaType());
        rollAreaDetails(area);

    }

    public static void rollAreaDetails(Area area){
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

        area.addDangers();
        area.addDiscoveries();
    }

    @Override
    public void showOptions(Scanner dataInput, Area area, List<Area> areaList) {
        int option;
        System.out.println("WELCOME TO THE AREA GENERATOR\n");

        try{

            do {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random area
                        2) View current area
                        3) Reroll this area
                        4) View list of generated area
                        5) Export current area
                        6) Main menu
                        
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
                            areaList.add(area.clone());
                            AreaService.rollArea(area);
                        }
                        printWithFlair(area);
                    }
                    case 3 ->{
                        if (area==null){
                            area = new Area();
                            AreaService.rollArea(area);
                            areaList.add(area.clone());
                        } else {
                            AreaService.rollAreaDetails(area);
                            areaList.add(area.clone());
                        }
                        printWithFlair(area);
                    }
                    case 4 -> area = new ViewAll().run(dataInput, areaList, area, Area.class);
                    case 5 -> {
                        if (area==null){
                            area = new Area();
                            AreaService.rollArea(area);
                        }
                        GenericFunctions.exportPW(area);
                    }
                    case 6 -> System.out.println("Going back to main menu");
                }
            } while (option!=6);


        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }
    }
}
