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
 * Date  : 2020. 10. 14. 오후 5:43:59
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh;

import java.util.concurrent.locks.ReentrantLock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * SSH 연결 정보
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SshConnection implements IConnectionInfo, AutoCloseable {

    /** 서버 사용자 계정 */
    @NotNull
    @NotEmpty
    private final String username;

    /** 사용자 비밀번호 */
    @NotNull
    @NotEmpty
    private final String password;

    /** SSH 서버 접속 IP 또는 도메인 */
    @NotNull
    @NotEmpty
    private final String host;

    /** SSH 서버 접속 포트 */
    @Min(1)
    @Max(65535)
    private final int port;

    /** {@link Session} 생성을 위한 Mutex 객체 */
    private final ReentrantLock mutexSession = new ReentrantLock();

    /** singleton 방식의 {@link Session} 객체 */
    private Session session;

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
     * @param username
     *            서버 사용자 계정
     * @param password
     *            사용자 비밀번호
     * @param host
     *            SSH 서버 접속 IP 또는 도메인
     * @param port
     *            SSH 서버 접속 포트
     *
     * @since 2020. 10. 14.
     */
    public SshConnection(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password, @NotNull @NotEmpty String host, @Min(1) @Max(65535) int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * @since 2020. 10. 16.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() {
        if (this.session == null) {
            return;
        }

        this.session.disconnect();
    }

    /**
     * SSH 세션 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		박준홍			최초 작성
     * </pre>
     *
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public Session createSession() throws JSchException {

        ReentrantLock lock = this.mutexSession;
        try {
            lock.lock();

            // 기존 세션이 유요한 경우
            if (this.session != null && this.session.isConnected()) {
                return this.session;
            }

            // 신규로 세션을 생성하는 경우
            JSch sch = new JSch();
            // #1. 신규 세션 생성
            this.session = sch.getSession(this.username, this.host, this.port);
            // #2. 비밀번호 설정
            SshUserInfo userInfo = new SshUserInfo(this.password, "Are you sure you want to continue connecting");
            this.session.setUserInfo(userInfo);

            return this.session;
        } finally {
            lock.unlock();
        }
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
     * @return the host
     *
     * @since 2020. 10. 14.
     * 
     * @see #host
     */
    public String getHost() {
        return host;
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
     * @return the password
     *
     * @since 2020. 10. 14.
     * 
     * @see #password
     */
    public String getPassword() {
        return password;
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
     * @return the port
     *
     * @since 2020. 10. 14.
     * 
     * @see #port
     */
    public int getPort() {
        return port;
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
     * @return the username
     *
     * @since 2020. 10. 14.
     * 
     * @see #username
     */
    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unchecked")
    public <T extends Channel> T openChannel(Session session, ChannelType type) throws JSchException {
        switch (type) {
            case AUTH_AGENT_AT_OPENSSH_DOT_COM:
            case DIRECT_TCPIP:
            case EXEC:
            case FORWARDED_TCPIP:
            case SESSION:
            case SFTP:
            case SHELL:
            case SUBSYSTEM:
            case X11:
                return (T) session.openChannel(type.get());
            default:
                // unreachable code
                throw new IllegalArgumentException("Not Supoorted Type=" + type);
        }
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SshConnection [username='");
        builder.append(username);
        builder.append("', password='");
        builder.append(password);
        builder.append("', host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append("]");
        return builder.toString();
    }
}
