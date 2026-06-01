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
public class Biome implements IPWClass {
    // se agrega atributo id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String biome;
    private String weather;
    private String weatherIntensity;
    private String wildlife;
    private String population;
    private String roads;
    private String alignment;
    private String distance;
    private String oneLiner;

    @OneToOne(mappedBy = "biome", optional = true)
    private Quest quest;


    //Constructor solo con id (sirve para eliminar elementos de la bd)

    public Biome(int id){
        this.id = id;
    }
    //constructor con todos los atributos para insertar
    public Biome (String biome, String weather, String weatherIntensity, String wildlife, String population, String roads, String alignment, String distance, String oneLiner){
        this.biome = biome;
        this.weather = weather;
        this.weatherIntensity = weatherIntensity;
        this.wildlife = wildlife;
        this.population = population;
        this.roads = roads;
        this.alignment = alignment;
        this.distance = distance;
        this.oneLiner = oneLiner;

    }

    //constructor con todos los atributos inlcuido id, para hacer select
    public Biome (int id,String biome, String weather, String weatherIntensity, String wildlife, String population, String roads, String alignment, String distance, String oneLiner){
        // se llama al constructor anterior EN LA PRIMERA LINEA del nuevo constructor
        //no se puede llamar el otro constructor para inicializar el id, sólo uno en la primera linea
        this(biome, weather, weatherIntensity, wildlife, population, roads, alignment, distance, oneLiner);
        this.id=id;

    }

    
    @Override
    public void setOneLiner(String oneLiner) {
        this.oneLiner = oneLiner;
    }

    @Override
    public Biome clone() {
        try {
                return (Biome) super.clone();
            } catch (Exception e){
                System.out.println("Error cloning object: "+e.getMessage());
                return null;
        }
    }

    @Override
    public String toString(){

        return String.format("""
            Biome: %s
            Weather: %s
            Weather Intensity: %s
            Wildlife: %s
            Population: %s
            Roads: %s
            Alignment: %s
            Distance: %s""", this.getBiome(), this.getWeather(),this.getWeatherIntensity(), this.getWildlife(), this.getPopulation(),this.getRoads(),this.getAlignment(),this.getDistance() );

    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Biome biome1 = (Biome) o;
        return id == biome1.id && Objects.equals(biome, biome1.biome) && Objects.equals(weather, biome1.weather) && Objects.equals(weatherIntensity, biome1.weatherIntensity) && Objects.equals(wildlife, biome1.wildlife) && Objects.equals(population, biome1.population) && Objects.equals(roads, biome1.roads) && Objects.equals(alignment, biome1.alignment) && Objects.equals(distance, biome1.distance) && Objects.equals(oneLiner, biome1.oneLiner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, biome, weather, weatherIntensity, wildlife, population, roads, alignment, distance, oneLiner);
    }
}
