package com.toutiao.web.common.util;

import com.google.gson.Gson;
import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.web.common.constant.syserror.RestfulInterfaceErrorCodeEnum;
import com.toutiao.web.common.restmodel.InvokeResult;
import com.toutiao.web.common.restmodel.NashResult;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * zhangjinglei 2017/9/6 下午2:53
 */
@Component
public class UploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadUtil.class);

    private static String qiniu_Access_key;
    @Value("${qiniu.access_key}")
    public void setQiniu_Access_key(String v){
        qiniu_Access_key=v;
    }

    private static String qiniu_Secret_key;
    @Value("${qiniu.secret_key}")
    public void setQiniu_Secret_key(String a) {
        qiniu_Secret_key = a;
    }

    private static String qiniu_bucketname;
    @Value("${qiniu.houseimg_buckname}")
    public void setQiniu_bucketname(String a) {
        qiniu_bucketname = a;
    }

    private static String qiniu_wapapp_bucketname;
    @Value("${qiniu.houseimg_wapapp_buckname}")
    public void setQiniu_wapapp_bucketname(String a) {
        qiniu_wapapp_bucketname = a;
    }

    private static String qiniu_houseimg_path;
    @Value("${qiniu.houseimg_directory}")
    public void setQiniu_houseimg_path(String a) {
        qiniu_houseimg_path = a;
    }


    private static String qiniu_headpic_path;
    @Value("${qiniu.headpic_directory}")
    public void setQiniu_headpic_path(String a) {
        qiniu_headpic_path = a;
    }

    @Value("${qiniu.img_domain}")
    public String qiniuImgDomain;

    private static Auth qiniu_auth=null;
    private static Configuration qiniu_conf;

    /**
     * 允许的文件类型
     */
    private static String allowFileType;

    @Value("${allow_file_type}")
    public void setAllowFileType(String allowFileType) {
        this.allowFileType = allowFileType;
    }

    /**
     * 上传图片大小限制
     */
    private static Integer maxLength;

    @Value("${img_maxlength}")
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    private static void init_qiniu(){
        qiniu_auth=Auth.create(qiniu_Access_key, qiniu_Secret_key);
        Zone z = Zone.zone1();
        qiniu_conf= new Configuration(z);

    }

    /**
     * 上传房源图片
     * @param filename
     * @param data
     * @return
     */
    public static String uploadImg(String filename,byte[] data){
        return upload(qiniu_houseimg_path,filename,data);
    }

    /**
     * 上传excel
     * @param filename
     * @param data
     * @return
     */
    public static String uploadErrorExcel(String filename,byte[] data){
        return upload("/errorexcel",filename,data);
    }

    private static String upload(String path,String filename,byte[] data){
        if(qiniu_auth==null){
            init_qiniu();
        }


        ///////////////////////指定上传的Zone的信息//////////////////
        //第一种方式: 指定具体的要上传的zone
        //注：该具体指定的方式和以下自动识别的方式选择其一即可
        //要上传的空间(bucket)的存储区域为华东时
        // Zone z = Zone.zone0();
        //要上传的空间(bucket)的存储区域为华北时
        // Zone z = Zone.zone1();
        //要上传的空间(bucket)的存储区域为华南时
        // Zone z = Zone.zone2();

        //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
        String fileKey =StringUtils.strip(path,"/")+"/"+StringUtils.strip(filename,"/");
        //创建上传对象
        UploadManager uploadManager = new UploadManager(qiniu_conf);
        String token = qiniu_auth.uploadToken(qiniu_bucketname);
        try {
            Response r = uploadManager.put(data, fileKey, token);

        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        return fileKey;
    }

    /**
     * 校验上传图片
     * @param substring
     * @return
     */
    public static NashResult checkFileName(String substring){

//        NashResult nashResult = new NashResult();
//        if(StringUtils.isBlank(substring)){
//            return nashResult.Fail("2","图片名称不正确");
//        }else{
//            String str = substring.toUpperCase().substring(1,substring.length());
//            if(ImgSuffixEnum.getByStr(str).getValue()==-1){
//                return nashResult.Fail("2","图片格式不正确");
//            }
//        }
        return null;
    }

    /**
     * 校验图片大小、类型
     * @param file
     * @return
     */
    private static boolean validateFile(MultipartFile file) {

        if (file.getSize() < 0 || file.getSize() > maxLength)
            return false;
        String filename = file.getOriginalFilename();

        String extName = filename.substring(filename.lastIndexOf("."));

        String[] allowExtName = allowFileType.split("\\|");
        if (allowExtName == null || allowExtName.length == 0
                || Arrays.binarySearch(allowExtName, extName) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 图片上传
     * @param file
     * @return
     */
    public static NashResult uploadImage(MultipartFile file) {

        NashResult nashResult = new NashResult();

        if(validateFile(file)) {//校验图片大小、图片格式
            if(qiniu_auth==null){
                init_qiniu();
            }

            String upToken = qiniu_auth.uploadToken(qiniu_bucketname);

            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone1());
            UploadManager uploadManager = new UploadManager(cfg);

            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = file.getContentType();
            //获得文件后缀名称
            String key = uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            try {
                byte[] data = file.getBytes();
                Response response = uploadManager.put(data, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                nashResult = NashResult.build(putRet);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            return nashResult.Fail("2","图片大小超出限制或图片格式不正确");
        }

        return nashResult;
    }

    /**
     * 获取图片分辨率
     * @param file
     * @return
     */
    public static String getImageResolution(MultipartFile file) {

        BufferedImage image = null;
        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int srcWidth = image .getWidth();      // 源图宽度
        int srcHeight = image .getHeight();    // 源图高度

        return srcWidth + "X" + srcHeight;//分滨率格式 400X600
    }

    /**
     * 用户头像图片上传（app）
     * @param file
     * @return
     */
    public static InvokeResult uploadImages(MultipartFile file) {

        if(qiniu_auth==null){
            init_qiniu();
        }

        String upToken = qiniu_auth.uploadToken(qiniu_wapapp_bucketname);

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);

        //生成uuid作为文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //获得文件类型（可以判断如果不是图片，禁止上传）
        String contentType = file.getContentType();
        //获得文件后缀名称
        String key = StringUtils.strip(qiniu_headpic_path,"/")+"/"+ uuid + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        InvokeResult result;
        try {
            byte[] data = file.getBytes();
            Response response = uploadManager.put(data, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            result = InvokeResult.build(putRet);
        } catch (IOException e) {
            logger.error("图片上传失败", e);
            return InvokeResult.Fail(RestfulInterfaceErrorCodeEnum.IMAGE_UPLOAD_FAIL);
        }

        return result;
    }

}
