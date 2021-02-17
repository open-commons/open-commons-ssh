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
 * Date  : 2020. 11. 26. 오후 6:01:23
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import open.commons.utils.StringUtils;

/**
 * 시스템 접속정보를 제공한다.
 * 
 * @since 2020. 11. 26.
 * @version _._._
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public class ConnectionInfo implements IConnectionInfo {
    /** 접속 IP 또는 도메인 */
    @NotNull
    @NotEmpty
    protected String host;
    /** 접속 포트 */
    @Min(1)
    @Max(65535)
    protected int port;
    /** 사용자 */
    @NotNull
    @NotEmpty
    protected String username;
    /** 비밀번호 */
    @NotNull
    @NotEmpty
    protected String password;

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
        return host;
    }

    /**
     *
     * @return the password
     *
     * @since 2020. 11. 26.
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return the port
     *
     * @since 2020. 11. 26.
     */
    public int getPort() {
        return port;
    }

    /**
     *
     * @return the username
     *
     * @since 2020. 11. 26.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param host
     *            the host to set
     *
     * @since 2020. 11. 26.
     */
    public void setHost(@NotNull @NotEmpty String host) {
        this.host = host;
    }

    /**
     * @param password
     *            the password to set
     *
     * @since 2020. 11. 26.
     */
    public void setPassword(@NotNull @NotEmpty String password) {
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
    public void setUsername(@NotNull @NotEmpty String username) {
        this.username = username;
    }

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 11. 26.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 11. 26.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see java.lang.Object#toString()
     */
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
