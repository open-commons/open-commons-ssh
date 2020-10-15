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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import open.commons.Result;
import open.commons.ssh.ChannelType;
import open.commons.ssh.SshClient;
import open.commons.ssh.SshConnection;
import open.commons.ssh.function.JSchFunction;
import open.commons.utils.ExceptionUtils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;

/**
 * 파일 전송 기능을 제공하는 클래스.
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class FileTransfer extends SshClient implements IFileUpload, IFileDownload {

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
     *            SSH 연결 객체
     *
     * @since 2020. 10. 14.
     */
    public FileTransfer(SshConnection ssh) {
        super(ssh);
    }

    /**
     * 저장하려는 경로의 디렉토리가 존재하는지 확인하고, 없는 경우 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @param sftp
     *            SSH 기반 SFTP 연결
     * @param filepath
     *
     * @return
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws SftpException
     */
    private boolean createParentIfNotExist(ChannelSftp sftp, String filepath) throws SftpException {
        int lastIndex = filepath.lastIndexOf('/');
        if (lastIndex == 0) {
            return true;
        }

        String parent = lastIndex < 0 ? filepath : filepath.substring(0, lastIndex);
        try {
            sftp.ls(parent);
            return true;
        } catch (SftpException e) {
            sftp.mkdir(parent);
            return false;
        }
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File)
     */
    @Override
    public Result<Boolean> download(String source, File destination) throws IOException {
        return download(source, destination, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, boolean)
     */
    @Override
    public Result<Boolean> download(String source, File destination, boolean overwrite) throws IOException {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, int)
     */
    @Override
    public Result<Boolean> download(String source, File destination, int connectTimeout) throws IOException {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, int, boolean)
     */
    @Override
    public Result<Boolean> download(String source, File destination, int connectTimeout, boolean overwrite) throws IOException {
        return download(source, Paths.get(destination.toURI()), connectTimeout, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream)
     */
    @Override
    public Result<Boolean> download(String source, OutputStream destination) {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream, int)
     */
    @Override
    public Result<Boolean> download(String source, OutputStream destination, int connectTimeout) {

        JSchFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                channel.get(source, destination, this.progressMonitor, ChannelSftp.OVERWRITE, 0);
                return new Result<>(true, true);
            } catch (SftpException e) {
                logger.error("SFTP 기능 수행 도중 에러가 발생하였습니다.", e);
                throw new JSchException("SFTP 기능 수행 도중 에러가 발생하였습니다.", e);
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("파일 다운로드를 실패하였습니다. connection={}, source={}, destination={}", this.ssh, source, destination, e);
            return new Result<Boolean>().setMessage("파일 다운로드를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path)
     */
    @Override
    public Result<Boolean> download(String source, Path destination) throws IOException {
        return download(source, destination, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, boolean)
     */
    @Override
    public Result<Boolean> download(String source, Path destination, boolean overwrite) throws IOException {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, int)
     */
    @Override
    public Result<Boolean> download(String source, Path destination, int connectTimeout) throws IOException {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, int, boolean)
     */
    @Override
    public Result<Boolean> download(String source, Path destination, int connectTimeout, boolean overwrite) throws IOException {

        // #1. 저장할 디렉토리 확인
        Path parent = destination.getParent();
        if (!Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        // #2. 저장할 파일 확인
        if (Files.exists(destination) && !overwrite) {
            throw new FileAlreadyExistsException(destination.toString());
        }
        if (Files.isDirectory(destination)) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "저장경로는 반드시 절대경로로 표기된 파일 경로이어야 합니다. destination=%s", destination);
        }
        // #3. 저장경로에 이미 파일이 존재하는 경우 삭제
        Files.deleteIfExists(destination);

        return download(source, Files.newOutputStream(destination, StandardOpenOption.CREATE), connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> download(String source, String destination) throws IOException {
        return download(source, destination, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, boolean)
     */
    @Override
    public Result<Boolean> download(String source, String destination, boolean overwrite) throws IOException {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> download(String source, String destination, int connectTimeout) throws IOException {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, int, boolean)
     */
    @Override
    public Result<Boolean> download(String source, String destination, int connectTimeout, boolean overwrite) throws IOException {
        return download(source, Paths.get(destination), connectTimeout, overwrite);
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
    public Result<Boolean> upload(byte[] source, String destination) {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(byte[], java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(byte[] source, String destination, int connectTimeout) {
        return upload(new ByteArrayInputStream(source), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.File, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(File source, String destination) throws IOException {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * @throws IOException
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.File, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(File source, String destination, int connectTimeout) throws IOException {

        return upload(Files.newInputStream(Paths.get(source.toURI()), StandardOpenOption.READ), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(InputStream source, String destination) {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(InputStream source, String destination, int connectTimeout) {
        // 절대 경로 확인
        if (!destination.startsWith("/")) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "데이터 저장경로는 절대경로('/'로 시작)이어야 합니다. destination=", destination);
        }

        JSchFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                // 저장경로 확인
                createParentIfNotExist(channel, destination);
                channel.put(source, destination, this.progressMonitor, ChannelSftp.OVERWRITE);
                return new Result<>(true, true);
            } catch (SftpException e) {
                throw new JSchException(e.getMessage(), e);
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("파일 업로드를 실패하였습니다. con={}, source={}, destination={}", this.ssh, source, destination, e);
            return new Result<Boolean>().setMessage("파일 업로드를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.nio.file.Path, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(Path source, String destination) throws IOException {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.nio.file.Path, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(Path source, String destination, int connectTimeout) throws IOException {

        if (!Files.exists(source)) {
            throw new FileNotFoundException(source.toString());
        }

        return upload(Files.newInputStream(source, StandardOpenOption.READ), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(String source, String destination) throws IOException {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(String source, String destination, int connectTimeout) throws IOException {
        return upload(Paths.get(source), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> uploadString(String source, String destination) {
        return uploadString(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, java.nio.charset.Charset)
     */
    @Override
    public Result<Boolean> uploadString(String source, String destination, Charset charset) {
        return uploadString(source, destination, DEFAULT_CONNECT_TIMEOUT, charset);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> uploadString(String source, String destination, int connectTimeout) {
        return uploadString(source, destination, connectTimeout, null);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, int,
     *      java.nio.charset.Charset)
     */
    @Override
    public Result<Boolean> uploadString(String source, String destination, int connectTimeout, Charset charset) {
        return upload(new ByteArrayInputStream(source.getBytes(charset != null ? charset : Charset.defaultCharset())), destination, connectTimeout);
    }
}
