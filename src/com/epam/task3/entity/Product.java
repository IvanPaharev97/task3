package com.epam.task3.entity;

public abstract class Product {
    private String name;
    private double caloriesPerGramm;
    
    public Product(String name, double caloriesPerGramm) {
        super();
        this.name = name;
        this.caloriesPerGramm = caloriesPerGramm;
    }

    public String getName() {
        return name;
    }

    public double getCaloriesPerGramm() {
        return caloriesPerGramm;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(caloriesPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Product other = (Product) obj;
        if (Double.doubleToLongBits(caloriesPerGramm) != Double.doubleToLongBits(other.caloriesPerGramm))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", caloriesPerGramm=" + caloriesPerGramm + "]";
    }
    
}
