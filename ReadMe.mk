#Android 6.0 权限适配问题

##1 权限分类：
    1.1 一般权限，生命即可使用，无需做特殊处理。
    1.2 特殊权限，需要动态授权。
    1.3 危险权限，也成运行时权限，需要动态的在使用时授权，如果未授权直接使用会导致崩溃。

##2 建议
    2.1 添加权限时请分类添加，可分为一般权限，运行时权限和特殊权限
        一般权限：
	    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
	    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
	    <uses-permission android:name="android.permission.INTERNET"/>
	    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
	    <uses-permission android:name="android.permission.VIBRATE"/>
	    <uses-permission android:name="android.permission.WAKE_LOCK"/>
	    <uses-permission android:name="getui.permission.GetuiService.com.baidao.ytxmobile"/>
	    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
	    <uses-permission android:name="android.permission.GET_TASKS"/>
	    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
	    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS"/>
        特殊权限：
        <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
        危险权限：(运行时权限)
        //Phone组
        <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
        //MicroPhone 麦克风组
        <uses-permission android:name="android.permission.RECORD_AUDIO"/>
        //CAMERA 相机组
        <uses-permission android:name="android.permission.CAMERA"/>
        //Location  定位组
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
        //STORAGE  存储组
        <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    2.2 处理要用到运行是权限或者特殊权限的方法时，尽量将方法写在Activity或者fragment里面。
    2.3 设计时请设计好，当用户拒绝相关权限后的处理方式。

##3 权限分类参考表
    3.1 一般权限
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
		INSTALL_SHORTCUT
		INTERNET
		KILL_BACKGROUND_PROCESSES
		MODIFY_AUDIO_SETTINGS
		NFC
		READ_SYNC_SETTINGS
		READ_SYNC_STATS
		RECEIVE_BOOT_COMPLETED
		REORDER_TASKS
		REQUEST_INSTALL_PACKAGES
		SET_ALARM
		SET_TIME_ZONE
		SET_WALLPAPER
		SET_WALLPAPER_HINTS
		TRANSMIT_IR
		UNINSTALL_SHORTCUT
		USE_FINGERPRINT
		VIBRATE
		WAKE_LOCK
		WRITE_SYNC_SETTINGS
    3.2 特殊权限
        SYSTEM_ALERT_WINDOW，设置悬浮窗，进行一些黑科技
		WRITE_SETTINGS 修改系统设置
    3.3 危险权限
        Permission Group	Permissions
		CALENDAR
							READ_CALENDAR
							WRITE_CALENDAR
		CAMERA
							CAMERA
		CONTACTS
							READ_CONTACTS
							WRITE_CONTACTS
							GET_ACCOUNTS
		LOCATION
							ACCESS_FINE_LOCATION
							ACCESS_COARSE_LOCATION
		MICROPHONE
							RECORD_AUDIO
		PHONE
							READ_PHONE_STATE
							CALL_PHONE
							READ_CALL_LOG
							WRITE_CALL_LOG
							ADD_VOICEMAIL
							USE_SIP
							PROCESS_OUTGOING_CALLS
		SENSORS
							BODY_SENSORS
		SMS
							SEND_SMS
							RECEIVE_SMS
							READ_SMS
							RECEIVE_WAP_PUSH
							RECEIVE_MMS
		STORAGE
							READ_EXTERNAL_STORAGE
							WRITE_EXTERNAL_STORAGE

