//
//package com.dfrobot.angelo.blunobasicremote;
//
//import android.os.Bundle;
//import android.content.Intent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.content.SharedPreferences;
//
//public class MainActivity extends BlunoLibrary1 {
//	private Button buttonScan;
//	private Button buttonSerialSend;
//	private EditText serialSendText;
//	private TextView serialReceivedText;
//	private static final String PREFS_NAME = "BlunoPrefs";
//	private static final String KEY_DEVICE_ADDRESS = "DeviceAddress";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		onCreateProcess();
//
//		serialBegin(115200);
//
//		serialReceivedText = findViewById(R.id.serialReveicedText);
//		serialSendText = findViewById(R.id.serialSendText);
//
//		buttonSerialSend = findViewById(R.id.buttonSerialSend);
//		buttonSerialSend.setOnClickListener(v -> serialSend(serialSendText.getText().toString()));
//
//		buttonScan = findViewById(R.id.buttonScan);
//		buttonScan.setOnClickListener(v -> buttonScanOnClickProcess());
//
//		setupDirectionalButtons();
//
//		// Automatic connection logic
//		SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//		String savedDeviceAddress = preferences.getString(KEY_DEVICE_ADDRESS, null);
//
//		if (savedDeviceAddress != null) {
//			connect(savedDeviceAddress);
//		} else {
//			buttonScanOnClickProcess();
//		}
//	}
//
//	private void setupDirectionalButtons() {
//		findViewById(R.id.UP).setOnClickListener(v -> serialSend("U"));
//		findViewById(R.id.DN).setOnClickListener(v -> serialSend("D"));
//		findViewById(R.id.LH).setOnClickListener(v -> serialSend("L"));
//		findViewById(R.id.RH).setOnClickListener(v -> serialSend("R"));
//	}
//
//	@Override
//	public void onConectionStateChange(connectionStateEnum theConnectionState) {
//		super.onConectionStateChange(theConnectionState);
//		switch (theConnectionState) {
//			case isConnected:
//				buttonScan.setText("Connected");
//
//				// Save the connected device's address
//				String connectedDeviceAddress = getDeviceAddress();
//				SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//				preferences.edit().putString(KEY_DEVICE_ADDRESS, connectedDeviceAddress).apply();
//				break;
//
//			case isToScan:
//				buttonScan.setText("Scan");
//				break;
//
//			default:
//				break;
//		}
//	}
//
//	@Override
//	public void onSerialReceived(String theString) {
//		super.onSerialReceived(theString);
//		serialReceivedText.append(theString);
//		((ScrollView) serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
//	}
//}



package com.dfrobot.angelo.blunobasicremote;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity  extends BlunoLibrary1 {
	private Button buttonScan;
	private Button buttonSerialSend;
	private EditText serialSendText;
	private TextView serialReceivedText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        onCreateProcess();														//onCreate Process by BlunoLibrary


        serialBegin(115200);													//set the Uart Baudrate on BLE chip to 115200

        serialReceivedText=(TextView) findViewById(R.id.serialReveicedText);	//initial the EditText of the received data
        serialSendText=(EditText) findViewById(R.id.serialSendText);			//initial the EditText of the sending data

        buttonSerialSend = (Button) findViewById(R.id.buttonSerialSend);		//initial the button for sending the data
        buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend(serialSendText.getText().toString());				//send the data to the BLUNO
			}
		});

        buttonScan = (Button) findViewById(R.id.buttonScan);					//initial the button for scanning the BLE device
        buttonScan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				buttonScanOnClickProcess();										//Alert Dialog for selecting the BLE device
			}
		});

		//	Up/down/left/right/center

		buttonSerialSend = (Button) findViewById(R.id.UP);		//initial the button for sending the data
		buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend("U");				//send the data to the BLUNO
			}
		});
		buttonSerialSend = (Button) findViewById(R.id.DN);		//initial the button for sending the data
		buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend("D");				//send the data to the BLUNO
			}
		});
		buttonSerialSend = (Button) findViewById(R.id.LH);		//initial the button for sending the data
		buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend("L");				//send the data to the BLUNO
			}
		});
		buttonSerialSend = (Button) findViewById(R.id.RH);		//initial the button for sending the data
		buttonSerialSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				serialSend("R");				//send the data to the BLUNO
			}
		});
	}

	protected void onResume(){
		super.onResume();
		System.out.println("BlUNOActivity onResume");
		onResumeProcess();														//onResume Process by BlunoLibrary
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		onActivityResultProcess(requestCode, resultCode, data);					//onActivityResult Process by BlunoLibrary
		super.onActivityResult(requestCode, resultCode, data);
	}

    @Override
    protected void onPause() {
        super.onPause();
        onPauseProcess();														//onPause Process by BlunoLibrary
    }

	protected void onStop() {
		super.onStop();
		onStopProcess();														//onStop Process by BlunoLibrary
	}

	@Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

	@Override
	public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
		switch (theConnectionState) {											//Four connection state
		case isConnected:
			buttonScan.setText("Connected");
			break;
		case isConnecting:
			buttonScan.setText("Connecting");
			break;
		case isToScan:
			buttonScan.setText("Scan");
			break;
		case isScanning:
			buttonScan.setText("Scanning");
			break;
		case isDisconnecting:
			buttonScan.setText("isDisconnecting");
			break;
		default:
			break;
		}
	}

	@Override
	public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
		// TODO Auto-generated method stub
		serialReceivedText.append(theString);							//append the text into the EditText
		//The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.
		((ScrollView)serialReceivedText.getParent()).fullScroll(View.FOCUS_DOWN);
	}

}