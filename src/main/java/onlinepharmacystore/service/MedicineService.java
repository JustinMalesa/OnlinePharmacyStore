
package onlinepharmacystore.service;

/**
 *
 * @author justi
 */

import onlinepharmacystore.model.Medicine;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {

    private static final List<Medicine> medicines =
            new ArrayList<>();

    static {

        medicines.add(new Medicine(
                1, "Panado", "Pain Relief", 45.99));

        medicines.add(new Medicine(
                2, "Vitamin C", "Vitamins", 89.99));

        medicines.add(new Medicine(
                3, "Cough Syrup", "Cold & Flu", 65.50));

        medicines.add(new Medicine(
                4, "Allergy Tablets", "Allergy", 72.99));

        medicines.add(new Medicine(
                5, "Antacid", "Digestive Health", 39.99));
    }

    public synchronized List<Medicine> getAllMedicines() {
        return new ArrayList<>(medicines);
    }

    public synchronized List<Medicine> getMedicinesByCategory(
            String category) {

        if (category == null
                || category.equalsIgnoreCase("All")) {

            return new ArrayList<>(medicines);
        }

        List<Medicine> filteredMedicines =
                new ArrayList<>();

        for (Medicine medicine : medicines) {

            if (medicine.getCategory()
                    .equalsIgnoreCase(category)) {

                filteredMedicines.add(medicine);
            }
        }

        return filteredMedicines;
    }

    public synchronized void addMedicine(
            String name,
            String category,
            double price) {

        int nextId = 1;

        for (Medicine medicine : medicines) {
            if (medicine.getId() >= nextId) {
                nextId = medicine.getId() + 1;
            }
        }

        Medicine medicine = new Medicine(
                nextId,
                name,
                category,
                price
        );

        medicines.add(medicine);
    }

    public synchronized void removeMedicine(int id) {

        medicines.removeIf(medicine ->
                medicine.getId() == id);
    }
}