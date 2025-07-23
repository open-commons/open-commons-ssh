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
 * Date  : 2020. 10. 14. 오후 9:09:54
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.file;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import open.commons.core.Result;

/**
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull File destination) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull File destination, boolean overwrite) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull File destination, @Min(1) int connectTimeout) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull File destination, @Min(1) int connectTimeout, boolean overwrite) throws IOException;

    /***
     * 원격경로에 있는 데이터를 지정한 대상에 저장합니다.<br>
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
     *            데이터 저장 객체
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination);

    /**
     * 원격경로에 있는 데이터를 지정한 대상에 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 객체
     * @param autoClose
     *            데이터 저장 객체 자동 종료 여부.
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see AutoCloseable
     */
    Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination, boolean autoClose);

    /**
     * 원격경로에 있는 데이터를 지정한 대상에 저장합니다.<br>
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
     *            데이터 저장 객체
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     *
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination, @Min(1) int connectTimeout);

    /**
     * 원격경로에 있는 데이터를 지정한 대상에 저장합니다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            읽어올 파일 경로 (절대경로)
     * @param destination
     *            데이터 저장 객체
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param autoClose
     *            데이터 저장 객체 자동 종료 여부.
     * @return
     *
     * @since 2025. 7. 23.
     * @version 0.4.0
     * @author parkjunhong77@gmail.com
     * 
     * @see AutoCloseable
     */
    Result<Boolean> download(@NotBlank String source, @NotNull OutputStream destination, @Min(1) int connectTimeout, boolean autoClose);

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull Path destination) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull Path destination, boolean overwrite) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull Path destination, @Min(1) int connectTimeout) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotNull Path destination, @Min(1) int connectTimeout, boolean overwrite) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotBlank String destination) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotBlank String destination, boolean overwrite) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> download(@NotBlank String source, @NotBlank String destination, @Min(1) int connectTimeout, boolean overwrite) throws IOException;

}