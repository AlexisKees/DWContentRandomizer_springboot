package dw.randomizer.service;

import dw.randomizer.data.DetailsArrays;
import dw.randomizer.data.SteadingArrays;
import dw.randomizer.model.Steading;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.SteadingRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class SteadingService implements IGenericService<Steading>, IGenericCRUDService<Steading> {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private ViewAll viewAll;
    @Autowired
    CreatureService creatureService;

    @Autowired
    SteadingRepository steadingRepository;

    @Override
    public List<Steading> list() {
        return steadingRepository.findAll();
    }

    @Override
    public Steading searchById(Integer id) {
        return steadingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Steading steading) {
        steadingRepository.save(steading);
    }

    @Override
    public void delete(Steading steading) {
        steadingRepository.delete(steading);
    }




    public void rollSteading(Steading steading){
        steading.setSize(PickFrom(SteadingArrays.SETTLEMENT_SIZE));
        rollDetails(steading);
    }

    public void rollSteading(Steading steading, String size){
        steading.setSize(size);
        rollDetails(steading);
    }

    private void rollDetails(Steading steading){
        steading.setName(PickFrom(SteadingArrays.STEADING_NAMES));
        switch (steading.getSize()){
            case "VILLAGE" ->{
                steading.setTags("Poor, steady, militia, resource (GM's choice)");
                steading.setFeature(PickFrom(SteadingArrays.VILLAGE_FEATURES));
                steading.setProblem(PickFrom(SteadingArrays.VILLAGE_PROBLEMS));
            }
            case "TOWN" -> {
                steading.setTags("Moderate, steady, watch, and trade (with 2 places of GM’s choice).");
                steading.setFeature(PickFrom(SteadingArrays.TOWN_FEATURES));
                steading.setProblem(PickFrom(SteadingArrays.TOWN_PROBLEMS));
            }
            case "KEEP" -> {
                steading.setTags("Poor, steady, militia, resource (GM decides or rolls)");
                steading.setFeature(PickFrom(SteadingArrays.KEEP_FEATURES));
                steading.setProblem(PickFrom(SteadingArrays.KEEP_PROBLEMS));
            }
            case "CITY"-> {
                steading.setTags("Poor, steady, militia, resource (GM decides or rolls)");
                steading.setFeature(PickFrom(SteadingArrays.CITY_FEATURES));
                steading.setProblem(PickFrom(SteadingArrays.CITY_PROBLEMS));
            }
        }

        steading.setRaceOfBuilders(creatureService.rollHumanoid());
        steading.setAlignment(PickFrom(DetailsArrays.ALIGNMENT));
        steading.setDangerLevel(PickFrom(SteadingArrays.DANGER_LEVEL));
        steading.setOneLiner(steading.getName()+", "+steading.getRaceOfBuilders()+" "+steading.getSize().toLowerCase());
    }

    @Override
    public String showOptions(Scanner dataInput, Class<Steading> parameterClass) {
        Steading steading;
        if(sessionManager.getSelected(parameterClass)==null) {
            steading = new Steading();
        } else {
            steading = sessionManager.getSelected(parameterClass);
        }
        int option;
        System.out.println("WELCOME TO THE STEADING GENERATOR\n");
        String menu = "MAIN_MENU";
        try {
            do {
                System.out.print("""
                        Please select an option:
                        1) Create new random steading
                        2) View current
                        3) View list of generated steadings
                        4) Export current
                        5) MANAGE DB
                        0) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        rollSteading(steading);
                        sessionManager.add(Steading.class,steading);
                        printWithFlair(steading);
                    }
                    case 2 -> {
                        if(steading.getSize() == null) {
                            rollSteading(steading);
                            sessionManager.add(Steading.class,steading);
                        }
                        printWithFlair(steading);
                    }
                    case 3 -> steading = viewAll.run(dataInput, Steading.class);
                    case 4 -> {
                        if(steading.getSize() == null) {
                            rollSteading(steading);
                            sessionManager.add(Steading.class,steading);
                        }
                        GenericFunctions.exportPW(steading);
                    }
                    case 5-> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");

                }

            } while (option != 0);
        } catch (Exception e) {
            System.out.println("\nPlease choose a valid option.\n");
        }
        return menu;
    }
}
