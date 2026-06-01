package dw.randomizer.service;

import dw.randomizer.data.CreatureArrays;
import dw.randomizer.data.DetailsArrays;
import dw.randomizer.model.Creature;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.CreatureRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.*;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class CreatureService implements IGenericService<Creature>, IGenericCRUDService<Creature> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ViewAll viewAll;

    @Autowired
    CreatureRepository creatureRepository;

    @Override
    public List<Creature> list() {
        return creatureRepository.findAll();
    }

    @Override
    public Creature searchById(Integer id) {
        return creatureRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Creature creature) {
        creatureRepository.save(creature);
    }

    @Override
    public void delete(Creature creature) {
        creatureRepository.delete(creature);
    }



    public String rollOddity(){
        String odd = PickFrom(DetailsArrays.ODDITY);

        return switch (odd){
            case "roll twice", "ROLL TWICE" -> rollTwice(DetailsArrays.ODDITY);
            default -> odd;
        };

    }

    public String rollAberrance(){
        String ab = PickFrom(DetailsArrays.ABERRANCE);
        return switch(ab){
            case "anatomical oddity" -> "Anatomical "+ rollOddity();
            case "roll twice", "ROLL TWICE" -> rollTwice(DetailsArrays.ABERRANCE);
            default ->  ab;
        };
    }

    public String rollElement(){
        return PickFrom(DetailsArrays.ELEMENT);
    }

    public String rollMagicType(){
        String mt = PickFrom(DetailsArrays.MAGIC_TYPE);
        return switch (mt){
            case "elemental" -> rollElement();
            default -> mt;
        };
    }

    public String rollAspect(){
        String asp = PickFrom(DetailsArrays.ASPECT);
        return "(Aspect) " + asp;
    }

    public String rollAbility(){
        String abi = PickFrom(DetailsArrays.ABILITY);
        return switch (abi){
            case "based on aspect" ->  "Based on " + rollAspect();
            case "based on element" ->  "Based on " + rollElement();
            case "magic type" ->  "(Magic ability) " + rollMagicType();
            case "roll twice", "ROLL TWICE" -> rollTwice(DetailsArrays.ABILITY);
            default ->  abi;
        };
    }

    public String rollBeast(){
        String beastsubcat = PickFrom(CreatureArrays.SUBCATEGORIES_BEAST);
        var beastPromptsTable = new String[12];

        beastPromptsTable = switch (beastsubcat){
                case "Water-going" -> CreatureArrays.PROMPTS_BEAST_WATER_GOING;
                case "Airborne"-> CreatureArrays.PROMPTS_BEAST_AIRBORNE;
                default -> CreatureArrays.PROMPTS_BEAST_EARTHBOUND;
        };

        return PickFrom(beastPromptsTable);

    }

    public String rollHumanoid(){
        String humanoidSubCat =  PickFrom(CreatureArrays.SUBCATEGORIES_HUMANOID);

        String[] humanoidPromptsTable = switch (humanoidSubCat){
            case "Rare" -> CreatureArrays.PROMPTS_HUMANOID_RARE;
            case "Uncommon" -> CreatureArrays.PROMPTS_HUMANOID_UNCOMMON;
            default -> CreatureArrays.PROMPTS_HUMANOID_COMMON;
        };

        String humanoidPrompt = PickFrom(humanoidPromptsTable);
            return switch (humanoidPrompt) {
                case "Human+Beast" -> String.join(" ", "Human", rollBeast());
                default -> humanoidPrompt;
            };
    }

    public void rollAttributes(Creature creature) {
        //Se asigna tabla de categorías (siempre igual)
        creature.setCategoryTable(CreatureArrays.CATEGORIES);

        // Se extrae el elemento string del array de categorías para impresión
        creature.setCategory(PickFrom(creature.getCategoryTable()));

        //Se asigna tabla de subcategoría con base en subcategoría obtenida en roll anterior
        assignSubcategoryTable(creature);

        //Se saca número aleatorio basándonos en el largo de la tabla de subcategorías y se asigna String de subcategoría
        creature.setSubcategory(PickFrom(creature.getSubcategoryTable()));

        //Se asigna tabla de Prompts
        assignPromptTable(creature);

        //Se saca número aleatorio basándonos en el largo de la tabla de prompts y se asigna prompt
        creature.setPrompt(PickFrom(creature.getPromptTable()));

        reviseRolls(creature);

        rollStats(creature);

        creature.setOneLiner(creature.getPrompt());
    }

    private void assignSubcategoryTable(Creature creature){
        switch (creature.getCategory()) {
            case "Monster" -> creature.setSubcategoryTable(CreatureArrays.SUBCATEGORIES_MONSTER);
            case "Beast" -> creature.setSubcategoryTable(CreatureArrays.SUBCATEGORIES_BEAST);
            default -> creature.setSubcategoryTable(CreatureArrays.SUBCATEGORIES_HUMANOID);
        }

    }

    private void assignPromptTable(Creature creature){
        switch (creature.getSubcategory()) {
            case "Extraplanar" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_EXTRAPLANAR);
            case "Legendary" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_LEGENDARY);
            case "Undead" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_UNDEAD);
            case "Unusual" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_UNUSUAL);
            case "Beastly" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_BEASTLY);
            case "Wild humanoid" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_WILD_HUMANOID);
            case "Water-going" -> creature.setPromptTable(CreatureArrays.PROMPTS_BEAST_WATER_GOING);
            case "Airborne" -> creature.setPromptTable(CreatureArrays.PROMPTS_BEAST_AIRBORNE);
            case "Earthbound" -> creature.setPromptTable(CreatureArrays.PROMPTS_BEAST_EARTHBOUND);
            case "Rare" -> creature.setPromptTable(CreatureArrays.PROMPTS_HUMANOID_RARE);
            case "Uncommon" -> creature.setPromptTable(CreatureArrays.PROMPTS_HUMANOID_UNCOMMON);
            case "Common" -> creature.setPromptTable(CreatureArrays.PROMPTS_HUMANOID_COMMON);
        }

    }

    public void reRollSubcategory(Creature creature) {
        String previousSubcategory = creature.getSubcategory();
        String currentSubcategory;
        do{
            creature.setSubcategory(PickFrom(creature.getSubcategoryTable()));
            currentSubcategory = creature.getSubcategory();
        } while (Objects.equals(previousSubcategory,currentSubcategory));

        switch (creature.getSubcategory()) {
            case "Extraplanar" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_EXTRAPLANAR);
            case "Legendary" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_LEGENDARY);
            case "Undead" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_UNDEAD);
            case "Unusual" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_UNUSUAL);
            case "Beastly" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_BEASTLY);
            case "Wild humanoid" -> creature.setPromptTable(CreatureArrays.PROMPTS_MONSTER_WILD_HUMANOID);
            case "Water-going" -> creature.setPromptTable(CreatureArrays.PROMPTS_BEAST_WATER_GOING);
            case "Airborne" -> creature.setPromptTable(CreatureArrays.PROMPTS_BEAST_AIRBORNE);
            case "Earthbound" -> creature.setPromptTable(CreatureArrays.PROMPTS_BEAST_EARTHBOUND);
            case "Rare" -> creature.setPromptTable(CreatureArrays.PROMPTS_HUMANOID_RARE);
            case "Uncommon" -> creature.setPromptTable(CreatureArrays.PROMPTS_HUMANOID_UNCOMMON);
            case "Common" -> creature.setPromptTable(CreatureArrays.PROMPTS_HUMANOID_COMMON);
        }


            //Se asigna prompt
            creature.setPrompt(PickFrom(creature.getPromptTable()));

        reviseRolls(creature);
        creature.setOneLiner(creature.getPrompt());
    }

    public void reRollPrompt(Creature creature){
        String previousPrompt = creature.getPrompt();
        String currentPrompt;
        do {
            creature.setPrompt(PickFrom(creature.getPromptTable()));
            reviseRolls(creature);
            creature.setOneLiner(creature.getPrompt());
            currentPrompt = creature.getPrompt();
        } while (Objects.equals(previousPrompt,currentPrompt));
    }

    private void reviseRolls(Creature creature){

        switch (creature.getPrompt()) {
            case "Human+Beast" -> creature.setPrompt(String.join(" ","Human", rollBeast()));
            case "Elemental" -> creature.setPrompt(rollElement() + " elemental");
            case "Huge+oddity" -> creature.setPrompt(String.join(" ", "Huge", rollOddity()));
            case "Dragon+beast" -> creature.setPrompt(String.join(" ", "Dragon", rollBeast()));
            case "Giant+beast" -> creature.setPrompt(String.join(" ", "Giant", rollBeast()));
            case "Huge+beast" -> creature.setPrompt(String.join(" ", "Huge", rollBeast()));
            case "Beast+aberrance" -> creature.setPrompt(String.join(" ", rollBeast(), rollAberrance()));
            case "Beast+element" -> creature.setPrompt(String.join(" ", rollElement(), rollBeast()));
            case "Beast+oddity" -> creature.setPrompt(String.join(" ", rollOddity(), rollBeast()));
            case "Beast+ability" -> creature.setPrompt(String.join(" ", rollAbility(), rollBeast()));
            case "Beast+Beast" -> creature.setPrompt(String.join(" ", rollBeast(), rollBeast()));
            case "Humanoid+Oddity" -> creature.setPrompt(String.join(" ", rollOddity(), rollHumanoid()));
            case "Humanoid+Beast" -> creature.setPrompt(String.join(" ", rollHumanoid(), rollBeast()));
        }
    }

    public void rollStats(Creature creature){
        creature.setHitPoints(0);
        rollGroupSize(creature);
        rollSize(creature);
        rollArmor(creature);
        rollDamageType(creature);
        rollTags(creature);
        rollAlignment(creature);
        rollDisposition(creature);

    }

    private void rollGroupSize(Creature creature){
        creature.setGroupSize(PickFrom(DetailsArrays.NO_APPEARING));

        switch (creature.getGroupSize()){
            case "horde (4d6 per wave)" -> {
                creature.setDamage("1d6");
                creature.setHitPoints(3);
            }
            case "group (1d6+2 per wave)" -> {
                creature.setDamage("1d8");
                creature.setHitPoints(6);
            }
            case "solitary (1)" ->{
                creature.setDamage("1d10");
                creature.setHitPoints(12);
            }
        }
    }

    public void setGroupSize(Creature creature, String groupSize){
        creature.setGroupSize(groupSize);

        switch (creature.getGroupSize()){
            case "horde (4d6 per wave)" -> {
                creature.setDamage("1d6");
                creature.setHitPoints(3);
            }
            case "group (1d6+2 per wave)" -> {
                creature.setDamage("1d8");
                creature.setHitPoints(6);
            }
            case "solitary (1)" ->{
                creature.setDamage("1d10");
                creature.setHitPoints(12);
            }
        }

    }

    private void rollSize(Creature creature){
        creature.setSize(PickFrom(DetailsArrays.SIZE));
        switch (creature.getSize()){
            case "Tiny" -> {
                creature.setDamage(creature.getDamage()+"-2");
                creature.setReach("hand");
            }
            case "Small","Medium-sized" ->
                    creature.setReach("close");

            case "Large" ->{
                creature.setReach("Close, reach");
                creature.setHitPoints(creature.getHitPoints()+4);
                creature.setDamage(creature.getDamage()+"+1");
            }
            case "Huge" -> {
                creature.setReach("Reach");
                creature.setHitPoints(creature.getHitPoints()+8);
                creature.setDamage(creature.getDamage()+"+3");
            }
        }

    }

    private void rollArmor(Creature creature){
        creature.setArmorType(PickFrom(DetailsArrays.ARMOR));
        switch (creature.getArmorType()){
            case "Cloth or flesh" -> creature.setArmor(0);
            case "Leathers or thick hides"->  creature.setArmor(1);
            case "Mail or scales" -> creature.setArmor(2);
            case "Plate or bone" -> creature.setArmor(3);
            case "Magical armor" -> creature.setArmor(4);
        }
    }

    private void rollDamageType(Creature creature){
        creature.setDamageType(PickFrom(DetailsArrays.DAMAGE_TYPE));
        switch (creature.getDamageType()){
            case "element" -> {
                String element = DetailsArrays.ELEMENT[UniversalRoll(DetailsArrays.ELEMENT)];
                creature.setDamageType("Element: "+element);
            }
            case "roll twice", "ROLL TWICE" -> creature.setDamageType(rollTwice(DetailsArrays.DAMAGE_TYPE).replace("element", "Element: "+ rollElement()));
        }


    }

    private void rollTags(Creature creature){
        String tag = PickFrom(DetailsArrays.TAG);
        if (tag.equals("roll twice")||tag.equals("ROLL TWICE")) tag = rollTwice(DetailsArrays.TAG);

        creature.setTags(tag);
    }

    private void rollAlignment(Creature creature) {
        creature.setAlignment(PickFrom(DetailsArrays.ALIGNMENT));
    }

    private void rollDisposition(Creature creature) {
        creature.setDisposition(PickFrom(DetailsArrays.DISPOSITION));
    }

    @Override
    public String showOptions(Scanner dataInput, Class<Creature> parameterClass) {
        Creature creature;
        if(sessionManager.getSelected(parameterClass)==null) {
            creature = new Creature();
        } else {
            creature = sessionManager.getSelected(parameterClass);
        }
        String menu = "MAIN_MENU";
        var option = 0;

        System.out.println("WELCOME TO THE CREATURE GENERATOR");

        do {
            try {
                System.out.print("""
                        Please select an option:
                        1) Create new random creature
                        2) Reroll Subcategory
                        3) Reroll Prompt
                        4) Reroll Stats
                        5) View current creature
                        6) View generated creature list
                        7) Export creature
                        8) MANAGE DB
                        0) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        rollAttributes(creature);
                        sessionManager.add(parameterClass,creature.clone());
                        printWithFlair(creature);
                    }
                    case 2 -> {
                        if (creature.getCategory()== null) {
                            rollAttributes(creature);
                            sessionManager.add(parameterClass,creature.clone());
                        } else {
                            reRollSubcategory(creature);
                            sessionManager.add(parameterClass,creature.clone());
                        }
                        printWithFlair(creature);
                    }
                    case 3 -> {
                        if (creature.getCategory() == null) {
                            rollAttributes(creature);
                        } else {
                            reRollPrompt(creature);
                        }
                        sessionManager.add(parameterClass,creature.clone());
                        printWithFlair(creature);
                    }
                    case 4 -> {
                        if (creature.getCategory() == null) {
                            rollAttributes(creature);
                        } else {
                            rollStats(creature);
                        }
                        sessionManager.add(parameterClass,creature.clone());
                        printWithFlair(creature);
                    }
                    case 5 -> {
                        if (creature.getCategory() == null) {
                            rollAttributes(creature);
                            sessionManager.add(parameterClass,creature.clone());
                        }
                        printWithFlair(creature);
                    }
                    case 6 -> creature = viewAll.run(dataInput,parameterClass);
                    case 7 -> {
                        if (creature.getCategory() == null) {
                            rollAttributes(creature);
                            sessionManager.add(parameterClass,creature.clone());
                        }
                        GenericFunctions.exportPW(creature);
                    }
                    case 8 -> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");
                    default -> System.out.print("\nInvalid number!\n\n");
                }
            } catch (Exception e) {
                System.out.println("\nPlease choose a valid option. Error: \n"+e.getMessage());
            }
        } while (option != 0);
        return menu;
    }
}
