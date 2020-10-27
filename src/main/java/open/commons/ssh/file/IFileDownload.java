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
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, File destination) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param overwrite
     *            데이터 저장 경로에 이미 파일이 존재하는 경우 삭제하고 덮어쓸지 여부.<br>
     *            <code>false</code>인 경우 이미 파일이 존재할 때는 {@link FileAlreadyExistsException}을 발생시킨다.
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, File destination, boolean overwrite) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, File destination, int connectTimeout) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param overwrite
     *            데이터 저장 경로에 이미 파일이 존재하는 경우 삭제하고 덮어쓸지 여부.<br>
     *            <code>false</code>인 경우 이미 파일이 존재할 때는 {@link FileAlreadyExistsException}을 발생시킨다.
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, File destination, int connectTimeout, boolean overwrite) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> download(String source, OutputStream destination);

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    Result<Boolean> download(String source, OutputStream destination, int connectTimeout);

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, Path destination) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param overwrite
     *            데이터 저장 경로에 이미 파일이 존재하는 경우 삭제하고 덮어쓸지 여부.<br>
     *            <code>false</code>인 경우 이미 파일이 존재할 때는 {@link FileAlreadyExistsException}을 발생시킨다.
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, Path destination, boolean overwrite) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, Path destination, int connectTimeout) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param overwrite
     *            데이터 저장 경로에 이미 파일이 존재하는 경우 삭제하고 덮어쓸지 여부.<br>
     *            <code>false</code>인 경우 이미 파일이 존재할 때는 {@link FileAlreadyExistsException}을 발생시킨다.
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, Path destination, int connectTimeout, boolean overwrite) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, String destination) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param overwrite
     *            데이터 저장 경로에 이미 파일이 존재하는 경우 삭제하고 덮어쓸지 여부.<br>
     *            <code>false</code>인 경우 이미 파일이 존재할 때는 {@link FileAlreadyExistsException}을 발생시킨다.
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, String destination, boolean overwrite) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, String destination, int connectTimeout) throws IOException;

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
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param overwrite
     *            데이터 저장 경로에 이미 파일이 존재하는 경우 삭제하고 덮어쓸지 여부.<br>
     *            <code>false</code>인 경우 이미 파일이 존재할 때는 {@link FileAlreadyExistsException}을 발생시킨다.
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    Result<Boolean> download(String source, String destination, int connectTimeout, boolean overwrite) throws IOException;

}