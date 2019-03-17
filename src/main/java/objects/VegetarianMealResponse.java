package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class VegetarianMealResponse {
    public boolean addVegetarianMealSuccess;

    public VegetarianMealResponse() {
    }

    public boolean isAddVegetarianMealSuccess() { return addVegetarianMealSuccess; }
    public void setAddVegetarianMealSuccess(boolean addVegetarianMealSuccess) { this.addVegetarianMealSuccess = addVegetarianMealSuccess; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VegetarianMealResponse that = (VegetarianMealResponse) o;
        return addVegetarianMealSuccess == that.addVegetarianMealSuccess;
    }
}
