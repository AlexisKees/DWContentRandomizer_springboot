package dw.randomizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discovery implements IPWClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    @Transient private String[] subcategoriesTable;
    private String subcategory;
    @Transient private String[] promptTable;
    private String prompt;
    private String finalResult;
    private String oneLiner;

    public String getOneLiner(){
        return oneLiner;
    }
    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Discovery clone() {
        try {
            return (Discovery) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }


    public String[] getPromptTable() {
        return promptTable;
    }

    public void setPromptTable(String[] promptTable) {
        this.promptTable = promptTable;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getSubcategoriesTable() {
        return subcategoriesTable;
    }

    public void setSubcategoriesTable(String[] subcategoriesTable) {
        this.subcategoriesTable = subcategoriesTable;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;
    }

    @Override
    public String toString(){
      String str= "DISCOVERY: "+this.category+" -> "+this.subcategory+" -> "+this.prompt+"\n";
        if (!Objects.equals(this.prompt,this.finalResult)){
            str+=this.finalResult;
        }
        return str;
    };
}
