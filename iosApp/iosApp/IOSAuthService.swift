import ComposeApp
import FirebaseAuth

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
}
