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
 * Date  : 2020. 10. 14. 오후 4:01:07
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.forwarding;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

import org.jspecify.annotations.Nullable;

/**
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class RemotePortForwarding implements Comparable<RemotePortForwarding> {

    /**
     * SSH Tunneling 포트. <br>
     * SSH Server에서 개방되는 포트이다.
     */
    private final int remotePort;

    /**
     * SSH Tunneling 포트로 연결되는 서버 Host.
     */
    private final String serviceHost;

    /**
     * SSH Tunneling 포트로 연결되는 서버 포트..
     */
    private final int servicePort;

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
     * @param remotePortFwdStr
     *            Remote Port Forwarding 정보로 구성된 문자열. "rport:host:hostport"
     *
     * @since 2020. 10. 14.
     */
    @SuppressWarnings("null")
    public RemotePortForwarding(@NotBlank String remotePortFwdStr) {
        Objects.requireNonNull(remotePortFwdStr);

        String[] strs = remotePortFwdStr.split(":");
        if (strs.length < 3) {
            throw new IllegalArgumentException("원격포트포워딩 정보가 올바르지 않습니다. 설정: " + remotePortFwdStr);
        }

        this.remotePort = Integer.parseInt(strs[0]);
        this.serviceHost = strs[1];
        this.servicePort = Integer.parseInt(strs[2]);
    }

    /**
     * @since 2020. 10. 14.
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(@Nullable RemotePortForwarding o) {
        if (o == null) {
            return -1;
        }

        int c = this.remotePort - o.remotePort;
        if (c != 0) {
            return c;
        }

        c = this.serviceHost.compareTo(o.serviceHost);
        if (c != 0) {
            return c;
        }

        return this.servicePort - o.servicePort;
    }

    /**
     * 원격서버에 연결되는 포트 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the remotePort
     *
     * @since 2020. 10. 14.
     * 
     * @see #remotePort
     */
    public int getRemotePort() {
        return remotePort;
    }

    /**
     * 로컬서버에서 연결하는 서비스 IP 또는 도메인을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the serviceHost
     *
     * @since 2020. 10. 14.
     * 
     * @see #serviceHost
     */
    public String getServiceHost() {
        return serviceHost;
    }

    /**
     * 로컬서버에서 연결하는 서비스 포트를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 14.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     * 
     * @return the servicePort
     *
     * @since 2020. 10. 14.
     * 
     * @see #servicePort
     */
    public int getServicePort() {
        return servicePort;
    }

    /**
     * @since 2020. 10. 14.
     *
     * @see java.lang.Object#toString()
     */
    // 아래 내용에 적용됨.
    // - StringBuilder.toString()
    // [PATCH] [JDK-Null] JDK 표준 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 JDK 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RemotePortForwarding [remotePort=");
        builder.append(remotePort);
        builder.append(", serviceHost=");
        builder.append(serviceHost);
        builder.append(", servicePort=");
        builder.append(servicePort);
        builder.append("]");

        return builder.toString();
    }

}
