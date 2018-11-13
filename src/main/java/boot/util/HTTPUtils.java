package boot.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpUtils;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHttpResponse;

/*   
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public abstract class HTTPUtils {
	public static HttpResponse getRawHtml(HttpClient client, String personalUrl) {
		// 获取响应文件，即html，采用get方法获取响应数据
		HttpGet getMethod = new HttpGet(personalUrl);
		// 设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect
		// Manager获取Connection超时时间、
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(10000).setSocketTimeout(10000)
				.setConnectionRequestTimeout(3000).build();
		getMethod.setConfig(requestConfig);
		// 设置请求头消息
		getMethod
				.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,
				HttpStatus.SC_OK, "OK");
		try {
			// 执行get方法
			response = client.execute(getMethod);
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			// getMethod.abort();
		}
		return response;
	}

	/**
	 * 设置代理ip
	 * 
	 * @throws IOException
	 */
	public static String setProxyIp() {
		String ipport = null;
		try {
			List<String> ipList = new ArrayList<String>();
			InputStream in = new FileInputStream(
					HTTPUtils.class.getClassLoader().getResource("/").getPath()+"/proxyip.txt");// 读取文件的数据。
			BufferedReader proxyIpReader = new BufferedReader(
					new InputStreamReader(in));
			String ip = "";
			while ((ip = proxyIpReader.readLine()) != null) {
				ipList.add(ip);
			}

			Random random = new Random();
			int randomInt = random.nextInt(ipList.size());
			ipport = ipList.get(randomInt);
			String proxyIp = ipport.substring(0, ipport.lastIndexOf(":"));
			String proxyPort = ipport.substring(ipport.lastIndexOf(":") + 1,
					ipport.length());
			System.out.println("设置代理ip为：" + proxyIp + "端口号为：" + proxyPort);
		} catch (Exception e) {
			System.out.println("重新设置代理ip");
			setProxyIp();
		}
		return ipport;
	}
}
