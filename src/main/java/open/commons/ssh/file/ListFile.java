/*
 *
 * This file is generated under this project, "open-commons-ssh".
 *
 * Date  : 2020. 10. 23. 오후 5:14:35
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
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
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 2020. 10. 23.		박준홍			최초 작성
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
     * 2020. 10. 23.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
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
     * 2020. 10. 23.		박준홍			최초 작성
     * </pre>
     *
     * @return
     *
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     */
    public List<String> getFiles() {
        return new ArrayList<String>(this.list.keySet());
    }

    /**
     * @since 2020. 10. 23.
     * @author Park_Jun_Hong_(fafanmama_at_naver_com)
     *
     * @see com.jcraft.jsch.ChannelSftp.LsEntrySelector#select(com.jcraft.jsch.ChannelSftp.LsEntry)
     */
    @Override
    public int select(LsEntry entry) {
        this.list.put(entry.getFilename(), entry);
        return CONTINUE;
    }
}
