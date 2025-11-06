package dw.randomizer.service.util;

import dw.randomizer.model.IPWClass;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SessionManager {

    private final Map<Class<? extends IPWClass>, List<IPWClass>> sessionLists = new HashMap<>();
    private final Map<Class<? extends IPWClass>, IPWClass> selectedObjects = new HashMap<>();


    @SuppressWarnings("unchecked")
    public <T extends IPWClass> List<T> getList(Class<? extends T> type){
        return (List<T>) sessionLists.computeIfAbsent(type, k -> new ArrayList<>());
    }

    public <T extends IPWClass> void add(Class<T> type, T obj){
        getList(type).add(obj);
        select(obj.getClass(), sessionLists.get(obj.getClass()).size()-1);
    }

    public <T extends IPWClass> void select(Class<T> type, int index){
        List<T> list = getList(type);
        if(index >=0 && index < list.size()){
            selectedObjects.put(type, list.get(index));
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends IPWClass> T getSelected(Class<T> type){
        return (T) selectedObjects.get(type);
    }

    public <T extends IPWClass> void removeSelected(Class<T> parameterClass){
        selectedObjects.remove(parameterClass);
    }
}
