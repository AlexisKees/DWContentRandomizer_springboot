package dw.randomizer.service;

import dw.randomizer.data.DangerArrays;
import dw.randomizer.data.DetailsArrays;
import dw.randomizer.model.*;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.DangerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

public class DangerService implements IGenericService<Danger>, IDangerCRUDService {

    @Autowired
    DangerRepository dangerRepository;

    @Override
    public List<Danger> listDangers() {
        List<Danger> dangerList = dangerRepository.findAll();
        return dangerList;
    }

    @Override
    public Danger searchById(Integer id) {
        Danger danger = dangerRepository.findById(id).orElse(null);
        return danger;
    }

    @Override
    public void saveDanger(Danger danger) {
        dangerRepository.save(danger);
    }

    @Override
    public void deleteDanger(Danger danger) {
        dangerRepository.delete(danger);
    }

    public static void rollDanger(Danger danger){
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
            String element = CreatureService.rollElement();
            danger.setFinalResult("lesser "+element+" elemental");
        }
        case "elemental" ->{
            String element = CreatureService.rollElement();
            danger.setFinalResult(element+" elemental");
        }
        case "greater elemental" -> {
            String element = CreatureService.rollElement();
            danger.setFinalResult("greater "+element+" elemental");
        }
        case "elemental lord" -> {
            String element = CreatureService.rollElement();
            danger.setFinalResult(element+" elemental lord");
        }
        case "magical: natural + MAGIC TYPE" -> {
            String magicType = CreatureService.rollMagicType();
            danger.setFinalResult("Magical natural phenomenon: "+magicType);
            danger.setOneLiner(danger.getFinalResult());
        }
        case "planar: natural + ELEMENT" -> {
            String magicType = CreatureService.rollMagicType();
            danger.setFinalResult("Planar natural phenomenon: "+magicType);
            danger.setOneLiner(danger.getFinalResult());
        }
        case "oddity-based" -> {
            String oddity = CreatureService.rollOddity();
            danger.setFinalResult("Natural "+oddity.toLowerCase());
            danger.setOneLiner(danger.getFinalResult());
        }
        case "Creature"->{
            Creature c = new Creature();
            CreatureService.rollAttributes(c);
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
    public void showOptions(Scanner dataInput, Danger danger, List<Danger> dangerList) {
        int option;
        System.out.println("WELCOME TO THE DANGER GENERATOR\n");

        try{
            do {
                System.out.print("""
                            Please select an option:
                            1) Create new random danger
                            2) View current danger
                            3) View list of generated dangers
                            4) Export current
                            5) Main menu
                            
                            \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 ->{
                        danger = new Danger();
                        DangerService.rollDanger(danger);
                        dangerList.add(danger.clone());
                        printWithFlair(danger);
                    }
                    case 2 -> {
                        if (danger == null){
                            danger = new Danger();
                            DangerService.rollDanger(danger);
                        }
                        dangerList.add(danger.clone());
                        printWithFlair(danger);
                    }
                    case 3 -> danger = new ViewAll().run(dataInput,dangerList,danger, Danger.class);
                    case 4 -> {
                        if (danger == null){
                            danger = new Danger();
                            DangerService.rollDanger(danger);
                            dangerList.add(danger.clone());
                        }
                        GenericFunctions.exportPW(danger);
                    }
                    case 5 -> System.out.println("\nReturning to main menu...\n");

                }
            }while (option !=5);
        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }
    }
}
