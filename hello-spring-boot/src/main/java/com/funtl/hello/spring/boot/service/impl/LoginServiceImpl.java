package com.funtl.hello.spring.boot.service.impl;

import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.mapper.YbUserMapper;
import com.funtl.hello.spring.boot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private YbUserMapper ybUserMapper;

    private static YbUserMapper ybUserMappers;

    private static YbUserMapper ybUserMapper2;

    private static LoginServiceImpl loginServiceImpl;

    @Value("${cyh.fromSystemCode}")
    String fromSystemCode;


    @PostConstruct
    public void init(){
        ybUserMappers=ybUserMapper;
    }

    public static LoginServiceImpl getInstance(YbUserMapper userMapper){
        if (loginServiceImpl==null){
            synchronized (LoginServiceImpl.class){
                if (loginServiceImpl==null){
                    loginServiceImpl=new LoginServiceImpl();
                }
            }
        }
        ybUserMapper2=userMapper;
        return loginServiceImpl;
    }



    @Override
    public YbUser login(YbUser ybUser) {
        System.out.println(fromSystemCode);
        Example example=new Example(YbUser.class);
        example.createCriteria().andEqualTo("username", ybUser.getUsername());
        List<YbUser> select = ybUserMapper.selectByExample(example);
        System.out.println(select);
        YbUser user=select.get(0);
        //账户错误
        if(user==null){
            //  TODO ,,,
        }
        else{
            //密码正确
//            if(user.getPassword().equals(DigestUtils.md5DigestAsHex(ybUser.getPassword().getBytes()))){
//                return user;
//            }
//            //密码错误
//            else {
//                //  TODO ...
//            }
        }
        return null;
    }

    public static void getUsername(){
        Example example=new Example(YbUser.class);
        example.createCriteria().andEqualTo("username", "gjh");
        YbUser ybUser = ybUserMappers.selectOneByExample(example);
        System.out.println(ybUser);
    }


    public void getUsername2(){
        Example example=new Example(YbUser.class);
        example.createCriteria().andEqualTo("username", "gjh");
        YbUser ybUser = ybUserMapper2.selectOneByExample(example);
        System.out.println(ybUser);
    }
}
