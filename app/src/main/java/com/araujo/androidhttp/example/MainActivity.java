package com.araujo.androidhttp.example;

import com.araujo.androidhttp.example.model.Ip;
import com.araujo.androidhttp.example.service.TestService;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.araujo.androidhttp.listener.service.ServiceListener;
import io.araujo.androidhttp.webservice.PersistType;
import io.araujo.androidhttp.webservice.RetrieveFrom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private TextView memoryDatabaseView;
	private TextView serverView;

	private ServiceListener serviceListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		memoryDatabaseView = (TextView) findViewById(R.id.memory_database_view);
		serverView = (TextView) findViewById(R.id.server_view);
		findViewById(R.id.button_server_no_persist).setOnClickListener(this);
		findViewById(R.id.button_database_server_no_persist).setOnClickListener(this);
		findViewById(R.id.button_server_persist_database).setOnClickListener(this);
		findViewById(R.id.button_server_persist_memory).setOnClickListener(this);
		findViewById(R.id.button_database).setOnClickListener(this);
		findViewById(R.id.button_memory).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		clean();
		if (view.getId() == R.id.button_server_no_persist) {
			callServerWithoutPersisting();
		} else if (view.getId() == R.id.button_database_server_no_persist) {
			callDatabaseAndServerWithoutPersisting();
		} else if (view.getId() == R.id.button_server_persist_database) {
			callServerPersistingDatabase();
		} else if (view.getId() == R.id.button_server_persist_memory) {
			callServerPersistingMemory();
		} else if (view.getId() == R.id.button_database) {
			callDatabase();
		} else if (view.getId() == R.id.button_memory) {
			callMemory();
		} else if (view.getId() == R.id.button_best) {
			callBest();
		}
	}

	private void clean() {
		memoryDatabaseView.setText("Memory/Database: ");
		serverView.setText("Server: ");
	}

	private void callServerWithoutPersisting() {
		new TestService().retrieveFrom(RetrieveFrom.SERVER).execute(null, getServiceListener());
	}

	private void callDatabaseAndServerWithoutPersisting() {
		new TestService().retrieveFrom(RetrieveFrom.BEFORE_CALL).execute(null, getServiceListener());
	}

	private void callServerPersistingMemory() {
		new TestService().retrieveFrom(RetrieveFrom.SERVER).persist(PersistType.MEMORY, PersistType.DATABASE).execute(null, getServiceListener());
	}

	private void callServerPersistingDatabase() {
		new TestService().retrieveFrom(RetrieveFrom.SERVER).persist(PersistType.MEMORY, PersistType.DATABASE).execute(null, getServiceListener());
	}

	private void callDatabase() {
		Ip ip = Ip.findFirst(Ip.class);
		if (ip != null) {
			setValue(memoryDatabaseView, "Database: " + ip.getIp());
		} else {
			setValue(memoryDatabaseView, "Database: " + ip.getIp());
		}
	}

	private void callMemory() {
		Ip ip = Ip.findFirstFromMemory(Ip.class);
		if (ip != null) {
			setValue(memoryDatabaseView, "Memory: " + ip.getIp());
		} else {
			setValue(memoryDatabaseView, "Memory: ");
		}
	}

	private void callBest() {
		Ip ip = Ip.findFirstBest(Ip.class);
		if (ip != null) {
			setValue(memoryDatabaseView, "Database/Memory: " + ip.getIp());
		} else {
			setValue(memoryDatabaseView, "Database/Memory: " + ip.getIp());
		}
	}

	private ServiceListener getServiceListener() {
		if (serviceListener == null) {
			serviceListener = new ServiceListener<Ip>() {
				@Override
				public void onSuccess(Ip object, RetrieveFrom from) {
					if (from.equals(RetrieveFrom.BEFORE_CALL)) {
						if (object != null) {
							setValue(memoryDatabaseView, "Memory/Database" + object.getIp());
						}
					} else if (from.equals(RetrieveFrom.SERVER)) {
						setValue(serverView, "Server: " + object.getIp());
					}
				}
			};
		}
		return serviceListener;
	}

	private void setValue(TextView view, String text) {
		view.setText(text);
	}

}
