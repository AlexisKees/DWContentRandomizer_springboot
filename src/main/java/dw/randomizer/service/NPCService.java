package dw.randomizer.service;

import dw.randomizer.data.CreatureArrays;
import dw.randomizer.data.DetailsArrays;
import dw.randomizer.data.NPCArrays;
import dw.randomizer.data.NPCNamesArrays;
import dw.randomizer.model.NPC;
import dw.randomizer.model.util.Rolls;
import dw.randomizer.presentation.ViewAll;
import dw.randomizer.repository.NPCRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import dw.randomizer.service.util.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;
import static dw.randomizer.service.GenericFunctions.printWithFlair;

@Service
public class NPCService implements IGenericService<NPC>, IGenericCRUDService<NPC> {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private ViewAll viewAll;

    @Autowired
    NPCRepository npcRepository;

    @Override
    public List<NPC> list() {
        return npcRepository.findAll();
    }

    @Override
    public NPC searchById(Integer id) {
        return npcRepository.findById(id).orElse(null);
    }

    @Override
    public void save(NPC npc) {
        npcRepository.save(npc);
    }

    @Override
    public void delete(NPC npc) {
        npcRepository.delete(npc);
    }


    public void rollFeatures(NPC npc){
        //set race rarity, races array and race
        String rarity = PickFrom(CreatureArrays.SUBCATEGORIES_HUMANOID);
        switch (rarity) {
            case "Uncommon" -> npc.setRaceTable(CreatureArrays.PROMPTS_HUMANOID_UNCOMMON);
            case "Rare" -> npc.setRaceTable(CreatureArrays.PROMPTS_HUMANOID_RARE);
            default -> npc.setRaceTable(CreatureArrays.PROMPTS_HUMANOID_COMMON);
        }

        npc.setRace(PickFrom(npc.getRaceTable()));
        // set gender, ethnics and name
        npc.setGender(PickFrom(NPCNamesArrays.GENDER));
        npc.setEthnics(PickFrom(NPCNamesArrays.ETHNICS));
        switch (npc.getEthnics()){
            case "Yoruba" -> npc.setNamesTable(NPCNamesArrays.NAMES_YORUBA_BASED);
            case "Finnish" -> npc.setNamesTable(NPCNamesArrays.NAMES_FINNISH_BASED);
            case "Indonesian" -> npc.setNamesTable(NPCNamesArrays.NAMES_INDONESIAN_BASED);
            default -> npc.setNamesTable(NPCNamesArrays.NAMES_HUNGARIAN_BASED);
        }
        //Male names use the first half of each 50 elements array, while female names use the second half
        switch (npc.getGender()){
            case "Male" ->{
                int roll = (int)(Math.random() * 24);
                npc.setName(npc.getNamesTable()[roll]);
            }
            case "Female" -> {
                int roll = (int)(Math.random() * 24 + 25);
                npc.setName(npc.getNamesTable()[roll]);
            }
            default -> npc.setName(PickFrom(npc.getNamesTable()));

        }

        //Set age using DetailArrays
        int ageRoll = (int)(Math.random()*7+3);
        npc.setAge(DetailsArrays.AGE[ageRoll]);

        npc.setCategory(PickFrom(NPCArrays.CATEGORY));
        switch (npc.getCategory()){
            case "Outsider" -> npc.setJobList(NPCArrays.OUTSIDER);
            case "Criminal" -> npc.setJobList(NPCArrays.CRIMINAL);
            case "Tradesperson" -> npc.setJobList(NPCArrays.TRADESPERSON);
            case "Merchant" -> npc.setJobList(NPCArrays.MERCHANT);
            case "Specialist" -> npc.setJobList(NPCArrays.SPECIALIST);
            case "Religious" -> npc.setJobList(NPCArrays.RELIGIOUS);
            case "Security" -> npc.setJobList(NPCArrays.SECURITY);
            case "Authority" -> npc.setJobList(NPCArrays.AUTHORITY);
            default ->  npc.setJobList(NPCArrays.COMMONER);
        }

        npc.setJob(PickFrom(npc.getJobList()));
        if (npc.getCategory().equals("Merchant")) npc.setJob(npc.getJob()+" merchant");

        npc.setAppearance(PickFrom(NPCArrays.APPEARANCE));
        npc.setPersonality(PickFrom(NPCArrays.PERSONALITY));
        npc.setQuirk(PickFrom(NPCArrays.QUIRK));

        if (Objects.equals(npc.getAppearance(), "roll twice")||Objects.equals(npc.getAppearance(), "ROLL TWICE")){
            npc.setAppearance(Rolls.rollTwice(NPCArrays.APPEARANCE));
        }

        if (Objects.equals(npc.getPersonality(), "roll twice")||Objects.equals(npc.getPersonality(), "ROLL TWICE")){
            npc.setPersonality(Rolls.rollTwice(NPCArrays.PERSONALITY));
        }

        if (Objects.equals(npc.getQuirk(), "roll twice")||Objects.equals(npc.getQuirk(), "ROLL TWICE")){
            npc.setQuirk(Rolls.rollTwice(NPCArrays.QUIRK));
        }

        npc.setOneLiner(String.format("%s, the %s %s %s", npc.getName(), npc.getQuirk(),npc.getRace(), npc.getJob()));
    }

    @Override
    public String showOptions(Scanner dataInput, Class<NPC> parameterClass) {
        NPC npc;
        if(sessionManager.getSelected(parameterClass)==null) {
            npc = new NPC();
        } else {
            npc = sessionManager.getSelected(parameterClass);
        }

        var option = 0;
        System.out.println("\nWELCOME TO THE NPC GENERATOR\n");
        String menu = "MAIN_MENU";
        do {
            try {
                System.out.print("""
                        Please select an option:
                        1) Create new random NPC
                        2) View current
                        3) View list of generated NPCs
                        4) Export current
                        5) MANAGE DB
                        0) Main menu
                        
                        Option:\s""");
                option = Integer.parseInt(dataInput.nextLine());
                System.out.println();

                switch (option) {
                    case 1 -> {
                        rollFeatures(npc);
                        printWithFlair(npc);
                        sessionManager.add(NPC.class,npc.clone());
                    }
                    case 2 -> {
                        if (npc.getRace()==null) {
                            rollFeatures(npc);
                            sessionManager.add(NPC.class,npc.clone());
                        }
                        printWithFlair(npc);
                    }
                    case 3 -> npc = viewAll.run(dataInput,NPC.class);
                    case 4 -> GenericFunctions.exportPW(npc);
                    case 5 -> {
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
