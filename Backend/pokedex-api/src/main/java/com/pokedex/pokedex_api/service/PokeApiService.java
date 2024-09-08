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
        
        // Faz a requisição GET e recebe um JsonNode
        JsonNode jsonNode = restTemplate.getForObject(url, JsonNode.class);
        
        // Acessando os campos 'name', 'weight', 'height' que é o nome do pokemon
        String name=jsonNode.get("name").asText();
        Integer height=jsonNode.get("height").asInt();
        Integer weight=jsonNode.get("weight").asInt();
        
        //Criando o pokemon
        PokemonEntity pokemon= new PokemonEntity(name, id, height, weight);
        JsonNode abilitiesNode = jsonNode.get("abilities");
        JsonNode statsNode = jsonNode.get("stats");
        List<Integer> statsValues = new ArrayList<>();
        JsonNode typesNode = jsonNode.get("types");
        JsonNode speciesNode = jsonNode.get("species");

        if (abilitiesNode != null && abilitiesNode.isArray()) {
            for (JsonNode abilityNode : abilitiesNode) {
                // Acessa o objeto ability dentro de cada item da lista
                JsonNode abilityDetails = abilityNode.get("ability");
                String abilityName = abilityDetails.get("name").asText();
                pokemon.setAbilities(abilityName);
            }
        }
        if (statsNode != null && statsNode.isArray()) {
            for (JsonNode Node : statsNode) {
                // Acessa o objeto ability dentro de cada item da lista
                Integer valor = Node.get("base_stat").asInt();
                statsValues.add(valor);
            }
        }
        pokemon.setStats(statsValues.get(0), statsValues.get(1), statsValues.get(2), statsValues.get(3), statsValues.get(4), statsValues.get(5));

        if (typesNode != null && typesNode.isArray()) {
            for (JsonNode typeNode : typesNode) {
                // Acessa o objeto ability dentro de cada item da lista
                JsonNode typeDetails = typeNode.get("type");
                String typeName = typeDetails.get("name").asText();
                pokemon.setTypes(typeName);
            }
        }

         if (speciesNode != null) {
                String speciesUrl = speciesNode.get("url").asText();
                JsonNode speciesData = restTemplate.getForObject(speciesUrl, JsonNode.class);
                if (speciesData != null) {
                    JsonNode evolutionChainNode = speciesData.get("evolution_chain");
                    if (evolutionChainNode != null) {
                        String evolutionChainUrl = evolutionChainNode.get("url").asText();
                        JsonNode evolutionChainData = restTemplate.getForObject(evolutionChainUrl, JsonNode.class);
                        if (evolutionChainData != null) {
                            Set<String> evolutionNames = new HashSet<>();
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
private void extractEvolutionNames(JsonNode chainNode, Set<String> evolutionNames) {
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
