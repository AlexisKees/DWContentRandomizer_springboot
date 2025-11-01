package dw.randomizer.service;

import dw.randomizer.data.DetailsArrays;
import dw.randomizer.data.SteadingArrays;
import dw.randomizer.model.Steading;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.SteadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class SteadingService implements IGenericService<Steading>, ISteadingCRUDService {

    @Autowired
    SteadingRepository steadingRepository;

    @Override
    public List<Steading> listCRUD() {
        List<Steading> steadingList = steadingRepository.findAll();
        return steadingList;
    }

    @Override
    public Steading searchByIdCRUD(Integer id) {
        Steading steading = steadingRepository.findById(id).orElse(null);
        return steading;
    }

    @Override
    public void saveCRUD(Steading steading) {
        steadingRepository.save(steading);
    }

    @Override
    public void deleteCRUD(Steading steading) {
        steadingRepository.delete(steading);
    }


    public static void rollSteading(Steading steading){
        steading.setSize(PickFrom(SteadingArrays.SETTLEMENT_SIZE));
        rollDetails(steading);
    }

    public static void rollSteading(Steading steading, String size){
        steading.setSize(size);
        rollDetails(steading);
    }

    private static void rollDetails(Steading steading){
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

        steading.setRaceOfBuilders(CreatureService.rollHumanoid());
        steading.setAlignment(PickFrom(DetailsArrays.ALIGNMENT));
        steading.setDangerLevel(PickFrom(SteadingArrays.DANGER_LEVEL));
        steading.setOneLiner(steading.getName()+", "+steading.getRaceOfBuilders()+" "+steading.getSize().toLowerCase());
    }

    @Override
    public void showOptions(Scanner dataInput, Steading steading, List<Steading> steadingList) {
        int option;
        System.out.println("WELCOME TO THE STEADING GENERATOR\n");

        try {
            do {
                System.out.print("""
                        Please select an option:
                        1) Create new random steading
                        2) View current
                        3) View list of generated steadings
                        4) Export current
                        5) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        steading = new Steading();
                        SteadingService.rollSteading(steading);
                        steadingList.add(steading);
                        printWithFlair(steading);
                    }
                    case 2 -> {
                        if (steading == null) {
                            steading = new Steading();
                            SteadingService.rollSteading(steading);
                            steadingList.add(steading);
                        }
                        printWithFlair(steading);
                    }
                    case 3 -> steading = new ViewAll().run(dataInput, steadingList, steading, Steading.class);
                    case 4 -> {
                        if (steading == null) {
                            steading = new Steading();
                            SteadingService.rollSteading(steading);
                            steadingList.add(steading);
                        }
                        GenericFunctions.exportPW(steading);
                    }
                    case 5 -> System.out.println("\nReturning to main menu...\n");

                }

            } while (option != 5);
        } catch (Exception e) {
            System.out.println("\nPlease choose a valid option.\n");
        }
    }
}
