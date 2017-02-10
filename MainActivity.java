package com.lxtest;

import com.facebook.react.ReactActivity;
import android.os.Bundle;


//import com.lxtest.util.AppDemo;
import com.lxtest.MainApplication;
import com.haier.uhome.usdk.api.interfaces.IuSDKCallback;
import com.haier.uhome.usdk.api.interfaces.IuSDKDeviceManagerListener;
import com.haier.uhome.usdk.api.interfaces.IuSDKCallback;
import com.haier.uhome.usdk.api.interfaces.IuSDKDeviceManagerListener;
import com.haier.uhome.usdk.api.interfaces.IuSDKManagerListener;
import com.haier.uhome.usdk.api.uSDKCloudConnectionState;
import com.haier.uhome.usdk.api.uSDKDevice;
import com.haier.uhome.usdk.api.uSDKDeviceManager;
import com.haier.uhome.usdk.api.uSDKErrorConst;
import com.haier.uhome.usdk.api.uSDKManager;

import java.util.List;

public class MainActivity extends ReactActivity {
    private uSDKManager uSDKMgr = uSDKManager.getSingleInstance();
    private uSDKDeviceManager uSDKDeviceMgr = uSDKDeviceManager.getSingleInstance();
    private List<uSDKDevice> allSightedDevices = null;
    private MainApplication mainApplication;
    private String Tag="lx-SDK";

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "lxTest";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initDeviceInteractive();
    }
    /**
     * <pre>
     * 注冊設備管理
     * 啟動uSDK
     *
     * 1.register uSDK smart device managment listner
     * 2.start uSDK
     * </pre>
     */
    private void initDeviceInteractive() {
        registerDevicesManageLogic();
        this.startuSDK();

    }
    /**
     * 啟動uSDK
     * start uSDK
     */
    private void startuSDK() {
        uSDKMgr.startSDK(this.getApplicationContext(), new IuSDKCallback() {

            @Override
            public void onCallback(uSDKErrorConst uSDKStartResult) {
                //使用此API查询uSDK启动状态
                //Is uSDK started?
                //boolean appQueryuSDKisStatus = uSDKMgr.isSDKStart();
                if (uSDKStartResult == uSDKErrorConst.RET_USDK_OK) {
                    initAutoFoundUnjoinedSmartDevice();
                    refreshSightedDeviceList(null);

                    String usdkver = uSDKMgr.getuSDKVersion();

                    System.out.println(Tag+"SDK started++++++");
                    //uSDKRunStatusTextView.setText(R.string.tip_uSDK_status_start);

                } else {
                    System.out.println(Tag+"SDK unstart+++++++++");
                   // uSDKRunStatusTextView.setText(R.string.tip_uSDK_status_notstarted);

                }

            }

        });

    }
    /**
     * 開啟發現未入網智能設備的功能
     * begin to search unjoined smart device
     */
    private void initAutoFoundUnjoinedSmartDevice() {
        mainApplication = (MainApplication) this.getApplication();
        //mainApplication.helpSmartDeviceAutoJoinWifi(uSDKDeviceMgr);
    }
    private void registerDevicesManageLogic() {
        uSDKDeviceMgr.setDeviceManagerListener(new IuSDKDeviceManagerListener() {

            /**
             * 設備管理業務-uSDK剔除无法交互的设备
             * smart devices become unsighted
             */
            @Override
            public void onDevicesRemove(List<uSDKDevice> devicesChanged) {
                refreshSightedDeviceList(devicesChanged);
                System.out.println("remove exec....");
            }

            /**
             * 設備管理業務-uSDK發現一臺可用設備
             * Device Management-uSDK device report event:
             */
            @Override
            public void onDevicesAdd(List<uSDKDevice> devicesChanged) {
                refreshSightedDeviceList(devicesChanged);
                System.out.println("add exec....");
            }

            /**
             * <pre>
             * 遠程服務器推送設備解綁定消息
             * remote server push a msg when register a smart device on user's account
             * app need to connect remote server first.
             * </pre>
             */
            @Override
            public void onDeviceUnBind(String devicesChanged) {
                System.out.println("Remote Server push msg:"
                        + devicesChanged + "has been removed from accout");

            }

            /**
             * <pre>
             * 遠程服務器推送設備綁定消息
             * remote server push a msg when unregister a smart device from user's account
             * app need to connect remote server first.
             * </pre>
             */
            @Override
            public void onDeviceBind(String devicesChanged) {
                System.out.println("Remote Server push msg:"
                        + devicesChanged + "has been added to accout");

            }

            /**
             * <pre>
             * App在此可以得到遠程服務器連接狀態
             * App get connection status of remote server here
             * </pre>
             */
            @Override
            public void onCloudConnectionStateChange(uSDKCloudConnectionState status) {
                //遠程服務器已連接
                //remote server connected
                if (status == uSDKCloudConnectionState.CLOUD_CONNECTION_STATE_CONNECTED) {
                    System.out.println("uSDK3XDemo connect to Remote Server");

                }

            }
        });

    }

    private void refreshSightedDeviceList(List<uSDKDevice> devicesChanged) {
        //直接刷新uSDK可发现的所有设备
        allSightedDevices = uSDKDeviceMgr.getDeviceList();
        //appManagedDevicesListViewAdapter.setDataSource(allSightedDevices);
        //appManagedDevicesListViewAdapter.notifyDataSetChanged();
    }

}
