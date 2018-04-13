package com.article.recommend.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: SerializeUtil  
 * @Description: 序列化  
 * @author sw  
 * @date 2018年4月13日
 */
public class SerializeUtil {
    private static Logger log = LoggerFactory.getLogger(SerializeUtil.class);  
    /**
     * 
     * @Title: serialize  
     * @Description: 序列化对象  
     * @param object
     * @return       
     * @return byte[]    
     * @author sw
     * @throws
     */
    public static byte[] serialize(Object object) {  
        ObjectOutputStream oos = null;  
        ByteArrayOutputStream baos = null;  
        byte[] bytes = null;
        try {  
            // 序列化  
            baos = new ByteArrayOutputStream();  
            oos = new ObjectOutputStream(baos);  
            oos.writeObject(object);  
            bytes = baos.toByteArray();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (oos != null) {  
                    oos.close();  
                }  
                if (baos != null) {  
                    baos.close();  
                }  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
        return bytes;  
    }  
    /***
     * 
     * @Title: unserialize  
     * @Description: 反序列化
     * @param bytes
     * @return       
     * @return Object    
     * @author sw
     * @throws
     */
    public static Object unserialize(byte[] bytes) { 
        Object obj = null; 
        ByteArrayInputStream bais = null;  
        ObjectInputStream ois =null;
        try {  
            // 反序列化  
            bais = new ByteArrayInputStream(bytes);  
            ois= new ObjectInputStream(bais);  
            obj = ois.readObject();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally {
        	  try {
        		if(null!=ois) {
        			ois.close();
        		}
        		if(null!=bais) {
        			bais.close();
        		}
			} catch (IOException e) {
				e.printStackTrace();
			}   
        }
        return obj;  
    }  
    /**
     * 
     * @Title: serialize  
     * @Description: list   
     * @param value
     * @return       
     * @return byte[]    
     * @author sw
     * @throws
     */
    public static <T> byte[] serialize(List<T> value) {  
        if (value == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv=null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            for(T obj : value){  
                os.writeObject(obj);  
            }  
            os.writeObject(null);  
            os.close();  
            bos.close();  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
            close(os);
            close(bos);
        }  
        return rv;  
    }
    /***
     * 
     * @Title: unserializeForList  
     * @Description: 反序列化  
     * @param in
     * @return       
     * @return List<T>    
     * @author sw
     * @throws
     */
    @SuppressWarnings("unchecked")
	public static <T> List<T> unserializeForList(byte[] in) {  
        List<T> list = new ArrayList<T>();  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if(in != null) {  
                bis=new ByteArrayInputStream(in);  
                is=new ObjectInputStream(bis);  
                while (true) {  
                    T obj = (T) is.readObject();  
                    if(obj == null){  
                        break;  
                    }else{  
                        list.add(obj);  
                    }  
                }  
                is.close();  
                bis.close();  
            }  
        } catch (IOException e) {  
            log.warn("Caught IOException decoding %d bytes of data",  
                    in == null ? 0 : in.length, e);  
        } catch (ClassNotFoundException e) {  
            log.warn("Caught CNFE decoding %d bytes of data",  
                    in == null ? 0 : in.length, e);  
        } finally {  
            close(is);  
            close(bis);  
        }  
        return list;  
    }  
    protected static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
            } catch (Exception e) {  
                log.info("Unable to close %s", closeable, e);  
            }  
        }  
    }

}
