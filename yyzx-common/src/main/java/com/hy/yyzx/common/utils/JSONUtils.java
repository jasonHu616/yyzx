/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hy.yyzx.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;


public class JSONUtils {
    public static String toJSONString(Object o) {
        return JSON.toJSONString(o);
    }

    public static Object parse(String text) {
        return JSON.parse(text);
    }

    public static <T> T parse(String text, Class<T> clz) {
        return JSON.parseObject(text, clz);
    }

    public static <T> List<T> parseList(String text, Class<T> clz) {
        return JSON.parseArray(text, clz);
    }

    public static Boolean is_json(String text){
        try{
            JSONObject jsonStr= JSONObject.parseObject(text);
            return  true;
        }catch (Exception e){
            return false;
        }
    }

}
