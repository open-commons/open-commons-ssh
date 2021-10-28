/*
 * Copyright 2021 Park Jun-Hong_(parkjunhong77/google/com)
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
 * Date  : 2021. 10. 28. 오후 1:48:57
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.ssh.file;

/**
 * 파일 복사/이동 방식 정의.
 * 
 * @since 2021. 10. 28.
 * @version 0.2.0
 * @author Park_Jun_Hong_(fafanmama_at_naver_com)
 */
public enum FileLocationChangeType {
    /** 파일 -> 파일 */
    FILE_TO_FILE,
    /** '파일'을 '디렉토리' 내부로 */
    FILE_TO_DIR,
    /** '디렉토리'를 '다른 디렉토리' 내부로 */
    DIR_TO_DIR
}
