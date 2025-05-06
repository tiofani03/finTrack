package com.tiooooo.fintrack

//import com.tiooooo.fintrack.adaptor.NetfoxManager
import platform.UIKit.UIApplication
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "AppDelegateAdapter")
class AppDelegateAdapter {

    fun application(
        application: UIApplication,
        didFinishLaunchingWithOptions: Map<Any, *>?
    ): Boolean {
//        NetfoxManager.startNetfox()
        return true
    }
}