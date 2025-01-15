import Model.Character;
import Model.Product;
import Model.ValidationException;
import Controller.Controller;
import Repository.Repository;
import Repository.InMemoryRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class GOT {
    private Controller controller;

    public GOT(Controller controller) {
        this.controller = controller;
    }

    private void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        Welcome to the Shop!
                        1. Add Character
                        2. Search Character
                        3. Update Character
                        4. Remove Character
                        5. View All Characters
                        6. Add Product
                        7. Search Product
                        8. Update Product
                        9. Remove Product
                        10. View All Products
                        11. Filter characters by a given origin:
                        12. Characters that have bought a product from a given region:
                        13. Sort a given characters products in a given order:
                        0. Exit
                        Please select an option:
                        """);
                String option = scanner.nextLine();
                switch (option) {
                    case "0":
                        running = false;
                        break;
                    case "1":
                        addCharacter(scanner);
                        break;
                    case "2":
                        searchCharacter(scanner);
                        break;
                    case "3":
                        updateCharacter(scanner);
                        break;
                    case "4":
                        deleteCharacter(scanner);
                        break;
                    case "5":
                        viewCharacters();
                        break;
                    case "6":
                        addProduct(scanner);
                        break;
                    case "7":
                        searchProduct(scanner);
                        break;
                    case "8":
                        updateProduct(scanner);
                        break;
                    case "9":
                        deleteProduct(scanner);
                        break;
                    case "10":
                        viewProducts(scanner);
                        break;
                    case "11":
                        filterCharacters(scanner);
                        break;
                    case "12":
                        charactersThatBoughtFromRegion(scanner);
                        break;
                    case "13":
                        sortProductPricesOfCharacter(scanner);
                        break;
                    default:
                        throw new ValidationException("Invalid option");
                }
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private void sortProductPricesOfCharacter(Scanner scanner) {
    }

    private void charactersThatBoughtFromRegion(Scanner scanner) {
        System.out.println("Enter region of products:");
        String region = scanner.nextLine();
        List<Character> characters = controller.charactersThatBoughtFromRegion(region);
    }

    private void filterCharacters(Scanner scanner) {
        System.out.println("Enter origin");
        String origin = scanner.nextLine();
        List<Character> filteredCharacters = controller.filterCharacters(origin);
        for (Character character : filteredCharacters) {
            System.out.println(character.toString());
        }
    }

    private void viewProducts(Scanner scanner) {
        System.out.println("Here are the products:");
        List<Product> products = controller.getAllProducts();
        for (Product product: products){
            System.out.println(product.toString());
        }
    }

    private void deleteProduct(Scanner scanner) {
        System.out.println("Enter the id of the Product you want to delete:");
        Integer id = Integer.parseInt(scanner.nextLine());
        if(controller.deleteProduct(id)){
            System.out.println("Product deleted successfully.");
        }
        else {
            System.out.println("Product not found.");
        }
    }

    private void updateProduct(Scanner scanner) {
        System.out.println("Enter the id of the Product you want to update:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new price:");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter new region:");
        String region = scanner.nextLine();
        Product product = controller.updateProduct(id,name,price,region);
    }

    private void searchProduct(Scanner scanner) {
        System.out.println("Enter the id of the Product you want to search:");
        Integer id = Integer.parseInt(scanner.nextLine());
        Product product = controller.getProduct(id);
        System.out.println(product.toString());
    }

    private void addProduct(Scanner scanner) {
        System.out.println("Enter Product ID:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Product Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Product Price:");
        Integer price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Product Region:");
        String region = scanner.nextLine();
        Product product = new Product(id, name, price, region);
        product = controller.addProduct(product);
        if(product != null){
            System.out.println("Product added successfully.");
        } else
        {
            System.out.println("Product could not be added.");
        }
    }

    private void viewCharacters() {
        System.out.println("Here are the Characters");
        List<Character> characters = controller.getAllCharacters();
        for (Character character: characters){
            System.out.println(character.toString());
        }
    }

    private void deleteCharacter(Scanner scanner) {
        System.out.println("Id of the Character you want to delete:");
        Integer id = Integer.parseInt(scanner.nextLine());
        if(controller.deleteCharacter(id)){
            System.out.println("Character deleted successfully.");
        }
        else {
            System.out.println("Character could not be deleted.");
        }
    }

    private void updateCharacter(Scanner scanner) {
        System.out.println("Enter the id of the Character you want to update:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new origin:");
        String origin = scanner.nextLine();
        Character character = controller.updateCharacter(id,name,origin);
        if(character != null){
            System.out.println("Character updated successfully.");

        }
        else {
            System.out.println("Character not found.");
        }

    }

    private void searchCharacter(Scanner scanner) {
        System.out.println("Enter Character ID:");
        Integer id = Integer.parseInt(scanner.nextLine());
        Character character = controller.getCharacter(id);
        System.out.println(character.toString());

    }

    private void addCharacter(Scanner scanner) {
        System.out.println("Enter Character ID:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Character Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Character Origin:");
        String origin = scanner.nextLine();
        Character character = new Character(id, name, origin);
        character = controller.addCharacter(character);
        if(character!= null) {
            System.out.println("Character added successfully.");
        }
        else {
            System.out.println("Character could not be added.");
        }
    }

    public static void main(String[] args) {
        Repository<Product> productRepository = new InMemoryRepository<Product>();
        Repository<Character> characterRepository = new InMemoryRepository<Character>();

        Product product1 = new Product(1,"Dragon Claw", 32, "Lava Lakes");
        Product product2 = new Product(2,"Spider Web", 12, "Dark Forests");
        Product product3 = new Product(3,"Dragon Egg", 56, "Lava Lakes");
        Product product4 = new Product(4,"Wolf Tooth", 16, "Dark Forests");
        Product product5 = new Product(4,"Frog Leg", 16, "Festering Swamps");
        Product product6 = new Product(4,"Bat Wings", 16, "Festering Swamps");

        List<Product> list1 = new ArrayList<Product>();
        List<Product> list2 = new ArrayList<Product>();
        List<Product> list3 = new ArrayList<Product>();

        list1.add(product1);
        list1.add(product2);
        list1.add(product4);
        list2.add(product3);
        list2.add(product6);
        list3.add(product5);
        list3.add(product6);
        list3.add(product1);
        list2.add(product4);

        productRepository.create(product1);
        productRepository.create(product2);
        productRepository.create(product3);
        productRepository.create(product4);
        productRepository.create(product5);
        productRepository.create(product6);

        Character character1 = new Character(1,"Dio", "Lava Pond");
        Character character2 = new Character(2,"Diana", "Lava Pond");
        Character character3 = new Character(3,"John", "Icy Slopes");
        Character character4 = new Character(4,"Daeneris", "Dragon Lair");
        Character character5 = new Character(5,"Drakel", "Dragon Lair");

        character1.setProducts(list1);
        character2.setProducts(list2);
        character3.setProducts(list3);
        character4.setProducts(list2);
        character5.setProducts(list1);

        characterRepository.create(character1);
        characterRepository.create(character2);
        characterRepository.create(character3);
        characterRepository.create(character4);
        characterRepository.create(character5);

        Controller controller = new Controller(productRepository, characterRepository);
        GOT got = new GOT(controller);
        got.start();
    }
}