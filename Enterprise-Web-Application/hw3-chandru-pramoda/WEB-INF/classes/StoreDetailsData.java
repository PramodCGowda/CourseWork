import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreDetailsData {

    public void storeData() {
        List<StoreDetails> storeLocations = new ArrayList<>();
        // Create 10 store locations in Illinois
        storeLocations.add(new StoreDetails(1, "123 Main St", "Chicago", "Illinois", 60601));
        storeLocations.add(new StoreDetails(2, "456 Elm St", "Springfield", "Illinois", 62701));
        storeLocations.add(new StoreDetails(3, "789 Oak St", "Peoria", "Illinois", 61601));
        storeLocations.add(new StoreDetails(4, "101 Pine St", "Rockford", "Illinois", 61101));
        storeLocations.add(new StoreDetails(5, "222 Maple St", "Naperville", "Illinois", 60540));
        storeLocations.add(new StoreDetails(6, "333 Birch St", "Champaign", "Illinois", 61820));
        storeLocations.add(new StoreDetails(7, "444 Cedar St", "Bloomington", "Illinois", 61701));
        storeLocations.add(new StoreDetails(8, "555 Redwood St", "Decatur", "Illinois", 62521));
        storeLocations.add(new StoreDetails(9, "666 Walnut St", "Aurora", "Illinois", 60505));
        storeLocations.add(new StoreDetails(10, "777 Oakwood St", "Evanston", "Illinois", 60201));
    }
}
