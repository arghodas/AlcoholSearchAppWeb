package alcohol.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Alcohol {

    @JsonProperty("ProName")
    private String proName;
    @JsonProperty("ProPrice")
    private String proPrice;
    @JsonProperty("Alcohol Concentrations")
    private String alcoholConcentrations;
    @JsonProperty("ProDescription")
    private String proDescription;

    public Alcohol() {
        super();
    }

    public Alcohol(String proName, String proPrice, String alcoholConcentrations, String proDescription) {
        super();
        this.proName = proName;
        this.proPrice = proPrice;
        this.alcoholConcentrations = alcoholConcentrations;
        this.proDescription = proDescription;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((proName == null) ? 0 : proName.hashCode());
        result = prime * result + ((proPrice == null) ? 0 : proPrice.hashCode());
        result = prime * result + ((alcoholConcentrations == null) ? 0 : alcoholConcentrations.hashCode());
        result = prime * result + ((proDescription == null) ? 0 : proDescription.hashCode());
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
        Alcohol other = (Alcohol) obj;
        if (proName == null) {
            if (other.proName != null)
                return false;
        } else if (!proName.equals(other.proName))
            return false;
        if (proPrice == null) {
            if (other.proPrice != null)
                return false;
        } else if (!proPrice.equals(other.proPrice))
            return false;
        if (alcoholConcentrations == null) {
            if (other.alcoholConcentrations != null)
                return false;
        } else if (!alcoholConcentrations.equals(other.alcoholConcentrations))
            return false;
        if (proDescription == null) {
            if (other.proDescription != null)
                return false;
        } else if (!proDescription.equals(other.proDescription))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Alcohol [proName=" + proName + ", proPrice=" + proPrice + ", alcoholConcentrations=" + alcoholConcentrations +", proDescription=" + proDescription + "]";
    }

    public String getproName() {
        return proName;
    }

    public void setproName(String proName) {
        this.proName = proName;
    }

    public String getproPrice() {
        return proPrice;
    }

    public void setproPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public String getalcoholConcentrations() {
        return alcoholConcentrations;
    }

    public void setalcoholConcentrations(String alcoholConcentrations) { this.alcoholConcentrations = alcoholConcentrations;}

    public String getproDescription() {
        return proDescription;
    }

    public void setproDescription(String proDescription) {
        this.proDescription = proDescription;
    }
}
