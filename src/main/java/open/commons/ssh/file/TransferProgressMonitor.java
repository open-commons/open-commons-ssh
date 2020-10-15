/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 15. 오전 10:37:42
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * 
 * @since 2020. 10. 15.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class TransferProgressMonitor implements SftpProgressMonitor {
    /** 설정되지 않은 상태의 파일 크기 */
    private static final int INIT_FILE_SIZE = -1;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /** 읽어올 데이터 경로 */
    private final String source;
    /** 데이터 저장 경로 */
    private final String destination;
    /** 읽어올 파일 크기 */
    private long sourcefileSize = INIT_FILE_SIZE;
    /** 전송 형태 */
    private int op;
    /** 저장된 데이터 누적 크기 */
    private long totalCount;
    /** 저장 진행률 */
    private double rate;

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @param source
     *            읽어올 데이터 경로
     * @param destination
     *            데이터 저장 경로
     * @since 2020. 10. 15.
     */
    public TransferProgressMonitor(String source, String destination) {
        this(source, destination, 0);
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @param source
     *            읽어올 데이터 경로
     * @param destination
     *            데이터 저장 경로
     * @param sourcefileSize
     *            읽어올 데이터 크기.<br>
     *            업로드인 경우 반드시 입력을 해야 진행률을 계산할 수 있다.
     * @since 2020. 10. 15.
     */
    public TransferProgressMonitor(String source, String destination, long sourcefileSize) {
        this.source = source;
        this.destination = destination;
        this.sourcefileSize = sourcefileSize;
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see com.jcraft.jsch.SftpProgressMonitor#count(long)
     */
    @Override
    public boolean count(long count) {
        this.totalCount += count;
        this.rate = this.sourcefileSize == INIT_FILE_SIZE || this.sourcefileSize < 1 ? -1 : (double) this.totalCount / this.sourcefileSize;

        logger.debug(String.format("[%sing] %,10d / %,10d / %,10d / %.4f.", GET == this.op ? "Download" : PUT == this.op ? "Upload" : "None" //
                , count, this.totalCount, this.sourcefileSize, this.rate));

        return true;
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see com.jcraft.jsch.SftpProgressMonitor#end()
     */
    @Override
    public void end() {
        logger.info(String.format("[%sed] %,10d / %,10d / %.4f.", GET == this.op ? "Download" : PUT == this.op ? "Upload" : "None" //
            , this.totalCount, this.sourcefileSize, this.rate));
        logger.info("[Finished] {}. source={}, destination={}", GET == this.op ? "Download" : PUT == this.op ? "Upload" : "None", this.source, this.destination);
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @return the destination
     *
     * @since 2020. 10. 15.
     * 
     * @see #destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @return the rate
     *
     * @since 2020. 10. 15.
     * 
     * @see #rate
     */
    public double getRate() {
        return rate;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @return the source
     *
     * @since 2020. 10. 15.
     * 
     * @see #source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @return the sourcefileSize
     *
     * @since 2020. 10. 15.
     * 
     * @see #sourcefileSize
     */
    public long getSourcefileSize() {
        return sourcefileSize;
    }

    /**
     *
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @return the totalCount
     *
     * @since 2020. 10. 15.
     * 
     * @see #totalCount
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see com.jcraft.jsch.SftpProgressMonitor#init(int, java.lang.String, java.lang.String, long)
     */
    @Override
    public void init(int op, String src, String dest, long max) {

        logger.info("[Begin] {}. source={}, destination={}", GET == this.op ? "Download" : PUT == this.op ? "Upload" : "None", this.source, this.destination);
        
        this.op = op;
        switch (op) {
            case GET:
                this.sourcefileSize = max;
                break;
            case PUT:
                break;
            default:
                // unsupported op
                break;
        }
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TransferProgressMonitor [source=");
        builder.append(source);
        builder.append(", destination=");
        builder.append(destination);
        builder.append(", sourcefileSize=");
        builder.append(sourcefileSize);
        builder.append(", op=");
        builder.append(op);
        builder.append(", totalCount=");
        builder.append(totalCount);
        builder.append(", rate=");
        builder.append(rate);
        builder.append("]");
        return builder.toString();
    }

}
