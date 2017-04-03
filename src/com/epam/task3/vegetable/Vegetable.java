package com.epam.task3.vegetable;

public class Vegetable implements Comparable<Vegetable>{
    private String name;
    private double caloriesPerGramm;
    private double proteinsPerGramm;
    private double fatPerGramm;
    private double carbohydratesPerGramm;
    
    private Vegetable(String name,
            double caloriesPerGramm,
            double proteinsPerGramm,
            double fatPerGramm,
            double carbohydratesPerGramm) {
        super();
        this.name = name;
        this.caloriesPerGramm = caloriesPerGramm;
        this.proteinsPerGramm = proteinsPerGramm;
        this.fatPerGramm = fatPerGramm;
        this.carbohydratesPerGramm = carbohydratesPerGramm;
    }

    public String getName() {
        return name;
    }

    public double getCaloricity() {
        return caloriesPerGramm;
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
        return "Vegetable [name=" + name + ", caloriesPerGramm=" + caloriesPerGramm + ", proteinsPerGramm="
                + proteinsPerGramm + ", fatPerGramm=" + fatPerGramm + ", carbohydratesPerGramm=" + carbohydratesPerGramm
                + "]";
    }

    @Override
    public int compareTo(Vegetable otherVegetable) {
        if (caloriesPerGramm > otherVegetable.caloriesPerGramm) {
            return 1;
        }
        if (caloriesPerGramm < otherVegetable.caloriesPerGramm) {
            return -1;
        }
        return 0;
    }    
       
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(caloriesPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(carbohydratesPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(fatPerGramm);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        temp = Double.doubleToLongBits(proteinsPerGramm);
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
        Vegetable other = (Vegetable) obj;
        if (Double.doubleToLongBits(caloriesPerGramm) != Double.doubleToLongBits(other.caloriesPerGramm))
            return false;
        if (Double.doubleToLongBits(carbohydratesPerGramm) != Double.doubleToLongBits(other.carbohydratesPerGramm))
            return false;
        if (Double.doubleToLongBits(fatPerGramm) != Double.doubleToLongBits(other.fatPerGramm))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
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
