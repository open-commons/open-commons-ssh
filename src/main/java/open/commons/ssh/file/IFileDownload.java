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
import java.io.OutputStream;
import java.nio.file.Path;

import open.commons.Result;

/**
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IFileDownload {

    /***
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 파일
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws FileNotFoundException
     */
    Result<Boolean> download(String remoteFilepath, File localfile) throws IOException;

    /**
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 파일
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws FileNotFoundException
     */
    Result<Boolean> download(String remoteFilepath, File localfile, int connectTimeout) throws IOException;

    /***
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 데이터
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> download(String remoteFilepath, OutputStream localfile);

    /**
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 데이터
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> download(String remoteFilepath, OutputStream localfile, int connectTimeout);

    /***
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 파일
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String remoteFilepath, Path localfile) throws IOException;

    /**
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 파일
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String remoteFilepath, Path localfile, int connectTimeout) throws IOException;

    /***
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 파일 경로 (절대경로)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String remoteFilepath, String localfile) throws IOException;

    /**
     * 원격경로에 있는 데이터를 지정한 로컬 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @param remoteFilepath
     *            서버 저장 경로 (절대경로)
     * @param localfile
     *            업로드할 파일 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위, ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String remoteFilepath, String localfile, int connectTimeout) throws IOException;

}