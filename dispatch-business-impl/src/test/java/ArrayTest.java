import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/21
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class ArrayTest {

    String data = "123";

    public class Inner{
        public String getOuterData(){
            return ArrayTest.this.data;
        }
    }


    public static void main(String[] args) {
        String[] strs = {"java8", "is", "easy", "to", "use"};

        List<String> distinctStrs = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctStrs.toString());
    }


    public static void isPrime(long number){
        boolean isExecuteLoop = false;
        for(long i = 2 ; i<=number; i++) {
            Predicate<Long> testPrime = isPrimeNumber ->  number % isPrimeNumber==0 && isPrimeNumber != number;
            if (testPrime.test(i)) {
//                isPrime(number+1);
                System.out.println("is not Prime:" + number);
                isExecuteLoop = true;
                break;
            }
            if(i==number) {
                System.out.println("is Prime:" + number);
            }
        }
        if(isExecuteLoop)
            isPrime(number+1);

    }

}
