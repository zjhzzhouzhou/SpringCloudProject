package com.dyhospital.cloudhis.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * 描述：直接执行操作系统的命令
 * 
 * @author Administrator
 *
 */
public class FileTool
{
	public static Log log = LogFactory.getLog(FileTool.class);
	private FileTool()
	{
	}
	private static String ENCODING = "UTF-8" ;
	private static int OS;
	static
	{
		String os = System.getProperty("os.name");
		if (os.toLowerCase().lastIndexOf("windows") >= 0)
		{
			OS = 1;
		}
		if (!StringUtils.isBlank(System.getProperty("sun.jnu.encoding")))
		{
			ENCODING = System.getProperty("sun.jnu.encoding");
		}
	}

	/**
	 * 剪切文件，直接调用操作系统的命令
	 * 
	 * @param sourceFile  	源文件
	 * @param targetPath	目的文件路径
	 * @param targetFileName	目的文件名
	 * @return 成功则返回新的文件名，失败则返回空。
	 * @throws Exception
	 */
	public static String moveFile(String sourceFile, String targetPath,
			String targetFileName) throws Exception
	{
		if (StringUtils.isBlank(targetFileName))
		{
			targetFileName = FilePathHelper.getFileNameFromPath(sourceFile);
		}
		targetPath = FilePathHelper.addSeparator(targetPath) + targetFileName  ;
		moveFile(sourceFile, targetPath) ;
		return targetFileName;
	}
	
	/**
	 * 剪切文件，直接调用操作系统的命令
	 * @param sourceFile  	源文件
	 * @param targetPath	目的文件路径
	 * @return 
	 * @throws Exception
	 */
	public static String moveFile2Dir(String sourceFile ,String targetPath ) throws Exception
	{
		return moveFile(sourceFile, targetPath, null);
	}
	/**
	 * 
	 * @param sourceFile  源文件
	 * @param targetFile  目的文件
	 * @throws Exception
	 */
	public static void moveFile(String sourceFile ,String targetFile) throws Exception
	{
		FilePathHelper.mkdirs4File(targetFile);
		
//		sourceFile = StringUtils.overlay(sourceFile, "\"", sourceFile
//				.lastIndexOf(FilePathHelper.SEPARATOR) + 1, sourceFile
//				.lastIndexOf(FilePathHelper.SEPARATOR) + 1)
//				+ "\"";
//		targetFile = StringUtils.overlay(targetFile, "\"", targetFile
//				.lastIndexOf(FilePathHelper.SEPARATOR) + 1, targetFile
//				.lastIndexOf(FilePathHelper.SEPARATOR) + 1)
//				+ "\"";
		
		// 组装命令
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("move /Y ").append(
					FilePathHelper.formatDir2Win(sourceFile)).append(" ")
					.append(FilePathHelper.formatDir2Win(targetFile))
					.toString();
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("mv -fu ").append(sourceFile)
					.append(" ").append(targetFile).toString();
		}

