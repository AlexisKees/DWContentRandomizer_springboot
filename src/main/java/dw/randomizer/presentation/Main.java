package dw.randomizer.presentation;

import dw.randomizer.model.*;
import dw.randomizer.service.*;
import dw.randomizer.service.util.ClassIdentifier;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {
    @Autowired
    private DBMenu dbMenu;
    @Autowired
    private AreaService areaService;
    @Autowired
    private BiomeService biomeService;
    @Autowired
    private CreatureService creatureService;
    @Autowired
    private DangerService dangerService;
    @Autowired
    private DiscoveryService discoveryService;
    @Autowired
    private DungeonService dungeonService;
    @Autowired
    private FollowerService followerService;
    @Autowired
    private NPCService npcService;
    @Autowired
    private QuestService questService;
    @Autowired
    private SteadingService steadingService;
    @Autowired
    private ClassIdentifier classIdentifier;
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private SubMenu subMenu;

    public void run() {
        Scanner dataInput = new Scanner(System.in);

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
                        0) Exit
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                 switch (option){
                     case 1 -> {
                         String menu = subMenu.run(dataInput, Area.class);
                         if(menu.equals("DB_MENU")) {
                             if (sessionManager.getSelected(Area.class)==null){
                                 Area area = new Area();
                                 areaService.rollArea(area);
                                 sessionManager.add(Area.class,area);
                             }
                             dbMenu.run(dataInput, Area.class);
                         }
                     }
                     case 2 -> {

                         String menu = subMenu.run(dataInput,Biome.class);
                         if(menu.equals("DB_MENU")){
                             if (sessionManager.getSelected(Biome.class)==null){
                                 Biome biome = new Biome();
                                 biomeService.rollBiome(biome);
                                 sessionManager.add(Biome.class,biome);
                             }
                             dbMenu.run(dataInput,Biome.class);
                         }
                     }
                     case 3 -> {
                         String menu = subMenu.run(dataInput, Creature.class);
                         if(menu.equals("DB_MENU")){
                             if(sessionManager.getSelected(Creature.class)==null){
                                 Creature creature = new Creature();
                                 creatureService.rollAttributes(creature);
                                 sessionManager.add(Creature.class,creature);
                             }
                             dbMenu.run(dataInput, Creature.class);
                         }
                     }
                     case 4 -> {
                         String menu = subMenu.run(dataInput, Danger.class);
                         if(menu.equals("DB_MENU")) {
                             if(sessionManager.getSelected(Danger.class)==null){
                                 Danger danger = new Danger();
                                 dangerService.rollDanger(danger);
                                 sessionManager.add(Danger.class,danger);
                             }
                             dbMenu.run(dataInput, Danger.class);
                         }

                     }
                     case 5 -> {
                         String menu = subMenu.run(dataInput,Discovery.class);
                         if(menu.equals("DB_MENU")) {
                             if(sessionManager.getSelected(Discovery.class)==null){
                                 Discovery discovery = new Discovery();
                                 discoveryService.rollDiscovery(discovery);
                                 sessionManager.add(Discovery.class,discovery);
                             }
                             dbMenu.run(dataInput, Discovery.class);
                         }
                     }
                     case 6 -> {
                         String menu = subMenu.run(dataInput, Dungeon.class);
                         if(menu.equals("DB_MENU")) {
                             if(sessionManager.getSelected(Dungeon.class)==null){
                                 Dungeon dungeon = new Dungeon();
                                 dungeonService.rollDungeon(dungeon);
                                 sessionManager.add(Dungeon.class,dungeon);
                             }
                             dbMenu.run(dataInput, Dungeon.class);
                         }
                     }
                     case 7 -> {
                         String menu = subMenu.run(dataInput,Follower.class);
                         if(menu.equals("DB_MENU")) {
                             if(sessionManager.getSelected(Follower.class)==null){
                                 Follower follower = new Follower();
                                 followerService.rollFollower(follower);
                                 sessionManager.add(Follower.class,follower);
                             }
                             dbMenu.run(dataInput,Follower.class);
                         }
                     }
                     case 8 -> {
                         String menu = subMenu.run(dataInput,NPC.class);
                         if(menu.equals("DB_MENU")) {
                             if(sessionManager.getSelected(NPC.class)==null){
                                 NPC npc = new NPC();
                                 npcService.rollFeatures(npc);
                                 sessionManager.add(NPC.class,npc);
                             }
                             dbMenu.run(dataInput, (NPC.class));
                         }
                     }
                     case 9 -> {
                         String menu = subMenu.run(dataInput, Quest.class);
                         if(menu.equals("DB_MENU")){
                             if(sessionManager.getSelected(Quest.class)==null){
                                 Quest quest = new Quest();
                                 questService.rollQuest(quest);
                                 sessionManager.add(Quest.class,quest);
                                 sessionManager.add(NPC.class, quest.getQuestGiver());
                                 sessionManager.add(Dungeon.class,quest.getDungeon());
                                 sessionManager.add(Biome.class,quest.getBiome());
                             }
                             dbMenu.run(dataInput, Quest.class);
                         }
                     }
                     case 10 -> {
                         String menu = subMenu.run(dataInput, Steading.class);
                         if(menu.equals("DB_MENU")){
                             if(sessionManager.getSelected(Steading.class)==null){
                                 Steading steading = new Steading();
                                 steadingService.rollSteading(steading);
                                 sessionManager.add(Steading.class,steading);
                             }
                             dbMenu.run(dataInput, Steading.class);
                         }
                     }
                     case 0 -> System.out.println("Come back soon!");
                     default -> System.out.println("Please, chose a valid option");
                 }

            } while (option != 0);
        } catch (NumberFormatException e){
            System.out.println("Please, chose a valid option. Error: ");
        }
    }

    @Override
    public void run(String... args) throws Exception {
//        run();
    }
}
