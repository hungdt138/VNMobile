package com.hbh.vnmobile.src;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.hbh.vnmobile.src.constants.Constants;
import com.hbh.vnmobile.src.obj.Appointment;
import com.hbh.vnmobile.src.obj.Appointments;
import com.hbh.vnmobile.src.obj.Kpi;
import com.hbh.vnmobile.src.obj.Kpis;
import com.hbh.vnmobile.src.obj.Order;
import com.hbh.vnmobile.src.obj.Orders;
import com.hbh.vnmobile.src.obj.Product;
import com.hbh.vnmobile.src.obj.Products;
import com.hbh.vnmobile.src.obj.Promotion;
import com.hbh.vnmobile.src.obj.Promotions;
import com.hbh.vnmobile.src.obj.Service;
import com.hbh.vnmobile.src.obj.Services;
import com.hbh.vnmobile.src.obj.Store;
import com.hbh.vnmobile.src.obj.Stores;
import com.hbh.vnmobile.src.obj.Survey;
import com.hbh.vnmobile.src.obj.Surveys;

public class Splash extends BaseActivity {

	ImageView vnn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub

		setContentView(R.layout.activity_splash);
		vnn = (ImageView) findViewById(R.id.vnn);
		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(1500);
		vnn.startAnimation(aa);

