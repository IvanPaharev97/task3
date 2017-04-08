package com.epam.task3.entity;

public abstract class Dish {
    private String name;
    private double caloricity;
    private double weight;
    
    public Dish(String name, double caloricity, double weight) {
        super();
        this.name = name;
        this.caloricity = caloricity;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getCaloricity() {
        return caloricity;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(caloricity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dish other = (Dish) obj;
        if (Double.doubleToLongBits(caloricity) != Double.doubleToLongBits(other.caloricity))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Dish [name=" + name + ", caloricity=" + caloricity + ", weight=" + weight + "]";
    }
    
    
}
