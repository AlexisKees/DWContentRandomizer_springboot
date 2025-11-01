package dw.randomizer.service;

import dw.randomizer.data.DungeonArrays;
import dw.randomizer.model.AreaDiscovery;
import dw.randomizer.repository.AreaDiscoveryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import static dw.randomizer.model.util.Rolls.*;

import java.util.List;

import static dw.randomizer.model.util.Rolls.PickFrom;

public class AreaDiscoveryService implements IAreaDiscoveryCRUDService {

    @Autowired
    AreaDiscoveryRepository areaDiscoveryRepository;

    @Override
    public List<AreaDiscovery> listAreaDiscoveries() {
        List<AreaDiscovery> areaDiscoveries = areaDiscoveryRepository.findAll();
        return areaDiscoveries;
    }

    @Override
    public AreaDiscovery searchById(Integer id) {
        AreaDiscovery areaDiscovery = areaDiscoveryRepository.findById(id).orElse(null);
        return areaDiscovery;
    }

    @Override
    public void saveAreaDiscovery(AreaDiscovery areaDiscovery) {
        areaDiscoveryRepository.save(areaDiscovery);
    }

    @Override
    public void deleteAreaDiscovery(AreaDiscovery areaDiscovery) {
        areaDiscoveryRepository.delete(areaDiscovery);
    }

    public static void rollAreaDiscovery(AreaDiscovery discovery){
        discovery.setCategory(PickFrom(DungeonArrays.DUNGEON_DISCOVERY_CATEGORIES));

        switch (discovery.getCategory()){
            case "Feature" ->  discovery.setPromptTable(DungeonArrays.DUNGEON_DISCOVERY_FEATURE_PROMPTS);
            case "Find" -> discovery.setPromptTable(DungeonArrays.DUNGEON_DISCOVERY_FIND_PROMPTS);
        }

        discovery.setPrompt(PickFrom(discovery.getPromptTable()));
        int roll;
        switch (discovery.getPrompt()) {

            case "roll again, add magic type" -> {
                roll = CustomRoll(23); //hardcodes number to remove elements that require rerolling
                String magicType = CreatureService.rollMagicType();
                discovery.setFinalResult(discovery.getPromptTable()[roll]+". "+magicType);
            }
            case "roll feature, add magic type" -> {
                String feature = PickFrom(DungeonArrays.DUNGEON_DISCOVERY_FEATURE_PROMPTS);
                String magicType = CreatureService.rollMagicType();
                discovery.setFinalResult(feature+". "+magicType);
            }
            case "roll twice", "ROLL TWICE" -> discovery.setFinalResult(rollTwice(DungeonArrays.DUNGEON_DISCOVERY_FIND_PROMPTS,23)); //hardcodes number to remove elements that require rerolling
            default -> discovery.setFinalResult(discovery.getPrompt());
        }

        discovery.setOneLiner(discovery.getFinalResult());

    }
}
