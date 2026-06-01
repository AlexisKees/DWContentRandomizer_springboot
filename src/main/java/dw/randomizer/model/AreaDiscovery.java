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
public class AreaDiscovery implements IPWClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String category;
    @Transient private String[] promptTable;
    private String prompt;
    private String finalResult;
    private String oneLiner;

    @ManyToOne
    @JoinColumn(name="area_id")
    private Area area;

    public String getOneLiner(){
        return oneLiner;
    }
    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public AreaDiscovery clone() {
        try {
            return (AreaDiscovery) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }

    @Override
    public String toString(){
        String str = "DUNGEON DISCOVERY: "+this.category+" -> "+this.prompt+"\n";
        if (!Objects.equals(this.prompt,this.finalResult)){
            str+=this.finalResult;
        }
        return str;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getPromptTable() {
        return promptTable;
    }

    public void setPromptTable(String[] promptTable) {
        this.promptTable = promptTable;
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
}
