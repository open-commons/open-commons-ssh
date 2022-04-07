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
 * Date  : 2020. 10. 15. 오후 2:56:14
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.exec;

import java.util.List;
import java.util.function.Supplier;

import open.commons.core.Result;

/**
 * 
 * @since 2020. 10. 15.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public interface ICommandExecutor {

    /**
     * PID 목록을 제공한다.<br>
     * 
     * <pre>
     * ps aux | grep &lt;args&gt; | awk '{print $2}'
     * </pre>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param args
     *            검색을 위한 설정
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Result<List<String>> listPids(int connectTimeout, String... args);

    /**
     * PID 목록을 제공한다.<br>
     * 
     * <pre>
     * ps aux | grep &lt;args&gt; | awk '{print $2}'
     * </pre>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param args
     *            검색을 위한 설정
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #listPids(int, String...)
     */
    public Result<List<String>> listPids(String... args);

    /**
     * 주어진 조건에 맞는 조회 결과를 제공한다.<br>
     * 
     * 검색 명령어 패턴
     * 
     * <pre>
     * ps -aef | grep &lt;args&gt;
     * </pre>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param args
     *
     * @return
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Result<List<String>> listProcesses(int connectTimeout, String... args);

    /**
     * 주어진 조건에 맞는 조회 결과를 제공한다.<br>
     * 
     * 검색 명령어 패턴
     * 
     * <pre>
     * ps -aef | grep &lt;args&gt;
     * </pre>
     * 
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @param args
     *
     * @return
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #listProcesses(int, String...)
     */
    public Result<List<String>> listProcesses(String... args);

    /**
     * 명령어를 실행시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param cmds
     *            명령어
     *
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Result<Boolean> startProcess(int connectTimeout, String... cmds);

    /**
     * 명령어를 실행시키고 PID 를 반환한다<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param uuid
     *            프로세스 식별정보
     * @param cmds
     *            명령어
     *
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Result<String> startProcess(int connectTimeout, Supplier<String> uuid, String... cmds);

    /**
     * 명령어를 실행시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param cmds
     *            명령어
     *
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #startProcess(int, String...)
     */
    public Result<Boolean> startProcess(String... cmds);

    /**
     * 명령어를 실행시키고 PID 를 반환한다<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param uuid
     *            프로세스 식별정보
     * @param cmds
     *            명령어
     *
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Result<String> startProcess(Supplier<String> uuid, String... cmds);

    /**
     * 명령어를 종료시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param pids
     *            프로세스 ID 목록
     *
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Result<List<String>> stopProcesses(int connectTimeout, String... pids);

    /**
     * 명령어를 종료시킨다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     * 
     * @param pids
     *            프로세스 ID 목록
     *
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * 
     * @see #stopProcesses(int, String...)
     */
    public Result<List<String>> stopProcesses(String... pids);
}
