package Controller;

import Model.Character;
import Model.EntityNotFoundException;
import Model.Product;
import Repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private final Repository<Product> productRepository;
    private final Repository<Character> characterRepository;

    public Controller(Repository<Product> productRepository, Repository<Character> characterRepository) {
        this.productRepository = productRepository;
        this.characterRepository = characterRepository;
    }

    public Product addProduct(Product product) {
        Integer id = product.getID();
        if (productRepository.get(id) != null) {
            return null;
        } else {
            productRepository.create(product);
            return product;
        }
    }

    public Product getProduct(Integer id) {
        try {
            Product product = productRepository.get(id);
            if (product != null) {
                return product;
            } else throw new EntityNotFoundException("Product not found");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Product updateProduct(Integer id, String name, Double price, String region) {
        Product product = productRepository.get(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setRegion(region);
            productRepository.update(product);
            return product;
        } else return null;
    }

    public boolean deleteProduct(Integer id) {
        if (productRepository.get(id) != null) {
           productRepository.delete(id);
            return true;
        } else return false;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    public Character addCharacter(Character character) {
        Integer id = character.getID();
        if (characterRepository.get(id) != null) {
            return null;
        } else {
            characterRepository.create(character);
            return character;
        }
    }

    public List<Character> getAllCharacters() {
        return characterRepository.getAll();
    }

    public Character getCharacter(Integer id) {
        try {
            Character character = characterRepository.get(id);
            if (character != null) {
                return character;
            } else throw new EntityNotFoundException("Client not found");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Character updateCharacter(Integer id, String name, String origin) {
        Character character = characterRepository.get(id);
        if (character != null) {
            character.setName(name);
            character.setOrigin(origin);
            characterRepository.update(character);
            return character;
        }
        else return null;
    }

    public boolean deleteCharacter(Integer id) {
        if (characterRepository.get(0) != null) {
            characterRepository.delete(id);
            return true;
        }
        else return false;
    }

    public List<Character> filterCharacters(String origin) {
        return characterRepository.getAll().stream().filter(character -> character.getOrigin().equals(origin)).toList();
    }

    public List<Character> charactersThatBoughtFromRegion(String region) {
        List<Character> characters = characterRepository.getAll();
        List<Character> filteredCharacters = new ArrayList<>();
        for (Character character : characters) {
            for (Product product : character.getProducts()) {
                if (product.getRegion().equals(region)) {
                    filteredCharacters.add(character);
                    break;
                }
            }
        }
        return filteredCharacters;
    }

    public List<Product> sortCharacterProducts(Integer id, String order) {
        Character character = characterRepository.get(id);
        List<Product> sortedProducts;
        if (order.equals("asc")) {
            character.getProducts().sort(Comparator.comparing(Product::getPrice));
            sortedProducts = character.getProducts();
            return sortedProducts;
        } else if (order.equals("desc")) {
            sortedProducts = character.getProducts().stream().sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
            return sortedProducts;
        } else return null;
    }
}
