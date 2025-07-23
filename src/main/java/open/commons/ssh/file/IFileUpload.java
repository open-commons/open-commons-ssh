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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import open.commons.core.Result;

/**
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> upload(@NotNull byte[] source, @NotBlank String destination);

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
     *            접속대기 제한시간 (단위: ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> upload(@NotNull byte[] source, @NotBlank String destination, int connectTimeout);

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws FileNotFoundException
     */
    Result<Boolean> upload(@NotNull File source, @NotBlank String destination) throws IOException;

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
     *            접속대기 제한시간 (단위: ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws FileNotFoundException
     */
    Result<Boolean> upload(@NotNull File source, @NotBlank String destination, int connectTimeout) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
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
    Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination, boolean autoClose);

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
     *            접속대기 제한시간 (단위: ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination, int connectTimeout);

    /**
     * 로컬 데이터를 지정한 원격경로에 저장한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2025. 7. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param source
     *            업로드할 데이터
     * @param destination
     *            데이터 저장 경로 (절대경로)
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
    Result<Boolean> upload(@NotNull InputStream source, @NotBlank String destination, int connectTimeout, boolean autoClose);

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> upload(@NotNull Path source, @NotBlank String destination) throws IOException;

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
     *            접속대기 제한시간 (단위: ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> upload(@NotNull Path source, @NotBlank String destination, int connectTimeout) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> upload(@NotBlank String source, @NotBlank String destination) throws IOException;

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
     *            접속대기 제한시간 (단위: ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @throws IOException
     */
    Result<Boolean> upload(@NotBlank String source, @NotBlank String destination, int connectTimeout) throws IOException;

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination);

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
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination, Charset charset);

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
     *            접속대기 제한시간 (단위: ms)
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination, int connectTimeout);

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
     *            접속대기 제한시간 (단위: ms)
     * @param charset
     *            문자열 캐릭터 셋
     * @return
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    Result<Boolean> uploadString(@NotBlank String source, @NotBlank String destination, int connectTimeout, Charset charset);

}