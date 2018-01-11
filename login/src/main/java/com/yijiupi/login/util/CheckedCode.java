package com.yijiupi.login.util;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yijiupi.login.controller.UserController;
import org.apache.log4j.Logger;
import org.omg.CORBA.SystemException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @deprecated 用于生成验证码,验证码放在session中，key为KAPTCHA_SESSION_KEY。
 * @author fengqigui
 * @Date 2017/11/7 12:46
 */
public class CheckedCode {

    private static final Logger LOGGER = Logger.getLogger(CheckedCode.class);

    public CheckedCode (){}

    /**
     *  向客前台输出验证码
     * @param captchaProducer   :   验证码生成实例
     * @param request
     * @param response
     * @throws SystemException
     */
    public static void putValidateCode(Producer captchaProducer, HttpServletRequest request, HttpServletResponse response) throws SystemException {

        ServletOutputStream out = null;

        try {

            HttpSession session = request.getSession();

            response.setDateHeader("Expires", 0);

            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

            response.addHeader("Cache-Control", "post-check=0, pre-check=0");

            response.setHeader("Pragma", "no-cache");

            response.setContentType("image/jpeg");

            String capText = captchaProducer.createText();

            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

            BufferedImage image = captchaProducer.createImage(capText);

            out = response.getOutputStream();

            ImageIO.write(image, "jpg", out);

            out.flush();

        } catch (IOException e){

            LOGGER.error("验证码生成失败"+e.getMessage());

            throw new RuntimeException(e.getMessage());

        } finally {
            if (null != out) {

                try {

                    out.close();

                } catch (IOException e) {

                }

            }

        }

    }


}
