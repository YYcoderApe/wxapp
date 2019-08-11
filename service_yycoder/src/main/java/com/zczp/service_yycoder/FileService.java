package com.zczp.service_yycoder;

import com.qiniu.common.QiniuException;

import java.io.File;
import java.util.Map;

public interface FileService {
    Map uploadFile(File file) throws QiniuException;
}
