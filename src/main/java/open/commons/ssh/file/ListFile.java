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
 * Date  : 2020. 10. 23. 오후 5:14:35
 *
 * Author: Park_Jun_Hong_(parkjunhong77@gmail.com)
 * 
 */

package open.commons.ssh.file;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.ChannelSftp.LsEntrySelector;

/**
 * 
 * @since 2020. 10. 23.
 * @version
 * @author Park_Jun_Hong_(parkjunhong77@gmail.com)
 */
public class ListFile implements LsEntrySelector {

    private TreeMap<String, LsEntry> list = new TreeMap<>();

    /**
     * <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @since 2020. 10. 23.
     */
    public ListFile() {
    }

    /**
     * 파일 정보를 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 10. 23.
     */
    public List<LsEntry> getEntries() {
        return new ArrayList<>(this.list.values());
    }

    /**
     * 파일 목록을 제공한다. <br>
     * 
     * <pre>
     * [개정이력]
     *      날짜    	| 작성자	|	내용
     * ------------------------------------------
     * 2020. 10. 23.		parkjunhong77@gmail.com			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 10. 23.
     */
    public List<String> getFiles() {
        return new ArrayList<String>(this.list.keySet());
    }

    /**
     * @since 2020. 10. 23.
     *
     * @see com.jcraft.jsch.ChannelSftp.LsEntrySelector#select(com.jcraft.jsch.ChannelSftp.LsEntry)
     */
    // 아래 내용에 적용됨.
    // - LsEntry entry
    // [PATCH] [3rdParty-Null] 외부 API의 JSpecify 미지원 '우회용' 어노테이션.
    // [TODO] 향후 자체 지원 또는 외부 Stub 환경이 갖춰지면 '제거'
    @SuppressWarnings("null")
    @Override
    public int select(LsEntry entry) {
        this.list.put(entry.getFilename(), entry);
        return CONTINUE;
    }
}
