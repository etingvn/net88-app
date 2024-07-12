package com.example.net88app

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.net88app.ui.theme.Net88AppTheme

class MainActivity : ComponentActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://net88.vip")
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // JavaScript để click vào button đăng nhập
                webView.evaluateJavascript(
                    """
                    (function() {
                        var buttons = document.getElementsByTagName('button');
                        var existElement = document.getElementById('hiddenInputId');
                        for (var i = 0; i < buttons.length; i++) {
                            if (buttons[i].innerText === 'Đăng nhập' && existElement === null) {
                                buttons[i].click();
                                var hiddenInput = document.createElement('input');
                                hiddenInput.type = 'hidden';
                                hiddenInput.id = 'hiddenInputId';
                                document.body.appendChild(hiddenInput);    
                                break;
                            }
                        }
                    })();
                    """.trimIndent()
                ) {
                    //
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Helloxxx $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Net88AppTheme {
        Greeting("Android")
    }
}