package ir.ac.aut.ceit.pervasive.common;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * An exception handler which reports any uncaught exceptions to the context
 * API's website, in order to facilitate remote diagnostics of user errors.
 *
 * @author Atefeh Zareh
 */
public class ExceptionHandler implements UncaughtExceptionHandler {

    private UncaughtExceptionHandler defaultUEH;

    private String appname, url, version, imei;

    public ExceptionHandler(String appname, String url, String version, String imei) {
        this.appname = appname;
        this.url = url;
        this.version = version;
        this.imei = imei;
        this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
    }

    public ExceptionHandler(Context context) {
        this(getAppName(context), "http://atefeh.zareh.name/android/upload",
                getVersionName(context), getIMEI(context));
    }

    private static String getVersionName(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException ex) {
            return "Unknown";
        }
    }

    private static String getAppName(final Context context) {
        try {
            return context.getPackageManager().getApplicationLabel(context
                    .getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .applicationInfo).toString().replaceAll("[^A-Za-z]", "");
        } catch (NameNotFoundException ex) {
            return "Unknown";
        }
    }

    private static String getIMEI(final Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    public void uncaughtException(Thread t, Throwable e) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        String stacktrace = result.toString();
        printWriter.close();
        String filename = timestamp + ".stacktrace";

        sendToServer(stacktrace, filename);

        defaultUEH.uncaughtException(t, e);
    }

    private void sendToServer(String stacktrace, String filename) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("x-application", appname + "-exception");
        httpPost.setHeader("x-version", version);
        httpPost.setHeader("x-imei", imei);

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("filename", filename));
        nvps.add(new BasicNameValuePair("stacktrace", stacktrace));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            httpClient.execute(httpPost);
        } catch (IOException e) {
            // Do nothing
        }
    }
}
