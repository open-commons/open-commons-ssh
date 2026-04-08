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
 * Date  : 2020. 11. 26. 오후 6:01:23
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh;

import java.util.Objects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import org.jspecify.annotations.Nullable;

import open.commons.core.utils.StringUtils;

/**
 * 시스템 접속정보를 제공한다.
 * 
 * @since 2020. 11. 26.
 * @version _._._
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ConnectionInfo implements IConnectionInfo {
    /** 접속 IP 또는 도메인 */
    @NotBlank
    protected @Nullable String host;

    /** 접속 포트 */
    protected @Min(1) @Max(65535) int port;

    /** 사용자 */
    @NotBlank
    protected @Nullable String username;

    /** 비밀번호 */
    @NotBlank
    protected @Nullable String password;

    /**
     * 
     * @since 2020. 11. 26.
     */
    public ConnectionInfo() {
    }

    public String getConnectionString() {
        return StringUtils.concatenate("", this.username, "@", this.host, ":", this.port);
    }

    /**
     *
     * @return the host
     *
     * @since 2020. 11. 26.
     */
    public String getHost() {
        String host = this.host;
        if (host == null) {
            throw new IllegalStateException("연결대상 정보가 설정되지 않았습니다.");
        }
        return host;
    }

    /**
     *
     * @return the password
     *
     * @since 2020. 11. 26.
     */
    public String getPassword() {
        String password = this.password;
        if (password == null) {
            throw new IllegalStateException("접속정보(비밀번호)가 설정되지 않았습니다.");
        }
        return password;
    }

    /**
     *
     * @return the port
     *
     * @since 2020. 11. 26.
     */
    public int getPort() {
        return this.port;
    }

    /**
     *
     * @return the username
     *
     * @since 2020. 11. 26.
     */
    public String getUsername() {
        String username = this.username;
        if (username == null) {
            throw new IllegalStateException("접속정보(사용자정보)가 설정되지 않았습니다.");
        }
        return username;
    }

    /**
     * @param host
     *            the host to set
     *
     * @since 2020. 11. 26.
     */
    public void setHost(@NotBlank String host) {
        Objects.requireNonNull(host);

        this.host = host;
    }

    /**
     * @param password
     *            the password to set
     *
     * @since 2020. 11. 26.
     */
    public void setPassword(@NotBlank String password) {
        Objects.requireNonNull(password);

        this.password = password;
    }

    /**
     * @param port
     *            the port to set
     *
     * @since 2020. 11. 26.
     */
    public void setPort(@Min(1) @Max(65535) int port) {
        this.port = port;
    }

    /**
     * @param username
     *            the username to set
     *
     * @since 2020. 11. 26.
     */
    public void setUsername(@NotBlank String username) {
        Objects.requireNonNull(username);

        this.username = username;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 26.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 11. 26.
     *
     * @see java.lang.Object#toString()
     */
    @SuppressWarnings("null")
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConnectionInfo [host=");
        builder.append(host);
        builder.append(", port=");
        builder.append(port);
        builder.append(", username=");
        builder.append(username);
        builder.append(", password=");
        builder.append(password);
        builder.append("]");

        return builder.toString();
    }
}
