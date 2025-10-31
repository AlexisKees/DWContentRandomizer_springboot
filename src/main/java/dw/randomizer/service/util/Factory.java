package dw.randomizer.service.util;

import dw.randomizer.model.IPWClass;

@FunctionalInterface
public interface Factory <T extends IPWClass> {
    T create();
}
