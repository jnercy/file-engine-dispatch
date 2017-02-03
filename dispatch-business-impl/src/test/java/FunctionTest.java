import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/18
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class FunctionTest {

    public static void main(String[] args) {


        Function<Integer, String> function1 = (x) -> "test result: " + x;

        CustomerInterface<Integer, String> functionxx = (x) -> "test result: " + x;

        //标准的,有花括号, return, 分号.
        Function<String, String> function2 = (x) ->"after function1"+x;
        System.out.println(function1.apply(6));
        System.out.println(function1.andThen(function2).apply(6));
//-----------------------------------------------------------------

        Supplier<String> supplier1 = () -> "Test supplier";
        System.out.println(supplier1.get());

        //标准格式
        Supplier<Integer> supplier2 = () -> 20;
        System.out.println(supplier2.get());

//-----------------------------------------------------------------


        Consumer<String> consumer1 = System.out::print;
        Consumer<String> consumer2 = (x) ->System.out.println(" after consumer 1");
        consumer1.andThen(consumer2).accept("test consumer1");

//-----------------------------------------------------------------

        Predicate<String> predicate = (x) -> x.length()>0;
        System.out.println(predicate.test("String"));


    }
}
