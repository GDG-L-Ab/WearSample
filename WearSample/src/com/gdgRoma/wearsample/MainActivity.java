package com.gdgRoma.wearsample;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.preview.support.wearable.notifications.*;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Style;


public class MainActivity extends Activity {
	
	private Button butSimple;
	private Button butAction;
	private Button butBig;
	private Button butPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		butSimple = (Button) findViewById(R.id.but_simple);
		butSimple.setOnClickListener(butSimpleOnClick);
		
		butAction = (Button) findViewById(R.id.but_action);
		butAction.setOnClickListener(butActionOnClick);
		
		butBig =  (Button) findViewById(R.id.but_bigtext);
		butBig.setOnClickListener(butBigOnClick);
		
		butPage =  (Button) findViewById(R.id.but_page);
		butPage.setOnClickListener(butPageOnClick);
	}
	
	
	

	OnClickListener butSimpleOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			int notificationId = 001;
			
			NotificationCompat.Builder notificationBuilder =
			        new NotificationCompat.Builder(getApplicationContext())
			        .setSmallIcon(R.drawable.ic_launcher)
			        .setContentTitle("Simple!")
			        .setContentText("Questa è una notifica. That's easy!");

			NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

			notificationManager.notify(notificationId, notificationBuilder.build());
			
		}
	};
	
	
	
	
	OnClickListener butActionOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			int notificationId = 002;
			
			Intent viewIntent = new Intent(getApplicationContext(), ActionNotification.class);
			PendingIntent viewPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, viewIntent, 0);
			
			NotificationCompat.Builder notificationBuilder =
			        new NotificationCompat.Builder(getApplicationContext())
			        .setSmallIcon(R.drawable.ic_launcher)
			        .setContentTitle("Whit Action!")
			        .setContentText("Swipe verso destra per passare all'azione!")
		        	.setContentIntent(viewPendingIntent);

			NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

			notificationManager.notify(notificationId, notificationBuilder.build());
			
		}
	};
	
	
	
	OnClickListener butBigOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			int notificationId = 003;
			
			BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
			bigStyle.bigText("Questo è invece il testo, come vedete può essere molto, ma molto, molto, molto... più lungo del precendente.");

			NotificationCompat.Builder notificationBuilder =
			        new NotificationCompat.Builder(getApplicationContext())
			        .setSmallIcon(R.drawable.ic_launcher)
			        .setLargeIcon(BitmapFactory.decodeResource(
			                getResources(), R.drawable.background))
			        .setContentTitle("BigText")
			        //.setContentText("Questo è invece il testo")
			        .setStyle(bigStyle);
			
			NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

			notificationManager.notify(notificationId, notificationBuilder.build());
		}
	};
	
	
	
	OnClickListener butPageOnClick = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			int notificationId = 004;
			
			//Imposto il tipo per la prima pagina
			NotificationCompat.Builder notificationBuilder =
			        new NotificationCompat.Builder(getApplicationContext())
			        .setSmallIcon(R.drawable.ic_launcher)
			        .setContentTitle("Page 1")
			        .setContentText("Notifica con più pagine")
			        .setLargeIcon(BitmapFactory.decodeResource(
			                getResources(), R.drawable.background));
			        //.setContentIntent(viewPendingIntent);

			//Imposto il BigText per la seconda pagina
			BigTextStyle secondPageStyle = new NotificationCompat.BigTextStyle();
			secondPageStyle.setBigContentTitle("Page 2")
			               .bigText("Questo è invece il testo, come vedete può essere molto, ma molto, molto, molto... più lungo del precendente.");

			// Create second page notification
			Notification secondPageNotification =
			        new NotificationCompat.Builder(getApplicationContext())
			        .setStyle(secondPageStyle)
			        .build();

			// Create main notification and add the second page
			Notification twoPageNotification =
			        new WearableNotifications.Builder(notificationBuilder)
			        .addPage(secondPageNotification)
			        .build();
			
			
			NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
			notificationManager.notify(notificationId, twoPageNotification);
			
		}
	};
	
}
