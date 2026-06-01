package dw.randomizer.service;

import dw.randomizer.model.*;

import java.util.Scanner;

public interface IGenericService<T extends IPWClass>  {
    String showOptions(Scanner dataInput,Class<T> parameterClass);
}
