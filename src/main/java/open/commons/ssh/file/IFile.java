/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2020. 10. 23. 오후 4:53:35
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.file;

import java.io.IOException;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import open.commons.core.Result;

import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * 파일이나 디렉토리 관련 기능을 제공한다.
 * 
 * @since 2020. 10. 23.
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface IFile {

    /**
     * 파일 권한을 변경한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자    |   내용
     * ------------------------------------------
     * 2020. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 메소드명 오타 교정(Otcal -> Octal) 및 제네릭 Nullability 문서화
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로 (절대 {@code null} 및 빈 문자열 불가)
     * @param permission
     *            8진법 표기 파일 권한 (예: 0755)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link LsEntry}) 역시 절대 {@code null}이 아님을 보장함.
     *
     * @since 2020. 10. 26.
     */
    // [PATCH] 오타 수정: chmodOtcalMode -> chmodOctalMode
    public Result<LsEntry> chmodOctalMode(@NotBlank String filepath, int permission);

    /**
     * 파일 권한을 변경한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자    |   내용
     * ------------------------------------------
     * 2020. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 메소드명 오타 교정(Otcal -> Octal) 및 제네릭 Nullability 문서화
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로 (절대 {@code null} 및 빈 문자열 불가)
     * @param permission
     *            8진법 표기 파일 권한 (예: 0755)
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms, 1 이상이어야 함)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link LsEntry}) 역시 절대 {@code null}이 아님을 보장함.
     *
     * @since 2020. 10. 26.
     */
    public Result<LsEntry> chmodOctalMode(@NotBlank String filepath, int permission, @Min(1) int connectTimeout);

    /**
     * 파일을 복사한다. <b>(원격서버에서 처리됨).</b> <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자    |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param source
     *            복사할 원본 파일 경로 (절대 {@code null} 및 빈 문자열 불가)
     * @param destination
     *            복사될 대상 파일 경로 (절대 {@code null} 및 빈 문자열 불가)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @throws IOException
     *             파일 복사 중 네트워크 입출력 오류가 발생한 경우
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination) throws IOException;

    /**
     * 파일을 복사한다. <b>(원격서버에서 처리됨).</b> <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자    |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param source
     *            복사할 원본 파일 경로
     * @param destination
     *            복사될 대상 파일 경로
     * @param overwrite
     *            대상 경로에 파일이 존재할 경우 덮어쓰기 여부
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @throws IOException
     *             파일 복사 중 네트워크 입출력 오류가 발생한 경우
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination, boolean overwrite)
            throws IOException;

    /**
     * 파일을 복사한다. <b>(원격서버에서 처리됨).</b> <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자    |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param source
     *            복사할 원본 파일 경로
     * @param destination
     *            복사될 대상 파일 경로
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @throws IOException
     *             파일 복사 중 네트워크 입출력 오류가 발생한 경우
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination, int connectTimeout)
            throws IOException;

    /**
     * 파일을 복사한다. <b>(원격서버에서 처리됨).</b> <br>
     *
     * <pre>
     * [개정이력]
     * 날짜        | 작성자    |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param source
     *            복사할 원본 파일 경로
     * @param destination
     *            복사될 대상 파일 경로
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param overwrite
     *            대상 경로에 파일이 존재할 경우 덮어쓰기 여부
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @throws IOException
     *             파일 복사 중 네트워크 입출력 오류가 발생한 경우
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> copy(@NotBlank String source, @NotBlank String destination, int connectTimeout,
            boolean overwrite) throws IOException;

    /**
     * 파일을 삭제한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param filepath
     *            삭제할 파일 경로 (절대 {@code null} 및 빈 문자열 불가)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @since 2020. 10. 27.
     */
    public Result<Boolean> delete(@NotBlank String filepath);

    /**
     * 파일을 삭제한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param filepath
     *            삭제할 파일 경로 (절대 {@code null} 및 빈 문자열 불가)
     * @param connectTimeout
     *            접속대기 제한 시간 (단위: ms, 1 이상이어야 함)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @since 2020. 10. 27.
     */
    public Result<Boolean> delete(@NotBlank String filepath, @Min(1) int connectTimeout);

    /**
     * 디렉토리를 삭제한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param filepath
     *            삭제할 디렉토리 경로 (절대 {@code null} 및 빈 문자열 불가)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @since 2020. 10. 27.
     */
    public Result<Boolean> deleteDir(@NotBlank String filepath);

    /**
     * 디렉토리를 삭제한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        parkjunhong77@gmail.com         최초 작성
     * 2026. 4. 8.          parkjunhong77@gmail.com         (3.0.0) 제네릭 Nullability 문서화
     * </pre>
     *
     * @param filepath
     *            삭제할 디렉토리 경로 (절대 {@code null} 및 빈 문자열 불가)
     * @param connectTimeout
     *            접속대기 제한 시간 (단위: ms, 1 이상이어야 함)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 {@code true}를 반환하며 절대 {@code null}이 아님.
     *
     * @since 2020. 10. 27.
     */
    public Result<Boolean> deleteDir(@NotBlank String filepath, @Min(1) int connectTimeout);

    /**
     * 파일 유형을 제공한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            파일 경로
     *
     * @return 파일 유형 조회 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link FileType}) 역시 절대 {@code null}이 아님을 보장함.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<FileType> getFileType(@NotBlank String pathname);

    /**
     * 파일 유형을 제공한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            파일 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     *
     * @return 파일 유형 조회 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link FileType}) 역시 절대 {@code null}이 아님을 보장함.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<FileType> getFileType(@NotBlank String pathname, @Min(1) int connectTimeout);

    /**
     * 디렉토리 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isDirectory(@NotBlank String pathname) {
        Result<FileType> resultFileType = getFileType(pathname);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.DIRECTORY == resultFileType.getData());
    }

    /**
     * 디렉토리 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     * @param connectTimeout
     *            접속대기 제한 시간. (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isDirectory(@NotBlank String pathname, @Min(1) int connectTimeout) {
        Result<FileType> resultFileType = getFileType(pathname, connectTimeout);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.DIRECTORY == resultFileType.getData());
    }

    /**
     * 파일 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isFile(@NotBlank String pathname) {
        Result<FileType> resultFileType = getFileType(pathname);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.REGULAR_FILE == resultFileType.getData());
    }

    /**
     * 파일 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     * @param connectTimeout
     *            접속대기 제한 시간. (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isFile(@NotBlank String pathname, @Min(1) int connectTimeout) {
        Result<FileType> resultFileType = getFileType(pathname, connectTimeout);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.REGULAR_FILE == resultFileType.getData());
    }

    /**
     * Socket 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isSocket(@NotBlank String pathname) {
        Result<FileType> resultFileType = getFileType(pathname);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.SOCKET == resultFileType.getData());
    }

    /**
     * Socket 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     * @param connectTimeout
     *            접속대기 제한 시간. (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isSocket(@NotBlank String pathname, @Min(1) int connectTimeout) {
        Result<FileType> resultFileType = getFileType(pathname, connectTimeout);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.SOCKET == resultFileType.getData());
    }

    /**
     * 심볼릭 링크 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isSymbolicLink(@NotBlank String pathname) {
        Result<FileType> resultFileType = getFileType(pathname);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.SYMBOLIC_LINK == resultFileType.getData());
    }

    /**
     * 심볼릭 링크 여부를 제공한다.<br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2021. 10. 28.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param pathname
     *            대상 경로
     * @param connectTimeout
     *            접속대기 제한 시간. (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    default Result<Boolean> isSymbolicLink(@NotBlank String pathname, @Min(1) int connectTimeout) {
        Result<FileType> resultFileType = getFileType(pathname, connectTimeout);
        if (resultFileType.isError()) {
            return Result.error(resultFileType.getMessage());
        }

        return Result.success(FileType.SYMBOLIC_LINK == resultFileType.getData());
    }

    /**
     * 파일 또는 디렉토리 조회 결과를 제공한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 23.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link List})와 리스트 내의 각 원소({@link LsEntry}) 모두 절대 {@code null}이 아님을
     *         보장함.<br>
     *         해당 경로가 존재하지 않는 경우 빈 목록(Empty List)을 제공.
     *
     * @since 2020. 10. 23.
     */
    public Result<List<LsEntry>> list(@NotBlank String filepath);

    /**
     * 파일 또는 디렉토리 조회 결과를 제공한다. <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 23.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link List})와 리스트 내의 각 원소({@link LsEntry}) 모두 절대 {@code null}이 아님을
     *         보장함.<br>
     *         해당 경로가 존재하지 않는 경우 빈 목록(Empty List)을 제공.
     *
     * @since 2020. 10. 23.
     */
    public Result<List<LsEntry>> list(@NotBlank String filepath, @Min(1) int connectTimeout);

    /**
     * 디렉토리를 생성한다. (부모 디렉토리까지 자동으로 생성한다.) <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param directory
     *            디렉토리 경로
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2020. 10. 26.
     */
    public Result<Boolean> mkdirs(@NotBlank String directory);

    /**
     * 디렉토리를 생성한다. (부모 디렉토리까지 자동으로 생성한다.) <br>
     *
     * <pre>
     * [개정이력]
     * 날짜       | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 26.        parkjunhong77@gmail.com         최초 작성
     * </pre>
     *
     * @param directory
     *            디렉토리 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     *
     * @return 실행 결과를 담은 {@link Result} 객체 (절대 {@code null}이 아님). <br>
     *         작업 성공 시 내부 데이터({@link Boolean})는 논리값(true/false)을 가지며 절대 {@code null}이 아님.
     *
     * @since 2020. 10. 26.
     */
    public Result<Boolean> mkdirs(@NotBlank String directory, @Min(1) int connectTimeout);

    /**
     * 파일을 이동시킨다. <b>(원격서버에서 처리됨).</b> <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param source
     *            복사할 파일 경로
     * @param destination
     *            복사될 파일 경로
     *
     * @return
     *
     * @throws IOException
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination) throws IOException;

    /**
     * 파일을 이동시킨다. <b>(원격서버에서 처리됨).</b> <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param source
     *            이동할 파일 경로
     * @param destination
     *            이동 후 파일 경로
     * @param overwrite
     *            덮어쓰기 여부.
     *
     * @return
     *
     * @throws IOException
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination, boolean overwrite)
            throws IOException;

    /**
     * 파일을 이동시킨다. <b>(원격서버에서 처리됨).</b> <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param source
     *            이동할 파일 경로
     * @param destination
     *            이동 후 파일 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     *
     * @return
     *
     * @throws IOException
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination, int connectTimeout)
            throws IOException;

    /**
     * 파일을 이동시킨다. <b>(원격서버에서 처리됨).</b> <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param source
     *            이동할 파일 경로
     * @param destination
     *            이동 후 파일 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     * @param overwrite
     *            덮어쓰기 여부.
     *
     * @return
     *
     * @throws IOException
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> move(@NotBlank String source, @NotBlank String destination, int connectTimeout,
            boolean overwrite) throws IOException;

    /**
     * 파일을 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     *
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> rm(@NotBlank String filepath);

    /**
     * 파일을 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     *
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     */
    public Result<Boolean> rm(@NotBlank String filepath, @Min(1) int connectTimeout);

}
