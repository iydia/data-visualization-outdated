package cpt;

public class Data extends DataCollection{
    private int intYear;
    private int intPopulationGrowth;

    /**
     * Constructor for Data, represents each data record
     * @param intTheYear the year
     * @param intThePopulationGrowth the annual population growth
     */
    public Data(int intTheYear, int intThePopulationGrowth) {
        this.intYear = intTheYear;
        this.intPopulationGrowth = intThePopulationGrowth;
    }

    /**
     * Getter method for the year
     * @return the year
     */
    public int getYear(){
        return intYear;
    }

    /**
     * Getter method for the annual population growth
     * @return annual population growth
     */
    public int getPopulationGrowth(){
        return intPopulationGrowth;
    }

    /**
     * String representation of Data
     * @return String representation of comma separated elements
     */
    public String toString(){
        return this.intYear + "," + this.intPopulationGrowth;
    }
}
