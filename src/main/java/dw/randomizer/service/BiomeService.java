package dw.randomizer.service;

import dw.randomizer.data.BiomeArrays;
import dw.randomizer.data.DetailsArrays;
import dw.randomizer.model.Biome;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.BiomeRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.*;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class BiomeService implements IGenericService<Biome>, IGenericCRUDService<Biome> {

    @Autowired
    private ViewAll viewAll;

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private BiomeRepository biomeRepository;

    public void rollBiome(Biome biome){
//        private String biome;
        biome.setBiome(PickFrom(BiomeArrays.BIOME));
//        private String weather;
        biome.setWeather(PickFrom(BiomeArrays.WEATHER));
//        private String weatherIntensity;
        biome.setWeatherIntensity(PickFrom(BiomeArrays.WEATHER_INTENSITY));
//        private String wildlife;
        biome.setWildlife(PickFrom(BiomeArrays.WILDLIFE));
//        private String population;
        biome.setPopulation(PickFrom(BiomeArrays.POPULATION));
//        private String roads;
        biome.setRoads(PickFrom(BiomeArrays.ROADS));
//        private String alignment;
        biome.setAlignment(PickFrom(DetailsArrays.ALIGNMENT));
//        private String distance;
        biome.setDistance(PickFrom(BiomeArrays.DISTANCE));
        biome.setOneLiner(String.format("%s %s",biome.getPopulation(),biome.getBiome()));
    }

    public void reRollDetails(Biome biome){
        //        private String weather;
        biome.setWeather(PickFrom(BiomeArrays.WEATHER));
//        private String weatherIntensity;
        biome.setWeatherIntensity(PickFrom(BiomeArrays.WEATHER_INTENSITY));
//        private String wildlife;
        biome.setWildlife(PickFrom(BiomeArrays.WILDLIFE));
//        private String population;
        biome.setPopulation(PickFrom(BiomeArrays.POPULATION));
//        private String roads;
        biome.setRoads(PickFrom(BiomeArrays.ROADS));
//        private String alignment;
        biome.setAlignment(PickFrom(DetailsArrays.ALIGNMENT));
//        private String distance;
        biome.setDistance(PickFrom(BiomeArrays.DISTANCE));
        biome.setOneLiner(String.format("%s %s",biome.getPopulation(),biome.getBiome()));

    }

    @Override
    public String showOptions(Scanner dataInput, Class<Biome> parameterClass) {
        Biome biome;
        if(sessionManager.getSelected(parameterClass)==null) {
            biome = new Biome();
        } else {
            biome = sessionManager.getSelected(parameterClass);
        }
        int option;
        System.out.println("WELCOME TO THE BIOME GENERATOR\n");
        String menu = "MAIN_MENU";
        try{
            do {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random Biome
                        2) View current
                        3) Reroll this biome
                        4) View list of generated biomes
                        5) Export current biome
                        6) MANAGE DATABASE
                        0) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 ->{
                        rollBiome(biome);
                        sessionManager.add(Biome.class,biome.clone());
                        printWithFlair(sessionManager.getSelected(Biome.class));
                    }
                    case 2 -> {
                        if(biome.getBiome()==null){
                            rollBiome(biome);
                            sessionManager.add(Biome.class,biome.clone());
                        }
                        printWithFlair(biome);
                    }
                    case 3 -> {
                        if(biome.getBiome()==null){
                            rollBiome(biome);
                            sessionManager.add(Biome.class,biome.clone());
                        } else {
                            reRollDetails(biome);
                            sessionManager.add(Biome.class,biome.clone());
                        }
                        printWithFlair(biome);
                    }
                    case 4 -> biome = viewAll.run(dataInput,Biome.class);
                    case 5 -> {
                        if(biome.getBiome()==null){
                            rollBiome(biome);
                            sessionManager.add(Biome.class,biome.clone());
                        }
                        GenericFunctions.exportPW(biome);
                    }
                    case 6 -> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");

                }
            }while (option !=0);
        }catch (Exception e){
            System.out.println("\nPlease choose a valid option. Error: \n"+e.getMessage());
        }
        return menu;
    }

    @Override
    public List<Biome> list() {
        return biomeRepository.findAll();
    }

    @Override
    public Biome searchById(Integer id) {
        return biomeRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Biome biome) {
        biomeRepository.save(biome);
    }

    @Override
    public void delete(Biome biome) {
        biomeRepository.delete(biome);
    }



}
