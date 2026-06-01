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
public class Area implements IPWClass {

    @Id
    @Column(name="area_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rarity;
    private String areaType;
    private String areaDressing;
    private int discoveriesAmount;
    private int dangersAmount;
    private String oneLiner;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<AreaDiscovery> discoveries = new ArrayList<>();

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<AreaDanger> dangers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "dungeon_id", nullable = true)
    private Dungeon dungeon;



    public String getOneLiner(){
        return oneLiner;
    }
    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Area clone() {
        try {
            return (Area) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }

    public String getAreaDressing() {
        return areaDressing;
    }

    public void setAreaDressing(String areaDressing) {
        this.areaDressing = areaDressing;
    }







    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public int getDiscoveriesAmount() {
        return discoveriesAmount;
    }

    public void setDiscoveriesAmount(int discoveriesAmount) {
        this.discoveriesAmount = discoveriesAmount;
    }



    public int getDangersAmount() {
        return dangersAmount;
    }

    public void setDangersAmount(int dangersAmount) {
        this.dangersAmount = dangersAmount;
    }

    public String getDiscoveries() {

            if (this.getDiscoveriesAmount()==0){
                return "no discoveries";
            } else {
                String str = "";
                int i;
                for(i=0;i<this.getDiscoveriesAmount();i++){
                    str+= String.format("""
                        \n%d) %S""",i+1,this.discoveries.get(i).getFinalResult());
                }
                return str;
            }

    }

    public void setDiscoveries(List<AreaDiscovery> discoveries) {
        this.discoveries = discoveries;
    }

    public String getDangers() {
        if (this.getDangersAmount()==0){
            return "no dangers.";
        } else {
            String str = "";
            int i;
            for(i=0;i<this.getDangersAmount();i++){
                str+= String.format("""
                        \n%d) %S""",i+1,this.dangers.get(i).getFinalResult());
            }
            return str;
        }
    }

    public void setDangers(List<AreaDanger> dangers) {
        this.dangers = dangers;
    }

    @Override
    public String toString(){

     return String.format("""
                Area: %s
                Dressing: %s
                Rarity: %s
                Discoveries: %s
                Dangers: %s""",this.areaType, this.areaDressing,this.rarity,this.getDiscoveries(),this.getDangers());
    }
}