		restoreUIState();
		if (isFirst) {
			System.out.println("IS FIRST");
			Stores s = new Stores();
			Store s1 = new Store(1, "Cty CP Hmobile",
					"52 Trung Liệt, Đống Đa, Hà Nội", "distributor", "Hanoi",
					"Đống Đa", "Nguyễn Phương A", "0925105600",
					"0925120144, 0925020299", "Open", "", 21.011688, 105.820757);
			s.stores.add(s1);

			Store s2 = new Store(2, "Anh Xuân",
					"số 750 Trương Định, Hoàng Mai, Hà Nội", "distributor",
					"Hanoi", "Hoàng Mai", "Nguyễn Phương B", "09251222333",
					"0925120144, 0925020299", "Open", "", 20.992705, 105.850665);
			s.stores.add(s2);

			Store s3 = new Store(3, "CH Nguyễn Phương Anh",
					"52 Sài Đồng - Long Biên - HN", "distributor", "Hanoi",
					"Long Biên", "Nguyễn Phương C", "0925105333",
					"0925120144, 0925020299", "Open", "", 21.035235, 105.910676);
			s.stores.add(s3);

			Store s4 = new Store(4, "CH Phú Thịnh",
					"67 Trần Phú - Hà Đông - Hà Nội", "distributor", "Hanoi",
					"Hà Đông", "Nguyễn Phương D", "0925105444",
					"0925120144, 0925020299", "Open", "", 20.984146, 105.793142);
			s.stores.add(s4);

			Store s5 = new Store(5, "CH Tiến Oanh",
					"Số 78c Nguyễn trường Tộ, Hà Nội", "distributor", "Hanoi",
					"Ba Đình", "Nguyễn Phương E", "0925105555",
					"0925120144, 0925020299", "Open", "", 21.043481, 105.842317);
			s.stores.add(s5);

			String str = gson.toJson(s);

			writeFile(str, Constants.TXT_STORE_FILE_NAME);

			// ------------------------------

			Surveys surveys = new Surveys();
			Survey sur1 = new Survey(1, "CH Tiến Oanh", "06/05/2013",
					"08/05/2013", "Trang NH", "21.043481/105.842317",
					"rat tot", true, false, true, false, true);
			surveys.surveys.add(sur1);

			Survey sur2 = new Survey(1, "CH Phú Thịnh", "06/05/2013",
					"08/05/2013", "Trang NH", "20.984146/105.793142",
					"rat tot", true, false, true, false, true);
			surveys.surveys.add(sur2);

			Survey sur3 = new Survey(1, "CH Anh Xuânh", "06/05/2013",
					"08/05/2013", "Trang NH", "20.992705/105.850665",
					"rat tot", true, false, true, false, true);
			surveys.surveys.add(sur3);

			Survey sur4 = new Survey(1, "Cty CP Hmobile", "06/05/2013",
					"08/05/2013", "Trang NH", "21.011688/105.820757",
					"rat tot", true, false, true, false, true);
			surveys.surveys.add(sur4);

			String str2 = gson.toJson(surveys);

			writeFile(str2, Constants.TXT_SURVEY_FILE_NAME);

			// -------------------------------------------------
			Kpis kpis = new Kpis();
			Kpi k1 = new Kpi(1, "CH Tiến Oanh", "06/05/2013", "08/05/2013",
					"Trang NH", "21.043481/105.842317", "100", "100", "100",
					"100", "100");
			kpis.kpis.add(k1);

			Kpi k2 = new Kpi(2, "CH Phú Thịnh", "06/05/2013", "08/05/2013",
					"Trang NH", "20.984146/105.793142", "100", "100", "100",
					"100", "100");
			kpis.kpis.add(k2);

			Kpi k3 = new Kpi(3, "CH Anh Xuânh", "06/05/2013", "08/05/2013",
					"Trang NH", "20.992705/105.850665", "100", "100", "100",
					"100", "100");
			kpis.kpis.add(k3);

			Kpi k4 = new Kpi(4, "Cty CP Hmobile", "06/05/2013", "08/05/2013",
					"Trang NH", "21.011688/105.820757", "100", "100", "100",
					"100", "100");
			kpis.kpis.add(k4);

			String str3 = gson.toJson(kpis);

			writeFile(str3, Constants.TXT_KPI_FILE_NAME);

			// ---------------------------------------------------

			Appointments apps = new Appointments();
			Appointment a1 = new Appointment(1, "Nguyen Van A", "122 Giang Vo",
					"meeting", "06/06", "8:00", "open", "");
			apps.appointments.add(a1);

			Appointment a2 = new Appointment(2, "Ha Van A", "122 Giang Vo",
					"meeting", "08/06", "8:00", "open", "");
			apps.appointments.add(a2);

			Appointment a3 = new Appointment(3, "Pham Van A", "122 Giang Vo",
					"meeting", "09/06", "8:00", "open", "");
			apps.appointments.add(a3);

			Appointment a4 = new Appointment(4, "Ho Van A", "122 Giang Vo",
					"meeting", "10/06", "8:00", "open", "");
			apps.appointments.add(a4);

			String str4 = gson.toJson(apps);

			writeFile(str4, Constants.TXT_APPOINTMENT_FILE_NAME);

			// ----------------------------------------

			Products products = new Products();
			Product p1 = new Product(
					1,
					"Nạp tiền trực tuyến",
					"Vietnamobile trân trọng giới thiệu dịch vụ Nạp Tiền Trực Tuyến - phối hợp với đối tác Banknet và SmartLink. Đây là dịch vụ nạp tiền điện thoại được cung cấp cho các khách hàng là thuê bao trả trước và trả sau.\n"
							+ "Lợi ích nạp tiền điện thoại trực tuyến:"
							+ "\n\n- Nạp tiền điện thoại không cần thẻ cào & không cần mã thẻ"
							+ "\n- Số tiền được nạp thẳng vào tài khoản điện thoại của bạn ngay khi giao dịch hoàn thành."
							+ "\n- Nạp tiền trực tuyến mọi lúc mọi nơi 24/24"
							+ "\n- An toàn với cổng thanh toán của ngân hàng"
							+ "\n- Được hưởng toàn bộ khuyến mại như các thuê bao thông thường."
							+ "\n\n* Chú ý: Chỉ áp dụng thuê bao trả trước và những thẻ ATM đã đăng ký dịch vụ Thanh toán trực tuyến");
			products.products.add(p1);

			Product p2 = new Product(
					2,
					"World cup",
					"“Sôi động cùng Vietnamobile” là dịch vụ cung cấp các thông tin giải trí, tin tức thời sự tổng hợp. Dịch vụ là gói cung cấp thông tin tổng hợp về: Tin tức về văn hoá, thể thao, kinh tế, xã hội … Khi khách hàng đăng ký nhận gói tin hàng ngày sẽ được tham gia trả lời các câu hỏi của dịch vụ “Sôi động cùng Vietnamobile”- “Sôi động cùng Vietnamobile” là chương trình khuyến mại của dịch vụ “Bản tin nhanh” Theo đó khi khách hàng đăng ký nhận gói tin nhanh sẽ được tham gia trả lời câu hỏi tích điểm và mã dự thưởng để tham gia quay thưởng may mắn cho chương trình. Khách hàng có thể tham gia trả lời các câu hỏi xoay quanh chủ đề về văn hóa, xã hội, thể thao...\n\n- Sau khi nhắn tin đăng ký tham gia chương trình, khách hàng được tham gia trả lời những câu hỏi trên sẽ có thêm cơ hội tích điểm để tăng cơ hội trúng thưởng. Phí nhắn tin tham gia trả lời câu hỏi là 5.000 đồng/SMS …");
			products.products.add(p2);

			String str5 = gson.toJson(products);

			writeFile(str5, Constants.TXT_PRODUCT_FILE_NAME);

			// ----------------------------------
			Promotions promotions = new Promotions();
			Promotion p11 = new Promotion(
					1,
					"Nạp tiền trực tuyến",
					"Vietnamobile trân trọng giới thiệu dịch vụ Nạp Tiền Trực Tuyến - phối hợp với đối tác Banknet và SmartLink. Đây là dịch vụ nạp tiền điện thoại được cung cấp cho các khách hàng là thuê bao trả trước và trả sau.\n"
							+ "Lợi ích nạp tiền điện thoại trực tuyến:"
							+ "\n\n- Nạp tiền điện thoại không cần thẻ cào & không cần mã thẻ"
							+ "\n- Số tiền được nạp thẳng vào tài khoản điện thoại của bạn ngay khi giao dịch hoàn thành."
							+ "\n- Nạp tiền trực tuyến mọi lúc mọi nơi 24/24"
							+ "\n- An toàn với cổng thanh toán của ngân hàng"
							+ "\n- Được hưởng toàn bộ khuyến mại như các thuê bao thông thường."
							+ "\n\n* Chú ý: Chỉ áp dụng thuê bao trả trước và những thẻ ATM đã đăng ký dịch vụ Thanh toán trực tuyến",
					"06/06/2013-06/08/2014");
			promotions.promotions.add(p11);

			Promotion p12 = new Promotion(
					2,
					"World cup",
					"“Sôi động cùng Vietnamobile” là dịch vụ cung cấp các thông tin giải trí, tin tức thời sự tổng hợp. Dịch vụ là gói cung cấp thông tin tổng hợp về: Tin tức về văn hoá, thể thao, kinh tế, xã hội … Khi khách hàng đăng ký nhận gói tin hàng ngày sẽ được tham gia trả lời các câu hỏi của dịch vụ “Sôi động cùng Vietnamobile”- “Sôi động cùng Vietnamobile” là chương trình khuyến mại của dịch vụ “Bản tin nhanh” Theo đó khi khách hàng đăng ký nhận gói tin nhanh sẽ được tham gia trả lời câu hỏi tích điểm và mã dự thưởng để tham gia quay thưởng may mắn cho chương trình. Khách hàng có thể tham gia trả lời các câu hỏi xoay quanh chủ đề về văn hóa, xã hội, thể thao...\n\n- Sau khi nhắn tin đăng ký tham gia chương trình, khách hàng được tham gia trả lời những câu hỏi trên sẽ có thêm cơ hội tích điểm để tăng cơ hội trúng thưởng. Phí nhắn tin tham gia trả lời câu hỏi là 5.000 đồng/SMS …",
					"06/06/2013-06/08/2014");
			promotions.promotions.add(p12);

			String str6 = gson.toJson(promotions);

			writeFile(str6, Constants.TXT_PROMOTION_FILE_NAME);

			// ---------------------------------
			Services services = new Services();
			Service service1 = new Service(1, "Starter Kit", "06/08/2013 8:20",
					"2", "0923323232", "06/08/2013 8:20", "TrangNH");
			services.services.add(service1);
			Service service2 = new Service(2, "CRBT", "19/06/2013 8:20", "4",
					"0923323232", "19/06/2013 8:20", "TrangNH");
			services.services.add(service2);
			Service service3 = new Service(3, "Voice mail", "10/09/2012 8:20",
					"2", "0923323232", "10/09/2012 8:20", "TrangNH");
			services.services.add(service3);
			Service service4 = new Service(4, "MCA", "10/10/2010 8:20", "2",
					"0923323232", "10/10/2010 8:20", "TrangNH");
			services.services.add(service4);
			Service service5 = new Service(5, "Airtime", "06/08/2013 8:20",
					"2", "0923323232", "06/08/2013 8:20", "TrangNH");
			services.services.add(service5);

			String str7 = gson.toJson(services);

			writeFile(str7, Constants.TXT_SERVICE_FILE_NAME);

			// ---------------------------------------

			Orders orders = new Orders();

			Order o4 = new Order(4, "Cty CP Hmobile", "06/05/2013",
					"08/05/2013", "Trang NH", "100", "100", "100", "100",
					"100", "", "Pending");
			orders.orders.add(o4);

			Order o3 = new Order(3, "CH Anh Xuânh", "06/05/2013", "08/05/2013",
					"Trang NH", "100", "100", "100", "100", "100", "",
					"Pending");
			orders.orders.add(o3);

			Order o2 = new Order(2, "CH Phú Thịnh", "06/05/2013", "08/05/2013",
					"Trang NH", "100", "100", "100", "100", "100", "",
					"Pending");
			orders.orders.add(o2);

			Order o1 = new Order(1, "CH Tiến Oanh", "06/05/2013", "08/05/2013",
					"Trang NH", "100", "100", "100", "100", "100", "fast",
					"Pending");
			orders.orders.add(o1);

			String str8 = gson.toJson(orders);

			writeFile(str8, Constants.TXT_ORDERBY_FILE_NAME);
			
			orders = new Orders();
			orders.orders.add(o1);
			orders.orders.add(o2);
			orders.orders.add(o3);
			orders.orders.add(o4);
			
			String str9 = gson.toJson(orders);
			writeFile(str9, Constants.TXT_ORDERD_FILE_NAME);

			isFirst = false;
		}
		/*
		 * 21.011688,105.820757 52 Trung Liệt, Đống Đa, Hà Nội | Cty CP Hmobile
		 * 20.992705,105.850665| số 750 Trương Định, Hoàng Mai, Hà Nội | Anh
		 * Xuân 21.035235,105.910676| 52 Sài Đồng - Long Biên - HN|CH Nguyễn
		 * Phương Anh 20.984146,105.793142|67 Trần Phú - Hà Đông - Hà Nội|CH Phú
		 * Thịnh 21.043481,105.842317|Số 78c Nguyễn trường Tộ, Hà Nội|CH Tiến
		 * Oanh
		 */

		new Thread() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				finally {
					startActivity(new Intent(Splash.this, ActivityLogin.class));
					finish();
				}
			}
		}.start();
	}

	String FIRST_TIME = "firsttime";
	boolean isFirst = true;

	public void saveUIState() {

		SharedPreferences saveUI = getPreferences(Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = saveUI.edit();

		editor.putBoolean(FIRST_TIME, isFirst);
		editor.commit();
	}

	public void restoreUIState() {
		SharedPreferences savedUI = getPreferences(Activity.MODE_PRIVATE);
		isFirst = savedUI.getBoolean(FIRST_TIME, true);
	}

	@Override
	public void onPause() {
		super.onPause();
		saveUIState();
	}

}
