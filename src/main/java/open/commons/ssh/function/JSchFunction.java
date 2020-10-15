/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 15. 오후 4:53:11
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.function;

import java.util.Objects;
import java.util.function.Function;

import com.jcraft.jsch.JSchException;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose functional method is {@link #apply(Object)}.
 *
 * @param <T>
 *            the type of the input to the function
 * @param <R>
 *            the type of the result of the function
 *
 * @revision_history : Park_Jun_Hong_(fafanmama_at_naver_com), 2019. 3. 28., 1.0
 * 
 * @since 2020. 10. 15.
 * @version 0.1.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 * @see Function
 */
@FunctionalInterface
public interface JSchFunction<T, R> {
    /**
     * Returns a composed function that first applies this function to its input, and then applies the {@code after}
     * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
     * composed function.
     *
     * @param <V>
     *            the type of output of the {@code after} function, and of the composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the {@code after} function
     * @throws NullPointerException
     *             if after is null
     * 
     * @since 2020. 10. 15
     * @see 0.1.0
     */
    default <V> JSchFunction<T, V> andThen(JSchFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t
     *            the first function argument
     * @return the function result
     * 
     * @since 2020. 10. 15
     * @see 0.1.0
     */
    R apply(T t) throws JSchException;

}
