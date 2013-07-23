package your.package.name;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class uccw extends Activity {

  private TextView textview;
	
	// change this to your apk skin name
	private static final String UCCW_TEST_SKIN_APK = "your skins.apk";
	
	// Do not touch code that follows unless you know what you are doing
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		context = this;
		setContentView(R.layout.uccw);
		findViewById(R.id.buttonRepairSkin).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						showInstallableSkins();
					}

				});
		
		textview = (TextView) findViewById(R.id.dluccw);
		textview.setOnClickListener(new View.OnClickListener() {
		

			@Override
			public void onClick(View v) {
				
  			  final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=in.vineetsirohi.customwidget"));
        		  startActivity(intent);
  		 
  }
  
});

	}
	
	
	
	

	private class RepairSkinAsyncTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog mDialog;

		@Override
		protected void onPreExecute() {
			mDialog = ProgressDialog.show(context, "", "Processing...", true);
		}

		@Override
		protected Void doInBackground(Void... nothing) {
			String SDCARD_MYAPK_APK = Environment.getExternalStorageDirectory()
					.getPath() + File.separator + "my_temporary_skin_apk.apk";
			deleteOldSkin(SDCARD_MYAPK_APK);
			saveSkinToSdCard(SDCARD_MYAPK_APK);
			startAppInstaller(SDCARD_MYAPK_APK);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mDialog.dismiss();
			finish();
		}
	}

	/**
	 * 
	 */
	private void showInstallableSkins() {
		if (isSDcardAvailable()) {
			new RepairSkinAsyncTask().execute();
		} else {
			Toast.makeText(this, "SD card not available", Toast.LENGTH_LONG)
					.show();
		}

	}

	private void deleteOldSkin(String pathToSkin) {
		File file = new File(pathToSkin);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * @param assetManager
	 * @param in
	 * @param out
	 * @param pathToSkin
	 */
	private void saveSkinToSdCard(String pathToSkin) {
		AssetManager assetManager = getAssets();

		InputStream in = null;
		OutputStream out = null;

		try {
			in = assetManager.open(UCCW_TEST_SKIN_APK);
			try {
				out = new FileOutputStream(pathToSkin);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			byte[] buffer = new byte[1024];
			int read;
			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}

			in.close();
			in = null;

			out.flush();

			out.close();

			out = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param pathToSkin
	 */
	private void startAppInstaller(String pathToSkin) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(pathToSkin)),
				"application/vnd.android.package-archive");
		startActivity(intent);
	}

	private boolean isSDcardAvailable() {
		String state = Environment.getExternalStorageState();
		return state.contentEquals(Environment.MEDIA_MOUNTED)
				|| state.contentEquals(Environment.MEDIA_MOUNTED_READ_ONLY);
	}
}
