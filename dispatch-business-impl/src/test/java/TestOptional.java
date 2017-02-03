import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/9
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class TestOptional {
    public static void main(String[] args) {

        Optional<String> str = Optional.of("abc");

        String result = str.map(s -> s=null)
                .map(String::toLowerCase)
                .orElse("error");

        System.out.println(result);
    }
}
