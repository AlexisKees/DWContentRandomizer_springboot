package dw.randomizer.service;

import dw.randomizer.data.DetailsArrays;
import dw.randomizer.data.DungeonArrays;
import dw.randomizer.model.AreaDanger;
import dw.randomizer.model.Creature;
import dw.randomizer.repository.AreaDangerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static dw.randomizer.model.util.Rolls.PickFrom;

public class AreaDangerService implements IAreaDangerCRUDService {
    @Autowired
    AreaDangerRepository areaDangerRepository;
    @Override
    public List<AreaDanger> listAreaDangers() {
        List<AreaDanger> areaDangerList = areaDangerRepository.findAll();
        return areaDangerList;
    }

    @Override
    public AreaDanger searchById(Integer id) {
        AreaDanger areaDanger = areaDangerRepository.findById(id).orElse(null);
        return areaDanger;
    }

    @Override
    public void saveAreaDanger(AreaDanger areaDanger) {
        areaDangerRepository.save(areaDanger);

    }

    @Override
    public void deleteAreaDanger(AreaDanger areaDanger) {
        areaDangerRepository.delete(areaDanger);

    }

    public static void rollAreaDanger(AreaDanger danger){
        danger.setCategory(PickFrom(DungeonArrays.DUNGEON_DANGER_CATEGORIES));

        switch (danger.getCategory()){
            case "Trap" ->  danger.setPromptTable(DungeonArrays.DUNGEON_DANGER_TRAP_PROMPTS);
            case "Creature" -> danger.setPromptTable(DungeonArrays.DUNGEON_DANGER_CREATURE_PROMPTS);
        }

        danger.setPrompt(PickFrom(danger.getPromptTable()));
        switch (danger.getPrompt()) {
            case "based on Element" -> {
                String element = CreatureService.rollElement();
                danger.setFinalResult(element+"trap");
                danger.setOneLiner(danger.getFinalResult());
            }
            case "based on Magic Type" -> {
                String magicType = CreatureService.rollMagicType();
                danger.setFinalResult("Magic "+magicType+" trap");
                danger.setOneLiner(danger.getFinalResult());
            }
            case "based on Oddity" -> {
                String oddity = CreatureService.rollOddity();
                danger.setFinalResult(oddity+" trap");
                danger.setOneLiner(danger.getFinalResult());
            }
            case "Creature leader (with minions)" -> {
                Creature c = new Creature();
                CreatureService.rollAttributes(c);
                CreatureService.setGroupSize(c,"solitary (1)");
                c.setDisposition(DetailsArrays.DISPOSITION[0]); //SET DISPOSITION TO "ATTACKING"
                danger.setFinalResult("CREATURE LEADER:\n"+c);
                danger.setOneLiner(c.getOneLiner()+" leader");
            }
            case "Creature lord (with minions)" ->{
                Creature c = new Creature();
                CreatureService.rollAttributes(c);
                c.setDisposition(DetailsArrays.DISPOSITION[0]); //SET DISPOSITION TO "ATTACKING"
                CreatureService.setGroupSize(c,"solitary (1)");
                danger.setFinalResult("CREATURE LORD:\n"+c);
                danger.setFinalResult(c.toString());
                danger.setOneLiner(c.getOneLiner()+" lord");
            }
            case "Creature" -> {
                Creature c = new Creature();
                CreatureService.rollAttributes(c);
                c.setDisposition(DetailsArrays.DISPOSITION[0]); //SET DISPOSITION TO "ATTACKING"
                danger.setFinalResult("CREATURE:\n"+c);
                danger.setOneLiner(c.getOneLiner());
            }
            default -> {
                if (danger.getCategory().equals("Trap")) danger.setFinalResult(danger.getPrompt()+" trap.");
                else danger.setFinalResult(danger.getPrompt());

                danger.setOneLiner(danger.getFinalResult());
            }
        }



    }
}
