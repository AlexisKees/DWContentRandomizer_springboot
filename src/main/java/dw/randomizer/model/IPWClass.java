package dw.randomizer.model;

public interface IPWClass extends Cloneable {

    String oneLiner ="";

    @Override
    String toString();

    String getOneLiner();

    void setOneLiner(String oneLiner);

    IPWClass clone();

    Integer getId();

    void setId(Integer id);
}
