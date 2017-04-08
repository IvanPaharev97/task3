package com.epam.task3.entity;

public class Vegetable extends Product{
    private double proteinsPerGramm;
    private double fatPerGramm;
    private double carbohydratesPerGramm;
    
    private Vegetable(String name,
            double caloriesPerGramm,
            double proteinsPerGramm,
            double fatPerGramm,
            double carbohydratesPerGramm) {
        super(name, caloriesPerGramm);
        this.proteinsPerGramm = proteinsPerGramm;
        this.fatPerGramm = fatPerGramm;
        this.carbohydratesPerGramm = carbohydratesPerGramm;
    }

    public double getProteinsPerGramm() {
        return proteinsPerGramm;
    }

    public double getFatPerGramm() {
        return fatPerGramm;
    }

    public double getCarbohydratesPerGramm() {
        return carbohydratesPerGramm;
    }

    @Override
    public String toString() {
        return "Vegetable [name=" + getName() + ", caloriesPerGramm=" + getCaloriesPerGramm() + ", proteinsPerGramm="
                + proteinsPerGramm + ", fatPerGramm=" + fatPerGramm + ", carbohydratesPerGramm=" + carbohydratesPerGramm
                + "]";
    }    
       
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(carbohydratesPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fatPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(proteinsPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vegetable other = (Vegetable) obj;
        if (Double.doubleToLongBits(carbohydratesPerGramm) != Double.doubleToLongBits(other.carbohydratesPerGramm))
            return false;
        if (Double.doubleToLongBits(fatPerGramm) != Double.doubleToLongBits(other.fatPerGramm))
            return false;
        if (Double.doubleToLongBits(proteinsPerGramm) != Double.doubleToLongBits(other.proteinsPerGramm))
            return false;
        return true;
    }

    public static class VegetableBuilder{
        private String name;
        private double caloriesPerGramm;
        private double proteinsPerGramm;
        private double fatPerGramm;
        private double carbohydratesPerGramm;
        
        public VegetableBuilder(String name, double caloriesPerGramm) {
            this.name = name;
            this.caloriesPerGramm = caloriesPerGramm;
        }
        
        public VegetableBuilder(Product product) {
            this(product.getName(), product.getCaloriesPerGramm());
        }
        
        public VegetableBuilder setProteinsPerGramm(double proteinsPerGramm) {
            this.proteinsPerGramm = proteinsPerGramm;
            return this;
        }
        
        public VegetableBuilder setFatPerGramm(double fatPerGramm) {
            this.fatPerGramm = fatPerGramm;
            return this;
        }
        
        public VegetableBuilder setCarbohydratesPerGramm(double carbohydratesPerGramm) {
            this.carbohydratesPerGramm = carbohydratesPerGramm;
            return this;
        }
        
        public Vegetable build() {
            return new Vegetable(name,
                    caloriesPerGramm,
                    proteinsPerGramm,
                    fatPerGramm,
                    carbohydratesPerGramm);
        }
    }
}
