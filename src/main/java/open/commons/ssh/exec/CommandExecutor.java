/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 15. 오후 2:57:07
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.exec;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import open.commons.Result;
import open.commons.ssh.ChannelType;
import open.commons.ssh.SshClient;
import open.commons.ssh.SshConnection;
import open.commons.ssh.function.JSchFunction;
import open.commons.utils.ArrayUtils;
import open.commons.utils.IOUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;

/**
 * 
 * @since 2020. 10. 15.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class CommandExecutor extends SshClient implements ICommandExecutor {

    private static final String[] LIST_PROCESS = { "ps", "-e", "-o", "command", "|", "grep" };

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     * 
     * @param ssh
     *            TODO
     *
     * @since 2020. 10. 15.
     */
    public CommandExecutor(SshConnection ssh) {
        super(ssh);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#listProcesses(int, java.lang.String[])
     */
    @Override
    public Result<List<String>> listProcesses(int connectTimeout, String... args) {

        JSchFunction<ChannelExec, Result<List<String>>> action = channel -> {
            // 조회 명령어 생성/설정
            // grep 실행시 자신의 명령어를 제거하기 위함.
            String argStr = String.join(" ", args);
            
            String command = String.join(" ", ArrayUtils.merge(LIST_PROCESS, args));
            channel.setCommand(command);
            // 데이터 조회 Stream 연결
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect();
                List<String> proclist = IOUtils.readLines(in);

                proclist.forEach(cmd -> {
                    logger.info("[read] {}", cmd);
                });

                return new Result<>(proclist, true);
            } catch (IOException e) {
                logger.error("프로세스 목록 조회 중 에러가 발생하였습니다.", e);
                throw new JSchException("프로세스 목록 조회 중 에러가 발생하였습니다.", e);
            }
        };

        return

        executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            logger.error("파일 다운로드를 실패하였습니다. connection={}, arguments={}", this.ssh, Arrays.toString(args), e);
            return new Result<List<String>>().setMessage("파일 다운로드를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#listProcesses(java.lang.String[])
     */
    @Override
    public Result<List<String>> listProcesses(String... args) {
        return listProcesses(DEFAULT_CONNECT_TIMEOUT, args);
    }
}
