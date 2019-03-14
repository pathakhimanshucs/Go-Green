package objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class VegetarianMealResponse {
    public boolean addVegetarianMealSuccess;

    public VegetarianMealResponse(){
    };

    public boolean isAddVegetarianMealSuccess() { return addVegetarianMealSuccess; }
    public void setAddVegetarianMealSuccess(boolean addVegetarianMealSuccess) { this.addVegetarianMealSuccess = addVegetarianMealSuccess; }
}
