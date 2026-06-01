package dw.randomizer.service;

import dw.randomizer.model.IPWClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenericFunctions {

    public static <T extends IPWClass> void exportPW(T pw) throws IOException {
        try {
            Class<?> c = pw.getClass();
            String prefix = c.getSimpleName()+ "_";
            int number = 1;
            String fileName = String.format(prefix + "%04d.txt", number);
            File file = new File(fileName);

            while (file.exists()) {
                number++;
                fileName = String.format(prefix + "%04d.txt", number);
                file = new File(fileName);
            }

            PrintWriter salida = new PrintWriter(new FileWriter(file, true));
            salida.println(pw);
            salida.close();

            System.out.println("""
                    ***********************
                    *  Check your files!  *
                    ***********************
                    """);
        } catch (Exception e){
            System.out.println("Couldn't print file: "+e.getMessage());
        }
    }

    public static <T extends IPWClass> void printWithFlair(T o){
        System.out.println("_________________________________________");
        System.out.println(o);
        System.out.println("_________________________________________\n");
    }

    public static void printWithFlair(String o){
        System.out.println("_________________________________________");
        System.out.println(o);
        System.out.println("_________________________________________\n");
    }
}
