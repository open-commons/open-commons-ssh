/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77@gmail.com)
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
 * Date  : 2021. 10. 28. 오후 5:59:34
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.file;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import open.commons.core.utils.ExceptionUtils;

import com.jcraft.jsch.SftpATTRS;

/**
 * 
 * @since 2021. 10. 28.
 * @version 0.2.0
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public enum FileType {

    MT("S_IFMT", 0xf000), //
    FIFO("S_IFIFO", 0x1000), //
    CHAR_DEVICE("S_IFCHR", 0x2000), //
    DIRECTORY("S_IFDIR", 0x4000), //
    BLOCK_DEVICE("S_IFBLK", 0x6000), //
    REGULAR_FILE("S_IFREG", 0x8000), //
    SYMBOLIC_LINK("S_IFLNK", 0xa000), //
    SOCKET("S_IFSOCK", 0xc000), //
    NULL("NULL", 0x0000), //
    /** 마지막 경로 정보에 wildcard(*)가 포함된 경우. */
    WILDCARD("WILDCARD", -0x0001), //
    ;

    private String code;
    private int flag;

    private FileType(String code, int flag) {
        this.code = code;
        this.flag = flag;
    }

    /**
     * st_mode 코드값을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public String getCode() {
        return this.code;
    }

    /**
     * st_mode bit 값을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     */
    public int getFlag() {
        return this.flag;
    }

    private static Set<Integer> flags() {
        return Arrays.stream(values()) //
                .map(e -> e.flag) //
                .collect(Collectors.toSet());
    }

    /**
     * flag에 해당하는 파일유형을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2021. 10. 28.		박준홍			최초 작성
     * </pre>
     *
     * @param flag
     * @return
     *
     * @since 2021. 10. 28.
     * @version 0.2.0
     * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
     * @see SftpATTRS
     */
    public static FileType get(final int flag, final int permissions) {
        Optional<FileType> opt = Arrays.stream(values()) //
                .filter(t -> NULL != t) //
                .filter(e -> (flag & SftpATTRS.SSH_FILEXFER_ATTR_PERMISSIONS) != 0 && (permissions & MT.flag) == e.flag) //
                .findAny();

        if (!opt.isPresent()) {
            throw ExceptionUtils.newException(IllegalArgumentException.class, "Unexpected 'flag' & 'permissions' value of 'FileType'. expected: %s, input: %s, permissions: %s",
                    flags(), flag, permissions);
        }

        return opt.get();
    }
}
