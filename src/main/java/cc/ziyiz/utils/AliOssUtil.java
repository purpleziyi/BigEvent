package cc.ziyiz.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.InputStream;

// Change the demo code to a utility-class
public class AliOssUtil {

    // Endpoint: Beijing
    private static final String ENDPOINT = "https://oss-cn-beijing.aliyuncs.com";

    private static final String ACCESS_KEY_ID = " ";    //hide sensitive info
    private static final String ACCESS_KEY_SECRET = " ";  //hide sensitive info

    private static final String BUCKET_NAME = "big-event-ziyiz";

    public static String uploadFile(String objectName, InputStream in) throws Exception {

        String url = "";
        // Create an OSS(object storage service)-Client instance.
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID,ACCESS_KEY_SECRET);

        try {
            String content = "Hello OSS，ziyi";

            // Create a PutObjectRequest object
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, objectName, in);
  

            // upload String
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            // URL-format: https://big-event-ziyiz.oss-cn-beijing.aliyuncs.com/001.png
            url = "https://"+BUCKET_NAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;

        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return url;
    }
}
