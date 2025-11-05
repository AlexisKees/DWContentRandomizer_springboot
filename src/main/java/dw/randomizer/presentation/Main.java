package dw.randomizer.presentation;

import dw.randomizer.model.*;
import dw.randomizer.service.*;
import dw.randomizer.service.crud.*;
import dw.randomizer.service.util.ClassIdentifier;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    @Autowired
    private DBMenu dbMenu;
    @Autowired
    private IAreaDangerCRUDService areaDangerService;
    @Autowired
    private IAreaDiscoveryCRUDService areaDiscoveryService;
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
        List<Quest> questList = sessionManager.getList(Quest.class);
        List<NPC> npcList = sessionManager.getList(NPC.class);
        List<Follower> followerList = sessionManager.getList(Follower.class);
        List<Steading> steadingList = sessionManager.getList(Steading.class);
        List<Dungeon> dungeonList = sessionManager.getList(Dungeon.class);
        List<Area> areaList = sessionManager.getList(Area.class);
        List<Biome> biomeList = sessionManager.getList(Biome.class);
        List<Discovery> discoveryList = sessionManager.getList(Discovery.class);
        List<Danger> dangerList = sessionManager.getList(Danger.class);
        List<Creature> creatureList = sessionManager.getList(Creature.class);



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
                         String menu = subMenu.run(dataInput, area);
                         if(menu.equals("DB_MENU")) {
                             if (area.getAreaType()==null){
                                 areaService.rollArea(area);
                                 sessionManager.add(Area.class,area);
                             }
                             dbMenu.run(dataInput, sessionManager.getSelected(Area.class));
                         }
                     }
                     case 2 -> {
                         Biome biome = new Biome();
                         String menu = subMenu.run(dataInput,biome);
                         if(menu.equals("DB_MENU")){
                             if (biome.getBiome()==null){
                                 biomeService.rollBiome(biome);
                                 sessionManager.add(Biome.class,biome);
                             }
                             dbMenu.run(dataInput, sessionManager.getSelected(Biome.class));
                         }
                     }
                     case 3 -> {
                         Creature creature = new Creature();
                         String menu = subMenu.run(dataInput, creature);
                         if(menu.equals("DB_MENU")){
                             if(creature.getCategory()==null){
                                 creatureService.rollAttributes(creature);
                                 sessionManager.add(Creature.class,creature);
                             }
                             dbMenu.run(dataInput, creature);
                         }
                     }
                     case 4 -> {
                         Danger danger = new Danger();
                         String menu = subMenu.run(dataInput, danger);
                         if(menu.equals("DB_MENU")) {
                             if(danger.getCategory()==null){
                                 dangerService.rollDanger(danger);
                                 sessionManager.add(Danger.class,danger);
                             }
                             dbMenu.run(dataInput, danger);}

                     }
                     case 5 -> {
                         Discovery discovery = new Discovery();
                         String menu = subMenu.run(dataInput,discovery);
                         if(menu.equals("DB_MENU")) {
                             if(discovery.getCategory()==null){
                                 discoveryService.rollDiscovery(discovery);
                                 sessionManager.add(Discovery.class,discovery);
                             }
                             dbMenu.run(dataInput, discovery);
                         }
                     }
                     case 6 -> {
                         Dungeon dungeon = new Dungeon();
                         String menu = subMenu.run(dataInput, dungeon, dungeonList, areaList);
                         if(menu.equals("DB_MENU")) {
                             if(dungeon.getName()==null){
                                 dungeonService.rollDungeon(dungeon);
                                 sessionManager.add(Dungeon.class,dungeon);
                             }
                             dbMenu.run(dataInput, dungeon);
                         }
                     }
                     case 7 -> {
                         Follower follower = new Follower();
                         String menu = subMenu.run(dataInput,follower);
                         if(menu.equals("DB_MENU")) {
                             if(follower.getRace()==null){
                                 followerService.rollFollower(follower);
                                 sessionManager.add(Follower.class,follower);
                             }
                             dbMenu.run(dataInput, follower);
                         }
                     }
                     case 8 -> {
                         NPC npc = new NPC();
                         String menu = subMenu.run(dataInput,npc);
                         if(menu.equals("DB_MENU")) {
                             if(npc.getCategory()==null){
                                 npcService.rollFeatures(npc);
                                 sessionManager.add(NPC.class,npc);
                             }
                             dbMenu.run(dataInput, npc);
                         }
                     }
                     case 9 -> {
                         Quest quest = new Quest();
                         String menu = subMenu.run(dataInput, quest, questList, npcList, dungeonList, biomeList);
                         if(menu.equals("DB_MENU")){
                             if(quest.getTask()==null){
                                 questService.rollQuest(quest);
                                 sessionManager.add(Quest.class,quest);
                             }
                             dbMenu.run(dataInput, quest);
                         }
                     }
                     case 10 -> {
                         Steading steading = new Steading();
                         String menu = subMenu.run(dataInput, steading);
                         if(menu.equals("DB_MENU")){
                             if(steading.getSize()==null){
                                 steadingService.rollSteading(steading);
                                 sessionManager.add(Steading.class,steading);
                             }
                             dbMenu.run(dataInput, steading);
                         }
                     }
                     case 11 -> System.out.println("Come back soon!");
                     default -> System.out.println("Please, chose a valid option");
                 }

            } while (option != 11);
        } catch (NumberFormatException e){
            System.out.println("Please, chose a valid option. Error: ");
        }
    }
}
