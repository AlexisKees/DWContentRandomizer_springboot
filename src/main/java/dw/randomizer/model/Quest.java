package dw.randomizer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quest implements IPWClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String task;
    private String relevance;
    private String reward;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "biome_id", nullable = false)
    private Biome biome;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "dungeon_id", nullable = false)
    private Dungeon dungeon;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "quest_giver_id", nullable = false)
    private NPC questGiver;

    private String oneLiner;
    private String brief;


    public String getOneLiner(){
        return oneLiner;
    }

    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Quest clone() {
        try {
            return (Quest) super.clone();
        } catch (Exception e){
            System.out.println("Error cloning object: "+e.getMessage());
            return null;
        }
    }


    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    @Override
    public String toString(){
        return String.format("""
                QUEST:
                TASK: %s\n
                RELEVANCE: %s\n
                REWARD: %s\n
                QUEST GIVER:
                %s\n
                QUEST LOCATION:
                %s\n
                %s\n""", this.task, this.relevance, this.reward, this.questGiver, this.biome,this.dungeon);

    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public NPC getQuestGiver() {
        return questGiver;
    }

    public void setQuestGiver(NPC questGiver) {
        this.questGiver = questGiver;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBrief() {
        return brief;
    }
}
