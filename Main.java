import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface MyPredicate<T> { boolean test(T t); }

@FunctionalInterface
interface MyFunction<T, R> { R apply(T t); }

@FunctionalInterface
interface MyConsumer<T> { void accept(T t); }

@FunctionalInterface
interface MySupplier<T> { T get(); }

@FunctionalInterface
interface MyBiFunction<T, U, R> { R apply(T t, U u); }

public class Main {

    public static <T> List<T> myFilter(List<T> list, MyPredicate<T> p) {
        List<T> result = new ArrayList<>();
        if (list == null) return result;
        for (T item : list) if (p.test(item)) result.add(item);
        return result;
    }

    public static <T, R> List<R> myMap(List<T> list, MyFunction<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T item : list) result.add(f.apply(item));
        return result;
    }

    public static <T> void myForEach(List<T> list, MyConsumer<T> c) {
        for (T item : list) c.accept(item);
    }

    public static <T, R> R myReduce(List<T> list, R identity,
                                    MyBiFunction<R, T, R> reducer) {
        R acc = identity;
        for (T item : list) acc = reducer.apply(acc, item);
        return acc;
    }

    public static void main(String[] args) {

        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        System.out.println("Cutlər: " + myFilter(nums, n -> n % 2 == 0));

        List<String> names = List.of("Ali", "Veli", "Aysel");
        System.out.println("A ile: " + myFilter(names, s -> s.startsWith("A")));


        List<String> words = List.of("java", "lambda");
        System.out.println("Uzunluqlar: " + myMap(words, s -> s.length()));
        myForEach(words, s -> System.out.println("-> " + s));
        System.out.println("Cem: " + myReduce(List.of(1, 2, 3, 4), 0, (a, b) -> a + b));
        System.out.println("Hasil: " + myReduce(List.of(1, 2, 3, 4), 1, (a, b) -> a * b));

        MyPredicate<Integer> cut = n -> n % 2 == 0;
        MyPredicate<Integer> boyuk = n -> n > 10;
        MySupplier<String> sup = () -> "Salam, Functional Interface!";
        System.out.println("Cut(4): " + cut.test(4) + ", Boyuk(5): " + boyuk.test(5));
        System.out.println("Supplier: " + sup.get());
    }
}