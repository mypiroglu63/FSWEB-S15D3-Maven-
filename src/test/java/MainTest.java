import org.example.Main;
import org.example.WordCounter;
import org.example.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ResultAnalyzer.class)
public class MainTest {

    List<Employee> employees = new LinkedList<>();

    @BeforeEach
    void setUp() {
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(1, "Dogancan", "Kinik"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(2, "Seyyit Battal", "Arvas"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(3, "Anil", "Ensari"));
        employees.add(new Employee(4, "Burak", "Cevizli"));
        employees.add(null); // Null değer eklenmiş
    }

    @DisplayName("Employee sınıfı doğru access modifiers sahip mi")
    @Test
    public void testTaskAccessModifiers() throws NoSuchFieldException {
        Field idFields = employees.get(0).getClass().getDeclaredField("id");
        Field firstnameFields = employees.get(0).getClass().getDeclaredField("firstName");
        Field lastnameFields = employees.get(0).getClass().getDeclaredField("lastName");

        assertEquals(idFields.getModifiers(), 2);
        assertEquals(firstnameFields.getModifiers(), 2);
        assertEquals(lastnameFields.getModifiers(), 2);
    }

    @DisplayName("findDuplicates method doğru çalışıyor mu?")
    @Test
    public void testFindDuplicatesMethod() {
        List<Employee> list = Main.findDuplicates(employees);
        assertEquals(list.size(), 3);
        assertEquals(list.get(0).getFirstName(), "Dogancan");
    }

    @DisplayName("findUniques method doğru çalışıyor mu?")
    @Test
    public void testFindUniquesMethod() {
        Map<Integer, Employee> map = Main.findUniques(employees);
        assertEquals(map.size(), 1);  // Sadece 1 unique eleman olmalı
        assertEquals(map.get(4).getFirstName(), "Burak");
    }

    @DisplayName("removeDuplicates method doğru çalışıyor mu?")
    @Test
    public void testRemoveMethod() {
        List<Employee> list = Main.removeDuplicates(employees);
        System.out.println(list);
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getFirstName(), "Burak");
    }

    @DisplayName("calculatedWord method doğru çalışıyor mu?")
    @Test
    public void testCalculateWordMethod() {
        String text = "When the offensive resumed, the Turks received their first victory when the Greeks encountered stiff resistance in the battles of First and Second İnönü," +
                " due to İsmet Pasha's organization of an irregular militia into a regular army." +
                " The two victories led to Allied proposals to amend the Treaty of Sèvres where both Ankara and Istanbul were represented, but Greece refused." +
                " With the conclusion of the Southern and Eastern fronts, Ankara was able to concentrate more forces on the West against the Greeks." +
                " They also began to receive support from Soviet Union, as well as France and Italy, who sought to check British influence in the Near East.\n" +
                " June–July 1921 saw heavy fighting in the Battle of Kütahya-Eskişehir. While it was an eventual Greek victory, the Turkish army withdrew in good order to the Sakarya river, their last line of defence." +
                " Mustafa Kemal Pasha replaced İsmet Pasha after the defeat as commander in chief as well as his political duties." +
                " The decision was made in the Greek military command to march on the nationalist capital of Ankara to force Mustafa Kemal to the negotiating table." +
                " For 21 days, the Turks and Greeks fought a pitched battle at the Sakarya river, which ended in Greek withdrawal." +
                " Almost of year of stalemate without much fighting followed, during which Greek moral and discipline faltered while Turkish strength increased." +
                " French and Italian forces evacuated from Anatolia. The Allies offered an armistice to the Turks, which Mustafa Kemal refused.";

        Map<String, Integer> map = WordCounter.calculatedWord(text);
        assertEquals(map.get("which"), 3);
        assertEquals(map.get("turkish"), 2);
        assertEquals(map.get("mustafa"), 3);
        assertEquals(map.get("kemal"), 3);
    }
}
