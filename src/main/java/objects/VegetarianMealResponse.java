package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class VegetarianMealResponse {
    public boolean addVegetarianMealSuccess;

    public VegetarianMealResponse() {
    }

    public boolean isAddVegetarianMealSuccess() {
        return addVegetarianMealSuccess;
    }

    public void setAddVegetarianMealSuccess(boolean addVegetarianMealSuccess) {
        this.addVegetarianMealSuccess = addVegetarianMealSuccess;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VegetarianMealResponse that = (VegetarianMealResponse) obj;
        return addVegetarianMealSuccess == that.addVegetarianMealSuccess;
    }
}