		exeOSCommond(comm);
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static void deleteFile(String filePath) throws Exception
	{
		if (!fileIsExists(filePath)) {
            return ;
        }
		// 组装命令
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("del /F ").append(FilePathHelper.formatDir2Win(filePath))
					.toString();
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("rm -f ").append(filePath)
					.toString();
		}
		exeOSCommond(comm);
	}

	/**
	 * 删除目录
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception 
	 */
	public static void deleteDir(String filePath) throws Exception
	{
		if (!fileIsExists(filePath)) {
            return ;
        }
		// 组装命令
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("rmdir /S /Q ").append(FilePathHelper.formatDir2Win(filePath))
					.toString();
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("rm -rf ").append(filePath)
					.toString();
		}
		exeOSCommond(comm);
	}
	
	/**
	 * 拷贝文件
	 * @param src
	 * @param dest
	 * @throws Exception
	 */
	public static void copyFile(String src,String dest)throws Exception
	{
		FilePathHelper.mkdirs4File(dest);
		
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("copy /Y ").append(FilePathHelper.formatDir2Win(src)).append(FilePathHelper.formatDir2Win(" "+dest))
					.toString();
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("cp -ruf ").append(src+" "+dest)
					.toString();
		}
		exeOSCommond(comm);
	}
	
	/**
	 * 视频文件加关键帧
	 * @throws Exception
	 */
	public static void yamdi(String srcFile,String destFile)throws Exception{
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("yamdi -i ").append(FilePathHelper.formatDir2Win(srcFile)).append(FilePathHelper.formatDir2Win(" -o "+destFile))
					.toString();
			log.info(comm[2]);
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("yamdi -i ").append(srcFile+" -o "+destFile)
					.toString();
		}
		exeOSCommond(comm);
	}

	/**
	 * 递归创建目录
	 * @param path
	 * @throws Exception
	 */
	public static void createDir(String path)throws Exception{
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("mkdir ").append(FilePathHelper.formatDir2Win(path))
					.toString();
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("mkdir -p ").append(path)
					.toString();
		}
		exeOSCommond(comm);
	}
	
	/**
	 * 执行操作系统的命令
	 * 
	 * @param comm
	 * @throws Exception
	 */
	public static void exeOSCommond(String[] comm) throws Exception
	{
		log.info("文件迁移命令执行开始==========>>>" + comm[0] + " " + comm[1] + " " + comm[2]);
		Process myproc = null;
		BufferedReader buf = null ;
		try
		{
			myproc = Runtime.getRuntime().exec(comm);
			try
			{
				// 等待执行完再往后执行
				myproc.waitFor();
				buf = new BufferedReader(new InputStreamReader(
						myproc.getErrorStream(),ENCODING));

				String str = "";
				StringBuilder sb = new StringBuilder();
				while ((str = buf.readLine()) != null)
				{
					sb.append(str);
				}

				if (StringUtils.isNotBlank(sb.toString()))
				{
					log.info("文件迁移命令执行异常==========>>>" + sb.toString());
					throw new Exception(String.format(
							"execute command [%s] failure! reason : %s",
							StringUtils.join(comm, ' '), sb.toString()));
				}
			} catch (InterruptedException e)
			{
				log.info("文件迁移命令执行异常==========>>>" + e);
				throw new Exception(String.format(
						"execute command [%s] Exception!", StringUtils.join(
								comm, ' ')), e);
			}
		} catch (IOException e)
		{
			log.info("文件迁移命令执行异常==========>>>" + e);
			throw new Exception(String.format(
					"execute command [%s] Exception!", StringUtils.join(comm,
							' ')), e);
		}
		finally
		{
			if (null != buf) {
                try
                {
                    buf.close();
                } catch (IOException e)
                {
                }
            }
			if(null != myproc) {
                myproc.destroy();
            }
		}
		log.info("文件迁移命令执行结束==========>>>");
	}
	
	/**
	 * 判断目录是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean fileIsExists(String filePath)
	{
		File tempFile = new File(filePath);
		return tempFile.exists();
	}
	
	/**
	 * 合并视频文件
	 */
	public static void mergeFile(ArrayList<String> src, String dest)
	{
		String[] comm = new String[3];
		if (OS == 1)
		{
			File file = new File(FilePathHelper.formatDir2Win(dest));
			String dir = file.getParent();
			File tempdir = new File(dir);
			if(!tempdir.exists()){
				tempdir.mkdirs();
			}
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			StringBuilder sb = new StringBuilder();
			sb.append("copy /b ");
			for(String cmd : src){
				sb.append(FilePathHelper.formatDir2Win(cmd)).append("+");
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(" " + FilePathHelper.formatDir2Win(dest));
			comm[2] = sb.toString();
			log.info(comm[2]);
		} else
		{
			File file = new File(dest);
			String dir = file.getParent();
			File tempdir = new File(dir);
			if(!tempdir.exists()){
				tempdir.mkdirs();
			}
			
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			
			StringBuilder sb = new StringBuilder();
			sb.append("cat ");
			for(String cmd : src){
				sb.append(cmd).append(" ");
			}
			sb = sb.deleteCharAt(sb.length() - 1);
			sb.append(" > " + dest);
			comm[2] = sb.toString();
			
		}
		try {
			exeOSCommond(comm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设备检测
	 * @param ip
	 * @throws Exception
	 */
	public static void ping(String ip)throws Exception{
		String[] comm = new String[3];
		if (OS == 1)
		{
			comm[0] = "cmd.exe";
			comm[1] = "/c";
			comm[2] = new StringBuilder().append("ping -n 4 ").append(ip).toString();
			log.info(comm[2]);
		} else
		{
			comm[0] = "/bin/sh";
			comm[1] = "-c";
			comm[2] = new StringBuilder().append("ping -c 4 ").append(ip).toString();
		}
		exeOSCommond(comm);
	}
	
	/**
	 * 根据内容ID获得内容的三级存储目录，前后都带"/",如果内容ID不足10为前面补0
	 * @return
	 */
	public static String getContentThreeDir(String contID)
	{
		contID = StringUtils.leftPad(contID, 10, '0') ;
		return "/"
				+ contID.substring(0, contID.length() - 6)
				+ "/"
				+ contID.substring(contID.length() - 6, contID
						.length() - 3)
				+ "/"
				+ contID.substring(contID.length() - 3, contID
						.length()) + "/";
	}
	
	/**
	 * 获取元数据相对路径
	 * @param contId
	 * @param srcFileNode 源数据挂载点
	 * @return
	 */
	public static String getContentFilePath(String contId, String srcFileNode)
	{
		String contentDir = getContentThreeDir(contId);
		return "/" + srcFileNode + contentDir;
	}
	
	/**
	 * 判断文件是否需要解压
	 * @param fileName
	 * @return
	 */
	public static boolean isZipFile(String fileName)
	{
		if (fileName == null) {
            return false;
        }
		fileName = fileName.toLowerCase() ;
		return fileName.endsWith(".zip");
	}
	
	 /**
     * 根据byte数组，生成文件 
     * @param bfile 文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
	public static File byte2File(byte[] bfile){
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file = new File("temp.jpg");
		try{
			fos=new FileOutputStream(file);
			bos=new BufferedOutputStream(fos);
			bos.write(bfile);
			return file;
		} 
		catch(Exception e){
			log.error("字节流转文件出错"+e);
			return null;
        }
		finally{
			try{
				if(bos != null){
					bos.close(); 
				}
				if(fos != null){
					fos.close();
				}
			}
			catch(Exception e){
				log.error("字节流转文件出错"+e);
				return null;
			}
		}
    }

}
