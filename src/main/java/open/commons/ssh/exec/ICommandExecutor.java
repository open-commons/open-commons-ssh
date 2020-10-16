/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 15. 오후 2:56:14
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.exec;

import java.util.List;
import java.util.function.Supplier;

import open.commons.Result;

/**
 * 
 * @since 2020. 10. 15.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface ICommandExecutor {

    /**
     * PID 목록을 제공한다.<br>
     * 
     * <pre>
     * ps aux | grep <args> | awk '{print $2}'
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<List<String>> listPids(int connectTimeout, String... args);

    /**
     * PID 목록을 제공한다.<br>
     * 
     * <pre>
     * ps aux | grep <args> | awk '{print $2}'
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * ps -aef | grep <args>
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
     * @param condition
     *
     * @return
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public Result<List<String>> listProcesses(int connectTimeout, String... args);

    /**
     * 주어진 조건에 맞는 조회 결과를 제공한다.<br>
     * 
     * 검색 명령어 패턴
     * 
     * <pre>
     * ps -aef | grep <args>
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
     * @param condition
     *
     * @return
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see #stopProcesses(int, String...)
     */
    public Result<List<String>> stopProcesses(String... pids);
}
