package com.company.Lecture7.CollectionZoo;

import java.util.*;

public class Zoo <T>{
    Collection <WildAnimal> collectionWildAnimal;
    Collection <PetAnimal> collectionPetAnimal;
    String [] arrayAnimalName = new String [] { "Platon" , "Filipp" , "Avvakum" , "Villi" , "Albert", "Zahar", "Terentiy", "Gennadiy", "Arhip", "Pafnutiy" };


   public Zoo(){
       collectionPetAnimal= new HashSet<PetAnimal>();
       fillCollectionWithPetAnimals(collectionPetAnimal);
       collectionWildAnimal= new HashSet<WildAnimal>();
       fillCollectionWithWildAnimals(collectionWildAnimal);

   }


    public void fillCollectionWithPetAnimals (Collection<PetAnimal> petAnimalsCollection){

        Random random= new Random();
        int max = 5;
        int min = 1;
        max-= min;
        int numberOfAnimal = (int) ((Math.random() * ++max) + min);
        for(int i=0; i<numberOfAnimal;i++){
            int petclassnum= random.nextInt(2);
            if(petclassnum==0) {
               petAnimalsCollection.add(new Cat(arrayAnimalName[random.nextInt(arrayAnimalName.length)]));
            }
            else if(petclassnum==1) {
                petAnimalsCollection.add(new Dog(arrayAnimalName[random.nextInt(arrayAnimalName.length)]));
            }
            }
    }
    public void fillCollectionWithWildAnimals (Collection<WildAnimal> wildAnimalsCollection){

        Random random= new Random();
        int max = 10;
        int min = 3;
        max-= min;
        int numberOfAnimal = (int) ((Math.random() * ++max) + min);
        for(int i=0; i<numberOfAnimal;i++){
            int petclassnum= random.nextInt(2);
            if(petclassnum==0) {
                wildAnimalsCollection.add(new Fox(arrayAnimalName[random.nextInt(arrayAnimalName.length)]));
            }
            else if(petclassnum==1) {
                wildAnimalsCollection.add(new Wolf(arrayAnimalName[random.nextInt(arrayAnimalName.length)]));
            }
        }
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "collectionWildAnimal=" + collectionWildAnimal +
                ", collectionPetAnimal=" + collectionPetAnimal +
                '}';
    }

    private void  printAnimalsFromCollection (Collection<T> myCollection) {

        for (
                Iterator<T> iterator = myCollection.iterator(); iterator.hasNext(); ) {

            T animal = iterator.next();
            System.out.println(animal);

        }
    }
    public void printAllAnimals (Zoo zooCollection){
       printAnimalsFromCollection(zooCollection.collectionWildAnimal);
        printAnimalsFromCollection(zooCollection.collectionPetAnimal);

    }
   }
 /*//Проверка на уникальность
   //ок
    public Zoo() {
        this.collectionPetAnimal= new HashSet<PetAnimal>();
        Cat cat1= new Cat ("Васька");
        Cat cat2= new Cat ("Кот");
        Cat cat3= new Cat ("Васька");
        Dog dog1= new Dog ("Васька");
        Dog dog2= new Dog ("Артемис");
        this.collectionPetAnimal.add(cat1);
        this.collectionPetAnimal.add(cat2);
        this.collectionPetAnimal.add(cat3);
        this.collectionPetAnimal.add(dog1);
        this.collectionPetAnimal.add(dog2);
        System.out.println(this.collectionPetAnimal.toString());
        System.out.println(this.collectionPetAnimal.size());
    }*/