package com.example.chattestapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.chattestapp.R
import com.example.chattestapp.data.Credentials
import com.example.chattestapp.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import zendesk.chat.Chat
import zendesk.chat.ChatConfiguration
import zendesk.classic.messaging.MessagingActivity
import zendesk.core.AnonymousIdentity
import zendesk.core.Zendesk
import zendesk.support.Support
import zendesk.support.SupportEngine
import zendesk.support.request.RequestActivity
import zendesk.support.requestlist.RequestListActivity


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(applicationModules)
        }
        setContentView(R.layout.activity_main)

        val buttonOpenChat = findViewById<Button>(R.id.openChatButton)
        val buttonCreateRequest = findViewById<Button>(R.id.createRequestButton)
        val buttonShowAllTicketsRequest = findViewById<Button>(R.id.showAllTicketsButton)
        val errorMessage = findViewById<TextView>(R.id.errorMessage)

        viewModel.credentials.observe(this) {
            Chat.INSTANCE.init(this, it);
        }
        viewModel.isMissingCredentials.observe(this) {
            if (it) {
                errorMessage.visibility = View.VISIBLE
                buttonCreateRequest.visibility = View.GONE
                buttonOpenChat.visibility = View.GONE
                buttonShowAllTicketsRequest.visibility = View.GONE
            }
        }
        viewModel.onOpenChatButtonClick.observe(this) {
            openChat()
        }
        viewModel.onCreateRequestButtonClick.observe(this) {
            openRequestActivity()
        }
        viewModel.onShowAllTicketsButtonClick.observe(this) {
            showAllTickets()
        }
        viewModel.initZendesk.observe(this) {
            initZendeskSupport()
        }

        buttonOpenChat.setOnClickListener {
            viewModel.onOpenChatClick()
        }
        buttonCreateRequest.setOnClickListener {
            viewModel.onCreateRequestButtonClick()
        }
        buttonShowAllTicketsRequest.setOnClickListener {
            viewModel.onShowAllTicketsClick()
        }
    }

    private fun initZendeskSupport() {
        Zendesk.INSTANCE.init(
            this,
            Credentials.SUBDOMAIN_URL,
            Credentials.APPLICATION_ID,
            Credentials.OAUTH_CLIENT_ID
        )
        // Anonymous (All fields are optional)
        Zendesk.INSTANCE.setIdentity(
            AnonymousIdentity.Builder()
                .withNameIdentifier("{optional name}")
                .withEmailIdentifier("{optional email}")
                .build()
        )
        Support.INSTANCE.init(Zendesk.INSTANCE)
    }

    private fun openChat() {
        val chatConfiguration = ChatConfiguration.builder().build()
        MessagingActivity.builder()
            .withEngines(SupportEngine.engine())
            .show(this, chatConfiguration)
    }

    private fun openRequestActivity() {
        RequestActivity.builder().show(this)
    }

    private fun showAllTickets() {
        RequestListActivity.builder().show(this)
    }

}