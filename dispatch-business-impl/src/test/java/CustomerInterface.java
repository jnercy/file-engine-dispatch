/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/11/21
 * Time: 14:55
 * To change this template use File | Settings | File Templates.
 */
@FunctionalInterface
public interface CustomerInterface<T, R> {

    R app(T t);



    default void andThen(T t){
        System.out.println(t);
    }
}
