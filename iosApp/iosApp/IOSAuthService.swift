import ComposeApp
import FirebaseAuth
import FirebaseCore
import GoogleSignIn
import UIKit

class IOSAuthService: AuthService {
    func signIn(email: String, password: String, callback: @escaping (KotlinBoolean, String?) -> Void) {
        Auth.auth().signIn(withEmail: email, password: password) { result, error in
            if let error = error {
                callback(false, error.localizedDescription)
            } else {
                callback(true, nil)
            }
        }
    }
    
    func signInWithGoogle(callback: @escaping (KotlinBoolean, String?) -> Void) {
        guard let clientID = FirebaseApp.app()?.options.clientID else {
            print("No client ID found.")
            return
        }

        let config = GIDConfiguration(clientID: clientID)
        GIDSignIn.sharedInstance.configuration = config

        guard let presentingVC = UIApplication.shared.connectedScenes
            .compactMap({ $0 as? UIWindowScene })
            .flatMap({ $0.windows })
            .first(where: { $0.isKeyWindow })?.rootViewController else {
                print("No presenting view controller.")
                callback(false, "No presenting view controller.")
                return
        }

        GIDSignIn.sharedInstance.signIn(withPresenting: presentingVC) { signInResult, error in
            if let error = error {
                print("Google sign-in error: \(error.localizedDescription)")
                callback(false, error.localizedDescription)
                return
            }

            guard
                let result = signInResult,
                let idToken = result.user.idToken?.tokenString
            else {
                print("Missing auth tokens from Google user.")
                callback(false, "Missing authentication tokens.")
                return
            }

            let accessToken = result.user.accessToken.tokenString
            let credential = GoogleAuthProvider.credential(withIDToken: idToken, accessToken: accessToken)

            Auth.auth().signIn(with: credential) { authResult, error in
                if let error = error {
                    print("Firebase sign-in with Google error: \(error.localizedDescription)")
                    callback(false, error.localizedDescription)
                } else {
                    let email = authResult?.user.email ?? "Unknown"
                    print("Google sign-in success: \(email)")
                    callback(true, nil)
                }
            }
        }
    }
}
