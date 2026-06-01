package dw.randomizer.service;

import dw.randomizer.data.DungeonArrays;
import dw.randomizer.model.Dungeon;
import dw.randomizer.model.Steading;
import dw.randomizer.model.util.Rolls;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.DungeonRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Slf4j
@Service
public class DungeonService implements IGenericService<Dungeon>, IGenericCRUDService<Dungeon> {
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private ViewAll viewAll;
    @Autowired
    private DungeonAreaService dungeonAreaService;

    @Autowired
    private CreatureService creatureService;

    @Autowired
    private SteadingService steadingService;

    @Autowired
    private DungeonRepository dungeonRepository;

    @Override
    public List<Dungeon> list() {
        List<Dungeon> dungeonList = dungeonRepository.findAll();
        return dungeonList;
    }

    @Override
    public Dungeon searchById(Integer id) {
        Dungeon dungeon = dungeonRepository.findById(id).orElse(null);
        return dungeon;
    }

    @Override
    public void save(Dungeon dungeon) {
        dungeonRepository.save(dungeon);
    }

    @Override
    public void delete(Dungeon dungeon) {
        dungeonRepository.delete(dungeon);
    }




    public void rollDungeon(Dungeon dungeon){

        //set name template and name
        dungeon.setNameTemplate(PickFrom(DungeonArrays.DUNGEON_NAME_TEMPLATES));
        String adjective = PickFrom(DungeonArrays.DUNGEON_ADJECTIVES);
        String noun = PickFrom(DungeonArrays.DUNGEON_NOUNS);
        String place = PickFrom(DungeonArrays.DUNGEON_PLACES);

        dungeon.setName(dungeon.getNameTemplate().replace("[PLACE]",place).replace("[NOUN]",noun).replace("[ADJECTIVE]",adjective));

        //set overview: size, themes, form, situation, builder, function, cause of ruin
        dungeon.setSize(PickFrom(DungeonArrays.DUNGEON_SIZE));
        //based on size, set the amount of rooms and themes
        switch (dungeon.getSize()){
            case "medium" -> {
                dungeon.setRooms((int)(Math.random()*8)+1+7);
                dungeon.setThemesAmount(3);
            }
            case "large" ->{
                dungeon.setRooms((int)(Math.random()*10)+1+15);
                dungeon.setThemesAmount(4);
            }
            case "huge" -> {
                dungeon.setRooms((int)(Math.random()*12)+1+25);
                dungeon.setThemesAmount(5);
            }
            case "megadungeon" ->{
                dungeon.setRooms((int)(Math.random()*20)+1+50);
                dungeon.setThemesAmount(10);
            }
            default ->{
                dungeon.setRooms((int)(Math.random()*6)+1+1);
                dungeon.setThemesAmount(2);
            }
        }

        dungeon.initializeThemes(dungeon.getThemesAmount());
        int i;
        for (i = 1; i<=dungeon.getThemesAmount(); i++ ){
          String themeCategory = PickFrom(DungeonArrays.DUNGEON_THEME_CATEGORIES);
          String theme;
          switch (themeCategory){
              case "HOPEFUL" -> theme =  PickFrom(DungeonArrays.HOPEFUL_PROMPTS);
              case "MYSTERIOUS" -> theme =  PickFrom(DungeonArrays.MYSTERIOUS_PROMPTS);
              case "GRIM" -> theme =  PickFrom(DungeonArrays.GRIM_PROMPTS);
              default -> theme = PickFrom(DungeonArrays.GONZO_PROMPTS);
          }
          if (Objects.equals(theme,"element")){
              theme = creatureService.rollElement();
          }
          if (Objects.equals(theme, "magic type")){
              theme = creatureService.rollMagicType();
          }
          if(Objects.equals(theme, "roll 1d10 twice, combine")){
              theme = (DungeonArrays.GONZO_PROMPTS[Rolls.Roll1d10()]+" and "+DungeonArrays.GONZO_PROMPTS[Rolls.Roll1d10()]);
          }
          dungeon.addTheme(theme);
        }

        dungeon.setForm(PickFrom(DungeonArrays.DUNGEON_FORM));
        switch (dungeon.getForm()){
         case "ruins of (roll again)" -> dungeon.setForm("Ruins of a "+DungeonArrays.DUNGEON_FORM[Rolls.CustomRoll(17)]);
         case "roll again, add oddity" ->dungeon.setForm(DungeonArrays.DUNGEON_FORM[Rolls.CustomRoll(17)]+" + "+ creatureService.rollOddity());
         case "ruins of steading" ->{
            Steading s = new Steading();
            steadingService.rollSteading(s);
            dungeon.setForm("Ruins of "+s.getOneLiner());
         }
            default -> dungeon.setForm(dungeon.getForm());
        }

        dungeon.setSituation(PickFrom(DungeonArrays.DUNGEON_SITUATION));
        dungeon.setBuilder(PickFrom(DungeonArrays.DUNGEON_BUILDER));
        dungeon.setPurpose(PickFrom(DungeonArrays.DUNGEON_FUNCTION));
        if (Objects.equals(dungeon.getPurpose(),"roll twice")||Objects.equals(dungeon.getPurpose(),"ROLL TWICE")) dungeon.setPurpose(Rolls.rollTwice(DungeonArrays.DUNGEON_FUNCTION));

        dungeon.setCauseOfRuin(PickFrom(DungeonArrays.DUNGEON_CAUSE_OF_RUIN));
        dungeon.setAccessibility(PickFrom(DungeonArrays.DUNGEON_ACCESSIBILITY));

        dungeon.setExits(PickFrom(DungeonArrays.AREA_EXITS_NUMBER));

        dungeon.setOneLiner(dungeon.getName());

    }

    public String showOptions(Scanner dataInput, Class<Dungeon> parameterClass) {
        Dungeon dungeon;
        if(sessionManager.getSelected(parameterClass)==null) {
            dungeon = new Dungeon();
        } else {
            dungeon = sessionManager.getSelected(parameterClass);
        }

        int option = 0;
        System.out.println("WELCOME TO THE DUNGEON GENERATOR\n");
        String menu = "MAIN_MENU";
        do {
            try {
                System.out.print("""
                        Please select an option:
                        1) Create new random dungeon
                        2) Add areas
                        3) View generated dungeons list
                        4) View current dungeon
                        5) Export dungeon
                        6) MANAGE DB
                        0) Main menu
                      
                        \tOption:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        rollDungeon(dungeon);
                        sessionManager.add(Dungeon.class,dungeon.clone());
                        printWithFlair(dungeon);
                    }
                    case 2 -> dungeonAreaService.showOptions(dataInput);
                    case 3 -> dungeon = viewAll.run(dataInput,Dungeon.class);
                    case 4 -> {
                        if (dungeon.getName()==null){
                            System.out.println("\nGenerating dungeon...\n");
                            dungeon = new Dungeon();
                            sessionManager.add(Dungeon.class,dungeon.clone());
                        }
                        printWithFlair(dungeon);
                    }
                    case 5 -> {
                        if (dungeon.getName() == null) {
                            dungeon = new Dungeon();
                            sessionManager.add(Dungeon.class,dungeon.clone());
                        }
                        GenericFunctions.exportPW(dungeon);
                    }
                    case 6 -> {
                        System.out.println("ACCESSING DATABASE...");
                        return "DB_MENU";
                    }
                    case 0 -> System.out.println("Going back to main menu");
                    default -> System.out.print("\nInvalid number!\n\n");
                }
            } catch (Exception e) {
                log.error("Please choose a valid option. Error: {}", e.getMessage(), e);
            }
        } while (option != 0);
        return menu;
    }
}
