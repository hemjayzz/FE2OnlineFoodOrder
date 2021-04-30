/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hemja
 */
class Dishes {
    private int DishID,FoodOrderID,Price,Quantity,Stock;
    private String DishName, DishImage;


public Dishes(int DishID,int FoodOrderID, String DishName,int Price,int Quantity,int Stock,String DishImage){
        this.DishID=DishID;
        this.FoodOrderID=FoodOrderID;
        this.DishName=DishName;
        this.Price=Price;
        this.Quantity=Quantity;
        this.Stock=Stock;
        this.DishImage=DishImage;
    }

    public int getDishID() {
        return DishID;
    }

    public int getFoodOrderID() {
        return FoodOrderID;
    }

    public int getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getStock() {
        return Stock;
    }

    public String getDishName() {
        return DishName;
    }

    public String getDishImage() {
        return DishImage;
    }
  

}