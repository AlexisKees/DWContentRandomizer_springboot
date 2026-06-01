package dw.randomizer.service;

import dw.randomizer.data.QuestArrays;
import dw.randomizer.model.*;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.QuestRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class QuestService implements IGenericService<Quest>, IGenericCRUDService<Quest> {

    @Autowired
    private SessionManager sessionManager;

    @Autowired
    private ViewAll viewAll;

    @Autowired
    private DungeonService dungeonService;
    @Autowired
    private BiomeService biomeService;
    @Autowired
    private NPCService npcService;

    @Autowired
    QuestRepository questRepository;

    @Override
    public List<Quest> list() {
        return questRepository.findAll();
    }

    @Override
    public Quest searchById(Integer id) {
        return questRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Quest quest) {
        questRepository.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        questRepository.delete(quest);
    }



    public void rollQuest(Quest quest){
        quest.setTask(PickFrom(QuestArrays.TASK));
        quest.setRelevance(PickFrom(QuestArrays.RELEVANCE));
        quest.setReward(PickFrom(QuestArrays.REWARD));

        quest.setQuestGiver(new NPC());
        quest.setDungeon(new Dungeon());
        quest.setBiome(new Biome());
        dungeonService.rollDungeon(quest.getDungeon());
        npcService.rollFeatures(quest.getQuestGiver());
        biomeService.rollBiome(quest.getBiome());
        quest.setOneLiner(String.format("%s's %s quest",quest.getQuestGiver().getName(),quest.getTask()));

        quest.setBrief(String.format("""
                \nQUEST
                Task: %s
                Relevance: %s
                Reward: %s
                Tasked by: %s
                To be carried out at: %s, in some %s
                """, quest.getTask(),quest.getRelevance(),quest.getReward(),quest.getQuestGiver().getOneLiner(),quest.getDungeon().getName(), quest.getBiome().getOneLiner()));
    }



    public String showOptions(Scanner dataInput, Class<Quest> parameterClass) {
        Quest quest;
        if(sessionManager.getSelected(parameterClass)==null) {
            quest = new Quest();
        } else {
            quest = sessionManager.getSelected(parameterClass);
        }

        var option = 0;
        String menu = "MAIN_MENU";
        System.out.println("WELCOME TO THE QUEST GENERATOR\n");

        do {
            try {
                System.out.print("""
                        \nPlease select an option:
                        1) Create new random quest
                        2) Quest giver details
                        3) Quest location details
                        4) See current quest
                        5) See previously generated quests
                        6) Print quest
                        7) MANAGE DB
                        0) Main menu
                        
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        rollQuest(quest);
                        sessionManager.add(Quest.class,quest);
                        sessionManager.add(NPC.class, quest.getQuestGiver());
                        sessionManager.add(Dungeon.class,quest.getDungeon());
                        sessionManager.add(Biome.class,quest.getBiome());
                        printWithFlair(quest.getBrief());
                    }
                    case 2 -> {
                        if(quest.getTask()==null){
                            rollQuest(quest);
                            sessionManager.add(Quest.class,quest);
                            sessionManager.add(NPC.class, quest.getQuestGiver());
                            sessionManager.add(Dungeon.class,quest.getDungeon());
                            sessionManager.add(Biome.class,quest.getBiome());
                        }
                        printWithFlair("QUEST GIVER:\n\n"+quest.getQuestGiver());
                    }
                    case 3 -> {
                        if(quest.getTask()==null){
                            rollQuest(quest);
                            sessionManager.add(Quest.class,quest);
                            sessionManager.add(NPC.class, quest.getQuestGiver());
                            sessionManager.add(Dungeon.class,quest.getDungeon());
                            sessionManager.add(Biome.class,quest.getBiome());
                        }
                        printWithFlair("QUEST LOCATION - BIOME:\n\n"+quest.getBiome()+"\n\n"+"QUEST LOCATION - DUNGEON:\n\n"+quest.getDungeon());
                    }
                    case 4 -> {
                        if(quest.getTask()==null){
                            rollQuest(quest);
                            sessionManager.add(Quest.class,quest);
                            sessionManager.add(NPC.class, quest.getQuestGiver());
                            sessionManager.add(Dungeon.class,quest.getDungeon());
                            sessionManager.add(Biome.class,quest.getBiome());
                        }
                        printWithFlair(quest);
                    }
                    case 5-> quest = viewAll.run(dataInput,Quest.class);
                    case 6 -> {
                        if(quest.getTask() == null) {
                            rollQuest(quest);
                            sessionManager.add(Quest.class,quest);
                            sessionManager.add(NPC.class, quest.getQuestGiver());
                            sessionManager.add(Dungeon.class,quest.getDungeon());
                            sessionManager.add(Biome.class,quest.getBiome());
                        }
                        GenericFunctions.exportPW(quest);
                    }
                    case 7 -> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");
                    default -> System.out.print("\nInvalid number!\n\n");
                }
            } catch (Exception e) {
                System.out.println("\nPlease choose a valid option.\n");
            }
        }
        while (option != 0);
        return menu;
    }
}
