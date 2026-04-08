/*
 * Copyright 2020 Park Jun-Hong (parkjunhong77@gmail.com)
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
 * Date  : 2020. 10. 14. 오후 3:52:52
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.forwarding;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import open.commons.core.utils.AssertUtils2;
import open.commons.core.utils.ObjectUtils;
import open.commons.ssh.utils.SessionUtils;

/**
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SshTunnelingInfo {
    /** SSH Server 사용자 */
    @NotNull
    @NotEmpty
    private final String username;

    /** SSH Server Host. */
    @NotNull
    @NotEmpty
    private final String host;

    /**
     * SSH Server 포트
     */
    @Min(1)
    @Max(65535)
    private final int port;

    /**
     * Remote Port Forwarding 정보
     */
    private Set<RemotePortForwarding> remotePortForwardings = new ConcurrentSkipListSet<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @param user
     *            TODO
     * @param host
     *            TODO
     * @param port
     *            TODO
     *
     * @since 2020. 10. 14.
     */
    public SshTunnelingInfo(@NotBlank String username, @NotBlank String host, int port) {
        this.username = username;
        this.host = host;
        this.port = port;
    }

    /**
     * 원격포트 정보를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param remote
     *
     * @since 2020. 10. 14.
     */
    public void addRemotePortForwarding(RemotePortForwarding remote) {
        Objects.requireNonNull(remote);

        this.remotePortForwardings.add(remote);
    }

    /**
     * 원격포트 정보를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param remotes
     *
     * @since 2020. 10. 14.
     */
    public void addRemotePortForwardings(Collection<RemotePortForwarding> remotes) {
        AssertUtils2.notExistNull(remotes);

        this.remotePortForwardings.addAll(remotes);
    }

    /**
     * 원격포트 정보를 추가한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param remotes
     *
     * @since 2020. 10. 14.
     */
    // 아래 내용에 적용됨.
    // - ObjectUtils.requireNonNulls((Object[]) remotes);
    // [PATCH] [Array-Null] 자바 배열(Array)의 가변성 및 와일드카드 제약으로 인한 IDE 분석기 오탐 우회
    // [TODO] 향후 IDE의 배열 데이터 흐름 분석이 고도화되거나 JSpecify가 완벽히 지원되면 '제거'
    // 아래 내용에 적용됨.
    // - Arrays.asList(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public void addRemotePortForwardings(RemotePortForwarding... remotes) {
        ObjectUtils.requireNonNulls((Object[]) remotes);

        this.addRemotePortForwardings(Arrays.asList(remotes));
    }

    /**
     * 원격 SSH 서버 IP 또는 도메인을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
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
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 10. 14.
     */
    // 아래 내용에 적용됨.
    // - Function.apply(...)
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    public String getId() {
        return SessionUtils.GENERATE_SESSION_KEY.apply(this.username, this.host, this.port);
    }

    /**
     * 원격 SSH 서버 포트 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
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
     * 원격서버에 연결된 {@link RemotePortForwarding} 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the remotePortForwardings
     *
     * @since 2020. 10. 14.
     * 
     * @see #remotePortForwardings
     */
    public Set<RemotePortForwarding> getRemotePortForwardings() {
        return remotePortForwardings;
    }

    /**
     * 원격 SSH 서버 사용자 정보을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
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

    /**
     * {@link RemotePortForwarding} 정보를 설정한다.<br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @param remotePortForwardings
     *            the remotePortForwardings to set
     *
     * @since 2020. 10. 14.
     * 
     * @see #remotePortForwardings
     */
    public void setRemotePortForwardings(Set<RemotePortForwarding> remotePortForwardings) {
        AssertUtils2.notExistNull(remotePortForwardings);

        this.remotePortForwardings = remotePortForwardings;
    }
}
