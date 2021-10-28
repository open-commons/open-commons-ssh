/*
 * Copyright 2020 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.file;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import open.commons.Result;

import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * 파일이나 디렉토리 관련 기능을 제공한다.
 * 
 * @since 2020. 10. 23.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface IFile {

    /**
     * 파일 권한을 변경한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로
     * @param permission
     *            8진법 표기 파일 권한
     * @return
     *
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<LsEntry> chmodOtcalMode(@NotNull @NotEmpty String filepath, int permission);

    /**
     * 파일 권한을 변경한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로
     * @param permission
     *            8진법 표기 파일 권한
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     * @return
     *
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<LsEntry> chmodOtcalMode(@NotNull @NotEmpty String filepath, int permission, @Min(1) int connectTimeout);

    /**
     * 파일을 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        박준홍         최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @return
     *
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> delete(@NotNull @NotEmpty String filepath);

    /**
     * 파일을 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        박준홍         최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @param connectTimeout
     *            접속대기 제한 시간. (단위: ms)
     * @return
     *
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> delete(@NotNull @NotEmpty String filepath, @Min(1) int connectTimeout);

    /**
     * 디렉토리를 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        박준홍         최초 작성
     * </pre>
     *
     * @param filepath
     *            디렉토리 경로
     * @return
     *
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> deleteDir(@NotNull @NotEmpty String filepath);

    /**
     * 디렉토리를 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 27.        박준홍         최초 작성
     * </pre>
     *
     * @param filepath
     *            디렉토리 경로
     * @param connectTimeout
     *            접속대기 제한 시간. (단위: ms)
     * @return
     *
     * @since 2020. 10. 27.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> deleteDir(@NotNull @NotEmpty String filepath, @Min(1) int connectTimeout);

    /**
     * 파일 또는 디렉토리 조회 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로
     * @return
     *
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<List<LsEntry>> list(@NotNull @NotEmpty String filepath);

    /**
     * 파일 또는 디렉토리 조회 결과를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 23.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 또는 디렉토리 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     * @return
     *
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<List<LsEntry>> list(@NotNull @NotEmpty String filepath, @Min(1) int connectTimeout);

    /**
     * 디렉토리를 생성한다. (부모 디렉토리까지 자동으로 생성한다.) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param directory
     *            디렉토리 경로
     * @return
     *
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> mkdirs(@NotNull @NotEmpty String directory);

    /**
     * 디렉토리를 생성한다. (부모 디렉토리까지 자동으로 생성한다.) <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 26.		박준홍			최초 작성
     * </pre>
     *
     * @param directory
     *            디렉토리 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     * @return
     *
     * @since 2020. 10. 26.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> mkdirs(@NotNull @NotEmpty String directory, @Min(1) int connectTimeout);

    /**
     * 파일을 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> rm(@NotEmpty String filepath);

    /**
     * 파일을 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param filepath
     *            파일 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> rm(@NotEmpty String filepath, @Min(1) int connectTimeout);

    /**
     * 디렉토리를 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param dirpath
     *            디렉토리 경로
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> rmdir(@NotEmpty String dirpath);

    /**
     * 디렉토리를 삭제한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param dirpath
     *            디렉토리 경로
     * @param connectTimeout
     *            접속대기 제한시간. (단위: ms)
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<Boolean> rmdir(@NotEmpty String dirpath, @Min(1) int connectTimeout);

}
