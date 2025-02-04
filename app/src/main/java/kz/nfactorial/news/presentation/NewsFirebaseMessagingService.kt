package kz.nfactorial.news.presentation

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NewsFirebaseMessagingService : FirebaseMessagingService(){

    override fun onCreate() {
        super.onCreate()
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

}