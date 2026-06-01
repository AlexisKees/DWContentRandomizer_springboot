package dw.randomizer.service;

import dw.randomizer.data.DungeonArrays;
import dw.randomizer.model.AreaDiscovery;
import dw.randomizer.repository.AreaDiscoveryRepository;
import dw.randomizer.service.crud.IGenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dw.randomizer.model.util.Rolls.*;

import java.util.List;
import java.util.Scanner;

import static dw.randomizer.model.util.Rolls.PickFrom;

@Service
public class AreaDiscoveryService implements IGenericService<AreaDiscovery>, IGenericCRUDService<AreaDiscovery> {

    @Autowired
    private CreatureService creatureService;

    @Autowired
    private AreaDiscoveryRepository areaDiscoveryRepository;

    @Override
    public List<AreaDiscovery> list() {
        return areaDiscoveryRepository.findAll();
    }

    @Override
    public AreaDiscovery searchById(Integer id) {
        return areaDiscoveryRepository.findById(id).orElse(null);
    }

    @Override
    public void save(AreaDiscovery areaDiscovery) {
        areaDiscoveryRepository.save(areaDiscovery);
    }

    @Override
    public void delete(AreaDiscovery areaDiscovery) {
        areaDiscoveryRepository.delete(areaDiscovery);
    }



    public void rollAreaDiscovery(AreaDiscovery discovery){
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
                String magicType = creatureService.rollMagicType();
                discovery.setFinalResult(discovery.getPromptTable()[roll]+". "+magicType);
            }
            case "roll feature, add magic type" -> {
                String feature = PickFrom(DungeonArrays.DUNGEON_DISCOVERY_FEATURE_PROMPTS);
                String magicType = creatureService.rollMagicType();
                discovery.setFinalResult(feature+". "+magicType);
            }
            case "roll twice", "ROLL TWICE" -> discovery.setFinalResult(rollTwice(DungeonArrays.DUNGEON_DISCOVERY_FIND_PROMPTS,23)); //hardcodes number to remove elements that require rerolling
            default -> discovery.setFinalResult(discovery.getPrompt());
        }

        discovery.setOneLiner(discovery.getFinalResult());

    }

    @Override
    public String showOptions(Scanner dataInput, Class<AreaDiscovery> parameterClass) {
        return "";
    }
}
