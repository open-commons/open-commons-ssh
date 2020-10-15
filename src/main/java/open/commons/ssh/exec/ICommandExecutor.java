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

import open.commons.Result;

/**
 * 
 * @since 2020. 10. 15.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public interface ICommandExecutor {

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
     */
    public Result<List<String>> listProcesses(String... args);
}
