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
 * Date  : 2020. 10. 15. 오후 2:58:15
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh;

import java.util.Properties;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import open.commons.Result;
import open.commons.ssh.function.JSchFunction;
import open.commons.ssh.function.SftpFunction;
import open.commons.utils.IOUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * SSH 기반 연동 클래스.
 * 
 * @since 2020. 10. 15.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public abstract class SshClient implements AutoCloseable {

    /** 연결 제한 시간. (단위, ms) */
    protected static final int DEFAULT_CONNECT_TIMEOUT = 5000;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** SSH 연결 제공 객체 */
    protected final SshConnection ssh;

    /** 내부 공용 {@link Session} */
    protected Session session;

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
     *            SSH 연결 객체
     *
     * @since 2020. 10. 15.
     */
    public SshClient(SshConnection ssh) {
        this.ssh = ssh;
    }

    /**
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() {
        IOUtils.close(this.ssh);
    }

    /**
     * {@link Channel}을 열고 명령을 실행한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param type
     *            {@link Channel} 타입
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param autoConnect
     *            자동 연결 여부
     * @param action
     *            {@link Channel} 생성 후 실행
     * @param onError
     *            에러 발생시 처리 함수
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <T extends Channel, R> Result<R> executeOnChannel(ChannelType type, int connectTimeout, boolean autoConnect, JSchFunction<T, Result<R>> action,
            Function<Throwable, Result<R>> onError) {
        T channel = null;
        try {
            channel = openChannel(type, connectTimeout, autoConnect);
            return action.apply(channel);
        } catch (Throwable e) {
            return onError.apply(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    /**
     * {@link Channel}을 열고 명령을 실행한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 15.        박준홍         최초 작성
     * </pre>
     *
     * @param <T>
     * @param <R>
     * @param type
     *            {@link Channel} 타입
     * @param connectTimeout
     *            접속대기 제한시간 (단위: ms)
     * @param autoConnect
     *            자동 연결 여부
     * @param action
     *            {@link Channel} 생성 후 실행
     * @param onError
     *            에러 발생시 처리 함수
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <T extends Channel, R> Result<R> executeOnChannel(ChannelType type, int connectTimeout, boolean autoConnect, SftpFunction<T, Result<R>> action,
            Function<Throwable, Result<R>> onError) {
        T channel = null;
        try {
            channel = openChannel(type, connectTimeout, autoConnect);
            return action.apply(channel);
        } catch (Throwable e) {
            return onError.apply(e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    /**
     * 내부 {@link Session} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 14.        박준홍         최초 작성
     * </pre>
     *
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     * 
     * @see #getSession(boolean, int)
     */
    protected Session getSession() throws JSchException {
        return getSession(true, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * 내부 {@link Session} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 14.        박준홍         최초 작성
     * </pre>
     * 
     * @param autoConnect
     *            대상 서버 자동 연결 여부
     *
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected Session getSession(boolean autoConnect) throws JSchException {
        return getSession(autoConnect, DEFAULT_CONNECT_TIMEOUT);
    }

    /**
     * 내부 {@link Session} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 14.        박준홍         최초 작성
     * </pre>
     * 
     * @param autoConnect
     *            대상 서버 자동 연결 여부
     * @param connectTimeout
     *            서버연결 대기시간 (단위: ms)
     *
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected Session getSession(boolean autoConnect, int connectTimeout) throws JSchException {
        ReentrantLock lock = new ReentrantLock(true);
        try {
            lock.lock();

            if (this.session == null || !this.session.isConnected()) {
                this.session = this.ssh.createSession();

                if (!this.session.isConnected() && autoConnect) {
                    Properties config = new java.util.Properties();
                    config.put("StrictHostKeyChecking", "no");
                    session.setConfig(config);

                    this.session.connect(connectTimeout);
                }
            }

            return this.session;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 내부 {@link Session} 객체를 생성한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 14.        박준홍         최초 작성
     * </pre>
     * 
     * @param connectTimeout
     *            서버연결 대기시간 (단위: ms)
     *
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected Session getSession(int connectTimeout) throws JSchException {
        return getSession(true, connectTimeout);
    }

    /**
     * SSH 연결 객체를 반환한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜      | 작성자   |   내용
     * ------------------------------------------
     * 2020. 10. 14.        박준홍         최초 작성
     * </pre>
     * 
     * @return the ssh
     *
     * @since 2020. 10. 14.
     * 
     * @see #ssh
     */
    public SshConnection getSsh() {
        return ssh;
    }

    /**
     * 주어진 타입에 맞는 채널을 생성하여 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 15.		박준홍			최초 작성
     * </pre>
     *
     * @param <T>
     * @param type
     *            {@link Channel} 타입.
     * @param connectTimeout
     *            접속대기 제한 시간 (단위: ms)
     * @param autoConnect
     *            TODO
     * @return
     * @throws JSchException
     *
     * @since 2020. 10. 15.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    protected <T extends Channel> T openChannel(ChannelType type, int connectTimeout, boolean autoConnect) throws JSchException {
        Session session = getSession(autoConnect, connectTimeout);
        T channel = this.ssh.openChannel(session, type);
        if (autoConnect) {
            channel.connect(connectTimeout);
        }
        return channel;
    }

    /**
     * @since 2020. 10. 19.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SshClient [ssh=");
        builder.append(ssh);
        builder.append(", session=");
        builder.append(session);
        builder.append("]");
        return builder.toString();
    }

}
