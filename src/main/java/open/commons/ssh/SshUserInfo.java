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
 * Date  : 2020. 10. 14. 오후 4:27:17
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.UserInfo;

/**
 * SSH 접속 정보 클래스.
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SshUserInfo implements UserInfo {
    private final Logger logger;

    /** 비밀번호 */
    private final String password;
    private final String passPhrase;

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
     * @param password
     *            접속 비밀번호
     * @param passPhrase
     *            접속 문구
     *
     * @since 2020. 10. 14.
     */
    public SshUserInfo(String password, String passPhrase) {
        this(password, passPhrase, null);
    }

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
     * @param password
     *            접속 비밀번호
     * @param passPhrase
     *            접속 문구
     * @param logger
     *            연동 메시지 출력용
     *
     * @since 2020. 10. 14.
     */
    public SshUserInfo(String password, String passPhrase, Logger logger) {
        this.password = password;
        this.passPhrase = passPhrase;
        this.logger = logger != null ? logger : LoggerFactory.getLogger(getClass());
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see com.jcraft.jsch.UserInfo#getPassphrase()
     */
    @Override
    public String getPassphrase() {
        return this.passPhrase;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see com.jcraft.jsch.UserInfo#getPassword()
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see com.jcraft.jsch.UserInfo#promptPassphrase(java.lang.String)
     */
    @Override
    public boolean promptPassphrase(String message) {
        logger.trace(" > promptPassPhrase: {}", message);
        return true;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see com.jcraft.jsch.UserInfo#promptPassword(java.lang.String)
     */
    @Override
    public boolean promptPassword(String message) {
        logger.trace(" > promptPassword: {}", message);
        return true;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see com.jcraft.jsch.UserInfo#promptYesNo(java.lang.String)
     */
    @Override
    public boolean promptYesNo(String message) {
        logger.trace(" > promptYesNo: {}", message);
        return true;
    }

    /**
     * @since 2020. 10. 14.
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     *
     * @see com.jcraft.jsch.UserInfo#showMessage(java.lang.String)
     */
    @Override
    public void showMessage(String message) {
        logger.debug(" > showMessage: {}", message);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SshUserInfo [passPhrase=");
        builder.append(passPhrase);
        builder.append("]");
        return builder.toString();
    }

}
