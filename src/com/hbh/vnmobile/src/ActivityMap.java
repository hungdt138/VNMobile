package com.hbh.vnmobile.src;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.hbh.vnmobile.src.adapter.StoreAdapter;
import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Store;
import com.hbh.vnmobile.src.obj.Stores;
import com.hbh.vnmobile.src.R;


public class ActivityMap extends BaseActivity implements OnClickListener {

	/*
	 * 21.011688,105.820757 52 Trung Liệt, Đống Đa, Hà Nội | Cty CP Hmobile
	 * 20.992705,105.850665| số 750 Trương Định, Hoàng Mai, Hà Nội | Anh Xuân
	 * 21.035235,105.910676| 52 Sài Đồng - Long Biên - HN|CH Nguyễn Phương Anh
	 * 20.984146,105.793142|67 Trần Phú - Hà Đông - Hà Nội|CH Phú Thịnh
	 * 21.043481,105.842317|Số 78c Nguyễn trường Tộ, Hà Nội|CH Tiến Oanh
	 */

	private GoogleMap map;
	static final LatLng HANOI = new LatLng(21.033333, 105.85);

	// static final LatLng KIEL = new LatLng(53.551, 9.993);
	private LocationManager locationManager;
	private Location location;
	private boolean hasGpsProvider, hasNetwrokProvider;
	private LinearLayout layoutList;
	private RelativeLayout layoutMap;

	private Button btn_map, btn_list, btn_add, btn_gohome;

	private StoreAdapter adapter;
	private ListView listview;

	public static double currentLat = 0;
	public static double currentLong = 0;

