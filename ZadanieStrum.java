import java.util.Arrays;
import java.util.List;

public class ZadanieStrum {

    public static void main(String[] args) {
        System.out.println("Zadanie 1:");
        zadanie1();

        System.out.println("\nZadanie 2:");
        zadanie2();

        System.out.println("\nZadanie 3:");
        zadanie3();
    }

    public static void zadanie1() {
        List<Integer> liczby = Arrays.asList(20, 22, 24, 28, 30, 32);

        double srednia = liczby.stream()
            .filter(num -> num % 10 != 9 && num % 10 != 7)
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);

        System.out.println("Srednia: " + srednia); 
    }

    public static void zadanie2() {
        List<Integer> liczby = Arrays.asList(5, 8, 12, 15);

        List<Integer> wynik = liczby.stream()
            .map(num -> num * num + 9)
            .filter(num -> !String.valueOf(num).contains("9"))
            .toList();

        System.out.println("Wynik: " + wynik);
    }

    public static void zadanie3() {
        List<String> ciagi = Arrays.asList("za", "aa", "bb", "cy", "pow");

        List<String> wynik = ciagi.stream()
            .map(str -> str + "y")
            .filter(str -> !str.contains("yy"))
            .toList();

        System.out.println("Wynik: " + wynik);
    }
}
