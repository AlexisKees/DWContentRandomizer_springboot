package dw.randomizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Creature implements IPWClass<Creature> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    private String subcategory;
    private String prompt;
    @Transient private String[] categoryTable;
    @Transient private String[] subcategoryTable;
    @Transient private String[] promptTable;
    private String size;
    private String groupSize;
    private int hitPoints;
    private int armor;
    private String damage;
    private String damageType;
    private String reach;
    private String tags;
    private String alignment;
    private String disposition;
    private String armorType;
    private String oneLiner;

    public String getOneLiner(){
        return oneLiner;
    }
    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Creature clone() {
        try {
            return (Creature) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }

    @Override
    public String toString(){
        return String.format("""
                    Creature category: %s
                    Creature subcategory: %s
                    Creature prompt: %s
                    Group size: %s
                    Individual size: %s
                    %dHP (%d armor: %s)
                    %s damage (%s, %s)
                    Tags: %s
                    Alignment: %s
                    Disposition: %s""", this.getCategory(), this.getSubcategory(), this.getPrompt(),this.getGroupSize(),this.getSize(),this.getHitPoints(),this.getArmor(),this.getArmorType(),
                this.getDamage(),this.getReach(),this.getDamageType(),this.getTags(),this.getAlignment(),this.getDisposition());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String[] getCategoryTable() {
        return categoryTable;
    }

    public void setCategoryTable(String[] categoryTable) {
        this.categoryTable = categoryTable;
    }

    public String[] getSubcategoryTable() {
        return subcategoryTable;
    }

    public void setSubcategoryTable(String[] subcategoryTable) {
        this.subcategoryTable = subcategoryTable;
    }

    public String[] getPromptTable() {
        return promptTable;
    }

    public void setPromptTable(String[] promptTable) {
        this.promptTable = promptTable;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
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

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getArmorType() {
        return armorType;
    }

    public void setArmorType(String armorType) {
        this.armorType = armorType;
    }

    public String getPrompt() {
        return prompt;
    }
}






















