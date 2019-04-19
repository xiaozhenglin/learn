package com.chehaha.api.wechat.xiao;

public interface OfficialException {
	/**
	 *  BF
	 */
	String FILE_NOT_EXIST ="BF010101";  //文件不存在
	String PHOTO_TYPE_ERROR = "BF010102";  //图片格式不是jpg或者jpeg
	String PHOTO_UPLOAD_WECHAT="BF010103"; //图片上传失败
	String MENU_PARAM_ERROR = "BF010104" ; //创建菜单传入参数格式错误
	String MENU_CREATE_ERROR ="BF010105"; //创建菜单失败
	String WECHAT_CODE_IS_EXIST = "BF010106"; //指令码已经存在
	String SAVE_CODE_ERROR = "BF010107" ;  //保存指令失败
	String PARAM_IS_NULL = "BF010108";  //指令参数为空
	String NOT_FIND_CODE = "BF010109"; //找不到指令
	String NEWCODE_IS_EXIST = "BF010110"; //新指令已经存在于其它指令,不能作为修改
	String DELETE_CODE_ERROR = "BF010111"; //删除指令失败
}
