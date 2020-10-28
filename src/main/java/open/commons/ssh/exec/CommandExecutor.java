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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import open.commons.Result;
import open.commons.ssh.ChannelType;
import open.commons.ssh.SshClient;
import open.commons.ssh.SshConnection;
import open.commons.ssh.function.JSchFunction;
import open.commons.text.NamedTemplate;
import open.commons.utils.ArrayUtils;
import open.commons.utils.CollectionUtils;
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
    /** 프로세스 실행을 위한 명령어 */
    private static final String CMD_START_PROCESS = "{args} &";
    /** PID를 이용하여 프로세스를 종료하기 위한 명령어 */
    private static final String CMD_STOP_PROCESS = "kill -15 {args} &";
    /** 프로세스 검색을 위한 명령어 */
    private static final String CMD_LIST_PROCESS = "ps -e -o command | grep {args}";
    /** 프로세스 ID 검식을 위한 명령어 */
    private static final String CMD_LIST_PROCESS_ID = "ps aux | grep {args}";

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
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#listPids(int, java.lang.String[])
     */
    @Override
    public Result<List<String>> listPids(int connectTimeout, String... args) {
        logger.debug("connect-timeout={}, arguments={}", connectTimeout, Arrays.toString(args));

        JSchFunction<ChannelExec, Result<List<String>>> action = channel -> {
            // 조회 명령어 생성/설정
            // grep 실행시 자신의 명령어를 제거하기 위함.
            String[] newArgs = ArrayUtils.copyOf(args, args.length);
            if (newArgs.length > 0) {
                newArgs[0] = String.join("", "[", String.valueOf(newArgs[0].charAt(0)), "]", newArgs[0].substring(1));
            }
            String command = String.join(" ", toCommand(CMD_LIST_PROCESS_ID, newArgs), " | awk '{print $2}'");
            channel.setCommand(command);
            logger.info("command={}", command);

            // 데이터 조회 Stream 연결
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect();
                List<String> pids = IOUtils.readLines(in);

                pids.forEach(pid -> {
                    logger.trace("pid={}", pid);
                });

                return new Result<>(pids, true);
            } catch (IOException e) {
                logger.error("프로세스 목록 조회 중 에러가 발생하였습니다.", e);
                throw new JSchException("프로세스 목록 조회 중 에러가 발생하였습니다.", e);
            }
        };

        return executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            logger.error("프로세스 목록 조회를 실패하였습니다. connection={}, arguments={}", this.ssh, Arrays.toString(args), e);
            return new Result<List<String>>().setMessage("프로세스 목록 조회를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#listPids(java.lang.String[])
     */
    @Override
    public Result<List<String>> listPids(String... args) {
        return listPids(DEFAULT_CONNECT_TIMEOUT, args);
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#listProcesses(int, java.lang.String[])
     */
    @Override
    public Result<List<String>> listProcesses(int connectTimeout, String... args) {

        logger.debug("connect-timeout={}, arguments={}", connectTimeout, Arrays.toString(args));

        JSchFunction<ChannelExec, Result<List<String>>> action = channel -> {
            String command = toCommand(CMD_LIST_PROCESS, args);
            channel.setCommand(command);
            logger.info("command={}", command);

            // 데이터 조회 Stream 연결
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect();
                List<String> cmdlist = IOUtils.readLines(in);

                cmdlist.forEach(cmd -> {
                    logger.trace("command={}", cmd);
                });

                return new Result<>(cmdlist, true);
            } catch (IOException e) {
                logger.error("프로세스 목록 조회 중 에러가 발생하였습니다.", e);
                throw new JSchException("프로세스 목록 조회 중 에러가 발생하였습니다.", e);
            }
        };

        return executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            logger.error("프로세스 목록 조회를 실패하였습니다. connection={}, arguments={}", this.ssh, Arrays.toString(args), e);
            return new Result<List<String>>().setMessage("프로세스 목록 조회를 실패하였습니다. 원인=%s", e.getMessage());
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

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#startProcess(int, java.lang.String[])
     */
    @Override
    public Result<Boolean> startProcess(int connectTimeout, String... cmds) {

        logger.debug("connect-timeout={}, command={}", connectTimeout, Arrays.toString(cmds));

        JSchFunction<ChannelExec, Result<Boolean>> action = channel -> {
            // 명령어 생성/설정
            String command = toCommand(CMD_START_PROCESS, cmds);
            channel.setCommand(command);
            logger.info("command={}", command);

            // 명령어 실행
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect();

                // skip input stream.
                String readline = null;
                BufferedReader reader = IOUtils.getReader(in);

                while ((readline = reader.readLine()) != null) {
                    logger.trace("{}", readline);
                }
//                while (in.read() != -1)
//                    ;
                return new Result<Boolean>(true, true);
            } catch (IOException e) {
                logger.error("프로세스 실행 중 에러가 발생하였습니다.", e);
                throw new JSchException("프로세스 실행 중 에러가 발생하였습니다.", e);
            }
        };

        return executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            logger.error("프로세스 실행을 실패하였습니다. connection={}, connect-timeout={}, command={}", this.ssh, connectTimeout, Arrays.toString(cmds), e);
            return new Result<Boolean>().setMessage("프로세스 실행을 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#startProcess(int, Supplier, java.lang.String[])
     */
    @Override
    public Result<String> startProcess(int connectTimeout, Supplier<String> uuid, String... cmds) {
        Result<Boolean> resultStart = startProcess(connectTimeout, cmds);
        if (!resultStart.getResult()) {
            return new Result<String>().setMessage(resultStart.getMessage());
        }

        Result<List<String>> resultPids = listPids(connectTimeout, uuid.get());
        if (!resultPids.getResult()) {
            return new Result<String>().setMessage(resultPids.getMessage());
        }

        String pid = "";
        List<String> pids = resultPids.getData();
        if (pids.size() > 0) {
            pid = pids.get(0);
        }

        return new Result<String>(pid, true).setMessage("pids=%s", CollectionUtils.toString(pids));
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#startProcess(java.lang.String[])
     */
    @Override
    public Result<Boolean> startProcess(String... cmds) {
        return startProcess(DEFAULT_CONNECT_TIMEOUT, cmds);
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#startProcess(Supplier, java.lang.String[])
     */
    @Override
    public Result<String> startProcess(Supplier<String> uuid, String... cmds) {
        return startProcess(DEFAULT_CONNECT_TIMEOUT, uuid, cmds);
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#stopProcesses(int, String...)
     */
    @Override
    public Result<List<String>> stopProcesses(int connectTimeout, String... pids) {

        logger.debug("connect-timeout={}, pids={}", connectTimeout, Arrays.toString(pids));

        JSchFunction<ChannelExec, Result<List<String>>> action = channel -> {
            // 명령어 생성/설정
            String command = toCommand(CMD_STOP_PROCESS, pids);
            channel.setCommand(command);
            logger.info("command={}", command);

            // 명령어 실행
            channel.setInputStream(null);
            try (InputStream in = channel.getInputStream();) {
                channel.connect();
                List<String> resultStop = IOUtils.readLines(in);

                resultStop.forEach(result -> {
                    logger.trace("{}", result);
                });

                return new Result<List<String>>(resultStop, true);
            } catch (IOException e) {
                logger.error("프로세스 종료 중 에러가 발생하였습니다.", e);
                throw new JSchException("프로세스 종료 중 에러가 발생하였습니다.", e);
            }
        };

        return executeOnChannel(ChannelType.EXEC, connectTimeout, false, action, e -> {
            logger.error("프로세스 종료를  실패하였습니다. connection={}, connect-timeout={}, command={}", this.ssh, connectTimeout, Arrays.toString(pids), e);
            return new Result<List<String>>().setMessage("프로세스 종료를 실패하였습니다. 원인=%s", e.getMessage());
        });
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see open.commons.ssh.exec.ICommandExecutor#stopProcesses(String...)
     */
    @Override
    public Result<List<String>> stopProcesses(String... pids) {
        return stopProcesses(DEFAULT_CONNECT_TIMEOUT, pids);
    }

    /**
     * arguments 를 받아 주어진 템플릿에 적용하여 명령어를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 16.		박준홍			최초 작성
     * </pre>
     *
     * @param cmdTpl
     *            실행 명령어 템플릿
     * @param args
     * @return
     *
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    private String toCommand(String cmdTpl, String... args) {
        String argStr = String.join(" ", args);
        return NamedTemplate.format(cmdTpl, "args", argStr);
    }
}
