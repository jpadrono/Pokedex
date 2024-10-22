package com.pokedex.pokedex_api.service;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

//import org.hibernate.mapping.Set;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.pokedex.pokedex_api.entities.PokemonEntity;


public class PokeApiService {
    private static final String ArrayList = null;
    private final RestTemplate restTemplate;

    public PokeApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonEntity getPokemon(Integer id) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;
        String img;
        if (id >= 1 && id <= 9){
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+id+".png";
        }
        else if (id>= 10 && id <= 99)
        {
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+id+".png";
        }
        else{
            img = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+id+".png";
        }
        // Faz a requisição GET e recebe um JsonNode
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        
        // Acessando os campos 'name', 'weight', 'height' que é o nome do pokemon
        String name=jsonNode.get("name").asText();
        Integer height=jsonNode.get("height").asInt();
        Integer weight=jsonNode.get("weight").asInt();
        
        //Criando o pokemon
        PokemonEntity pokemon= new PokemonEntity(name, id, height, weight, img);

        JsonNode abilitiesNode = jsonNode.get("abilities");
        if (abilitiesNode != null && abilitiesNode.isArray()) {
            for (JsonNode abilityNode : abilitiesNode) {
                // Acessa o objeto ability dentro de cada item da lista
                JsonNode abilityDetails = abilityNode.get("ability");
                String abilityName = abilityDetails.get("name").asText();
                pokemon.setAbilities(abilityName);
            }
        }

        /*JsonNode spritesNode = jsonNode.get("sprites");
        if (spritesNode != null) {
            // Extrai a URL da sprite padrão da frente
            JsonNode frontDefaultNode = spritesNode.get("front_default");
            if (frontDefaultNode != null) {
                String frontDefaultUrl = frontDefaultNode.asText();
                pokemon.setImgUrl(frontDefaultUrl);
            }}*/
        if(id<10)
        {
            pokemon.setImgUrl("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/00"+id+".png");
        }
        else if(id<100){
            pokemon.setImgUrl("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/0"+id+".png");
        }
        else{
            pokemon.setImgUrl("https://assets.pokemon.com/assets/cms2/img/pokedex/detail/"+id+".png");
        }

        List<Integer> statsValues = new ArrayList<>();
        JsonNode statsNode = jsonNode.get("stats");
        if (statsNode != null && statsNode.isArray()) {
            for (JsonNode Node : statsNode) {
                // Acessa o objeto ability dentro de cada item da lista
                Integer valor = Node.get("base_stat").asInt();
                statsValues.add(valor);
            }
        }
        pokemon.setStats(statsValues.get(0), statsValues.get(1), statsValues.get(2), statsValues.get(3), statsValues.get(4), statsValues.get(5));

        JsonNode typesNode = jsonNode.get("types");
        if (typesNode != null && typesNode.isArray()) {
            for (JsonNode typeNode : typesNode) {
                // Acessa o objeto ability dentro de cada item da lista
                JsonNode typeDetails = typeNode.get("type");
                String typeName = typeDetails.get("name").asText();
                pokemon.setTypes(typeName);
            }
        }

        JsonNode speciesNode = jsonNode.get("species");
         if (speciesNode != null) {
                String speciesUrl = speciesNode.get("url").asText();
                JsonNode speciesData = restTemplate.getForObject(speciesUrl, JsonNode.class);
                if (speciesData != null) {
                    JsonNode evolutionChainNode = speciesData.get("evolution_chain");
                    if (evolutionChainNode != null) {
                        String evolutionChainUrl = evolutionChainNode.get("url").asText();
                        JsonNode evolutionChainData = restTemplate.getForObject(evolutionChainUrl, JsonNode.class);
                        if (evolutionChainData != null) {
                            List<String> evolutionNames = new ArrayList<>();
                            extractEvolutionNames(evolutionChainData.get("chain"), evolutionNames);
                            for (String evolutionName : evolutionNames) {
                            pokemon.setEvolves_to(evolutionName);
                            }
                        }
                    }
                }
            }
    
        
        return pokemon;
}

private void extractEvolutionNames(JsonNode chainNode, List<String> evolutionNames) {
    if (chainNode != null) {
        JsonNode speciesNode = chainNode.get("species");
        if (speciesNode != null) {
            String name = speciesNode.get("name").asText();
            evolutionNames.add(name);
        }
        JsonNode evolvesToNode = chainNode.get("evolves_to");
        if (evolvesToNode != null && evolvesToNode.isArray()) {
            for (JsonNode evolveNode : evolvesToNode) {
                extractEvolutionNames(evolveNode, evolutionNames);
            }
        }
    }
}


}
