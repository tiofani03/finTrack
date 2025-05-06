import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
    init() {
               let authService = IOSAuthService()
               KoinKt.doInitKoin(authService: authService)
           }
}
