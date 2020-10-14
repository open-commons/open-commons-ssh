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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.Result;
import open.commons.ssh.ChannelType;
import open.commons.ssh.SshConnection;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpProgressMonitor;

/**
 * 파일 전송 기능을 제공하는 클래스.
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class FileTransfer implements AutoCloseable, IFileUpload, IFileDownload {

    /** 연결 제한 시간. (단위, ms) */
    private static final int DEFAULT_CONNECT_TIMEOUT = 5000;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /** SSH 연결 제공 객체 */
    private final SshConnection ssh;

    /** 내부 공용 {@link Session} */
    private Session session;

    /** 파일 전송 진행 모니터링 객체 */
    private SftpProgressMonitor progressMonitor;

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
    public void close() {
        if (this.session != null) {
            this.session.disconnect();
        }
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, File localfile) throws IOException {
        return download(remoteFilepath, localfile, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, int)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, File localfile, int connectTimeout) throws IOException {
        return download(remoteFilepath, Paths.get(localfile.toURI()), connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, OutputStream localfile) {
        return download(remoteFilepath, localfile, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream, int)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, OutputStream localfile, int connectTimeout) {
        ChannelSftp sftp = null;
        try {
            Session session = getSession();

            sftp = ssh.openChannel(session, ChannelType.SFTP);
            sftp.connect(connectTimeout);

            sftp.get(remoteFilepath, localfile, this.progressMonitor, ChannelSftp.OVERWRITE, 0);

            return new Result<>(true, true);
        } catch (Exception e) {
            logger.error("파일 업로드를 실패하였습니다. con={}, remoteFilepath={}, 원인={}", this.ssh, remoteFilepath, e.getMessage());
            return new Result<Boolean>().setMessage("파일 업로드를 실패하였습니다. 원인=%s", e.getMessage());
        } finally {
            if (sftp != null) {
                sftp.disconnect();
            }
        }
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, Path localfile) throws IOException {
        return download(remoteFilepath, localfile, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, int)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, Path localfile, int connectTimeout) throws IOException {
        return download(remoteFilepath, Files.newOutputStream(localfile, StandardOpenOption.CREATE), connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, String localfile) throws IOException {
        return download(remoteFilepath, localfile, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> download(String remoteFilepath, String localfile, int connectTimeout) throws IOException {
        return download(remoteFilepath, Paths.get(localfile), connectTimeout);
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
     * @return the progressMonitor
     *
     * @since 2020. 10. 14.
     * 
     * @see #progressMonitor
     */
    public SftpProgressMonitor getProgressMonitor() {
        return progressMonitor;
    }

    /**
     * 내부 {@link Session} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private Session getSession() throws JSchException {
        ReentrantLock lock = new ReentrantLock(true);
        try {
            lock.lock();

            if (this.session == null || !this.session.isConnected()) {
                this.session = this.ssh.createSession();
            }

            this.session.connect(DEFAULT_CONNECT_TIMEOUT);

            return this.session;
        } finally {
            lock.unlock();
        }
    }

    /**
     * SSH 연결 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     * 
     * @return the ssh
     *
     * @since 2020. 10. 14.
     * 
     * @see #ssh
     */
    public SshConnection getSsh() {
        return ssh;
    }

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
     * @param progressMonitor
     *            the progressMonitor to set
     *
     * @since 2020. 10. 14.
     * 
     * @see #progressMonitor
     */
    public void setProgressMonitor(SftpProgressMonitor progressMonitor) {
        this.progressMonitor = progressMonitor;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(byte[], java.lang.String)
     */
    @Override
    public Result<Boolean> upload(byte[] content, String remoteFilepath) {
        return upload(content, remoteFilepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(byte[], java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(byte[] content, String remoteFilepath, int connectTimeout) {
        return upload(new ByteArrayInputStream(content), remoteFilepath, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.File, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(File file, String remoteFilepath) throws IOException {
        return upload(file, remoteFilepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.File, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(File file, String remoteFilepath, int connectTimeout) throws IOException {
        return upload(Files.newInputStream(Paths.get(file.toURI()), StandardOpenOption.READ), remoteFilepath, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(InputStream content, String remoteFilepath) {
        return upload(content, remoteFilepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(InputStream content, String remoteFilepath, int connectTimeout) {
        ChannelSftp sftp = null;
        try {
            Session session = getSession();

            sftp = ssh.openChannel(session, ChannelType.SFTP);
            sftp.connect(connectTimeout);

            sftp.put(content, remoteFilepath, this.progressMonitor, ChannelSftp.OVERWRITE);

            return new Result<>(true, true);
        } catch (Exception e) {
            logger.error("파일 업로드를 실패하였습니다. con={}, content={}, remoteFilepath={}, 원인={}", this.ssh, content, remoteFilepath, e.getMessage());
            return new Result<Boolean>().setMessage("파일 업로드를 실패하였습니다. 원인=%s", e.getMessage());
        } finally {
            if (sftp != null) {
                sftp.disconnect();
            }
        }
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.nio.file.Path, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(Path path, String remoteFilepath) throws IOException {
        return upload(path, remoteFilepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.nio.file.Path, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(Path path, String remoteFilepath, int connectTimeout) throws IOException {
        return upload(Files.newInputStream(path, StandardOpenOption.READ), remoteFilepath, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(String path, String remoteFilepath) throws IOException {
        return upload(path, remoteFilepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(String path, String remoteFilepath, int connectTimeout) throws IOException {
        return upload(Paths.get(path), remoteFilepath, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> uploadString(String content, String remoteFilepath) {
        return uploadString(content, remoteFilepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, java.nio.charset.Charset)
     */
    @Override
    public Result<Boolean> uploadString(String content, String remoteFilepath, Charset charset) {
        return uploadString(content, remoteFilepath, DEFAULT_CONNECT_TIMEOUT, charset);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> uploadString(String content, String remoteFilepath, int connectTimeout) {
        return uploadString(content, remoteFilepath, connectTimeout, null);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, int,
     *      java.nio.charset.Charset)
     */
    @Override
    public Result<Boolean> uploadString(String content, String remoteFilepath, int connectTimeout, Charset charset) {
        return upload(new ByteArrayInputStream(content.getBytes(charset != null ? charset : Charset.defaultCharset())), remoteFilepath, connectTimeout);
    }
}
