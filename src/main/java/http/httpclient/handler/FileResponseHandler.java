package http.httpclient.handler;

import http.utils.HttpConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import tools.FileUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: M˚Haonan
 * @Date: 2020-06-19 17:21
 * @Description: 对响应为文件的处理
 */
@Slf4j
public class FileResponseHandler extends AbstractResponseHandler{

    @Getter
    private String fileName;

    public FileResponseHandler(HttpEntity entity) {
        super(entity);
    }

    @Override
    public void onHandler(HttpResponse response) {
        try {
            //从Content-Disposition中获取文件名
            Header contentHeader = response.getFirstHeader("Content-Disposition");
            if (contentHeader != null) {
                HeaderElement[] elements = contentHeader.getElements();
                if (elements.length == 1) {
                    NameValuePair param = elements[0].getParameterByName("filename");
                    if (param != null) {
                        this.fileName = param.getValue();
                    }else {
                        this.fileName = FileUtils.generateName() + getSuffix(response);
                    }
                }
            }else {
                this.fileName = FileUtils.generateName() + getSuffix(response);
            }
            log.info("要下载的文件名称为{}", this.fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String path) {
        try {
            FileUtils.saveFile(path, this.fileName, entity.getContent());
            log.info("文件{}已保存在{}", this.fileName, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String path, String fileName) {
        try {
            FileUtils.saveFile(path, fileName, entity.getContent());
            log.info("文件{}已保存在{}", fileName, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getSuffix(HttpResponse response) {
        Header header = response.getFirstHeader("content-type");
        String suffix = HttpConstant.SUFFIX_MAP.get(header.getValue());
        return suffix == null ? "" : suffix;
    }

}
