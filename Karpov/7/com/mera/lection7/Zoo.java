package com.mera.lection7;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class Zoo {

    private final int MIN_PET_ANIMAL_COLLECTION=1;
    private final int MAX_PET_ANIMAL_COLLECTION=5;
    private final int MIN_WILD_ANIMAL_COLLECTION=3;
    private final int MAX_WILD_ANIMAL_COLLECTION=10;

    private Set<WildAnimal> wildAnimals = new HashSet<WildAnimal>();
    private Set<PetAnimal> petAnimals= new HashSet<PetAnimal>();

    Zoo(){
        fillCollectionWithPetAnimals(petAnimals);
        fillCollectionWithWildAnimals(wildAnimals);
    }

    private void fillCollectionWithPetAnimals(Set <PetAnimal> petSet){
        for (int i=0; i<genRandom(MAX_PET_ANIMAL_COLLECTION, MIN_PET_ANIMAL_COLLECTION); i++){
            switch (new Random().nextInt(2)){
                 case 0:
                     while(!petSet.add(new Dog()));
                     break;
                 default:
                     while(!petSet.add(new Cat()));
             }

        }
    }

    private void fillCollectionWithWildAnimals(Set <WildAnimal> wildSet){
        for (int i=0; i<genRandom(MIN_WILD_ANIMAL_COLLECTION, MAX_WILD_ANIMAL_COLLECTION); i++){
            switch (new Random().nextInt(2)){
                case 0:
                    while(!wildSet.add(new Wolf()));
                    break;
                default:
                    while(!wildSet.add(new Fox()));
            }
        }
    }
    private void printAnimalsFromCollection(Set <? extends Animal> animalSet){
        for (Animal i: animalSet) {
            System.out.println(i);
        }
    }

    public void printAllAnimals(){
        System.out.println("Wild animals in our zoo:");
        printAnimalsFromCollection(wildAnimals);

        System.out.println("\nPet animals in our zoo:");
        printAnimalsFromCollection(petAnimals);
    }

    private int genRandom(int minimal, int maximal){
        int min = minimal;
        int max = maximal;

        if (min>max){
            int buf = max;
            max = min;
            min = buf;
        }

        int bound = max-min+1;

        return (new Random().nextInt(bound)+min);
    }
}
