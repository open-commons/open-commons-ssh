/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 14. 오후 9:57:15
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.file;

import java.util.function.Consumer;
import java.util.function.Function;

import open.commons.function.Runner;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ProgressMonitorBuilder {

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param init
     * @param count
     * @param end
     * @since 2020. 10. 14.
     */
    public static SftpProgressMonitor build(Consumer<ProgressInit> init, Function<Long, Boolean> count, Runner end) {
        return new ProgressMonitor(init, count, end);
    }

    public static class ProgressInit {
        /**
         * @see SftpProgressMonitor#GET
         * @see SftpProgressMonitor#PUT
         */
        private final int op;
        private final String src;
        private final String dest;
        private final long max;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 10. 14.		박준홍			최초 작성
         * </pre>
         *
         * @param op
         * @param src
         * @param dest
         * @param max
         * @since 2020. 10. 14.
         */
        public ProgressInit(int op, String src, String dest, long max) {
            this.op = op;
            this.src = src;
            this.dest = dest;
            this.max = max;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 10. 14.		박준홍			최초 작성
         * </pre>
         * 
         * @return the dest
         *
         * @since 2020. 10. 14.
         * 
         * @see #dest
         */
        public String getDest() {
            return dest;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 10. 14.		박준홍			최초 작성
         * </pre>
         * 
         * @return the max
         *
         * @since 2020. 10. 14.
         * 
         * @see #max
         */
        public long getMax() {
            return max;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 10. 14.		박준홍			최초 작성
         * </pre>
         * 
         * @return the op
         *
         * @since 2020. 10. 14.
         * 
         * @see #op
         */
        public int getOp() {
            return op;
        }

        /**
         *
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 10. 14.		박준홍			최초 작성
         * </pre>
         * 
         * @return the src
         *
         * @since 2020. 10. 14.
         * 
         * @see #src
         */
        public String getSrc() {
            return src;
        }

        /**
         * @since 2020. 10. 14.
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("ProgressInit [op=");
            builder.append(op);
            builder.append(", src=");
            builder.append(src);
            builder.append(", dest=");
            builder.append(dest);
            builder.append(", max=");
            builder.append(max);
            builder.append("]");
            return builder.toString();
        }
    }

    private static class ProgressMonitor implements SftpProgressMonitor {

        private final Consumer<ProgressInit> init;
        private final Function<Long, Boolean> count;
        private final Runner end;

        /**
         * <br>
         * 
         * <pre>
         * [개정이력]
         *      날짜    	| 작성자	|	내용
         * ------------------------------------------
         * 2020. 10. 14.		박준홍			최초 작성
         * </pre>
         *
         * @param init
         * @param count
         * @param end
         * @since 2020. 10. 14.
         */
        public ProgressMonitor(Consumer<ProgressInit> init, Function<Long, Boolean> count, Runner end) {
            super();
            this.init = init;
            this.count = count;
            this.end = end;
        }

        /**
         * @since 2020. 10. 14.
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see com.jcraft.jsch.SftpProgressMonitor#count(long)
         */
        @Override
        public boolean count(long count) {
            return this.count.apply(count);
        }

        /**
         * @since 2020. 10. 14.
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see com.jcraft.jsch.SftpProgressMonitor#end()
         */
        @Override
        public void end() {
            this.end.run();
        }

        /**
         * @since 2020. 10. 14.
         * @author Park_Jun_Hong_(fafanmama_at_naver_com)
         *
         * @see com.jcraft.jsch.SftpProgressMonitor#init(int, java.lang.String, java.lang.String, long)
         */
        @Override
        public void init(int op, String src, String dest, long max) {
            this.init.accept(new ProgressInit(op, src, dest, max));
        }
    }
}
