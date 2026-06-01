package dw.randomizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower implements IPWClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Transient private String[] raceTable;
    private String race;
    private String gender;
    private String ethnics;
    @Transient private String[] namesTable;
    private String name;
    private String age;

    private String qualityString;
    private int quality = 0;
    private String loyaltyString;
    private int loyalty = 0;
    private String background;
    private String cost;
    private String instinct;
    private String hpAndDamage;
    private int HP;
    private String armorString;
    private String armorType;
    private int armor;

    private boolean hasShield;
    private String damage;
    private String tags ="";

    private String oneLiner;

    @Override
    public String toString(){
        String str = String.format("""
                %s, %s %s
                Age: %s
                Quality: %d
                Loyalty: %d
                Instinct: %s
                Cost: %s
                %dHP, %d armor (%s)
                %s damage""", this.name.toUpperCase(), this.gender.toUpperCase(),
                this.race.toUpperCase(),this.age,this.quality,this.loyalty,this.instinct, this.cost,this.HP,this.armor,this.armorType,this.damage);
        return str;
    }

    @Override
    public String getOneLiner(){
        return oneLiner;
    }

    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Follower clone() {
        try {
            return (Follower) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }

    public String[] getRaceTable() {
        return raceTable;
    }

    public void setRaceTable(String[] raceTable) {
        this.raceTable = raceTable;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEthnics() {
        return ethnics;
    }

    public void setEthnics(String ethnics) {
        this.ethnics = ethnics;
    }

    public String[] getNamesTable() {
        return namesTable;
    }

    public void setNamesTable(String[] namesTable) {
        this.namesTable = namesTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getQualityString() {
        return qualityString;
    }

    public void setQualityString(String qualityString) {
        this.qualityString = qualityString;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInstinct() {
        return instinct;
    }

    public void setInstinct(String instinct) {
        this.instinct = instinct;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setLoyaltyString(String followerLoyalty) {
        this.loyaltyString = followerLoyalty;
    }

    public String getLoyaltyString() {
        return loyaltyString;
    }

    public String getHpAndDamage() {
        return hpAndDamage;
    }

    public void setHpAndDamage(String hpAndDamage) {
        this.hpAndDamage = hpAndDamage;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setShield(boolean shield) {
        this.hasShield = shield;
    }

    public boolean getShield(){
        return hasShield;
    }

    public String getArmorString() {
        return armorString;
    }

    public void setArmorString(String armorString) {
        this.armorString = armorString;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }
}
