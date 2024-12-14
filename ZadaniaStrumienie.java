import java.util.*;
import java.util.stream.*;

public class ZadaniaStrumienie {

    public static void main(String[] args) {
        zadanie1();
        zadanie2();
        zadanie3();
    }

    // Zadanie 1
    public static void zadanie1() {
        List<Integer> liczby = Arrays.asList(45, 39, 17, 25, 10, 4);
        double srednia = liczby.stream()
                .filter(x -> x % 10 != 9 && x % 10 != 7)
                .mapToInt(x -> x)
                .average()
                .orElse(0.0);

        System.out.println("Srednia: " + srednia);
    }

    // Zadanie 2
    public static void zadanie2() {
        List<Integer> liczby = Arrays.asList(3, 10, 9, 4);
        List<Integer> wynik = liczby.stream()
                .map(x -> x * x + 9)
                .filter(x -> !String.valueOf(x).contains("9"))
                .collect(Collectors.toList());

        System.out.println("Wynik: " + wynik);
    }

    // Zadanie 3
    public static void zadanie3() {
        List<String> teksty = Arrays.asList("aa", "cy", "b", "yycd", "c");
        List<String> wynik = teksty.stream()
                .map(x -> x + "y")
                .filter(x -> !x.contains("yy"))
                .collect(Collectors.toList());

        System.out.println("Wynik: " + wynik);
    }
}

