package dw.randomizer.service;

import dw.randomizer.data.DangerArrays;
import dw.randomizer.data.DetailsArrays;
import dw.randomizer.model.*;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.DangerRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class DangerService implements IGenericService<Danger>, IGenericCRUDService<Danger> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ViewAll viewAll;

    @Autowired
    private CreatureService creatureService;

    @Autowired
    private DangerRepository dangerRepository;

    @Override
    public List<Danger> list() {
        return dangerRepository.findAll();
    }

    @Override
    public Danger searchById(Integer id) {
        return dangerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Danger danger) {
        dangerRepository.save(danger);
    }

    @Override
    public void delete(Danger danger) {
        dangerRepository.delete(danger);
    }



    public void rollDanger(Danger danger){
        danger.setCategory(PickFrom(DangerArrays.DANGER_CATEGORIES));

        switch (danger.getCategory()){
            case "UNNATURAL ENTITY" ->  danger.setSubcategoriesTable(DangerArrays.UNNATURAL_ENTITY_SUBCATEGORIES);
            case "HAZARD" -> danger.setSubcategoriesTable(DangerArrays.HAZARD_SUBCATEGORIES);
            case "CREATURE" -> danger.setSubcategoriesTable(DangerArrays.CREATURE_SUBCATEGORIES);
        }

        danger.setSubcategory(PickFrom(danger.getSubcategoriesTable()));

        switch (danger.getSubcategory()){
            case "Divine" -> danger.setPromptTable(DangerArrays.DIVINE_PROMPTS);
            case "Planar" -> danger.setPromptTable(DangerArrays.PLANAR_PROMPTS);
            case "Undead" -> danger.setPromptTable(DangerArrays.UNDEAD_PROMPTS);
            case "Unnatural" -> danger.setPromptTable(DangerArrays.UNNATURAL_PROMPTS);
            case "Natural" -> danger.setPromptTable(DangerArrays.NATURAL_PROMPTS);
            case "Creature" -> danger.setPromptTable(DangerArrays.CREATURE_SUBCATEGORIES);
        }

        danger.setPrompt(PickFrom(danger.getPromptTable()));

        switch (danger.getPrompt()) {
        case "lesser elemental" -> {
            String element = creatureService.rollElement();
            danger.setFinalResult("lesser "+element+" elemental");
        }
        case "elemental" ->{
            String element = creatureService.rollElement();
            danger.setFinalResult(element+" elemental");
        }
        case "greater elemental" -> {
            String element = creatureService.rollElement();
            danger.setFinalResult("greater "+element+" elemental");
        }
        case "elemental lord" -> {
            String element = creatureService.rollElement();
            danger.setFinalResult(element+" elemental lord");
        }
        case "magical: natural + MAGIC TYPE" -> {
            String magicType = creatureService.rollMagicType();
            danger.setFinalResult("Magical natural phenomenon: "+magicType);
            danger.setOneLiner(danger.getFinalResult());
        }
        case "planar: natural + ELEMENT" -> {
            String magicType = creatureService.rollMagicType();
            danger.setFinalResult("Planar natural phenomenon: "+magicType);
            danger.setOneLiner(danger.getFinalResult());
        }
        case "oddity-based" -> {
            String oddity = creatureService.rollOddity();
            danger.setFinalResult("Natural "+oddity.toLowerCase());
            danger.setOneLiner(danger.getFinalResult());
        }
        case "Creature"->{
            Creature c = new Creature();
            creatureService.rollAttributes(c);
            c.setDisposition(DetailsArrays.DISPOSITION[0]); //SET DISPOSITION TO "ATTACKING"
            danger.setFinalResult(c.toString());
            danger.setOneLiner(c.getOneLiner());
        }
            default -> {
                danger.setFinalResult(danger.getPrompt());
                danger.setOneLiner(danger.getFinalResult());
            }
        }


    }

    @Override
    public String showOptions(Scanner dataInput, Class<Danger> parameterClass) {
        Danger danger;
        if(sessionManager.getSelected(parameterClass)==null) {
            danger = new Danger();
        } else {
            danger = sessionManager.getSelected(parameterClass);
        }
        int option;
        System.out.println("WELCOME TO THE DANGER GENERATOR\n");
        String menu = "MAIN_MENU";
        try{
            do {
                System.out.print("""
                            Please select an option:
                            1) Create new random danger
                            2) View current danger
                            3) View list of generated dangers
                            4) Export current
                            5) MANAGE DB
                            0) Main menu
                            
                            \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 ->{
                        rollDanger(danger);
                        sessionManager.add(Danger.class,danger.clone());
                        printWithFlair(danger);
                    }
                    case 2 -> {
                        if (danger.getCategory() == null){
                            rollDanger(danger);
                        }
                        sessionManager.add(Danger.class,danger.clone());
                        printWithFlair(danger);
                    }
                    case 3 -> danger = viewAll.run(dataInput,Danger.class);
                    case 4 -> {
                        if (danger.getCategory() == null){
                            rollDanger(danger);
                            sessionManager.add(Danger.class,danger.clone());
                        }
                        GenericFunctions.exportPW(danger);
                    }
                    case 5-> {
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
