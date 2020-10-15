/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 14. 오후 9:09:54
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

import open.commons.Result;

/**
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IFileUpload {

    /***
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> upload(byte[] source, String destination);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> upload(byte[] source, String destination, int connectTimeout);

    /***
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws FileNotFoundException
     */
    Result<Boolean> upload(File source, String destination) throws IOException;

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws FileNotFoundException
     */
    Result<Boolean> upload(File source, String destination, int connectTimeout) throws IOException;

    /***
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> upload(InputStream source, String destination);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> upload(InputStream source, String destination, int connectTimeout);

    /***
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> upload(Path source, String destination) throws IOException;

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> upload(Path source, String destination, int connectTimeout) throws IOException;

    /***
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> upload(String source, String destination) throws IOException;

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> upload(String source, String destination, int connectTimeout) throws IOException;

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            문자열 데이터. (파일 경로가 아님).
     * @param destination
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> uploadString(String source, String destination);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            문자열 데이터. (파일 경로 아님)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param charset
     *            문자열 캐릭터 셋
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> uploadString(String source, String destination, Charset charset);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            문자열 데이터. (파일 경로 아님)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> uploadString(String source, String destination, int connectTimeout);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            문자열 데이터. (파일 경로 아님)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     * @param charset
     *            문자열 캐릭터 셋
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> uploadString(String source, String destination, int connectTimeout, Charset charset);

}