package dw.randomizer.service;

import dw.randomizer.data.BiomeArrays;
import dw.randomizer.data.DetailsArrays;
import dw.randomizer.model.Biome;
import dw.randomizer.repository.BiomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.*;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class BiomeService implements IGenericService<Biome>, IBiomeCRUDService {
    @Autowired
    private BiomeRepository biomeRepository;

    public static void rollBiome(Biome biome){
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

    public static void reRollDetails(Biome biome){
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
    public void showOptions(Scanner dataInput, Biome biome, List<Biome> biomeList) {
        int option;
        System.out.println("WELCOME TO THE BIOME GENERATOR\n");

        try{
            do {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random Biome
                        2) View current
                        3) Reroll this biome
                        4) View list of generated biomes
                        5) Export current biome
                        6) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option){
                    case 1 ->{
                        biome = new Biome();
                        BiomeService.rollBiome(biome);
                        biomeList.add(biome.clone());
                        printWithFlair(biome);
                    }
                    case 2 -> {
                        if(biome==null){
                            biome = new Biome();
                            BiomeService.rollBiome(biome);
                            biomeList.add(biome.clone());
                        }
                        System.out.println(biome);
                    }
                    case 3 -> {
                        if(biome==null){
                            biome = new Biome();
                            BiomeService.rollBiome(biome);
                            biomeList.add(biome.clone());
                        } else {
                            BiomeService.reRollDetails(biome);
                            biomeList.add(biome.clone());
                        }
                        System.out.println(biome);
                    }
//                    case 4 -> biome = new ViewAll().run(dataInput,biomeList,biome, Biome.class);
                    case 5 -> {
                        if(biome==null){
                            biome = new Biome();
                            BiomeService.rollBiome(biome);
                            biomeList.add(biome.clone());
                        }
                        GenericFunctions.exportPW(biome);
                    }
                    case 6 -> System.out.println("\nReturning to main menu...\n");

                }
            }while (option !=6);
        }catch (Exception e){
            System.out.println("\nPlease choose a valid option.\n");
        }
    }

    @Override
    public List<Biome> listBiome() {
        List<Biome> biomes = biomeRepository.findAll();
        return biomes;
    }

    @Override
    public Biome searchByIdBiome(Integer id) {
        Biome biome = biomeRepository.findById(id).orElse(null);
        return biome;
    }

    @Override
    public void saveBiome(Biome biome) {
        biomeRepository.save(biome);

    }

    @Override
    public void deleteBiome(Biome biome) {
        biomeRepository.delete(biome);

    }

}
