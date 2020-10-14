/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 14. 오후 5:53:40
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.Result;
import open.commons.ssh.ChannelType;
import open.commons.ssh.SshConnection;
import open.commons.utils.IOUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

/**
 * 파일 전송 기능을 제공하는 클래스.
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class FileTransfer implements AutoCloseable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final SshConnection ssh;

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
     * @param ssh
     *            TODO
     *
     * @since 2020. 10. 14.
     */
    public FileTransfer(SshConnection ssh) {
        this.ssh = ssh;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws Exception {
        IOUtils.close(this.ssh);
    }

    /***
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            업로드할 데이터
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(byte[] content, String filepath) {
        return upload(new ByteArrayInputStream(content), filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            업로드할 데이터
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(byte[] content, String filepath, int connectTimeout) {
        return upload(new ByteArrayInputStream(content), filepath, connectTimeout);
    }

    /***
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            업로드할 데이터
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(InputStream content, String filepath) {
        return upload(content, filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            업로드할 데이터
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(InputStream content, String filepath, int connectTimeout) {
        try {
            Session session = ssh.connect();

            ChannelSftp sftp = (ChannelSftp) session.openChannel(ChannelType.SFTP.get());
            sftp.connect(connectTimeout);
            // sftp.put(src, dst, monitor, mode);

            return null;
        } catch (Exception e) {
            logger.error("파일 업로드를 실패하였습니다. con={}, content={}, filepath={}, 원인={}", this.ssh, content, filepath, e.getMessage());
            return new Result<Boolean>().setMessage("파일 업로드를 실패하였습니다. 원인=%s", e.getMessage());
        } finally {

        }
    }

    /**
     * 데이터를 지정한 경로에 저장한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            문자열 데이터. (파일 경로가 아님).
     * @param filepath
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(String content, String filepath) {
        return upload(new ByteArrayInputStream(content.getBytes()), filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            문자열 데이터. (파일 경로 아님)
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param charset
     *            문자열 캐릭터 셋
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(String content, String filepath, Charset charset) {
        return upload(new ByteArrayInputStream(content.getBytes(charset)), filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            문자열 데이터. (파일 경로 아님)
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(String content, String filepath, int connectTimeout) {
        return upload(new ByteArrayInputStream(content.getBytes()), filepath, connectTimeout);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param content
     *            문자열 데이터. (파일 경로 아님)
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @param charset
     *            문자열 캐릭터 셋
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> upload(String content, String filepath, int connectTimeout, Charset charset) {
        return upload(new ByteArrayInputStream(content.getBytes(charset)), filepath, connectTimeout);
    }

    /***
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            업로드할 파일
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws FileNotFoundException
     */
    public Result<Boolean> uploadFile(File file, String filepath) throws FileNotFoundException {
        return upload(new FileInputStream(file), filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param file
     *            업로드할 파일
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws FileNotFoundException
     */
    public Result<Boolean> uploadFile(File file, String filepath, int connectTimeout) throws FileNotFoundException {
        return upload(new FileInputStream(file), filepath, connectTimeout);
    }

    /***
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     *            업로드할 파일
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    public Result<Boolean> uploadFile(Path path, String filepath) throws IOException {
        return upload(Files.newInputStream(path, StandardOpenOption.READ), filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     *            업로드할 파일
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    public Result<Boolean> uploadFile(Path path, String filepath, int connectTimeout) throws IOException {
        return upload(Files.newInputStream(path, StandardOpenOption.READ), filepath, connectTimeout);
    }

    /***
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     *            업로드할 파일 경로 (절대경로)
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    public Result<Boolean> uploadFile(String path, String filepath) throws IOException {
        return upload(Files.newInputStream(Paths.get(path), StandardOpenOption.READ), filepath, 0);
    }

    /**
     * 데이터를 지정한 경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @param path
     *            업로드할 파일 경로 (절대경로)
     * @param filepath
     *            서버 저장 경로 (절대경로)
     * @param connectTimeout
     *            접속대기 시간 (단위, 초)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     */
    public Result<Boolean> uploadFile(String path, String filepath, int connectTimeout) throws IOException {
        return upload(Files.newInputStream(Paths.get(path), StandardOpenOption.READ), filepath, connectTimeout);
    }
}
