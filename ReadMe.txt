

1 正常权限列表（安装后就赋予这些权限，不需要显示提醒用户，用户也不能取消这些权限）：
    ACCESS_LOCATION_EXTRA_COMMANDS
    ACCESS_NETWORK_STATE
    ACCESS_NOTIFICATION_POLICY
    ACCESS_WIFI_STATE
    BLUETOOTH
    BLUETOOTH_ADMIN
    BROADCAST_STICKY
    CHANGE_NETWORK_STATE
    CHANGE_WIFI_MULTICAST_STATE
    CHANGE_WIFI_STATE
    DISABLE_KEYGUARD
    EXPAND_STATUS_BAR
    GET_PACKAGE_SIZE
    INTERNET
    KILL_BACKGROUND_PROCESSES
    MODIFY_AUDIO_SETTINGS
    NFC
    READ_SYNC_SETTINGS
    READ_SYNC_STATS
    RECEIVE_BOOT_COMPLETED
    REORDER_TASKS
    REQUEST_INSTALL_PACKAGES
    SET_TIME_ZONE
    SET_WALLPAPER
    SET_WALLPAPER_HINTS
    TRANSMIT_IR
    USE_FINGERPRINT
    VIBRATE
    WAKE_LOCK
    WRITE_SYNC_SETTINGS
    SET_ALARM
    INSTALL_SHORTCUT
    UNINSTALL_SHORTCUT

2 危险权限（运行时权限）
    危险权限实际上才是运行时权限主要处理的对象，这些权限可能引起隐私问题或者影响其他程序运行。Android中的危险权限可以归为以下几个分组：
    CALENDAR
    CAMERA
    CONTACTS
    LOCATION
    MICROPHONE
    PHONE
    SENSORS
    SMS
    STORAGE
具体如下：
    //android.permission-group.CALENDAR(日历分组)
    <uses-permission android:name="android.permission.READ_CALENDAR"/>
    <uses-permission android:name="android.permission.WRITE_CALENDAR"/>
    //android.permission-group.CAMERA(照相机分组)
    <uses-permission android:name="android.permission.CAMERA"/>
    //android.permission-group.CONTACTS(联系人分组)
    <uses-permission android:name="android.permission.READ_CONTACTS"/>
    <uses-permission android:name="android.permission.WRITE_CONTACTS"/>
    <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
    //android.permission-group.LOCATION(定位分组)
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    //android.permission-group.MICROPHONE(麦克风分组)
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    //android.permission-group.PHONE(电话分组)
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
    <uses-permission android:name="android.permission.CALL_PHONE"/>
    <uses-permission android:name="android.permission.READ_CALL_LOG"/>
    <uses-permission android:name="android.permission.WRITE_CALL_LOG"/>
    <uses-permission android:name="android.permission.USE_SIP"/>
    <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"/>
    <uses-permission android:name="com.android.voicemail.ADD_VOICEMAIL"/>
    //android.permission-group.SENSORS(人体传感器分组)
    <uses-permission android:name="android.permission.BODY_SENSORS"/>
    //android.permission-group.SMS(短信分组)
    <uses-permission android:name="android.permission.SEND_SMS"/>
    <uses-permission android:name="android.permission.READ_SMS"/>
    <uses-permission android:name="android.permission.READ_SMS"/>
    <uses-permission android:name="android.permission.RECEIVE_WAP_PUSH"/>
    <uses-permission android:name="android.permission.RECEIVE_MMS"/>
    <uses-permission android:name="android.permission.READ_CELL_BROADCASTS"/>
    //android.permission-group.CAMERA(存储分组)
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

3 特殊权限
    特殊权限，顾名思义，就是一些特别敏感的权限，在Android系统中，主要由两个
    SYSTEM_ALERT_WINDOW，设置悬浮窗，进行一些黑科技
    WRITE_SETTINGS 修改系统设置
