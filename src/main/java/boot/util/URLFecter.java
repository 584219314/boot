package boot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/*   
 *  合肥工业大学 管理学院 qianyang 1563178220@qq.com
 */
public class URLFecter {
    public static String uRLParser (HttpClient client, String url) throws Exception {
        //获取网站响应的html，这里调用了HTTPUtils类
        HttpResponse response = AbstractHTTPUtils.getRawHtml(client, url);      
        //获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();
        //如果状态响应码为200，则获取html实体内容或者json文件
        String entity = null;
        if(statusCode == 200){
            entity = EntityUtils.toString (response.getEntity(),"utf-8");    
            EntityUtils.consume(response.getEntity());
        }else {
            //否则，消耗掉实体
            EntityUtils.consume(response.getEntity());
        }
        
		return entity;
    }
    
    private static void test2() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String urlStr = "http://localhost:8000/oauth/token?scope=read&grant_type=client_credentials";
		// 用户名:密码
		String encoding = new String(Base64.encodeBase64(StringUtils.getBytesUtf8("test_client:test_secret")));
		try {
			HttpPost httpget = new HttpPost(urlStr);
			// 向header中设置参数
			httpget.addHeader("Authorization", "Basic " + encoding);
			CloseableHttpResponse response = httpclient.execute(httpget);
			int status = response.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == status) {
				HttpEntity entity = response.getEntity();
				if (null == entity) {
					return;
				}
				// Document doc = Jsoup.parse(entity.getContent(), "UTF-8", ""); 可直接用jsoup接收为网页
				// entity.getContent内容流, 该api返回的是json字符串
				BufferedReader isr = new BufferedReader(new InputStreamReader(entity.getContent()));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while ((line = isr.readLine()) != null) {
					sb.append(line);
				}
				// 接口返回的是json数据
				JSONObject objs = JSONObject.parseObject(sb.toString());
				System.out.println(objs.toString());
				// TODO 根据业务需要处理数据
			}
		} finally {
			httpclient.close();
		}
	}
    public static void main(String[] args) {
		
    	try {
			test2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
