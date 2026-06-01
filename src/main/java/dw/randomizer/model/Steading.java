package dw.randomizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Steading implements IPWClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name = "";
    private String size = "";
    private String tags = "";
    @Transient private String[] featuresTable;
    private String feature = "";
    @Transient private String[] problemsTable;
    private String problem = "";
    private String raceOfBuilders = "";
    private String alignment = "";
    private String dangerLevel = "";
    private String oneLiner;

    public String getOneLiner(){
        return oneLiner;
    }

    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Steading clone() {
        try {
            return (Steading) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("""
                %s, %s %s
                
                Alignment: %s
                Danger level: %s
                Tags: %s
                Feature: %s
                Problem: %s""", this.name.toUpperCase(), this.raceOfBuilders.toUpperCase(), this.size.toUpperCase(), this.alignment, this.dangerLevel, this.getTags(), this.feature, this.problem);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getRaceOfBuilders() {
        return raceOfBuilders;
    }

    public void setRaceOfBuilders(String raceOfBuilders) {
        this.raceOfBuilders = raceOfBuilders;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(String dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public String[] getFeaturesTable() {
        return featuresTable;
    }

    public void setFeaturesTable(String[] featuresTable) {
        this.featuresTable = featuresTable;
    }

    public String[] getProblemsTable() {
        return problemsTable;
    }

    public void setProblemsTable(String[] problemsTable) {
        this.problemsTable = problemsTable;
    }
}
