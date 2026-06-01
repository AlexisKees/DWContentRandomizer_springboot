package dw.randomizer.service.util;

import dw.randomizer.model.*;
import dw.randomizer.service.*;
import dw.randomizer.service.crud.IGenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClassIdentifier {
    private final Map<Class<?>, IGenericService<?>> serviceMap = new HashMap<>();


    @Autowired
    public ClassIdentifier(
            AreaService areaService,
            BiomeService biomeService,
            CreatureService creatureService,
            DangerService dangerService,
            DiscoveryService discoveryService,
            AreaDangerService areaDangerService,
            AreaDiscoveryService areaDiscoveryService,
            FollowerService followerService,
            NPCService npcService,
            SteadingService steadingService,
            DungeonService dungeonService,
            QuestService questService
    ) {
        serviceMap.put(Area.class, areaService);
        serviceMap.put(AreaDanger.class, areaDangerService);
        serviceMap.put(AreaDiscovery.class, areaDiscoveryService);
        serviceMap.put(Biome.class, biomeService);
        serviceMap.put(Creature.class, creatureService);
        serviceMap.put(Danger.class, dangerService);
        serviceMap.put(Discovery.class, discoveryService);
        serviceMap.put(Dungeon.class,dungeonService);
        serviceMap.put(Follower.class, followerService);
        serviceMap.put(NPC.class, npcService);
        serviceMap.put(Quest.class,questService);
        serviceMap.put(Steading.class, steadingService);
    }


    @SuppressWarnings("unchecked")
    public <T extends IPWClass> IGenericService<T> getServiceFile(Class<T> c) {
        return (IGenericService<T>) serviceMap.get(c);
    }
    @SuppressWarnings("unchecked")
    public <T extends IPWClass> IGenericCRUDService<T> getCRUDFile(Class<T> c){
        return (IGenericCRUDService<T>) serviceMap.get(c);
    }
}


