/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77@gmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 14. 오후 5:53:40
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
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
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import open.commons.core.Result;
import open.commons.core.utils.ExceptionUtils;
import open.commons.core.utils.FileUtils;
import open.commons.core.utils.IOUtils;
import open.commons.core.utils.NumberUtils;
import open.commons.core.utils.StringUtils;
import open.commons.ssh.ChannelType;
import open.commons.ssh.SshClient;
import open.commons.ssh.SshConnection;
import open.commons.ssh.function.JSchFunction;
import open.commons.ssh.function.SftpFunction;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * 파일 전송 기능을 제공하는 클래스.
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class FileTransfer extends SshClient implements IFileUpload, IFileDownload, IFile {

    /** 파일 전송 진행 모니터링 객체 */
    private TransferProgressMonitor progressMonitor;

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
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            작업 대상 경로
     * @param destination
     *            작업 완료 경로
     * @param connectTimeout
     *            접속 대기 시간 (단위: ms)
     * @param overwrite
     *            덮어쓰기 여부
     * @param isCopy
     *            복사 여부
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private Result<Boolean> changeLocation(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout, boolean overwrite, final boolean isCopy) {

        // #1. source 파일 존재 여부 검증
        // #1-1. source 데이터의 마지막 경로에 wildcard(*)가 포함된 경우 처리
        String lastpath = FileUtils.getFileName(source);
        if (StringUtils.isNullOrEmptyString(lastpath)) {
            return Result.error("%s할 파일/디렉토리 정보(%s)가 올바르지 않습니다.", isCopy ? "복사" : "이동", source);
        }

        FileType srcFileType = null;
        if (lastpath.contains("*")) {
            srcFileType = FileType.WILDCARD;
        } else
        // #1-2. 일반적인 파일 경로 검증.
        {
            Result<FileType> resultSrcFileType = getFileType(source, connectTimeout);
            srcFileType = resultSrcFileType.getData();
            if (resultSrcFileType.isError() || (srcFileType != FileType.REGULAR_FILE && srcFileType != FileType.DIRECTORY)) {
                return Result.error("파일유형=%s, 원인=%s", srcFileType, resultSrcFileType.getMessage());
            }
        }

        // #2. destination 타입 확인
        Result<FileType> resultDstFileType = getFileType(destination, connectTimeout);
        FileType dstFileType = resultDstFileType.getData();
        if (resultDstFileType.isError() || (dstFileType != FileType.REGULAR_FILE && dstFileType != FileType.DIRECTORY)) {
            return Result.error("파일유형=%s, 원인=%s", dstFileType, resultDstFileType.getMessage());
        }

        ArrayList<String> bufCmd = new ArrayList<>();
        bufCmd.add(isCopy ? "cp" : "mv");
        bufCmd.add("-v");

        // #3. source가 디렉토리인 경우
        if (srcFileType == FileType.DIRECTORY || srcFileType == FileType.WILDCARD) {
            // destination의 경로가 '파일'인 경우 불가.
            if (dstFileType == FileType.REGULAR_FILE) {
                return Result.error("디렉토리(%s)를 파일(%s)로 위치를 변경할 수는 없습니다.", source, destination);
            }

            if (isCopy) {
                // 복사할 대상이 디렉토리/여러 개이기 때문에
                bufCmd.add("-r");
            }
        }

        // 대상 경로의 상위 디렉토리 생성
        try {
            createParentIfNotExist(openChannel(ChannelType.SFTP, connectTimeout, true), destination);
        } catch (SftpException | JSchException e) {
            return Result.error("'%s' 상위 디렉토리 생성 실패. 원인=%s", destination, e.getMessage());
        }

        // 복사할 대상이 디렉토리이기 때문에
        bufCmd.add(source);
        bufCmd.add(destination);

        JSchFunction<ChannelExec, Result<Boolean>> action = channel -> {
            // 명령어 생성/설정
            String command = String.join(" ", bufCmd.toArray(new String[0]));
            channel.setCommand(command);
            logger.info("command={}", command);

            // 명령어 실행
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect(connectTimeout);
                List<String> resultStop = IOUtils.readLines(in);

                resultStop.forEach(result -> {
                    logger.trace("{}", result);
                });

                return new Result<Boolean>(true, true);
            } catch (IOException e) {
                throw new JSchException(String.format("[실패] %s, %s -> %s", isCopy ? "이동" : "복사", source, destination));
            }
        };

        return executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            String errMsg = String.format("%s를 %s로 %s 도중 에러가 발생하였습니다.", source, destination, isCopy ? "이동" : "복사");
            logger.error(errMsg, e);
            return new Result<Boolean>().setMessage(errMsg);
        });
    }

    /**
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#chmodOtcalMode(java.lang.String, int)
     */
    @Override
    public Result<LsEntry> chmodOtcalMode(@NotBlank String filepath, int permission) {
        return chmodOtcalMode(filepath, permission, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#chmodOtcalMode(java.lang.String, int, int)
     */
    @Override
    public Result<LsEntry> chmodOtcalMode(@NotBlank String filepath, int permission, @Min(1) int connectTimeout) {
        SftpFunction<ChannelSftp, Result<LsEntry>> action = channel -> {
            try {
                channel.chmod(permission, filepath);
                Result<List<LsEntry>> lsResult = list(filepath, connectTimeout);
                if (!lsResult.getResult() || lsResult.getData().size() < 1) {
                    return new Result<LsEntry>().setMessage(lsResult.getMessage());
                }
                return new Result<>(lsResult.getData().get(0), true);
            } catch (SftpException e) {
                throw e;
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            updateProgressState(true,
                    String.format("파일/디렉토리 권한 설정을 실패하였습니다. connection=%s, filepath=%s, mod=%s, connect-timeout=%,d", this.ssh, filepath, permission, connectTimeout));

            logger.error("파일/디렉토리 권한 설정을 실패하였습니다. connection={}, filepath={}, mod={}, connect-timeout={}", this.ssh, filepath, permission,
                    NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<LsEntry>().setMessage("파일/디렉토리 권한 설정을 실패하였습니다. connection=%s, filepath=%s, mod=%s, 원인=%s", this.ssh, filepath, permission, e.getMessage());
        });
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#copy(java.lang.String, java.lang.String)
     * @see #copy(String, String, int, boolean)
     */
    @Override
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination) throws IOException {
        return copy(source, destination, DEFAULT_CONNECT_TIMEOUT, false);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#copy(java.lang.String, java.lang.String, boolean)
     * @see #copy(String, String, int, boolean)
     */
    @Override
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination, boolean overwrite) throws IOException {
        return copy(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#copy(java.lang.String, java.lang.String, int)
     * @see #copy(String, String, int, boolean)
     */
    @Override
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination, int connectTimeout) throws IOException {
        return copy(source, destination, connectTimeout, false);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#copy(java.lang.String, java.lang.String, int, boolean)
     */
    @Override
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination, int connectTimeout, boolean overwrite) throws IOException {
        logger.debug("[copy] source={}, destination={}, connection-timeout={}ms, overwrite={}", source, destination, connectTimeout, overwrite);
        return changeLocation(source, destination, connectTimeout, overwrite, true);
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
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws SftpException
     */
    private void createParentIfNotExist(@NotNull ChannelSftp sftp, @NotBlank String filepath) throws SftpException {
        int lastIndex = filepath.lastIndexOf('/');
        if (lastIndex == 0) {
            return;
        }

        mkdirs(filepath.substring(0, lastIndex));
    }

    /**
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#delete(java.lang.String)
     */
    @Override
    public Result<Boolean> delete(@NotBlank String filepath) {
        return delete(filepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#delete(java.lang.String, int)
     */
    @Override
    public Result<Boolean> delete(@NotBlank String filepath, @Min(1) int connectTimeout) {
        SftpFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                channel.rm(filepath);
                return new Result<>(true, true);
            } catch (SftpException e) {
                if (ExceptionUtils.startsWithIgnoreCase(e, "no such file")) {
                    return new Result<>(true, true).setMessage(e.getMessage());
                } else {
                    throw e;
                }
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("파일 삭제를 실패하였습니다. connection={}, filepath={}, connect-timeout={}", this.ssh, filepath, NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<Boolean>().setMessage("파일 삭제를 실패하였습니다. connection=%s, filepath=%s, 원인=%s", this.ssh, filepath, e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#deleteDir(java.lang.String)
     */
    @Override
    public Result<Boolean> deleteDir(@NotBlank String filepath) {
        return deleteDir(filepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#deleteDir(java.lang.String, int)
     */
    @Override
    public Result<Boolean> deleteDir(@NotBlank String filepath, @Min(1) int connectTimeout) {
        SftpFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                channel.rmdir(filepath);
                return new Result<>(true, true);
            } catch (SftpException e) {
                if (ExceptionUtils.startsWithIgnoreCase(e, "no such file")) {
                    return new Result<>(true, true).setMessage(e.getMessage());
                } else {
                    throw e;
                }
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("디렉토리 삭제를 실패하였습니다. connection={}, filepath={}, connect-timeout={}", this.ssh, filepath, NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<Boolean>().setMessage("디렉토리 삭제를 실패하였습니다. connection=%s, filepath=%s, 원인=%s", this.ssh, filepath, e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull File destination) throws IOException {
        return download(source, destination, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull File destination, boolean overwrite) throws IOException {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, int)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull File destination, @Min(1) int connectTimeout) throws IOException {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.File, int, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull File destination, @Min(1) int connectTimeout, boolean overwrite) throws IOException {
        return download(source, Paths.get(destination.toURI()), connectTimeout, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination) {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2025. 7. 23.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination, boolean autoClose) {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, autoClose);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream, int)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination, @Min(1) int connectTimeout) {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2025. 7. 23.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.io.OutputStream, int, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination, @Min(1) int connectTimeout, boolean autoClose) {
        SftpFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                channel.get(source, destination, this.progressMonitor, ChannelSftp.OVERWRITE, 0);
                return new Result<>(true, true);
            } catch (SftpException e) {
                throw e;
            } finally {
                if (autoClose) {
                    // PATCH [2020. 12. 24.]: 자원 해제 | Park_Jun_Hong_(parkjunhong77@gmail.com)
                    IOUtils.close(destination);
                }
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            updateProgressState(true,
                    String.format("파일 다운로드를 실패하였습니다. connection=%s, source=%s, destination=%s, connect-timeout=%,d", this.ssh, source, destination, connectTimeout));

            logger.error("파일 다운로드를 실패하였습니다. connection={}, source={}, destination={}, connect-timeout={}", this.ssh, source, destination,
                    NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<Boolean>().setMessage("파일 다운로드를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull Path destination) throws IOException {
        return download(source, destination, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull Path destination, boolean overwrite) throws IOException {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, int)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull Path destination, @Min(1) int connectTimeout) throws IOException {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.nio.file.Path, int, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotNull Path destination, @Min(1) int connectTimeout, boolean overwrite) throws IOException {

        // #1. 저장할 디렉토리 확인
        Path parent = destination.getParent();
        if (parent == null) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "잘못된 파일경로 입니다. destination=%s", destination.toString());
        }

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotBlank String destination) throws IOException {
        return download(source, destination, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotBlank String destination, boolean overwrite) throws IOException {
        return download(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout) throws IOException {
        return download(source, destination, connectTimeout, true);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileDownload#download(java.lang.String, java.lang.String, int, boolean)
     */
    @Override
    public Result<Boolean> download(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout, boolean overwrite) throws IOException {
        return download(source, Paths.get(destination), connectTimeout, overwrite);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#getFileType(java.lang.String)
     */
    @Override
    public Result<FileType> getFileType(@NotBlank String pathname) {
        return getFileType(pathname, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#getFileType(java.lang.String, int)
     */
    @Override
    public Result<FileType> getFileType(@NotBlank String pathname, @Min(1) int connectTimeout) {

        SftpFunction<ChannelSftp, Result<FileType>> action = channel -> {
            try {
                SftpATTRS attrs = channel.stat(pathname);
                return new Result<FileType>(FileType.get(attrs.getFlags(), attrs.getPermissions()), true);
            } catch (SftpException e) {
                if (ExceptionUtils.startsWithIgnoreCase(e, "no such file")) {
                    return Result.success(FileType.NULL).setMessage(e.getMessage());
                } else {
                    throw e;
                }
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("파일 유형 조회를 실패하였습니다. connection={}, filepath={}, connect-timeout={}", this.ssh, pathname, NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<FileType>().setMessage("파일 유형 조회를 실패하였습니다. 원인=%s", e.getMessage());
        });
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
    public TransferProgressMonitor getProgressMonitor() {
        return progressMonitor;
    }

    /**
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#list(java.lang.String)
     */
    @Override
    public Result<List<LsEntry>> list(@NotBlank String filepath) {
        return list(filepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#list(java.lang.String, int)
     */
    @Override
    public Result<List<LsEntry>> list(@NotBlank String filepath, @Min(1) int connectTimeout) {

        SftpFunction<ChannelSftp, Result<List<LsEntry>>> action = channel -> {
            try {
                ListFile lsResult = new ListFile();
                channel.ls(filepath, lsResult);
                return new Result<List<LsEntry>>(lsResult.getEntries(), true);
            } catch (SftpException e) {
                if (e.getMessage() != null && e.getMessage().contains("No such file")) {
                    return new Result<List<LsEntry>>(new ArrayList<>(), true).setMessage(e.getMessage());
                }
                throw e;
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("파일/디렉토리 조회를 실패하였습니다. connection={}, filepath={}, connect-timeout={}", this.ssh, filepath, NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<List<LsEntry>>().setMessage("파일/디렉토리 조회를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#mkdirs(java.lang.String)
     */
    @Override
    public Result<Boolean> mkdirs(@NotBlank String directory) {
        return mkdirs(directory, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#mkdirs(java.lang.String, int)
     */
    @Override
    public Result<Boolean> mkdirs(@NotBlank String directory, @Min(1) int connectTimeout) {

        SftpFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                String[] dirs = directory.split("/");
                StringBuffer path = new StringBuffer();
                for (String dir : dirs) {
                    path.append('/');
                    try {
                        path.append(dir);
                        channel.ls(path.toString());
                    } catch (SftpException e) {
                        channel.mkdir(path.toString());
                    }
                }
                return new Result<>(true, true);
            } catch (SftpException e) {
                throw e;
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            logger.error("디렉토리 생성 도중 에러가 발생하였습니다. connnection={}, directory={}, connect-timeout={}", this.ssh, directory, NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<Boolean>().setMessage("디렉토리 생성 도중 에러가 발생하였습니다. connection=%s, directory=%s, connect-timeout=%,d, 원인=%s", this.ssh, directory, connectTimeout,
                    e.getMessage());
        });
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#move(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination) throws IOException {
        return move(source, destination, DEFAULT_CONNECT_TIMEOUT, false);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#move(java.lang.String, java.lang.String, boolean)
     */
    @Override
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination, boolean overwrite) throws IOException {
        return move(source, destination, DEFAULT_CONNECT_TIMEOUT, overwrite);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#move(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination, int connectTimeout) throws IOException {
        return move(source, destination, connectTimeout, false);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#move(java.lang.String, java.lang.String, int, boolean)
     */
    @Override
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination, int connectTimeout, boolean overwrite) throws IOException {
        logger.debug("[move] source={}, destination={}, connection-timeout={}ms, overwrite={}", source, destination, connectTimeout, overwrite);
        return changeLocation(source, destination, connectTimeout, overwrite, false);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#rm(java.lang.String)
     */
    @Override
    public Result<Boolean> rm(@NotBlank String filepath) {
        return rm(filepath, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFile#rm(java.lang.String, int)
     */
    @Override
    public Result<Boolean> rm(@NotBlank String pathname, @Min(1) int connectTimeout) {

        // #1. source 파일 존재 여부 검증
        // #1-1. source 데이터의 마지막 경로에 wildcard(*)가 포함된 경우 처리
        String lastpath = FileUtils.getFileName(pathname);
        if (StringUtils.isNullOrEmptyString(lastpath)) {
            return Result.error("삭제할 파일/디렉토리 정보(%s)가 올바르지 않습니다.", pathname);
        }

        FileType fileType = null;
        if (lastpath.contains("*")) {
            fileType = FileType.WILDCARD;
        } else
        // #1-2. 일반적인 파일 경로 검증.
        {
            Result<FileType> resultFileType = getFileType(pathname, connectTimeout);
            if (resultFileType.isError()) {
                return Result.error(resultFileType.getMessage());
            }
            fileType = resultFileType.getData();
        }

        // 명령어 생성/설정
        ArrayList<String> bufCmd = new ArrayList<>();
        switch (fileType) {
            // 존재하지 않는 경우
            case NULL:
                return Result.success(true).setMessage("%s이 존재하지 않습니다.", pathname);
            case WILDCARD:
            case DIRECTORY:
                bufCmd.add(" -r");
            case REGULAR_FILE:
                bufCmd.add("-v");
                bufCmd.add(pathname);
                bufCmd.add(0, "rm");
                break;
            default:
                return Result.error("%s (%s)이 파일 또는 디렉토리가 아닙니다.", pathname, fileType.getCode());

        }

        JSchFunction<ChannelExec, Result<Boolean>> action = channel -> {
            String command = String.join(" ", bufCmd.toArray(new String[0]));
            channel.setCommand(command);
            logger.info("command={}", command);

            // 명령어 실행
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect(connectTimeout);
                List<String> resultStop = IOUtils.readLines(in);

                resultStop.forEach(result -> {
                    logger.trace("{}", result);
                });

                return new Result<Boolean>(true, true);
            } catch (IOException e) {
                throw new JSchException(e.getMessage(), e);
            }
        };

        final boolean isFile = FileType.REGULAR_FILE == fileType;

        return executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            String errMsg = String.format("%s(%s) 삭제 도중 에러가 발생하였습니다. connnection=%s, connect-timeout=%s, 원인=%s", isFile ? "파일" : "디렉토리", pathname, this.ssh,
                    NumberUtils.INT_TO_STR.apply(connectTimeout), e.getMessage());
            logger.error(errMsg, e);
            return new Result<Boolean>().setMessage(errMsg);
        });
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
    public void setProgressMonitor(TransferProgressMonitor progressMonitor) {
        this.progressMonitor = progressMonitor;
    }

    /**
     * 파일 전송 상황을 갱신한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 19.		박준홍			최초 작성
     * </pre>
     *
     * @param isError
     *            에러 여부
     * @param message
     *            메시지
     *
     * @since 2020. 10. 19.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    private void updateProgressState(boolean isError, @NotBlank String message) {
        if (this.progressMonitor == null) {
            return;
        }

        this.progressMonitor.setStatus(isError);
        this.progressMonitor.setMessage(message);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(byte[], java.lang.String)
     */
    @Override
    public Result<Boolean> upload(@NotNull byte[] source, @NotBlank String destination) {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(byte[], java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(@NotNull byte[] source, @NotBlank String destination, @Min(1) int connectTimeout) {
        return upload(new ByteArrayInputStream(source), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.File, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(@NotNull File source, @NotBlank String destination) throws IOException {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.File, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(@NotNull File source, @NotBlank String destination, @Min(1) int connectTimeout) throws IOException {
        return upload(Files.newInputStream(Paths.get(source.toURI()), StandardOpenOption.READ), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination) {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2025. 7. 23.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String, boolean)
     */
    @Override
    public Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination, boolean autoClose) {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT, autoClose);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination, @Min(1) int connectTimeout) {
        return upload(source, destination, connectTimeout, true);
    }

    /**
     * @since 2025. 7. 23.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.io.InputStream, java.lang.String, int, boolean)
     */
    @Override
    public Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination, int connectTimeout, boolean autoClose) {
        // 절대 경로 확인
        if (!destination.startsWith("/")) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "데이터 저장경로는 절대경로('/'로 시작)이어야 합니다. destination=%s", destination);
        }

        SftpFunction<ChannelSftp, Result<Boolean>> action = channel -> {
            try {
                // 저장경로 확인
                createParentIfNotExist(channel, destination);
                channel.put(source, destination, this.progressMonitor, ChannelSftp.OVERWRITE);
                return new Result<>(true, true);
            } catch (SftpException e) {
                throw e;
            } finally {
                if (autoClose) {
                    // PATCH [2020. 12. 24.]: 자원 해제 | Park_Jun_Hong_(parkjunhong77@gmail.com)
                    IOUtils.close(source);
                }
            }
        };

        return executeOnChannel(ChannelType.SFTP, connectTimeout, true, action, e -> {
            updateProgressState(true,
                    String.format("파일 업로드를 실패하였습니다. connection=%s, source=%s, destination=%s, connect-timeout=%,d", this.ssh, source, destination, connectTimeout));

            logger.error("파일 업로드를 실패하였습니다. connnection={}, source={}, destination={}, connect-timeout={}", this.ssh, source, destination,
                    NumberUtils.INT_TO_STR.apply(connectTimeout), e);
            return new Result<Boolean>().setMessage("파일 업로드를 실패하였습니다. connection=%s, source=%s, destination=%s, connect-timeout=%,d, 원인=%s", this.ssh, source, destination,
                    connectTimeout, e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.nio.file.Path, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(@NotNull Path source, @NotBlank String destination) throws IOException {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.nio.file.Path, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(@NotNull Path source, @NotBlank String destination, @Min(1) int connectTimeout) throws IOException {

        if (!Files.exists(source)) {
            throw new FileNotFoundException(source.toString());
        }

        return upload(Files.newInputStream(source, StandardOpenOption.READ), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> upload(@NotBlank String source, @NotBlank String destination) throws IOException {
        return upload(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#upload(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> upload(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout) throws IOException {
        return upload(Paths.get(source), destination, connectTimeout);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String)
     */
    @Override
    public Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination) {
        return uploadString(source, destination, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, java.nio.charset.Charset)
     */
    @Override
    public Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination, Charset charset) {
        return uploadString(source, destination, DEFAULT_CONNECT_TIMEOUT, charset);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, int)
     */
    @Override
    public Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout) {
        return uploadString(source, destination, connectTimeout, null);
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see open.commons.ssh.file.IFileUpload#uploadString(java.lang.String, java.lang.String, int,
     *      java.nio.charset.Charset)
     */
    @Override
    public Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout, Charset charset) {
        return upload(new ByteArrayInputStream(source.getBytes(charset != null ? charset : Charset.defaultCharset())), destination, connectTimeout);
    }

}
