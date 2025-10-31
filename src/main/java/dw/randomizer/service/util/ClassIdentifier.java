package dw.randomizer.service.util;

import dw.randomizer.model.*;
import dw.randomizer.service.*;

import java.util.HashMap;
import java.util.Map;

public class ClassIdentifier {
    private static final Map<Class<?>, IGenericService<?>> map = new HashMap<>();

    static {
        map.put(Area.class,new AreaService());
        map.put(Biome.class,new BiomeService());
        map.put(Creature.class,new CreatureService());
        map.put(Danger.class,new DangerService());
        map.put(Discovery.class,new DiscoveryService());
        map.put(AreaDanger.class,new AreaDangerService());
        map.put(AreaDiscovery.class,new AreaDiscoveryService());
        map.put(Follower.class,new FollowerService());
        map.put(NPC.class,new NPCService());
        map.put(Steading.class,new SteadingService());
    }

    @SuppressWarnings("unchecked")
    public static <T extends IPWClass> IGenericService<T> getServiceFile(Class<T> c) {
        return (IGenericService<T>) map.get(c);
    }
}


