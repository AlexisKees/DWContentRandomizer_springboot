package dw.randomizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dungeon implements IPWClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Transient private String nameTemplate;
    private String name;
    private String size;
    private int rooms;
    private int themesAmount;
    @Transient private String[] themes;
    private String themePrompt;
    private String form;
    private String exits;
    private String situation;
    private String accessibility;
    private String builder;
    private String purpose;
    private String causeOfRuin;

    @OneToOne(mappedBy = "dungeon", optional = true)
    private Quest quest;

    @OneToMany(mappedBy = "dungeon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Area> areas = new ArrayList<>();
    private String oneLiner;

    @Override
    public String getOneLiner(){
        return oneLiner;
    }
    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Dungeon clone() {
        try {
            return (Dungeon) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }



    public void addTheme(String theme){
        int i;
        for (i=0;i<this.themes.length;i++){
            if (this.themes[i]==null){
                this.themes[i]=theme;
                break;
            }
        }
    }

    public void initializeThemes(int i){
        this.themes = new String[i];
    }

    public void addArea(Area area){
        if(this.areas.size()<this.rooms){
            this.areas.add(area);
            System.out.println("AREA ADDED SUCCESFULLY!");
        } else System.out.println("Cannot add area: maximum amount of areas reached!");
    }


    public String getNameTemplate() {
        return nameTemplate;
    }

    public void setNameTemplate(String nameTemplate) {
        this.nameTemplate = nameTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

        public String getThemePrompt() {
        return themePrompt;
    }

    public void setThemePrompt(String themePrompt) {
        this.themePrompt = themePrompt;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getBuilder() {
        return builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getCauseOfRuin() {
        return causeOfRuin;
    }

    public void setCauseOfRuin(String causeOfRuin) {
        this.causeOfRuin = causeOfRuin;
    }


    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getThemesAmount() {
        return themesAmount;
    }

    public void setThemesAmount(int themesAmount) {
        this.themesAmount = themesAmount;
    }

        public String getExits() {
        return exits;
    }

    public void setExits(String exits) {
        this.exits = exits;
    }

    public String getThemes() {
        int i;
        String themesSTring = "";
        for (i=0;i<this.themes.length;i++){
            if (i==0){
                themesSTring+=themes[i];
            } else if (i<this.themes.length-1 && this.themes[i]!=null){
                themesSTring+= ", "+themes[i];
            } else if (i==this.themes.length-1 && this.themes[i]!=null){
                themesSTring+= " and "+themes[i];
            }
        }
        return themesSTring;
    }

    public void setThemes(String[] themes) {
        this.themes = themes;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public String toString(){
        String str = String.format("""
             *** %s ***
             
             Size: %s
             Rooms: %s
             Theme amount: %d
             Themes: %s
             Form: %s
             Exits: %s
             Situation: %s
             Accessibility: %s
             Builder: %s
             Function: %s
             Cause of ruin: %s""",this.name.toUpperCase(), this.size, this.rooms,this.themesAmount, this.getThemes(), this.form,this.exits, this.situation, this.accessibility,this.builder, this.purpose, this.causeOfRuin);
        if (!this.getAreas().isEmpty()){
            str+=String.format("\n\nDUNGEON AREAS:");
            int areaNumber =1 ;
            for(Area a: this.getAreas()){
                str+=String.format("\nAREA %d:\n%s",areaNumber,a);
                areaNumber++;
            }
        }
     return str;
    }
}
