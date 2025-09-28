package Test;
abstract class TaxCalculator {
    protected double income;

    public TaxCalculator(double income) {
        this.income = income;
    }
    public abstract double calculateTax();
    public double afterTaxIncome() {
        return income - calculateTax();
    }
}
class FlatTaxCalculator extends TaxCalculator {
    private static final double RATE = 0.15;
    public FlatTaxCalculator(double income) {super(income);}
    public double calculateTax() {
        return income * RATE;
    }
}

public class TaxApp {
    static class BracketTaxCalculator extends TaxCalculator {
        public BracketTaxCalculator(double income) {
            super(income);
        }
        public double calculateTax() {
            if (income <= 10000) {return income * 0.10;} 
            else if (income <= 50000) { return (10000 * 0.10) + ((income - 10000) * 0.20);}
            else { return (10000 * 0.10) + (40000 * 0.20) + ((income - 50000) * 0.30);}
        }
    }

    public static void main(String[] args) {
        double income = 60000;

        TaxCalculator flat = new FlatTaxCalculator(income);
        double flatTax = flat.calculateTax();
        double flatAfterTax = flat.afterTaxIncome();

        TaxCalculator bracket = new BracketTaxCalculator(income);
        double bracketTax = bracket.calculateTax();
        double bracketAfterTax = bracket.afterTaxIncome();
    }
}