	@SuppressLint("NewApi")
	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_map);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		layoutList = (LinearLayout) findViewById(R.id.layoutList);
		layoutMap = (RelativeLayout) findViewById(R.id.layoutMap);

		listview = (ListView) findViewById(R.id.listview);

		btn_list = (Button) findViewById(R.id.btn_list);
		btn_list.setOnClickListener(this);

		btn_map = (Button) findViewById(R.id.btn_map);
		btn_map.setOnClickListener(this);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);

		btn_gohome = (Button) findViewById(R.id.gohome);
		btn_gohome.setOnClickListener(this);

		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		if (map != null) {
			loadData();
			initMap();
		}
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	Stores stores = new Stores();

	protected void loadData() {
		String str = readFile(Constants.TXT_STORE_FILE_NAME);
		if (str != null) {
			stores = gson.fromJson(str, Stores.class);
			adapter = new StoreAdapter(getBaseContext(), 0, stores.stores);
			listview.setAdapter(adapter);
			listview.setSelector(R.drawable.selector_btn);
			adapter.notifyDataSetChanged();

			//
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					// if (!stores.stores.get(arg2).markerid.equals(me.getId()))
					{
						Intent intent = new Intent(ActivityMap.this,
								ActivityDistributor.class);
						Bundle b = new Bundle();
						b.putInt(Constants.TXT_ID, stores.stores.get(arg2).id);
						b.putString(Constants.TXT_NAME,
								stores.stores.get(arg2).name);
						b.putString(Constants.TXT_TYPE,
								stores.stores.get(arg2).type);
						b.putString(Constants.TXT_PROVINCE,
								stores.stores.get(arg2).province);
						b.putString(Constants.TXT_DISTRICT,
								stores.stores.get(arg2).district);
						b.putString(Constants.TXT_ADDRESS,
								stores.stores.get(arg2).address);
						b.putString(Constants.TXT_STK,
								stores.stores.get(arg2).stk);
						b.putString(Constants.TXT_MOBILE,
								stores.stores.get(arg2).mobile);
						b.putString(Constants.TXT_CONTACT,
								stores.stores.get(arg2).contact);
						b.putString(Constants.TXT_STATUS,
								stores.stores.get(arg2).status);
						b.putString(Constants.TXT_DESCRIPTION,
								stores.stores.get(arg2).description);
						startActivity(intent.putExtras(b));
					}
				}
			});

		}
	}

	Marker me;

	protected void initMap() {

		hasGpsProvider = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		hasNetwrokProvider = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (hasGpsProvider) {
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 100, locationlistener);
			location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		} else
			Toast.makeText(getBaseContext(), "Chưa bật thiết bị định vị GPS",
					3000).show();

		if (hasNetwrokProvider) {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 100, locationlistener);
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		} else
			Toast.makeText(getBaseContext(),
					"Chưa có kết nối Internet, không tải được dữ liệu", 3000)
					.show();
		currentLat = location.getLatitude();
		currentLong = location.getLongitude();

		LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

		// Marker hanoi = map.addMarker(new MarkerOptions().position(ll)
		// .title("Hà Nội"));
		Store s = new Store(stores.stores.size(), "I'm here", "Hello", "", "",
				"", "", "", "", "", "", location.getLatitude(),
				location.getLongitude());
		me = map.addMarker(new MarkerOptions().position(ll).title(s.name)
				.snippet(s.address)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_me)));
		s.markerid = me.getId();

		// stores.stores.add(s);

		for (int i = 0; i < stores.stores.size() - 1; i++) {
			LatLng ll2 = new LatLng(stores.stores.get(i).latitude,
					stores.stores.get(i).longitude);
			Marker lo = map
					.addMarker(new MarkerOptions()
							.position(ll2)
							.title(stores.stores.get(i).name)
							.snippet(stores.stores.get(i).address)
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.pin)));
			stores.stores.get(i).markerid = lo.getId();
		}

		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

			@Override
			public void onInfoWindowClick(Marker marker) {
				// TODO Auto-generated method stub
				for (int i = 0; i < stores.stores.size() - 1; i++)
					if (stores.stores.get(i).markerid.equals(marker.getId())) {
						Intent intent = new Intent(ActivityMap.this,
								ActivityDistributor.class);
						Bundle b = new Bundle();
						b.putInt(Constants.TXT_ID, stores.stores.get(i).id);
						b.putString(Constants.TXT_NAME,
								stores.stores.get(i).name);
						b.putString(Constants.TXT_TYPE,
								stores.stores.get(i).type);
						b.putString(Constants.TXT_PROVINCE,
								stores.stores.get(i).province);
						b.putString(Constants.TXT_DISTRICT,
								stores.stores.get(i).district);
						b.putString(Constants.TXT_ADDRESS,
								stores.stores.get(i).address);
						b.putString(Constants.TXT_STK, stores.stores.get(i).stk);
						b.putString(Constants.TXT_MOBILE,
								stores.stores.get(i).mobile);
						b.putString(Constants.TXT_CONTACT,
								stores.stores.get(i).contact);
						b.putString(Constants.TXT_STATUS,
								stores.stores.get(i).status);
						b.putString(Constants.TXT_DESCRIPTION,
								stores.stores.get(i).description);
						startActivity(intent.putExtras(b));
					}
			}
		});

	}

	LocationListener locationlistener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub

		}
	};

	boolean onMap = true;

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.gohome:
			if (map != null) {
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 15));
				map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
			}
			break;
		case R.id.btn_add:
			Bundle b = new Bundle();
			b.putString(Constants.TXT_TYPE_CHANGE, Constants.TXT_TYPE_ADD);
			Intent inten = new Intent(ActivityMap.this,
					ActivityEditDistributor.class);

			startActivity(inten.putExtras(b));
			break;
		case R.id.btn_map:
			if (!onMap) {
				onMap = true;
				//
				AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
				aa.setDuration(300);
				AlphaAnimation aa1 = new AlphaAnimation(1.0f, 0.0f);
				aa1.setDuration(300);
				//
				layoutMap.setVisibility(View.VISIBLE);
				layoutList.setVisibility(View.INVISIBLE);

				// layoutMap.startAnimation(aa);
				// layoutMap.startAnimation(aa1);
				aa1.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						layoutList.setVisibility(View.INVISIBLE);
					}
				});
			}
			break;

		case R.id.btn_list:
			if (onMap) {
				onMap = false;
				//
				AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
				aa.setDuration(300);
				AlphaAnimation aa1 = new AlphaAnimation(1.0f, 0.0f);
				aa1.setDuration(300);
				//

				layoutList.setVisibility(View.VISIBLE);
				layoutMap.setVisibility(View.INVISIBLE);

				// layoutList.startAnimation(aa);
				// layoutMap.startAnimation(aa1);
				aa1.setAnimationListener(new AnimationListener() {

					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						layoutMap.setVisibility(View.INVISIBLE);
					}
				});
			}
			break;

		default:
			break;
		}
	}
}
