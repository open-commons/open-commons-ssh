[2021/11/01]
- Update
  + open.commons.ssh.file.FileTransfer
    + changeLocation(String, String, int, boolean, boolean)
    + rm(String, int)

[2021/10/28]
- Add
  + open.commons.ssh.file.IFIle
    + copy(String, String): 복사
    + copy(String, String, boolean): 복사
    + copy(String, String, int): 복사
    + copy(String, String, int, boolean): 복사
    + getFileType(String): 파일 유형 제공
    + getFileType(String, int): 파일 유형 제공
    + isDirectory(String): 디렉토리 여부
    + isDirectory(String, int): 디렉토리 여부
    + isFile(String): 파일 여부
    + isFile(String, int): 파일 여부
    + move(String, String): 이동
    + move(String, String, boolean): 이동
    + move(String, String, int): 이동
    + move(String, String, int, boolean): 이동
    + rm(String): 삭제
    + rm(String, int):  삭제
    + <strike>rmdir(String): 디렉토리 삭제
    + rmdir(String, int): 디렉토리 삭제</strike>
  + open.commons.ssh.file.FileTransfer
    + copy(String, String): 복사
    + copy(String, String, boolean): 복사
    + copy(String, String, int): 복사
    + copy(String, String, int, boolean): 복사
    + getFileType(String): 파일 유형 제공
    + getFileType(String, int): 파일 유형 제공    
    + isDirectory(String): 디렉토리 여부
    + isDirectory(String, int): 디렉토리 여부
    + isFile(String): 파일 여부
    + isFile(String, int): 파일 여부    
    + move(String, String): 이동
    + move(String, String, boolean): 이동
    + move(String, String, int): 이동
    + move(String, String, int, boolean): 이동  
    + rm(String): 삭제
    + rm(String, int): 삭제
    + <strike>rmdir(String): 삭제
    + rmdir(String, int): 삭제</strike>
  + open.commond.ssh.file.FIleType: 파일 유형

[2021/10/26]
- Bugfix
  + open.commons.exec.CommandExecutor 
    + listPids(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 false로 복구
    + listProcesses(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 false로 복구
    + startProcess(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 false로 복구
    + stopProcesses(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 false로 복구
  + open.commons.ssh.SshClient
    + openChannel(ChannelType, int, boolean): 내부에서 getSession(boolean, int) 호출 할 때 'autoConnect'값을 true로 고정.

[2021/10/25]
- Bugfix
  + open.commons.exec.CommandExecutor 
    + listPids(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 true로 변경
    + listProcesses(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 true로 변경
    + startProcess(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 true로 변경
    + stopProcesses(int, String...): executeOnChannel(ChannelType, int, boolean, JSchFunction&lt;T, Result&lt;R&gt;&gt;, Function&lt;Throwable, Result&lt;R&gt;&gt;) 의 autoConnect 값을 true로 변경

[2020/11/26]
- New
  + open.commons.ssh.IConnectionInfo
  + open.commons.ssh.ConnectionInfo

[2020/10/26]
- Add
  + open.commons.ssh.file.IFile
    - delete(String): 파일 삭제
    - delete(String, int)
    - deleteDir(String): 디렉토리 삭제
    - deleteDir(String, int)
  
[2020/10/26]
- Add
  + open.commons.ssh.file.IFile
    - chmodOtcalMode(String, int): 파일 권한 변경
    - chmodOtcalMode(String, int, int)
    - mkdirs(String): 상위 디렉토리 생성
    - mkdirs(String, int)  
   
[2020/10/23]
- Add
  + open.commons.ssh.file.IFile: 파일/디렉토리 관련 기능 제공 
  + open.commons.ssh.file.ListFile: 파일/디렉토리 조회 결과 객체 
- Update
  + open.commons.ssh.file.FileTransfer
    - list(String): 파일/디렉토리 조회
    - list(String, int): 파일/디렉토리 조회

[2020/10/19]
- Bugfix
  + open.commons.ssh.SshConnection
    - Session createSession(): Session 생성을 위한 Mutual Exclusive 적용
  + open.commons.ssh.file.TransferProgressMonitor
    - 진행상태, 메시지 추가
  + open.commons.ssh.exec.CommandExecutor
    - listProcesses(int, String[]): 명령어 변경시 원본 데이터 유지
    - listPids(int, String[]): 명령어 변경시 원본 데이터 유지

[2020/10/16]
- Snapshot:0.2.0-SNAPSHOT

[2020/10/16]
- Release:0.1.0

[2020/10/16]
- 프로세스 시작/종료/조회/PID 조회 적용
- Add
  + open.commons.ssh.exec
    - CommandExecutor
    - ICommandExecutor
  
[2020/10/15]
- 파일 업로드/다운로드 모니터링 기능 추가
- Add
  + open.commons.ssh.file.TransferProgressMonitor

[2020/10/14]
- 파일 업로드/다운로드 기능 추가
- Add
  + open.commons.ssh  
    - ChannelType
    - SshConnection
    - SshUserInfo
  + open.commons.ssh.file
    - FielTransfer
    - IFileDownload
    - IFileUpload
