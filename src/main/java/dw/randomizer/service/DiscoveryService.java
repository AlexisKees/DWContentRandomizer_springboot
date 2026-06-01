package dw.randomizer.service;

import dw.randomizer.data.DetailsArrays;
import dw.randomizer.data.DiscoveryArrays;
import dw.randomizer.model.*;
import dw.randomizer.model.util.Rolls;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.DiscoveryRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class DiscoveryService implements IGenericService<Discovery>, IGenericCRUDService<Discovery> {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private ViewAll viewAll;
    @Autowired
    private DungeonService dungeonService;
    @Autowired
    private SteadingService steadingService;
    @Autowired
    private CreatureService creatureService;

    @Autowired
    private DiscoveryRepository discoveryRepository;

    @Override
    public List<Discovery> list() {
        return discoveryRepository.findAll();
    }

    @Override
    public Discovery searchById(Integer id) {
        return discoveryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Discovery discovery) {
        discoveryRepository.save(discovery);
    }

    @Override
    public void delete(Discovery discovery) {
        discoveryRepository.delete(discovery);
    }



    public void rollDiscovery(Discovery discovery){
        discovery.setCategory(PickFrom(DiscoveryArrays.DISCOVERY_CATEGORIES));
        switch (discovery.getCategory()){
             case "UNNATURAL FEATURE" -> discovery.setSubcategoriesTable(DiscoveryArrays.UNNATURAL_FEATURE_SUBCATEGORIES);
             case "NATURAL FEATURE" -> discovery.setSubcategoriesTable(DiscoveryArrays.NATURAL_FEATURE_SUBCATEGORIES);
             case "EVIDENCE" -> discovery.setSubcategoriesTable(DiscoveryArrays.EVIDENCE_SUBCATEGORIES);
             case "CREATURE" -> {
                 discovery.setSubcategoriesTable(DiscoveryArrays.CREATURE_SUBCATEGORIES);
                 discovery.setPromptTable(DiscoveryArrays.CREATURE_SUBCATEGORIES);
                 discovery.setPrompt("Creature");
             }
             case "STRUCTURE" -> discovery.setSubcategoriesTable(DiscoveryArrays.STRUCTURE_SUBCATEGORIES);
        }



        discovery.setSubcategory(PickFrom(discovery.getSubcategoriesTable()));

        switch (discovery.getSubcategory()){

            case "Divine" -> discovery.setPromptTable(DiscoveryArrays.DIVINE_PROMPTS);
            case "Planar" -> discovery.setPromptTable(DiscoveryArrays.PLANAR_PROMPTS);
            case "Arcane" -> discovery.setPromptTable(DiscoveryArrays.ARCANE_PROMPTS);
            case "Lair" -> discovery.setPromptTable(DiscoveryArrays.LAIR_PROMPTS);
            case "Terrain Change" -> discovery.setPromptTable(DiscoveryArrays.TERRAIN_CHANGE_PROMPTS);
            case "Water Feature" -> discovery.setPromptTable(DiscoveryArrays.WATER_FEATURE_PROMPTS);
            case "Landmark" -> discovery.setPromptTable(DiscoveryArrays.LANDMARK_PROMPTS);
            case "Flora" -> discovery.setPromptTable(DiscoveryArrays.FLORA_PROMPTS);
            case "Fauna" -> discovery.setPromptTable(DiscoveryArrays.FAUNA_PROMPTS);
            case "Resource" -> discovery.setPromptTable(DiscoveryArrays.RESOURCE_PROMPTS);
            case "Tracks" -> discovery.setPromptTable(DiscoveryArrays.TRACKS_PROMPTS);
            case "Remains" -> discovery.setPromptTable(DiscoveryArrays.REMAINS_PROMPTS);
            case "Debris" -> discovery.setPromptTable(DiscoveryArrays.DEBRIS_PROMPTS);
            case "Stash" -> discovery.setPromptTable(DiscoveryArrays.STASH_PROMPTS);
            case "Enigmatic" -> discovery.setPromptTable(DiscoveryArrays.ENIGMATIC_PROMPTS);
            case "Infrastructure" -> discovery.setPromptTable(DiscoveryArrays.INFRASTRUCTURE_PROMPTS);
            case "Dwelling" -> discovery.setPromptTable(DiscoveryArrays.DWELLING_PROMPTS);
            case "Religious" -> discovery.setPromptTable(DiscoveryArrays.RELIGIOUS_PROMPTS);
            case "Ruin" -> discovery.setPromptTable(DiscoveryArrays.RUIN_PROMPTS);
            case "Steading" -> discovery.setPromptTable(DiscoveryArrays.STEADING_PROMPTS);
        }

        discovery.setPrompt(PickFrom(discovery.getPromptTable()));

        switch (discovery.getPrompt()){
            case "Lair RUIN" -> rollRuins(discovery);
            case "pocket of TERRAIN" -> {
                String terrain = PickFrom(DetailsArrays.TERRAIN);
                discovery.setFinalResult("Pocket of "+terrain);
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "Landmark ODDITY" -> {
                String oddity = creatureService.rollOddity();
                discovery.setFinalResult("Landmark "+oddity);
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "notable BEAST" -> {
                String beast = creatureService.rollBeast();
                discovery.setFinalResult("Notable "+beast);
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "useful BEAST" -> {
                String beast = creatureService.rollBeast();
                discovery.setFinalResult("Useful "+beast);
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "bones of CREATURE" -> {
                Creature c = new Creature();
                creatureService.rollAttributes(c);
                discovery.setFinalResult(String.format("""
                        Bones of a creature:
                        %s""", c));
                discovery.setOneLiner("Bones of a "+c.getOneLiner());
            }
            case "CREATURE carcass" -> {
                Creature c = new Creature();
                creatureService.rollAttributes(c);
                discovery.setFinalResult(String.format("""
                        Creature carcass:
                        %s""", c));
                discovery.setOneLiner("Carcass of a "+c.getOneLiner());
            }
            case "Creature" -> {
                Creature c = new Creature();
                creatureService.rollAttributes(c);
                discovery.setFinalResult(c.toString());
                discovery.setOneLiner(c.getOneLiner());
            }
            case "treasure" -> {
                int n1 = Rolls.UniversalRoll(DiscoveryArrays.TREASURE_TABLE); // números aleatorios entre 0 y length-1, promediados para que tienda al centro
                int n2 = Rolls.UniversalRoll(DiscoveryArrays.TREASURE_TABLE);
                int n3 = (n1 + n2) / 2;
                discovery.setPrompt(DiscoveryArrays.TREASURE_TABLE[n3]);
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "Enigmatic ODDITY" -> {
                String oddity = creatureService.rollOddity();
                discovery.setFinalResult("Enigmatic "+oddity);
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "DUNGEON" -> {
                Dungeon d = new Dungeon();
                dungeonService.rollDungeon(d);
                discovery.setFinalResult("Dungeon in ruins:\n"+d);
                discovery.setOneLiner("Dungeon in ruins: "+d.getOneLiner());
            }
            case "STEADING" -> {
                Steading s = new Steading();
                steadingService.rollSteading(s);
                discovery.setFinalResult("Steading in ruins:\n"+s);
                discovery.setOneLiner("Steading in ruins: "+s.getOneLiner());
            }
            case "religious" -> {
                int roll = Rolls.Roll1d8()+4;
                String structure = DiscoveryArrays.RELIGIOUS_PROMPTS[roll];
                discovery.setFinalResult(structure+" in ruins");
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "dwelling" -> {
                int roll = Rolls.Roll1d8()+4;
                String structure = DiscoveryArrays.DWELLING_PROMPTS[roll];
                discovery.setFinalResult(structure+" in ruins");
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "infrastructure" -> {
                int roll = Rolls.Roll1d8()+4;
                String structure = DiscoveryArrays.INFRASTRUCTURE_PROMPTS[roll];
                discovery.setFinalResult(structure+" in ruins");
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "village" -> {
                Steading s = new Steading();
                steadingService.rollSteading(s,"VILLAGE");
                discovery.setFinalResult(s.toString());
                discovery.setOneLiner("Village: "+s.getOneLiner());
            }
            case "town" -> {
                Steading s = new Steading();
                steadingService.rollSteading(s,"TOWN");
                discovery.setFinalResult(s.toString());
                discovery.setOneLiner("Town: "+s.getOneLiner());
            }
            case "keep" -> {
                Steading s = new Steading();
                steadingService.rollSteading(s,"KEEP");
                discovery.setFinalResult(s.toString());
                discovery.setOneLiner("Keep: "+s.getOneLiner());
            }
            case "city" -> {
                Steading s = new Steading();
                steadingService.rollSteading(s,"CITY");
                discovery.setFinalResult(s.toString());
                discovery.setOneLiner("City: "+s.getOneLiner());
            }
            default -> {
                discovery.setFinalResult(discovery.getPrompt());
                discovery.setOneLiner(discovery.getFinalResult());
            }

        }


    }

    private void rollRuins(Discovery discovery){
        String ruin = PickFrom(DiscoveryArrays.RUIN_PROMPTS);

        switch (ruin) {

            case "DUNGEON" -> {
                Dungeon d = new Dungeon();
                dungeonService.rollDungeon(d);
                discovery.setFinalResult("A lair in a Dungeon in ruins:\n"+d);
                discovery.setOneLiner("A lair in a Dungeon in ruins: "+d.getOneLiner());
            }
            case "STEADING" -> {
                Steading s = new Steading();
                steadingService.rollSteading(s);
                discovery.setFinalResult("A lair in a "+s.getSize().toLowerCase()+" in ruins:\n"+s);
                discovery.setOneLiner("A lair in a "+s.getSize().toLowerCase()+" in ruins:\n"+s.getOneLiner());
            }
            case "religious" -> {
                int roll = Rolls.Roll1d8() + 4;
                String structure = DiscoveryArrays.RELIGIOUS_PROMPTS[roll];
                discovery.setFinalResult(structure + " in ruins");
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "dwelling" -> {
                int roll = Rolls.Roll1d8() + 4;
                String structure = DiscoveryArrays.DWELLING_PROMPTS[roll];
                discovery.setFinalResult(structure + " in ruins");
                discovery.setOneLiner(discovery.getFinalResult());
            }
            case "infrastructure" -> {
                int roll = Rolls.Roll1d8() + 4;
                String structure = DiscoveryArrays.INFRASTRUCTURE_PROMPTS[roll];
                discovery.setFinalResult(structure + " in ruins");
                discovery.setOneLiner(discovery.getFinalResult());
            }
            default -> {}
        }
    }

    @Override
    public String showOptions(Scanner dataInput, Class<Discovery> parameterClass) {
        Discovery discovery;
        if(sessionManager.getSelected(parameterClass)==null) {
            discovery = new Discovery();
        } else {
            discovery = sessionManager.getSelected(parameterClass);
        }
        int option;
        System.out.println("WELCOME TO THE DISCOVERY GENERATOR\n");
        String menu = "MAIN_MENU";
        try{
            do {
                System.out.print("""
                        Please select an option:
                        1) Create new random discovery
                        2) View current discovery
                        3) View list of generated discoveries
                        4) Export current
                        5) MANAGE DB
                        0) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 ->{
                        rollDiscovery(discovery);
                        sessionManager.add(Discovery.class,discovery.clone());
                        printWithFlair(discovery);
                    }
                    case 2 -> {
                        if(discovery.getCategory()==null){
                            rollDiscovery(discovery);
                            sessionManager.add(Discovery.class,discovery.clone());
                        }
                        printWithFlair(discovery);
                        System.out.println("\n");
                    }
                    case 3 -> discovery = viewAll.run(dataInput,Discovery.class);
                    case 4 -> {
                        if(discovery.getCategory()==null){
                            rollDiscovery(discovery);
                            sessionManager.add(Discovery.class,discovery.clone());
                        }
                        GenericFunctions.exportPW(discovery);
                    }
                    case 5 -> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");

                }
            }while (option !=0);
        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }
        return menu;
    }



}
