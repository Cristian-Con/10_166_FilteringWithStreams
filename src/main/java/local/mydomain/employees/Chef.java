package local.mydomain.employees;

public interface Chef {

    String favoriteFood = "hamburger"; //this field is made final automatically by Java

    default void cook(String food) {
        System.out.println("I am now cooking " + food);
    }

    default String cleanUp() {
        return  "I'm done cleaning up";
    }

    default String getFavoriteFood() {
        return favoriteFood;
    }

    default void setFavoriteFood(String favoriteFood) { //this is a limitation for multiple inheritance
                                                       //because the favoriteFood being final, it can't be
                                                       //set for another value
       // this.favoriteFood = favoriteFood;
    }



}
