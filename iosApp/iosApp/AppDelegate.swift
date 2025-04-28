//
//  AppDelegate.swift
//  iosApp
//
//  Created by Tio Fani on 28/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import ComposeApp

class AppDelegate : NSObject, UIApplicationDelegate{
    private let appDelegateAdapter = AppDelegateAdapter()
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil) -> Bool {
        return appDelegateAdapter.application(application: application, didFinishLaunchingWithOptions: launchOptions)
    }
}
