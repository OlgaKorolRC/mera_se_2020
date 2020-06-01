package VostrikovaE.Lesson7;

import VostrikovaE.Lesson7.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Zoo {
    private final Set<WildAnimal> wildAnimals = new HashSet<>();
    private final Set<PetAnimal> petAnimals = new HashSet<>();

    private final int MAX_PET_ANIMAL=5;
    private final int MIN_PET_ANIMAL=0;

    private final int MAX_WILD_ANIMAL=10;
    private final int MIN_WILD_ANIMAL=3;



    public Zoo(){
        fillCollectionWithPetAnimals(petAnimals);
        fillCollectionWithWildAnimals(wildAnimals);
    }

    private PetAnimal  generateRandomPet(){
        Random rnd = new Random();
        PetAnimal returnedAnimal;
        int rndVal=rnd.nextInt(100);
        if (rndVal%2==0){
            returnedAnimal= new Cat(getRandomName());
        } else{
            returnedAnimal= new Dog(getRandomName());
        }
        return returnedAnimal;
    }

    private WildAnimal  generateRandomWild(){
        Random rnd = new Random();
        WildAnimal returnedAnimal;
        int rndVal=rnd.nextInt(100);
        if (rndVal%2==0){
            returnedAnimal= new Fox(getRandomName());
        } else{
            returnedAnimal= new Wolf(getRandomName());
        }
        return returnedAnimal;
    }


    private String getRandomName(){
        Random rnd = new Random();

        int rndVal=rnd.nextInt(5);
        String randomName;
        switch (rndVal){
            case 0->randomName="Lusya";
            case 1->randomName="Marusya";
            case 2->randomName="Jack";
            case 3->randomName="Lessy";
            case 4->randomName="Druzhok";
            case 5->randomName="Murka";
            default -> randomName="Random name";
        }
        return randomName;

    }
    private void fillCollectionWithPetAnimals(Set<PetAnimal> petAnimals){
        Random rnd=new Random();
        int countPet=rnd.nextInt(MAX_PET_ANIMAL-MIN_PET_ANIMAL)+MIN_PET_ANIMAL;
        for (int i=0; i<countPet; i++) {
            petAnimals.add(generateRandomPet());
        }
    }
    private void fillCollectionWithWildAnimals(Set<WildAnimal> wildAnimals){
        Random rnd=new Random();
        int countWild=rnd.nextInt(MAX_WILD_ANIMAL-MIN_WILD_ANIMAL)+MIN_WILD_ANIMAL;
        for (int i=0; i<countWild; i++) {
            wildAnimals.add(generateRandomWild());
        }
    }
    public void printAllAnimals(){
        System.out.println("Hello");
        System.out.println("Our Zoo contains the following pet animals:"+ '\n');
        printAnimalFromCollection(petAnimals);
        System.out.println('\n'+"And the following wild animals:"+'\n');
        printAnimalFromCollection(wildAnimals);
    }


    private void printAnimalFromCollection(Set<? extends Animal> animals){
        try{ //отлавливаем неинициализированную коллекцию
            if(animals.size()>0){ //в коллекции что то есть
                for (Animal animal : animals) {
                    System.out.println(animal);
                }
            } else { //коллекция пустая
                System.out.println("Ooops, we don't have animals");
            }
        } catch (NullPointerException ex){ // коллекция неинициализирована
            System.out.println("Ooops, we don't have animals");
        }
    }
}
