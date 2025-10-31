package dw.randomizer.service;

import dw.randomizer.model.*;

import java.util.List;
import java.util.Scanner;

public interface IGenericService<T extends IPWClass>  {
    void showOptions(Scanner dataInput,T object, List<T> list);
}
