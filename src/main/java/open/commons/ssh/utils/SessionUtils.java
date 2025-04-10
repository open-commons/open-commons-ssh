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
 * Date  : 2020. 10. 14. 오후 3:44:13
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.utils;

import java.util.function.Function;

import open.commons.core.function.TripleFunction;

import com.jcraft.jsch.Session;

/**
 * {@link Session} 관련 유틸리티 메소드를 제공한다.
 * 
 * @since 2020. 10. 14.
 * @version 0.1.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class SessionUtils {

    /**
     * Session 식별정보를 제공한다.
     * 
     * @param u
     *            username
     * @param h
     *            SSH Server host
     * @param p
     *            SSH Server port
     */
    public static final TripleFunction<String, String, Integer, String> GENERATE_SESSION_KEY = (u, h, p) -> String.join(":", u, h, String.valueOf(p));
    /**
     * @see #GENERATE_SESSION_KEY
     */
    public static final Function<Session, String> GET_SESSION_KEY = s -> GENERATE_SESSION_KEY.apply(s.getUserName(), s.getHost(), s.getPort());

    /**
     * Remote Port Forwarding 식별정보를 제공한다.
     * 
     * @param r
     *            remote port
     * @param h
     *            Service Host
     * @param p
     *            Service port
     * 
     * @since 2020.10.14
     */
    public static final TripleFunction<Integer, String, Integer, String> REMOTE_PORT_FORWARDING_KEYGEN = (r, h, p) -> String.join(":", String.valueOf(r), h, String.valueOf(p));

    // prevent to create an instance.
    private SessionUtils() {
    }
}
