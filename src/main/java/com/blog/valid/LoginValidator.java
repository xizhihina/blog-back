package com.blog.valid;

import com.blog.param.LoginParam;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 验证登录请求体
 */
public class LoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LoginParam.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target==null){
            errors.rejectValue("",null,"用户不能为空");
            return;
        }
        LoginParam loginParam=(LoginParam) target;
        if(loginParam.getId()==0){
            errors.rejectValue("id",null,"用户id不能为0");
        }
        if(loginParam.getPassword()==null|| loginParam.getPassword().equals("")){
            errors.rejectValue("password",null,"用户密码不能为空");
        }
    }
}
