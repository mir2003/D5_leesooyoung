package org.androidtown.ui.pager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WebPage extends LinearLayout {

//	Animation translateLeftAnim;
//	Animation translateRightAnim;
//	LinearLayout slidingPage01; //슬라이딩으로 보여지는 페이지 레이아웃
//	Button showTextButton;
//	// android:text=" △ "
//	slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01); // 슬라이딩으로 보여질 레이아웃 객체 참조
//	// 애니메이션 객체 로딩
//	translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
//	translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);
//
//	// 애니메이션 객체에 리스너 설정
//	SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
//	translateLeftAnim.setAnimationListener(animListener);
//	translateRightAnim.setAnimationListener(animListener);






	Context mContext;
	TextView urlText;
	private static final String TAG = "MainActivity"; //로그를 위한 태그
	private WebView webview; //웹뷰 객체
	private Button loadButton; //웹사이트 로딩을 위한 버튼
	private Handler mHandler = new Handler(); //핸들러 객체

	public WebPage(Context context) {
		super(context);
		init(context);
	}

	public WebPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;

		// inflate XML layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.web_page, this, true);

		urlText = (TextView) findViewById(R.id.urlText);
		loadButton = (Button) findViewById(R.id.loadButton);

		// 웹뷰 객체 참조
		webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient(new WebViewClient());

		// 웹뷰 설정 정보
		WebSettings webSettings = webview.getSettings();
		webSettings.setJavaScriptEnabled(true);

		final EditText urlText = (EditText) findViewById(R.id.urlText);

		// 버튼 이벤트 처리
		loadButton = (Button) findViewById(R.id.loadButton);
		loadButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				webview.loadUrl(urlText.getText().toString()); // 입력한 URL의 페이지 로딩
			}
		});
	}

	final class WebBrowserClient extends WebChromeClient {
		public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
			Log.d(TAG, message);
			result.confirm();

			return true;
		}
	}

	public String getUrlText() {
		return urlText.getText().toString();
	}

	public void setUrlText(String urlText1) {
		urlText.setText(urlText1);
	}
}

