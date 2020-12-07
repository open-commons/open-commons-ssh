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
 * Date  : 2020. 10. 14. 오후 6:08:56
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh;

import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.Session;

/**
 * Channel Type on {@link Session}
 * 
 * @since 2020. 10. 14.
 * @version
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public enum ChannelType {

    /** 'session' */
    SESSION("session"), //
    /** 'shell' */
    SHELL("shell"), //
    /** 'exec' */
    EXEC("exec"), //
    /** 'x11' */
    X11("x11"), //
    /** 'auth-agent@openssh.com' */
    AUTH_AGENT_AT_OPENSSH_DOT_COM("auth-agent@openssh.com"), //
    /** 'direct-tcpip' */
    DIRECT_TCPIP("direct-tcpip"), //
    /** 'forwarded-tcpip' */
    FORWARDED_TCPIP("forwarded-tcpip"), //
    /** 'sftp' */
    SFTP("sftp"), //
    /** 'subsystem' */
    SUBSYSTEM("subsystem"), //
    ;

    private String type;

    private ChannelType(String type) {
        this.type = type;
    }

    /**
     *
     * @return a string of an instance of {@link ChannelType}
     */
    public String get() {
        return this.type;
    }

    /**
     * 
     * @param type
     *            a string for {@link ChannelType} instance.
     *
     * @return an instance of {@link ChannelType}
     *
     * @see #get(String, boolean)
     */
    public static ChannelType get(String type) {
        return get(type, false);
    }

    /**
     *
     * @param type
     *            a string for an instance of {@link ChannelType}.
     * @param ignoreCase
     *            ignore <code><b>case-sensitive</b></code> or not.
     *
     * @return an instance of {@link ChannelType}
     */
    public static ChannelType get(String type, boolean ignoreCase) {

        if (type == null) {
            throw new IllegalArgumentException("'type' MUST NOT be null. input: " + type);
        }

        if (ignoreCase) {
            for (ChannelType value : values()) {
                if (value.type.equalsIgnoreCase(type)) {
                    return value;
                }
            }
        } else {
            for (ChannelType value : values()) {
                if (value.type.equals(type)) {
                    return value;
                }
            }
        }

        throw new IllegalArgumentException("Unexpected 'type' value of 'ChannelType'. expected: " + values0() + " & Ignore case-sensitive: " + ignoreCase + ", input: " + type);
    }

    private static List<String> values0() {

        List<String> valuesStr = new ArrayList<>();

        for (ChannelType value : values()) {
            valuesStr.add(value.get());
        }

        return valuesStr;
    }

}
