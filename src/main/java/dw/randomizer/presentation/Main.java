package dw.randomizer.presentation;

import dw.randomizer.model.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner dataInput = new Scanner(System.in);
//        List<Quest> questList = new ArrayList<>();
//        List<NPC> npcList = new ArrayList<>();
//        List<Follower> followerList = new ArrayList<>();
//        List<Steading> steadingList = new ArrayList<>();
//        List<Dungeon> dungeonList = new ArrayList<>();
//        List<Area> areaList = new ArrayList<>();
        List<Biome> biomeList = new ArrayList<>();
//        List<Discovery> discoveryList = new ArrayList<>();
//        List<Danger> dangerList = new ArrayList<>();
//        List<Creature> creatureList = new ArrayList<>();
/*
        try {
            int option;
            do {
                System.out.println("""
                
                
                *********************************
                  Welcome to the Dungeon World
                  random content generator
                *********************************
                Created by Alexis Kees Bahl
                Based on "The Perilous Wilds, revised edition".
                """);

                System.out.print("""
                        Chose what you'd like to generate:
                        1)  Generate Area
                        2)  Generate Biome
                        3)  Generate Creature
                        4)  Generate Danger
                        5)  Generate Discovery
                        6)  Generate Dungeon
                        7)  Generate Follower
                        8)  Generate NPC
                        9)  Generate Quest
                        10) Generate Steading
                        11) Exit
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                 switch (option){
                     case 1 -> {
                         Area area = new Area();
                         SubMenu.run(dataInput, area, areaList);
                     }
                     case 2 -> {
                         Biome biome = new Biome();
                         SubMenu.run(dataInput,biome,biomeList);
                     }
                     case 3 -> {
                         Creature creature = new Creature();
                         SubMenu.run(dataInput, creature, creatureList);
                     }
                     case 4 -> {
                         Danger danger = new Danger();
                         SubMenu.run(dataInput, danger, dangerList);
                     }
                     case 5 -> {
                         Discovery discovery = new Discovery();
                         SubMenu.run(dataInput,discovery, discoveryList);
                     }
                     case 6 -> {
                         Dungeon dungeon = new Dungeon();
                         SubMenu.run(dataInput, dungeon, dungeonList, areaList);
                     }
                     case 7 -> {
                         Follower follower = new Follower();
                         SubMenu.run(dataInput,follower, followerList);
                     }
                     case 8 -> {
                         NPC npc = new NPC();
                         SubMenu.run(dataInput,npc, npcList);
                     }
                     case 9 -> {
                         Quest quest = new Quest();
                         SubMenu.run(dataInput, quest, questList, npcList, dungeonList, biomeList);
                     }
                     case 10 -> {
                         Steading steading = new Steading();
                         SubMenu.run(dataInput, steading, steadingList);
                     }
                     case 11 -> System.out.println("Come back soon!");
                     default -> System.out.println("Please, chose a valid option");
                 }

            } while (option != 11);
        } catch (Exception e){
            System.out.println("Please, chose a valid option. Error: "+e.getMessage());
        }

 */
    }
}
