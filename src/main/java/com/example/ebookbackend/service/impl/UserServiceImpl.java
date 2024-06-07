package com.example.ebookbackend.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.example.ebookbackend.DTO.StatisticsUserDTOInterface;
import com.example.ebookbackend.DTO.UserSignupDTO;
import com.example.ebookbackend.dao.UserDao;
import com.example.ebookbackend.domain.Result;
import com.example.ebookbackend.domain.User;
import com.example.ebookbackend.domain.UserAuth;
import com.example.ebookbackend.service.UserService;
import com.example.ebookbackend.utils.JwtUtil;
import com.example.ebookbackend.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public Result verifyByUsername(String username, String password) {
        UserAuth userAuth = userDao.getAuthByUsername(username, password);
        if(userAuth == null) {
            return Result.failure("用户名或密码错误", false);
        }
        int state = userAuth.getValid();
        if(state == 0) {
            return Result.failure("用户被封禁", false);
        } else {
            Map<String, Object> claims = new HashMap<>();
            SessionUtil.setSession(userAuth);
            claims.put("username", userAuth.getUsername());
            String jwt = JwtUtil.getJwt(claims);
            return Result.success(jwt);
        }
    }

    @Override
    public Result insertNewUser(UserSignupDTO user) {
        User old_user = userDao.getUserByUsername(user.getUsername());
        if(old_user != null) {
            return Result.failure("username already exists!", false);
        }
        User tmp_user = userDao.insertNewUser(user);
        if(tmp_user.getId() == 0) {
            return Result.failure("signup failure.", null);
        } else {
            return Result.success(tmp_user);
        }
    }

    @Override
    public Result updateProfile(User user) {
        Integer col = userDao.updateProfile(user);
        if(col == 0) {
            return Result.failure("signup failure.", null);
        } else {
            return Result.success(col);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public List<StatisticsUserDTOInterface> getUserStat(Integer uid, String startTime, String endTime) {
        return userDao.getUserStatInfo(uid, startTime, endTime);
    }

    @Override
    public Result uploadAvatar(MultipartFile avatar) throws Exception {
        InputStream inputStream = avatar.getInputStream();
        String originalFilename = avatar.getOriginalFilename();
        String filename = UUID.randomUUID().toString() + originalFilename;

        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "bookstore-hwq";
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = "avatar/" + filename;

        String url = "";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);
        boolean isFinish = false;
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            // 创建PutObject请求。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
            isFinish = true;
        }catch (OSSException oe) {
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
            if(isFinish) {
                return Result.success(url);
            } else {
                return Result.failure("upload message failed.", null);
            }
        }
    }

    @Autowired
    UserDao userDao;
}
